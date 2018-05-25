package cs.ncsu.hackathon.risk.io;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * 
 * @author adithyakasali
 *
 */
public class testRowInfo {
	/** test the RowInfo constructor, setters and getters */
	@Test
	public void testConstructor() {	
		String date1 = "2014-04-01";
		String date2 = "2014-04-03";
		LocalDate localDate1 = LocalDate.parse(date1);
		LocalDate localDate2 = LocalDate.parse(date2);
		RowInfo info = new RowInfo(localDate1, 96.24);
		
		assertEquals(96.24, info.getPrice(), 0);
		assertEquals(localDate1, info.getDate());
		
		info.setDate(localDate2);
		assertEquals(localDate2, info.getDate());
		
		info.setPrice(45.24);
		assertEquals(45.24, info.getPrice(), 0);
	}

}
