# Practicals 16, 17, 18 – Token Ring Mutual Exclusion & Leader Election

Three independent Java programs in this folder:

| # | File | What it does |
|---|------|--------------|
| 16 | `TokenRing.java`     | Token ring based mutual exclusion |
| 17 | `Bully.java`         | Bully algorithm for leader election |
| 18 | `RingElection.java`  | Ring (Chang–Roberts) algorithm for leader election |

Each one runs as a single Java program (no separate server/client) and prints step-by-step output of the algorithm.

## Prerequisites – Install Java JDK

Pure Java – uses only `java.util.Scanner`. No external libraries. Any JDK 8 or higher works.

```bash
sudo apt update
sudo apt install -y default-jdk
```
Verify:
```bash
java -version
javac -version
```

## How to Run

### Step 1 – Compile (any program)
```bash
cd token_leader
javac *.java
```

### Step 2 – Run the program you want
```bash
java TokenRing       # Practical 16
java Bully           # Practical 17
java RingElection    # Practical 18
```
Then answer the input prompts.

---

## Practical 16 – Token Ring Mutual Exclusion (`TokenRing.java`)

A logical ring of N processes shares one **token**. Only the process holding the token may enter its critical section. After leaving the CS (or if it does not need the CS), it passes the token to the next process in the ring.

### Inputs
- Number of processes `n`
- How many processes want to enter CS, then their IDs
- Starting token holder

### Sample Run
```
Enter number of processes: 4
Enter how many processes want CS, then their IDs (0..3): 2 1 3
Enter starting token holder (0..3): 0

--- Token Ring Mutual Exclusion ---
Ring: P0 -> P1 -> P2 -> P3 -> P0
Token starts at P0

Token at P0
  P0 does not want CS, passing token
Token at P1
  P1 wants CS, holds the token, entering...
  >>> P1 ENTERS Critical Section
  <<< P1 EXITS  Critical Section
Token at P2
  P2 does not want CS, passing token
Token at P3
  P3 wants CS, holds the token, entering...
  >>> P3 ENTERS Critical Section
  <<< P3 EXITS  Critical Section
```

---

## Practical 17 – Bully Algorithm (`Bully.java`)

When a process detects that the current coordinator has crashed, it starts an **ELECTION** by messaging all processes with **higher** IDs. If any reply, they take over the election. The process with the highest alive ID eventually wins and announces itself as the new **COORDINATOR**.

### Inputs
- Number of processes
- How many are crashed, then their IDs
- ID of the process that detects the failure (initiator)

### Sample Run
```
Enter number of processes: 5
Enter how many processes are crashed, then their IDs: 1 4
Enter ID of process that detects coordinator failure: 1

--- Bully Algorithm ---
Alive processes : P0 P1 P2 P3
Crashed processes: P4

P1 starts an ELECTION
  P1 --ELECTION--> P2
  P2 --OK--> P1 (will take over)
  P1 --ELECTION--> P3
  P3 --OK--> P1 (will take over)

P2 starts an ELECTION
  P2 --ELECTION--> P3
  P3 --OK--> P2 (will take over)

P3 starts an ELECTION
P3 has no higher process responding.
P3 declares ITSELF as the new COORDINATOR.
  P3 --COORDINATOR--> P0
  P3 --COORDINATOR--> P1
  P3 --COORDINATOR--> P2

Final Coordinator = P3
```

---

## Practical 18 – Ring Algorithm (`RingElection.java`)

Processes are arranged in a logical ring. The initiator sends an **ELECTION** message containing its ID. Each alive process appends its ID and forwards. When the message returns to the initiator, the process with the **highest** ID in the collected list is the new coordinator. A **COORDINATOR** message is then circulated.

### Inputs
- Number of processes
- How many are crashed, then their IDs
- ID of the process that starts the election

### Sample Run
```
Enter number of processes: 5
Enter how many processes are crashed, then their IDs: 1 3
Enter ID of process that starts the election: 0

--- Ring Algorithm (Chang-Roberts) ---
Ring: P0 -> P1 -> P2 -> P3 -> P4 -> P0
Alive processes  : P0 P1 P2 P4
Crashed processes: P3

P0 starts ELECTION with list [0]
  -> P1 adds itself, list = [0, 1]
  -> P2 adds itself, list = [0, 1, 2]
  -> P3 is CRASHED, skipped
  -> P4 adds itself, list = [0, 1, 2, 4]

Message returned to initiator P0
Leader = max[0, 1, 2, 4] = P4

P0 sends COORDINATOR(P4) around the ring:
  -> P1 accepts P4 as coordinator
  -> P2 accepts P4 as coordinator
  -> P4 accepts P4 as coordinator

Final Coordinator = P4
```

---

## Notes
- All three are pure simulations (no sockets), so a single terminal is enough.
- Process IDs are integers `0..n-1`. The "highest ID" in Bully and Ring algorithms is the process with the largest such number.
- The token-ring program stops once every process that wanted CS has been served (max `2 * n` rounds).
