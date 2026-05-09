import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EqualInterface extends Remote {
    boolean isEqual(String a, String b) throws RemoteException;
}
