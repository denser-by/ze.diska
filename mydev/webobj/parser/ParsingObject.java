package mydev.webobj.parser;

public abstract class ParsingObject implements State {
	protected int current;

	public ParsingObject(int current) {
		super();
		this.current = current;
	}

	public int getCurrent() {
		return current;
	}

}
