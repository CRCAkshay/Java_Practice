import com.parkinglot.classes.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void testTheCarObject(){
        Car car = new Car();
        car.setCarNumber("123");
        car.setCarColor("Blue");
        assertEquals(car.getCarColor(),"Blue");
        assertEquals(car.getCarNumber(),"123");
    }
}