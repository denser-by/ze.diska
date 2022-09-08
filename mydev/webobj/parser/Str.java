package mydev.webobj.parser;

public class Str extends ParsingObject {
	private String value;

	public Str(int current, String value) {
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
		return "Str [current=" + current + ", value=" + value + "]";
	}

}