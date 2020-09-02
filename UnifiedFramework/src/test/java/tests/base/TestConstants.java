//Guidelines:
////All variable should be kept private

package tests.base;

import lombok.extern.log4j.Log4j;
import utilities.FileReaderUtil;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utilities.GenericMethods.returnCurrentWorkingDirectPath;

@Log4j
public class TestConstants {

    private static Path projectDir=null;

    //NOTE::'PROGRAMVARIABLE' SHOULD TO BE REPLACED AT RUNTIME IN GETTER METHODS
    //Config Paths
    private static final String GLOBAL_FW_CONFIG_PATH = "\\src\\test\\resources\\global\\global-fw-config.properties";
    private static final String GLOBAL_TEST_CONFIG_PATH = "\\src\\test\\resources\\global\\global-test-config.properties";
    private static final String PROGRAM_FW_CONFIG_PATH = "\\src\\test\\resources\\program\\PROGRAMVARIABLE\\fw-config.properties";
    private static final String PROGRAM_TEST_CONFIG_PATH ="\\src\\test\\resources\\program\\PROGRAMVARIABLE\\test-config.properties";

    //Object repository path
    private static final String GLOBAL_OBJECT_REPO_PATH = "\\src\\test\\resources\\global\\global-obj.properties";
    private static final String PROGRAM_OBJECT_REPO_PATH = "\\src\\test\\resources\\program\\PROGRAMVARIABLE\\obj.properties";

    //Payload path
    private static final String GLOBAL_PAYLOAD_PATH = "\\src\\test\\resources\\global\\global-payload.properties";
    private static final String PROGRAM_PAYLOAD_PATH = "\\src\\test\\resources\\program\\PROGRAMVARIABLE\\payload.properties";

    //Query Path
    private static final String GLOBAL_QUERY_PATH = "\\src\\test\\resources\\global\\global-query.properties";
    private static final String PROGRAM_QUERY_PATH = "\\src\\test\\resources\\program\\PROGRAMVARIABLE\\query.properties";

    //TestData Path
    private static final String TEST_DATA_PATH = "\\testdata\\PROGRAMVARIABLE\\FILENAMEPREFIX_Testdata.xlsx";
    private static final String TEST_DATA_SHEETNAME ="SHEETNAMEPREFIX_TestData";
    private static final String TEST_RESULT_PATH = "\\results\\PROGRAMVARIABLE\\FILENAMEPREFIX_TestResult_TIMESTAMP.xlsx";
    private static final String TEST_RESULT_SHEETNAME ="SHEETNAMEPREFIX_Result";

    //Suite level properties
    private static final String PROGRAM = "Program";
    private static final String COUNTRY = "Country";
    private static final String BROWSER = "Browser";
    private static final String DOCKERINIT = "DockerInit";

    //
    private static final String TEST_DATA_FILE_PATH = "\\testdata\\TestSuite.xlsx";

    //Getter methods
    public static String getPROGRAM() {
        return PROGRAM;
    }

    public static String getCOUNTRY() {
        return COUNTRY;
    }

    public static String getBROWSER() {
        return BROWSER;
    }

    public static String getDOCKERINIT() {
        return DOCKERINIT;
    }

    public static String getTestDataFilePath() {
        return TEST_DATA_FILE_PATH;
    }


