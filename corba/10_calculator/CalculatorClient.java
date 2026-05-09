import CalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Calculator calc = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first number: ");
            double a = sc.nextDouble();
            System.out.print("Enter second number: ");
            double b = sc.nextDouble();

            System.out.println("Addition       : " + calc.add(a, b));
            System.out.println("Subtraction    : " + calc.sub(a, b));
            System.out.println("Multiplication : " + calc.mul(a, b));
            System.out.println("Division       : " + calc.div(a, b));

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
