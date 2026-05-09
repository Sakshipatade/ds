import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CubeInterface extends Remote {
    double cube(double n) throws RemoteException;
}
