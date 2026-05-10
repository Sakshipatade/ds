import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method add().
public class AddServer extends UnicastRemoteObject implements AddInterface {

    public AddServer() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        System.out.println("Server: adding " + a + " + " + b);
        return a + b;
    }

    public static void main(String[] args) throws Exception {
        AddServer server = new AddServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("AddService", server);
        System.out.println("Add Server is ready...");
    }
}
