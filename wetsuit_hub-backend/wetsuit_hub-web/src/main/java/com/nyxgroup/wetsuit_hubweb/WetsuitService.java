package com.nyxgroup.wetsuit_hubweb;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WetsuitService {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitService(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public List<Wetsuit> getAllWetsuits(String g, String t) {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();
        allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

        if(t == "" && g == ""){
            return allWetsuits;
        }

        List <Wetsuit> genderWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals(g))).collect(Collectors.toList());

        if(t == ""){
            return genderWetsuits;
        }

        List <Wetsuit> thicknessWetsuits = genderWetsuits.stream().filter(wetsuit -> (wetsuit.getThickness().equals(t))).collect(Collectors.toList());

        return thicknessWetsuits;
    }


    public void scrapeWetsuits() {
        wetsuitsRepository.deleteAllInBatch();
        wetsuitsRepository.flush();
        DeeplyScraper deeplyScraper = new DeeplyScraper(wetsuitsRepository);
        WetsuitCenterScraper wetsuitCenterScraper = new WetsuitCenterScraper(wetsuitsRepository);
        WetsuitOutletScraper wetsuitOutletScraper = new WetsuitOutletScraper(wetsuitsRepository);

//        deeplyScraper.getWetsuits();
        wetsuitCenterScraper.getWetsuits();
//        wetsuitOutletScraper.getWetsuits();

    }
}
