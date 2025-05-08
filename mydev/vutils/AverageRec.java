package mydev.vutils;

public class AverageRec {
	Time ticTak;
	long records;
	double minTime;
	double maxTime;
	double avgTime;

	public AverageRec() {
		super();
		clearItems();
	}

	public void clearItems() {
		this.ticTak = new Time();
		this.records = 0L;
		this.maxTime = Float.MIN_VALUE;
		this.minTime = Float.MAX_VALUE;
		this.avgTime = 0.f;
	}

	public void start() {
		ticTak = new Time();
		ticTak.start();
	}

	public long trust() {
		long dt = ticTak.trust();
		double curTime = (double) dt / 1000.f;
		records += 1L;
		if (curTime < minTime)
			minTime = curTime;
		if (curTime > maxTime)
			maxTime = curTime;
		avgTime *= records - 1;
		avgTime += curTime;
		avgTime /= records;
		return dt;
	}

	public double getAverageTime() {
		return avgTime;
	}

	public long getRecordsNum() {
		return records;
	}

	public double getMinTime() {
		return minTime;
	}

	public double getMaxTime() {
		return maxTime;
	}
}