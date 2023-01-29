package mydev.csprofile;

public final class VisibilityProfile {
	private boolean farSide;
	private boolean wallTop;
	private boolean rightSide;
	private boolean wallDown;
	private boolean leftSide;
	private boolean frontSide;

	public static VisibilityProfile create() {
		return new VisibilityProfile();
	}

	protected VisibilityProfile() {
		super();
	}

	public boolean isFar() {
		return farSide;
	}

	public VisibilityProfile farSide(boolean farSide) {
		this.farSide = farSide;
		return this;
	}

	public boolean isTop() {
		return wallTop;
	}

	public VisibilityProfile wallTop(boolean wallTop) {
		this.wallTop = wallTop;
		return this;
	}

	public boolean isRight() {
		return rightSide;
	}

	public VisibilityProfile rightSide(boolean rightSide) {
		this.rightSide = rightSide;
		return this;
	}

	public boolean isDown() {
		return wallDown;
	}

	public VisibilityProfile wallDown(boolean wallDown) {
		this.wallDown = wallDown;
		return this;
	}

	public boolean isLeft() {
		return leftSide;
	}

	public VisibilityProfile leftSide(boolean leftSide) {
		this.leftSide = leftSide;
		return this;
	}

	public boolean isFront() {
		return frontSide;
	}

	public VisibilityProfile frontSide(boolean frontSide) {
		this.frontSide = frontSide;
		return this;
	}

	public VisibilityProfile visible() {
		this.farSide = true;
		this.wallTop = true;
		this.rightSide = true;
		this.wallDown = true;
		this.leftSide = true;
		this.frontSide = true;
		return this;
	}

	public VisibilityProfile inVisibility() {
		this.farSide = false;
		this.wallTop = false;
		this.rightSide = false;
		this.wallDown = false;
		this.leftSide = false;
		this.frontSide = false;
		return this;
	}

	public String toString() {
		return "VisibilityProfile [farSide=" + farSide + ", wallTop=" + wallTop
				+ ", rightSide=" + rightSide + ", wallDown=" + wallDown
				+ ", leftSide=" + leftSide + ", frontSide=" + frontSide + "]";
	}
}