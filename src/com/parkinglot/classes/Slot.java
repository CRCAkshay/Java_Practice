package com.parkinglot.classes;

import java.util.ArrayList;

public class Slot {
    private boolean isParked;
    private Integer slotNumber;
    private ArrayList<Slot> totalSlots;

    public boolean isSlotFree() {
        return isParked != true;
    }
    public ArrayList<Slot> initializeSlot(int totalNumberOfSlot){

        this.totalSlots = new ArrayList<Slot>() {};
        for (int i=1; i<= totalNumberOfSlot; i++) {
            this.slotNumber =i;
            totalSlots.add(this);
        }

        return totalSlots;
    }
    public Slot makeSlotFree(){
        isParked = false;
        return this;
    }
    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void makeSlotOccupied() {
        this.isParked = false;
    }
}
