package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SurfDomeScraper implements IWetsuitScraper{

    private final WetsuitsRepository wetsuitsRepository;

    public SurfDomeScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    @Override
    public void getWetsuits() {
        final String baseUrl = "https://www.surfdome.com/Wetsuits/sddsl13817.htm?page=1&sortBy=newest";

        try {
            for (int i = 1; i < 10; i++) {
                final Document doc = Jsoup.connect(baseUrl.replace("?page=1", "?page=" + i)).get();

                Elements wetsuits = doc.getElementsByClass("ais-Hits-item product");

                for (Element element : wetsuits) {
                    Wetsuit wetsuit = new Wetsuit();
                    String productName = element.getElementsByClass("product__title").text();

                    String price = element.getElementsByClass("product__price").text().replace("£", "").replaceAll("[a-zA-Z]", "");
                    if (price.contains(" ")) {
                        String newPrice = price;
                        price = newPrice.split(" ")[0];
                    }

                    String webAddress = element.getElementsByClass("i-availability-check i-product-card").get(0).children().attr("href");

                    String imageAddress = element.getElementsByClass("product__image").get(0).attr("data-srcset");

                    String name = productName.toLowerCase(Locale.ROOT);

                    int sizesEnd = element.toString().indexOf("data-id=\"");
                    String sizeId = element.toString().substring(sizesEnd -8 , sizesEnd-2);

                    String genSizeURL = "https://www.surfdome.com/api/product/366857/availability";

                    final Document sizeDoc = Jsoup.connect(genSizeURL.replace("366857", sizeId)).ignoreContentType(true).get();

                    String sizesWholeText = sizeDoc.wholeText();

                    String sizes = sizesWholeText.replaceAll("\"name\"", "").replaceAll("\"available\"", "").replaceAll("\"stockid\"", "").replaceAll("[\\[\\]{\":}]", "");

                    ArrayList<String> sizeArray = new ArrayList<>(List.of(sizes.split(",")));
                    ArrayList<String> finalSizeArray = new ArrayList<>();

                    for(String o: sizeArray) {
                        if (o.contains("UK")) {
                            finalSizeArray.add(o);
                        }else if (o.contains("Age")) {
                            finalSizeArray.add(o);
                        }else if (!sizes.contains("UK")&&o.length()<5) {
                            String modifiedString = o.replaceAll("[0-9]", "");
                            if (modifiedString.length() > 0) {
                                finalSizeArray.add(modifiedString);
                            }
                        }
                    }

                    String finalSize = finalSizeArray.toString();

                    StringFinder stringFinder = new StringFinder();

                    wetsuit.setName(productName);
                    wetsuit.setGender(stringFinder.genderFinder(name));
                    wetsuit.setThickness(stringFinder.thicknessFinder(name));
                    wetsuit.setZipper(stringFinder.zipperFinder(name));
                    wetsuit.setPrice(Double.parseDouble(price));
                    wetsuit.setWebAddress(webAddress);
                    wetsuit.setImageAddress(imageAddress);
                    wetsuit.setBrand(stringFinder.brandFinder(productName));
                    wetsuit.setOriginWebpage("Surfdome");
                    wetsuit.setSize(finalSize.substring(1, finalSize.length()-1));


                    if (!wetsuitsRepository.findAll().toString().contains(wetsuit.toString())) {
                        wetsuitsRepository.save(wetsuit);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
