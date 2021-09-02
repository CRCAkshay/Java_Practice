package com.parkinglot.classes;

import java.util.ArrayList;

public class Slot {
    private boolean isParked;
    private Integer slotNumber;


    public Slot(Integer slotNumber){
        this.slotNumber = slotNumber;
        this.isParked = false;
    }

    public boolean isSlotFree() {
        return isParked != true;
    }

    public Slot makeSlotFree(){
        isParked = false;
        return this;
    }
    public Integer getSlotNumber() {
        return slotNumber;
    }
    public void makeSlotOccupied() {
        this.isParked = true;
    }

}
