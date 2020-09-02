package utilities;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoggerUtil {


    public static String logErrorStackTrace(Exception ex, Logger log, String customMessage){

        String methodName = ex.getStackTrace()[0].getMethodName();
        String exceptionMsg="";
        if(ex.getMessage()!=null){
            exceptionMsg = ex.getMessage();
        }
        else{
            exceptionMsg = "NA";
        }
        if(customMessage==null||customMessage=="")
            customMessage="NA";

        String fullExceptionMessage="|--Error occurred in Test -"+methodName+"--|--CustomMsg:"+customMessage+"--|--ExceptionMsg:"+exceptionMsg+"--|--Exception:"+ex.toString()+"--|";
        log.info(fullExceptionMessage);
        log.error(fullExceptionMessage);
        log.error("+++Printing Stack Trace+++");
        for(int i= 0; i<ex.getStackTrace().length; i++){
            log.error("+++" +ex.getStackTrace()[i] +"+++");
        }
        log.error("+++End of Stack Trace+++");
        LoggerUtil.logTrace("End Utility Method - logErrorStackTrace()!",log);

        return fullExceptionMessage;
    }

    public static void logInfo(String message, Logger log){
        LoggerUtil.logTrace("Inside Utility Method - logInfo()!",log);
        log.info(message);
        LoggerUtil.logTrace("End Utility Method - logInfo()!",log);
    }

    public static void logDebug(String message,Logger log){
        LoggerUtil.logTrace("Inside Utility Method - logDebug()!",log);
        log.debug(message);
        LoggerUtil.logTrace("End Utility Method - logDebug()!",log);
    }

    public static void logTrace(String message, Logger log){
        log.trace(message);
    }

    public static void logEntryForUtilityMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.debug("#####---Inside Utility Method - "+methodName+" ---#####");
    }

    public static void logEntryForPageMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.debug("#####---Inside Page Method - "+methodName+" ---#####");
    }

    public static void logEntryForTestMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside Test Method - "+methodName+" ---#####");
    }

    public static void logExitForUtilityMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.debug("#####---Exiting Utility Method - "+methodName+" ---#####");
    }

    public static void logExitForPageMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.debug("#####---Exiting Page Method - "+methodName+" ---#####");
    }

    public static void logExitForTestMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting Test Method - "+methodName+" ---#####");
    }

    public static void logReturnValues(Exception ex, String message,Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info(methodName+" is returning "+ message);

    }

    public static void logEntryForBeforeMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside Before Method - "+methodName+" ---#####");
    }

    public static void logExitForBeforeMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting Before Method - "+methodName+" ---#####");
    }

    public static void logEntryForAfterMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside After Method - "+methodName+" ---#####");
    }

    public static void logExitForAfterMethod(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting After Method - "+methodName+" ---#####");
    }

    public static void logEntryForBeforeTest(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside Before Test - "+methodName+" ---#####");
    }

    public static void logExitForBeforeTest(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting Before Test - "+methodName+" ---#####");
    }

    public static void logEntryForAfterTest(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside After Test- "+methodName+" ---#####");
    }

    public static void logExitForAfterTest(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting After Test - "+methodName+" ---#####");
    }

    public static void logEntryForBeforeSuite(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside Before Suite - "+methodName+" ---#####");
    }

    public static void logExitForBeforeSuite(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting Before Suite - "+methodName+" ---#####");
    }

    public static void logEntryForAfterSuite(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside After Suite - "+methodName+" ---#####");
    }

    public static void logExitForAfterSuite(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting After Suite - "+methodName+" ---#####");
    }

    public static void logEntryForDataProvider(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Inside Data Provider - "+methodName+" ---#####");
    }

    public static void logExitForDataProvider(Exception ex, Logger log){
        String methodName = ex.getStackTrace()[0].getMethodName();
        log.info("#####---Exiting Data Provider - "+methodName+" ---#####");
    }


    public static Map<String,String> setStatusFAIL(String failureType, Map<String,String> resultMap, String errorMsg){
        resultMap.put("ExecutionStatus","FAIL");
        resultMap.put("FailureType",failureType);
        resultMap.put("FailureReason",errorMsg);
        return resultMap;
    }

    public static Map<String,String> setStatusPASS(Map<String,String> resultMap){
        resultMap.put("ExecutionStatus","PASS");
        return resultMap;
    }

    public static Map<String,String> setStatusINCOMPLETE(String failureType, Map<String,String> resultMap, String exceptionMsg){
        resultMap.put("ExecutionStatus","INCOMPLETE");
        resultMap.put("FailureType",failureType);
        resultMap.put("FailureReason",exceptionMsg);
        return resultMap;
    }

    public static Map<String,String> setStatusBLOCKED(String failureType, Map<String,String> resultMap, String exceptionMsg){
        resultMap.put("ExecutionStatus","BLOCKED");
        resultMap.put("FailureType",failureType);
        resultMap.put("FailureReason",exceptionMsg);
        return resultMap;
    }

    public static Map<String,String> setTestStatus(String testResultStatus, String failureType, List<String> failureList, Map<String,String> resultMap){

        String failureMsg = String.join("\n", failureList);
        switch (testResultStatus){
            case "PASS":
                resultMap = setStatusPASS(resultMap);
                break;
            case "FAIL":
                resultMap = setStatusFAIL(failureType, resultMap, failureMsg);
                break;
            case "INCOMPLETE":
                resultMap=setStatusINCOMPLETE(failureType, resultMap, failureMsg);
                break;
            case "BLOCKED":
                resultMap=setStatusBLOCKED(failureType, resultMap, failureMsg);
                break;
        }

        return resultMap;
    }

}
