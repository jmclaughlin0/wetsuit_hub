package com.nyxgroup.wetsuit_hubweb;

import org.jsoup.nodes.Element;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "suits")
public class Wetsuit {

    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String thickness;
    @Column
    private String webAddress;
    @Column
    private String imageAddress;
    @Column
    private String size;
    @Column
    private String gender;
    @Column
    private String zipper;
    @Column
    private String brand;
    @Column
    private String originWebpage;

    @Column
    @Id
    @GeneratedValue
    private UUID id;

    public Wetsuit() {
    }

    public String getOriginWebpage() {
        return originWebpage;
    }

    public void setOriginWebpage(String originWebpage) {
        this.originWebpage = originWebpage;
    }

    public String getZipper() {
        return zipper;
    }

    public void setZipper(String zipper) {
        this.zipper = zipper;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Origin Webpage: " + originWebpage + "\n" +
                "Thickness: " + thickness;
    }

    public void wetsuitLookupAndSaveNewSize(Wetsuit wetsuit, WetsuitsRepository wetsuitsRepository, String size){
        final Wetsuit currentWetsuit = wetsuit;

        if (wetsuitsRepository.findAll().toString().contains(currentWetsuit.toString())) {
            List<Wetsuit> oldWetsuits = wetsuitsRepository.findAll().stream().filter(w -> (w.toString().contains(currentWetsuit.toString()))).collect(Collectors.toList());

            Wetsuit oldWetsuit = oldWetsuits.get(0);
            String oldSizes = oldWetsuit.getSize();

            oldWetsuit.setSize(oldSizes.concat(", ").concat(size.replace("/", "").toUpperCase()));
            wetsuit = oldWetsuit;
        } else {
            wetsuit.setSize(size.replace("/", "").toUpperCase());
        }

        wetsuitsRepository.save(wetsuit);
    }

    public String jsonSwatchSizeFinder(Element element){

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
                }else if(o.contains("Y")) {
                    int end = o.indexOf("Y");
                    String newString = "";
                    if((o.length()==5) || o.length()==9){
                        newString = o.substring(end -1, end+1);
                    }else if(o.length()==6 ||o.length()==10)
                        newString = o.substring(end -2, end+1);
                    if (newString.length()>0) {
                        finalSizeArray.add(newString);
                    }else{
                        finalSizeArray.add(o);
                    }
                } else {
                    String newString =  o.replaceAll("[0-9]", "");
                    if(newString.length()<4){
                        finalSizeArray.add(newString);
                    }
                }
            }
            int sizeLength = finalSizeArray.toString().length();

            finalSize = finalSizeArray.toString().substring(1, sizeLength-1);
        }
        return finalSize;
    }

    public boolean stringTooLongChecker(Wetsuit w){
        return w.getImageAddress().length() <= 700;
    }
}


