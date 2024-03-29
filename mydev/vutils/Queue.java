package mydev.vutils;

public final class Queue {
	private long qSize;
	private QueueNode first;
	private QueueNode last;

	public Queue() {
		super();
		this.qSize = 0L;
		this.last = null;
		this.first = null;
	}

	public Queue(Object[] dataItems) {
		this();
		enqueueQueueRecords(dataItems);
	}

	public Queue(Queue otherQueue) {
		this();
		enqueueQueueRecords(otherQueue);
	}

	public synchronized void enqueueQueueRecords(Object[] dataItems) {
		for (int i = 0; i < dataItems.length; i++)
			enqueueQueueRecord(dataItems[i]);
	}

	public synchronized void enqueueQueueRecords(Queue otherQueue) {
		Object[] dataItems = otherQueue.getQueueRecordsArray();
		enqueueQueueRecords(dataItems);
	}

	public synchronized void enqueueQueueRecord(Object data) {
		qSize += 1L;
		QueueNode newLastNode = new QueueNode(data);
		if (last != null)
			last.next = newLastNode;
		last = newLastNode;
		if (first == null)
			first = newLastNode;
	}

	public synchronized Object dequeueQueueRecord() {
		if (first != null) {
			qSize -= 1L;
			QueueNode removeNode = first;
			first = first.next;
			return removeNode.data;
		}
		return null;
	}

	public synchronized Object getQueueRecord(int idx) {
		QueueNode curNode = first;
		for (int qPos = 0; curNode != null; qPos++) {
			if (qPos == idx)
				return curNode.data;
			curNode = curNode.next;
		}
		return null;
	}

	public synchronized void setQueueRecord(int idx, Object dataReplace) {
		QueueNode curNode = first;
		for (int qPos = 0; curNode != null; qPos++) {
			if (qPos == idx) {
				curNode.data = dataReplace;
				curNode = null;
				continue;
			}
			curNode = curNode.next;
		}
	}

	public synchronized Object[] getQueueRecordsArray() {
		Object[] array = new Object[(int) qSize];
		QueueNode curNode = first;
		for (int qPos = 0; curNode != null; qPos++) {
			array[qPos] = curNode.data;
			curNode = curNode.next;
		}
		return array;
	}

	public Queue quickQueueCopy() {
		Object[] dataItems = this.getQueueRecordsArray();
		return new Queue(dataItems);
	}

	public synchronized long sizeOfQueue() {
		return qSize;
	}

	public synchronized boolean isEmptyQueue() {
		return first == null;
	}

	public synchronized void clearQueue() {
		this.qSize = 0L;
		this.last = null;
		this.first = null;
	}
}

class QueueNode {
	protected Object data;
	protected QueueNode next;

	public QueueNode(Object data) {
		super();
		this.data = data;
		this.next = null;
	}

	public QueueNode(Object data, QueueNode next) {
		super();
		this.data = data;
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public QueueNode getNext() {
		return next;
	}

	public void setNext(QueueNode next) {
		this.next = next;
	}
}