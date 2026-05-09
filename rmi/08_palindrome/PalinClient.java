import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PalinClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PalinInterface stub = (PalinInterface) registry.lookup("PalinService");

            Scanner sc = new Scanner(System.in);
            String[] strs = new String[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter string " + (i + 1) + ": ");
                strs[i] = sc.nextLine();
            }

            Thread[] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                final String s = strs[i];
                threads[i] = new Thread(() -> {
                    try {
                        boolean result = stub.isPalindrome(s);
                        System.out.println("\"" + s + "\" is " + (result ? "" : "NOT ") + "a palindrome");
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
