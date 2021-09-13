package com.creditrepaircloud.parkinglot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    @Test
    public void testTheCarObject(){
    Car car = new Car("Blue","TS08GG1234");
    assertEquals("TS08GG1234",car.getCarNumber());
}
}