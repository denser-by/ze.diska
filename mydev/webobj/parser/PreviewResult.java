package mydev.webobj.parser;

public class PreviewResult extends ParsingObject {
	private boolean working;

	public PreviewResult(boolean working, int current) {
		super(current);
		this.working = working;
	}

	public void clear() {
	}

	public boolean isWorking() {
		return working;
	}

	public int getCurrent() {
		return current;
	}

	public String toString() {
		return "PreviewResult [working=" + working + ", current=" + current
				+ "]";
	}

}
