import CalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

// The actual implementation of the Calculator interface.
class CalculatorImpl extends CalculatorPOA {
    public double add(double a, double b) { return a + b; }
    public double sub(double a, double b) { return a - b; }
    public double mul(double a, double b) { return a * b; }
    public double div(double a, double b) {
        if (b == 0) return 0;
        return a / b;
    }
}

public class CalculatorServer {
    public static void main(String[] args) throws Exception {
        // 1. Start the ORB (Object Request Broker).
        ORB orb = ORB.init(args, null);

        // 2. Activate the root POA (Portable Object Adapter).
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        // 3. Create the implementation object.
        CalculatorImpl impl = new CalculatorImpl();
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
        Calculator href = CalculatorHelper.narrow(ref);

        // 4. Register the object with the naming service under the name "Calculator".
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        NameComponent[] path = ncRef.to_name("Calculator");
        ncRef.rebind(path, href);

        System.out.println("Calculator Server is ready...");

        // 5. Wait for client requests.
        orb.run();
    }
}
