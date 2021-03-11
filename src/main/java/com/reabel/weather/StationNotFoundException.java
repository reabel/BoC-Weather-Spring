package com.reabel.weather;

public class StationNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -2256888735313378006L;

    StationNotFoundException(Long id) {
        super("Could not find station " + id);
    }
}
