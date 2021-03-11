package com.reabel.weather;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/")
    public String table(@RequestParam(name = "sort", defaultValue = "desc") String sort, Model model) {
        ArrayList<Station> stations;
        if (sort.equals("asc")) {
            stations = new ArrayList<>(repository.findByOrderByDateAsc());
        } else if (sort.equals("desc")) {
            stations = new ArrayList<>(repository.findByOrderByDateDesc());
        } else {
            stations = new ArrayList<>(repository.findAll());
        }

        log.info("sort:" + sort);
        model.addAttribute("stations", stations);
        return "table";
    }

    @GetMapping("/record")
    public String record(@RequestParam(name = "id", required = true, defaultValue = "Not Found") String name,
            Model model) {
        Long parsedId = Long.parseLong(name);
        Station station = repository.findById(parsedId).orElseThrow(() -> new StationNotFoundException(parsedId));
        model.addAttribute("station", station);
        return "station";
    }

    @GetMapping("/record/{id}")
    public String one(@PathVariable Long id, Model model) {
        Station station = repository.findById(id).orElseThrow(() -> new StationNotFoundException(id));
        model.addAttribute("station", station);
        return "station";
    }
}