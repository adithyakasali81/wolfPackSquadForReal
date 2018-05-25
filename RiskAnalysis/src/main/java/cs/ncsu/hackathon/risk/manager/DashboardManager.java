/**
 * 
 */
package cs.ncsu.hackathon.risk.manager;

import java.io.FileNotFoundException;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

import cs.ncsu.hackathon.risk.graphics.*;
import cs.ncsu.hackathon.risk.io.FileParser;
import cs.ncsu.hackathon.risk.io.RowInfo;
/**
 * @author Yussef Guerrab and Adithya Kasali
 *
 */
public class DashboardManager {

	/**
	 * execute program
	 * @param args : command line arguments
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please input security name: ");
		String companyName = scanner.next();

		System.out.print("Please enter start date (in the format of yyyy-mm-dd: ");
		String dateOne = scanner.next();

		System.out.print("Please enter end date (in the format of yyyy-mm-dd: ");
		String dateTwo = scanner.next();

		LocalDate startDate = LocalDate.parse(dateOne);
		LocalDate endDate = LocalDate.parse(dateTwo);

		FileParser fileParser = new FileParser();
		List<RowInfo> graphList = fileParser.ParseCSV("src/main/resources/" + companyName + ".csv", startDate, endDate);
		//level 1 - plot the the time series graph
		
		String title = "Time Series Chart";
		TimeSeriesMaker timeSeriesMaker = new TimeSeriesMaker(title,startDate, endDate, companyName, graphList);
		timeSeriesMaker.makeTimeSeriesGraph(startDate, endDate, companyName, graphList);
		//level 2 - plot the the time series graph
		Operations operations = new Operations();
		List<Double> values = operations.calculateValueAtRisk(graphList);
		Iterator<Double> iterator = values.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		//level 3 - plot the histogram frequency vs daily returns
		Map<Integer, Integer> map = operations.calculateFrequencyOverDailyReturn(values);
		HistogramMaker histogramMaker = new HistogramMaker("Histogram", map);
		histogramMaker.pack();
        RefineryUtilities.centerFrameOnScreen(histogramMaker);
        histogramMaker.setVisible(true);
        //level 4 - give the option for the user to put in a time frame
        //this what completed incorporated in level 1
		
	}

}