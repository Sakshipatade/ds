import java.util.Scanner;

public class TokenRing {

    static int n;
    static boolean[] wantsCS;
    static int token;

    static void enterCS(int p) throws InterruptedException {
        System.out.println("  >>> P" + p + " ENTERS Critical Section");
        Thread.sleep(500);
        System.out.println("  <<< P" + p + " EXITS  Critical Section");
        wantsCS[p] = false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        wantsCS = new boolean[n];
        System.out.print("Enter how many processes want CS, then their IDs (0.." + (n - 1) + "): ");
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int id = sc.nextInt();
            wantsCS[id] = true;
        }

        System.out.print("Enter starting token holder (0.." + (n - 1) + "): ");
        token = sc.nextInt();

        System.out.println("\n--- Token Ring Mutual Exclusion ---");
        System.out.println("Ring: P0 -> P1 -> ... -> P" + (n - 1) + " -> P0");
        System.out.println("Token starts at P" + token + "\n");

        int rounds = 0, maxRounds = 2 * n;
        while (anyWants() && rounds++ < maxRounds) {
            System.out.println("Token at P" + token);
            if (wantsCS[token]) {
                System.out.println("  P" + token + " wants CS, holds the token, entering...");
                enterCS(token);
            } else {
                System.out.println("  P" + token + " does not want CS, passing token");
            }
            token = (token + 1) % n;
        }

        if (anyWants())
            System.out.println("\nMax rounds reached.");
        else
            System.out.println("\nAll processes that wanted CS have completed. Token continues to circulate.");

        sc.close();
    }

    static boolean anyWants() {
        for (boolean b : wantsCS) if (b) return true;
        return false;
    }
}
