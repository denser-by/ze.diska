package mydev.oline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import mydev.vutils.Spiska;
import java.lang.StringBuffer;
import mydev.oneway.OneWay;

public class OneLine {
	public OneLine() {
	}

	public String line(String path) throws IOException {
		OneWay ow = new OneWay();
		byte[] data = read(path);
		String[] pieces = ow.check(data);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < pieces.length && pieces[i] != null; i++) {
			if (pieces[i].startsWith("//"))
				continue;
			if (pieces[i].startsWith("/*"))
				continue;
			buf.append(pieces[i]);
			if (i < pieces.length - 1 && pieces[i + 1] != null && !pieces[i + 1].startsWith("=")
					&& !pieces[i].startsWith("=") && !pieces[i].startsWith("-") && !pieces[i + 1].startsWith("-")
					&& !pieces[i + 1].startsWith("+") && !pieces[i].startsWith("+") && !pieces[i].startsWith(",")
					&& !pieces[i + 1].startsWith(",") && !pieces[i].startsWith("!") && !pieces[i].startsWith("(")
					&& !pieces[i].startsWith("[") && !pieces[i + 1].startsWith("}") && !pieces[i + 1].startsWith(")")
					&& !pieces[i + 1].startsWith(";") && !pieces[i + 1].startsWith("(")
					&& !pieces[i + 1].startsWith("[") && !pieces[i + 1].startsWith("]")
					&& !pieces[i + 1].startsWith(".") && !pieces[i].startsWith("."))
				buf.append(' ');
		}
		return buf.toString();
	}

	public static void main(String[] args) throws IOException {
		OneLine one = new OneLine();
		if (args.length > 0) {
			String param = args[0];
			String line = one.line(param);
			System.out.println(line);
		} else {
			System.out.println("Please specify source file as a parameter.");
		}
	}

	public byte[] read(String fPath) throws IOException {
		File fOpen = new File(fPath);
		FileInputStream fos = new FileInputStream(fOpen);
		Spiska ht = new Spiska();
		int vPower = 0;
		int count;
		byte[] buf = new byte[2048];
		do {
			count = fos.read(buf);
			if (count > 0)
				vPower += count;
			if (count < 1)
				continue;
			if (count <= 2048) {
				byte[] zzz = new byte[count];
				System.arraycopy(buf, 0, zzz, 0, count);
				ht.append((Object) zzz);
			}
		} while (count > 0);
		fos.close();
		byte[] total = new byte[vPower];
		int totalIdx = 0;
		for (int i = 0; i < ht.size(); i++) {
			byte[] cur = (byte[]) ht.at(i);
			int curLen = cur.length;
			System.arraycopy(cur, 0, total, totalIdx, curLen);
			totalIdx += curLen;
		}
		return total;
	}

	public void write(String fPath, byte[] bCont) throws IOException {
		File wOpen = new File(fPath);
		FileOutputStream os = new FileOutputStream(wOpen);
		os.write(bCont);
		os.flush();
		os.close();
	}
}