package enums;

public enum APIs {
    CREATE("create"),
    EMPLOYEE("employee/"),
    UPDATE("update/");

    private String api;

    APIs(String api) {
        this.api = api;
    }

    public String getApi() {
        return api;
    }
}
