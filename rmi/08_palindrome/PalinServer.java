import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method isPalindrome().
public class PalinServer extends UnicastRemoteObject implements PalinInterface {

    public PalinServer() throws RemoteException {
        super();
    }

    public boolean isPalindrome(String s) throws RemoteException {
        System.out.println("Server: checking palindrome for \"" + s + "\"");
        // Reverse the string and compare with the original (ignore case).
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equalsIgnoreCase(reversed);
    }

    public static void main(String[] args) throws Exception {
        PalinServer server = new PalinServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("PalinService", server);
        System.out.println("Palindrome Server is ready...");
    }
}
