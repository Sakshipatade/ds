import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method sqrt().
public class SqrtServer extends UnicastRemoteObject implements SqrtInterface {

    public SqrtServer() throws RemoteException {
        super();
    }

    // This method runs on the server when a client calls it.
    public double sqrt(double n) throws RemoteException {
        System.out.println("Server: computing square root of " + n);
        return Math.sqrt(n);
    }

    public static void main(String[] args) throws Exception {
        SqrtServer server = new SqrtServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("SqrtService", server);
        System.out.println("Sqrt Server is ready...");
    }
}
