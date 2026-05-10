import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server and calls isPalindrome() remotely.
public class PalinClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        PalinInterface stub = (PalinInterface) registry.lookup("PalinService");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        boolean result = stub.isPalindrome(s);
        if (result) {
            System.out.println("\"" + s + "\" is a palindrome");
        } else {
            System.out.println("\"" + s + "\" is NOT a palindrome");
        }

        sc.close();
    }
}
