package mydev.uix;

import java.awt.Scrollbar;
import mydev.about.PieChart2D;
import mydev.about.PieChartLegend2D;
import mydev.about.Point2D;

public final class PieChartUnity {
	private PieChart2D pieChart;
	private PieChartLegend2D pieChartLegend;

	public PieChartUnity() {
		super();
	}

	public PieChart2D getPieChart() {
		return pieChart;
	}

	public PieChart2D obtainPieChart(int centerX, int centerY, int r) {
		pieChart = new PieChart2D(new Point2D(centerX, centerY), r,
				Props.bgChartColor.getRGB(), false);
		if (pieChart.getLegend() == null && pieChartLegend != null)
			pieChart.setLegend(pieChartLegend);
		return pieChart;
	}

	public PieChartLegend2D getPieChartLegend() {
		return pieChartLegend;
	}

	public PieChartLegend2D obtainPieChartLegend(int width, int height,
			Scrollbar vScroll) {
		pieChartLegend = new PieChartLegend2D(new Point2D(0, 0), width, height,
				Props.bgChartColor.getRGB(), Props.textPieChartLegendColor,
				vScroll);
		if (pieChart != null && pieChart.getLegend() == null)
			pieChart.setLegend(pieChartLegend);
		return pieChartLegend;
	}
}