# Practical 13 – Prime Number Check (CORBA)

Distributed application using CORBA to check whether a given number is prime.

> **JDK 8 required.** CORBA was removed from JDK 11+. See `corba/README.md` for install instructions.

## Files
- `Prime.idl` – IDL interface definition
- `PrimeServer.java` – Server (with `PrimeImpl` inside)
- `PrimeClient.java` – Client

## How to Run

Open **three terminals** and `cd` into this folder.

### Step 1 – Generate stubs from the IDL
```bash
idlj -fall Prime.idl
```
This creates a `PrimeApp/` folder with auto-generated stub/skeleton files.

### Step 2 – Compile
```bash
javac *.java PrimeApp/*.java
```

### Step 3 – Start the Naming Service (Terminal 1)
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```
Leave this running.

### Step 4 – Start the Server (Terminal 2)
```bash
java PrimeServer -ORBInitialPort 1050 -ORBInitialHost localhost
```
Wait for: `Prime Server is ready...`

### Step 5 – Run the Client (Terminal 3)
```bash
java PrimeClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
Enter a number when prompted.

## Sample Output
```
Enter a number: 17
17 is PRIME
```
```
Enter a number: 20
20 is NOT PRIME
```
```
Enter a number: 1
1 is NOT PRIME
```

Stop `orbd` and the server with `Ctrl + C`.