    //Methods
    //Global level getters
    public static final String getGlobalFWConfigPath() throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            configPath=projectDir+GLOBAL_FW_CONFIG_PATH;
            FileReaderUtil.isFileExists(configPath);
        }catch (Exception ex){
            throw new Exception("Exception in getGlobalConfigPath() method.",ex);
        }
        return configPath;
    }

    public static final String getGlobalTestConfigPath() throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            configPath=projectDir+GLOBAL_TEST_CONFIG_PATH;
            FileReaderUtil.isFileExists(configPath);
        }catch (Exception ex){
            throw new Exception("Exception in getGlobalConfigPath() method.",ex);
        }
        return configPath;
    }

    public static final String getGlobalObjectRepoPath() throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            configPath=projectDir+GLOBAL_OBJECT_REPO_PATH;
            FileReaderUtil.isFileExists(configPath);

        }catch (Exception ex){
            throw new Exception("Exception in getGlobalObjectRepoPath() method.",ex);
        }
        return configPath;
    }

    public static final String getGlobalPayloadPath() throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            configPath=projectDir+GLOBAL_PAYLOAD_PATH;
            FileReaderUtil.isFileExists(configPath);

        }catch (Exception ex){
            throw new Exception("Exception in getGlobalPayloadPath() method.",ex);
        }
        return configPath;
    }

    public static final String getGlobalQueryPath() throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            configPath=projectDir+GLOBAL_QUERY_PATH;
            FileReaderUtil.isFileExists(configPath);

        }catch (Exception ex){
            throw new Exception("Exception in getGlobalQueryPath() method.",ex);
        }
        return configPath;
    }


    //Program level getters
    public static final String getFWConfigPath(String program) throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            if(program!=null){
                configPath=projectDir+PROGRAM_FW_CONFIG_PATH.replace("PROGRAMVARIABLE",program.toLowerCase());
                FileReaderUtil.isFileExists(configPath);
            }
            else{
                throw new NullPointerException("Error: Program name not specified!");
            }
        }catch (Exception ex){
            throw new Exception("Exception in getFWConfigPath() method.",ex);
        }
        return configPath;
    }

    public static final String getTestConfigPath(String program) throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            if(program!=null){
                configPath=projectDir+PROGRAM_TEST_CONFIG_PATH.replace("PROGRAMVARIABLE",program.toLowerCase());
                FileReaderUtil.isFileExists(configPath);
            }
            else{
                throw new NullPointerException("Error: Program name not specified!");
            }
        }catch (Exception ex){
            throw new Exception("Exception in getTestConfigPath() method.",ex);
        }
        return configPath;
    }

    public static final String getProgramObjectRepoPath(String program) throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            if(program!=null){
                configPath=projectDir+PROGRAM_OBJECT_REPO_PATH.replace("PROGRAMVARIABLE",program.toLowerCase());
                FileReaderUtil.isFileExists(configPath);
            }
            else{
                throw new NullPointerException("Error: Program name not specified!");
            }
        }catch (Exception ex){
            throw new Exception("Exception in getProgramObjectRepoPath() method.",ex);
        }
        return configPath;
    }

    public static final String getProgramPayloadPath(String program) throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            if(program!=null){
                configPath=projectDir+PROGRAM_PAYLOAD_PATH.replace("PROGRAMVARIABLE",program.toLowerCase());
                FileReaderUtil.isFileExists(configPath);
            }
            else{
                throw new NullPointerException("Error: Program name not specified!");
            }
        }catch (Exception ex){
            throw new Exception("Exception in getProgramPayloadPath() method.",ex);
        }
        return configPath;
    }

    public static final String getProgramQueryPath(String program) throws Exception {
        String configPath;
        try{
            projectDir = returnCurrentWorkingDirectPath();
            if(program!=null){
                configPath=projectDir+PROGRAM_QUERY_PATH.replace("PROGRAMVARIABLE",program.toLowerCase());
                FileReaderUtil.isFileExists(configPath);
            }
            else{
                throw new NullPointerException("Error: Program name not specified!");
            }
        }catch (Exception ex){
            throw new Exception("Exception in getProgramQueryPath() method.",ex);
        }
        return configPath;
    }

    public static final String getProgramTestDataFilePath(String program, String environment){
        String filePath=null;
        String filePrefix = environment.toUpperCase();
        filePath=projectDir+TEST_DATA_PATH.replaceAll("PROGRAMVARIABLE",program.toLowerCase());
        filePath=filePath.replaceAll("FILENAMEPREFIX",filePrefix);

        return filePath;
    }

    public static final String getProgramTestResultFilePath(String program, String environment){
        String filePath=null;
        String filePrefix = environment.toUpperCase();
        filePath=projectDir+TEST_RESULT_PATH.replaceAll("PROGRAMVARIABLE",program.toLowerCase());
        filePath=filePath.replaceAll("FILENAMEPREFIX",filePrefix);
        filePath=filePath.replaceAll("TIMESTAMP",new SimpleDateFormat("yyyy_MM_dd'T'HH_mm_ss'Z'").format(new Date()));

        return filePath;
    }

    public static final String getProgramTestDataSheetName(String program, String environment){
        String sheetName=null;
        String filePrefix = program.toUpperCase()+"_"+environment.toUpperCase();
        sheetName=TEST_DATA_SHEETNAME.replaceAll("SHEETNAMEPREFIX",filePrefix);
        return sheetName;
    }

    public static final String getProgramResultSheetName(String program, String environment){
        String sheetName=null;
        String filePrefix = program.toUpperCase()+"_"+environment.toUpperCase();
        sheetName=TEST_RESULT_SHEETNAME.replaceAll("SHEETNAMEPREFIX",filePrefix);

        return sheetName;
    }


}
