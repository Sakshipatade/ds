import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method sub().
public class SubServer extends UnicastRemoteObject implements SubInterface {

    public SubServer() throws RemoteException {
        super();
    }

    public double sub(double a, double b) throws RemoteException {
        System.out.println("Server: subtracting " + a + " - " + b);
        return a - b;
    }

    public static void main(String[] args) throws Exception {
        SubServer server = new SubServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("SubService", server);
        System.out.println("Subtraction Server is ready...");
    }
}
