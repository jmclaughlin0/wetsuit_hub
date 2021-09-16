package com.nyxgroup.wetsuit_hubweb;

public class StringFinder {
    
    public String genderFinder(String wetsuitTitle){
        String gender = "";
        if (wetsuitTitle.contains(" man ") || wetsuitTitle.contains(" men ") || wetsuitTitle.contains(" mens ")) {
            gender = "Mens";
        } else if (wetsuitTitle.contains(" Woman ") || wetsuitTitle.contains(" women ") || wetsuitTitle.contains(" womens ")) {
            gender = "Womens";
        } else if (wetsuitTitle.contains(" kids ") || wetsuitTitle.contains(" junior ") || wetsuitTitle.contains(" toddler ") || wetsuitTitle.contains(" toddlers ") || wetsuitTitle.contains(" baby ") || wetsuitTitle.contains(" youth ") || wetsuitTitle.contains(" girls ") || wetsuitTitle.contains(" boys ")) {
            gender = "Kids";
        } else {
            gender = "Accessories";
        }
        
        return gender;
    }
    
    public String thicknessFinder(String wetsuitTitle){
        String thickness = "";
        
        if (wetsuitTitle.contains(" 6/")) {
            thickness = "6 mm";
        } else if (wetsuitTitle.contains(" 5/")) {
            thickness = "5 mm";
        } else if (wetsuitTitle.contains(" 4/")) {
            thickness = "4 mm";
        } else if (wetsuitTitle.contains(" 3/")) {
            thickness = "3 mm";
        } else if (wetsuitTitle.contains(" 2/")) {
            thickness = "2 mm";
        } else {
            thickness = "1 mm";
        }

        return thickness;
    }
    
    public String zipperFinder(String wetsuitTitle){
        String zipper = "";

        if (wetsuitTitle.contains(" back zip ")) {
            zipper = "Back Zip";
        } else if (wetsuitTitle.contains(" front zip ") || wetsuitTitle.contains(" chest zip ")) {
            zipper = "Chest Zip";
        } else if (wetsuitTitle.contains(" zipperless ") || wetsuitTitle.contains(" zip less ") || wetsuitTitle.contains(" zipper less ")) {
            zipper = "Zipperless";
        } else {
            zipper = "Unknown";
        }

        return zipper;
    }
}
