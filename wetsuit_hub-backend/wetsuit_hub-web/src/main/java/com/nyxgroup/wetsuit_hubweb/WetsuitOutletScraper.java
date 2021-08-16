package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Locale;

public class WetsuitOutletScraper implements IWetsuitScraper {

    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitOutletScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public void getWetsuits(){
        final String baseUrl = "https://www.wetsuitoutlet.co.uk/all-wetsuits-lp-344.html?max=180";

        try {


            for(int i = 1; i<4; i++){
                final Document doc = Jsoup.connect(baseUrl + "&page=" + i).get();

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

                    String name = productName.toLowerCase(Locale.ROOT);

                    if(name.contains(" man ")||name.contains(" men ")|| name.contains(" mens ")){
                        wetsuit.setGender("Mens");
                    }else if(name.contains(" Woman ")||name.contains(" women ")|| name.contains(" womens ")){
                        wetsuit.setGender("Womens");
                    }else if(name.contains(" kids ")||name.contains(" junior ")|| name.contains(" toddler ")||name.contains(" toddlers ")||name.contains(" baby ")||name.contains(" youth ")||name.contains(" girls ")||name.contains(" boys ")){
                        wetsuit.setGender("Kids");
                    }else {
                        wetsuit.setGender("Accessories");
                    }

                    wetsuit.setName(productName);
                    if(price != ""){
                        wetsuit.setPrice(Double.parseDouble(price));
                    }else wetsuit.setPrice(-1);

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
