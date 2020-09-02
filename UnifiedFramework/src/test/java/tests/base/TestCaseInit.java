package tests.base;

import framework.web.WebDriverFactory;
import lombok.extern.log4j.Log4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utilities.ExcelUtil;
import utilities.FileReaderUtil;
import utilities.GenericMethods;
import utilities.LoggerUtil;
import utilities.exceptions.FailureType;
import utilities.exceptions.TestStatus;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static utilities.GenericMethods.compareString;
import static utilities.GenericMethods.returnCurrentWorkingDirectPath;

@Log4j
public class TestCaseInit {

    FileReaderUtil fileReaderUtil = new FileReaderUtil();
    ExcelUtil excelUtil = new ExcelUtil();
    protected Map<String,String> resultMap = null;
    private static String resultExcelPath =null;
    protected static HashMap<String,Integer> testExecutionDetails =null;

    //Test Result Variables
    protected static TestStatus testResultStatus=TestStatus.FAIL;
    protected static FailureType testFailureType=FailureType.ASSERTIONFAILURE;
    protected static List<String> testFailureMsg=null;

    //Suite-level properties
    protected static String browser =null;
    protected static String program=null;
    protected static String country=null;
    protected static String environment=null;
    protected static String dockerInit=null;

    //Config properties
    protected static Properties globalFWProp = null;
    protected static Properties globalTestProp = null;
    protected static Properties programFWProp = null;
    protected static Properties programTestProp = null;

    //Object repo properties
    protected static Properties globalObjProp = null;
    protected static Properties programObjProp = null;

    //Payload properties
    protected static Properties globalPayloadProp = null;
    protected static Properties programPayloadProp = null;

    //Query properties
    protected static Properties globalQueryProp = null;
    protected static Properties programQueryProp = null;

    //Project directory
    protected static Path projectDir = null;

    //Program Test Data
    protected static List<LinkedHashMap<String,String>> programTestData=null;

    //WebDriverFactory
    protected WebDriverFactory webDriverFactory;

    @Parameters({ "browser","country","program","environment","dockerinit"})
    @BeforeSuite
    public void suiteSetup(String browser, String country, String program, String environment, String dockerInit) throws Exception {
        try {

            LoggerUtil.logEntryForBeforeSuite(new Exception(),log);
            /*/-------------------------Log4j dynamic name logic------------------
            //System.setProperty("filename",".\\results\\logs\\logfile1.log");
            String logFile = "logfile"+"_"+country;
            log = Logger.getLogger(logFile);
            log.info("Logger Instance created for country "+country+log.toString());
            log.removeAllAppenders();
            String logFilePath = "./results/logs/";
            log.addAppender(new FileAppender(new PatternLayout("%d %-5p [%c{1}] %m %n" ), logFilePath+logFile+".log", true));
            //--------------------------------------------------------------------*/
            projectDir = returnCurrentWorkingDirectPath();

            //Load global properties
            globalFWProp = fileReaderUtil.loadProperties(TestConstants.getGlobalFWConfigPath());
            globalTestProp = fileReaderUtil.loadProperties(TestConstants.getGlobalTestConfigPath());
            globalObjProp = fileReaderUtil.loadProperties(TestConstants.getGlobalObjectRepoPath());
            globalQueryProp = fileReaderUtil.loadProperties(TestConstants.getGlobalQueryPath());
            globalPayloadProp = fileReaderUtil.loadProperties(TestConstants.getGlobalPayloadPath());

            //Load suite level properties
            //program = fileReaderUtil.getProperty(globalFWProp, TestConstants.getPROGRAM());
            //browser = fileReaderUtil.getProperty(globalFWProp, TestConstants.getBROWSER());
            //country = fileReaderUtil.getProperty(globalFWProp, TestConstants.getCOUNTRY());
            //dockerInit = fileReaderUtil.getProperty(globalFWProp, TestConstants.getDOCKERINIT());

            //Load suite level properties
            this.browser=browser;
            this.country=country;
            this.program=program;
            this.environment=environment;
            this.dockerInit=dockerInit;

            //Load program level properties
            programFWProp = fileReaderUtil.loadProperties(TestConstants.getFWConfigPath(program));
            programTestProp = fileReaderUtil.loadProperties(TestConstants.getTestConfigPath(program));
            programObjProp = fileReaderUtil.loadProperties(TestConstants.getProgramObjectRepoPath(program));
            programQueryProp = fileReaderUtil.loadProperties(TestConstants.getProgramQueryPath(program));
            programPayloadProp = fileReaderUtil.loadProperties(TestConstants.getProgramPayloadPath(program));

            //Load Test Data
            programTestData = excelUtil.getSheetData(Integer.parseInt(fileReaderUtil.getProperty(programFWProp,"TestDataRowStartIndex")),
                    TestConstants.getProgramTestDataSheetName(program,environment),
                    TestConstants.getProgramTestDataFilePath(program,environment));

            //Loading testCount
            testExecutionDetails = GenericMethods.getValueCountForKey("TestCase","ExecutionRequired","YES",programTestData);

            //Create Result Excel
            resultExcelPath=TestConstants.getProgramTestResultFilePath(program,environment);
            excelUtil.createExcelSheet(TestConstants.getProgramResultSheetName(program,environment),resultExcelPath,FileReaderUtil.getProperty(programFWProp,"ResultExcelHeaders"));
            LoggerUtil.logExitForBeforeSuite(new Exception(),log);
        }
        catch (Exception ex){
            LoggerUtil.logErrorStackTrace(ex,log,null);
        }
        finally {

        }
    }

