package utilities;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j
public class GenericMethods {

    String path = "test path need to move to congig";

    /**if in your application have dorpDown box then by using selectByIndex() you can handle
     //only enter the index value ,this method will give you the value of the corresponding index *//*
		public boolean selectByIndex(String xpath, int indexvalue) {
			boolean isPresent = true;
			try {
				WebElement webElt = testInit.driver
						.findElement(By.xpath(xpath));
				Select sel = new Select(webElt);
				sel.selectByIndex(indexvalue);
			} catch (NoSuchElementException e) {
				isPresent = false;
			}
			return isPresent;
		}
	  *//**if in your application have dorpDown box then by using selectByValue() you can handle
     * only enter the value ,this method will give you the value of the drop down Box*//*
	public boolean selectByValue(String xpath, String value) {
		boolean isPresent = true;
		try {
			WebElement webElt = testInit.driver
					.findElement(By.xpath(xpath));
			Select sel = new Select(webElt);
			sel.selectByValue(value);
		} catch (java.util.NoSuchElementException e) {
		isPresent = false;
		}
		return isPresent;
	}
	   *//**if in your application have dorpDown box then by using selectByIndex() you can handle
     * only enter the visibleText of the drop down box,this method will give you the value of the corresponding visible text *//*
	public boolean selectByVisibleText(String xpath, String visibleText) {
		boolean isPresent = true;
		try {
			WebElement webElt = testInit.driver
					.findElement(By.xpath(xpath));
			Select sel = new Select(webElt);
			sel.selectByVisibleText(visibleText);
		} catch (java.util.NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}

	    *//** this method is used for mouseOver operation
     * give the xpath of the web element in which you want to do mouse over*//*
	public boolean mouseOver(String xpath) {
		boolean isPresent = true;
		try {
			WebElement wb = testInit.driver.findElement(By.xpath(xpath));
			Actions act = new Actions(testInit.driver);
			act.moveToElement(wb).perform();
		} catch (java.util.NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}

	     *//** this method is used for mouseOverOnLinkText operation
     * give the link text of the web element in which you want to do mouse over*//*
	public boolean mouseOverOnLinkText(String linkText) {
		boolean isPresent = true;
		try {
			WebElement wb = testInit.driver.findElement(By.linkText(linkText));
			Actions act = new Actions(testInit.driver);
			act.moveToElement(wb).perform();
		} catch (java.util.NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}


        /** Read the excel data by giving the SHEET NAME,column name and rowName of the sheet
         * @return : data in the excel
         * @param sheetName
         * @param rowNum
         * @param colNum
         * @param testSuiteExcel
         * @return
         * @throws Exception
         */
    public String getExcelData(String sheetName, int rowNum, int colNum,String testSuiteExcel)
            throws Exception {
        // reading the data form the excel sheet

        FileInputStream fis = new FileInputStream(new File(testSuiteExcel));
        Workbook wbook = WorkbookFactory.create(fis);
        org.apache.poi.ss.usermodel.Sheet sh = wbook.getSheet(sheetName);
        Row row = sh.getRow(rowNum);
        String data = row.getCell(colNum).getStringCellValue();
        return data;
    }

