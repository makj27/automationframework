package utilities.exceptions;

public enum TestStatus {

    PASS("PASS"), FAIL("FAIL"), INCOMPLETE("INCOMPLETE"), BLOCKED("BLOCKED"),
    NEEDRETEST("NEEDRETEST"), PENDING("PENDING");

    private String strValue;
    TestStatus(String strValue){this.strValue = strValue;}

    public String toString(){return strValue;}

}
