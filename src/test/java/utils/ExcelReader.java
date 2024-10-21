package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String getCellValue(String sheetName, String rowName, String columnName) throws IOException {
		// File path for the Excel file
		String path = System.getProperty("user.dir") + "/src/test/resources/testData/BookStoreApi.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		// Maps to store the column index and row index
		HashMap<String, Integer> columnMap = new HashMap<>();
		int rowIndex = -1;

		// Iterator to go through rows
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next(); // Assuming the first row contains the column headers
		Iterator<Cell> cells = firstRow.cellIterator();

		// Populate the column map with column name and its index
		int columnIndex = 0;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			columnMap.put(cell.getStringCellValue(), columnIndex);
			columnIndex++;
		}

		// Check if the specified column exists
		if (!columnMap.containsKey(columnName)) {
			workbook.close();
			throw new IllegalArgumentException("Column '" + columnName + "' does not exist.");
		}

		// Find the row that matches the given row name in the first column
		while (rows.hasNext()) {
			Row row = rows.next();
			Cell firstCell = row.getCell(0);
			if (firstCell != null && firstCell.getCellType() == CellType.STRING
					&& firstCell.getStringCellValue().equalsIgnoreCase(rowName)) {
				rowIndex = row.getRowNum();
				break;
			}
		}

		// Check if the row was found
		if (rowIndex == -1) {
			workbook.close();
			throw new IllegalArgumentException("Row '" + rowName + "' does not exist.");
		}

		// Get the cell value from the specified column for the found row
		Row targetRow = sheet.getRow(rowIndex);
		int targetColumnIndex = columnMap.get(columnName);
		Cell targetCell = targetRow.getCell(targetColumnIndex);

		String cellValue;
		if (targetCell == null) {
			cellValue = "";
		} else if (targetCell.getCellType() == CellType.STRING) {
			cellValue = targetCell.getStringCellValue();
		} else {
			cellValue = NumberToTextConverter.toText(targetCell.getNumericCellValue());
		}

		workbook.close();
		return cellValue;
	}
}
