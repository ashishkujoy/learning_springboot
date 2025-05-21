package org.learning.irctc.service;

import org.learning.irctc.repository.StationRepository;

public class StationService {
    private final StationRepository stationRepository;
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    public void searchStation(String name) {
        System.out.println("Searching for station: " + name);
        stationRepository.findStation(name);
    }
}
