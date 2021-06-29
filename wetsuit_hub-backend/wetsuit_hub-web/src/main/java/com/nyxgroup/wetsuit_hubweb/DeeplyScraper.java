package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DeeplyScraper {
        public static void main(String[] args){
            final String baseUrl = "https://eu.deeply.com/collections/wetsuits-men";

            try {
                final Document doc = Jsoup.connect(baseUrl).get();
                System.out.printf("Title: %s\n", doc.title());

                Elements wetsuits = doc.getElementsByClass("prd-List_Item");

                int i=1;

                for(Element element : wetsuits){
                    Elements wetsuitPage = element.getElementsByClass("boost-pfs-filter-product-item-image-link prd-Card_FauxLink util-FauxLink_Link lazyload prd-Card_FauxLink util-FauxLink_Link");
                    Document currentWetsuit = Jsoup.connect("https://eu.deeply.com/" + wetsuitPage.attr("href")).get();
                    String wetsuitTitle = currentWetsuit.title();
                    System.out.println("#" + i + " " + wetsuitTitle);
                    String [] price = currentWetsuit.getElementsByClass("prd-Price_Price").text().split(" ");
                    System.out.println("Price : " + price[0]);
                    Elements wetsuitImageAddress = currentWetsuit.getElementsByAttribute("data-lowsrc");
                    String wetsuitPlaceholder = wetsuitImageAddress.get(9).toString();
                    String wetsuitPlaceholderAddress = wetsuitPlaceholder.split(" ")[6].split("//")[1].replace('"',' ');
                    System.out.println("web: " + wetsuitPlaceholderAddress);
                    i++;
                }


            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

