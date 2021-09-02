package com.parkinglot.test.classes;
import com.parkinglot.classes.Car;
import org.junit.Test;
import static org.junit.Assert.*;
public class CarTest {
    @Test
    public void testTheCarObject(){
        Car car = new Car();
        car.setCarNumber("123");
        car.setCarColor("Blue");
        assertEquals(car.getCarColor(),"Blue");
        assertEquals(car.getCarNumber(),"123");
    }
}
