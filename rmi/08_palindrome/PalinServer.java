import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class PalinServer extends UnicastRemoteObject implements PalinInterface {

    public PalinServer() throws RemoteException {
        super();
    }

    public boolean isPalindrome(String s) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Checking palindrome: " + s);
        String rev = new StringBuilder(s).reverse().toString();
        return s.equalsIgnoreCase(rev);
    }

    public static void main(String[] args) {
        try {
            PalinServer server = new PalinServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PalinService", server);
            System.out.println("Palindrome Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
