import java.util.*;

public class ParkingPOC extends UserInput{
    private static String mainMenuInput;
    private static ArrayList<Object> Cars = new ArrayList<Object>();
    // private static LinkedHashSet<LinkedHashSet<String> > Cars2 = new LinkedHashSet<LinkedHashSet<String> >();
    private static UserInput UserInput = new UserInput();

    public static void main(String[] args) {
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("######## Main Menu ########");
        System.out.println("1 Entry");
        System.out.println("2 Exit");
        System.out.println("3 Search");
        System.out.println("4 list all the Cars parked");

        UserInput UserInput = new UserInput();

        mainMenuInput = UserInput.getInput();

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
        ArrayList<Object> Car = new ArrayList<Object>();



        System.out.println("Car Color: ");
        String carColor = UserInput.getInput();
        
        System.out.println("Car Number: ");
        String carNumber = UserInput.getInput();


        Car.add(carColor);
        Car.add(carNumber);
//name
        LinkedHashSet<ArrayList<Object>> listOfSlots = new LinkedHashSet(Cars);
        Integer indexOfEmpty=0;
        boolean spaceAvailable = false;
// One class for Slots holding slots details        
        for (ArrayList<Object> eachCar : listOfSlots) {
            if(eachCar.get(0).equals("Empty")){
                spaceAvailable = true;
                break;
            }
            indexOfEmpty ++;
        }

        if(spaceAvailable){
            System.out.println(indexOfEmpty);
            Cars.set(indexOfEmpty,Car);
            System.out.println("Ticket Number = "+indexOfEmpty);

        }else{
            Integer ticketNumber = Cars.size();
            Cars.add(Car);
            System.out.println("Ticket Number = "+ticketNumber);
        }
        mainMenu();
    }
    public static void makeExit(){
        ArrayList<Object> EmptySpace = new ArrayList<Object>();
        System.out.println("Ticket Number: ");
        String exitNumber = UserInput.getInput();

        EmptySpace.add("Empty");
        EmptySpace.add("Empty");
        
        Cars.set(Integer.parseInt(exitNumber),EmptySpace);
        mainMenu();

    }
    public static void makeSearch(){
        
        System.out.println("Car Number/Color: ");
        String searchVal = UserInput.getInput();
        Integer ticketCounter = 0;
        LinkedHashSet<ArrayList<Object>> lhSet = new LinkedHashSet(Cars);
        
        System.out.println("LinkedHashSet contains: " + lhSet);
        for (ArrayList<Object> arrayList : lhSet) {
            if(arrayList.get(0).equals(searchVal) || arrayList.get(1).equals(searchVal) ){
                System.out.println("=========Ticket Number: "+ticketCounter+"=========");
                System.out.println("Car Color: "+arrayList.get(0));
                System.out.println("Car Number: "+arrayList.get(1));
            }
            ticketCounter++;
        }        
        mainMenu();
    }
    public static void listTotalCars(){
        System.out.println("All cars list: "+Cars);
        mainMenu();
    }

}