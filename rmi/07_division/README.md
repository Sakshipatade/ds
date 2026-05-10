# Practical 7 – Division of Two Numbers (RMI)

Simple RMI client/server to divide two given numbers. Throws a `RemoteException` if the divisor is zero.

## Files
- `DivInterface.java` – Remote interface
- `DivServer.java` – Server implementation
- `DivClient.java` – Client

## Prerequisites – Install JDK 8 (Java 1.8)

```bash
sudo apt update
sudo apt install -y openjdk-8-jdk
```
Verify with `java -version` and `javac -version` (should show 1.8.x).

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
Enter two numbers when prompted.

## Sample Output
```
Enter first number: 100
Enter second number: 4
100.0 / 4.0 = 25.0
```

If you enter `0` as the divisor:
```
Enter first number: 50
Enter second number: 0
Error: Cannot divide by zero
```

Stop the server with `Ctrl + C`.
