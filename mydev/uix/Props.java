package mydev.uix;

import java.awt.Color;

import mydev.about.ColorsArray;

public class Props {
	public static final Color objRibs = new Color(248, 186, 111);
	public static final Color bgTopColor = objRibs;
	public static final Color bgDownColor = Color.lightGray;
	public static final Color bgIgnoreColor = new Color(
			mydev.about.Color.middleColor(ColorsArray.yellowPoint,
					ColorsArray.whitePoint, ColorsArray.whitePoint,
					ColorsArray.whitePoint));
	public static final Color bgSideColor = Color.white;
	public static final Color borderProgressColor = Color.blue;
	public static final Color bgProgressColor = Color.yellow;
	public static final Color mainProgressColor = Color.magenta;
	public static final Color borderProgressTotalColor = Color.green;
	public static final Color bgProgressTotalColor = Color.white;
	public static final Color mainProgressTotalColor = Color.green;
	public static final Color bgLogPanelColor = Color.green;
	public static final Color bgCtrlPanelColor = Color.white;
	public static final Color bgAvgPanelColor = Color.white;
	public static final Color bgStatPanelColor = Color.lightGray;
	public static final Color bgSizePanelColor = bgStatPanelColor;
	public static final Color bgAmountPanelColor = bgSizePanelColor;
	public static final Color northBorderColor = bgIgnoreColor;
	public static final Color northBorderColor2 = new Color(
			mydev.about.Color.middleColor(ColorsArray.bluePoint,
					ColorsArray.whitePoint));
	public static final Color northTextColor = northBorderColor;
	public static final Color northBgColor = bgTopColor;
	public static final Color northContentSplitterColor = Color.blue;
	public static final Color splitParallelPanelColor = Color.white;
	public static final int msecAnimationRefresh = 750;
	public static final Color bgSouthPanelColor = bgSideColor;
	public static final int animationSize = 100;
	public static final Color bgLogoColor = bgSideColor;
	public static final Color borderLogoColor = bgTopColor;
	public static final Color bgChartColor = Color.white;
	public static final int textPieChartLegendColor = Color.black.getRGB();
	public static final Color textChartPanelColor = Color.black;
	public static final Color bgChartPanelColor = Color.white;
	public static final Color fgChartPanelColor = bgSizePanelColor;

	public static final Color bgParallelColor = Color.white;
	public static final Color bgParallelWrapColor = bgParallelColor;
	public static final Color borderParallelWrapColor = bgParallelColor;
	public static final Color textParallelWrapColor = Color.black;

}