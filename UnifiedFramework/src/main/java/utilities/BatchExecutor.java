package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BatchExecutor {

    public static void runBatchFile(String fileName) throws Exception {
        try{
            String workingDir = System.getProperty("user.dir");
            String fileNmWithPath = workingDir+"\\batch\\"+fileName+".bat";
            String logFileNmWithPath = workingDir+"\\results\\logs\\batch\\"+fileName+".log";
            FileReaderUtil.isFileExists(fileNmWithPath);

            Process process = Runtime.getRuntime().exec("cmd /c start "+fileNmWithPath);

            /*----------Code to write batch log---but need separate thread to fetch
            java.io.InputStream inputStream = (java.io.InputStream) process.getInputStream();
            inputstream before process completes-------------------/
            File logfile = new File(logFileNmWithPath);
            if(!logfile.exists()){
                logfile.createNewFile();
            }
            @SuppressWarnings("resource")
            FileOutputStream fop = new FileOutputStream(logfile);

            int i = 0;
            while( (i = inputStream.read() ) != -1) {

                fop.write((char)i);
            }*/
        }
        catch (Exception ex){
            throw new Exception("Exception inside runBatchFile() method!",ex);
        }

    }

    public static void killCMD() throws Exception {
        try{
            Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
        }catch (Exception ex){
            throw new Exception("Error inside killCMD() method!",ex);
        }
    }
}
