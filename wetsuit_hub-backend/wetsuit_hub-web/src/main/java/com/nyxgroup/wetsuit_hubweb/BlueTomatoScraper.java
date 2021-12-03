package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

public class BlueTomatoScraper implements IWetsuitScraper{

    private final WetsuitsRepository wetsuitsRepository;

    public BlueTomatoScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {
        int j = 0;
        final String baseUrl = "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/?sort=-max_price";
        String [] genders = {
                "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/women/",
                "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/men/",
                "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/boys/"
                };

        for(String gender:genders){
            try {
                final Document doc = Jsoup.connect(gender).get();

                Elements wetsuits = doc.getElementsByClass("productcell ");

                for (Element element : wetsuits) {
                    Wetsuit wetsuit = new Wetsuit();
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

                    wetsuit.setName(productName);
                    wetsuit.setGender(stringFinder.genderFinder(gender));
                    wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                    wetsuit.setZipper(stringFinder.zipperFinder(productName));
                    wetsuit.setImageAddress(imageAddress);
                    wetsuit.setWebAddress(webAddress);
    //                    System.out.println("Sizes: " + sizes);


                    if (!Objects.equals(price, "")) {
                        if(price.contains("instead")){
                            price = price.replace("instead","");
                        }
                        if(price.contains("-")){
                            price = price.replace("-","");
                        }
                        wetsuit.setPrice(Double.parseDouble(price));
                        wetsuitsRepository.save(wetsuit);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    }
}
