package mydev.droid.noted.support;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import android.app.Activity;
import android.content.Context;
//import android.util.Log;

public class MyNotes {
	public static final int DIM = 3;
	public static final int BASE = 100 - DIM;
	private Vector<FirstJob> allNotes;
	private boolean changes;
	private Activity act;

	public MyNotes(Activity act) {
		super();
		this.allNotes = new Vector<FirstJob>();
		this.changes = false;
		this.act = act;
	}

	public void loadt() {
		Date dt = new Date();
		allNotes.add(new FirstJob("aaaaaaaaaaa", dt));
		allNotes.add(new FirstJob("bbbbbbbbbbb", new Date(dt.getTime() - 1000)));
		allNotes.add(new FirstJob("bbcccccc", new Date(dt.getTime() + 1000)));
		allNotes.add(new FirstJob("ggggggbbbb", new Date(dt.getTime() - 2000)));
		allNotes.add(new FirstJob(
				"???????? ?????????? ???????? ?????????? ???????? ?????????? ... ???? ?????? ?????????? ?????? ????????????",
				new Date(dt.getTime() + 600)));
	}

	public void load() {
//		int totalItems = 0;
		allNotes.clear();
		FileInputStream input = null;
		FirstJob firstJob = null;
		try {
			input = act.openFileInput(FirstJob.STORE);
			do {
				if (firstJob != null)
					allNotes.add(firstJob);
				firstJob = readItem(input);
			} while (firstJob != null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			totalItems = allNotes.size();
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
//		Log.i(FirstJob.APPINFO, "There were " + totalItems
//				+ " items picked-up.");
	}

	public void save() {
		if (changes == false) {
//			Log.i(FirstJob.APPINFO, "There was no changes.");
			return;
		}
		int totalItems = 0;
		FileOutputStream fos = null;
		try {
			fos = act.openFileOutput(FirstJob.STORE, Context.MODE_PRIVATE);
			totalItems = allNotes.size();
			for (int i = 0; i < totalItems; i++) {
				FirstJob firstJob = allNotes.elementAt(i);
				writeItem(fos, firstJob);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
//		Log.i(FirstJob.APPINFO, "There were " + totalItems + " items saved.");
	}

	private FirstJob readItem(FileInputStream input) throws IOException {
		FirstJob firstJob = null;
		try {
			byte[] dataLen3 = new byte[DIM];
			if (3 != input.read(dataLen3))
				throw new IOException("BytesOf new hvataet!");
			int dataLen = decode3(dataLen3);
			byte[] dataBytes = new byte[dataLen];
			if (dataLen != input.read(dataBytes))
				throw new IOException("BytesOf new hvataet!");
			String data = new String(dataBytes);
//			Log.i(FirstJob.APPINFO, "data=<<" + data + ">>");
			byte[] timeLen1 = new byte[1];
			if (1 != input.read(timeLen1))
				throw new IOException("BytesOf new hvataet!");
			int timeLen = decode1(timeLen1);
			byte[] timeBytes = new byte[timeLen];
			if (timeLen != input.read(timeBytes))
				throw new IOException("BytesOf new hvataet!");
			String time = new String(timeBytes);
//			Log.i(FirstJob.APPINFO, "time=<<" + time + ">>");
			Long milli = Long.parseLong(time);
			Date dt = new Date(milli.longValue());
			firstJob = new FirstJob(data, dt);
		} catch (IOException ex) {
			firstJob = null;
		}
		return firstJob;
	}

	private void writeItem(FileOutputStream fos, FirstJob firstJob)
			throws IOException {
		String data = firstJob.getData();
		byte[] dataBytes = data.getBytes();
		String time = firstJob.getTime();
		byte[] timeBytes = time.getBytes();
		fos.write(encode3(dataBytes.length));
		fos.write(dataBytes);
		fos.write(encode1(timeBytes.length));
		fos.write(timeBytes);
	}

	public int decode3(byte[] rep) {
		return rep[0] + rep[1] * BASE + rep[2] * BASE * BASE;
	}

	public int decode1(byte[] rep) {
		return rep[0];
	}

	private byte[] encode3(int num) {
		byte[] tmp = new byte[DIM];
		int idx = 0;
		while (num > 0 && idx < DIM) {
			byte delta = (byte) (num - num / BASE * BASE);
			num = num / BASE;
			tmp[idx++] = delta;
		}
		return tmp;
	}

	private byte[] encode1(int num) {
		byte[] tmp = new byte[1];
		tmp[0] = (byte) num;
		return tmp;
	}

	public void erase(String noteKey) {
		changes = true;
		for (int i = 0; i < allNotes.size(); i++)
			if (allNotes.elementAt(i).wrap().compareTo(noteKey) == 0) {
				allNotes.remove(i);
				return;
			}
	}

	public FirstJob obtain(String noteKey) {
		for (int i = 0; i < allNotes.size(); i++)
			if (allNotes.elementAt(i).wrap().compareTo(noteKey) == 0)
				return allNotes.elementAt(i);
		return new FirstJob("misstaken", new Date());
	}

	public void create(FirstJob firstJob) {
		changes = true;
		allNotes.add(firstJob);
	}

	public String[] arryacht() {
		final FirstJob[] aaa = new FirstJob[allNotes.size()];
		for (int i = 0; i < aaa.length; i++)
			aaa[i] = allNotes.elementAt(i);
		for (int i = 0; i < aaa.length - 1; i++)
			for (int j = i; j < aaa.length; j++)
				if (aaa[i].less(aaa[j])) {
					FirstJob tmp = aaa[j];
					aaa[j] = aaa[i];
					aaa[i] = tmp;
				}
		final String[] oki = new String[aaa.length];
		for (int i = 0; i < oki.length; i++)
			oki[i] = new String(aaa[i].wrap());
		return oki;
	}
}
