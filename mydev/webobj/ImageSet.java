package mydev.webobj;

import mydev.vutils.Spiska;

public class ImageSet {
	private int totalCount;
	private int number;
	private int offset;
	private Spiska imgs;

	public ImageSet(int totalCount, int number, int offset) {
		super();
		this.totalCount = totalCount;
		this.number = number;
		this.offset = offset;
		this.imgs = new Spiska();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getNumber() {
		return number;
	}

	public int getOffset() {
		return offset;
	}

	public long getSize() {
		return imgs.size();
	}

	public void addImage(Image img) {
		imgs.append(img);
	}

	public Image getImage(int idx) {
		Image im = null;
		if (idx >= 0 && idx < imgs.size())
			im = (Image) imgs.at(idx);
		return im;
	}

}
