import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface DivInterface extends Remote {
    double div(double a, double b) throws RemoteException;
}
