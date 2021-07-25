package com.nyxgroup.wetsuit_hubweb;

import javax.persistence.*;
import java.util.UUID;

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
    private String size;

    @Column
    @Id
    @GeneratedValue
    private UUID id;

    public Wetsuit() {
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
                "Web Address: " + webAddress + "\n" +
                "Thickness: " + thickness +
                "Size: " + size;
    }
}


