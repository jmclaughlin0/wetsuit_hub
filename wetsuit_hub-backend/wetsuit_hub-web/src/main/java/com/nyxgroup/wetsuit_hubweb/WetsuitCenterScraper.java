package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WetsuitCenterScraper implements IWetsuitScraper {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitCenterScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public void getWetsuits(){
        final String baseUrl = "https://www.wetsuitcentre.co.uk/wetsuits.html?product_list_limit=all&zip=744%2C745%2C743";



        try {
            final Document doc = Jsoup.connect(baseUrl).get();
//            System.out.printf("Title: %s\n", doc.title());

            Elements wetsuits = doc.getElementsByClass("item product col col-6 col-sm-4 col-md-3 col-lg-4 col-xl-3");

            for(Element element : wetsuits){
                Wetsuit wetsuit = new Wetsuit();

                String productName = element.getElementsByClass("product-item-link").text();
                String price = element.getElementsByClass("price").text().replace("£" , "");

                if(price.contains(" ")){
                    price = price.split(" ")[1];
                }

                String imageAddress = element.getElementsByClass("product-image-photo").toString().split(" ")[3].split("=")[1].replace('"', ' ');
                String webAddress = element.getElementsByClass("product-item-link").attr("href");

                if(productName.toLowerCase().contains(" man ")||productName.contains(" men ")|| productName.contains(" mens ")){
                    wetsuit.setGender("Mens");
                }else if(productName.toLowerCase().contains(" Woman ")||productName.contains(" women ")|| productName.contains(" womens ")){
                    wetsuit.setGender("Womens");
                }else if(productName.toLowerCase().contains(" kids ")||productName.contains(" junior ")|| productName.contains(" toddler ")||productName.contains(" toddlers ")||productName.contains(" baby ")||productName.contains(" youth ")){
                    wetsuit.setGender("Mens");
                }else {
                    wetsuit.setGender("Accessories");
                }

                wetsuit.setName(productName);
                wetsuit.setPrice(Double.parseDouble(price));
                wetsuit.setImageAddress(imageAddress);
                wetsuit.setWebAddress(webAddress);

                wetsuitsRepository.save(wetsuit);
            }


        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
