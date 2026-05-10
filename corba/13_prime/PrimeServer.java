import PrimeApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

// The actual implementation of the Prime interface.
class PrimeImpl extends PrimePOA {
    public String check(int n) {
        // 0, 1, and negatives are not prime.
        if (n < 2) {
            return n + " is NOT PRIME";
        }
        // Try to divide by every number from 2 up to sqrt(n).
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return n + " is NOT PRIME";
            }
        }
        return n + " is PRIME";
    }
}

public class PrimeServer {
    public static void main(String[] args) throws Exception {
        // 1. Start the ORB and activate the POA.
        ORB orb = ORB.init(args, null);
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        // 2. Create the implementation object.
        PrimeImpl impl = new PrimeImpl();
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
        Prime href = PrimeHelper.narrow(ref);

        // 3. Register the object with the naming service.
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        NameComponent[] path = ncRef.to_name("Prime");
        ncRef.rebind(path, href);

        System.out.println("Prime Server is ready...");

        // 4. Wait for client requests.
        orb.run();
    }
}
