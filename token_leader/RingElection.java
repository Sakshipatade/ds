import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Ring (Chang-Roberts) Algorithm for Leader Election (simulation).
// Processes are arranged in a logical ring. The initiator sends an ELECTION
// message containing its ID. Each alive process appends its ID and forwards.
// When the message returns to the initiator, the highest ID in the list
// becomes the new coordinator. A COORDINATOR message is then circulated.
public class RingElection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);

        System.out.print("Enter how many processes are crashed, then their IDs: ");
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            alive[sc.nextInt()] = false;
        }

        System.out.print("Enter ID of process that starts the election: ");
        int initiator = sc.nextInt();

        System.out.println("\n--- Ring Algorithm (Chang-Roberts) ---");
        System.out.println("Ring: P0 -> P1 -> ... -> P" + (n - 1) + " -> P0");
        System.out.print("Alive processes  : ");
        for (int i = 0; i < n; i++) {
            if (alive[i]) System.out.print("P" + i + " ");
        }
        System.out.println();
        System.out.print("Crashed processes: ");
        for (int i = 0; i < n; i++) {
            if (!alive[i]) System.out.print("P" + i + " ");
        }
        System.out.println("\n");

        // Step 1: initiator starts the ELECTION message with its own ID.
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(initiator);
        System.out.println("P" + initiator + " starts ELECTION with list " + ids);

        // Step 2: walk around the ring once. Each alive process appends its ID.
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

        // Step 3: the leader is the process with the largest ID in the list.
        int leader = ids.get(0);
        for (int i = 1; i < ids.size(); i++) {
            if (ids.get(i) > leader) leader = ids.get(i);
        }
        System.out.println("\nMessage returned to initiator P" + initiator);
        System.out.println("Leader = max" + ids + " = P" + leader);

        // Step 4: circulate the COORDINATOR message around the ring.
        System.out.println("\nP" + initiator + " sends COORDINATOR(P" + leader + ") around the ring:");
        curr = (initiator + 1) % n;
        while (curr != initiator) {
            if (alive[curr]) {
                System.out.println("  -> P" + curr + " accepts P" + leader + " as coordinator");
            }
            curr = (curr + 1) % n;
        }

        System.out.println("\nFinal Coordinator = P" + leader);
        sc.close();
    }
}
