import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// Client that calls the /add endpoint of the web service.
public class AddClient {

    public static void main(String[] args) throws Exception {
        // 1. Read two numbers from the user.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first  number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        // 2. Build the URL and send a GET request.
        URL url = new URL("http://localhost:8080/add?a=" + a + "&b=" + b);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // 3. Read the response and print it line by line.
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        System.out.println("\n--- Response from web service ---");
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }
}
