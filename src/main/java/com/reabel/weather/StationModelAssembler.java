package com.reabel.weather;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class StationModelAssembler implements RepresentationModelAssembler<Station, EntityModel<Station>> {

    @Override
    public EntityModel<Station> toModel(Station station) {

        return EntityModel.of(station, //
                linkTo(methodOn(StationController.class).one(station.getId())).withSelfRel(),
                linkTo(methodOn(StationController.class).all()).withRel("stations"));
    }
}
