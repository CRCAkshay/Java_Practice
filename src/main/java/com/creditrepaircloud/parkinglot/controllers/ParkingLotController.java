package com.creditrepaircloud.parkinglot.controllers;

import com.creditrepaircloud.parkinglot.domain.Car;
import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import com.creditrepaircloud.parkinglot.services.ParkingLot;
import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLot parkingLot;

    @PostMapping(value = "/ParkTheCar", consumes = "application/json")
    public Token parkCar(@RequestBody() JsonNode car) throws Exception {
        String carNumber = car.get(0).get("number").asText();
        String carColor = car.get(0).get("color").asText();

        Token token = parkingLot.parkTheCar(carColor,carNumber);
        return token;

    }
    @PostMapping(value = "/initiateLot", consumes = "application/json")
    public ArrayList<Slot> initiateLot(@RequestBody() JsonNode numberOfLot){
        ArrayList<Slot> availableSlot= parkingLot.initiateLot(Integer.parseInt(numberOfLot.get(0).get("numberOfLot").asText()));
        return  availableSlot;
    }
    @DeleteMapping("/unParkTheCar/{tokenNumber}")
    public String unParkCar(@PathVariable String tokenNumber) throws Exception{
        String parkingStatus = parkingLot.unParkTheCar(tokenNumber);
        return parkingStatus;
    }

    @GetMapping("/getCar/{carNumber}")
    public Token getCar(@PathVariable String carNumber) throws Exception{
        Token token = parkingLot.getCar(carNumber);
        return token;
    }

}
