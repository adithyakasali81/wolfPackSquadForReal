/**
 * 
 */
package cs.ncsu.hackathon.risk.graphics;

import java.awt.Color;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import cs.ncsu.hackathon.risk.io.RowInfo;

/**
 * @author Yussef Guerrab
 *
 */

public class TimeSeriesMaker extends JFrame {
	/** the serial Version UID need since TimeSeries Maker extends JFrame*/
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Graphs a Times Series (time vs closing price of stock)
	 * @param title : title of the Time Series chart
	 * @param startDate : the start date as Local Date
	 * @param endDate : the end date as Local Date
	 * @param companyName : the name of the company
	 * @param graphList : the list of row information
	 * @throws FileNotFoundException : if file is not found
	 */
	public TimeSeriesMaker(String title, LocalDate startDate, LocalDate endDate, String companyName,
			List<RowInfo> graphList) throws FileNotFoundException {
		super(title);
		// Create dataset
		XYDataset dataset = graphDataset(startDate, endDate, companyName, graphList);
		// Create chart
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Time Series", // Chart
				"Dates", // X-Axis Label
				"Closing Price", // Y-Axis Label
				dataset);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(255, 228, 196));

		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);

	}
	/**
	 * return a XY data set to be graphed
	 * @param startDate2 : start date as localDate object
	 * @param endDate2 : end date as localDate object
	 * @param companyName : name of the company
	 * @param graphList : list of row information objects
	 * @return a XY Data set to be graphed
	 * @throws FileNotFoundException : if input file is not found
	 */
	public XYDataset graphDataset(LocalDate startDate2, LocalDate endDate2, String companyName, List<RowInfo> graphList)
			throws FileNotFoundException {
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		TimeSeries plotPoints = new TimeSeries("Time Series");

		Iterator<RowInfo> iter = graphList.iterator();

		while (iter.hasNext()) {
			RowInfo row = iter.next();
			LocalDate date = row.getDate();
			double price = row.getPrice();
			// extract info from date object into int data types
			int day = date.getDayOfMonth();
			int month = date.getMonthValue();
			int year = date.getYear();
			plotPoints.add(new Day(day, month, year), price);
		}
		dataset.addSeries(plotPoints);

		return dataset;
	}
	/**
	 * Graphs a times series onto panel
	 * @param startDate : start date as localDate object
	 * @param endDate : end date as localDate object
	 * @param companyName : company name
	 * @param graphList : a list of row information
	 * @throws FileNotFoundException
	 */
	public void makeTimeSeriesGraph(LocalDate startDate, LocalDate endDate, String companyName,
			List<RowInfo> graphList) throws FileNotFoundException {

		TimeSeriesMaker timeSeriesMaker = new TimeSeriesMaker("Time Series Chart", startDate, endDate, companyName,
				graphList);
		timeSeriesMaker.setSize(800, 400);
		timeSeriesMaker.setLocationRelativeTo(null);
		timeSeriesMaker.setVisible(true);
		timeSeriesMaker.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

}