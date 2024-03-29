package mydev.vutils;

import mydev.aaa.Sleeper;

public final class ExpectHere extends Sleeper {
	public static final short DEF_SLEEP_TIME = (short) (90 / 3);
	private boolean ready;
	private short sleepTime;

	public ExpectHere() {
		super();
		this.sleepTime = DEF_SLEEP_TIME;
		clear();
	}

	public ExpectHere(int time) {
		super();
		this.sleepTime = (short) time;
		clear();
	}

	public void markAsReached() {
		this.ready = true;
	}

	public void stopUntilExpected() {
		while (!ready)
			sleep(sleepTime);
	}

	public void clear() {
		this.ready = false;
	}
}