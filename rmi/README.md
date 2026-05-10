# Distributed Systems – Java RMI Practicals

Multi-threaded client/server programs using Java RMI.

## Folder Structure

```
rmi/
├── 01_cube/             - Cube of a number
├── 02_sqrt/             - Square root of a number
├── 03_square/           - Square of a number
├── 04_addition/         - Addition of two numbers
├── 05_subtraction/      - Subtraction of two numbers
├── 06_multiplication/   - Multiplication of two numbers
├── 07_division/         - Division of two numbers
├── 08_palindrome/       - String palindrome check
└── 09_string_equal/     - String equality check
```

Each folder contains 3 files:
- `XxxInterface.java`  – Remote interface
- `XxxServer.java`     – Server implementation
- `XxxClient.java`     – Multi-threaded client

---

## Prerequisites – Install Java JDK

Java RMI ships inside the standard JDK – no extra libraries to install. Any JDK 8 or higher works.

```bash
sudo apt update
sudo apt install -y default-jdk
```
Verify:
```bash
java -version
javac -version
```

---

## How to Run (any practical)

Open **two terminals** and `cd` into the practical folder, e.g.:

```bash
cd 01_cube
```

### Step 1 – Compile (in both terminals or once)
```bash
javac *.java
```

### Step 2 – Start the Server (Terminal 1)
```bash
java CubeServer
```
You should see: `Cube Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java CubeClient
```
Then enter the inputs when prompted.

> Replace `CubeServer` / `CubeClient` with the matching class name for the practical you are running (e.g. `SqrtServer`, `AddClient`, `PalinServer`, etc.).

---

## Quick Reference – Class Names per Practical

| # | Folder              | Server         | Client         |
|---|---------------------|----------------|----------------|
| 1 | 01_cube             | CubeServer     | CubeClient     |
| 2 | 02_sqrt             | SqrtServer     | SqrtClient     |
| 3 | 03_square           | SquareServer   | SquareClient   |
| 4 | 04_addition         | AddServer      | AddClient      |
| 5 | 05_subtraction      | SubServer      | SubClient      |
| 6 | 06_multiplication   | MulServer      | MulClient      |
| 7 | 07_division         | DivServer      | DivClient      |
| 8 | 08_palindrome       | PalinServer    | PalinClient    |
| 9 | 09_string_equal     | EqualServer    | EqualClient    |

---

## Notes

- The server starts an RMI registry on port **1099** automatically (no need to run `rmiregistry` separately).
- The client launches **3 threads**, each making a separate RMI call to the server – this demonstrates the multi-threaded behavior.
- RMI servers are inherently multi-threaded: each incoming client request is handled on its own thread.
- Stop the server with `Ctrl + C` after testing.
- If you get `Address already in use`, either wait a few seconds or kill the previous Java process.
