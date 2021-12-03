package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

public class ProxyScraper {
    public static void main(String[] args) {

        int j = 0;
        final String baseUrl = "https://www.blue-tomato.com/en-GB/products/brand/Rip+Curl-609/c_wetsuits_dicke_rumpf/2.6-3.5/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/men/";
            try {
                final Document doc = Jsoup.connect(baseUrl).get();

                Elements wetsuits = doc.getElementsByClass("productcell ");

                for (Element element : wetsuits) {
                    System.out.println(" ");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    j++;
                    System.out.println("Wetsuit #" + j);

                    String productName = element.getElementsByClass("ellipsis").text();

                    String price = element.getElementsByClass("price").text().replaceAll("£", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[1];
                    }

                    String webAddress =  "https://www.blue-tomato.com" + element.getElementsByClass("name track-click track-load-producttile").attr("href");

                    String imageAddress = "";

                    String tempAddress = element.getElementsByClass("productimage").get(0).children().get(1).attr("src");



                    if (tempAddress.isEmpty()||tempAddress.contains("data:image/png;base64")) {
                        Elements newAddress = element.getElementsByClass("productimage");
                        imageAddress =  "https:" + newAddress.toString().split("img src=")[1].split("\"")[1];
                        if(imageAddress.contains("data:image/png;base64")){
                            String [] anotherAddress = element.children().get(0).toString().split("data-src=");
                            imageAddress = "https:" + anotherAddress[1].split("class=\"js-lazy\"")[0].replaceAll("\"", "");
                        }
                    }else{
                        imageAddress = "https:" + tempAddress;
                    }



//                    Elements sizes  = element.getElementsByAttribute("swatch-attribute-options");

                    StringFinder stringFinder = new StringFinder();

                    if(j == 56){
                        System.out.println("this is where the issue lie");
                    }

                    System.out.println(productName);
                    System.out.println("Gender: " + stringFinder.genderFinder(productName));
                    System.out.println("Thickness: " + stringFinder.thicknessFinder(productName));
                    System.out.println("Zipper: " + stringFinder.zipperFinder(productName));
//                    System.out.println("Sizes: " + sizes);


                    if (!Objects.equals(price, "")) {
                        System.out.println("Price: " + price);
                    }

                    System.out.println(imageAddress);
                    System.out.println(webAddress);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}