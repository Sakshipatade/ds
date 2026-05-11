# Practical 3 – Square of a Number (RMI)

Simple RMI client/server to compute the square of a given number.

## Files
- `SquareInterface.java` – Remote interface
- `SquareServer.java` – Server implementation
- `SquareClient.java` – Client

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
java SquareServer
```
Wait for: `Square Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java SquareClient
```
Enter a number when prompted.

## Sample Output
```
Enter a number: 7
Square of 7.0 = 49.0
```

Stop the server with `Ctrl + C`.
