package framework.mobile;

public enum MobileDriverType {

    REAL_DEVICE("REAL_DEVICE"), SAUCE_LABS("SAUCE_LABS"),BROWSERSTACK_JENKINS("BROWSERSTACK_JENKINS"), BROWSERSTACK("BROWSERSTACK"), ANDROID_EMULATOR("ANDROID_EMULATOR");

    private String strValue;
    MobileDriverType(String strValue){this.strValue = strValue;}

    public String toString(){return strValue;}

}
