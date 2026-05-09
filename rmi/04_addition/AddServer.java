import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AddServer extends UnicastRemoteObject implements AddInterface {

    public AddServer() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Adding " + a + " + " + b);
        return a + b;
    }

    public static void main(String[] args) {
        try {
            AddServer server = new AddServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AddService", server);
            System.out.println("Add Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
