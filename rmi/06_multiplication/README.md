# Practical 6 – Multiplication of Two Numbers (RMI)

Simple RMI client/server to multiply two given numbers.

## Files
- `MulInterface.java` – Remote interface
- `MulServer.java` – Server implementation
- `MulClient.java` – Client

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
java MulServer
```
Wait for: `Multiplication Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java MulClient
```
Enter two numbers when prompted.

## Sample Output
```
Enter first number: 6
Enter second number: 7
6.0 * 7.0 = 42.0
```

Stop the server with `Ctrl + C`.
