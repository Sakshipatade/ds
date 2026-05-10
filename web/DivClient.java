import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// Client that calls the /div endpoint of the web service.
public class DivClient {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first  number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        URL url = new URL("http://localhost:8080/div?a=" + a + "&b=" + b);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // If the server returned an error, read from the error stream instead.
        int code = con.getResponseCode();
        InputStream stream;
        if (code >= 200 && code < 300) {
            stream = con.getInputStream();
        } else {
            stream = con.getErrorStream();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        System.out.println("\n--- Response from web service (HTTP " + code + ") ---");
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }
}
