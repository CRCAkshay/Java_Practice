package com.creditrepaircloud.parkinglot.services;

import com.creditrepaircloud.parkinglot.domain.Car;
import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import com.creditrepaircloud.parkinglot.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotImp implements ParkingLot{
   ArrayList<Slot> availableSlotList;
   private final List<Token> tokenForLot;
   private final List<Token> historyOfParking;

   public ParkingLotImp() {
      this.tokenForLot = new ArrayList<>();
      this.historyOfParking = new ArrayList<>();
   }

   @Override
   public ArrayList<Slot> initiateLot(int numberOfLots) {
      ArrayList<Slot> totalSlots = new ArrayList<>() {};
      for (int i = 1; i<= numberOfLots; i++) {
         Slot getSlotAssignment = new Slot(i);
         totalSlots.add(getSlotAssignment);
      }

      return this.availableSlotList = totalSlots;
   }

   public Token parkTheCar(String carColor, String carNumber) throws Exception{
      Car car = new Car(carColor,carNumber);
      if(isSlotAvailable()){
         Slot availableSlot = getTheNextFreeSlot();
         Token parkingToken = new Token(String.valueOf(System.currentTimeMillis()),availableSlot,car);
         this.tokenForLot.add(parkingToken);
         return parkingToken;
      }else {
         throw new DataNotFoundException("Slot is not initialized!");
      }
   }
   private boolean isSlotAvailable() {
      if(availableSlotList!=null) {
         boolean isSlotAvailable = false;

         for (Slot slot : availableSlotList) {
            if (slot.isSlotFree()) {
               isSlotAvailable = true;
               break;
            }
         }
         return isSlotAvailable;
      }else {
         throw new DataNotFoundException("Slot is not initialized!");
      }
   }
   private Slot getTheNextFreeSlot() throws Exception{
         for(Slot slot : availableSlotList){
            if(slot.isSlotFree()){
               slot.makeSlotOccupied();
               return slot;
            }
         }
      throw new DataNotFoundException("Slot is not initialized!");
   }

   @Override
   public Token getCar(String carNumber) throws Exception{
      for(Token tokenSearch:tokenForLot){
         String carDetails = tokenSearch.getCarDetails().getCarNumber();
         if(carDetails.equalsIgnoreCase(carNumber)){
           return tokenSearch;
         }
      }
      throw new DataNotFoundException("There is no car with carNumber:"+carNumber+"");
   }

   @Override
   public String unParkTheCar(String tokenNumber) throws Exception{
      for(Token tokenInLot:tokenForLot){
         if(tokenInLot.getTokenNumber().equals(tokenNumber)){
            tokenForLot.remove(tokenInLot);
            Slot slot = tokenInLot.getSlotDetails();
            int slotNumber = slot.getSlotNumber();
            try {
               return removeCarFromSlot(tokenInLot,slotNumber);
            }catch (Exception e){
               throw new Exception(e.getMessage());
            }
         }
         throw new DataNotFoundException("Invalid token number.");
      }
      throw new DataNotFoundException("Lot is empty.");
   }


   private String removeCarFromSlot(Token token, int slotNumber) throws Exception{
      for (Slot removeEntry:availableSlotList){
         if(removeEntry.getSlotNumber() == slotNumber){
            removeEntry.makeSlotFree();
            Token historyToken = token.updateCheckOutTime();
            historyOfParking.add(historyToken);
            return "Car entry removed";
         }

      }
      throw new DataNotFoundException("Slot not available with given slot details");
   }
   public String listAllCars(){
      for(Token tokenSearch:tokenForLot){
         return "\nToken Number: " +tokenSearch.getTokenNumber()+"\nSlot Number: " +tokenSearch.getSlotDetails().getSlotNumber()+"\nCar Color: " +tokenSearch.getCarDetails().getCarColor();
      }
      return "No any car so far";
   }
   public List<Token> historyOfParking(){
      return historyOfParking;
   }
}
