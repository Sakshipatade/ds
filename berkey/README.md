# Practical 15 – Berkeley Algorithm for Clock Synchronization

A simple Java implementation of the Berkeley clock synchronization algorithm using TCP sockets.

## How the Algorithm Works
1. **Master polls** every slave for its current clock time.
2. **Master computes** the average of its own clock and all slave clocks.
3. **Master sends** an offset (`average − slave_time`) to each slave.
4. **Each slave adjusts** its clock by adding the offset.
5. After this round, all clocks (master + slaves) read the same average time.

In this demo, clock times are entered manually (in minutes) so you can clearly see the algorithm in action without dealing with real wall-clock skew.

## Files
- `BerkeleyServer.java` – Master (collects times, computes average, sends offsets)
- `BerkeleyClient.java` – Slave (sends time to master, applies received offset)

## How to Run

You will need **1 master terminal + N slave terminals** (e.g., 3 slaves = 4 terminals total).

### Step 1 – Compile (any terminal)
```bash
cd berkey
javac *.java
```

### Step 2 – Start the Master (Terminal 1)
```bash
java BerkeleyServer
```
You will be asked:
```
Enter master's current clock time (in minutes, e.g. 600 for 10:00): 600
How many slave clients will connect? 3
```
The server then waits for the slaves.

### Step 3 – Start each Slave (Terminals 2, 3, 4, …)
In each new terminal:
```bash
cd berkey
java BerkeleyClient
```
Each slave will ask:
```
Enter this slave's current clock time (in minutes): 605
```
Enter a different time in each slave terminal to simulate clock drift.

### Step 4 – Watch the synchronization
Once all slaves have connected and sent their times, the master computes the average and sends the offset back to each slave. Each slave prints its old time, the offset, and the new (synchronized) time.

## Sample Run (Master + 3 Slaves)

**Inputs**
- Master: 600
- Slave 1: 605
- Slave 2: 595
- Slave 3: 610

**Average** = (600 + 605 + 595 + 610) / 4 = 602

**Master output:**
```
--- Berkeley Algorithm ---
Master time     : 600
Slave 1 time     : 605
Slave 2 time     : 595
Slave 3 time     : 610
Average (sync)  : 602

Master's new time = 600 + (2) = 602
Sent offset to Slave 1 = -3
Sent offset to Slave 2 = 7
Sent offset to Slave 3 = -8

Clock synchronization complete.
```

**Slave 1 output:**
```
Received offset from master = -3
Old time : 605
New time : 605 + (-3) = 602
```
All clocks now agree on 602.

## Notes
- Master listens on **port 5000**. Stop with `Ctrl + C`.
- If you get `Address already in use`, wait a few seconds or kill any leftover Java process.
- The clock value is just an integer (minutes) for clarity — you could plug in real `System.currentTimeMillis()` values the same way.
