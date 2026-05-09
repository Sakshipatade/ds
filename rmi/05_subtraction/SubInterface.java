import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubInterface extends Remote {
    double sub(double a, double b) throws RemoteException;
}
