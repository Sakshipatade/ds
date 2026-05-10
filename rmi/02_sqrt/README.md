# Practical 2 – Square Root of a Number (RMI)

Simple RMI client/server to compute the square root of a given number.

## Files
- `SqrtInterface.java` – Remote interface
- `SqrtServer.java` – Server implementation
- `SqrtClient.java` – Client

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
java SqrtServer
```
Wait for: `Sqrt Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java SqrtClient
```
Enter a number when prompted.

## Sample Output
```
Enter a number: 25
Square root of 25.0 = 5.0
```

Stop the server with `Ctrl + C`.
