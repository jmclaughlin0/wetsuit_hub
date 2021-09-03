package com.nyxgroup.wetsuit_hubweb;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WetsuitService {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitService(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public List<Wetsuit> getAllWetsuits() {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));
        return allWetsuits;
    }

    public List<Wetsuit> getMensWetsuits() {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

        List <Wetsuit> genderWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals("Mens"))).collect(Collectors.toList());
            return genderWetsuits;

    }

    public List<Wetsuit> getWomensWetsuits() {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

        List <Wetsuit> genderWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals("Womens"))).collect(Collectors.toList());
        return genderWetsuits;

    }

    public List<Wetsuit> getKidsWetsuits() {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

        List <Wetsuit> genderWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals("Kids"))).collect(Collectors.toList());
        return genderWetsuits;

    }


    public void scrapeWetsuits() {
        wetsuitsRepository.deleteAllInBatch();
        wetsuitsRepository.flush();
        DeeplyScraper deeplyScraper = new DeeplyScraper(wetsuitsRepository);
        WetsuitCenterScraper wetsuitCenterScraper = new WetsuitCenterScraper(wetsuitsRepository);
        WetsuitOutletScraper wetsuitOutletScraper = new WetsuitOutletScraper(wetsuitsRepository);

        deeplyScraper.getWetsuits();
        wetsuitCenterScraper.getWetsuits();
        wetsuitOutletScraper.getWetsuits();

    }
}
