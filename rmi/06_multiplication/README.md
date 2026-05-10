# Practical 6 – Multiplication of Two Numbers (RMI)

Multi-threaded RMI client/server to multiply two given numbers.

## Files
- `MulInterface.java` – Remote interface
- `MulServer.java` – Server implementation
- `MulClient.java` – Multi-threaded client

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
java MulServer
```
Wait for: `Multiplication Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java MulClient
```
Enter 3 pairs of numbers (`a b`) on separate prompts.

## Sample Output
```
Enter pair 1 (a b): 6 7
Enter pair 2 (a b): 9 9
Enter pair 3 (a b): 12 5
6.0 * 7.0 = 42.0
12.0 * 5.0 = 60.0
9.0 * 9.0 = 81.0
```

Stop the server with `Ctrl + C`.
