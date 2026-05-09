import StringApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class StringOpsImpl extends StringOpsPOA {
    public int length(String s) { return s.length(); }
    public String concat(String a, String b) { return a + b; }
    public String reverse(String s) { return new StringBuilder(s).reverse().toString(); }
    public String toUpper(String s) { return s.toUpperCase(); }
}

public class StringServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            StringOpsImpl impl = new StringOpsImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
            StringOps href = StringOpsHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("StringOps");
            ncRef.rebind(path, href);

            System.out.println("String Operations Server is ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
