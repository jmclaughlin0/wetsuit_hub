package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Locale;
import java.util.Scanner;

public class WetsuitCenterScraper implements IWetsuitScraper {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitCenterScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public void getWetsuits(){
        final String baseUrl = "https://www.wetsuitcentre.co.uk/wetsuits.html?p=1&product_list_limit=36&product_list_order=price_asc&zip=744%2C745%2C743";


        try {

            for(int i = 1; i<12; i++) {
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

                    productName = productName.toLowerCase(Locale.ROOT);

                    if (productName.contains(" man ") || productName.contains(" men ") || productName.contains(" mens ")) {
                        wetsuit.setGender("Mens");
                    } else if (productName.contains(" Woman ") || productName.contains(" women ") || productName.contains(" womens ")) {
                        wetsuit.setGender("Womens");
                    } else if (productName.contains(" kids ") || productName.contains(" junior ") || productName.contains(" toddler ") || productName.contains(" toddlers ") || productName.contains(" baby ") || productName.contains(" youth ") || productName.contains(" girls ") || productName.contains(" boys ")) {
                        wetsuit.setGender("Kids");
                    } else {
                        wetsuit.setGender("Accessories");
                    }

                    if (productName.contains(" 6/")) {
                        wetsuit.setThickness("6 mm");
                    } else if (productName.contains(" 5/")) {
                        wetsuit.setThickness("5 mm");
                    } else if (productName.contains(" 4/")) {
                        wetsuit.setThickness("4 mm");
                    } else if (productName.contains(" 3/")) {
                        wetsuit.setThickness("3 mm");
                    } else if (productName.contains(" 2/")) {
                        wetsuit.setThickness("2 mm");
                    } else {
                        wetsuit.setThickness("1 mm");
                    }

                    if (productName.contains(" back zip ")) {
                        wetsuit.setZipper("Back Zip");
                    } else if (productName.contains(" front zip ") || productName.contains(" chest zip ")) {
                        wetsuit.setZipper("Chest Zip");
                    } else if (productName.contains(" zipperless ") || productName.contains(" zip less ") || productName.contains(" zipper less ")) {
                        wetsuit.setZipper("Zipperless");
                    } else {
                        wetsuit.setZipper("Unknown");
                    }


                    wetsuit.setName(productName);
                    wetsuit.setPrice(Double.parseDouble(price));
                    wetsuit.setImageAddress(imageAddress);
                    wetsuit.setWebAddress(webAddress);

                    wetsuitsRepository.save(wetsuit);
                }
            }


        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
