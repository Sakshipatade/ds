# Distributed Systems – CORBA Practicals

Distributed applications using **Java IDL (CORBA)** to demonstrate object brokering.

## ⚠️ Important – JDK Requirement

CORBA was **removed from JDK 11 and later**. These programs need **JDK 8** because they use:
- `idlj` (IDL-to-Java compiler) – ships only with JDK 8
- `orbd` (Object Request Broker Daemon / Naming Service) – ships only with JDK 8
- `org.omg.CORBA.*` and `org.omg.CosNaming.*` packages

### Install JDK 8 on Ubuntu/Debian:
```bash
sudo apt update
sudo apt install openjdk-8-jdk
```

### Switch to JDK 8 (if multiple JDKs installed):
```bash
sudo update-alternatives --config java
sudo update-alternatives --config javac
```
Verify with:
```bash
java -version    # should show 1.8.x
javac -version   # should show 1.8.x
```

### Optional – On Windows

After installing JDK 8 (e.g. from Adoptium or Oracle), set the path in `cmd` for the current session:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xxx
set PATH=%JAVA_HOME%\bin;%PATH%
```
(Replace `jdk1.8.0_xxx` with the folder name actually installed on your machine.)
Verify with `java -version`, `javac -version`, `where idlj`, `where orbd`.

---

## Folder Structure

```
corba/
├── 10_calculator/   - Calculator (add, sub, mul, div)
├── 11_string_ops/   - String operations (length, concat, reverse, upper)
├── 12_odd_even/     - Check odd or even
└── 13_prime/        - Check prime number
```

Each folder contains:
- `Xxx.idl`          – IDL interface definition
- `XxxServer.java`   – Server implementation
- `XxxClient.java`   – Client

---

## How to Run (any practical)

You will need **3 terminals** for each practical.

### Step 1 – Go to the practical folder
```bash
cd 10_calculator
```

### Step 2 – Generate stub/skeleton classes from the IDL
```bash
idlj -fall Calculator.idl
```
This creates a sub-folder (e.g. `CalculatorApp/`) with auto-generated stub files (`*Helper.java`, `*Holder.java`, `*POA.java`, `_*Stub.java`, etc.).

### Step 3 – Compile all Java files
```bash
javac *.java CalculatorApp/*.java
```

### Step 4 – Start the ORB Naming Service (Terminal 1)
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```
Leave this running.

### Step 5 – Start the Server (Terminal 2)
```bash
java CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost
```
You should see: `Calculator Server is ready...`

### Step 6 – Run the Client (Terminal 3)
```bash
java CalculatorClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
Enter inputs as prompted.

---

## Quick Reference – Per Practical

| # | Folder         | IDL file       | Server class       | Client class       |
|---|----------------|----------------|--------------------|--------------------|
| 10| 10_calculator  | Calculator.idl | CalculatorServer   | CalculatorClient   |
| 11| 11_string_ops  | StringOps.idl  | StringServer       | StringClient       |
| 12| 12_odd_even    | OddEven.idl    | OddEvenServer      | OddEvenClient      |
| 13| 13_prime       | Prime.idl      | PrimeServer        | PrimeClient        |

For each, replace the IDL/class names in the steps above accordingly.

---

## Example – Practical 11 (String Operations)
```bash
cd 11_string_ops
idlj -fall StringOps.idl
javac *.java StringApp/*.java

# Terminal 1
orbd -ORBInitialPort 1050 -ORBInitialHost localhost

# Terminal 2
java StringServer -ORBInitialPort 1050 -ORBInitialHost localhost

# Terminal 3
java StringClient -ORBInitialPort 1050 -ORBInitialHost localhost
```

---

## Notes

- The naming service (`orbd`) acts as the Object Request Broker – it lets the client locate the remote object by name without knowing its physical location. This is the **object brokering** demonstration.
- The IDL file defines a language-neutral interface; `idlj` generates Java bindings from it.
- Stop `orbd` and the server with `Ctrl + C` after testing.

---

## Specifying the Port

`orbd` accepts two port-related flags:

| Flag                | Meaning                                                                 |
|---------------------|-------------------------------------------------------------------------|
| `-ORBInitialPort N` | Bootstrap / naming-service port that **clients and servers** connect to |
| `-port N`           | Activation port used internally by `orbd` itself (optional)             |

### Default usage (only the bootstrap port)
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```
Then start the server and client with the **same** `-ORBInitialPort 1050`:
```bash
java CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost
java CalculatorClient -ORBInitialPort 1050 -ORBInitialHost localhost
```

### Specify both ports
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost -port 1030
```
Here `1050` is the port the server / client talk to, and `1030` is the internal activation port.

### If port 1050 is already in use
Pick any other free port (e.g. `1060`, `2050`, `9000`) and use it consistently in all three commands:
```bash
orbd -ORBInitialPort 1060 -ORBInitialHost localhost
java CalculatorServer -ORBInitialPort 1060 -ORBInitialHost localhost
java CalculatorClient -ORBInitialPort 1060 -ORBInitialHost localhost
```
Check whether a port is free with:
```bash
ss -tlnp | grep :1050
```
