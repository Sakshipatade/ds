import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface SubInterface extends Remote {
    double sub(double a, double b) throws RemoteException;
}
