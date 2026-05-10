# Practical 5 – Subtraction of Two Numbers (RMI)

Simple RMI client/server to subtract two given numbers.

## Files
- `SubInterface.java` – Remote interface
- `SubServer.java` – Server implementation
- `SubClient.java` – Client

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
java SubServer
```
Wait for: `Subtraction Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java SubClient
```
Enter two numbers when prompted.

## Sample Output
```
Enter first number: 50
Enter second number: 20
50.0 - 20.0 = 30.0
```

Stop the server with `Ctrl + C`.
