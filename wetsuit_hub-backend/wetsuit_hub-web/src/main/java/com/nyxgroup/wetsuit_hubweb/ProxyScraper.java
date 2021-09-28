package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ProxyScraper {
    public static void main(String[] args) {

        int j = 0;
        final String baseUrl = "https://www.tikisurf.co.uk/product-category/hardware/wetsuits/all-wetsuits/page/1/";

        for(int i=1; i<4; i++) {
            String newURL = baseUrl.replace("page/1/", "page/" + i + "/" );
            try {
                final Document doc = Jsoup.connect(newURL).get();

                Elements wetsuits = doc.getElementsByClass("product-item  spinner-circle palign-center  product_hover_enable product_hover_mob_enable");

                for (Element element : wetsuits) {
                    System.out.println(" ");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    j++;
                    System.out.println("Wetsuit #" + j);

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

                    if(!outOfStock.isEmpty()){
                        System.out.println("****** this item is out of stock ******");
                    }


                    StringFinder stringFinder = new StringFinder();

                    System.out.println(productName);
                    System.out.println("Gender: " + stringFinder.genderFinder(productName));
                    System.out.println("Thickness: " + stringFinder.thicknessFinder(productName));
                    System.out.println("Zipper: " + stringFinder.zipperFinder(productName));
//                    System.out.println("Sizes: " + sizes);


                    if (price != "") {
                        System.out.println("Price: " + price);
                    }

                    System.out.println(imageAddress);
                    System.out.println(webAddress);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }}