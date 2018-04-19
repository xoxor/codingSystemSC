package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYSeriesDemo extends ApplicationFrame {

	public XYSeriesDemo(String title, String[] seriesName, double[][] x, double[][] y) {
		super(title);
		XYSeriesCollection result = new XYSeriesCollection();
		for (int s = 0; s < seriesName.length; s++) {
			XYSeries series = new XYSeries(seriesName[s]);
			for (int i = 0; i < x[0].length; i++) {
				series.add(x[s][i], y[s][i]);
			}
			result.addSeries(series);
		}

		JFreeChart chart = ChartFactory.createXYLineChart("XY Series Demo", "X", "Y", result, PlotOrientation.VERTICAL,
				true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

	public static void main(final String[] args) {
		String title = "Prova";
		String[] seriesName = { "s1", "s2" };
		double x[][] = new double[seriesName.length][10];
		double y[][] = new double[seriesName.length][10];
		for (int i = 0; i < seriesName.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				x[i][j] = Math.random();
				y[i][j] = Math.random();
			}
		}
		XYSeriesDemo demo = new XYSeriesDemo(title, seriesName, x, y);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}