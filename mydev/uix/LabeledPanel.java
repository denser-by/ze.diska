package mydev.uix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Panel;
import mydev.about.Canvas;
import mydev.about.Line2D;
import mydev.about.Paint;
import mydev.about.Point2D;
import mydev.about.Vector3;

public final class LabeledPanel extends Panel {
	public static final Color DEF_BG_COLOR = Props.bgCtrlPanelColor;
	public static final Color DEF_BORDER_COLOR = Color.black;
	public static final Color DEF_TEXT_COLOR = Color.black;
	public static final int DEF_MIN_SIZE = 5 + 5 + 5;
	public static final int DEF_WRAP_SIZE = 5 - 1 - 1;
	private String panelName;
	private int minSize;
	private Color bgColor;
	private Component wrappedComponent;
	private WestCanvas westSide;
	private NorthCanvas northSide;
	private EastCanvas eastSide;
	private SouthCanvas southSide;

	public LabeledPanel(String panelName, Component wrappedComponent) {
		this(panelName, wrappedComponent, DEF_MIN_SIZE, DEF_BG_COLOR,
				DEF_BORDER_COLOR, DEF_TEXT_COLOR, true, true, true, true);
	}

	public LabeledPanel(String panelName, Component wrappedComponent,
			int minSize, Color bgColor, Color borderColor, Color textColor) {
		this(panelName, wrappedComponent, minSize, bgColor, borderColor,
				textColor, true, true, true, true);
	}

	public LabeledPanel(String panelName, Component wrappedComponent,
			int minSize, Color bgColor, Color borderColor, Color textColor,
			boolean left, boolean top, boolean right, boolean bottom) {
		super();
		setBackground(bgColor);
		setLayout(new BorderLayout());
		this.wrappedComponent = wrappedComponent;
		this.panelName = panelName;
		this.minSize = minSize;
		this.bgColor = bgColor;
		add("Center", wrappedComponent);
		if (top)
			add("North", northSide = new NorthCanvas(panelName, minSize,
					bgColor, borderColor, textColor));
		if (bottom)
			add("South", southSide = new SouthCanvas(minSize, bgColor,
					borderColor));
		if (left)
			add("West",
					westSide = new WestCanvas(minSize, bgColor, borderColor));
		if (right)
			add("East",
					eastSide = new EastCanvas(minSize, bgColor, borderColor));
	}

	public Font getFont() {
		if (northSide != null)
			return northSide.getFont2();
		return null;
	}

	public void setFont(Font font) {
		if (northSide != null)
			northSide.setFont2(font);
	}

	public int getMinSize() {
		return minSize;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public String getPanelName() {
		return panelName;
	}

	public Color getBorderColor() {
		if (northSide != null)
			return northSide.getBorderColor();
		if (southSide != null)
			return southSide.getBorderColor();
		if (westSide != null)
			return westSide.getBorderColor();
		if (eastSide != null)
			return eastSide.getBorderColor();
		return null;
	}

	public void setBorderColor(Color borderColor) {
		if (northSide != null)
			northSide.setBorderColor(borderColor);
		if (southSide != null)
			southSide.setBorderColor(borderColor);
		if (westSide != null)
			westSide.setBorderColor(borderColor);
		if (eastSide != null)
			eastSide.setBorderColor(borderColor);
	}

	public Color getTextColor() {
		if (northSide != null)
			return northSide.getTextColor();
		return null;
	}

	public void setTextColor(Color textColor) {
		if (northSide != null)
			northSide.setTextColor(textColor);
	}

	public Component getWrappedComponent() {
		return wrappedComponent;
	}

	public void refreshUpdatable() {
		if (northSide != null)
			northSide.refreshUpdatable();
		if (southSide != null)
			southSide.refreshUpdatable();
		if (westSide != null)
			westSide.refreshUpdatable();
		if (eastSide != null)
			eastSide.refreshUpdatable();
	}
}

abstract class BorderCanvas extends CommonCanvas {
	protected int minSize;
	protected Color bgColor;
	protected Color borderColor;

