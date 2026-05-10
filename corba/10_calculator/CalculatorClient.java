import CalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) throws Exception {
        // 1. Start the ORB.
        ORB orb = ORB.init(args, null);

        // 2. Get the naming service and look up the "Calculator" object.
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        Calculator calc = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));

        // 3. Read two numbers from the user.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        // 4. Call the remote methods and print results.
        System.out.println("Addition       : " + calc.add(a, b));
        System.out.println("Subtraction    : " + calc.sub(a, b));
        System.out.println("Multiplication : " + calc.mul(a, b));
        System.out.println("Division       : " + calc.div(a, b));

        sc.close();
    }
}
