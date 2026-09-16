package src;

public class LogEntry {
    private String timestamp;
    private String ip;
    private String username;
    private String action;
    private String status;

    public LogEntry(String timestamp, String ip, String username, String action, String status) {
        this.timestamp = timestamp;
        this.ip = ip;
        this.username = username;
        this.action = action;
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return timestamp + " | " + ip + " | " + username + " | " + action + " | " + status;
    }
}
