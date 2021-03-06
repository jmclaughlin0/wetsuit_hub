package com.nyxgroup.wetsuit_hubweb.scrapers;

import com.nyxgroup.wetsuit_hubweb.IWetsuitScraper;
import com.nyxgroup.wetsuit_hubweb.StringFinder;
import com.nyxgroup.wetsuit_hubweb.Wetsuit;
import com.nyxgroup.wetsuit_hubweb.WetsuitsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

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

                    Elements sizes  = element.getElementsByClass("prod_sizes");

                    String name = productName.toLowerCase(Locale.ROOT);

                    StringFinder stringFinder = new StringFinder();

                    wetsuit.setGender(stringFinder.genderFinder(name));
                    wetsuit.setThickness(stringFinder.thicknessFinder(name));
                    wetsuit.setZipper(stringFinder.zipperFinder(name));
                    wetsuit.setSize(sizes.text());

                    wetsuit.setName(productName);
                    if(!Objects.equals(price, "")){
                        wetsuit.setPrice(Double.parseDouble(price));
                    }else wetsuit.setPrice(-1);

                    wetsuit.setImageAddress(imageAddress);
                    wetsuit.setWebAddress(webAddress);
                    wetsuit.setBrand(stringFinder.brandFinder(productName));
                    wetsuit.setOriginWebpage("Wetsuit Outlet");



                    if(wetsuit.getSize().contains("UK")){
                        wetsuit.setGender("Womens");
                    }

                    if (!wetsuitsRepository.findAll().toString().contains(wetsuit.toString())) {
                        if(wetsuit.stringTooLongChecker(wetsuit)) {
                            wetsuitsRepository.save(wetsuit);
                        }
                    }
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Wetsuit Outlet Done");
    }
}
