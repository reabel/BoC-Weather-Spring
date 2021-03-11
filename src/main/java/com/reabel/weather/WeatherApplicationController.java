package com.reabel.weather;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class WeatherApplicationController {
    // private static final Logger log =
    // LoggerFactory.getLogger(LoadDatabase.class);
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

        model.addAttribute("stations", stations);
        // referencing the Parameter directly wasn't working
        // so add it to the model for thymeleaf to handle directly
        model.addAttribute("sort", sort);
        return "table";
    }

    @GetMapping("/filter")
    public String filteredTable(@RequestParam(name = "dateFrom", required = true) String dateFrom,
            @RequestParam(name = "dateTo", required = true) String dateTo, Model model) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, dateFormat);
        LocalDate dateToParsed = LocalDate.parse(dateTo, dateFormat);
        ArrayList<Station> stations = new ArrayList<>(repository.findByDateBetween(dateFromParsed, dateToParsed));

        model.addAttribute("stations", stations);
        return "table-sorted";
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