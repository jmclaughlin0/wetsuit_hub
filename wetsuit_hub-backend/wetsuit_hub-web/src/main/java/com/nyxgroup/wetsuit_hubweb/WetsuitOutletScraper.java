package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WetsuitOutletScraper implements IWetsuitScraper {
    public void getWetsuits(){
        final String baseUrl = "https://www.wetsuitoutlet.co.uk/mens-4mm-c-155_156.html";

        try {
            final Document doc = Jsoup.connect(baseUrl).get();
            System.out.printf("Title: %s\n", doc.title());

            Elements wetsuits = doc.getElementsByClass("productListEntry col-xs-6 col-lg-3 tac double-view");

            int i=1;

            for(Element element : wetsuits){
                String productName = element.getElementsByClass("prod_name").text();
                System.out.println("#" + i + " " + productName);
                String price = element.getElementsByClass("norm").text();
                System.out.println("price: " + price);
                String imageAddress = element.getElementsByAttribute("data-src").get(0).toString().split(" ")[3].split("=")[1].replace('"', ' ');
                System.out.println("address: " + imageAddress);
                i++;
            }


        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
