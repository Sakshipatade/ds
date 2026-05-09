# Practical 4 – Addition of Two Numbers (RMI)

Multi-threaded RMI client/server to add two given numbers.

## Files
- `AddInterface.java` – Remote interface
- `AddServer.java` – Server implementation
- `AddClient.java` – Multi-threaded client

## How to Run

Open **two terminals** and `cd` into this folder.

### Step 1 – Compile
```bash
javac *.java
```

### Step 2 – Start the Server (Terminal 1)
```bash
java AddServer
```
Wait for: `Add Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java AddClient
```
Enter 3 pairs of numbers (`a b`) on separate prompts.

## Sample Output
```
Enter pair 1 (a b): 10 20
Enter pair 2 (a b): 5 7
Enter pair 3 (a b): 100 50
100.0 + 50.0 = 150.0
10.0 + 20.0 = 30.0
5.0 + 7.0 = 12.0
```

Stop the server with `Ctrl + C`.
