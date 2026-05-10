import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server and calls mul() remotely.
public class MulClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        MulInterface stub = (MulInterface) registry.lookup("MulService");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        double result = stub.mul(a, b);
        System.out.println(a + " * " + b + " = " + result);

        sc.close();
    }
}
