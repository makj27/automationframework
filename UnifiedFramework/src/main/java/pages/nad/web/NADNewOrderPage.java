package pages.nad.web;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.base.web.BaseNewOrderPage;
import utilities.LoggerUtil;

import java.util.Properties;

@Log4j
public class NADNewOrderPage extends BaseNewOrderPage {

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
    public NADNewOrderPage(RemoteWebDriver driver, Properties globalFWProp, Properties programFWProp,
                       Properties globalTestProp, Properties programTestProp,
                       Properties globalObjProp, Properties programObjProp,
                       Properties globalQueryProp, Properties programQueryProp,
                       Properties globalPayload, Properties programPayload){
        super(driver, globalFWProp, programFWProp, globalTestProp, programTestProp,
                globalObjProp, programObjProp, globalQueryProp, programQueryProp,
                globalPayload, programPayload);
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
    public void openUrl(String url) throws InterruptedException {
        LoggerUtil.logEntryForPageMethod(new Exception(),log);
        /*
        Page code to be entered here

         */
        LoggerUtil.logExitForPageMethod(new Exception(),log);
    }

}
