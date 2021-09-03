package com.nyxgroup.wetsuit_hubweb;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class WetsuitController {

    private final WetsuitService wetsuitService;

    public WetsuitController( WetsuitService wetsuitService) {
        this.wetsuitService = wetsuitService;
    }

    @CrossOrigin
    @GetMapping("wetsuits")
    public List<Wetsuit> getAllWetsuits() {
        return wetsuitService.getAllWetsuits();
    }

    @CrossOrigin
    @GetMapping("wetsuits-mens")
    public List<Wetsuit> getMensWetsuits() {
        return wetsuitService.getMensWetsuits();
    }

    @CrossOrigin
    @GetMapping("wetsuits-womens")
    public List<Wetsuit> getWomensWetsuits() {
        return wetsuitService.getWomensWetsuits();
    }

    @CrossOrigin
    @GetMapping("wetsuits-kids")
    public List<Wetsuit> getKidsWetsuits() {
        return wetsuitService.getKidsWetsuits();
    }

    @CrossOrigin
    @PostMapping("scrape-wetsuits")
    public String scrapeWetsuits() {
        wetsuitService.scrapeWetsuits();
        return "Wetsuits Scraped";
    }

}
