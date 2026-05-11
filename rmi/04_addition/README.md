# Practical 4 – Addition of Two Numbers (RMI)

Simple RMI client/server to add two given numbers.

## Files
- `AddInterface.java` – Remote interface
- `AddServer.java` – Server implementation
- `AddClient.java` – Client

## Prerequisites – Install JDK 8 (Java 1.8)

```bash
sudo apt update
sudo apt install -y openjdk-8-jdk
```
Verify with `java -version` and `javac -version` (should show 1.8.x).

### Optional – On Windows

After installing JDK 8 (e.g. from Adoptium or Oracle), set the path in `cmd` for the current session:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xxx
set PATH=%JAVA_HOME%\bin;%PATH%
```
(Replace `jdk1.8.0_xxx` with the folder name actually installed on your machine.)

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
Enter two numbers when prompted.

## Sample Output
```
Enter first number: 10
Enter second number: 20
10.0 + 20.0 = 30.0
```

Stop the server with `Ctrl + C`.
