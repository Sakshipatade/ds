import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SubServer extends UnicastRemoteObject implements SubInterface {

    public SubServer() throws RemoteException {
        super();
    }

    public double sub(double a, double b) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Subtracting " + a + " - " + b);
        return a - b;
    }

    public static void main(String[] args) {
        try {
            SubServer server = new SubServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SubService", server);
            System.out.println("Subtraction Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
