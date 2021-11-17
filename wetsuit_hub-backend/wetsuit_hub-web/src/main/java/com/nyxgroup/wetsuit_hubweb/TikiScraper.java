package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TikiScraper implements IWetsuitScraper{
    
    private final WetsuitsRepository wetsuitsRepository;

    public TikiScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {
        final String baseUrl = "https://www.tikisurf.co.uk/product-category/hardware/wetsuits/all-wetsuits/page/1/";

        try {
            for (int i = 1; i < 10; i++) {
                String newURL = baseUrl.replace("page/1/", "page/" + i + "/" );
                final Document doc = Jsoup.connect(newURL).get();

                Elements wetsuits = doc.getElementsByClass("product-item  spinner-circle palign-center  product_hover_enable product_hover_mob_enable");

                for (Element element : wetsuits) {
                    Wetsuit wetsuit = new Wetsuit();

                    String productName = element.getElementsByClass("product-title-link").get(0).text();

                    String price = element.getElementsByClass("woocommerce-Price-amount amount").text().replaceAll("£", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[0];
                    }

                    String webAddress = element.getElementsByClass("product-title-link").attr("href");

                    String imageAddress = element.getElementsByClass("attachment-shop_catalog size-shop_catalog wp-post-image").attr("src");

//                    Elements sizes  = element.getElementsByAttribute("swatch-attribute-options");

                    String outOfStock = element.getElementsByClass("out_of_stock_title").text();

                    StringFinder stringFinder = new StringFinder();

                    wetsuit.setName(productName);
                    wetsuit.setGender(stringFinder.genderFinder(productName));
                    wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                    wetsuit.setZipper(stringFinder.zipperFinder(productName));
                    wetsuit.setPrice(Double.parseDouble(price));
                    wetsuit.setWebAddress(webAddress);
                    wetsuit.setImageAddress(imageAddress);
//                    wetsuit.setSize(sizes);

                    if(outOfStock.isEmpty()){
                        wetsuitsRepository.save(wetsuit);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
