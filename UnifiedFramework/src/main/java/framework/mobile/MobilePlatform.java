package framework.mobile;

public enum MobilePlatform {
    ANDROID("ANDROID"), IOS("IOS");

    private String strValue;
    MobilePlatform(String strValue){this.strValue = strValue;}

    public String toString(){return strValue;}

}
