package mydev.vutils;

public final class MegaAverageRec extends AverageRec {
	private Stack ars;
	private AverageRec[] arsCopy;

	public MegaAverageRec() {
		super();
		this.ars = new Stack();
		this.arsCopy = makeCopyCash();
	}

	public void clearItems() {
		if (ars != null)
			for (int i = 0; i < arsCopy.length; i++)
				arsCopy[i].clearItems();
	}

	public void addItem(AverageRec ar) {
		if (ar != null) {
			ars.push(ar);
			this.arsCopy = makeCopyCash();
		}
	}

	public double getAverageTime() {
		double avgTime = 0.f;
		int j = 0;
		for (int i = 0; i < arsCopy.length; i++) {
			AverageRec ar = arsCopy[i];
			double at = ar.getAverageTime();
			if (at > 0.f) {
				avgTime *= j;
				avgTime += at;
				avgTime /= (j + 1);
				j++;
			}
		}
		return avgTime;
	}

	public long getRecordsNum() {
		int num = 0;
		for (int i = 0; i < arsCopy.length; i++)
			num += arsCopy[i].getRecordsNum();
		return num;
	}

	public double getMinTime() {
		double minTime = Float.MAX_VALUE;
		for (int i = 0; i < arsCopy.length; i++) {
			AverageRec ar = arsCopy[i];
			double mt = ar.getMinTime();
			double at = ar.getAverageTime();
			if (at > 0.f)
				if (mt < minTime)
					minTime = mt;
		}
		return minTime;
	}

	public double getMaxTime() {
		double maxTime = 0.f;
		for (int i = 0; i < arsCopy.length; i++) {
			AverageRec ar = arsCopy[i];
			double mt = ar.getMaxTime();
			double at = ar.getAverageTime();
			if (at > 0.f)
				if (mt > maxTime)
					maxTime = mt;
		}
		return maxTime;
	}

	private AverageRec[] makeCopyCash() {
		Object[] queueRecordsArray = ars.getStackRecordsArray();
		AverageRec[] result = new AverageRec[queueRecordsArray.length];
		for (int i = 0; i < result.length; i++)
			result[i] = (AverageRec) queueRecordsArray[i];
		return result;
	}
}