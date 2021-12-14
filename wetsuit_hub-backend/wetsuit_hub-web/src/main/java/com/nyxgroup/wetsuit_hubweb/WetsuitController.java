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


    @CrossOrigin("*")
    @GetMapping("welcome")
    public String healthChecker(){
        return "The app is working...";
    }

    @CrossOrigin("*")
    @GetMapping("wetsuits")
    public List<Wetsuit> getAllWetsuits(@RequestParam(required = false) String g,
                                        @RequestParam(required = false) String t,
                                        @RequestParam(required = false) String z,
                                        @RequestParam(required = false) String p,
                                        @RequestParam(required = false) String o,
                                        @RequestParam(required = false) String s,
                                        @RequestParam(required = false) String h,
                                        @RequestParam(required = false) String d
                                        )
    {
        return wetsuitService.getAllWetsuits(g, t, z, p, o, s, h, d);
    }

    @CrossOrigin("*")
    @GetMapping("pages")
    public int getNumberPages(@RequestParam(required = false) String g,
                              @RequestParam(required = false) String t,
                              @RequestParam(required = false) String z,
                              @RequestParam(required = false) String s,
                              @RequestParam(required = false) String h,
                              @RequestParam(required = false) String d)
    {
        return wetsuitService.getNumberPages(g, t, z, s, h, d);
    }

    @CrossOrigin("*")
    @PostMapping("scrape-wetsuits")
    public String scrapeWetsuits() {
        return wetsuitService.scrapeWetsuits();
    }

    @CrossOrigin("*")
    @GetMapping("stateUpdate")
    public String getStateOfUpdate() {
        return wetsuitService.getStateOfUpdate();
    }

}
