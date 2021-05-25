package com.nyxgroup.wetsuit_hubweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class MockDatabaseRepository {
    public List<Wetsuit> getAllWetsuitsFromFile(String fileLocation) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Wetsuit> wetsuitList = objectMapper.readValue(
                new File(fileLocation),
                new TypeReference<>() {
                });

        return wetsuitList;
    }
}
