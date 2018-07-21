package mydev.webobj;

public class Image {
	private String title;
	private String url;
	private int width;
	private int height;
	private int size;
	private String urlOriginal;

	public Image(String title, String url, int width, int height, int size, String urlOriginal) {
		super();
		this.title = title;
		this.url = url;
		this.width = width;
		this.height = height;
		this.size = size;
		this.urlOriginal = urlOriginal;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSize() {
		return size;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

}