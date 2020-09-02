package runner;

import lombok.extern.log4j.Log4j;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utilities.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log4j
public class TestExecutor {

    public static void main(String args[]) throws IOException {

        FileReaderUtil fileReaderUtil = new FileReaderUtil();
        //------Parameters to be fetched at runtime---//
        String browserName = null;
        String browserVersion = null;
        int maxThreadCountSuiteLevel=3;
        int maxThreadCountTestLevel=3;
        String [] country = {"CO"};
        String testSuiteExcel =null;
        int iterationCount = 1;
        int browserRun =1;

        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        //suites.add(generatedXMLFile("SA_CO_SUITE","SA"));
        //suites.add(generatedXMLFile("BRAZIL_SUITE","Brazil"));

        TestNG testngExecutor = new TestNG();
        testngExecutor.setXmlSuites(suites);
        testngExecutor.setSuiteThreadPoolSize(2);
        testngExecutor.run();



        /*
        //--------------------------------------------//
        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        for(int i=0;i<country.length;i++){
            XmlSuite countrySuite = new XmlSuite();
            countrySuite.setName("Parallel Execution Suite of "+country[0]);
            countrySuite.setParallel(XmlSuite.ParallelMode.TESTS);
            //log.info("Parallel Count - "+parallelCount);
            countrySuite.setThreadCount(maxThreadCountSuiteLevel);
            //Add suite level parameters
            countrySuite.setParameters(new HashMap<>());
            suites.add(countrySuite);
        }


        for(XmlSuite countrySuite:suites)
        {
            testSuiteExcel = TestConstants.getTestDataFilePath();
            List<XmlClass> classes = ExcelUtil.checkModulesInExcel("MasterSheet", testSuiteExcel,log);
            XmlTest test = new XmlTest(countrySuite);
            log.info(country[0]+"-Test-"+iterationCount+"--Browser-"+browserRun);
            log.info("Inner loop");
            test.setName(country[0]+"-Test-"+iterationCount+"--Browser-"+browserRun);
            test.addParameter("browser",browserName);
            test.addParameter("browserVersion",browserVersion);
            test.addParameter("country",country[0]);
            test.setXmlClasses(classes);
            test.setParallel(XmlSuite.ParallelMode.CLASSES);
            test.setThreadCount(maxThreadCountTestLevel);
        }

        TestNG testngExecutor = new TestNG();
        testngExecutor.setXmlSuites(suites);
        testngExecutor.run();

        if(testngExecutor.hasFailure())
            System.exit(4);
        else
            System.exit(0);


         */
    }

    public static List<XmlInclude> constructIncludes(String... methodNames) {
        List<XmlInclude> includes = new ArrayList<XmlInclude> ();
        for (String eachMethod : methodNames) {
            includes.add (new XmlInclude (eachMethod));
        }
        return includes;
    }

