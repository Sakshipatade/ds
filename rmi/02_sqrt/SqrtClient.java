import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server and calls sqrt() remotely.
public class SqrtClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        SqrtInterface stub = (SqrtInterface) registry.lookup("SqrtService");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        double n = sc.nextDouble();

        double result = stub.sqrt(n);
        System.out.println("Square root of " + n + " = " + result);

        sc.close();
    }
}
