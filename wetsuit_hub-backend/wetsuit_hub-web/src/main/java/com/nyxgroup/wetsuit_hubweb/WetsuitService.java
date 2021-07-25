package com.nyxgroup.wetsuit_hubweb;

import java.util.List;

public class WetsuitService {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitService(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public List<Wetsuit> getWetsuits() {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        return allWetsuits;
    }

    public void scrapeWetsuits() {
        DeeplyScraper deeplyScraper = new DeeplyScraper(wetsuitsRepository);
        deeplyScraper.getWetsuits();
    }
}
