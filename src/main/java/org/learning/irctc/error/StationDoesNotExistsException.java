package org.learning.irctc.error;

public class StationDoesNotExistsException extends RuntimeException {
    private final String station;

    public StationDoesNotExistsException(String station) {
        this.station = station;
    }
}
