package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BlueTomatoScraper implements IWetsuitScraper{

    private final WetsuitsRepository wetsuitsRepository;

    public BlueTomatoScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {
        String [] genders = {
                "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/women/v_sizestring/",
                "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/men/v_sizestring/",
                "https://www.blue-tomato.com/en-GB/products/categories/Surf+Shop-0000002P--Wetsuits+Surfwear-00008VG3--Full+Wetsuits-00000031/gender/boys/v_sizestring/"
                };

        ArrayList<String> womensSizes = new ArrayList<>(
                Arrays.asList("02/",
                "04/",
                "06/",
                "08/",
                "10/",
                "12/",
                "s/",
                "m/",
                "l/"));

        ArrayList<String> mensSizes = new ArrayList<>(
                Arrays.asList("xs/",
                "s/",
                "ms/",
                "m/",
                "mt/",
                "ls/",
                "l/",
                "lt/",
                "xl/",
                "xxl/"));

        ArrayList<String> kidsSizes = new ArrayList<>(
                Arrays.asList(
                "06/",
                "08/",
                "10/",
                "12/",
                "14/",
                "16/"));

        ArrayList<String> sizes = new ArrayList<>();

        for(String gender:genders) {

            if (gender.contains("women")) {
                sizes = womensSizes;
            } else if (gender.contains("men")) {
                sizes = mensSizes;
            } else if (gender.contains("boys")) {
                sizes = kidsSizes;
            }

            for (String size : sizes) {
                try {
                    final Document doc = Jsoup.connect(gender + size).get();

                    Elements wetsuits = doc.getElementsByClass("productcell ");

                    for (Element element : wetsuits) {
                        Wetsuit wetsuit = new Wetsuit();
                        String productName = element.getElementsByClass("ellipsis").text();

                        String price = element.getElementsByClass("price").text().replaceAll("£", "");
                        if (price.contains(" ")) {
                            String newPrice = price;
                            price = newPrice.split(" ")[1];
                        }

                        String webAddress = "https://www.blue-tomato.com" + element.getElementsByClass("name track-click track-load-producttile").attr("href");

                        String imageAddress = "";

                        String tempAddress = element.getElementsByClass("productimage").get(0).children().get(1).attr("src");


                        if (tempAddress.isEmpty() || tempAddress.contains("data:image/png;base64")) {
                            Elements newAddress = element.getElementsByClass("productimage");
                            imageAddress = "https:" + newAddress.toString().split("img src=")[1].split("\"")[1];
                            if (imageAddress.contains("data:image/png;base64")) {
                                String[] anotherAddress = element.children().get(0).toString().split("data-src=");
                                imageAddress = "https:" + anotherAddress[1].split("class=\"js-lazy\"")[0].replaceAll("\"", "");
                            }
                        } else {
                            imageAddress = "https:" + tempAddress;
                        }


                        StringFinder stringFinder = new StringFinder();

                        wetsuit.setName(productName);
                        wetsuit.setGender(stringFinder.genderFinder(gender));
                        wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                        wetsuit.setZipper(stringFinder.zipperFinder(productName));
                        wetsuit.setImageAddress(imageAddress);
                        wetsuit.setWebAddress(webAddress);
                        wetsuit.setBrand(stringFinder.brandFinder(productName));
                        wetsuit.setOriginWebpage("blue-tomato");


                        if (!Objects.equals(price, "")) {
                            if (price.contains("instead")) {
                                price = price.replace("instead", "");
                            }
                            if (price.contains("-")) {
                                price = price.replace("-", "");
                            }
                            wetsuit.setPrice(Double.parseDouble(price));
                        }

                        final Wetsuit currentWetsuit = wetsuit;

                        if (wetsuitsRepository.findAll().toString().contains(currentWetsuit.toString())) {
                            List <Wetsuit> oldWetsuits = wetsuitsRepository.findAll().stream().filter(w -> (w.toString().contains(currentWetsuit.toString()))).collect(Collectors.toList());

                            Wetsuit oldWetsuit = oldWetsuits.get(0);
                            String oldSizes = oldWetsuit.getSize();

                            oldWetsuit.setSize(oldSizes.concat(", ").concat(size.replace("/", "").toUpperCase()));
                            wetsuit = oldWetsuit;
                            } else {
                            wetsuit.setSize(size.replace("/", "").toUpperCase());
                            }
                        wetsuitsRepository.save(wetsuit);
                        }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
