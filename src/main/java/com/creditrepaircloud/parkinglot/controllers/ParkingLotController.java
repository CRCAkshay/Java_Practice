package com.creditrepaircloud.parkinglot.controllers;

import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import com.creditrepaircloud.parkinglot.services.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLot parkingLot;

    @PostMapping("/ParkTheCar")
    public Token parkCar(@RequestParam("Color") String carColor,@RequestParam("Number") String carNumber){
        Token token = parkingLot.parkTheCar(carColor,carNumber);
        return token;
    }
    @PostMapping("/initiateLot")
    public ArrayList<Slot> initiateLot(@RequestParam("NumberOfLot") String numberOfLot){
        ArrayList<Slot> availableSlot= parkingLot.initiateLot(Integer.parseInt(numberOfLot));
        return  availableSlot;
    }
    @DeleteMapping("/unParkTheCar/{tokenNumber}")
    public String unParkCar(@PathVariable String tokenNumber){
        String parkingStatus = parkingLot.unParkTheCar(tokenNumber);
        return parkingStatus;
    }

    @GetMapping("/getCar/{carNumber}")
    public Token getCar(@PathVariable String carNumber) {
        Token token = parkingLot.getCar(carNumber);
        return token;
    }

}
