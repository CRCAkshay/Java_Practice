package com.creditrepaircloud.parkinglot.services;
import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    void testToParkTheCar() throws Exception{
        Token token = parkingLot.parkTheCar("Blue","TS08GG1234");
        assertFalse(token.getTokenNumber().isBlank());
    }
    @Test
    void testToUnParkTheLastParkedCar() throws Exception{
        Token token = parkingLot.parkTheCar("Blue","TS08GG1234");
        String unParkMessage = parkingLot.unParkTheCar(token.getTokenNumber());
        assertEquals(unParkMessage,"Car entry removed");
    }
    @Test
    public void testUnParkInvalidCar() throws Exception{
        String newUnParkMessage = null;
        try {
            Token token = parkingLot.parkTheCar("Blue","TS08GG1234");
            parkingLot.unParkTheCar(token.getCarDetails().getCarNumber()+"12");
        }catch (Exception e) {
            newUnParkMessage = e.getMessage();
        }
        assertEquals(newUnParkMessage,"Invalid token number.");
    }

    @Test
    public void testToParkThreeCarWhenWeHaveTwoSlots() throws Exception{
        String NoSlotException = null;
        try {
            Token token1 = parkingLot.parkTheCar("Blue","TS08GG1234");
            Token token2 = parkingLot.parkTheCar("Red","TS08GG1232");
            Token token3 = parkingLot.parkTheCar("Yellow","TS08GG1233");
        }catch (Exception e){
            NoSlotException = e.getMessage();
        }
        assertEquals(NoSlotException,"No Slot available!");

    }
    @Test
    void testToSearchATokenWithCarNumber() throws Exception{
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token searchResult = parkingLot.getCar(initialToken.getCarDetails().getCarNumber());
        assertTrue(searchResult.getCarDetails().getCarNumber() == initialToken.getCarDetails().getCarNumber());
    }
    @Test
    void testToSearchATokenWithInvalidCarNumber() throws Exception{
        String ExceptionMessage = null;
        try {
            Token initialToken = parkingLot.parkTheCar("Blue", "TS08GG1234");
            Token searchResult = parkingLot.getCar(initialToken.getCarDetails().getCarNumber() + "123");
        }catch (Exception e){
            ExceptionMessage = e.getMessage();
        }
        assertEquals(ExceptionMessage,"No Car with the given car number");
    }
    @Test
    public void testNoList(){
        String searchCar = parkingLot.listAllCars();
        assertEquals(searchCar,"No any car so far");
    }
    @Test
    public void testToListAllCar() throws Exception{
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token initialToken2 = parkingLot.parkTheCar("Red","TS08GG1232");
        String searchResult = parkingLot.listAllCars();
        assertTrue(!searchResult.isBlank());
    }
    @Test
    public void testTheHistoryOfTheParking() throws Exception{
        Token initialToken = parkingLot.parkTheCar("Blue","TS08GG1234");
        Token initialToken2 = parkingLot.parkTheCar("Red","TS08GG1232");
        parkingLot.unParkTheCar(initialToken.getTokenNumber());
        parkingLot.unParkTheCar(initialToken2.getTokenNumber());
        List<Token> searchResult = parkingLot.historyOfParking();
        assertTrue(!searchResult.isEmpty());
    }

}