    /** Set data in the excel sheet by giving the
     * SHEET NAME,column name and rowName and what you want to write in the excel sheet of the sheet
     * @param sheetName
     * @param rowNum
     * @param ColNum
     * @param data
     * @throws Exception
     */
    public void setExcelData(String sheetName, int rowNum, int ColNum,
                             String data) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet(sheetName);
        Row row = sh.getRow(rowNum);
        org.apache.poi.ss.usermodel.Cell cel = row.createCell(ColNum);
        cel.setCellType(CellType.STRING);
        cel.setCellValue(data);
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
    }

    /**
     * Method compare two given string.
     * @return boolean
     */
    public static boolean compareString(String value1, String value2, String operation, Logger log) {
        try {
            LoggerUtil.logTrace("Inside util method - compareString() !", log);
            LoggerUtil.logTrace("Value 1 is " + value1 + " and Value 2 is " + value2,log);
            boolean flag = false;
            boolean output = false;
            if(value1!=null)
                value1=value1.trim();
            if(value2!=null)
                value2=value2.trim();
            //LoggerUtil.logTrace("Value 1 is " + value1 + " and Value 2 is " + value2,log);

            if (value1 == null && value2 == null)
                flag = true;
            else if (value1 == null)
                flag = value2.equalsIgnoreCase(value1);
            else
                flag = value1.equalsIgnoreCase(value2);

            if (operation.equalsIgnoreCase("equals"))
                output = flag;
            else
                output = !flag;
            return output;
        }
        finally {
            LoggerUtil.logTrace("End Util Method - compareString()!",log);
        }
    }

    /**
     * Method compare two given string.
     * @return boolean
     */
    public static boolean compareString(String value1, String value2, String operation) {
        try {
            LoggerUtil.logTrace("Inside util method - compareString() !", log);
            LoggerUtil.logTrace("Value 1 is " + value1 + " and Value 2 is " + value2,log);
            boolean flag = false;
            boolean output = false;
            if(value1!=null)
                value1=value1.trim();
            if(value2!=null)
                value2=value2.trim();
            //LoggerUtil.logTrace("Value 1 is " + value1 + " and Value 2 is " + value2,log);

            if (value1 == null && value2 == null)
                flag = true;
            else if (value1 == null)
                flag = value2.equalsIgnoreCase(value1);
            else
                flag = value1.equalsIgnoreCase(value2);

            if (operation.equalsIgnoreCase("equals"))
                output = flag;
            else
                output = !flag;
            return output;
        }
        finally {
            LoggerUtil.logTrace("End Util Method - compareString()!",log);
        }
    }

    /**
     * Append a string value in an array of string.
     *  @return array of string
     */
    public static String[] appendinArray(String array[], String tagvalue)
    {
        String[] newarray= new String [array.length+1];


        for(int i=0;i<array.length;i++)
        {
            newarray[i]=array[i];

        }
        newarray[newarray.length-1]= tagvalue;

        return newarray;
    }

    /**
     * prints the info log
     */
    public static void printInfoLog(Logger log, String message){
        log.info("--Message INFO--"+message);
    }

    /**
     * prints the error log
     * @param log
     * @param message
     */
    public static void printErrorLog(Logger log, String message){
        log.error("--Message ERROR--"+message);
    }

    /**
     *  Gets the current date and time
     */
    public static String getCurrentDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = dateFormat.format(date).toString();
        return currentTime;
    }

    /**
     * Method generates random string
     * @return string
     */
    public static String getRandomStringOfSpecifiedFormat(String stringFormat,Logger log) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("SSSssmmHHddMMyyyy");
        Date date = new Date();
        String s=dateFormat.format(date);
        String number="";
        StringBuilder sb = new StringBuilder(s);//using StringBuilder to use insert function which is not available in String
        log.info("String with timestamp is "+s);
        if(stringFormat.contains("-")) {
            int indexSpecialCharacter = stringFormat.indexOf("-");
            sb.insert(indexSpecialCharacter, '-');
        }
        if(stringFormat.length()<=sb.length())
            number =sb.substring(0, stringFormat.length());
        else
            number= sb.append(sb, 0, stringFormat.length()-sb.length()).toString();
        log.info("Number generated is "+number);
        return number;
    }

    /**
     * This method is returns the Current Working Directory
     * @return
     * @return: Current Working Directory
     */
    public static Path returnCurrentWorkingDirectPath() {
        Path path = Paths.get(System.getProperty("user.dir"));
        //Path CWD = path.getParent();         // <-- Parent of parent directory
        return path;
    }

    /**
     * Method gives past date.
     * @return Past Date
     * @param daysBefore
     * @param format
     * @param log
     * @return
     */
    public static String getPastDate(int daysBefore, String format,Logger log){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -daysBefore);
        log.info(dateFormat.format(cal.getTime()).toString());
        return dateFormat.format(cal.getTime()).toString();
    }

    /**
     * Method gives future date.
     * @return Future Date
     * @param daysAfter
     * @param format
     * @param log
     */
    public static String getFutureDate(int daysAfter, String format,Logger log){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +daysAfter);
        log.info(dateFormat.format(cal.getTime()).toString());
        return dateFormat.format(cal.getTime()).toString();
    }

    /**
     * Print message in extent reports as per given status and message.
     * @param log
     * @param logger
     * @param type
     * @param msg
     */
    public static void reportLogs(Logger log, ExtentTest logger, String type, String msg){
        log.info(msg);
        if(type.equalsIgnoreCase("info"))
            logger.log(LogStatus.INFO, msg);
        if(type.equalsIgnoreCase("pass"))
            logger.log(LogStatus.PASS, msg);
        if(type.equalsIgnoreCase("fail"))
            logger.log(LogStatus.FAIL, msg);
        if(type.equalsIgnoreCase("warning"))
            logger.log(LogStatus.WARNING, msg);
    }

    /**
     * Method gives current date.
     * @return Current Date
     * */
    public static String getCurrentDatewithSpecifiedFormat(String format)
    {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String currentDate= dateFormat.format(date).toString();
        return currentDate;
    }

    /**
     * Method compare two given date.
     * @return string message
     * */
    public static String compareDateswithSpecfiedFormat(String date1, String date2,String format) throws ParseException {
        String message = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date obj1 = sdf.parse(date1);
        Date obj2 = sdf.parse(date2);
        if(obj1.compareTo(obj2)>0)
            message = "date 1 is greater";
        else if(obj1.compareTo(obj2)<0)
            message = "date 2 is greater";
        else if(obj1.compareTo(obj2)==0)
            message = "dates are equal";

        return message;


    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static LinkedHashMap<String,String> getMapFrmList(List<LinkedHashMap<String,String>> lstOfHmap){


        return new LinkedHashMap<>();
    }

    public static Map<String,String> getSubMap(HashMap<String,String> superMap, List<String> listToRetain, Map<String,String> inputMap){

        Map<String, String> subMap = listToRetain.stream()
                .filter(superMap::containsKey)
                .collect(Collectors.toMap(Function.identity(), superMap::get));

        subMap.putAll(inputMap);
        return subMap;
    }

    public static HashMap<String, Integer> getValueCountForKey(String requiredKey, String filterKey, String filterValue, List<LinkedHashMap<String,String>> hmapList){

        HashMap<String,Integer> resultSet = new HashMap<>();
        for(HashMap<String,String> hmap:hmapList){
            if(compareString(hmap.get(filterKey),filterValue,"EQUALS")){
                if(resultSet.containsKey(hmap.get(requiredKey))){
                    resultSet.put(hmap.get(requiredKey),resultSet.get(hmap.get(requiredKey))+1);
                }
                else{
                    resultSet.put(hmap.get(requiredKey),1);
                }
            }
        }
        return resultSet;
    }
}