	public BorderCanvas(int minSize, Color bgColor, Color borderColor) {
		super();
		setBackground(bgColor);
		this.minSize = minSize;
		this.bgColor = bgColor;
		this.borderColor = borderColor;
		resize(minSize, minSize);
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getMinSize() {
		return minSize;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void clearItems() {
	}
}

class NorthCanvas extends BorderCanvas {
	private String ctrlName;
	private Color textColor;
	private Font font2;
	private Line2D line;
	private Line2D line2;
	private Line2D line3;

	public NorthCanvas(String ctrlName, int minSize, Color bgColor,
			Color borderColor, Color textColor) {
		super(minSize, bgColor, borderColor);
		this.ctrlName = ctrlName;
		this.textColor = textColor;
	}

	public void setFont2(Font font) {
		this.font2 = font;
	}

	public Font getFont2() {
		return font2;
	}

	protected void drawItems(Canvas ics, Paint pn) {
		line.toPen(ics, pn.select(line));
		Vector3.DY1m.getShiftedCopy(line).toPen(ics, pn);
		line2.toPen(ics, pn.select(line2));
		Vector3.DX1m.getShiftedCopy(line2).toPen(ics, pn);
		line3.toPen(ics, pn.select(line3));
		Vector3.DX1.getShiftedCopy(line3).toPen(ics, pn);
	}

	protected void newSizeArrive(short width, short height) {
		super.newSizeArrive(width, height);
		this.line = new Line2D(new Point2D(sCur.height / 2, sCur.height / 2,
				borderColor.getRGB()), new Point2D(sCur.width - sCur.height / 2
				- 1, sCur.height / 2, borderColor.getRGB()),
				borderColor.getRGB());
		this.line2 = new Line2D(new Point2D(sCur.height / 2, sCur.height / 2,
				borderColor.getRGB()), new Point2D(sCur.height / 2,
				sCur.height, borderColor.getRGB()), borderColor.getRGB());
		this.line3 = new Line2D(new Point2D(sCur.width - sCur.height / 2 - 1,
				sCur.height / 2, borderColor.getRGB()), new Point2D(sCur.width
				- sCur.height / 2 - 1, sCur.height, borderColor.getRGB()),
				borderColor.getRGB());
	}

	public void paint(Graphics ics) {
		super.paint(ics);
		if (font2 == null) {
			Font font = ics.getFont();
			font = new Font(font.getName(), Font.BOLD,
					font.getSize() + 1 + 1 - 1);
			ics.setFont(font);
			font = font;
		} else
			ics.setFont(font2);
		FontMetrics fm = ics.getFontMetrics();
		int th = fm.getHeight();
		int tw = fm.stringWidth(ctrlName);
		int dt = 3;
		ics.setColor(bgColor);
		ics.fillRect(sCur.height * 2 - dt, sCur.height - th - dt, tw + dt * 2,
				th);
		ics.setColor(textColor);
		ics.drawString(ctrlName, sCur.height * 2, sCur.height - dt);
	}

	public void setCtrlName(String ctrlName) {
		this.ctrlName = ctrlName;
	}

	public String getCtrlName() {
		return ctrlName;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
}

class SouthCanvas extends BorderCanvas {
	private Line2D line;
	private Line2D line2;
	private Line2D line3;

	public SouthCanvas(int minSize, Color bgColor, Color borderColor) {
		super(minSize, bgColor, borderColor);
	}

	protected void drawItems(Canvas ics, Paint pn) {
		line.toPen(ics, pn.select(line));
		Vector3.DY1.getShiftedCopy(line).toPen(ics, pn);
		line2.toPen(ics, pn.select(line2));
		Vector3.DX1m.getShiftedCopy(line2).toPen(ics, pn);
		line3.toPen(ics, pn.select(line3));
		Vector3.DX1.getShiftedCopy(line3).toPen(ics, pn);
	}

	protected void newSizeArrive(short width, short height) {
		super.newSizeArrive(width, height);
		this.line = new Line2D(new Point2D(sCur.height / 2, sCur.height / 2,
				borderColor.getRGB()), new Point2D(sCur.width - sCur.height / 2
				- 1, sCur.height / 2, borderColor.getRGB()),
				borderColor.getRGB());
		this.line2 = new Line2D(new Point2D(sCur.height / 2, sCur.height / 2,
				borderColor.getRGB()), new Point2D(sCur.height / 2, 0,
				borderColor.getRGB()), borderColor.getRGB());
		this.line3 = new Line2D(new Point2D(sCur.width - sCur.height / 2 - 1,
				sCur.height / 2, borderColor.getRGB()), new Point2D(sCur.width
				- sCur.height / 2 - 1, 0, borderColor.getRGB()),
				borderColor.getRGB());
	}
}

class EastCanvas extends BorderCanvas {
	private Line2D line;

	public EastCanvas(int minSize, Color bgColor, Color borderColor) {
		super(minSize, bgColor, borderColor);
	}

	protected void drawItems(Canvas ics, Paint pn) {
		line.toPen(ics, pn.select(line));
		Vector3.DX1.getShiftedCopy(line).toPen(ics, pn);
	}

	protected void newSizeArrive(short width, short height) {
		super.newSizeArrive(width, height);
		this.line = new Line2D(new Point2D(sCur.width / 2, 0,
				borderColor.getRGB()), new Point2D(sCur.width / 2, sCur.height,
				borderColor.getRGB()), borderColor.getRGB());
	}
}

class WestCanvas extends BorderCanvas {
	private Line2D line;

	public WestCanvas(int minSize, Color bgColor, Color borderColor) {
		super(minSize, bgColor, borderColor);
	}

	protected void drawItems(Canvas ics, Paint pn) {
		line.toPen(ics, pn.select(line));
		Vector3.DX1m.getShiftedCopy(line).toPen(ics, pn);
	}

	protected void newSizeArrive(short width, short height) {
		super.newSizeArrive(width, height);
		this.line = new Line2D(new Point2D(sCur.width / 2, 0,
				borderColor.getRGB()), new Point2D(sCur.width / 2, sCur.height,
				borderColor.getRGB()), borderColor.getRGB());
	}
}