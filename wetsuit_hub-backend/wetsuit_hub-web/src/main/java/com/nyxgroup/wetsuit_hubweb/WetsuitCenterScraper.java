package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class WetsuitCenterScraper implements IWetsuitScraper {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitCenterScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public void getWetsuits(){
        final String baseUrl = "https://www.wetsuitcentre.co.uk/wetsuits.html?p=1&zip=744%2C745%2C743";


        try {

            for(int i = 1; i<30; i++) {
                String modifiedURL = baseUrl.replace(".html?p=1", ".html?p=" + i);
                final Document doc = Jsoup.connect(modifiedURL).get();

                Elements wetsuits = doc.getElementsByClass("item product col col-6 col-sm-4 col-md-3 col-lg-4 col-xl-3");

                for (Element element : wetsuits) {
                    Wetsuit wetsuit = new Wetsuit();

                    String productName = element.getElementsByClass("product-item-link").text();
                    String price = element.getElementsByClass("price").text().replace("£", "");

                    if (price.contains(" ")) {
                        price = price.split(" ")[1];
                    }

                    Scanner s = new Scanner(price);

                    if (!s.hasNextDouble()) {
                        System.out.println(productName + " is the faulty one");
                        continue;
                    }

                    String imageAddress = element.getElementsByClass("product-image-photo").toString().split(" ")[3].split("=")[1].replace('"', ' ');
                    String webAddress = element.getElementsByClass("product-item-link").attr("href");

                    StringFinder stringFinder = new StringFinder();

                    wetsuit.setGender(stringFinder.genderFinder(productName));
                    wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                    wetsuit.setZipper(stringFinder.zipperFinder(productName));

                    wetsuit.setName(productName);
                    wetsuit.setPrice(Double.parseDouble(price));
                    wetsuit.setImageAddress(imageAddress);
                    wetsuit.setWebAddress(webAddress);
                    wetsuit.setBrand(stringFinder.brandFinder(productName));

                    wetsuitsRepository.save(wetsuit);
                }
            }


        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
