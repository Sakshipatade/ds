# Practical 1 – Cube of a Number (RMI)

Simple RMI client/server to compute the cube of a given number.

## Files
- `CubeInterface.java` – Remote interface
- `CubeServer.java` – Server implementation
- `CubeClient.java` – Client

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
java CubeServer
```
Wait until you see: `Cube Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java CubeClient
```
Enter a number when prompted.

## Sample Output
```
Enter a number: 5
Cube of 5.0 = 125.0
```

Stop the server with `Ctrl + C`.