    public static XmlSuite generatedXMLFile(String suiteName, int threadCount, String country, String program, String browser, String environment, String dockerinit, List<HashMap<String,String>> suiteData) throws IOException {

        XmlSuite suite = new XmlSuite ();
        suite.setName (suiteName);
        suite.setParallel (XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount (threadCount);

        XmlTest test = new XmlTest (suite);
        test.setName ("Test");
        test.setPreserveOrder (true);
        test.addParameter("country",country);
        test.setParallel(XmlSuite.ParallelMode.METHODS);
        test.setThreadCount(threadCount);

        XmlClass testClass = new XmlClass ();
        testClass.setName ("tests.common.web.Login.LoginTests");
        List<XmlInclude> methodsToRun = constructIncludes (new String[] {"validateLoginTest"});
        testClass.setIncludedMethods (methodsToRun);

        test.setXmlClasses (Arrays.asList (new XmlClass[] { testClass}));
        System.out.println ("Printing TestNG Suite Xml");
        System.out.println (suite.toXml ());

        return suite;

    }

    public static XmlSuite generatedXMLFile(String suiteName, int threadCount, String country) throws IOException {

        XmlSuite suite = new XmlSuite ();
        suite.setName (suiteName);
        suite.setParallel (XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount (threadCount);

        XmlTest test = new XmlTest (suite);
        test.setName ("Test");
        test.setPreserveOrder (true);
        test.addParameter("country",country);
        test.setParallel(XmlSuite.ParallelMode.METHODS);
        test.setThreadCount(threadCount);

        XmlClass testClass = new XmlClass ();
        testClass.setName ("tests.common.web.Login.LoginTests");
        List<XmlInclude> methodsToRun = constructIncludes (new String[] {"validateLoginTest"});
        testClass.setIncludedMethods (methodsToRun);

        test.setXmlClasses (Arrays.asList (new XmlClass[] { testClass}));
        System.out.println ("Printing TestNG Suite Xml");
        System.out.println (suite.toXml ());

        return suite;

    }



    /**
     * @Desc main class
     * @param
     */
    /*
    public static void main(String args[]){

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        String todayDate=dateFormat.format(date);
        TestCaseInit.todayDate=todayDate;
        String logfilename="logs_" + todayDate;
        System.setProperty("logFile",logfilename);
        Logger log = Logger.getLogger(TestNg.class);

        try{
            org.apache.log4j.BasicConfigurator.configure();
            log.info("--Inside TestNG -- Loading all basic configurations");
            FileInputStream fileInput = new FileInputStream(TestCaseInit.file_Path);
            Properties prop = new Properties();
            prop.load(fileInput);
            String testSuiteExcel="";
//------------------------------------------------------------------------------------------------------
            //	int parallelCount = Integer.valueOf(prop.getProperty("ParallelCount"));
            String soakTestStatus = prop.getProperty("SoakTest");
            int noOfIterations =  Integer.valueOf(prop.getProperty("TotalIterations"));
//-------------------------------------------------------------------------------------------------------

            String browsers = prop.getProperty("Browser");
            String[] browser = browsers.split(",");
            log.info("Number of Browsers -"+browser.length);

//--------------------------------Splitting Countries --------------------------------------------------
            String countries = prop.getProperty("countries");
            String[] country = countries.split(",");
            int parallelCount= country.length;
//-------------------------------------------------------------------------------------------------------
            if(!soakTestStatus.equalsIgnoreCase("Yes")){
                noOfIterations= 1;
            }

            if(prop.getProperty("sauceLabExceution").equalsIgnoreCase("YES")){
                log.info("As sauceLab is enabled so setting parallel count as 1.");
                parallelCount = 1;
            }

            XmlSuite suite = new XmlSuite();
            suite.setName("Parallel Execution Suite");
            suite.setParallel("tests");
            log.info("Parallel Count - "+parallelCount);
            suite.setThreadCount(parallelCount);

            int iterationCount = 1;
            int browserRun =1;

            for(int b=0;b<browser.length;b++){
                String browserName = browser[b].split(",")[0].split("\\|")[0];
                String browserVersion = browser[b].split("\\|")[1];
                log.info("Browsers Name-"+browserName);
                log.info("Browser Version -"+browserVersion);

                for(int itr=0;itr<noOfIterations;itr++){
                    for(int i=0;i<country.length;i++)
                    {
                        testSuiteExcel = TestCaseInit.current_Directory+"\\TestData\\"+prop.getProperty("TestSuiteFile")+"_"+country[i]+".xlsx";
                        List<XmlClass> classes = Excel_Generic_Methods.checkModulesInExcel("MasterSheet",log, testSuiteExcel);
                        XmlTest	test = new XmlTest(suite);
                        log.info(country[i]+"-Test-"+iterationCount+"--Browser-"+browserRun);
                        log.info("Inner loop");
                        test.setName(country[i]+"-Test-"+iterationCount+"--Browser-"+browserRun);
                        test.addParameter("browser",browserName);
                        test.addParameter("browserVersion",browserVersion);
                        test.addParameter("country",country[i]);
                        test.setXmlClasses(classes);
                        test.setParallel("classes");
                        test.setThreadCount(Integer.parseInt(prop.getProperty("threads_"+country[i])));

                    }
                    iterationCount++;
                }
                browserRun++;
            }

            List<XmlSuite> suites = new ArrayList<XmlSuite>();
            suites.add(suite);
            TestNG tng = new TestNG();
            tng.setXmlSuites(suites);

            tng.run();

            if(tng.hasFailure())

                System.exit(4);
            else
                System.exit(0);

        }catch(Exception e)
        {
            e.printStackTrace() ;
            log.info("Exception in TestNG class run");
            log.info(e.toString());
        }


    }
    */
    public int bvalue(int i,int len,int bcount) {

        int dvalue = len/(len/2)*10;
        if(i<=len/bcount)
        {

            return 1;
        }
        else if((i>len/bcount) && (i<=dvalue))
        {

            return 2;
        }
        else
        {

            return 3;
        }
    }



}
