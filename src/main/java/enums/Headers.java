package enums;

public enum Headers {
    COOKIE("Cookie");

    private String header;

    Headers(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
