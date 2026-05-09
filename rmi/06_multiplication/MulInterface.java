import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MulInterface extends Remote {
    double mul(double a, double b) throws RemoteException;
}
