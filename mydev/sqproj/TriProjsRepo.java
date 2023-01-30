package mydev.sqproj;

import java.awt.Color;

public final class TriProjsRepo {
	private TriProj dist;
	private TriProj ceiling;
	private TriProj right;
	private TriProj floor;
	private TriProj left;
	private TriProj near;
	private TriProjBulk bulkX;
	private TriProjBulk bulkY;
	private TriProjBulk bulkZ;

	public TriProjsRepo(Color color) {
		super();
		init(color);
	}

	protected void init(Color color) {
		this.dist = new TriProj(color);
		this.ceiling = new TriProj(color);
		this.right = new TriProj(color);
		this.floor = new TriProj(color);
		this.left = new TriProj(color);
		this.near = new TriProj(color);
		this.bulkX = new TriProjBulk(color);
		this.bulkY = new TriProjBulk(color);
		this.bulkZ = new TriProjBulk(color);
	}

	public void clear(Color color) {
		init(color);
	}

	public TriProj getDist() {
		return dist;
	}

	public TriProj getCeiling() {
		return ceiling;
	}

	public TriProj getRight() {
		return right;
	}

	public TriProj getFloor() {
		return floor;
	}

	public TriProj getLeft() {
		return left;
	}

	public TriProj getNear() {
		return near;
	}

	public TriProjBulk getBulkX() {
		return bulkX;
	}

	public TriProjBulk getBulkY() {
		return bulkY;
	}

	public TriProjBulk getBulkZ() {
		return bulkZ;
	}

	public String toString() {
		return "TriProjsRepo [dist=" + dist + ", ceiling=" + ceiling
				+ ", right=" + right + ", floor=" + floor + ", left=" + left
				+ ", near=" + near + ", bulkX=" + bulkX + ", bulkY=" + bulkY
				+ ", bulkZ=" + bulkZ + "]";
	}
}