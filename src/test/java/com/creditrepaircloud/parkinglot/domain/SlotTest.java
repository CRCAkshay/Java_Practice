package com.creditrepaircloud.parkinglot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotTest {

    @Test
    void testIsSlotFree() {
        Slot slot = new Slot(1);
        assertTrue(slot.isSlotFree());
    }

    @Test
    void testMakeSlotFree() {
        Slot slot = new Slot(1);
        slot.makeSlotOccupied();
        slot.makeSlotFree();
        assertTrue(slot.isSlotFree());
    }

    @Test
    void getSlotNumber() {
        Slot slot = new Slot(1);
        assertEquals(1,slot.getSlotNumber());
    }

    @Test
    void makeSlotOccupied() {
        Slot slot = new Slot(1);
        slot.makeSlotOccupied();
        assertFalse(slot.isSlotFree());
    }
}