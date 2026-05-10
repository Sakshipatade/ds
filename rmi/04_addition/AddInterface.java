import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface AddInterface extends Remote {
    double add(double a, double b) throws RemoteException;
}
