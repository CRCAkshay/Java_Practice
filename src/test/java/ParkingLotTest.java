import com.parkinglot.classes.Car;
import com.parkinglot.classes.Token;
import com.parkinglot.services.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotTest {
    @Test
    public void testToParkTheCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car("Blue","ABC");
        Token token = numberOfSlots.parkTheCar(car);
        assertFalse(token.getTokenNumber().isBlank());

    }

    @Test
    public void testToUnParkTheCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car("Blue","ABC");

        Token Token = numberOfSlots.parkTheCar(car);

        String unParkMessage = numberOfSlots.unParkTheCar(Token.getTokenNumber());
        assertEquals(unParkMessage,"Car entry removed");
    }

    @Test
    public void testUnParkInvalidCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        String unParkMessage = numberOfSlots.unParkTheCar("1233123");
        assertNull(unParkMessage);

        Car car = new Car("Blue","ABC");

        Token Token = numberOfSlots.parkTheCar(car);

        String newUnParkMessage = numberOfSlots.unParkTheCar(Token.getTokenNumber()+"12");
        assertEquals(newUnParkMessage,"No token found");
    }

    @Test
    public void testToParkThreeCarWhenWeHaveTwoSlots(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car1 = new Car("Blue","ABC");
        numberOfSlots.parkTheCar(car1);

        Car car2 = new Car("Red","ABC2");
        numberOfSlots.parkTheCar(car2);

        Car car3 = new Car("Yellow","ABC3");
        Token Token3 = numberOfSlots.parkTheCar(car3);

        //Change it to Exception
        assertNull(Token3);

    }

    @Test
    public void testToSearchATokenPositive(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car("Blue","ABC");
        numberOfSlots.parkTheCar(car);
        String searchToken = numberOfSlots.searchCarNumber("123");
        assertNotNull(searchToken);
    }

    @Test
    public void testToSearchATokenNegative(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car("Blue","ABC");
        numberOfSlots.parkTheCar(car);
        String searchToken = numberOfSlots.searchCarNumber("WrongVal");
        assertEquals(searchToken,"There is no any car");
    }

    @Test
    public void testTolistAllCar(){
        ParkingLot numberOfSlots = new ParkingLot(2);
        Car car = new Car("Blue","ABC");
        numberOfSlots.parkTheCar(car);

        String searchToken = numberOfSlots.listAllCars();
        assertNotNull(searchToken);
    }

    @Test
    public void testNoList(){
        ParkingLot numberOfSlots = new ParkingLot(0);
        String searchCar = numberOfSlots.listAllCars();
        assertEquals(searchCar,"No any car so far");
    }
}
