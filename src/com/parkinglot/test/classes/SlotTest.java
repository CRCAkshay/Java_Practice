package com.parkinglot.test.classes;
import com.parkinglot.classes.Slot;
import org.junit.Test;
import static org.junit.Assert.*;
public class SlotTest {
    @Test
    public void testTheSlotObject(){
    Slot slot = new Slot(1);
    int slotNumber=slot.getSlotNumber();
    assertEquals(slotNumber,1);
    }
}

