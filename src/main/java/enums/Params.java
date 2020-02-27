package enums;

public enum Params {
    AGE("age"),
    DATA("data"),
    EMPLOYEE_SALARY("employee_salary"),
    ID("id"),
    NAME("name"),
    SALARY("salary");
    private String parameter;

    Params(String parameter) {
        this.parameter = parameter;
    }

    public String getParam() {
        return parameter;
    }
}
