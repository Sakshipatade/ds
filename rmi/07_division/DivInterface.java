import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DivInterface extends Remote {
    double div(double a, double b) throws RemoteException;
}
