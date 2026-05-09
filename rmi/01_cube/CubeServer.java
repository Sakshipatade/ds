import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CubeServer extends UnicastRemoteObject implements CubeInterface {

    public CubeServer() throws RemoteException {
        super();
    }

    public double cube(double n) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Computing cube of " + n);
        return n * n * n;
    }

    public static void main(String[] args) {
        try {
            CubeServer server = new CubeServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CubeService", server);
            System.out.println("Cube Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
