import OddEvenApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class OddEvenClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            OddEven oe = OddEvenHelper.narrow(ncRef.resolve_str("OddEven"));

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int n = sc.nextInt();

            System.out.println(oe.check(n));

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
