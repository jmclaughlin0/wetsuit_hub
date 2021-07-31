package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WetsuitOutletScraper implements IWetsuitScraper {

    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitOutletScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public void getWetsuits(){
        final String baseUrl = "https://www.wetsuitoutlet.co.uk/mens-4mm-c-155_156.html?sort=2a";

        try {
            final Document doc = Jsoup.connect(baseUrl).get();
//            System.out.printf("Title: %s\n", doc.title());

            Elements wetsuits = doc.getElementsByClass("productListEntry col-xs-6 col-lg-3 tac double-view");


            for(Element element : wetsuits){
                Wetsuit wetsuit = new Wetsuit();

                String productName = element.getElementsByClass("prod_name").text();

                String price = element.getElementsByClass("norm").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                if(price.isEmpty()){
                    price = element.getElementsByClass("special").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                }

                String imageAddress = element.getElementsByAttribute("data-src").get(0).toString().split(" ")[3].split("=")[1].replace('"', ' ');
                String webAddress = element.attr("data-url");

                wetsuit.setName(productName);
                if(price != ""){
                    wetsuit.setPrice(Double.parseDouble(price));
                }else wetsuit.setPrice(-1);

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
