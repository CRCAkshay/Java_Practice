package com.creditrepaircloud.parkinglot.services;

import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;

import java.util.ArrayList;

public interface ParkingLot {
    Token parkTheCar(String carColor, String carNumber) throws Exception;
    ArrayList<Slot> initiateLot (int numberOfLots);
    String unParkTheCar(String tokenNumber) throws Exception;
    Token getCar(String carNumber) throws Exception;
}
