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
        WetsuitCenterScraper wetsuitCenterScraper = new WetsuitCenterScraper(wetsuitsRepository);
        WetsuitOutletScraper wetsuitOutletScraper = new WetsuitOutletScraper(wetsuitsRepository);
        SurfDomeScraper surfDomeScraper = new SurfDomeScraper(wetsuitsRepository);
        NeedEssentialsScraper needEssentialsScraper = new NeedEssentialsScraper(wetsuitsRepository);
        SortedScraper sortedScraper = new SortedScraper(wetsuitsRepository);
        TikiScraper tikiScraper = new TikiScraper(wetsuitsRepository);

//        wetsuitCenterScraper.getWetsuits();
//        wetsuitOutletScraper.getWetsuits();
//        surfDomeScraper.getWetsuits();
//        needEssentialsScraper.getWetsuits();
//        sortedScraper.getWetsuits();
        tikiScraper.getWetsuits();
    }
}
