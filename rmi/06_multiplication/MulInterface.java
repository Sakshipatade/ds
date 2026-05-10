import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface MulInterface extends Remote {
    double mul(double a, double b) throws RemoteException;
}
