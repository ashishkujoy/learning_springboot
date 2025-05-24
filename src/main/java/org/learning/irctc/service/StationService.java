package org.learning.irctc.service;

import org.learning.irctc.error.StationDoesNotExistsException;
import org.learning.irctc.model.Station;
import org.learning.irctc.repository.StationRepository;

public class StationService {
    private final StationRepository stationRepository;
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    public Station searchStation(String name) {
        System.out.println("Searching for station: " + name);
        return stationRepository.findStation(name);
    }

    public void validateStationsExists(String... stations) {
        for (String station : stations) {
            if(this.searchStation(station) == null) {
                throw new StationDoesNotExistsException(station);
            }
        }
    }
}
