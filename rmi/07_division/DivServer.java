import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class DivServer extends UnicastRemoteObject implements DivInterface {

    public DivServer() throws RemoteException {
        super();
    }

    public double div(double a, double b) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Dividing " + a + " / " + b);
        if (b == 0) throw new RemoteException("Cannot divide by zero");
        return a / b;
    }

    public static void main(String[] args) {
        try {
            DivServer server = new DivServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("DivService", server);
            System.out.println("Division Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
