import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server and calls square() remotely.
public class SquareClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        SquareInterface stub = (SquareInterface) registry.lookup("SquareService");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        double n = sc.nextDouble();

        double result = stub.square(n);
        System.out.println("Square of " + n + " = " + result);

        sc.close();
    }
}
