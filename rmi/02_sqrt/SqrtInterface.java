import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SqrtInterface extends Remote {
    double sqrt(double n) throws RemoteException;
}
