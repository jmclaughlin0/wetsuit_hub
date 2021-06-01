package com.nyxgroup.wetsuit_hubweb;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("wetsuits")
public class WetsuitController {

    private final WetsuitService wetsuitService;

    public WetsuitController( WetsuitService wetsuitService) {
        this.wetsuitService = wetsuitService;
    }

    @CrossOrigin
    @GetMapping
    public List<Wetsuit> getAllWetsuits() throws IOException {
        return wetsuitService.getWetsuits();
    }
    
}
