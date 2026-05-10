import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server and calls isEqual() remotely.
public class EqualClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        EqualInterface stub = (EqualInterface) registry.lookup("EqualService");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String a = sc.nextLine();
        System.out.print("Enter second string: ");
        String b = sc.nextLine();

        boolean result = stub.isEqual(a, b);
        if (result) {
            System.out.println("\"" + a + "\" and \"" + b + "\" are EQUAL");
        } else {
            System.out.println("\"" + a + "\" and \"" + b + "\" are NOT EQUAL");
        }

        sc.close();
    }
}
