package framework.web;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CommonWebActions {



        /**
         * Label is given then no need to create xpath for the web element
         * simply call this method and mention the "label" and "Value",the method will give you the xpath
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param label
         * @param value
         * @return
         */
        public static boolean EnterTextValue(WebDriver driver, Properties prop, Properties prop_loc, WebDriverWait wait, Logger log, ExtentTest logger, String label, String value) {
            String xpath = "//input[@placeholder='" + label + "']";

            if (driver.findElements(By.xpath(xpath)).size() != 0) {
                driver.findElement(By.xpath(xpath)).sendKeys(value);
                return true;
            } else {
                log.info("XPATH dosen't exists on this page");
                return false;
            }
        }

        /**
         * This method is used to submit any type of form
         * @return: result to submit a form or not
         */
        public boolean submitForm(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String xpath) {
            if (driver.findElements(By.xpath(prop_loc.getProperty("submitxpath"))).size() != 0) {
                driver.findElement(By.xpath(prop_loc.getProperty("submitxpath"))).submit();
                return true;
            } else {
                return false;
            }
        }

        /*
         *//** if any web site having links then call this method and give the full name of the linkText *//*
		public void clickOnLinkText(String linkText) {
			testInit.driver.findElement(By.linkText(linkText)).click();
		}
	  *//** if any web site having links then call this method and give the partial name of the linkText
         *  no need to give full name of the text*//*
		public void clickOnPartiallinkText(String partialLinkText){
			testInit.driver.findElement(By.partialLinkText(partialLinkText)).click();
		}
	   */
        /**implecitWait in Seconds
         * @param driver
         * @param timeInSec
         */
        public static void implicitWait(WebDriver driver,int timeInSec) {
            try{
                driver.manage().timeouts()
                        .implicitlyWait(timeInSec, TimeUnit.SECONDS);
            }catch(Exception e){

            }
        }

        /**implecitWait in Milliseconds
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param timeInmilisec
         */
        public static void implicitWaitmiliseconds(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,int timeInmilisec) {
            try{
                driver.manage().timeouts()
                        .implicitlyWait(timeInmilisec, TimeUnit.MILLISECONDS);
            }catch(Exception e){

            }
        }

        /**
         * ExplecitWait:Wait for specified element is to be present
         * @param driver
         * @param wait
         * @param xpath
         */
        public static void waitForElementToBePresent(WebDriver driver,WebDriverWait wait,String xpath) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }

        /**
         * ExplecitWait:Wait for specified element is visible
         * @param driver
         * @param wait
         * @param xpath
         */
        public static void waitForElementToBeVisible(WebDriver driver,WebDriverWait wait,String xpath){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        }

        /**
         * ExplecitWait:Wait for specified element is Clickable
         * @param driver
         * @param wait
         * @param xpath
         */
        public static void waitForElementToBeClickable(WebDriver driver,WebDriverWait wait,String xpath){
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        }

        /**
         * ExplecitWait:Wait for specified frame is available and switch to it
         * @param driver
         * @param wait
         * @param xpath
         */
        public static void waitForframeToBeavailableandswitch(WebDriver driver,WebDriverWait wait,String xpath){
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(xpath)));
        }

        /** Generate the screenShot
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param Method_Name
         * @throws Exception
         */
        public void getscreenshot(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String Method_Name) throws Exception {
            // Fetch Current Date and Time
            String timestamp = new java.text.SimpleDateFormat("dd_MM_yy_HH_mm_ss")
                    .format(new java.util.Date().getTime());

            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\New folder\\ScreenShot\\"
                    + Method_Name + "_" + timestamp + "_" + ".png"));

        }

        /**
         * Switches to main frame
         * @param driver
         */
        public static void switchToMainFrame(WebDriver driver,Logger log){
            driver.switchTo().defaultContent();
            log.info("Switched to top window");

        }

        /**
         * Switch to that Frame whose locator is provided
         * @param driver
         * @param Locator
         * @throws InterruptedException
         */
        public static void switchToFrame(WebDriver driver,WebDriverWait wait,Logger log,String Locator) {
            log.info("Switching to top window");
            log.info("Xpath of locator--"+ Locator);

            CommonWebActions.switchToMainFrame(driver, log);
		/* List<WebElement> x = driver.findElements(By.xpath("//iframe"));
		 for(int i=0;i<x.size();i++)
		 {
			  log.info(x.get(i).getAttribute("src"));
			  log.info(x.get(i).getAttribute("id"));
		 }*/
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
            CommonWebActions.waitForElementToBePresent(driver,wait,Locator);
            log.info("Switching to given frame");
            switchToDSSFrame(driver,wait,Locator);
            log.info("Switched to given frame");
        }

        /**
         * Switch to that Frame whose locator is provided
         * @param driver
         * @param Locator
         */
        public static void switchToDSSFrame(WebDriver driver,WebDriverWait wait,String Locator){
            CommonWebActions.waitForElementToBePresent(driver,wait,Locator);
            WebElement element = driver.findElement(By.xpath(Locator));
            driver.switchTo().frame(element);
        }

        /**
         * Verifies whether or not the element is present
         * @param driver
         * @param Locator
         * @return
         * @return
         */
        public static boolean isElementVisible(WebDriver driver,Logger log, String Locator){
            boolean result;


            if(driver.findElements(By.xpath(Locator)).size()!=0){
                result = true;
            }
            else {
                log.info("Element not visible");
                result = false;
            }return result;
        }

        /**
         * Waits for an element to be present
         */
        public static void waitforElement()
        {	try{
            Thread.sleep(2000);
        }catch(Exception e){}
        }

        /**
         * verifies the presence of an alert box
         * @return : result of presence of an alert box
         */
        public static boolean isAlertPresernt(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger)
        {
            try{
                driver.switchTo().alert();
                log.info("Alert or popup is present");
                return true;
            }catch(Exception e)
            {
                log.info("Alert or popup is not present");
                return false;
            }
        }

        /**
         * Take screenShort  for a page   and  save at given path
         * @param : String Method_Name
         * @param: String file path
         * @return
         * @return: boolean
         */
        public static String getscreenshot(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String Method_Name, String FilePath) throws Exception {
            String timestamp = new java.text.SimpleDateFormat("dd_MM_yy_HH_mm_ss").format(new java.util.Date().getTime());
            if (driver !=null) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile =  new File(FilePath+ Method_Name + "_" + timestamp + ".png");
                FileUtils.copyFile(scrFile, destFile);
            }
            return FilePath+ Method_Name + "_" + timestamp + ".png";
        }

        /**
         * Take screenShort for a page and attached it to Extend Report.
         * @param : String Method_Name
         * @param: String file path
         */
        public static String getscreenshot_extentReport(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String Method_Name, String FilePath) throws Exception {
            String timestamp = new java.text.SimpleDateFormat("dd_MM_yy_HH_mm_ss").format(new java.util.Date().getTime());
            if (driver !=null) {
                FileInputStream fileInputStreamReader = null;
                try {

                    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    File destFile =  new File(FilePath+ Method_Name + "_" + timestamp + ".png");
                    FileUtils.copyFile(scrFile, destFile);
                    String encodedBase64 = null;
                    fileInputStreamReader = new FileInputStream(destFile);
                    byte[] bytes = new byte[(int)destFile.length()];
                    fileInputStreamReader.read(bytes);
                    encodedBase64 = new String(Base64.getEncoder().encode(bytes));
                    logger.log(LogStatus.INFO, "Failure Snapshot below: " + logger.addScreenCapture("data:image/png;base64,"+encodedBase64));
                } catch (Exception e) {
                    //
                }finally{
                    fileInputStreamReader.close();
                }
            }
            return FilePath+ Method_Name + "_" + timestamp + ".png";
        }

        public static void getElementScreenshot(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String locator ,String Method_Name, String FilePath) throws IOException{
            FileInputStream fileInputStreamReader = null;
            try{
                String timestamp = new java.text.SimpleDateFormat("dd_MM_yy_HH_mm_ss").format(new java.util.Date().getTime());
                Screenshot screenshot =new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver) ;
                File srcFile=new File(FilePath+ Method_Name + "_" + timestamp + ".png");
                ImageIO.write(screenshot.getImage(), "PNG", srcFile);
                String encodedBase64 = null;
                fileInputStreamReader = new FileInputStream(srcFile);
                byte[] bytes = new byte[(int)srcFile.length()];
                fileInputStreamReader.read(bytes);
                encodedBase64 = new String(Base64.getEncoder().encode(bytes));
                logger.log(LogStatus.INFO, "Snapshot below: " + logger.addScreenCapture("data:image/png;base64,"+encodedBase64));
            }
            catch(UncheckedIOException e){
                log.info("Exception in get Element Screenshot method "+e.toString());
            }
            catch(Exception e){
                log.info("Exception in get Element Screenshot method "+e.toString());
            }finally{
                fileInputStreamReader.close();
            }

        }

        /**
         * switch to a frame inside a frame
         * @param driver
         * @param wait
         * @param Locator
         */
        public static void switchToFrameInsideFrame(WebDriver driver,WebDriverWait wait,String Locator){
            switchToDSSFrame(driver,wait,Locator);
        }

        /**
         * Switch to that Frame whose locator is provided
         * @param driver
         *
         */
        public static void switchParentWindow(WebDriver driver,String ParentWindowId){
            String parentWindowID= ParentWindowId;
            if (!parentWindowID.equals(null))
            {
                driver.close();
                driver.switchTo().window(parentWindowID);
            }
        }

        public static void clickOnLinkButton(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String locator) throws Exception {
            log.info("Clicking on link button" + locator);
            //Thread.sleep(3000);
            //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            driver.findElement(By.xpath(locator)).click();
            log.info("Clicked on link button" + locator);

        }


        /**
         * Clicks on the locator provided
         * @throws InterruptedException
         */
        public static void clickElement(WebDriver driver, WebDriverWait wait,Logger log,String locator){
            log.info("Clicking on locator-- "+locator);

            CommonWebActions.waitforElement();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            driver.findElement(By.xpath(locator)).click();
        }

        /**
         *  Clicks on locator provided.
         *
         */
        public static void clickElementActions(WebDriver driver, WebDriverWait wait,Logger log,String locator) throws InterruptedException{
            log.info("Clicking on locator-- "+locator);
            Thread.sleep(2000);
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
            act.moveToElement(driver.findElement(By.xpath(locator))).click().perform();
        }

        /**
         * Returns true if element is visible
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param locator
         * @return
         */
        public static boolean isElementDisplayed(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String locator){
            try{
                driver.findElement(By.xpath(locator));
                log.info("Locator  found - "+locator);
                return true;
            }catch(Exception e){
                log.info("Locator not found - "+locator);
                log.info(e.toString());
                return false;
            }
        }

        public static boolean isDisplayed(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, String locator){
            try{
                if(driver.findElement(By.xpath(locator)).isDisplayed()==true) {
                    log.info("Locator is displayed - "+locator);
                    return true;
                }
                else {
                    log.info("Locator is not displayed - "+locator);
                    return false;
                }

            }catch(Exception e){
                log.info("Locator not found - "+locator);
                log.info(e.toString());
                return false;
            }
        }

        /**
         * Sends text to the locator given
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param locator
         * @param text
         */
        public static void fillText(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String locator, String text){
            log.info("Entering text-"+text+" in locator--"+locator);

            CommonWebActions.waitforElement();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

            driver.findElement(By.xpath(locator)).click();
            driver.findElement(By.xpath(locator)).clear();
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }

        /**
         * Sends text to the locator given without clicking or clearing text
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param locator
         * @param text
         */
        public static void fillTextWithoutClear(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String locator, String text){
            log.info("Entering text-"+text+" in locator--"+locator);

            CommonWebActions.waitforElement();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

            driver.findElement(By.xpath(locator)).sendKeys(text);
        }

        /**
         * Clears text of the locator given
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param locator
         */
        public static void clearText(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String locator){
            log.info("Clearing text in locator--"+locator);

            CommonWebActions.waitforElement();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

            driver.findElement(By.xpath(locator)).click();
            driver.findElement(By.xpath(locator)).clear();
        }

        /**check if file is downloaded
         * @param downloadPath
         * @param fileName
         * @return
         */
        public static boolean isFileDownloaded(String downloadPath, String fileName) {
            File dir = new File(downloadPath);
            File[] dirContents = dir.listFiles();

            for (int i = 0; i < dirContents.length; i++) {
                if (dirContents[i].getName().equals(fileName)) {
                    // File has been found, it can now be deleted:
                    dirContents[i].delete();
                    return true;
                }
            }
            return false;
        }

        /**
         * Selects option from given locator
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param locator
         * @param text
         */
        public static void selectText(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String locator, String text){
            log.info("Selecting option-"+text+" on locator--"+locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            driver.findElement(By.xpath(locator)).click();
            new Select(driver.findElement(By.xpath(locator))).selectByVisibleText(text);
        }

        /**
         * clicks on the locator twice if first click does not work
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param locator
         * @param condition
         */
        public static void clickElementExceptionalCase(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String locator, String condition){{
            int i = 0;
            while(i<2){
                log.info("Clicking on locator--"+locator);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
                driver.findElement(By.xpath(locator)).click();
                if(isElementDisplayed(driver,prop,prop_loc,wait,log,logger, condition)){
                    break;
                }
                i++;
            }
        }
        }

        /**
         * Selects drop down menu  using given index and xpath.
         * @param driver
         * @param xpath
         * @param indexvalue
         */
        public static void selectByIndex(WebDriver driver,String xpath, int indexvalue) {
            Select select = new Select(driver.findElement(By.xpath(xpath)));
            select.selectByIndex(indexvalue);
        }

        /**
         * Selects drop down menu using given value and xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param xpath
         * @param value
         */
        public static void selectByValue(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath, String value) {
            Select select = new Select(driver.findElement(By.xpath(xpath)));
            select.selectByValue(value);
        }

        /**
         * Selects drop down menu using given Text and xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param xpath
         * @param text
         */
        public static void selectByVisibleText(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath, String text) {
            log.info("Selecting text from dropdown "+text);
            Select select = new Select(driver.findElement(By.xpath(xpath)));
            select.selectByVisibleText(text);
        }

        /**
         * Gives first selected value from drop down of given Xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param xpath
         * @return
         */
        public static String getSelectedText(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath){
            Select select = new Select(driver.findElement(By.xpath(xpath)));
            return select.getFirstSelectedOption().getText();
        }

        /**
         * Returns all options from select Tag of given Xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param xpath
         * @return
         */
        public static List<WebElement> getallOptions(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath){
            Select select = new Select(driver.findElement(By.xpath(xpath)));
            return select.getOptions();
        }

        /**
         * Returns Value attribute of given Xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param xpath
         * @return
         */
        public static String getValueAttribute(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath){
            return driver.findElement(By.xpath(xpath)).getAttribute("value");
        }

        /**
         * Returns defaultValue attribute of given Xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param xpath
         * @return
         */
        public static String getDefaultValueAttribute(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath){
            return driver.findElement(By.xpath(xpath)).getAttribute("defaultValue");
        }

        /**
         * Returns innerText attribute of given Xpath.
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log
         * @param logger
         * @param xpath
         * @return
         */
        public static String getInnerTextAttribute(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger,String xpath){

            return driver.findElement(By.xpath(xpath)).getAttribute("innerText");
        }

        /**
         * Switch to givne WINDOW Id
         * @param driver
         * @param prop
         * @param prop_loc
         * @param wait
         * @param log:log4j
         * @param logger :Extent Report instance
         * @param id
         */
        public static void switchtoWindow(WebDriver driver,Properties prop,Properties prop_loc,WebDriverWait wait,Logger log,ExtentTest logger, int id) {

            Set<String> wid = driver.getWindowHandles();
            Iterator<String> it = wid.iterator();
            ArrayList<String> ids = new ArrayList<String>();
            while (it.hasNext()) {
                ids.add(it.next());
            }
            driver.switchTo().window(ids.get(id));

        }

        /**
         * Method moves the mouse to the specified element.
         * @param driver
         * @param prop_loc
         * @param xpath_name
         */
        public static void movetoelement(WebDriver driver,Properties prop_loc,String xpath_name){

            Actions a = new Actions(driver);
            WebElement x = driver.findElement(By.xpath(prop_loc.getProperty(xpath_name)));
            a.moveToElement(x).build().perform();
        }

        /**
         * Method moves the mouse to the specified element.
         * @param driver
         * @param xpath
         */
        public static void movetoelementwithXpath(WebDriver driver,String xpath){

            Actions a = new Actions(driver);
            WebElement x = driver.findElement(By.xpath(xpath));
            a.moveToElement(x).build().perform();
        }

        /**
         * Convenient method to click the specified element using javascript executor.
         * @param xpath
         * @param driver
         */
        public static void Javascriptclick(String xpath, WebDriver driver){
            WebElement element = driver.findElement(By.xpath(xpath));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
        }

        /**
         * Convenient method to scroll to specified element using javascript executor.
         * @param xpath
         * @param driver
         */
        public static void javascriptscrolltoelement(String xpath, WebDriver driver){
            WebElement element = driver.findElement(By.xpath(xpath));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].scrollIntoView();",element);
        }

        /**
         * Method switch to alert and click on OK button of alert.
         */
        public static void switchandAcceptAlert( WebDriver driver){
            driver.switchTo().alert().accept();

        }

        /**
         * checks if given text is present or not in given select tag options.
         * @return boolean
         * @param driver
         * @param xpath
         * @param text
         */
        public static boolean verifyOptionTextUnderSelectTag(WebDriver driver,String xpath,String text){
            boolean flag = false;
            Select select = new Select(driver.findElement(By.xpath(xpath)));
            List <WebElement> options = select.getOptions();
            for(WebElement we: options){
                String optiontext = we.getText();
                if(text.equals(optiontext)){
                    flag= true;
                    break;
                }
            }
            return flag;
        }

        public static String switchandgetAlerttext( WebDriver driver){
            return driver.switchTo().alert().getText();

        }

        public static String getAttribute_ValueByxpath(WebDriver driver,String xpath, String AttributeName){

            return driver.findElement(By.xpath(xpath)).getAttribute(AttributeName);
        }

        public static void waitFor(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

        public static List<WebElement> getAllElements(WebDriver driver,String xpath) {
            try {

                return driver.findElements(By.xpath(xpath));
            }catch(Exception ex) {
                ex.printStackTrace();
                return null;
            }

        }

        public static String fetchText(WebDriver driver,String xpath) {
            try {
                return driver.findElement(By.xpath(xpath)).getText();
            }
            catch(Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        public static String fetchText(WebDriver driver,WebElement webElement) {
            try {
                return webElement.getText();
            }
            catch(Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        /**
         * @Desc methods waits for element to be present for a given timeout and polling interval
         * @param xpath
         * @param driver
         * @param duration
         * @param interval
         */
        public static void fluentWaitTillElementIsPresent(String xpath, WebDriver driver, Duration duration, Duration interval, Logger log) throws Exception {

            try{
                log.info("Inside fluentWaitTillElementIsPresent method. Waiting for element:"+xpath);
                Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                        .withTimeout(duration)
                        .pollingEvery(interval)
                        .ignoring(NoSuchElementException.class)
                        .ignoring(ElementNotVisibleException.class);
                fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            }catch(Exception ex){
                log.info(ex.toString());
                throw new Exception("Exception in fluentWaitTillElementIsPresent method");
            }

        }

        /**
         * @Desc method waits for element to be visible for a givem timeout and polling interval
         * @param xpath
         * @param driver
         * @param duration
         * @param interval
         */
        public static void fluentWaitTillElementIsVisible(String xpath, WebDriver driver, Duration duration, Duration interval, Logger log) throws Exception {

            try{
                log.info("Waiting for element:"+xpath);
                Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                        .withTimeout(duration)
                        .pollingEvery(interval)
                        .ignoring(NoSuchElementException.class)
                        .ignoring(ElementNotVisibleException.class);

                fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            }catch (Exception ex){
                log.info(ex.toString());
                throw new Exception("Exception in fluentWaitTillElementIsVisible method");
            }

        }


}
