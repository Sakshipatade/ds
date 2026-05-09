import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PalinInterface extends Remote {
    boolean isPalindrome(String s) throws RemoteException;
}
