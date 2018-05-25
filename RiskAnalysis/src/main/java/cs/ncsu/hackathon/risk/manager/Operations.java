/**
 * 
 */
package cs.ncsu.hackathon.risk.manager;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs.ncsu.hackathon.risk.io.RowInfo;


/**
 * @author Yussef Guerrab
 *
 */
public class Operations {
	/**
	 * return map of daily return vs frequency
	 * given a list of var values
	 * @param values : the unsorted var values
	 * @return map of daily return vs frequency
	 */
	public Map<Integer, Integer> calculateFrequencyOverDailyReturn(List<Double> values) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> tempList = new ArrayList<>();

		for (double value : values) {
			int number = (int) Math.round(value);
			tempList.add(number);
		}
		Set<Integer> set = new HashSet<Integer>(tempList);

		for (Integer tmp : set) {
			map.put(tmp, Collections.frequency(tempList, tmp));
		}

		return map;
	}
	/**
	 * return a Arraylist of var values
	 * given a list of row information
	 * @param list : list of Row information
	 * @return a Arraylist of var values
	 */
	public ArrayList<Double> calculateValueAtRisk(List<RowInfo> list) {

		ArrayList<Double> var = new ArrayList<Double>();
		Iterator<RowInfo> iter = list.iterator();
		double p0;
		double p1;
		double calcVar;
		RowInfo row;

		if (iter.hasNext()) {
			RowInfo oneRow = iter.next();
			p0 = oneRow.getPrice();

			if (iter.hasNext()) {
				row = iter.next();
				p1 = row.getPrice();

				calcVar = Math.log(p1 / p0) * 100;
				var.add(calcVar);

				while (iter.hasNext()) {
					p0 = p1;
					row = iter.next();
					p1 = row.getPrice();
					calcVar = Math.log(p1 / p0) * 100;
					var.add(calcVar);
					if (!iter.hasNext()) {
						break;
					}
				}
			}
		}

		return var;

	}

}