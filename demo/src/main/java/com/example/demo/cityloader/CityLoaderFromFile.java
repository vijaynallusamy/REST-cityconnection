package com.example.demo.cityloader;

import com.example.demo.domain.CityConnection;
import com.example.demo.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityLoaderFromFile implements  ICityLoader{

    @Value("${cityFile.path}")
    private String userBucketPath;

    private final List<CityConnection> cityConnections = new ArrayList<>();



    @Override
    public ArrayList<CityConnection> loadCity() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ResourceUtils.getFile(userBucketPath)))) {
            bufferedReader.lines().forEach(line -> {
                String[] cities = line.split(",");
                CityConnection cityConnection = new CityConnection(cities[0], cities[1]);
                cityConnections.add(cityConnection);

            });
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }


        return (ArrayList<CityConnection>) cityConnections;
    }
}
