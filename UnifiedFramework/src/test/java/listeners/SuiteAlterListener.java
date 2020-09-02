package listeners;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.commons.compress.utils.Lists;
import org.testng.IAlterSuiteListener;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

import static runner.TestExecutor.generatedXMLFile;

@Log4j
public class SuiteAlterListener implements IAlterSuiteListener {

    @SneakyThrows
    @Override
    public void alter(List<XmlSuite> suites) {

        //------Parameters to be fetched at runtime---//
        String browserName = null;
        String browserVersion = null;
        int maxThreadCountSuiteLevel=3;
        int maxThreadCountTestLevel=3;
        String [] country = {"CO"};
        String testSuiteExcel =null;
        int iterationCount = 1;
        int browserRun =1;

        /*TestNG testngExecutor = new TestNG();
        testngExecutor.setXmlSuites(suites);
        testngExecutor.setSuiteThreadPoolSize(2);
        testngExecutor.run();*/

        //List<XmlSuite> Xsuites = new ArrayList<>();
        //Xsuites.add(generatedXMLFile("SA_CO_SUITE",1,"SA"));
        //Xsuites.add(generatedXMLFile("BRAZIL_SUITE",1,"Brazil"));

        List<String> suiteFiles = Lists.newArrayList();
        suiteFiles.add("./suites/suite2.xml");

        //Xsuites.get(0).setParentSuite(suites.get(0));
        for(XmlSuite suite:suites) {
            suite.getParentSuite();

            //suite.addIncludedGroup("bdd_runner");
            //log.info("in ialtersuite");
        }
    }
}
