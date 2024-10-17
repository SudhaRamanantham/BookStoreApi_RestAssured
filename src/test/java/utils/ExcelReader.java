package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public ArrayList<String> getData(String testColumnName) throws IOException {

		// Saving row in array list
		ArrayList<String> al = new ArrayList<String>();

		// fileInputStream argument
		String path = System.getProperty("user.dir") + "/src/test/resources/testData/BookStoreApi.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // workbook is the .xlsx file

		int sheets = workbook.getNumberOfSheets(); // To check number of sheets in workbook
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("BookStoreApi")) { // Give sheet name and Here checking the
																				// desired sheet to go in
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify TestScenarios column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator(); // This will proceed to rows in that sheet
				Row firstRow = rows.next(); // This will landed on the first row
				Iterator<Cell> cells = firstRow.cellIterator(); // This will proceed to first cell on that row

				int k = 0;
				int columnNum = 0;
				while (cells.hasNext()) { // hasNext() is checking the value present on next cell
					Cell value = cells.next(); // landed on first cell
					if (value.getStringCellValue().equalsIgnoreCase("TestScenarios")) { // Give Column name
						columnNum = k;
					}
					k++;
				}
				System.out.println("TestScenarios column number: " + columnNum);

				// Once column is identified then scan entire TestCases column to identify
				// Purchase testcase row
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(columnNum).getStringCellValue().equalsIgnoreCase(testColumnName)) {
						// after you grab purchase testcase row = pull all the data of that row and feed
						// into test
						Iterator<Cell> cellValue = r.cellIterator();
						while (cellValue.hasNext()) {
							Cell ecv = cellValue.next();
							if (ecv.getCellType() == CellType.STRING) {
								al.add(ecv.getStringCellValue());
							} else {
								al.add(NumberToTextConverter.toText(ecv.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return al;
	}

}
