# Practical 5 – Subtraction of Two Numbers (RMI)

Multi-threaded RMI client/server to subtract two given numbers.

## Files
- `SubInterface.java` – Remote interface
- `SubServer.java` – Server implementation
- `SubClient.java` – Multi-threaded client

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
java SubServer
```
Wait for: `Subtraction Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java SubClient
```
Enter 3 pairs of numbers (`a b`) on separate prompts.

## Sample Output
```
Enter pair 1 (a b): 50 20
Enter pair 2 (a b): 100 75
Enter pair 3 (a b): 8 3
100.0 - 75.0 = 25.0
50.0 - 20.0 = 30.0
8.0 - 3.0 = 5.0
```

Stop the server with `Ctrl + C`.
