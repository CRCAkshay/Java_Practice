package com.parkinglot.service;

import com.parkinglot.classes.Car;
import com.parkinglot.classes.Slot;
import com.parkinglot.classes.Token;
import java.util.*;

public class ParkingLot {
    private int totalNumberOfSlots;
    ArrayList<Slot> availableSlotList;
    private List<Token> tokenForLot;
    private List<Token> historyOfParking;

    public ParkingLot(int totalNumberOfSlots){
        this.totalNumberOfSlots = totalNumberOfSlots;
        this.tokenForLot = new ArrayList<>();
        this.historyOfParking = new ArrayList<>();
        Slot getSlotAssignment = new Slot();
        this.availableSlotList = getSlotAssignment.initializeSlot(totalNumberOfSlots);
    }

    public Token parkTheCar(Car car){
        if(isSlotAvailble()){
            Slot availableSlot = getTheNextFreeSlot();
            Token parkingToken = new Token(String.valueOf(System.currentTimeMillis()),availableSlot,car);
            this.tokenForLot.add(parkingToken);
            return parkingToken;
        }else {
            return null;
        }
    }

    public String unParkTheCar(Token token){
        for(Token tokenInLot:tokenForLot){
            if(tokenInLot.getTokenNumber() == token.getTokenNumber()){
                tokenForLot.remove(tokenInLot);
                Slot slot = token.getSlotDetails();
                int slotNumber = slot.getSlotNumber();
                String processMessage = removeCarFromSlot(token,slotNumber);
                return  processMessage;
            }
            return "No token found";
        }
        return null;
    }

    private String removeCarFromSlot(Token token, int slotNumber) {
        for (Slot removeEntry:availableSlotList){

            if(removeEntry.getSlotNumber() == slotNumber){
                removeEntry.makeSlotFree();
                Token historyToken = token.updateCheckOutTime();
                historyOfParking.add(historyToken);
                return "Car entry removed";
            }

        }
        return null;
    }

    private Slot getTheNextFreeSlot() {
        for(Slot slot : availableSlotList){
            if(slot.isSlotFree()){
                slot.makeSlotOccupied();
                return slot;
            }
        }
        return null;
    }
    public Token searchTokenNumber(String tokenNumber){
        for(Token tokenSearch:tokenForLot){
            if(tokenSearch.getTokenNumber() == tokenNumber){
                return tokenSearch;
            }
        }
        return null;
    }
    private boolean isSlotAvailble() {
        boolean isSlotAvailble = false;

        for(Slot slot:availableSlotList){
            if(slot.isSlotFree()){
                isSlotAvailble = true;
                break;
            }
        }
        return isSlotAvailble;
    }
}
