import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DivClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first  number: ");  double a = sc.nextDouble();
        System.out.print("Enter second number: ");  double b = sc.nextDouble();

        URL url = new URL("http://localhost:8080/div?a=" + a + "&b=" + b);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int code = con.getResponseCode();
        InputStream stream = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        System.out.println("\n--- Response from web service (HTTP " + code + ") ---");
        while ((line = in.readLine()) != null) System.out.println(line);
        in.close();
    }
}
