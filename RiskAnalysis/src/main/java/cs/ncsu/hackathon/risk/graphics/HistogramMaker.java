package cs.ncsu.hackathon.risk.graphics;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

/**
 * A HistogramMaker class
 * @author adithyakasali
 *
 */
public class HistogramMaker extends ApplicationFrame {

    /**
	 * Serial VersionUID needed seince Application Frame
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * makein a new panel with a histogram
	 * @param title : the title of the Histogram
	 * @param map : the map containing Daily Returns vs Frequency
	 */
    public HistogramMaker(final String title, Map<Integer, Integer> map ) {
        super(title);
        IntervalXYDataset dataset = createDataset(map);
        JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private IntervalXYDataset createDataset(Map<Integer, Integer> map) {
    	
        final XYSeries series = new XYSeries("Frequency");
        for (Entry<Integer,Integer> pair : map.entrySet()){
        	series.add(pair.getKey(), pair.getValue());
        }
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    /**
     * Creates a sample chart.
     * 
     * @param dataset : the dataset.
     * 
     * @return A sample chart.
     */
    private JFreeChart createChart(IntervalXYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYBarChart(
            "Daily Returns VS Frequency",
            "Daily Returns", 
            false,
            "Frequency", 
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        final IntervalMarker target = new IntervalMarker(400.0, 700.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);
        return chart;    
    }

}