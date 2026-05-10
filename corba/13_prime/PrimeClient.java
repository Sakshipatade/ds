import PrimeApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class PrimeClient {
    public static void main(String[] args) throws Exception {
        // 1. Start the ORB.
        ORB orb = ORB.init(args, null);

        // 2. Look up the "Prime" object in the naming service.
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        Prime p = PrimeHelper.narrow(ncRef.resolve_str("Prime"));

        // 3. Read a number from the user.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        // 4. Call the remote method and print the result.
        System.out.println(p.check(n));

        sc.close();
    }
}
