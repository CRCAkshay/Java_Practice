import com.parkinglot.classes.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void testTheCarObject(){
        Car car = new Car("Blue","TS01GG1234");
        assertEquals(car.getCarColor(),"Blue");
        assertEquals(car.getCarNumber(),"TS01GG1234");
    }
}