package mydev.im12x;

public final class BackupResolution {
	public static final String BACKUP_SAME = "Same as originals";
	public static final String BACKUP_NEW_RESOLUT = "New resolution";
	private String resolutionSaveMode;
	private String width;
	private String height;
	private short cutLeft;
	private short cutTop;
	private short cutRight;
	private short cutBottom;

	public BackupResolution(String resolutionSaveMode, String width,
			String height) {
		this.resolutionSaveMode = resolutionSaveMode;
		this.width = width;
		this.height = height;
	}

	public BackupResolution setCutSides(int cutLeft, int cutTop, int cutRight,
			int cutBottom) {
		this.cutLeft = (short) cutLeft;
		this.cutTop = (short) cutTop;
		this.cutRight = (short) cutRight;
		this.cutBottom = (short) cutBottom;
		return this;
	}

	public String getResolutionSaveMode() {
		return resolutionSaveMode;
	}

	public String getWidth() {
		return width;
	}

	public String getHeight() {
		return height;
	}

	public static BackupResolution createNoAction() {
		BackupResolution result = new BackupResolution(BACKUP_SAME, "", "");
		return result;
	}

	public short getCutLeft() {
		return cutLeft;
	}

	public short getCutTop() {
		return cutTop;
	}

	public short getCutRight() {
		return cutRight;
	}

	public short getCutBottom() {
		return cutBottom;
	}

	public boolean needsConversion() {
		if (cutLeft + cutTop + cutRight + cutBottom > 0)
			return true;
		return resolutionSaveMode != null
				&& !resolutionSaveMode.equalsIgnoreCase(BACKUP_SAME);
	}

	public short getNewWidth(int currentWidth) {
		if (width != null && width.length() > 0)
			return (short) Integer.parseInt(width);
		return (short) currentWidth;
	}

	public short getNewHeight(int currentHeight) {
		if (height != null && height.length() > 0)
			return (short) Integer.parseInt(height);
		return (short) currentHeight;
	}

	public String toString() {
		return "BackupResolution [resolutionSaveMode=" + resolutionSaveMode
				+ ", width=" + width + ", height=" + height + ", cutLeft="
				+ cutLeft + ", cutTop=" + cutTop + ", cutRight=" + cutRight
				+ ", cutBottom=" + cutBottom + "]";
	}
}