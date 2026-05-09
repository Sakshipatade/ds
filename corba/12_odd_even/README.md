# Practical 12 – Odd or Even (CORBA)

Distributed application using CORBA to check whether a given number is odd or even.

> **JDK 8 required.** CORBA was removed from JDK 11+. See `corba/README.md` for install instructions.

## Files
- `OddEven.idl` – IDL interface definition
- `OddEvenServer.java` – Server (with `OddEvenImpl` inside)
- `OddEvenClient.java` – Client

## How to Run

Open **three terminals** and `cd` into this folder.

### Step 1 – Generate stubs from the IDL
```bash
idlj -fall OddEven.idl
```
This creates an `OddEvenApp/` folder with auto-generated stub/skeleton files.

### Step 2 – Compile
```bash
javac *.java OddEvenApp/*.java
```

### Step 3 – Start the Naming Service (Terminal 1)
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```
Leave this running.

### Step 4 – Start the Server (Terminal 2)
```bash
java OddEvenServer -ORBInitialPort 1050 -ORBInitialHost localhost
```
Wait for: `OddEven Server is ready...`

### Step 5 – Run the Client (Terminal 3)
```bash
java OddEvenClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
Enter a number when prompted.

## Sample Output
```
Enter a number: 7
7 is ODD
```
```
Enter a number: 12
12 is EVEN
```

Stop `orbd` and the server with `Ctrl + C`.
