import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

// Slave client for the Berkeley clock synchronization algorithm.
// 1. Sends its current clock time to the master.
// 2. Receives an offset from the master.
// 3. Adjusts its clock by adding the offset.
public class BerkeleyClient {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter this slave's current clock time (in minutes): ");
        int myTime = sc.nextInt();

        // Connect to the master server.
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to master at localhost:5000");

        // Send my time to the master.
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeInt(myTime);
        System.out.println("Sent my time = " + myTime + " to master");

        // Receive the offset from the master.
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
