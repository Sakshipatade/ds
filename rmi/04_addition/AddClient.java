import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server and calls add() remotely.
public class AddClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        AddInterface stub = (AddInterface) registry.lookup("AddService");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        double result = stub.add(a, b);
        System.out.println(a + " + " + b + " = " + result);

        sc.close();
    }
}
