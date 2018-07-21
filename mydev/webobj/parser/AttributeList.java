package mydev.webobj.parser;

import mydev.vutils.Karta;
import mydev.vutils.Spiska;

public class AttributeList extends ParsingObject {
	private Karta attributes;

	public AttributeList(int current, Spiska attrList) {
		super(current);
		this.attributes = new Karta();
		for (long i = 0; i < attrList.size(); i++) {
			Attribute attr = (Attribute) attrList.at(i);
			attributes.put(attr.getName().getValue(), attr);
		}
	}

	public void clear() {
	}

	public void add(Attribute attr) {
		attributes.put(attr.getName().getValue(), attr);
	}

	public int getCurrent() {
		return current;
	}

	public Attribute getAttribute(String name) {
		if (attributes != null) {
			Attribute attr = (Attribute) attributes.get(name);
			return attr;
		}
		return null;
	}

}