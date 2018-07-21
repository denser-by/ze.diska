package mydev.webobj.parser;

public class Id extends ParsingObject {
	private String value;

	public Id(int current, String value) {
		super(current);
		this.value = value;
	}

	public void clear() {
	}

	public int getCurrent() {
		return current;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return "Id [current=" + current + ", value=" + value + "]";
	}

}