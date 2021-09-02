import com.parkinglot.classes.Token;
import  com.parkinglot.service.ParkingLot;
import  com.parkinglot.classes.Car;

import java.util.Scanner;

public class MainMenu {
    private static String mainMenuInput;
    static Scanner userInput = new Scanner(System.in);
    static ParkingLot parkingLot;
    public static void main(String[] args) {
        System.out.println("Enter maximum number of Slots: ");
        String maxInput = userInput.nextLine();
        parkingLot = new ParkingLot(Integer.parseInt(maxInput));

        mainMenu();
    }

    public static void mainMenu(){


        System.out.println("######## Main Menu ########");
        System.out.println("1 Entry");
        System.out.println("2 Exit");
        System.out.println("3 Search");
        System.out.println("4 list all the Cars parked");


        mainMenuInput = userInput.nextLine();

        switch (mainMenuInput) {
            case "1":
                makeEntry();
                break;
            case "2":
                makeExit();
                break;
            case "3":
                makeSearch();
                break;
            case "4":
                listTotalCars();
            default:
        }
    }
    public static void makeEntry(){
        System.out.println("Car Color: ");
        String carColor = userInput.nextLine();

        System.out.println("Car Number: ");
        String carNumber =  userInput.nextLine();

        Car car = new Car();
        car.setCarColor(carColor);
        car.setCarNumber(carNumber);
        try {
            Token token = parkingLot.parkTheCar(car);
            System.out.println(token.getTokenNumber());
        }catch (Exception e){
            System.out.println("No more Place In Parking!!");
        }

        mainMenu();
    }
    public static void makeExit(){
        System.out.println("Enter Token Number: ");
        String tokenNumber = userInput.nextLine();

        try {
            String Message = parkingLot.unParkTheCar(tokenNumber);
            System.out.println(Message);
        }catch (Exception e){
            System.out.println("No parking with this Token");
        }

        mainMenu();
    }
    public static void makeSearch(){
        System.out.println("Enter Token Number: ");
        String carNumber = userInput.nextLine();

        try {
            System.out.println(parkingLot.searchCarNumber(carNumber));
        }catch (Exception e){
            System.out.println("No parking with this Token");
        }

        mainMenu();
    }
    public static void listTotalCars(){
        try {
            System.out.println(parkingLot.listAllCars());
        }catch (Exception e){
            System.out.println("No parking with this Token");
        }
        mainMenu();
    }
}
