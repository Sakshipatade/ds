# Practical 7 – Division of Two Numbers (RMI)

Multi-threaded RMI client/server to divide two given numbers. Throws a `RemoteException` if the divisor is zero.

## Files
- `DivInterface.java` – Remote interface
- `DivServer.java` – Server implementation
- `DivClient.java` – Multi-threaded client

## Prerequisites – Install Java JDK

Java RMI is part of the standard JDK – no extra packages needed. Any JDK 8+ works.

```bash
sudo apt update
sudo apt install -y default-jdk
```
Verify with `java -version` and `javac -version`.

## How to Run

Open **two terminals** and `cd` into this folder.

### Step 1 – Compile
```bash
javac *.java
```

### Step 2 – Start the Server (Terminal 1)
```bash
java DivServer
```
Wait for: `Division Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java DivClient
```
Enter 3 pairs of numbers (`a b`) on separate prompts.

## Sample Output
```
Enter pair 1 (a b): 100 4
Enter pair 2 (a b): 81 9
Enter pair 3 (a b): 50 0
81.0 / 9.0 = 9.0
100.0 / 4.0 = 25.0
Error: RemoteException occurred in server thread; nested exception is:
       java.rmi.RemoteException: Cannot divide by zero
```

Stop the server with `Ctrl + C`.
