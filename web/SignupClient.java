import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

// Client that calls the /signup endpoint of the web service.
public class SignupClient {

    public static void main(String[] args) throws Exception {
        // 1. Read sign-up details from the user.
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Sign-up Form ---");
        System.out.print("Name    : ");
        String name = sc.nextLine();
        System.out.print("Email   : ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        // 2. Build the URL-encoded body.
        String body = "name=" + URLEncoder.encode(name, "UTF-8")
                    + "&email=" + URLEncoder.encode(email, "UTF-8")
                    + "&password=" + URLEncoder.encode(pass, "UTF-8");

        // 3. Send a POST request with the body.
        URL url = new URL("http://localhost:8080/signup");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream os = con.getOutputStream();
        os.write(body.getBytes("UTF-8"));
        os.close();

        // 4. Read the response (success or error).
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
