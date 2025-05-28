package org.learning.di;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicationProperties {
    private final HashMap<String, String> properties;

    private ApplicationProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public String get(String value) {
        return this.properties.get(value);
    }

    static ApplicationProperties load() {
        List<String> lines = readLinesFromResourceFile("app.properties");
        HashMap<String, String> properties = parseProperties(lines);

        return new ApplicationProperties(properties);
    }

    private static HashMap<String, String> parseProperties(List<String> lines) {
        HashMap<String, String> properties = new HashMap<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue; // Skip empty lines and comments
            }
            String[] split = trimmed.split("=");
            if (split.length != 2) {
                throw new ApplicationPropertiesLoadError("Invalid property format: " + trimmed);
            }
            String key = split[0].trim();
            String value = split[1].trim();
            if(key.isEmpty() || value.isEmpty()) {
                throw new ApplicationPropertiesLoadError("Key or value cannot be empty in property: " + trimmed);
            }
            properties.put(key, value);
        }

        return properties;
    }

    private static List<String> readLinesFromResourceFile(String name) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(name);
        if(resource == null) {
            System.out.println("Resource file " + name + " not found.");
            return new ArrayList<>();
        }
        try {
            Path path = Paths.get(resource.toURI());
            return Files.readAllLines(path);
        } catch (URISyntaxException | IOException e) {
            System.out.println("Error reading resource file " + name + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
