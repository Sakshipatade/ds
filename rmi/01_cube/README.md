# Practical 1 – Cube of a Number (RMI)

Multi-threaded RMI client/server to compute the cube of a given number.

## Files
- `CubeInterface.java` – Remote interface
- `CubeServer.java` – Server implementation
- `CubeClient.java` – Multi-threaded client

## Prerequisites – Install Java JDK

Java RMI is part of the standard JDK – no extra packages needed. Any JDK 8+ works.

```bash
sudo apt update
sudo apt install -y default-jdk
```
Verify with `java -version` and `javac -version`.

## How to Run

Open **two terminals** and `cd` into this folder.

### Step 1 – Compile (any terminal)
```bash
javac *.java
```

### Step 2 – Start the Server (Terminal 1)
```bash
java CubeServer
```
Wait until you see: `Cube Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java CubeClient
```
Enter 3 numbers when prompted.

## Sample Output
```
Enter number 1: 2
Enter number 2: 3
Enter number 3: 4
Cube of 3.0 = 27.0
Cube of 4.0 = 64.0
Cube of 2.0 = 8.0
```
The order may vary because each call runs on its own thread.

Stop the server with `Ctrl + C`.
