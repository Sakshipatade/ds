import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface PalinInterface extends Remote {
    boolean isPalindrome(String s) throws RemoteException;
}
