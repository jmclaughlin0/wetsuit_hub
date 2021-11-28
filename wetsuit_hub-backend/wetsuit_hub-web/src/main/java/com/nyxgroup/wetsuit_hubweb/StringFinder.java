package com.nyxgroup.wetsuit_hubweb;

import java.util.Locale;

public class StringFinder {
    
    public String genderFinder(String wetsuitTitle){
        String gender = "";
        wetsuitTitle = wetsuitTitle.toLowerCase(Locale.ROOT);

        if (wetsuitTitle.contains("woman") || wetsuitTitle.contains("women") || wetsuitTitle.contains("womens") || wetsuitTitle.contains("ladies")) {
            gender = "Womens";
        } else if (wetsuitTitle.contains("man") || wetsuitTitle.contains("men") || wetsuitTitle.contains("mens")) {
            gender = "Mens";
        } else if (wetsuitTitle.contains("kids") || wetsuitTitle.contains("junior") || wetsuitTitle.contains("toddler") || wetsuitTitle.contains("toddlers") || wetsuitTitle.contains("baby") || wetsuitTitle.contains("youth") || wetsuitTitle.contains("girls") || wetsuitTitle.contains("boys")|| wetsuitTitle.contains("boy")|| wetsuitTitle.contains("girl")|| wetsuitTitle.contains("childrens")) {
            gender = "Kids";
        } else if((wetsuitTitle.contains("/") || wetsuitTitle.contains("mm")) && (wetsuitTitle.contains("wetsuit") || wetsuitTitle.contains("suit") || wetsuitTitle.contains("zip"))){
            gender = "Mens";
        } else {
            gender = "Accessories";
        }
        return gender;
    }
    
    public String thicknessFinder(String wetsuitTitle){
        String thickness = "";
        
        if (wetsuitTitle.contains("6/")) {
            thickness = "6 mm";
        } else if (wetsuitTitle.contains("5/")) {
            thickness = "5 mm";
        } else if (wetsuitTitle.contains("4/")) {
            thickness = "4 mm";
        } else if (wetsuitTitle.contains("3/")) {
            thickness = "3 mm";
        } else if (wetsuitTitle.contains("2/") || wetsuitTitle.contains("2mm") || wetsuitTitle.contains("2 mm")) {
            thickness = "2 mm";
        } else {
            thickness = "1 mm";
        }

        return thickness;
    }
    
    public String zipperFinder(String wetsuitTitle){
        String zipper = "";
        wetsuitTitle = wetsuitTitle.toLowerCase(Locale.ROOT);
        if (wetsuitTitle.contains(" back zip ")) {
            zipper = "Back Zip";
        } else if (wetsuitTitle.contains(" front zip ") || wetsuitTitle.contains(" chest zip ") || wetsuitTitle.contains("hooded")|| wetsuitTitle.contains("freezip")) {
            zipper = "Chest Zip";
        } else if (wetsuitTitle.contains(" zipperless ") || wetsuitTitle.contains(" zip less ") || wetsuitTitle.contains(" zipper less ") || wetsuitTitle.contains(" zip free ")) {
            zipper = "Zipperless";
        } else {
            zipper = "Unknown";
        }

        return zipper;
    }

    public String brandFinder(String wetsuitTitle){
        String brand = wetsuitTitle.split(" ")[0];

        if(brand.equalsIgnoreCase("rip")||brand.equalsIgnoreCase("c")||brand.equalsIgnoreCase("zone")){
            brand = wetsuitTitle.split(" ")[0] + " " + wetsuitTitle.split(" ")[1];
        }
        if(brand.equalsIgnoreCase("ocean")){
            brand = wetsuitTitle.split(" ")[0] + " " + wetsuitTitle.split(" ")[1] + " " + wetsuitTitle.split(" ")[2];
        }
        return brand;
    }

}
