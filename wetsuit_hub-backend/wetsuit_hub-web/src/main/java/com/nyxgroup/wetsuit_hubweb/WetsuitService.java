package com.nyxgroup.wetsuit_hubweb;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.stream.Collectors;

public class WetsuitService {
    private final WetsuitsRepository wetsuitsRepository;

    private String stateOfUpdate;

    public WetsuitService(WetsuitsRepository wetsuitsRepository) {
        this.wetsuitsRepository = wetsuitsRepository;
    }

    public List<Wetsuit> getAllWetsuits(String g, String t, String z, String p, String o, String s, String h, String d) {
        List<Wetsuit> allWetsuits = getAllUnsortedWetsuits(g, t, z, s, h, d);

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

        if(!(p==null)&&!p.isBlank()&&!Objects.equals(p, "")){
            int pageNumber = Integer.parseInt(p);

            int startPage = pageNumber*20 - 20;
            int endPage = pageNumber* 20;

            if(startPage > allWetsuits.size()){
                return Collections.emptyList();
            }

            if(endPage > allWetsuits.size()){
                endPage = allWetsuits.size();
            }

            allWetsuits =  allWetsuits.subList(startPage, endPage);
        }

        return allWetsuits;
    }


    @Scheduled(fixedRate = 72000000)
    public String scrapeWetsuits() {
        stateOfUpdate = "updateInProgress";
        wetsuitsRepository.deleteAllInBatch();
        wetsuitsRepository.flush();
        WetsuitCenterScraper wetsuitCenterScraper = new WetsuitCenterScraper(wetsuitsRepository);
        WetsuitOutletScraper wetsuitOutletScraper = new WetsuitOutletScraper(wetsuitsRepository);
        SurfDomeScraper surfDomeScraper = new SurfDomeScraper(wetsuitsRepository);
        NeedEssentialsScraper needEssentialsScraper = new NeedEssentialsScraper(wetsuitsRepository);
        SortedScraper sortedScraper = new SortedScraper(wetsuitsRepository);
        TikiScraper tikiScraper = new TikiScraper(wetsuitsRepository);
        BlueTomatoScraper blueTomatoScraper = new BlueTomatoScraper(wetsuitsRepository);

        sortedScraper.getWetsuits();
        tikiScraper.getWetsuits();
        blueTomatoScraper.getWetsuits();
        wetsuitOutletScraper.getWetsuits();
        wetsuitCenterScraper.getWetsuits();
        surfDomeScraper.getWetsuits();

        needEssentialsScraper.getWetsuits();
//        ^ Am I going to do sizes for this one...??

        stateOfUpdate = "completed";
        String finished = "Finished Scraping Wetsuits...";
        System.out.println(finished);
        return finished;
    }

    public int getNumberPages(String g, String t, String z, String s, String h, String d) {

        List<Wetsuit> allWetsuits = getAllUnsortedWetsuits(g, t, z, s, h, d);

        int size;

        if(allWetsuits.size()%20 == 0){
            size = allWetsuits.size()/20;
        }else size = allWetsuits.size()/20 + 1;


        return size;
    }

    public String getStateOfUpdate() {
        return stateOfUpdate;
    }

    private List<Wetsuit> getAllUnsortedWetsuits(String g, String t, String z, String s, String h, String d){
        List<Wetsuit> allWetsuits = wetsuitsRepository.findAll();

        if(!(s==null)&&!s.isBlank()&&!Objects.equals(s, "")){
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getName().toLowerCase().contains(s.toLowerCase()))).collect(Collectors.toList());
        }

        if(!(d==null)&&!d.isBlank()&&!Objects.equals(d, "")){
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getSize().toLowerCase().contains(d.toLowerCase()))).collect(Collectors.toList());
        }

        if(!(z==null)&&!z.isBlank()&&Objects.equals(z, "true")){
            List <Wetsuit> ziplessWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getZipper().equals("Zipperless"))).collect(Collectors.toList());

            ArrayList<Wetsuit> zipWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getZipper().equals("Chest Zip"))).collect(Collectors.toCollection(ArrayList::new));

            zipWetsuits.addAll(ziplessWetsuits);

            zipWetsuits.sort(Comparator.comparing(Wetsuit::getPrice));

            allWetsuits = zipWetsuits;
        }

        if(!(t==null)&&!t.isBlank()&&!Objects.equals(t, "")) {
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getThickness().equals(t))).collect(Collectors.toList());
        }

        if(!(g==null)&&!g.isBlank()&&!Objects.equals(g, "")){
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getGender().equals(g))).collect(Collectors.toList());
        }

        if(!(h==null)&&!h.isBlank()&&!Objects.equals(h, "")){
            allWetsuits = allWetsuits.stream().filter(wetsuit -> (wetsuit.getName().toLowerCase().contains("hood"))).collect(Collectors.toList());
        }
        return allWetsuits;
    }
}
