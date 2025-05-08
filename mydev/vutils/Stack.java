package mydev.vutils;

public final class Stack {
	private long stackSize;
	private QueueNode top;

	public Stack() {
		super();
		this.stackSize = 0L;
		this.top = null;
	}

	public Stack(Object[] dataItems) {
		this();
		push(dataItems);
	}

	public Stack(Stack otherStack) {
		this();
		push(otherStack);
	}

	public synchronized void push(Object[] dataItems) {
		for (int i = 0; i < dataItems.length; i++)
			push(dataItems[i]);
	}

	public synchronized void push(Stack otherStack) {
		Object[] dataItems = otherStack.getStackRecordsArray();
		push(dataItems);
	}

	public synchronized void push(Object data) {
		stackSize += 1L;
		QueueNode newNode = new QueueNode(data, top);
		top = newNode;
	}

	public synchronized Object pop() {
		if (top != null) {
			stackSize -= 1L;
			QueueNode removedNode = top;
			top = top.next;
			return removedNode.data;
		}
		return null;
	}

	public synchronized Object getStackRecord(int idx) {
		QueueNode curNode = top;
		for (int qPos = 0; curNode != null; qPos++) {
			if (qPos == idx)
				return curNode.data;
			curNode = curNode.next;
		}
		return null;
	}

	public synchronized void setStackRecord(int idx, Object dataReplace) {
		QueueNode curNode = top;
		for (int qPos = 0; curNode != null; qPos++) {
			if (qPos == idx) {
				curNode.data = dataReplace;
				curNode = null;
				continue;
			}
			curNode = curNode.next;
		}
	}

	public synchronized Object[] getStackRecordsArray() {
		Object[] array = new Object[(int) stackSize];
		QueueNode curNode = top;
		for (int qPos = 0; curNode != null; qPos++) {
			array[qPos] = curNode.data;
			curNode = curNode.next;
		}
		return array;
	}

	public Stack quickStackCopy() {
		Object[] dataItems = this.getStackRecordsArray();
		return new Stack(dataItems);
	}

	public synchronized long sizeOfStack() {
		return stackSize;
	}

	public synchronized boolean isEmptyStack() {
		return top == null;
	}

	public synchronized void clearStack() {
		this.stackSize = 0L;
		this.top = null;
	}
}