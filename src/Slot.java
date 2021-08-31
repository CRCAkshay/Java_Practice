import java.util.ArrayList;

public class Slot {
    private boolean isParked;
    private String slotNumber;
    public ArrayList<Integer> availableSlotList;

    public boolean isSlotFree() {
        return isParked != true;
    }

    public Slot makeSlotFree(){
        isParked = false;
        return this;
    }

    public Slot initializeSlot(int totalNumberOfSlot){
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= totalNumberOfSlot; i++) {
            this.availableSlotList.add(i);
        }
        return this;
    }

    public String getSlotNumber() {
        return slotNumber;
    }
}
