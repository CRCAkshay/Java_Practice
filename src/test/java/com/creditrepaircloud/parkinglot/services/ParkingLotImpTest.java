package com.creditrepaircloud.parkinglot.services;

import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import com.creditrepaircloud.parkinglot.services.ParkingLotImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotImpTest {

    private ParkingLotImp parkingLot = new ParkingLotImp();

    @BeforeEach
    public void beforeAllMethod(){
        parkingLot.initiateLot(2);
    }

    @Test
    void testToInitializeASlotWithTwoSlots(){
        ArrayList<Slot> availableSlot = parkingLot.initiateLot(2);
        assertTrue(availableSlot.size()>0);
    }

    @Test
    void testToParkTheCar(){
        Token token = parkingLot.parkTheCar("Blue","TS08GG1234");
        assertFalse(token.getTokenNumber().isBlank());
    }
    @Test
    void testToUnParkTheLastParkedCar(){
        Token token = parkingLot.parkTheCar("Blue","TS08GG1234");
        String unParkMessage = parkingLot.unParkTheCar(token.getTokenNumber());
        assertEquals(unParkMessage,"Car entry removed");
    }
    @Test
    public void testUnParkInvalidCar(){
        Token token = parkingLot.parkTheCar("Blue","TS08GG1234");
        String newUnParkMessage = parkingLot.unParkTheCar(token.getCarDetails().getCarNumber()+"12");
        assertEquals(newUnParkMessage,"No token found");
    }

    @Test
    public void testToParkThreeCarWhenWeHaveTwoSlots(){
        Token token1 = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token token2 = parkingLot.parkTheCar("Red","TS08GG1232");
        Token token3 = parkingLot.parkTheCar("Yellow","TS08GG1233");

        //Change it to Exception
        assertNull(token3);

    }
    @Test
    void testToSearchATokenWithCarNumber(){
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token searchResult = parkingLot.getCar(initialToken.getCarDetails().getCarNumber());
        assertTrue(searchResult.getCarDetails().getCarNumber() == initialToken.getCarDetails().getCarNumber());
    }
    @Test
    void testToSearchATokenWithInvalidCarNumber(){
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token searchResult = parkingLot.getCar(initialToken.getCarDetails().getCarNumber()+"123");
        assertNull(searchResult);
    }
    @Test
    public void testNoList(){
        String searchCar = parkingLot.listAllCars();
        assertEquals(searchCar,"No any car so far");
    }
    @Test
    public void testToListAllCar(){
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token initialToken2 = parkingLot.parkTheCar("Red","TS08GG1232");
        String searchResult = parkingLot.listAllCars();
        assertTrue(!searchResult.isBlank());
    }
    @Test
    public void testTheHistoryOfTheParking(){
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token initialToken2 = parkingLot.parkTheCar("Red","TS08GG1232");
        parkingLot.unParkTheCar(initialToken.getTokenNumber());
        parkingLot.unParkTheCar(initialToken2.getTokenNumber());
        List<Token> searchResult = parkingLot.historyOfParking();
        assertTrue(!searchResult.isEmpty());
    }
}