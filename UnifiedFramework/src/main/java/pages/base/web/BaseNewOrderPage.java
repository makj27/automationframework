package pages.base.web;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Properties;

public abstract class BaseNewOrderPage {

    //Driver declaration
    private RemoteWebDriver driver;

    //Global Properties declaration
    private Properties globalFWProp;
    private Properties globalTestProp;
    private Properties globalObjProp;
    private Properties globalQueryProp;
    private Properties globalPayload;

    //Program Properties declaration
    private Properties programFWProp;
    private Properties programTestProp;
    private Properties programObjProp;
    private Properties programQueryProp;
    private Properties programPayload;

    //Constructor
    protected BaseNewOrderPage(RemoteWebDriver driver, Properties globalFWProp, Properties programFWProp,
                               Properties globalTestProp, Properties programTestProp,
                               Properties globalObjProp, Properties programObjProp,
                               Properties globalQueryProp, Properties programQueryProp,
                               Properties globalPayload, Properties programPayload){
        this.driver=driver;
        this.globalFWProp=globalFWProp;
        this.globalTestProp=globalTestProp;
        this.globalObjProp=globalObjProp;
        this.globalQueryProp=globalQueryProp;
        this.globalPayload=globalPayload;
        this.programFWProp=programFWProp;
        this.programTestProp=programTestProp;
        this.programObjProp=programObjProp;
        this.programQueryProp=programQueryProp;
        this.programPayload=programPayload;
    }

    //----------------------------Page Methods-----------------------------------//
    public abstract void openUrl(String url) throws InterruptedException;
}
