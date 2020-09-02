package utilities;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlMethodSelector;

import java.io.*;
import java.util.*;

@Log4j
public class ExcelUtil {


    //Need to implement this method
    public static Map<XmlClass, List<XmlMethodSelector>> checkModulesInExcel(String masterSheet, String filePath, Logger log) {

        List<XmlClass> xmlClasses = new ArrayList<>();
        xmlClasses.add(new XmlClass("tests.common.web.Login.LoginTests"));
        return new HashMap<XmlClass, List<XmlMethodSelector>>();

    }

    public static String checkValueInExcel(String key, String keyColumnName, String valueColumnName, String sheetName, String excelPath) {

        return "Yes";
    }

    public static List<LinkedHashMap<String, String>> fetchExcelData(int startIndex, String sheetName, String excelPath) {
        List<LinkedHashMap<String, String>> excelSheetData = new ArrayList<>();


        return excelSheetData;
    }


    //------------------------------------generic utility----------------------------------------------------//

    //-------------------------------------Private methods------------------------------------------------------------//

    /**
     * @param fis
     * @param filePath
     * @return XSSFWorkbook
     * @throws IOException
     * @Desc loads excel data into a XSSF workbook object
     */
    private XSSFWorkbook loadExcelData(FileInputStream fis, String filePath) throws IOException {

        XSSFWorkbook workbook;
        File file = new File(filePath);
        fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
        return workbook;
    }

