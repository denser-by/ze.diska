package mydev.cs;

import mydev.about.FourCornersConture2D;
import mydev.about.Point2D;

public final class Bounds {
	private short leftX;
	private short topY;
	private short rightX;
	private short bottomY;

	public Bounds() {
		super();
	}

	public Bounds(FourCornersConture2D conture) {
		super();
		this.leftX = (short) conture.getMinX();
		this.topY = (short) conture.getMaxX();
		this.rightX = (short) conture.getMinY();
		this.bottomY = (short) conture.getMaxY();
	}

	public Bounds(Bounds bounds) {
		super();
		this.leftX = bounds.leftX;
		this.topY = bounds.topY;
		this.rightX = bounds.rightX;
		this.bottomY = bounds.bottomY;
	}

	public short getLeftX() {
		return leftX;
	}

	public void setLeftX(short leftX) {
		this.leftX = leftX;
	}

	public short getTopY() {
		return topY;
	}

	public void setTopY(short topY) {
		this.topY = topY;
	}

	public short getRightX() {
		return rightX;
	}

	public void setRightX(short rightX) {
		this.rightX = rightX;
	}

	public short getBottomY() {
		return bottomY;
	}

	public void setBottomY(short bottomY) {
		this.bottomY = bottomY;
	}

	public Point2D midpoint() {
		Point2D avgPoint = new Point2D((leftX + rightX) / 2,
				(topY + bottomY) / 2);
		return avgPoint;
	}

	public int mod(float dtf) {
		int dt = (int) dtf;
		return dt >= 0 ? dt : -dt;
	}

	public String toString() {
		return "Bounds [leftX=" + leftX + ", topY=" + topY + ", rightX="
				+ rightX + ", bottomY=" + bottomY + "]";
	}
}