package mydev.vutils;

public class ReadyFree {
	private boolean readyFree;

	public ReadyFree() {
		super();
		this.readyFree = true;
	}

	public boolean isFree() {
		return readyFree == true;
	}

	public void setBusy() {
		this.readyFree = false;
	}

	public void setFree() {
		this.readyFree = true;
	}
}