# Practical 9 – String Equality Check (RMI)

Simple RMI client/server to check whether two given strings are equal.

## Files
- `EqualInterface.java` – Remote interface
- `EqualServer.java` – Server implementation
- `EqualClient.java` – Client

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
java EqualServer
```
Wait for: `String Equal Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java EqualClient
```
Enter two strings when prompted.

## Sample Output
```
Enter first string: hello
Enter second string: hello
"hello" and "hello" are EQUAL
```
```
Enter first string: java
Enter second string: Java
"java" and "Java" are NOT EQUAL
```

The check is case-sensitive (`equals`, not `equalsIgnoreCase`).

Stop the server with `Ctrl + C`.
