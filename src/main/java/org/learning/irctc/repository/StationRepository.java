package org.learning.irctc.repository;

import org.learning.irctc.model.Station;

import java.util.HashMap;

public class StationRepository {
    private static final HashMap<String, Station> stations = new HashMap<>();

    static {
        stations.put("Mumbai", new Station("Mumbai", "01"));
        stations.put("Pune", new Station("Pune", "02"));
        stations.put("Delhi", new Station("Delhi", "03"));
        stations.put("Bengaluru", new Station("Bengaluru", "04"));
    }

    public Station findStation(String name) {
        System.out.println("StationRepository: finding station " + name);
        return stations.get(name);
    }
}
