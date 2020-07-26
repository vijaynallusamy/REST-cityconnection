package com.example.demo.domain;

import java.util.Objects;

public class CityConnection {

    private String fromCity;
    private String toCity;

    public CityConnection(String fromCity, String toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    @Override
    public String toString() {
        return "CityConnection{" +
                "fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                '}';
    }

    public String getFromCity() {
        return fromCity.trim().toLowerCase();
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity.trim().toLowerCase();
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityConnection)) return false;
        CityConnection that = (CityConnection) o;
        return (getFromCity().equals(that.getFromCity()) &&
                getToCity().equals(that.getToCity())) || (getToCity().equals(that.getFromCity())
                && getFromCity().equals(that.getToCity()));
    }


    @Override
    public int hashCode() {
        return Objects.hash(getFromCity(), getToCity());
    }
}
