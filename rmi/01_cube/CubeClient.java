import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CubeClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            CubeInterface stub = (CubeInterface) registry.lookup("CubeService");

            Scanner sc = new Scanner(System.in);
            double[] nums = new double[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter number " + (i + 1) + ": ");
                nums[i] = sc.nextDouble();
            }

            Thread[] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                final double n = nums[i];
                threads[i] = new Thread(() -> {
                    try {
                        double result = stub.cube(n);
                        System.out.println("Cube of " + n + " = " + result);
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
