package com.nyxgroup.wetsuit_hubweb;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List<Wetsuit> getAllWetsuits() throws IOException {
        return wetsuitService.getWetsuits();
    }

    @CrossOrigin
    @PostMapping("scrape-wetsuits")
    public String scrapeWetsuits() {
        wetsuitService.scrapeWetsuits();
        return "Wetsuits Scraped";
    }
    
}
