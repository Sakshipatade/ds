# Practical 2 – Square Root of a Number (RMI)

Multi-threaded RMI client/server to compute the square root of a given number.

## Files
- `SqrtInterface.java` – Remote interface
- `SqrtServer.java` – Server implementation
- `SqrtClient.java` – Multi-threaded client

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
Enter 3 numbers when prompted.

## Sample Output
```
Enter number 1: 16
Enter number 2: 25
Enter number 3: 100
Square root of 25.0 = 5.0
Square root of 100.0 = 10.0
Square root of 16.0 = 4.0
```

Stop the server with `Ctrl + C`.
