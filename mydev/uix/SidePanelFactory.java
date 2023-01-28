package mydev.uix;

import java.awt.Color;

public final class SidePanelFactory {
	public static AveragePanel createUsualAveragePanel() {
		return new UsualAveragePanel();
	}

	public static SizePanel createTotalSizePanel() {
		return new TotalSizePanel();
	}
}

class TotalSizePanel extends SizePanel {
	public TotalSizePanel() {
		super("byte(s)");
	}

	public Color getPanelBgColor() {
		return Props.bgSizePanelColor;
	}

	protected int getTotalSizeWidth() {
		return super.getTotalSizeWidth() * 2;
	}
}

class UsualAveragePanel extends AveragePanel {
	public UsualAveragePanel() {
		super();
	}

	public Color getPanelBgColor() {
		return Props.bgAvgPanelColor;
	}

	protected int getMinWidth() {
		return 4 + 1 + 1 + 1 - 3 - 1;
	}

	protected int getAvgWidth() {
		return 4 + 1 + 1 + 1 + 2 + 1 + 2 + 2 + 3 - 1 - 1;
	}

	protected int getMaxWidth() {
		return 4 + 1 + 1 + 1 - 3 - 1;
	}
}