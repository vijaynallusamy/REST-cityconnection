package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityConrollerHttpRequestTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCityLocationTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?fromCity=Boston&toCity=New York",
                String.class)).isEqualTo(CityConnectionStatusEnum.Yes.name());
    }

    @Test
    public void wrongFromCityTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?fromCity=Boston1&toCity=New York",
                String.class)).isEqualTo(CityConnectionStatusEnum.No.name());
    }

    @Test
    public void wrongToCityTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?fromCity=Boston&toCity=NewYork",
                String.class)).isEqualTo(CityConnectionStatusEnum.No.name());
    }

    @Test
    public void emptyToCityTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?fromCity=Boston",
                String.class)).isEqualTo(CityConnectionStatusEnum.No.name());
    }

    @Test
    public void emptyFromCityTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?toCity=Boston",
                String.class)).isEqualTo(CityConnectionStatusEnum.No.name());
    }

    @Test
    public void cityNameWithLowerCaseTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?fromCity=boston&toCity=new york",
                String.class)).isEqualTo(CityConnectionStatusEnum.Yes.name());
    }

    @Test
    public void interChangeFromAndToCityTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/api/city-connection?fromCity=Newark&toCity=Philadelphia",
                String.class)).isEqualTo(CityConnectionStatusEnum.Yes.name());
    }

    enum CityConnectionStatusEnum {
        Yes,
        No
    }

}
