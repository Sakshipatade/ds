import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SubClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            SubInterface stub = (SubInterface) registry.lookup("SubService");

            Scanner sc = new Scanner(System.in);
            double[][] pairs = new double[3][2];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter pair " + (i + 1) + " (a b): ");
                pairs[i][0] = sc.nextDouble();
                pairs[i][1] = sc.nextDouble();
            }

            Thread[] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                final double a = pairs[i][0], b = pairs[i][1];
                threads[i] = new Thread(() -> {
                    try {
                        double result = stub.sub(a, b);
                        System.out.println(a + " - " + b + " = " + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                threads[i].start();
            }
            for (Thread t : threads) t.join();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
