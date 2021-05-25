package com.nyxgroup.wetsuit_hubweb;

import java.util.UUID;

public class Wetsuit {
    private UUID id;
    private String name;
    private String size;
    private int price;
    private String webAddress;

    public Wetsuit(UUID id, String name, int price, String webAddress, String size) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.size = size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
