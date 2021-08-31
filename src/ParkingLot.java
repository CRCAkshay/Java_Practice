import java.util.*;

public class ParkingLot {
    private int totalNumberOfSlots;
    private Slot slotsInParkingLot;
    private List<Token> tokenForLot;
    private List<Token> historyOfParking;


    public ParkingLot(int totalNumberOfSlots){
        this.totalNumberOfSlots = totalNumberOfSlots;
        Slot abc = new Slot();
        this.slotsInParkingLot = abc.initializeSlot(10);
        this.tokenForLot = new ArrayList<>();
        this.historyOfParking = new ArrayList<>();

    }
    
    public Token parkTheCar(Car Car){
        if(isSlotAvailble()){
            Slot availbleSlot = getTheNextFreeSlot();
            Token parkingToken = new Token(String.valueOf(System.currentTimeMillis()),availbleSlot,car);
            this.tokenForLot.add(parkingToken);
            return parkingToken;
        }
        return null;
    }

    public void unParkTheCar(Token token){
        for(Token tokenInLot:tokenForLot){
            if(tokenInLot.getTokenNumber() == token.getTokenNumber()){
                historyOfParking.add(token.updateCheckOutTime());
                tokenForLot.remove(tokenInLot);
            }
        }
    }
    
    private Slot getTheNextFreeSlot() {
        for(Slot slot : slotsInParkingLot){
            if(slot.isSlotFree()){
                return slot;
            }
        }
        return null;
    }

    private boolean isSlotAvailble() {
        boolean isSlotAvailble = false;
        for(Slot slot : slotsInParkingLot){
            if(slot.isSlotFree()){
                isSlotAvailble = true;
                break;
            }
        }
        return isSlotAvailble;
    }
}