    @AfterSuite
    public void suiteTearDown() throws Exception{
        try {
            LoggerUtil.logEntryForAfterSuite(new Exception(),log);
            //LoggerUtil.logInfo("Quiting driver",log);
            //driver.quit();
            LoggerUtil.logExitForAfterSuite(new Exception(),log);
        }
        finally {

        }
    }

/*
    @BeforeSuite(dependsOnMethods = {"suiteSetup"})
    public void startDockerGrid() throws Exception {
        if(GenericMethods.compareString(dockerInit,"Yes","EQUALS",log)){
            BatchExecutor.runBatchFile("start_zalenium");
            Thread.sleep(30000);
        }
    }

    @AfterSuite(dependsOnMethods = {"suiteTearup"})
    public void stopDockerGrid() throws Exception {
        if(GenericMethods.compareString(dockerInit,"Yes","EQUALS",log)){
            BatchExecutor.runBatchFile("stop_zalenium");
            Thread.sleep(15000);
            BatchExecutor.killCMD();
            BatchExecutor.killCMD();
        }
    }

*/

    @BeforeMethod
    public void setup(Method testcaseId, Object[] testData){
        LinkedHashMap<String,String> testMap=null;
        String testName =null;
        try {
            //Setting testname and threadname
            testMap = ((LinkedHashMap) testData[0]);
            testName = testMap.get("TestId")+ "|" + testMap.get("QTestId")+"|"+testcaseId.getName();
            Thread.currentThread().setName(testName);

            LoggerUtil.logEntryForBeforeMethod(new Exception(),log);

            //Resetting resultMap
            resultMap = new HashMap<>();

            //Setting time details
            Instant start = Instant.now();
            resultMap.put("StartTime",start.toString());
            webDriverFactory = new WebDriverFactory();

            testFailureMsg=new ArrayList<>();
            testResultStatus=TestStatus.FAIL;
            testFailureType=FailureType.ASSERTIONFAILURE;

            LoggerUtil.logExitForBeforeMethod(new Exception(),log);

        }
        finally {

        }
    }

    @AfterMethod
    public void tearDown(){
        try{
            LoggerUtil.logEntryForAfterMethod(new Exception(),log);

            //Setting thread details
            resultMap.put("BrowserType",browser);
            resultMap.put("ThreadName",Thread.currentThread().getName());

            //Calculating test duration i.e. time elapsed between @BeforeMethod to @AfterMethod
            Instant startTime = Instant.parse(resultMap.get("StartTime"));
            Instant endTime = Instant.now();
            long timeElapsed = Duration.between(startTime, endTime).getSeconds();

            //Setting time details
            resultMap.put("EndTime",endTime.toString());
            resultMap.put("TimeTaken",String.valueOf(timeElapsed));

            //Writing result row in result excel sheet
            excelUtil.writeNewRowToExcel(TestConstants.getProgramResultSheetName(program,environment),resultExcelPath,resultMap);

        } catch (InvalidFormatException ex) {
            LoggerUtil.logErrorStackTrace(ex,log,null);
        } catch (IOException ex) {
            LoggerUtil.logErrorStackTrace(ex,log,null);
        }
        catch (Exception ex){
            LoggerUtil.logErrorStackTrace(ex,log,null);
        }
        finally{
            LoggerUtil.logExitForAfterMethod(new Exception(),log);
        }
    }

    //-------------------------Data-Provider-----------------------------------------------//

    @DataProvider(name = "testData",parallel = true)
    public Object[][] getData(Method testcaseId, ITestContext iTestContext) throws Exception {
        Object[][] testData=null;
        boolean dataFlag = false;
        int objectCount = 0;
        try {
            LoggerUtil.logEntryForDataProvider(new Exception(), log);

            testData = new Object[testExecutionDetails.get(testcaseId.getName())][1];
            for (LinkedHashMap<String, String> testRow : programTestData) {

                if (testRow.get("TestCase").matches(testcaseId.getName())&&compareString(testRow.get("ExecutionRequired"),"YES","Equals")) {
                    testData[objectCount++][0] = testRow;
                    LoggerUtil.logInfo("Adding TestData in DataProvider for TestCase:" + testcaseId.getName() + "...\n" + testRow.toString(), log);
                    dataFlag = true;
                }
            }
            return testData;
        }
        finally {
            if (!dataFlag) {
                LoggerUtil.logErrorStackTrace(new Exception("Test data not available for TesId:" + iTestContext.getName()),log,null);
                throw new Exception("Test data not available for TesId:" + iTestContext.getName());
            }
            LoggerUtil.logExitForDataProvider(new Exception(),log);
        }
    }

    //--------------------------Other-Methods--------------//

    public Object getPageObject(String program, String className, RemoteWebDriver driver) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        if(className.contains("Base"))
            className=className.replace("Base","");
        className = program.toUpperCase()+className;
        Class cls = Class.forName("pages."+program.toLowerCase()+".web."+className);
        Constructor[] ctr = cls.getDeclaredConstructors();
        return cls.getDeclaredConstructors()[0].newInstance(new Object[]{driver,globalFWProp,programFWProp,globalTestProp,
                programTestProp,globalObjProp,programObjProp,globalQueryProp,programQueryProp,
                globalPayloadProp,programPayloadProp});

    }


}
