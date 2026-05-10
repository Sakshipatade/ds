import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface SqrtInterface extends Remote {
    double sqrt(double n) throws RemoteException;
}
