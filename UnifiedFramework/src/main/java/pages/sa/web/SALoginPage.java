package pages.sa.web;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.base.web.BaseLoginPage;
import utilities.FileReaderUtil;
import utilities.LoggerUtil;

import java.io.FileNotFoundException;
import java.util.Properties;

@Log4j
public class SALoginPage extends BaseLoginPage {

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
    public SALoginPage(RemoteWebDriver driver, Properties globalFWProp, Properties programFWProp,
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
    public void navigateToLoginPage(){
        //SA specific navigation
        LoggerUtil.logEntryForPageMethod(new Exception(),log);
        LoggerUtil.logExitForPageMethod(new Exception(),log);
    }

    public void validateHomeTitle(){
        LoggerUtil.logEntryForPageMethod(new Exception(),log);
        LoggerUtil.logExitForPageMethod(new Exception(),log);
    }

    public void validateHomeTitle(String file) throws FileNotFoundException {
        //SA specific login validation

        LoggerUtil.logEntryForPageMethod(new Exception(),log);
        file.length();
        String variable=null;
        LoggerUtil.logDebug("Fetching home page title",log);
        if( file==null){
            throw new FileNotFoundException("Error in method validateHomeTitle(), XYZ File is not present");
        }
        if(variable==null){
            throw new NullPointerException("Error in method validateHomeTitle(), variable is null");
        }

        LoggerUtil.logDebug("End Method - validateHomeTitle()!",log);

    }

    public void openUrl(String url) throws InterruptedException {
        LoggerUtil.logEntryForPageMethod(new Exception(),log);
        driver.get(url);
        LoggerUtil.logInfo("Page Tile: " + driver.getTitle(),log);
        Thread.sleep(2000);
        LoggerUtil.logInfo(FileReaderUtil.getProperty(programTestProp,"DSSUI"),log);
        LoggerUtil.logExitForPageMethod(new Exception(),log);
    }


}
