import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SignupClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Sign-up Form ---");
        System.out.print("Name    : "); String name  = sc.nextLine();
        System.out.print("Email   : "); String email = sc.nextLine();
        System.out.print("Password: "); String pass  = sc.nextLine();

        String body =
              "name="     + URLEncoder.encode(name,  StandardCharsets.UTF_8)
            + "&email="   + URLEncoder.encode(email, StandardCharsets.UTF_8)
            + "&password="+ URLEncoder.encode(pass,  StandardCharsets.UTF_8);

        URL url = new URL("http://localhost:8080/signup");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = con.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        int code = con.getResponseCode();
        InputStream stream = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        System.out.println("\n--- Response from web service (HTTP " + code + ") ---");
        while ((line = in.readLine()) != null) System.out.println(line);
        in.close();
    }
}
