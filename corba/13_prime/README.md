# Practical 13 – Prime Number Check (CORBA)

Distributed application using CORBA to check whether a given number is prime.

## Prerequisites – Install JDK 8

CORBA tooling (`idlj`, `orbd`) and the `org.omg.CORBA.*` / `org.omg.CosNaming.*` packages were **removed in JDK 11+**, so JDK 8 is mandatory.

```bash
sudo apt update
sudo apt install -y openjdk-8-jdk
```
If multiple JDKs are installed, switch the active one to JDK 8:
```bash
sudo update-alternatives --config java
sudo update-alternatives --config javac
```
Verify:
```bash
java -version    # must show 1.8.x
javac -version   # must show 1.8.x
which idlj orbd
```

### Optional – On Windows

After installing JDK 8 (e.g. from Adoptium or Oracle), set the path in `cmd` for the current session:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xxx
set PATH=%JAVA_HOME%\bin;%PATH%
```
(Replace `jdk1.8.0_xxx` with the folder name actually installed on your machine.)
Verify with `java -version`, `javac -version`, `where idlj`, `where orbd`.

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

## Specifying the Port

`orbd` understands two port flags:

- `-ORBInitialPort N` – the bootstrap port that both server and client connect to (mandatory).
- `-port N` – an extra internal activation port for `orbd` itself (optional).

Example using both:
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost -port 1030
```

If `1050` is busy, pick any other free port and use the **same** number everywhere:
```bash
orbd           -ORBInitialPort 1060 -ORBInitialHost localhost
java PrimeServer -ORBInitialPort 1060 -ORBInitialHost localhost
java PrimeClient -ORBInitialPort 1060 -ORBInitialHost localhost
```
Check whether a port is in use with `ss -tlnp | grep :1050`.
