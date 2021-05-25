package com.nyxgroup.wetsuit_hubweb;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WetsuitService {
    private final MockDatabaseRepository mockDatabaseRepository;

    public WetsuitService(MockDatabaseRepository mockDatabaseRepository) {
        this.mockDatabaseRepository = mockDatabaseRepository;
    }

    public List<Wetsuit> findAllWetsuits(String order) throws IOException, FileNotFoundException {
        String filePath = ResourceUtils.getFile("classpath:MockDatabase.JSON").getPath();
        List<Wetsuit> allWetsuits = mockDatabaseRepository.getAllWetsuitsFromFile(filePath);
        switch (order){
            case "A-Z":
                return allWetsuits.stream()
                        .sorted(Comparator.comparing(Wetsuit::getName, String.CASE_INSENSITIVE_ORDER))
                        .collect(Collectors.toList());
            case "Z-A":
                return allWetsuits.stream()
                        .sorted(Comparator.comparing(Wetsuit::getName, String.CASE_INSENSITIVE_ORDER)
                                .reversed())
                        .collect(Collectors.toList());
            default:
                return allWetsuits;
        }
    }
}
