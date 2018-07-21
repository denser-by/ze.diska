package mydev.webobj.parser;

public class Num extends ParsingObject {
	private String value;

	public Num(int current, String value) {
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
		return "Number [current=" + current + ", value=" + value + "]";
	}

}
