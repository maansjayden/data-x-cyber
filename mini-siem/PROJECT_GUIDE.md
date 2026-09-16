# Mini-SIEM: Security Log ETL & Detection Pipeline

A dual-domain project bridging **Data Engineering** (ETL, log ingestion, schema normalization) and **Cybersecurity** (SIEM threat detection, brute-force analysis, anomaly alerting).

---

## 1. Project Overview & Architecture

Modern Security Operations Centers (SOCs) rely on data pipelines to ingest millions of log lines daily, clean them into structured formats, and query them for cyber threats. This project builds a lightweight, foundational version of that architecture.

```
+------------------------+      +------------------------+      +--------------------------+
|      Raw Log Data      | ---> |  Data Engineering ETL  | ---> |   Security Threat Rules  |
|  (data/auth_logs.txt)  |      |   (LogParser.java)     |      | (SecurityDetector.java)  |
+------------------------+      +------------------------+      +--------------------------+
                                                                             |
                                                                             v
                                                                +--------------------------+
                                                                | Security Alerts / Report |
                                                                +--------------------------+
```

### Components:
* **Extraction & Parsing (Data Engineering):** Reads unstructured or semi-structured raw log lines, extracts timestamps, IP addresses, usernames, actions, and status codes into structured [`LogEntry`](file:///home/jay/Desktop/mini-siem/src/LogEntry.java) objects.
* **Threat Detection (Cybersecurity):** Evaluates the structured log stream against threat detection rules (e.g., detecting repeated login failures from a specific IP address within an attack window).

---

## 2. Directory Structure

```
mini-siem/
|-- data/
|   `-- auth_logs.txt          # Raw authentication log events
|-- src/
|   |-- LogEntry.java          # Data model representing a single structured log event
|   |-- LogParser.java        # ETL ingestion and parsing logic
|   |-- SecurityDetector.java # Cyber threat detection engine
|   `-- Main.java             # Entry point orchestrating the pipeline
|-- bin/                      # Compiled Java bytecode (.class files)
`-- PROJECT_GUIDE.md          # Comprehensive project documentation
```

---

## 3. How to Run on Your Home PC

### Prerequisites
* Java Development Kit (JDK 11 or newer installed).
* Any text editor or IDE (VS Code, IntelliJ IDEA, or Eclipse).

### Compile and Run via Terminal / Command Prompt

1. Open a terminal and navigate to the project root directory:
   ```bash
   cd mini-siem
   ```

2. Compile all Java source files into the `bin` directory:
   ```bash
   mkdir -p bin
   javac -d bin src/*.java
   ```

3. Execute the pipeline:
   ```bash
   java -cp bin src.Main
   ```

---

## 4. Hands-On Debugging Exercises

The final detection step in [`SecurityDetector.java`](file:///home/jay/Desktop/mini-siem/src/SecurityDetector.java) was designed with beginner logic bugs to practice debugging and code review:

### Exercise 1: String Comparison
* **Symptom:** In [`Main.java`](file:///home/jay/Desktop/mini-siem/src/Main.java), IP `10.0.0.45` has 3 failed login attempts in [`data/auth_logs.txt`](file:///home/jay/Desktop/mini-siem/data/auth_logs.txt), but the detector reports `OK: Normal activity`.
* **Hint:** Look at how strings are compared in `entry.getIp() == targetIp` and `entry.getStatus() == "FAILED"`. In Java, `==` tests object reference equality, not character sequence equality.

### Exercise 2: Boundary / Threshold Check
* **Symptom:** Even when String comparison is corrected, an IP with exactly 3 failed attempts does not trigger an alert when threshold is set to 3.
* **Hint:** Check the condition `failedCount > threshold`. Should it be `>` or `>=`?

### Exercise 3: Alert Message Typos
* **Symptom:** Notice any misspelled words or inconsistent formatting in the alert output string.
* **Hint:** Inspect `"ALERT: Suspicous..."`.

---

## 5. Roadmap to Expand Your Portfolio

Once you finish fixing and running the base pipeline, consider these extensions:

1. **Database Integration (Data Engineering):**
   * Replace in-memory `ArrayList` with an embedded SQLite or DuckDB database using JDBC.
   * Write SQL queries to group by IP and count occurrences:
     ```sql
     SELECT ip, COUNT(*) AS failed_attempts
     FROM auth_logs
     WHERE status = 'FAILED'
     GROUP BY ip
     HAVING COUNT(*) >= 3;
     ```

2. **Web Server Log Support (Cybersecurity):**
   * Expand [`LogParser.java`](file:///home/jay/Desktop/mini-siem/src/LogParser.java) to ingest Common Log Format (Apache/Nginx access logs).
   * Add detection rules for SQL Injection signatures (e.g., `UNION SELECT`, `' OR 1=1`) or Directory Traversal (`../..`).

3. **Reporting & Dashboard:**
   * Export the detected alerts into a summary JSON file or build a simple web dashboard.
