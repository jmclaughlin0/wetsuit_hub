package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WetsuitCenterScraper {
    public static void main(String[] args){
        final String baseUrl = "https://www.wetsuitcentre.co.uk/wetsuits-mens.html?product_list_limit=all";

        try {
            final Document doc = Jsoup.connect(baseUrl).get();
            System.out.printf("Title: %s\n", doc.title());

            Elements wetsuits = doc.getElementsByClass("item product col col-6 col-sm-4 col-md-3 col-lg-4 col-xl-3");

            int i=1;

            for(Element element : wetsuits){
                String productName = element.getElementsByClass("product-item-link").text();
                System.out.println("#" + i + " " + productName);
                String price = element.getElementsByClass("price").text();
                System.out.println("price: " + price);
                String imageAddress = element.getElementsByClass("product-image-photo").toString().split(" ")[3].split("=")[1].replace('"', ' ');
                System.out.println("address: " + imageAddress);
                i++;
            }


        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
