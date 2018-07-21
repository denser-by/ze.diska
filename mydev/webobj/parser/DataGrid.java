package mydev.webobj.parser;

import mydev.vutils.Spiska;

public class DataGrid extends ParsingObject {
	private Spiska attrLists;

	public DataGrid(int current, Spiska attrLists) {
		super(current);
		this.attrLists = attrLists;
	}

	public void clear() {
	}

	public int getCurrent() {
		return current;
	}

	public long size() {
		long size = 0;
		if (attrLists != null)
			size = attrLists.size();
		return size;
	}

	public AttributeList get(int index) {
		AttributeList result = null;
		if (attrLists != null && index >= 0 && index < attrLists.size())
			result = (AttributeList) attrLists.at(index);
		return result;
	}

}
