package com.creditrepaircloud.parkinglot.controllers;

import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import com.creditrepaircloud.parkinglot.exception.BadRequest;
import com.creditrepaircloud.parkinglot.services.ParkingLot;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLot parkingLot;

    @PostMapping(value = "/ParkTheCar", consumes = "application/json")
    public Token parkCar(@RequestBody() JsonNode car) throws Exception {
        if(!car.isEmpty()) {
            String carNumber = car.get(0).get("number").asText();
            String carColor = car.get(0).get("color").asText();
            return parkingLot.parkTheCar(carColor, carNumber);
        }else{
            throw new BadRequest("Missing Body Parameters!");
        }
    }
    @PostMapping(value = "/initiateLot", consumes = "application/json")
    public ArrayList<Slot> initiateLot(@RequestBody() JsonNode numberOfLot){
        return parkingLot.initiateLot(Integer.parseInt(numberOfLot.get(0).get("numberOfLot").asText()));
    }
    @DeleteMapping("/unParkTheCar/{tokenNumber}")
    public String unParkCar(@PathVariable String tokenNumber) throws Exception{
        return parkingLot.unParkTheCar(tokenNumber);
    }

    @GetMapping("/getCar/{carNumber}")
    public Token getCar(@PathVariable String carNumber) throws Exception{
        return parkingLot.getCar(carNumber);
    }

}
