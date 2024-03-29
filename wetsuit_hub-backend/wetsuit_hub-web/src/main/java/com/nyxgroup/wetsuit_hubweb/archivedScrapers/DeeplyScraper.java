package com.nyxgroup.wetsuit_hubweb.archivedScrapers;

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
                for(int i = 1; i > 0; i++){
                    try {
                        Document doc = Jsoup.connect(baseUrl + "?page=" + i).get();

                        Elements wetsuits = doc.getElementsByClass("prd-List_Item");

                        if(wetsuits.size()==0){
                            break;
                        }

                        for(Element element : wetsuits){
                            Wetsuit wetsuit = new Wetsuit();

                            Elements wetsuitPage = element.getElementsByClass("boost-pfs-filter-product-item-image-link prd-Card_FauxLink util-FauxLink_Link lazyload prd-Card_FauxLink util-FauxLink_Link");

                            Elements sizes = element.getElementsByClass("prd-Card_Item");

                            String availableSizes = "";

                            for(Element size : sizes){
                                if(availableSizes == ""){
                                    availableSizes = size.text();
                                }else {
                                    availableSizes += ", " + size.text();
                                }
                            }
                            wetsuit.setSize(availableSizes);

                            Document currentWetsuit = Jsoup.connect("https://eu.deeply.com/" + wetsuitPage.attr("href")).get();

                            String wetsuitTitle = currentWetsuit.title();
                            wetsuit.setName(wetsuitTitle);

                            wetsuitTitle = wetsuitTitle.toLowerCase(Locale.ROOT);

                            StringFinder stringFinder = new StringFinder();

                            wetsuit.setGender(stringFinder.genderFinder(wetsuitTitle));
                            wetsuit.setThickness(stringFinder.thicknessFinder(wetsuitTitle));
                            wetsuit.setZipper(stringFinder.zipperFinder(wetsuitTitle));

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
                        break;
                    }
                }



            }

        }
    }

