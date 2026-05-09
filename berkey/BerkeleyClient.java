import java.io.*;
import java.net.*;
import java.util.*;

public class BerkeleyClient {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter this slave's current clock time (in minutes): ");
        int myTime = sc.nextInt();

        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to master at localhost:5000");

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeInt(myTime);
        System.out.println("Sent my time = " + myTime + " to master");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        int offset = in.readInt();
        int newTime = myTime + offset;

        System.out.println("\nReceived offset from master = " + offset);
        System.out.println("Old time : " + myTime);
        System.out.println("New time : " + myTime + " + (" + offset + ") = " + newTime);

        socket.close();
        sc.close();
    }
}
