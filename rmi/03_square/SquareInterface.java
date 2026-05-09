import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SquareInterface extends Remote {
    double square(double n) throws RemoteException;
}
