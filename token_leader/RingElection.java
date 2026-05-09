import java.util.*;

public class RingElection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);

        System.out.print("Enter how many processes are crashed, then their IDs: ");
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) alive[sc.nextInt()] = false;

        System.out.print("Enter ID of process that starts the election: ");
        int initiator = sc.nextInt();

        System.out.println("\n--- Ring Algorithm (Chang-Roberts) ---");
        System.out.println("Ring: P0 -> P1 -> ... -> P" + (n - 1) + " -> P0");
        System.out.print("Alive processes  : ");
        for (int i = 0; i < n; i++) if (alive[i]) System.out.print("P" + i + " ");
        System.out.println();
        System.out.print("Crashed processes: ");
        for (int i = 0; i < n; i++) if (!alive[i]) System.out.print("P" + i + " ");
        System.out.println("\n");

        List<Integer> ids = new ArrayList<>();
        ids.add(initiator);
        System.out.println("P" + initiator + " starts ELECTION with list " + ids);

        int curr = (initiator + 1) % n;
        while (curr != initiator) {
            if (alive[curr]) {
                ids.add(curr);
                System.out.println("  -> P" + curr + " adds itself, list = " + ids);
            } else {
                System.out.println("  -> P" + curr + " is CRASHED, skipped");
            }
            curr = (curr + 1) % n;
        }

        int leader = Collections.max(ids);
        System.out.println("\nMessage returned to initiator P" + initiator);
        System.out.println("Leader = max" + ids + " = P" + leader);

        System.out.println("\nP" + initiator + " sends COORDINATOR(P" + leader + ") around the ring:");
        curr = (initiator + 1) % n;
        while (curr != initiator) {
            if (alive[curr])
                System.out.println("  -> P" + curr + " accepts P" + leader + " as coordinator");
            curr = (curr + 1) % n;
        }

        System.out.println("\nFinal Coordinator = P" + leader);
        sc.close();
    }
}
