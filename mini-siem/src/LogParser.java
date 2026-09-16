package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogParser {

    public ArrayList<LogEntry> parseFile(String filePath) {
        ArrayList<LogEntry> entries = new ArrayList<LogEntry>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                line = line.trim();
                if (line.length() > 0) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 5) {
                        String timestamp = parts[0];
                        String ip = parts[1];
                        String username = parts[2];
                        String action = parts[3];
                        String status = parts[4];

                        LogEntry entry = new LogEntry(timestamp, ip, username, action, status);
                        entries.add(entry);
                    }
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading log file: " + e.getMessage());
        }

        return entries;
    }
}
