import java.util.Scanner;

class UserInput {
    public static String UserInput;

    public String getInput(){
        Scanner sc = new Scanner(System.in);
        UserInput = sc.nextLine();
        return UserInput;
    }
}
