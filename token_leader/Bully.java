import java.util.*;

public class Bully {

    static int n;
    static boolean[] alive;
    static int coordinator = -1;

    static void election(int initiator) {
        System.out.println("\nP" + initiator + " starts an ELECTION");

        boolean higherResponded = false;
        for (int i = initiator + 1; i < n; i++) {
            if (alive[i]) {
                System.out.println("  P" + initiator + " --ELECTION--> P" + i);
                System.out.println("  P" + i + " --OK--> P" + initiator + " (will take over)");
                higherResponded = true;
            }
        }

        if (!higherResponded) {
            coordinator = initiator;
            System.out.println("\nP" + initiator + " has no higher process responding.");
            System.out.println("P" + initiator + " declares ITSELF as the new COORDINATOR.");
            for (int i = 0; i < n; i++) {
                if (i != initiator && alive[i])
                    System.out.println("  P" + initiator + " --COORDINATOR--> P" + i);
            }
            return;
        }

        int next = -1;
        for (int i = initiator + 1; i < n; i++) {
            if (alive[i]) { next = i; break; }
        }
        if (next != -1) election(next);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        alive = new boolean[n];
        Arrays.fill(alive, true);

        System.out.print("Enter how many processes are crashed, then their IDs: ");
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) alive[sc.nextInt()] = false;

        System.out.print("Enter ID of process that detects coordinator failure: ");
        int detector = sc.nextInt();

        System.out.println("\n--- Bully Algorithm ---");
        System.out.print("Alive processes : ");
        for (int i = 0; i < n; i++) if (alive[i]) System.out.print("P" + i + " ");
        System.out.println();
        System.out.print("Crashed processes: ");
        for (int i = 0; i < n; i++) if (!alive[i]) System.out.print("P" + i + " ");
        System.out.println();

        election(detector);

        System.out.println("\nFinal Coordinator = P" + coordinator);
        sc.close();
    }
}