    /**
     * @param wbook
     * @return List<String>
     * @Desc load all the sheet names from a excel workbook object
     */
    private List<String> loadSheetNames(XSSFWorkbook wbook) {

        List<String> sheetNames;
        sheetNames = new ArrayList<>();
        int numOfSheets = wbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheets; i++) {
            sheetNames.add(wbook.getSheetName(i));
        }
        return sheetNames;
    }

    /**
     * @param cell
     * @return String
     * @Desc Provides string value of a cell in excel sheet
     */
    private String getStringValue(XSSFCell cell) {


        String value = cell.getCellType().equals(CellType.STRING) ? cell.getStringCellValue()
                : cell.getCellType().equals(CellType.NUMERIC) ? String.valueOf(cell.getNumericCellValue())
                : cell.getCellType().equals(CellType.BOOLEAN) ? String.valueOf(cell.getBooleanCellValue())
                : cell.getCellType().equals(CellType.ERROR) ? cell.getErrorCellString()
                : cell.getStringCellValue();

        return value;
    }

    /**
     * @param row
     * @return boolean
     * @Desc Determine whether a row is effectively completely empty - i.e. all cells either contain an empty string or nothing.
     */
    private boolean isRowEmpty(XSSFRow row) {
        if (row == null) {
            return true;
        }

        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            String cellValue = getStringValue(row.getCell(i));
            if (cellValue != null && cellValue.length() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param keyrow
     * @param rowDataInSheet
     * @param sheet
     * @return ArrayList<LinkedHashMap < String, String>>
     * @Desc fetch sheet data from excel - each map in the list represents a row in excel
     */
    private ArrayList<LinkedHashMap<String, String>> getSheetDataFromRowKeys(XSSFRow keyrow, ArrayList<LinkedHashMap<String, String>> rowDataInSheet, XSSFSheet sheet) {
        List<String> lstOfHeaders = new ArrayList();
        ;
        for (int j = 0; j < keyrow.getPhysicalNumberOfCells(); j++) {
            lstOfHeaders.add(getStringValue(keyrow.getCell(j)));
        }

        for (int i = keyrow.getRowNum() + 1; i < sheet.getLastRowNum() + 1; i++) {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            XSSFRow sheetRow = sheet.getRow(i);
            if (isRowEmpty(sheetRow))
                break;

            for (int s = 0; s < keyrow.getPhysicalNumberOfCells(); s++) {
                String value = getStringValue(sheetRow.getCell(s));
                String key = lstOfHeaders.get(s);
                map.put(key, value);
            }
            rowDataInSheet.add(map);
        }
        return rowDataInSheet;
    }

    /**
     * @param headerRowIndex
     * @param fileName
     * @return Map<String, ArrayList < LinkedHashMap < String, String>>>
     * @throws IOException
     * @Desc Provides every sheet data from excel file
     */
    private Map<String, ArrayList<LinkedHashMap<String, String>>> createTestDataMapper(int headerRowIndex, String fileName) throws IOException {

        Map<String, ArrayList<LinkedHashMap<String, String>>> sheetMap = new HashMap<>();
        ArrayList<LinkedHashMap<String, String>> testExecControlMap = new ArrayList<>();
        Map<String, ArrayList<LinkedHashMap<String, String>>> excelDataMap = null;
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        XSSFSheet masterSheet = null;
        XSSFSheet sheet = null;
        XSSFRow row = null;
        List<String> sheetNames = null;

        if (sheet == null) {
            workbook = loadExcelData(fis, fileName);
        }

        sheetNames = loadSheetNames(workbook);

        for (int k = 0; k < sheetNames.size(); k++) {
            ArrayList<LinkedHashMap<String, String>> rowDataInSheet = new ArrayList<>();

            sheet = workbook.getSheet(sheetNames.get(k));
            XSSFRow keyrow = sheet.getRow(headerRowIndex - 1);
            sheetMap.put(sheet.getSheetName(), getSheetDataFromRowKeys(keyrow, rowDataInSheet, sheet));
        }

        excelDataMap = sheetMap;

        JSONObject jsonMap = new JSONObject(sheetMap);
        LoggerUtil.logTrace(jsonMap.toString(), log);
        LoggerUtil.logTrace("Data mapped successfully from Input file !!!", log);

        return excelDataMap;

    }

    //----------------------------Public methods: For reading from excel---------------------------------------//

    /**
     * @param headerRowIndex
     * @param fileName
     * @return Map<String, ArrayList < LinkedHashMap < String, String>>>
     * @throws IOException
     * @Desc Synchronized method provides map of sheetname and sheetdata for all sheets in excel
     */
    public synchronized Map<String, ArrayList<LinkedHashMap<String, String>>> getExcelDataMap(int headerRowIndex, String fileName) throws IOException {

        Map<String, ArrayList<LinkedHashMap<String, String>>> excelDataMap = null;
        //if(CollectionUtils.sizeIsEmpty(excelDataMap))
        excelDataMap = createTestDataMapper(headerRowIndex, fileName);

        return excelDataMap;
    }

    /**
     * @param headerRowIndex
     * @param sheetName
     * @param fileName
     * @return
     * @throws IOException
     * @Desc Synchronized method provides list of rows(map) from a particular sheet in excel
     */
    public synchronized ArrayList<LinkedHashMap<String, String>> getSheetData(int headerRowIndex, String sheetName, String fileName) throws IOException {

        Map<String, ArrayList<LinkedHashMap<String, String>>> excelDataMap = getExcelDataMap(headerRowIndex, fileName);
        ArrayList<LinkedHashMap<String, String>> sheetData = excelDataMap.get(sheetName);
        return sheetData;
    }


    //------------------------Public methods: For writing in excel-------------------------------------//

    public List<String> getHeaders(String headersCommaSeparated){

        headersCommaSeparated = headersCommaSeparated.trim();
        String [] headers = headersCommaSeparated.split(",");

        return Arrays.asList(headers);
    }

    public synchronized void createExcelSheet(String sheetName, String excelSheetPath, String headersCommaSeparated) throws IOException {

        LoggerUtil.logEntryForUtilityMethod(new Exception(), log);
        String filePath = excelSheetPath;

        LoggerUtil.logTrace("Excel Filepath is " + filePath, log);
        File file = new File(filePath);

        List<String> headers = getHeaders(headersCommaSeparated);
        LoggerUtil.logTrace("Headers for excel are:" + headers.toString(), log);

        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            if (file.exists()) {
                file.delete();
            }
            XSSFSheet sheet = workbook.createSheet(sheetName);

            LoggerUtil.logTrace("Creating header row",log);
            Row row1 = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(headers.get(i));
            }
            workbook.write(fileOut);
            workbook.close();

            LoggerUtil.logTrace("Excel File Created", log);
        }

    }

    public synchronized static void writeNewRowToExcel(String sheetName,String path, Map<String, String> data) throws IOException, InvalidFormatException {
        String filePath = path;
        XSSFWorkbook workbook=null;

        File file = new File(filePath);
        try(FileInputStream fi = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fi);

            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(0);

            int rowNum = sheet.getLastRowNum() + 1;
            LoggerUtil.logTrace("Entering at RowNo.---" + rowNum, log);

            Row newRow = sheet.createRow(rowNum);

            LoggerUtil.logTrace("-----------================================-------------------", log);
            for (Map.Entry<String, String> entry : data.entrySet()) {
                LoggerUtil.logTrace("Creating cell for column: " + entry.getKey() + ", and with value as:" + entry.getValue(), log);
                LoggerUtil.logTrace("-----------================================-------------------", log);

                newRow.createCell(getCellNumber(sheet, entry.getKey(), log)).setCellValue(entry.getValue());

            }
        }
        try(FileOutputStream fileOut = new FileOutputStream(file)){
            workbook.write(fileOut);
            workbook.close();
        }
    }

    private static int getCellNumber(Sheet sheetName, String columnName, Logger log)
            throws InvalidFormatException, IOException {

        int cellNo = -1;
        int totalNoOfCols = sheetName.getRow(0).getLastCellNum();
        // log.info("Total Cols -" + totalNoOfCols);
        XSSFRow row0 = (XSSFRow) sheetName.getRow(0);
        for (int i = 0; i <= totalNoOfCols; i++) {
            String cellValue = row0.getCell(i).getStringCellValue().trim();
            if (cellValue.equalsIgnoreCase(columnName)) {
                cellNo = i;
                break;
            }
        }
        log.info("Cell No -" + cellNo);
        return cellNo;
    }
}