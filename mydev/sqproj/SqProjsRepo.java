package mydev.sqproj;

import java.awt.Color;

public final class SqProjsRepo {
	private SqProj dist;
	private SqProj ceiling;
	private SqProj right;
	private SqProj floor;
	private SqProj left;
	private SqProj near;
	private SqProjStripe stripeX;
	private SqProjStripe stripeY;
	private SqProjStripe stripeZ;
	private SqProjBulk bulkX;
	private SqProjBulk bulkY;
	private SqProjBulk bulkZ;

	public SqProjsRepo(int color) {
		super();
		init(color);
	}

	protected void init(int color) {
		this.dist = new SqProj(color);
		this.ceiling = new SqProj(color);
		this.right = new SqProj(color);
		this.floor = new SqProj(color);
		this.left = new SqProj(color);
		this.near = new SqProj(color);
		this.stripeX = new SqProjStripe(color);
		this.stripeY = new SqProjStripe(color);
		this.stripeZ = new SqProjStripe(color);
		this.bulkX = new SqProjBulk(color);
		this.bulkY = new SqProjBulk(color);
		this.bulkZ = new SqProjBulk(color);
	}

	public void clear(Color color) {
		init(color.getRGB());
	}

	public SqProj getLeft() {
		return left;
	}

	public SqProj getRight() {
		return right;
	}

	public SqProj getCeiling() {
		return ceiling;
	}

	public SqProj getFloor() {
		return floor;
	}

	public SqProj getDist() {
		return dist;
	}

	public SqProj getNear() {
		return near;
	}

	public SqProjStripe getStripeX() {
		return stripeX;
	}

	public SqProjStripe getStripeY() {
		return stripeY;
	}

	public SqProjStripe getStripeZ() {
		return stripeZ;
	}

	public SqProjBulk getBulkX() {
		return bulkX;
	}

	public SqProjBulk getBulkY() {
		return bulkY;
	}

	public SqProjBulk getBulkZ() {
		return bulkZ;
	}

	public String toString() {
		return "SqProjsRepo [dist=" + dist + ", ceiling=" + ceiling
				+ ", right=" + right + ", floor=" + floor + ", left=" + left
				+ ", near=" + near + ", stripeX=" + stripeX + ", stripeY="
				+ stripeY + ", stripeZ=" + stripeZ + ", bulkX=" + bulkX
				+ ", bulkY=" + bulkY + ", bulkZ=" + bulkZ + "]";
	}
}