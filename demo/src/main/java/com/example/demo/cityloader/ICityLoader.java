package com.example.demo.cityloader;

import com.example.demo.domain.CityConnection;
import com.example.demo.exception.ServiceException;

import java.util.ArrayList;

public interface ICityLoader {
    ArrayList<CityConnection> loadCity() throws ServiceException;
}
