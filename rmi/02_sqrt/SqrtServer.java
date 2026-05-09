import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SqrtServer extends UnicastRemoteObject implements SqrtInterface {

    public SqrtServer() throws RemoteException {
        super();
    }

    public double sqrt(double n) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Computing sqrt of " + n);
        return Math.sqrt(n);
    }

    public static void main(String[] args) {
        try {
            SqrtServer server = new SqrtServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SqrtService", server);
            System.out.println("Sqrt Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
