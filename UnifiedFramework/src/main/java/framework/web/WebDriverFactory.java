package framework.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.FileReaderUtil;
import utilities.GenericMethods;
import utilities.LoggerUtil;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Log4j
public class WebDriverFactory {



    private RemoteWebDriver getDriverWithDocker(String browser, RemoteWebDriver driver) throws Exception {
        try {

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
            //desiredCapabilities.setCapability("name", "myTestName");
            desiredCapabilities.setCapability("build", "myTestBuild");
            desiredCapabilities.setCapability("tz", "Asia/Kolkata");

            switch (browser.toUpperCase()) {

                case "CHROME": {
                    desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                    desiredCapabilities.setCapability("name", "myTestChrome");
                    break;
                }
                case "FIREFOX": {
                    desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                    desiredCapabilities.setCapability("name", "myTestFirefox");
                    break;
                }
                default:

            }

            URL url = new URL("http://localhost:4444/wd/hub");
            driver = new RemoteWebDriver(url, desiredCapabilities);

        } catch (Exception ex) {
            throw new Exception("Error inside getDriver() method!", ex);
        }
        return driver;
    }

    private RemoteWebDriver getDriverWithoutDocker(String browser, RemoteWebDriver driver) throws Exception{
        try{
            switch (browser.toUpperCase()) {
                case "CHROME": {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                }
                case "FIREFOX": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
        }catch (Exception ex){
            throw new Exception("Exception occurred in getDriver() method",ex);
        }
        return driver;
    }

    public RemoteWebDriver getDriver(String browser, String dockerInit, Properties fwProp) throws Exception {

        RemoteWebDriver driver=null;

        if(GenericMethods.compareString(dockerInit,"Yes","EQUALS",log)){
            driver = getDriverWithDocker(browser, driver);
        }else{
            driver = getDriverWithoutDocker(browser, driver);
        }
        LoggerUtil.logInfo("Driver SessionId: "+driver.getSessionId().toString(),log);
        driver = configureBrowser(driver,fwProp);

        return driver;
    }

    private RemoteWebDriver configureBrowser(RemoteWebDriver driver, Properties fwProp){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(FileReaderUtil.getProperty(fwProp,"Implicit_Timeout")), TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(FileReaderUtil.getProperty(fwProp,"PageLoad_Timeout")), TimeUnit.MILLISECONDS);

        return driver;
    }

    public void exitDriver(RemoteWebDriver driver){
        driver.close();
        driver.quit();
    }

}
