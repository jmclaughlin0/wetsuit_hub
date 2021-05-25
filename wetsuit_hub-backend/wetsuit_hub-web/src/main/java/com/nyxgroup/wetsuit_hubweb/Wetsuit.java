package com.nyxgroup.wetsuit_hubweb;

import java.util.UUID;

public class Wetsuit {
    private UUID id;
    private String name;
    private int price;
    private String webAddress;

    public Wetsuit(UUID id, String name, int price, String webAddress) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.webAddress = webAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }
}
