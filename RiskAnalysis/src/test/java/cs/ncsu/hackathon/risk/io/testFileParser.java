package cs.ncsu.hackathon.risk.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
/**
 * 
 * @author adithyakasali
 *
 */
public class testFileParser {
	/** test the input reader*/
	@Test
	public void testParseCSV() {
		String date1 = "2014-04-01";
		String date2 = "2014-04-03";
		LocalDate startDate = LocalDate.parse(date1);
		LocalDate endDate = LocalDate.parse(date2);
		List<RowInfo> info = null;
		FileParser parser = new FileParser();
		try {
			info = parser.ParseCSV("test/input1.csv", startDate, endDate);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(96.24, info.get(0).getPrice(), 0);
		assertEquals(45.24, info.get(1).getPrice(), 0);
		assertEquals(12.24, info.get(2).getPrice(), 0);
	}

}
