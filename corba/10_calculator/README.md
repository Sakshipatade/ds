# Practical 10 – Calculator (CORBA)

Distributed Calculator using CORBA – demonstrates object brokering through the ORB / Naming Service. Operations: add, sub, mul, div.

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

## Files
- `Calculator.idl` – IDL interface definition
- `CalculatorServer.java` – Server (with `CalculatorImpl` inside)
- `CalculatorClient.java` – Client

## How to Run

Open **three terminals** and `cd` into this folder.

### Step 1 – Generate stubs from the IDL
```bash
idlj -fall Calculator.idl
```
This creates a `CalculatorApp/` folder with auto-generated stub/skeleton files.

### Step 2 – Compile
```bash
javac *.java CalculatorApp/*.java
```

### Step 3 – Start the Naming Service (Terminal 1)
```bash
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```
Leave this running.

### Step 4 – Start the Server (Terminal 2)
```bash
java CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost
```
Wait for: `Calculator Server is ready...`

### Step 5 – Run the Client (Terminal 3)
```bash
java CalculatorClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
Enter two numbers when prompted.

## Sample Output
```
Enter first number: 20
Enter second number: 4
Addition       : 24.0
Subtraction    : 16.0
Multiplication : 80.0
Division       : 5.0
```

Stop `orbd` and the server with `Ctrl + C`.
