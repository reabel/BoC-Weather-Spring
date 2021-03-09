package com.reabel.weather;

public class StationNotFoundException extends RuntimeException {
    StationNotFoundException(Long id) {
        super("Could not find station " + id);
    }
}
