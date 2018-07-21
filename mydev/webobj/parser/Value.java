package mydev.webobj.parser;

public class Value extends ParsingObject {
	private String value;
	private AttributeList valueAttrList;
	private DataGrid valueGrid;

	public Value(int current, String value) {
		super(current);
		this.value = value;
	}

	public Value(int current, AttributeList valueAttrList) {
		super(current);
		this.valueAttrList = valueAttrList;
	}

	public Value(int current, DataGrid valueGrid) {
		super(current);
		this.valueGrid = valueGrid;
	}

	public void clear() {
	}

	public String getValue() {
		return value;
	}

	public AttributeList getValueAttributeList() {
		return valueAttrList;
	}

	public DataGrid getValueDataGrid() {
		return valueGrid;
	}

	public String toString() {
		return "Value [current=" + current + ", value=" + value
				+ ", valueAttrList=" + valueAttrList + ", valueGrid="
				+ valueGrid + "]";
	}

}
