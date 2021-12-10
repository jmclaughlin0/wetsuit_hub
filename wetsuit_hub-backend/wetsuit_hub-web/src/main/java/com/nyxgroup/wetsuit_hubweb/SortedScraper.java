package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortedScraper implements IWetsuitScraper {

    private final WetsuitsRepository wetsuitsRepository;

    public SortedScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {

        final String baseUrl = "https://www.sortedsurfshop.co.uk/wetsuits.html?p=1&zip=743%2C745%2C744";

        for(int i=1; i<20; i++) {
            String newURL = baseUrl.replace("?p=1", "?p="+i);
            try {
                final Document doc = Jsoup.connect(newURL).get();

                Elements wetsuits = doc.getElementsByClass("item product col col-6 col-sm-6 col-md-4 col-lg-3");

                for (Element element : wetsuits) {
                    Wetsuit wetsuit = new Wetsuit();

                    String productName = element.getElementsByClass("product-item-link").text();

                    String price = element.getElementsByClass("price").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[0];
                    }

                    String webAddress = element.getElementsByClass("product-item-link").attr("href");

                    String imageAddress = element.getElementsByClass("product-image-photo").attr("data-amsrc");

                    int sizesCharStartNumber  = element.toString().indexOf("jsonSwatchConfig") + 16;
                    int sizesCharEndNumber  = element.toString().indexOf("additional_data");

                    String finalSize = "";
                    if(sizesCharEndNumber != -1 && sizesCharStartNumber != 15){

                        String sizeMess = element.toString().substring(sizesCharStartNumber, sizesCharEndNumber).replace("\"type\":\"0\",\"value\":", "").replace("label", "").replaceAll("[\"{:}]", "");
                        ArrayList<String> sizeArray = new ArrayList<>(List.of(sizeMess.split(",")));
                        ArrayList<String> finalSizeArray = new ArrayList<>();

                        for(String o: sizeArray){
                            if(o.contains("UK")){
                                int start = o.indexOf("UK");
                                String newString = o.substring(start);
                                if(newString.length()<6){
                                    finalSizeArray.add(newString);
                                }
                            }else {
                                String newString =  o.replaceAll("[0-9]", "");
                                if(newString.length()<4){
                                    finalSizeArray.add(newString);
                                }
                            }
                        }
                        int sizeLength = finalSizeArray.toString().length();

                        finalSize = finalSizeArray.toString().substring(1, sizeLength-1);
                    }


                    StringFinder stringFinder = new StringFinder();

                    wetsuit.setName(productName);
                    wetsuit.setGender(stringFinder.genderFinder(productName));
                    wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                    wetsuit.setZipper(stringFinder.zipperFinder(productName));
                    if(!price.isEmpty()){
                        wetsuit.setPrice(Double.parseDouble(price));

                    }
                    wetsuit.setWebAddress(webAddress);
                    wetsuit.setImageAddress(imageAddress);
                    wetsuit.setBrand(stringFinder.brandFinder(productName));
                    wetsuit.setSize(finalSize);
                    wetsuit.setOriginWebpage("Sorted Surf Shop");

                    System.out.println(productName);

                    if (!wetsuitsRepository.findAll().toString().contains(wetsuit.toString())) {
                        wetsuitsRepository.save(wetsuit);
                    }
                }

            }catch (IOException e) {
                e.printStackTrace();
        }}
    }
}
