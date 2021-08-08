package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DeeplyScraper implements IWetsuitScraper {

    private final WetsuitsRepository wetsuitsRepository;

    public DeeplyScraper(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public void getWetsuits(){

            String monzoCurrencyUrl = "https://www.google.com/search?q=pound+to+euro&ei=R7oBYb_1NJTdgQb9vLPoAg&oq=pound+to+euro&gs_lcp=Cgdnd3Mtd2l6EAMyDQgAELEDEIMBEEYQggIyBQgAEJECMgsIABCxAxCDARCRAjILCAAQsQMQgwEQkQIyCAgAELEDEIMBMgcIABCxAxAKMgsIABCxAxCDARCLAzIFCAAQiwMyBQgAEIsDMgUIABCLAzoHCAAQRxCwAzoECAAQQzoHCAAQsQMQQzoNCAAQsQMQgwEQQxCLAzoHCAAQQxCLAzoKCAAQsQMQgwEQQzoPCAAQsQMQgwEQQxBGEIICSgQIQRgAUJgtWP07YKY-aANwAXgAgAF-iAH_CZIBAzkuNZgBAKABAaoBB2d3cy13aXqwAQDIAQi4AQPAAQE&sclient=gws-wiz&ved=0ahUKEwj_tKzlyYbyAhWUbsAKHX3eDC0Q4dUDCA8&uact=5";

            double poundToEuro = 1;

            try {
                final Document monzoDoc = Jsoup.connect(monzoCurrencyUrl).get();
                poundToEuro = Double.parseDouble(monzoDoc.getElementsByClass("DFlfde SwHCTb").text());
            } catch (IOException e) {
                e.printStackTrace();
            }

            String URLS [] = {"https://eu.deeply.com/collections/wetsuits", "https://eu.deeply.com/collections/last-swell-wetsuits-women", "https://eu.deeply.com/collections/last-swell-wetsuits-men-1"};


            for(String baseUrl:URLS){
                try {
                    final Document doc = Jsoup.connect(baseUrl).get();

                    Elements wetsuits = doc.getElementsByClass("prd-List_Item");

                    for(Element element : wetsuits){
                        Wetsuit wetsuit = new Wetsuit();

                        Elements wetsuitPage = element.getElementsByClass("boost-pfs-filter-product-item-image-link prd-Card_FauxLink util-FauxLink_Link lazyload prd-Card_FauxLink util-FauxLink_Link");
                        Document currentWetsuit = Jsoup.connect("https://eu.deeply.com/" + wetsuitPage.attr("href")).get();

                        String wetsuitTitle = currentWetsuit.title();
                        wetsuit.setName(wetsuitTitle);

                        if(wetsuitTitle.toLowerCase().contains(" man ")||wetsuitTitle.contains(" men ")|| wetsuitTitle.contains(" mens ")){
                            wetsuit.setGender("Mens");
                        }else if(wetsuitTitle.toLowerCase().contains(" woman ")||wetsuitTitle.contains(" women ")|| wetsuitTitle.contains(" womens ")){
                            wetsuit.setGender("Womens");
                        }else if(wetsuitTitle.toLowerCase().contains(" kids ")||wetsuitTitle.contains(" junior ")|| wetsuitTitle.contains(" toddler ")||wetsuitTitle.contains(" toddlers ")||wetsuitTitle.contains(" baby ")||wetsuitTitle.contains(" youth ")){
                            wetsuit.setGender("Kids");
                        }else {
                            wetsuit.setGender("Accessories");
                        }

                        String [] price = currentWetsuit.getElementsByClass("prd-Price_Price").text().split(" ");
                        double cost = Double.parseDouble(price[0].replace("€", "").replace(',', '.'));
                        wetsuit.setPrice(cost/poundToEuro);

                        Elements wetsuitImageAddress = currentWetsuit.getElementsByAttribute("data-lowsrc");
                        String wetsuitPlaceholder = wetsuitImageAddress.get(9).toString();
                        String wetsuitPlaceholderAddress = wetsuitPlaceholder.split(" ")[6].split("//")[1].replace('"',' ').replace("20x", "400x");
                        wetsuit.setImageAddress("https://" + wetsuitPlaceholderAddress);

                        wetsuit.setWebAddress("https://eu.deeply.com/" + wetsuitPage.attr("href"));

                        wetsuitsRepository.save(wetsuit);
                    }
            }
                catch (Exception exception){
                    exception.printStackTrace();
                }


            }

        }
    }

