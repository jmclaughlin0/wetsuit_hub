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
import java.util.ArrayList;
import java.util.Arrays;

public class TikiScraper implements IWetsuitScraper {
    
    private final WetsuitsRepository wetsuitsRepository;

    public TikiScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {
        String [] genders = {
                "https://www.tikisurf.co.uk/product-category/hardware/wetsuits/mens-wetsuits/page/1/?filter_size=",
                "https://www.tikisurf.co.uk/product-category/hardware/wetsuits/ladies-wetsuits/page/1/?filter_size=",
                "https://www.tikisurf.co.uk/product-category/hardware/wetsuits/kids-wetsuits/page/1/?filter_size="
        };

        ArrayList<String> womensSizes = new ArrayList<>(
                Arrays.asList(
                        "4-us/",
                        "08/",
                        "6-us/",
                        "6t-us/",
                        "10/",
                        "10t/",
                        "8s-us/",
                        "8-us/",
                        "8t-us/",
                        "12/",
                        "12t/",
                        "10s-us/",
                        "10-us/",
                        "14/",
                        "14t/",
                        "16"
                        ));

        ArrayList<String> mensSizes = new ArrayList<>(
                Arrays.asList(
                        "xs/",
                        "s/",
                        "ms/",
                        "ts/",
                        "m/",
                        "mt/",
                        "ls/",
                        "l/",
                        "lt/",
                        "xl/",
                        "xls/",
                        "xlt/",
                        "xxl/",
                        "xxxl/",
                        "xxxxl/"
                        ));

        ArrayList<String> kidsSizes = new ArrayList<>(
                Arrays.asList(
                        "1/",
                        "2/",
                        "3/",
                        "4/",
                        "6/",
                        "8/",
                        "10/",
                        "12/",
                        "14/",
                        "16/",
                        "J4/",
                        "J6/",
                        "J8/",
                        "J10/",
                        "J12/",
                        "J14/",
                        "J16/",
                        "J18/"
                        ));

        ArrayList<String> sizes = new ArrayList<>();

        for(String gender:genders) {
            if (gender.contains("ladies")) {
                sizes = womensSizes;
            } else if (gender.contains("mens")) {
                sizes = mensSizes;
            } else if (gender.contains("kids")) {
                sizes = kidsSizes;
            }

            try {
                for (String size : sizes) {
                    final Document doc = Jsoup.connect(gender + size).get();

                    Elements wetsuits = doc.getElementsByClass("product-item  spinner-circle palign-center  product_hover_enable product_hover_mob_enable");

                    for (Element element : wetsuits) {
                        Wetsuit wetsuit = new Wetsuit();

                        String productName = element.getElementsByClass("product-title-link").get(0).text();

                        String price = element.getElementsByClass("woocommerce-Price-amount amount").text().replaceAll("£", "");
                        if (price.contains(" ")) {
                            String newPrice = price;
                            price = newPrice.split(" ")[0];
                        }

                        String webAddress = element.getElementsByClass("product-title-link").attr("href");

                        String imageAddress = element.getElementsByClass("attachment-shop_catalog size-shop_catalog wp-post-image").attr("src");

                        String outOfStock = element.getElementsByClass("out_of_stock_title").text();

                        StringFinder stringFinder = new StringFinder();

                        wetsuit.setName(productName);
                        wetsuit.setGender(stringFinder.genderFinder(gender));
                        wetsuit.setThickness(stringFinder.thicknessFinder(productName));
                        wetsuit.setZipper(stringFinder.zipperFinder(productName));
                        wetsuit.setPrice(Double.parseDouble(price));
                        wetsuit.setWebAddress(webAddress);
                        wetsuit.setImageAddress(imageAddress);
                        wetsuit.setBrand(stringFinder.brandFinder(productName));
                        wetsuit.setSize(size.replace("/", "").toUpperCase());
                        wetsuit.setOriginWebpage("Tiki Surf");

                        if(wetsuit.getSize().contains("UK")){
                            wetsuit.setGender("Womens");
                        }

                        if(wetsuit.getSize().isBlank()){
                            wetsuit.setSize("N/A");
                        }

                        if (outOfStock.isEmpty()) {
                            if(wetsuit.stringTooLongChecker(wetsuit)) {
                                wetsuit.wetsuitLookupAndSaveNewSize(wetsuit, wetsuitsRepository, size);
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Tiki Done");
    }
}
