package com.parkinglot.test;
import com.parkinglot.classes.Car;
import com.parkinglot.classes.Slot;
import com.parkinglot.classes.Token;
import com.parkinglot.service.ParkingLot;

import org.junit.Test;

import javax.management.ObjectName;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private Token tokenNumber;

    @Test
    public void testToParkTheCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        Token token = numberOfSlots.parkTheCar(car);
        assertTrue(!token.getTokenNumber().isBlank());

    }

    @Test
    public void testToUnParkTheCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        Token abc = numberOfSlots.parkTheCar(car);

        String unParkMessage = numberOfSlots.unParkTheCar(abc);
        assertEquals(unParkMessage,"Car entry removed");
    }

    @Test
    public void testToParkManyCars(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc1");
        car.setCarColor("Blue1");
        Token abc = numberOfSlots.parkTheCar(car);

        car.setCarNumber("Abc2");
        car.setCarColor("Blue3");
        Token abc2 = numberOfSlots.parkTheCar(car);

        car.setCarNumber("Abc3");
        car.setCarColor("Blue3");
        Token abc3 = numberOfSlots.parkTheCar(car);

        assertEquals(abc3,null);

    }

    @Test
    public void testToSearchATokenPositive(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        Token token = numberOfSlots.parkTheCar(car);
        Token searchToken = numberOfSlots.searchTokenNumber(token.getTokenNumber());
        Car carDetails = searchToken.getCarDetails();
        assertEquals(carDetails.getCarColor(),"Blue");
    }

    @Test
    public void testToSearchATokenNegative(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        Token token = numberOfSlots.parkTheCar(car);
        Token searchToken = numberOfSlots.searchTokenNumber("WrongVal");
        assertEquals(searchToken,null);
    }
}
