package pages.base.web;
//abstract class
//blueprint of common methods across different countries/programs


import lombok.extern.log4j.Log4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.FileReaderUtil;
import utilities.LoggerUtil;

import java.util.Properties;

@Log4j
public abstract class BaseLoginPage {

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
    protected BaseLoginPage(RemoteWebDriver driver, Properties globalFWProp, Properties programFWProp,
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



    public void navigateToLoginPage(){
        //Common implementation for navigating to login page
        try{
            LoggerUtil.logEntryForPageMethod(new Exception(),log);
            LoggerUtil.logExitForPageMethod(new Exception(),log);
        }
        finally{

        }
    }

    public void enterCredentialsAndSubmit(){
        //Common implementation for doing login
        try{
            LoggerUtil.logEntryForPageMethod(new Exception(),log);
            LoggerUtil.logExitForPageMethod(new Exception(),log);
            LoggerUtil.logInfo(FileReaderUtil.getProperty(programTestProp,"URL"),log);
        }
        finally{

        }
    }

    public abstract void validateHomeTitle() throws Exception;

    public abstract void validateHomeTitle(String file) throws Exception;

    public abstract void openUrl(String url) throws InterruptedException;

}
