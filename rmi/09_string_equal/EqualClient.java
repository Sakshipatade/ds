import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class EqualClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            EqualInterface stub = (EqualInterface) registry.lookup("EqualService");

            Scanner sc = new Scanner(System.in);
            String[][] pairs = new String[3][2];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter pair " + (i + 1) + " - first string: ");
                pairs[i][0] = sc.nextLine();
                System.out.print("Enter pair " + (i + 1) + " - second string: ");
                pairs[i][1] = sc.nextLine();
            }

            Thread[] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                final String a = pairs[i][0], b = pairs[i][1];
                threads[i] = new Thread(() -> {
                    try {
                        boolean result = stub.isEqual(a, b);
                        System.out.println("\"" + a + "\" and \"" + b + "\" are " + (result ? "EQUAL" : "NOT EQUAL"));
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
