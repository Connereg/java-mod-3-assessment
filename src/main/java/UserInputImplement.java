import java.util.Scanner;



//  ERROR HANDLING TO BE ADDED IN THE IMPLEMENT METHODS

public class UserInputImplement implements UserInputInterface {
    Scanner scanner;

    //CONSTRUCTOR
    public UserInputImplement() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String receiveStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public int receiveIntInput(String prompt) {
        System.out.println(prompt);
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;

    }
}
