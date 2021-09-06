import com.parkinglot.classes.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SlotTest {
    @Test
    public void testTheSlotObject(){
        Slot slot = new Slot(1);
        int slotNumber=slot.getSlotNumber();
        assertEquals(slotNumber,1);
        assertTrue(slot.isSlotFree());
    }
}