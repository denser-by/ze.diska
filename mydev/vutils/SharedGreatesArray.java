package mydev.vutils;

public class SharedGreatesArray {
	public final static byte TYPE_INT = 1;
	public final static byte TYPE_BYTE = 2;
	public final static int DEF_SIZE = 866;
	protected int[] sharedArr;
	protected byte[] sharedArrByte;
	protected int lastDeclrSize;
	protected byte type;
	protected int pos;

	protected SharedGreatesArray(int initialSize) {
		this(initialSize, TYPE_INT);
	}

	protected SharedGreatesArray(int initialSize, byte type) {
		super();
		this.type = type;
		if (type == TYPE_INT)
			this.sharedArr = initialSize > 0 ? new int[initialSize] : new int[DEF_SIZE];
		else if (type == TYPE_BYTE)
			this.sharedArrByte = initialSize > 0 ? new byte[initialSize] : new byte[DEF_SIZE];
		cleanData();
	}

	public void cleanData() {
		this.pos = 0;
	}

	public void append(int[] part) {
		if (pos + part.length <= sharedArr.length)
			System.arraycopy(part, 0, sharedArr, pos, part.length);
		else {
			int[] oldArray = sharedArr;
			declareRequiredSize(pos + part.length);
			System.arraycopy(oldArray, 0, sharedArr, 0, oldArray.length);
			System.arraycopy(part, 0, sharedArr, pos, part.length);
		}
		pos += part.length;
	}

	public void append(byte[] part) {
		if (pos + part.length <= sharedArrByte.length)
			System.arraycopy(part, 0, sharedArrByte, pos, part.length);
		else {
			byte[] oldArray = sharedArrByte;
			declareRequiredSize(pos + part.length);
			System.arraycopy(oldArray, 0, sharedArrByte, 0, oldArray.length);
			System.arraycopy(part, 0, sharedArrByte, pos, part.length);
		}
		pos += part.length;
	}

	public void declareRequiredSize(int newSize) {
		if (sharedArr != null && newSize > sharedArr.length && type == TYPE_INT)
			sharedArr = new int[newSize];
		else if (sharedArrByte != null && newSize > sharedArrByte.length && type == TYPE_BYTE)
			sharedArrByte = new byte[newSize];
		lastDeclrSize = newSize;
	}

	public int[] getMemoryView() {
		return sharedArr;
	}

	public byte[] getMemoryViewByte() {
		return sharedArrByte;
	}

	public int getLastDeclaredSize() {
		return lastDeclrSize;
	}

	public static SharedGreatesArray create(int initialSize) {
		return new SharedGreatesArray(initialSize, TYPE_INT);
	}

	public static SharedGreatesArray create(int initialSize, byte type) {
		return new SharedGreatesArray(initialSize, type);
	}

	public static SharedGreatesArray createIntArray(int initialSize) {
		return create(initialSize);
	}

	public static SharedGreatesArray createByteArray(int initialSize) {
		return new SharedGreatesArray(initialSize, TYPE_BYTE);
	}
}