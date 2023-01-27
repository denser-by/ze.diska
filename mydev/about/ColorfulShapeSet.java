package mydev.about;

import mydev.vutils.Data;
import mydev.vutils.Queue;

public final class ColorfulShapeSet extends Colorfull {
	private Queue qItems;
	private Colorfull[] qItemsArray;

	public ColorfulShapeSet() {
		super(ColorsArray.blackPoint.ic());
		clear();
	}

	public ColorfulShapeSet(Colorfull[] cfItems) {
		this();
		qItems.enqueueQueueRecords(cfItems);
	}

	public void represent(Matrix2D matrix) {
		if (qItemsArray.length != qItems.sizeOfQueue())
			copyQueue();
		for (int i = 0; i < qItemsArray.length; i++)
			qItemsArray[i].represent(matrix);
	}

	public void toPen(Canvas ics, Paint pn) {
		if (qItemsArray.length != qItems.sizeOfQueue())
			copyQueue();
		for (int i = 0; i < qItemsArray.length; i++) {
			Colorfull cfItem = qItemsArray[i];
			cfItem.toPen(ics, pn.select(cfItem));
		}
	}

	void copyQueue() {
		Object[] queueRecordsArray = qItems.getQueueRecordsArray();
		Colorfull[] newItems = new Colorfull[queueRecordsArray.length];
		for (int i = 0; i < queueRecordsArray.length; i++)
			newItems[i] = (Colorfull) queueRecordsArray[i];
		qItemsArray = newItems;
	}

	public ColorfulShapeSet append(Colorfull item) {
		qItems.enqueueQueueRecord(item);
		return this;
	}

	public long size() {
		return qItems.sizeOfQueue();
	}

	public Colorfull atIndexed(int idx) {
		return qItemsArray[idx];
	}

	public Colorfull at(int idx) {
		return (Colorfull) qItems.getQueueRecord(idx);
	}

	public void clear() {
		qItems = new Queue();
		copyQueue();
	}

	public Colorfull[] toArray() {
		return qItemsArray;
	}

	public String toString() {
		return "ColorfulShapeSet [qItems=" + new Data().dump(qItemsArray) + "]";
	}
}