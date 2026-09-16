package src;

import java.util.ArrayList;

public class SecurityDetector {

    // Final step: Detect brute force login attempts for a specific IP address
    public String detectBruteForce(ArrayList<LogEntry> logs, String targetIp, int threshold) {
        int failedCount = 0;

        // Loop through all parsed log entries
        for (int i = 0; i < logs.size(); i++) {
            LogEntry entry = logs.get(i);

            // Beginner Bug 1 (String Comparison Bug): Using == instead of .equals() for String comparison
            if (entry.getIp() == targetIp) {
                // Beginner Bug 1 (continued): Using == instead of .equals() for status
                if (entry.getStatus() == "FAILED") {
                    failedCount = failedCount + 1;
                }
            }
        }

        // Beginner Bug 2 (Boundary Bug): Checking > instead of >= threshold (off-by-one)
        if (failedCount > threshold) {
            // Beginner Bug 3 (Typo Bug): Typo in alert description ("Suspicous" instead of "Suspicious")
            return "ALERT: Suspicous brute force attack detected for IP: " + targetIp + " with " + failedCount + " failed attempts";
        }

        return "OK: Normal activity for IP: " + targetIp;
    }
}
