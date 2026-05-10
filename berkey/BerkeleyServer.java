import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// Master server for the Berkeley clock synchronization algorithm.
// 1. Reads its own time and waits for N slaves.
// 2. Receives each slave's time.
// 3. Computes the average of all clocks.
// 4. Sends each slave the offset it must apply to reach the average.
public class BerkeleyServer {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter master's current clock time (in minutes, e.g. 600 for 10:00): ");
        int masterTime = sc.nextInt();

        System.out.print("How many slave clients will connect? ");
        int n = sc.nextInt();

        // Open a server socket on port 5000.
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("\nMaster Server started on port 5000");
        System.out.println("Waiting for " + n + " slaves to connect...\n");

        Socket[] sockets = new Socket[n];
        int[] slaveTimes = new int[n];

        // Step 1: accept N slaves and read their clock times.
        for (int i = 0; i < n; i++) {
            sockets[i] = ss.accept();
            DataInputStream in = new DataInputStream(sockets[i].getInputStream());
            slaveTimes[i] = in.readInt();
            System.out.println("Slave " + (i + 1) + " connected. Reported time = " + slaveTimes[i]);
        }

        // Step 2: compute the average of all clocks (master + slaves).
        int sum = masterTime;
        for (int i = 0; i < n; i++) {
            sum = sum + slaveTimes[i];
        }
        int avg = sum / (n + 1);

        System.out.println("\n--- Berkeley Algorithm ---");
        System.out.println("Master time     : " + masterTime);
        for (int i = 0; i < n; i++) {
            System.out.println("Slave " + (i + 1) + " time     : " + slaveTimes[i]);
        }
        System.out.println("Average (sync)  : " + avg);

        // Step 3: master adjusts its own time.
        int masterOffset = avg - masterTime;
        System.out.println("\nMaster's new time = " + masterTime + " + (" + masterOffset + ") = " + avg);

        // Step 4: send each slave its offset and close the connection.
        for (int i = 0; i < n; i++) {
            int offset = avg - slaveTimes[i];
            DataOutputStream out = new DataOutputStream(sockets[i].getOutputStream());
            out.writeInt(offset);
            System.out.println("Sent offset to Slave " + (i + 1) + " = " + offset);
            sockets[i].close();
        }

        ss.close();
        sc.close();
        System.out.println("\nClock synchronization complete.");
    }
}
