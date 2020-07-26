package com.example.demo.controller;

import com.example.demo.cityloader.ICityLoader;
import com.example.demo.domain.CityConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CityConnectionController {
    private final ICityLoader cityLoader;

    public CityConnectionController(@Autowired ICityLoader cityLoader) {
        this.cityLoader = cityLoader;
    }

    enum CityConnectionStatusEnum {
        Yes,
        No
    }
    @GetMapping("city-connection")
    public ResponseEntity<String> geCityConnection(@RequestParam( required = false) String fromCity,
                                                    @RequestParam( required = false) String toCity) {

        List<CityConnection> cityConnections = cityLoader.loadCity();

        CityConnectionStatusEnum connectionStatusEnum;
        if (fromCity == null || toCity == null) {
            connectionStatusEnum = CityConnectionStatusEnum.No;
        } else {
            connectionStatusEnum = cityConnections.contains(new CityConnection(fromCity, toCity)) ?
                    CityConnectionStatusEnum.Yes : CityConnectionStatusEnum.No;
        }
        return ResponseEntity.ok().body(connectionStatusEnum.name());

    }

}
