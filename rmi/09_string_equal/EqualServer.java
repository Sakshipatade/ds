import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

// Server: implements the remote method isEqual().
public class EqualServer extends UnicastRemoteObject implements EqualInterface {

    public EqualServer() throws RemoteException {
        super();
    }

    public boolean isEqual(String a, String b) throws RemoteException {
        System.out.println("Server: comparing \"" + a + "\" and \"" + b + "\"");
        return a.equals(b);
    }

    public static void main(String[] args) throws Exception {
        EqualServer server = new EqualServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("EqualService", server);
        System.out.println("String Equal Server is ready...");
    }
}
