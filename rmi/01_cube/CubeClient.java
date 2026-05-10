import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// Client: looks up the server in the registry and calls cube() remotely.
public class CubeClient {

    public static void main(String[] args) throws Exception {
        // 1. Connect to the RMI registry on the server.
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);

        // 2. Look up the remote object by its name.
        CubeInterface stub = (CubeInterface) registry.lookup("CubeService");

        // 3. Read a number from the user.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        double n = sc.nextDouble();

        // 4. Call the remote method and print the result.
        double result = stub.cube(n);
        System.out.println("Cube of " + n + " = " + result);

        sc.close();
    }
}
