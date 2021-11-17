package com.nyxgroup.wetsuit_hubweb;

import java.util.*;
import java.util.stream.Collectors;

public class WetsuitService {
    private final WetsuitsRepository wetsuitsRepository;

    public WetsuitService(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public List<Wetsuit> getAllWetsuits(String g, String t, String z, String p, String o) {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();

        if(Objects.equals(z, "true")){
            List <Wetsuit> ziplessWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getZipper().equals("Zipperless"))).collect(Collectors.toList());

            ArrayList<Wetsuit> zipWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getZipper().equals("Chest Zip"))).collect(Collectors.toCollection(ArrayList::new));

            zipWetsuits.addAll(ziplessWetsuits);

            zipWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

            allWetsuits = zipWetsuits;
        }

        if(!Objects.equals(t, "")) {
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getThickness().equals(t))).collect(Collectors.toList());
        }

        if(!Objects.equals(g, "")){
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals(g))).collect(Collectors.toList());
        }

        if(Objects.equals(o, "PA")){
            allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));
        }else if(Objects.equals(o, "PD")){
            allWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));
            Collections.reverse(allWetsuits);
        }else if(Objects.equals(o, "AB")){
            allWetsuits.sort(Comparator.comparing(Wetsuit::getName));
        }else if(Objects.equals(o, "RA")){
            allWetsuits.sort(Comparator.comparing(Wetsuit::getName));
            Collections.reverse(allWetsuits);
        }


//        int pageNumber = Integer.parseInt(p);
//
//        int startPage = pageNumber*20 - 20;
//        int endPage = pageNumber* 20;
//
//        if(startPage > allWetsuits.size()){
//            return Collections.emptyList();
//        }
//
//        if(endPage > allWetsuits.size()){
//            endPage = allWetsuits.size();
//        }

//        return allWetsuits.subList(startPage, endPage);

        return allWetsuits;
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
        BlueTomatoScraper blueTomatoScraper = new BlueTomatoScraper(wetsuitsRepository);

        wetsuitCenterScraper.getWetsuits();
        wetsuitOutletScraper.getWetsuits();
        surfDomeScraper.getWetsuits();
        needEssentialsScraper.getWetsuits();
        sortedScraper.getWetsuits();
        tikiScraper.getWetsuits();
//        blueTomatoScraper.getWetsuits();

    }

    public int getNumberPages(String g, String t, String z) {
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();

        if(Objects.equals(z, "true")){
            List <Wetsuit> ziplessWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getZipper().equals("Zipperless"))).collect(Collectors.toList());

            ArrayList<Wetsuit> zipWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getZipper().equals("Chest Zip"))).collect(Collectors.toCollection(ArrayList::new));

            zipWetsuits.addAll(ziplessWetsuits);

            zipWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

            allWetsuits = zipWetsuits;
        }

        if(!Objects.equals(t, "")) {
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getThickness().equals(t))).collect(Collectors.toList());
        }

        if(!Objects.equals(g, "")){
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals(g))).collect(Collectors.toList());
        }

        int size = 0;

        if(allWetsuits.size()%20 == 0){
            size = allWetsuits.size()/20;
        }else size = allWetsuits.size()/20 + 1;


        return size;
    }
}
