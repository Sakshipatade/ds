import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface SquareInterface extends Remote {
    double square(double n) throws RemoteException;
}
