package com.nyxgroup.wetsuit_hubweb;

import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;

public class WetsuitService {
    private final MockDatabaseRepository mockDatabaseRepository;

    public WetsuitService(MockDatabaseRepository mockDatabaseRepository) {
        this.mockDatabaseRepository = mockDatabaseRepository;
    }

    public List<Wetsuit> getWetsuits() throws IOException {
        String filePath = ResourceUtils.getFile("classpath:MockDatabase.JSON").getPath();
        List<Wetsuit> allWetsuits = mockDatabaseRepository.getAllWetsuitsFromFile(filePath);
        return allWetsuits;
    }
}
