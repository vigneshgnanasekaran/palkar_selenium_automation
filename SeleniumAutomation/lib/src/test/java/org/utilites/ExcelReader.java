package org.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public List<UserCredentials> readTestData(String filepath, String sheetName) throws IOException {
		List<UserCredentials> userCredentialsList = new ArrayList<>();

		File file = new File(filepath);

		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet(sheetName);
			int lastRowNum = sheet.getLastRowNum();
			System.out.println(lastRowNum);

			for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
				Row row = sheet.getRow(rowNum);
				System.out.println(lastRowNum);
				System.out.println("row = " + rowNum);

				Cell usernameCell = row.getCell(0);
				Cell passwordCell = row.getCell(1);

				String username;
				String password;

				if (usernameCell.getCellType() == Cell.CELL_TYPE_STRING) {
					username = usernameCell.getStringCellValue();
				} else if (usernameCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					username = String.valueOf((int) usernameCell.getNumericCellValue());
				} else {
					username = "";
				}

				if (passwordCell.getCellType() == Cell.CELL_TYPE_STRING) {
					password = passwordCell.getStringCellValue();
				} else if (passwordCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					password = String.valueOf((int) passwordCell.getNumericCellValue());
				} else {
					password = "";
				}
				UserCredentials userCredentials = new UserCredentials(username, password);
				userCredentialsList.add(userCredentials);
			}
		}
		return userCredentialsList;
	}

	public static class UserCredentials {
		private final String username;
		private final String password;

		public UserCredentials(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}
	}
}
