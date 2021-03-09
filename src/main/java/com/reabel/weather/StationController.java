package com.reabel.weather;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;

@RestController
class StationController {

    private final StationRepository repository;

    private final StationModelAssembler assembler;

    StationController(StationRepository repository, StationModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/stations")
    CollectionModel<EntityModel<Station>> all() {

        List<EntityModel<Station>> stations = repository.findAll().stream().map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(stations, linkTo(methodOn(StationController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/stations")
    Station newStation(@RequestBody Station newStation) {
        return repository.save(newStation);
    }

    // Single item

    @GetMapping("/stations/{id}")
    EntityModel<Station> one(@PathVariable Long id) {

        Station station = repository.findById(id).orElseThrow(() -> new StationNotFoundException(id));

        return assembler.toModel(station);
    }

    @PutMapping("/stations/{id}")
    Station replaceStation(@RequestBody Station newStation, @PathVariable Long id) {

        return repository.findById(id).map(Station -> {
            Station.setStationName(newStation.getStationName());
            Station.setProvince(newStation.getProvince());
            return repository.save(Station);
        }).orElseGet(() -> {
            newStation.setId(id);
            return repository.save(newStation);
        });
    }

    @DeleteMapping("/stations/{id}")
    void deleteStation(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
