package com.reabel.weather;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class WeatherApplicationController {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final StationRepository repository;

    WeatherApplicationController(StationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/table")
    public String table(Model model) {
        ArrayList<Station> stations = new ArrayList<>(repository.findAll());
        log.info("Stations:", stations);
        model.addAttribute("stations", stations);
        return "table";
    }

    // @GetMapping("/record")
    // public String greeting(@RequestParam(name = "id", required = true,
    // defaultValue = "Not Found") String name,
    // Model model) {
    // model.addAttribute("station", station);
    // return "record";
    // }

    @GetMapping("/record/{id}")
    public String one(@PathVariable Long id, Model model) {
        Station station = repository.findById(id).orElseThrow(() -> new StationNotFoundException(id));
        model.addAttribute("station", station);
        return "station";
    }
}