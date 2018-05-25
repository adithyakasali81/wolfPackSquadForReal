/**
 * 
 */
package cs.ncsu.hackathon.risk.io;

import java.time.LocalDate;

/**
 * @author Yussef Guerrab
 *
 */
public class RowInfo {
	/** date as a localDate object*/
	private LocalDate date;
	/** price of Stock */
	private double price;
	/**
	 * constructor for Row information with out date and price
	 */
	public RowInfo() {
		
	}
	/**
	 * constructor for Row information
	 * @param date : date a LocalDate
	 * @param price : the price of stock
	 */
	public RowInfo(LocalDate date, double price) {
		this.date = date;
		this.price = price;
	}
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
}