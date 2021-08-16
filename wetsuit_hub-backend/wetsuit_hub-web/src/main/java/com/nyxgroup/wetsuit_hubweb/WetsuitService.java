package com.nyxgroup.wetsuit_hubweb;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WetsuitService {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitService(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public List<Wetsuit> getWetsuits() {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));
        return allWetsuits;
    }

    public List<Wetsuit> getGenderWetsuits(String gender) {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));
        if(gender == null){
            return allWetsuits;
        }else {
            List <Wetsuit> genderWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender() == gender)).collect(Collectors.toList());
            return genderWetsuits;
        }

    }

    public void scrapeWetsuits() {
        wetsuitsRepository.deleteAllInBatch();
        wetsuitsRepository.flush();
        DeeplyScraper deeplyScraper = new DeeplyScraper(wetsuitsRepository);
        WetsuitCenterScraper wetsuitCenterScraper = new WetsuitCenterScraper(wetsuitsRepository);
        WetsuitOutletScraper wetsuitOutletScraper = new WetsuitOutletScraper(wetsuitsRepository);

        deeplyScraper.getWetsuits();
//        wetsuitCenterScraper.getWetsuits();
//        wetsuitOutletScraper.getWetsuits();

    }
}
