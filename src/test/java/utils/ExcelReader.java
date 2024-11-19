package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public static Map<String, List<Map<String, String>>> loadExcelData() {

		String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/BookStoreApi.xlsx";

		Workbook wb = null;

		Map<String, List<Map<String, String>>> sheetNameRowsMap = new HashMap<>();

		try {

			wb = WorkbookFactory.create(new File(filePath));

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {

				Sheet sheet = wb.getSheetAt(i);

				List<Map<String, String>> recordList = new ArrayList<>();

				Row headerRow = sheet.getRow(0);

				for (int j = 1; j <= sheet.getLastRowNum(); j++) {

					Row row = sheet.getRow(j);
					Map<String, String> record = new HashMap<>();

					for (int k = 0; k < row.getLastCellNum(); k++) {

						Cell cell = row.getCell(k);
						String cellValue = cell != null
								? cell.getCellType() == CellType.NUMERIC ? ((long) cell.getNumericCellValue() + "")
										: cell.getStringCellValue()
								: null;
						String key = headerRow.getCell(k) != null ? headerRow.getCell(k).getStringCellValue() : "";
						record.put(key, cellValue);
					}

					recordList.add(record);
				}
				sheetNameRowsMap.put(sheet.getSheetName(), recordList);
			}

		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		} finally {

			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sheetNameRowsMap;
	}

	public static Map<String, String> getRowData(String sheetName, String testCase) {
		// Load all Excel data into the map
		Map<String, List<Map<String, String>>> sheetData = loadExcelData();
		System.out.println("sheetData: " + sheetData);

		// Check if the specified sheet exists
		if (!sheetData.containsKey(sheetName)) {
			System.out.println("Sheet '" + sheetName + "' not found.");
			return null;
		}

		// Retrieve rows for the specified sheet
		List<Map<String, String>> rows = sheetData.get(sheetName);

		// Iterate through rows to find the row matching the testCase
		for (Map<String, String> row : rows) {
			System.out.println("Row keys: " + row.keySet()); // Print available keys
			String testCaseValue = row.get("TestCases"); // Use the exact key name as in your data structure
			if (testCaseValue != null && testCase.equalsIgnoreCase(testCaseValue)) {
				return row; // Return the matching row
			}
		}

		// If no matching test case is found, return null
		System.out.println("Test case '" + testCase + "' not found in sheet '" + sheetName + "'.");
		return null;
	}

	public static String getCellValue(String sheetName, String testCase, String columnName) {
		// Load all Excel data into the map
		Map<String, List<Map<String, String>>> sheetData = loadExcelData();

		// Check if the specified sheet exists
		if (!sheetData.containsKey(sheetName)) {
			System.out.println("Sheet '" + sheetName + "' not found.");
			return null;
		}

		// Retrieve rows for the specified sheet
		List<Map<String, String>> rows = sheetData.get(sheetName);

		// Iterate through rows to find the row matching the testCase
		for (Map<String, String> row : rows) {
			String testCaseValue = row.get("TestCases"); // Use the exact key name as in your data structure
			if (testCaseValue != null && testCase.equalsIgnoreCase(testCaseValue)) {
				// Return the cell value for the specified column name
				return row.getOrDefault(columnName, null); // Use columnName to get the cell value
			}
		}

		// If no matching test case is found, return null
		System.out.println("Test case '" + testCase + "' not found in sheet '" + sheetName + "'.");
		return null;
	}

}
