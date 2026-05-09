import PrimeApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class PrimeImpl extends PrimePOA {
    public String check(int n) {
        if (n < 2) return n + " is NOT PRIME";
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return n + " is NOT PRIME";
        }
        return n + " is PRIME";
    }
}

public class PrimeServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            PrimeImpl impl = new PrimeImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
            Prime href = PrimeHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("Prime");
            ncRef.rebind(path, href);

            System.out.println("Prime Server is ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
