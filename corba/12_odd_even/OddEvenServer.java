import OddEvenApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

// The actual implementation of the OddEven interface.
class OddEvenImpl extends OddEvenPOA {
    public String check(int n) {
        if (n % 2 == 0) {
            return n + " is EVEN";
        } else {
            return n + " is ODD";
        }
    }
}

public class OddEvenServer {
    public static void main(String[] args) throws Exception {
        // 1. Start the ORB and activate the POA.
        ORB orb = ORB.init(args, null);
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        // 2. Create the implementation object.
        OddEvenImpl impl = new OddEvenImpl();
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
        OddEven href = OddEvenHelper.narrow(ref);

        // 3. Register the object with the naming service.
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        NameComponent[] path = ncRef.to_name("OddEven");
        ncRef.rebind(path, href);

        System.out.println("OddEven Server is ready...");

        // 4. Wait for client requests.
        orb.run();
    }
}
