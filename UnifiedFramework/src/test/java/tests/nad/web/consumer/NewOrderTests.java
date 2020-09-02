package tests.nad.web.consumer;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.base.web.BaseNewOrderPage;
import utilities.exceptions.ApplicationException;
import utilities.exceptions.FailureType;
import tests.base.TestCaseInit;
import utilities.exceptions.TestStatus;
import utilities.ExcelUtil;
import utilities.FileReaderUtil;
import utilities.GenericMethods;
import utilities.LoggerUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Log4j
public class NewOrderTests extends TestCaseInit {


    @Test(dataProvider = "testData")
    public void verifyNewOrder(LinkedHashMap<String,String> testData){
        RemoteWebDriver driver=null;
        //Map<String,String> resultMap = new HashMap<>();
        ExcelUtil excelUtil = new ExcelUtil();
        String SAN=null;
        String orderID=null;
        String fsoID=null;
        //UI validation variables
        String phnNum=null;
        String email=null;
        HashMap<String,String> address=null;
        //...
        String dssPIN = null;

        try {
            LoggerUtil.logEntryForTestMethod(new Exception(),log);

            //-----------------------Initialize driver and page class instance------------------------------//
            driver = webDriverFactory.getDriver(browser, dockerInit, programFWProp);
            BaseNewOrderPage newOrderPage = (BaseNewOrderPage)getPageObject(program,"BaseNewOrderPage",driver);
            //------------------------Set Column values from Test Data--------------------------------------------------//
            resultMap=GenericMethods.getSubMap(testData,excelUtil.getHeaders(FileReaderUtil.getProperty(programFWProp,"ResultColumnFrmTestData")),resultMap);
            //--------------------------------------------------------------------------------------------------------//

            LoggerUtil.logInfo("Start Opening URL",log);
            newOrderPage.openUrl(testData.get("URL"));

            testFailureMsg.add("ABC");
            testFailureMsg.add("XYZ");
            throw new ApplicationException("Package Not Found!",FailureType.DATAFAILURE, TestStatus.BLOCKED);
            //resultMap.put("ExecutionStatus","PASS");
            //Assert.fail();

        }
        catch (ApplicationException aex){
            testResultStatus = aex.getTestStatus();
            testFailureType = aex.getFailureType();
            testFailureMsg.clear();
            testFailureMsg.add(LoggerUtil.logErrorStackTrace(aex,log,null));
            Assert.fail("Test Execution Failed. Please check logs!");
        }
        catch(Exception ex){
            testResultStatus= TestStatus.FAIL;
            testFailureType=FailureType.EXCEPTIONFAILURE;
            testFailureMsg.clear();
            testFailureMsg.add(LoggerUtil.logErrorStackTrace(ex,log,null));
            Assert.fail("Test Execution Failed. Please check logs!");
        } finally {
            LoggerUtil.setTestStatus(testResultStatus.toString(), testFailureType.toString(), testFailureMsg, resultMap);
            webDriverFactory.exitDriver(driver);
            LoggerUtil.logExitForTestMethod(new Exception(), log);
        }
    }

}
