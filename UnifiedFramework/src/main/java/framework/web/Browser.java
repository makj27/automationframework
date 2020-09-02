package framework.web;

public enum Browser {

    CHROME("CHROME"), FIREFOX("FIREFOX");

    private String strValue;
    Browser(String strValue){this.strValue = strValue;}

    public String toString(){return strValue;}

}
