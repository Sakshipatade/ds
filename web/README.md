# Practicals 19, 20, 21, 22, 23 – Web Service + Distributed Client

A single Java HTTP web service (`CalcServer.java`) and five small client programs that consume it. Uses only the JDK – no Tomcat, no external libraries.

| # | Operation       | Client file         | Endpoint   |
|---|-----------------|---------------------|------------|
| 19 | Addition       | `AddClient.java`    | `/add`     |
| 20 | Subtraction    | `SubClient.java`    | `/sub`     |
| 21 | Multiplication | `MulClient.java`    | `/mul`     |
| 22 | Division       | `DivClient.java`    | `/div`     |
| 23 | Sign-up form   | `SignupClient.java` | `/signup`  |

The "service" runs as a stand-alone HTTP server on `http://localhost:8080`. The client opens an HTTP connection, sends parameters, and prints the response — that is the distributed call.

---

## Prerequisites – Install JDK 8 (Java 1.8)

Uses only the JDK's built-in HTTP libraries (`com.sun.net.httpserver.HttpServer`, `java.net.HttpURLConnection`) – **no Tomcat, no Maven, no external libraries**.

```bash
sudo apt update
sudo apt install -y openjdk-8-jdk
```
Verify:
```bash
java -version       # should show 1.8.x
javac -version      # should show 1.8.x
```

### Optional – On Windows

After installing JDK 8 (e.g. from Adoptium or Oracle), set the path in `cmd` for the current session:
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xxx
set PATH=%JAVA_HOME%\bin;%PATH%
```
(Replace `jdk1.8.0_xxx` with the folder name actually installed on your machine.)
Verify with `java -version` and `javac -version` (should show 1.8.x).

`curl` is used in the optional browser/curl test below. It is normally pre-installed; if not:
```bash
sudo apt install -y curl
```

---

## How to Run

You need **two terminals** open in the `web` folder.

### Step 1 – Compile (once)
```bash
cd web
javac *.java
```

### Step 2 – Terminal 1: start the web service
```bash
java CalcServer
```
You should see:
```
Web service running on http://localhost:8080
Endpoints: /add  /sub  /mul  /div  /signup
```
Leave this running.

### Step 3 – Terminal 2: run any client
```bash
java AddClient        # Practical 19
java SubClient        # Practical 20
java MulClient        # Practical 21
java DivClient        # Practical 22
java SignupClient     # Practical 23
```
Each client asks for input, calls the web service, and prints the response.

When done, stop the server with `Ctrl+C` in Terminal 1.

---

## Sample Runs

### Practical 19 – Addition
```
Enter first  number: 12
Enter second number: 8

--- Response from web service ---
add(12.0, 8.0) = 20.0
```

### Practical 20 – Subtraction
```
Enter first  number: 50
Enter second number: 18

--- Response from web service ---
sub(50.0, 18.0) = 32.0
```

### Practical 21 – Multiplication
```
Enter first  number: 7
Enter second number: 6

--- Response from web service ---
mul(7.0, 6.0) = 42.0
```

### Practical 22 – Division
```
Enter first  number: 25
Enter second number: 4

--- Response from web service (HTTP 200) ---
div(25.0, 4.0) = 6.25
```
(If the second number is `0`, the server returns HTTP 400 with `Error: division by zero`.)

### Practical 23 – Sign-up
```
--- Sign-up Form ---
Name    : Akshay
Email   : akshay@example.com
Password: secret123

--- Response from web service (HTTP 200) ---
Sign-up SUCCESS
  name : Akshay
  email: akshay@example.com
  total registered users: 1
```
Trying to register the same email again returns HTTP 409 `User already registered`.

---

## (Optional) Test from a browser / curl

While `CalcServer` is running you can also hit it without the Java client:
```
http://localhost:8080/add?a=4&b=5
http://localhost:8080/sub?a=10&b=3
http://localhost:8080/mul?a=6&b=7
http://localhost:8080/div?a=20&b=5
```
Sign-up needs a POST:
```bash
curl -X POST -d "name=Akshay&email=a@b.com&password=secret" \
     http://localhost:8080/signup
```
This proves the service is a real, language-independent web service – any HTTP-capable client can consume it.

---

## Notes
- Only Java standard library is used (`com.sun.net.httpserver.HttpServer`, `HttpURLConnection`). Compatible with JDK 8.
- The "user database" in Practical 23 lives in memory and is reset every time you restart the server.
- Port `8080` must be free. If it is busy, change the port in `CalcServer.java` and in each client URL.
