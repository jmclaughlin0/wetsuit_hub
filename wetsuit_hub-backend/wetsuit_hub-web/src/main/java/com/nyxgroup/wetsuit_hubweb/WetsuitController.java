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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("wetsuits")
    public List<Wetsuit> getAllWetsuits(@RequestParam(required = false) String g, @RequestParam(required = false) String t) {
        return wetsuitService.getAllWetsuits(g, t);
    }

    @CrossOrigin
    @PostMapping("scrape-wetsuits")
    public String scrapeWetsuits() {
        wetsuitService.scrapeWetsuits();
        return "Wetsuits Scraped";
    }

}
