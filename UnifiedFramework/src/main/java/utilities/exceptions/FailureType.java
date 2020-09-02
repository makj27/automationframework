package utilities.exceptions;

public enum FailureType {

    ASSERTIONFAILURE("ASSERTIONFAILURE"), EXCEPTIONFAILURE("EXCEPTIONFAILURE"), DATAFAILURE("DATAFAILURE"),
    DBCONNECTIONFAILURE("DBCONNECTIONFAILURE");

    private String strValue;
    FailureType(String strValue){this.strValue = strValue;}

    public String toString(){return strValue;}
}
