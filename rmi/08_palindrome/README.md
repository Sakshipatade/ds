# Practical 8 – String Palindrome Check (RMI)

Multi-threaded RMI client/server to check if a given string is a palindrome.

## Files
- `PalinInterface.java` – Remote interface
- `PalinServer.java` – Server implementation
- `PalinClient.java` – Multi-threaded client

## How to Run

Open **two terminals** and `cd` into this folder.

### Step 1 – Compile
```bash
javac *.java
```

### Step 2 – Start the Server (Terminal 1)
```bash
java PalinServer
```
Wait for: `Palindrome Server is ready...`

### Step 3 – Run the Client (Terminal 2)
```bash
java PalinClient
```
Enter 3 strings when prompted.

## Sample Output
```
Enter string 1: madam
Enter string 2: hello
Enter string 3: racecar
"madam" is a palindrome
"racecar" is a palindrome
"hello" is NOT a palindrome
```

The check is case-insensitive (e.g., "Madam" is treated as a palindrome).

Stop the server with `Ctrl + C`.
