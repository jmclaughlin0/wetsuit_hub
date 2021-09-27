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
        final String baseUrl = "https://needessentialsuk.com/collections/wetsuits-new-1";

        try {
            final Document doc = Jsoup.connect(baseUrl).get();

                Elements wetsuits = doc.getElementsByClass("Grid__Cell 1/1--phone 1/3--tablet-and-up 1/4--lap-and-up");

                for (Element element : wetsuits) {
                    System.out.println(" ");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    j++;
                    System.out.println("Wetsuit #" + j);

                    String productName = element.getElementsByClass("ProductItem__Title Heading").text();

                    String price = element.getElementsByClass("ProductItem__Price Price Text--subdued").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[0];
                    }

                    String webAddress =  "https://needessentialsuk.com/" + element.getElementsByClass("ProductItem__ImageWrapper ProductItem__ImageWrapper--withAlternateImage").attr("href");

                    String imageAddress = element.getElementsByClass("ProductItem__Image").get(1).attr("data-src").replace("{width}", "200").replace("//", "https://");

                    String name = productName.toLowerCase(Locale.ROOT);

//                    String sizes  = element.getElementsByClass("product__availability-sizes product-card__availability-sizes").get(0).cssSelector();


                    StringFinder stringFinder = new StringFinder();

                    System.out.println(productName);
                    System.out.println("Gender: " + stringFinder.genderFinder(name));
                    System.out.println("Thickness: " + stringFinder.thicknessFinder(name));
                    System.out.println("Zipper: " + stringFinder.zipperFinder(name));
//                    System.out.println("Sizes: " + sizes);


                    if (price != "") {
                        System.out.println("Price: £" + price);
                    }

                    System.out.println(imageAddress);
                    System.out.println(webAddress);

                }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }}