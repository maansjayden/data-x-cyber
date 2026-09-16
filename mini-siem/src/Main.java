package src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Mini-SIEM Security Log Pipeline ===");

        String logPath = "data/auth_logs.txt";

        // Step 1: Extraction & Transformation (Data Engineering)
        System.out.println("\n[1] Ingesting and parsing logs from: " + logPath);
        LogParser parser = new LogParser();
        ArrayList<LogEntry> logs = parser.parseFile(logPath);

        System.out.println("Successfully parsed " + logs.size() + " log entries:");
        for (int i = 0; i < logs.size(); i++) {
            System.out.println("  -> " + logs.get(i).toString());
        }

        // Step 2: Threat Detection (Cybersecurity)
        System.out.println("\n[2] Running Security Detector (Brute-Force Rule)...");
        SecurityDetector detector = new SecurityDetector();

        String suspiciousIp = "10.0.0.45";
        int threshold = 3;

        String result = detector.detectBruteForce(logs, suspiciousIp, threshold);
        System.out.println("Result: " + result);

        System.out.println("\n=== Pipeline Execution Completed ===");
    }
}
