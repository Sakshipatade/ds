import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method cube().
public class CubeServer extends UnicastRemoteObject implements CubeInterface {

    public CubeServer() throws RemoteException {
        super();
    }

    // This method runs on the server when a client calls it.
    public double cube(double n) throws RemoteException {
        System.out.println("Server: computing cube of " + n);
        return n * n * n;
    }

    public static void main(String[] args) throws Exception {
        // 1. Create the server object.
        CubeServer server = new CubeServer();

        // 2. Start the RMI registry on port 1099.
        Registry registry = LocateRegistry.createRegistry(1099);

        // 3. Register the server with a name so clients can find it.
        registry.rebind("CubeService", server);

        System.out.println("Cube Server is ready...");
    }
}
