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
- If you get a port conflict on 1050, choose any other free port (e.g. 1060) – just use it consistently across all 3 commands.
