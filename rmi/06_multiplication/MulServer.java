import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class MulServer extends UnicastRemoteObject implements MulInterface {

    public MulServer() throws RemoteException {
        super();
    }

    public double mul(double a, double b) throws RemoteException {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Multiplying " + a + " * " + b);
        return a * b;
    }

    public static void main(String[] args) {
        try {
            MulServer server = new MulServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MulService", server);
            System.out.println("Multiplication Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
