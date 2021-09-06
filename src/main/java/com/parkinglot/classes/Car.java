package com.parkinglot.classes;

public class Car {
    private String carColor;
    private String carNumber;

    public Car(String carColor, String carNumber){
        this.carColor = carColor;
        this.carNumber = carNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getCarNumber() {
        return carNumber;
    }
}
