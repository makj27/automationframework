package utilities;


import lombok.extern.log4j.Log4j;

import java.io.*;
import java.util.Properties;

@Log4j
public class FileReaderUtil {

    //Declaration of class variables
    private Properties properties=null;

    public Properties loadProperties(String propertyFilePath) throws IOException {
        BufferedReader reader;
        try {
            LoggerUtil.logTrace("Inside Utility Method - loadProperties()!",log);
            isFileExists(propertyFilePath);
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();

            properties.load(reader);
            reader.close();

        }
        finally{
            LoggerUtil.logTrace("End Utility Method - loadProperties()!",log);
        }
        return properties;
    }

    public static String getProperty(Properties prop, String propertyName){

        String propertyValue=null;
        try{
            LoggerUtil.logTrace("Inside Utility Method - getProperty()!",log);
            propertyValue=prop.getProperty(propertyName);
            if(propertyValue==null||propertyValue==""){
                throw new NullPointerException("Error: Property is not present or Property value is null for: "+propertyName);
            }
        }
       finally{
            LoggerUtil.logTrace("End Utility Method - getProperty()!",log);
        }
        return propertyValue;
    }

    public static void isFileExists(String fileNameWithPath) throws FileNotFoundException {

        File file = new File(fileNameWithPath);
        if(!file.exists()){
            throw new FileNotFoundException("Inside Utility Method - isFileExists(),'"+fileNameWithPath+"' does not exists!");
        }
    }

    public String fetchQuery(Properties prop, String key, String... args){

        String value = getProperty(prop,key);
        for(int i=0;i<args.length;i++){
            value=value.replaceAll("PARAM"+i,args[i]);
        }
        return null;
    }


}
