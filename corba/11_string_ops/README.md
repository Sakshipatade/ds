# Practical 11 – String Operations (CORBA)

Distributed String Operations application using CORBA – demonstrates object brokering. Operations: length, concat, reverse, uppercase.

> **JDK 8 required.** CORBA was removed from JDK 11+. See `corba/README.md` for install instructions.

## Files
- `StringOps.idl` – IDL interface definition
- `StringServer.java` – Server (with `StringOpsImpl` inside)
- `StringClient.java` – Client

## How to Run

Open **three terminals** and `cd` into this folder.

### Step 1 – Generate stubs from the IDL
```bash
idlj -fall StringOps.idl
```
This creates a `StringApp/` folder with auto-generated stub/skeleton files.

### Step 2 – Compile
```bash
javac *.java StringApp/*.java
```

### Step 3 – Start the Naming Service (Terminal 1)
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```
Leave this running.

### Step 4 – Start the Server (Terminal 2)
```bash
java StringServer -ORBInitialPort 1050 -ORBInitialHost localhost
```
Wait for: `String Operations Server is ready...`

### Step 5 – Run the Client (Terminal 3)
```bash
java StringClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
Enter two strings when prompted.

## Sample Output
```
Enter first string: Hello
Enter second string: World
Length of s1   : 5
Concatenation  : HelloWorld
Reverse of s1  : olleH
Uppercase of s1: HELLO
```

Stop `orbd` and the server with `Ctrl + C`.
