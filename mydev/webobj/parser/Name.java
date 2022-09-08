package mydev.webobj.parser;

public class Name extends ParsingObject {
	private String value;

	public Name(int current, String value) {
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
		return "Name [current=" + current + ", value=" + value + "]";
	}

}
