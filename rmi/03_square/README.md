# Practical 3 – Square of a Number (RMI)

Multi-threaded RMI client/server to compute the square of a given number.

## Files
- `SquareInterface.java` – Remote interface
- `SquareServer.java` – Server implementation
- `SquareClient.java` – Multi-threaded client

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
Enter 3 numbers when prompted.

## Sample Output
```
Enter number 1: 5
Enter number 2: 7
Enter number 3: 12
Square of 7.0 = 49.0
Square of 5.0 = 25.0
Square of 12.0 = 144.0
```

Stop the server with `Ctrl + C`.
