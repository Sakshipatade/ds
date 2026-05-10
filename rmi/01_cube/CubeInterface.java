import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface: lists methods that the server offers to clients.
public interface CubeInterface extends Remote {
    double cube(double n) throws RemoteException;
}
