import java.io.*;
import java.net.*;
import java.util.*;

public class BerkeleyServer {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter master's current clock time (in minutes, e.g. 600 for 10:00): ");
        int masterTime = sc.nextInt();

        System.out.print("How many slave clients will connect? ");
        int n = sc.nextInt();

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("\nMaster Server started on port 5000");
        System.out.println("Waiting for " + n + " slaves to connect...\n");

        Socket[] sockets = new Socket[n];
        int[] slaveTimes = new int[n];

        for (int i = 0; i < n; i++) {
            sockets[i] = ss.accept();
            DataInputStream in = new DataInputStream(sockets[i].getInputStream());
            slaveTimes[i] = in.readInt();
            System.out.println("Slave " + (i + 1) + " connected. Reported time = " + slaveTimes[i]);
        }

        long sum = masterTime;
        for (int t : slaveTimes) sum += t;
        int avg = (int) (sum / (n + 1));

        System.out.println("\n--- Berkeley Algorithm ---");
        System.out.println("Master time     : " + masterTime);
        for (int i = 0; i < n; i++)
            System.out.println("Slave " + (i + 1) + " time     : " + slaveTimes[i]);
        System.out.println("Average (sync)  : " + avg);

        int masterOffset = avg - masterTime;
        System.out.println("\nMaster's new time = " + masterTime + " + (" + masterOffset + ") = " + avg);

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
