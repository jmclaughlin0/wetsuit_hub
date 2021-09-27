package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NeedEssentialsScraper implements IWetsuitScraper {

    private final WetsuitsRepository wetsuitsRepository;

    public NeedEssentialsScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {
        final String baseUrl = "https://needessentialsuk.com/collections/wetsuits-new-1";

        try {
            final Document doc = Jsoup.connect(baseUrl).get();

            Elements wetsuits = doc.getElementsByClass("Grid__Cell 1/1--phone 1/3--tablet-and-up 1/4--lap-and-up");

            for (Element element : wetsuits) {
                Wetsuit wetsuit = new Wetsuit();
                String productName = element.getElementsByClass("ProductItem__Title Heading").text();

                String price = element.getElementsByClass("ProductItem__Price Price Text--subdued").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                if (price.contains(" ")) {
                    String newPrice = price;
                    price = newPrice.split(" ")[0];
                }

                String webAddress = "https://needessentialsuk.com/" + element.getElementsByClass("ProductItem__ImageWrapper ProductItem__ImageWrapper--withAlternateImage").attr("href");

                String imageAddress = element.getElementsByClass("ProductItem__Image").get(1).attr("data-src").replace("{width}", "200").replace("//", "https://");

//              String sizes  = element.getElementsByClass("product__availability-sizes product-card__availability-sizes").get(0).cssSelector();


                StringFinder stringFinder = new StringFinder();

                wetsuit.setName(productName);
                wetsuit.setGender(stringFinder.genderFinder(productName));
                wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                wetsuit.setZipper(stringFinder.zipperFinder(productName));
                wetsuit.setWebAddress(webAddress);
                wetsuit.setImageAddress(imageAddress);
//              wetsuit.setSize(sizes);


                if (!price.isEmpty()) {
                    wetsuit.setPrice(Double.parseDouble(price));
                    wetsuitsRepository.save(wetsuit);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

