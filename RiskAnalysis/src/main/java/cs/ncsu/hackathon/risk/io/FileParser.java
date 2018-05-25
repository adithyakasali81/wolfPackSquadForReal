/**
 * 
 */
package cs.ncsu.hackathon.risk.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yussef Guerrab
 *
 */

public class FileParser {
	/**
	 * return a list of Row information
	 * @param fileName : name of file 
	 * @param startDate : start date at local date object
	 * @param endDate : end date at local date object
	 * @return list of Row information
	 * @throws FileNotFoundException : file not found 
	 */
	public List<RowInfo> ParseCSV(String fileName, LocalDate startDate, LocalDate endDate) throws FileNotFoundException {
		
		List<RowInfo> ParsedCSV = new ArrayList<>();
		Scanner scanner = new Scanner(new File(fileName));
		scanner.useDelimiter(",");
		if(scanner.hasNext()){
			//skip the first line
			if(endDate.isBefore(startDate)) {
				System.out.print("End Date cannot be prior to Start Date. Please retry.");
				System.exit(0);
			}
			scanner.nextLine();
			while (scanner.hasNext()) {
				String passLine = scanner.nextLine();
				RowInfo row = parseRow(passLine);
			
				LocalDate localDate = row.getDate();
				double price = row.getPrice();
				
				if(localDate.isEqual(startDate) || (localDate.isAfter(startDate) && 
						localDate.isBefore(endDate)) || localDate.isEqual(endDate)) {
					//make RowInfo object and set it
					//RowInfo oneLine = new RowInfo();
					row.setDate(localDate);
					row.setPrice(price);
					//add to ParsedCSV list
					ParsedCSV.add(row);	
					
				}
				if(localDate.isEqual(endDate) || localDate.isAfter(endDate)) {
					break;
				}
						
			}
		}
			
		scanner.close();
		return ParsedCSV;
	}
	/**
	 * returns a row information object containing 
	 * price and date of stock
	 * @param passLine : the String line of input file
	 * @return row : a row information object
	 */
	public static RowInfo parseRow(String passLine) {
		Scanner sc = new Scanner(passLine);
		sc.useDelimiter(",");
		RowInfo row = new RowInfo();
		
		while(sc.hasNext()) {
			String colPrice = sc.next();
			double price = Double.parseDouble(colPrice);
			String date = sc.next();
			//String date = "2014-01-24";
			LocalDate localDate = LocalDate.parse(date);
			row.setPrice(price);
			row.setDate(localDate);
			
		}
		//cs.ncsu.hackathon.risk.graphics
		//cs.ncsu.hackathon.risk.io
		// cs.ncsu.hackathon.risk.manager
		sc.close();
		return row;
	}

}