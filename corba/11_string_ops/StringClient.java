import StringApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class StringClient {
    public static void main(String[] args) throws Exception {
        // 1. Start the ORB.
        ORB orb = ORB.init(args, null);

        // 2. Look up the "StringOps" object in the naming service.
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        StringOps ops = StringOpsHelper.narrow(ncRef.resolve_str("StringOps"));

        // 3. Read two strings from the user.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String s1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String s2 = sc.nextLine();

        // 4. Call the remote methods and print results.
        System.out.println("Length of s1   : " + ops.length(s1));
        System.out.println("Concatenation  : " + ops.concat(s1, s2));
        System.out.println("Reverse of s1  : " + ops.reverse(s1));
        System.out.println("Uppercase of s1: " + ops.toUpper(s1));

        sc.close();
    }
}
