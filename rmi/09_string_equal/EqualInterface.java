import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface EqualInterface extends Remote {
    boolean isEqual(String a, String b) throws RemoteException;
}
