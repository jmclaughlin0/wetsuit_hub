package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SortedScraper implements IWetsuitScraper {

    private final WetsuitsRepository wetsuitsRepository;

    public SortedScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {

        final String baseUrl = "https://www.sortedsurfshop.co.uk/wetsuits.html?p=1&zip=743%2C745%2C744";

        for(int i=1; i<20; i++) {
            String newURL = baseUrl.replace("?p=1", "?p="+i);
            try {
                final Document doc = Jsoup.connect(newURL).get();

                Elements wetsuits = doc.getElementsByClass("item product col col-6 col-sm-6 col-md-4 col-lg-3");

                for (Element element : wetsuits) {
                    Wetsuit wetsuit = new Wetsuit();

                    String productName = element.getElementsByClass("product-item-link").text();

                    String price = element.getElementsByClass("price").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[0];
                    }

                    String webAddress = element.getElementsByClass("product-item-link").attr("href");

                    String imageAddress = element.getElementsByClass("product-image-photo").attr("data-amsrc");

//                    Elements sizes  = element.getElementsByAttribute("swatch-attribute-options");

                    StringFinder stringFinder = new StringFinder();

                    wetsuit.setName(productName);
                    wetsuit.setGender(stringFinder.genderFinder(productName));
                    wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                    wetsuit.setZipper(stringFinder.zipperFinder(productName));
                    if(!price.isEmpty()){
                        wetsuit.setPrice(Double.parseDouble(price));

                    }
                    wetsuit.setWebAddress(webAddress);
                    wetsuit.setImageAddress(imageAddress);
//                    wetsuit.setSize(sizes);

                    if (!wetsuitsRepository.findAll().toString().contains(wetsuit.toString())) {
                        wetsuitsRepository.save(wetsuit);
                    }
                }

            }catch (IOException e) {
                e.printStackTrace();
        }}
    }
}
