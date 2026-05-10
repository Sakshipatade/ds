import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method square().
public class SquareServer extends UnicastRemoteObject implements SquareInterface {

    public SquareServer() throws RemoteException {
        super();
    }

    public double square(double n) throws RemoteException {
        System.out.println("Server: computing square of " + n);
        return n * n;
    }

    public static void main(String[] args) throws Exception {
        SquareServer server = new SquareServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("SquareService", server);
        System.out.println("Square Server is ready...");
    }
}
