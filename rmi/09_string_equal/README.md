# Practical 9 – String Equality Check (RMI)

Multi-threaded RMI client/server to check whether two given strings are equal.

## Files
- `EqualInterface.java` – Remote interface
- `EqualServer.java` – Server implementation
- `EqualClient.java` – Multi-threaded client

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
java EqualServer
```
Wait for: `String Equal Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java EqualClient
```
Enter 3 pairs of strings (each pair takes two prompts).

## Sample Output
```
Enter pair 1 - first string: hello
Enter pair 1 - second string: hello
Enter pair 2 - first string: java
Enter pair 2 - second string: Java
Enter pair 3 - first string: openai
Enter pair 3 - second string: claude
"openai" and "claude" are NOT EQUAL
"hello" and "hello" are EQUAL
"java" and "Java" are NOT EQUAL
```

The check is case-sensitive (`equals`, not `equalsIgnoreCase`).

Stop the server with `Ctrl + C`.
