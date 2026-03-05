package payslip;

public class DownloadToken {
    private final long createdTime;
    private final long expiryMillis;

    public DownloadToken() {
        this(60_000L); // default 1 minute
    }

    public DownloadToken(long expiryMillis) {
        this.createdTime = System.currentTimeMillis();
        this.expiryMillis = expiryMillis;
    }

    public boolean isExpired() {
        long now = System.currentTimeMillis();
        return (now - createdTime) > expiryMillis;
    }
}
