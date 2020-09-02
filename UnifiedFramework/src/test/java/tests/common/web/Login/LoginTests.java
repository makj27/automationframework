package tests.common.web.Login;

import framework.web.WebDriverFactory;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import pages.base.web.BaseLoginPage;
import tests.base.TestCaseInit;
import utilities.LoggerUtil;

import java.util.LinkedHashMap;

@Log4j
public class LoginTests extends TestCaseInit {



    @Test(dataProvider = "testData")
    public void validateLoginTest(LinkedHashMap<String,String> testData){
        RemoteWebDriver driver=null;
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        try {
            LoggerUtil.logEntryForTestMethod(new Exception(),log);

            //-----------------------Initialize driver and page class instance------------------------------//
            driver = webDriverFactory.getDriver(browser, dockerInit, programFWProp);
            BaseLoginPage loginPage = (BaseLoginPage)getPageObject(program,"BaseLoginPage",driver);
            //Create instances of all page classes which are used in this test method
            //----------------------------------------------------------------------------------------------//

            LoggerUtil.logInfo("Start Opening url",log);
            loginPage.openUrl("https://www.google.com");
            LoggerUtil.logInfo("Start Navigating to login page",log);
            loginPage.navigateToLoginPage();
            LoggerUtil.logInfo("Start Entering Credentials",log);
            loginPage.enterCredentialsAndSubmit();
            LoggerUtil.logInfo("Start Validating Home Page Title",log);
            loginPage.validateHomeTitle(null);

        }
        catch(Exception ex){
            LoggerUtil.logErrorStackTrace(ex,log,null);
        }
        finally {
            webDriverFactory.exitDriver(driver);
            LoggerUtil.logExitForTestMethod(new Exception(),log);
        }
    }


    @Test(dataProvider = "testData")
    public void validateSampleTest(LinkedHashMap<String,String> testData){
        RemoteWebDriver driver=null;
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        try {
            LoggerUtil.logEntryForTestMethod(new Exception(),log);

            //-----------------------Initialize driver and page class instance------------------------------//
            driver = webDriverFactory.getDriver(browser, dockerInit, programFWProp);
            BaseLoginPage loginPage = (BaseLoginPage)getPageObject(program,"BaseLoginPage",driver);
            //Create instance of all page classes which are used in this method
            //----------------------------------------------------------------------------------------------//

            LoggerUtil.logInfo("Start Opening url",log);
            loginPage.openUrl("https://www.wikipedia.org");
            LoggerUtil.logInfo("Start Navigating to login page",log);
            loginPage.navigateToLoginPage();
            LoggerUtil.logInfo("Start Entering Credentials",log);
            loginPage.enterCredentialsAndSubmit();
            LoggerUtil.logInfo("Start Validating Home Page Title",log);
            loginPage.validateHomeTitle(null);
        }
        catch(Exception ex){
            LoggerUtil.logErrorStackTrace(ex,log,null);
        }
        finally {
            webDriverFactory.exitDriver(driver);
            LoggerUtil.logExitForTestMethod(new Exception(),log);
        }
    }

}



