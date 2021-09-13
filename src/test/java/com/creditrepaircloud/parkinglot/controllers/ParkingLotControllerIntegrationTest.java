package com.creditrepaircloud.parkinglot.controllers;

import com.creditrepaircloud.parkinglot.ParkinglotApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

@SpringBootTest(classes = ParkinglotApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class ParkingLotControllerIntegrationTest
{
    @LocalServerPort
    private int port;

    @Autowired
    private ParkingLotController parkingLotController;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Test
    public void testGetCarIntegration()
    {


    }
    @Test
    public void addCourse() {


    }
}