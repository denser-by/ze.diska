package mydev.webobj.parser;

public class Attribute extends ParsingObject {
	private Name name;
	private Value value;

	public Attribute(int current, Name name, Value value) {
		super(current);
		this.name = name;
		this.value = value;
	}

	public void clear() {
	}

	public Name getName() {
		return name;
	}

	public Value getValue() {
		return value;
	}

	public String toString() {
		return "Attribute [current=" + current + ", name=" + name + ", value="
				+ value + "]";
	}

}