package com.creditrepaircloud.parkinglot.services;

import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;

import java.util.ArrayList;

public interface ParkingLot {
    public Token parkTheCar(String carColor, String carNumber) throws Exception;
    public ArrayList<Slot> initiateLot (int numberOfLots);
    public String unParkTheCar(String tokenNumber) throws Exception;
    public Token getCar(String carNumber) throws Exception;
}
