package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;

public class ProxyScraper {
    public static void main(String[] args) {

        int j = 0;
        final String baseUrl = "https://www.surfdome.com/Wetsuits/sddsl13817.htm?page=1&sortBy=newest";

        try {


            for (int i = 1; i < 3; i++) {
                final Document doc = Jsoup.connect(baseUrl + "?page=" + i).maxBodySize(0).timeout(60000).get();

                Elements wetsuits = doc.getElementsByClass("ais-Hits-item product");

                for (Element element : wetsuits) {
                    System.out.println(" ");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    j++;
                    System.out.println("Wetsuit #" + j);

                    String productName = element.getElementsByClass("product__title").text();

                    String price = element.getElementsByClass("product__price").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[0];
                    }

                    String webAddress = element.getElementsByClass("i-availability-check i-product-card").get(0).children().attr("href");

                    String imageAddress = element.getElementsByClass("product__image").get(0).attr("data-srcset");

                    String name = productName.toLowerCase(Locale.ROOT);

                    String sizes  = element.getElementsByClass("product__availability-sizes product-card__availability-sizes").get(0).cssSelector();


                    StringFinder stringFinder = new StringFinder();

                    System.out.println(productName);
                    System.out.println("Gender: " + stringFinder.genderFinder(name));
                    System.out.println("Thickness: " + stringFinder.thicknessFinder(name));
                    System.out.println("Zipper: " + stringFinder.zipperFinder(name));
                    System.out.println("Sizes: " + sizes);


                    if (price != "") {
                        System.out.println("Price: £" + price);
                    }

                    System.out.println(imageAddress);
                    System.out.println(webAddress);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }}