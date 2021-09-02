package com.parkinglot.test;
import com.parkinglot.classes.Car;
import com.parkinglot.classes.Token;
import com.parkinglot.service.ParkingLot;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingLotTest {
    @Test
    public void testToParkTheCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        Token token = numberOfSlots.parkTheCar(car);
        assertFalse(token.getTokenNumber().isBlank());

    }

    @Test
    public void testToUnParkTheCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        Token Token = numberOfSlots.parkTheCar(car);

        String unParkMessage = numberOfSlots.unParkTheCar(Token.getTokenNumber());
        assertEquals(unParkMessage,"Car entry removed");
    }

    @Test
    public void testToParkManyCars(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc1");
        car.setCarColor("Blue1");
        numberOfSlots.parkTheCar(car);

        car.setCarNumber("Abc2");
        car.setCarColor("Blue3");
        numberOfSlots.parkTheCar(car);

        car.setCarNumber("Abc3");
        car.setCarColor("Blue3");
        Token Token3 = numberOfSlots.parkTheCar(car);

        assertNull(Token3);

    }

    @Test
    public void testToSearchATokenPositive(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("123");
        car.setCarColor("Blue");
        numberOfSlots.parkTheCar(car);
        String searchToken = numberOfSlots.searchCarNumber("123");
        assertNotNull(searchToken);
    }

    @Test
    public void testToSearchATokenNegative(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("Abc");
        car.setCarColor("Blue");
        numberOfSlots.parkTheCar(car);
        String searchToken = numberOfSlots.searchCarNumber("WrongVal");
        assertEquals(searchToken,"There is no any car");
    }

    @Test
    public void testTolistAllCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car();
        car.setCarNumber("123");
        car.setCarColor("Blue");
        numberOfSlots.parkTheCar(car);
        String searchToken = numberOfSlots.listAllCars();
        assertNotNull(searchToken);
    }
}
