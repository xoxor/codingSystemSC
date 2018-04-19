package gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing a line chart drawn using spline curves.
 */
public class XYSplineRendererDemo extends JFrame {

	public XYSplineRendererDemo(String title, String[] series, double[][] x, double[][] y, String xAx) {
		super(title);
		JPanel content = null;

		// create plot...
		NumberAxis xAxis = new NumberAxis(xAx);
		xAxis.setAutoRangeIncludesZero(false);
		NumberAxis yAxis = new NumberAxis("BER(Bit Error Ratio)");
		yAxis.setAutoRangeIncludesZero(false);

		XYSplineRenderer renderer1 = new XYSplineRenderer();
		//renderer1.setPrecision(3);
		XYPlot plot = new XYPlot(getData(series, x, y), xAxis, yAxis, renderer1);
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		// plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

		// create and return the chart panel...
		JFreeChart chart = new JFreeChart("Plot", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		ChartUtilities.applyCurrentTheme(chart);
		ChartPanel chartPanel = new ChartPanel(chart);
		content = chartPanel;

		// content.setPreferredSize(new java.awt.Dimension(500, 270));
		getContentPane().add(content);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private static XYDataset getData(String[] seriesName, double[][] x, double[][] y) {
		XYSeriesCollection result = new XYSeriesCollection();
		for (int s = 0; s < seriesName.length; s++) {
			XYSeries series = new XYSeries(seriesName[s]);
			for (int i = 0; i < x[0].length; i++) {
				series.add(x[s][i], y[s][i]);
			}
			result.addSeries(series);
		}
		return result;
	}

	public static void main(String args[]) {
		String title = "Prova";
		String[] seriesName = { "s1", "s2","s3" };
		double x[][] = new double[seriesName.length][10];
		double y[][] = new double[seriesName.length][10];
		for (int i = 0; i < seriesName.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				x[i][j] = Math.random();
				y[i][j] = Math.random();
			}
		}

		XYSplineRendererDemo appFrame = new XYSplineRendererDemo(title, seriesName, x, y,"X");
		appFrame.pack();
		RefineryUtilities.centerFrameOnScreen(appFrame);
		appFrame.setVisible(true);
		appFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}