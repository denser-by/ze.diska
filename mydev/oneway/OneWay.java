package mydev.oneway;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import mydev.vutils.Spiska;

public class OneWay {
	public OneWay() {
	}

	public static void main(String[] args) throws IOException {
		OneWay ow = new OneWay();
		if (args.length > 0) {
			String param = args[0];
			char[] content = ow.read(param);
			String[] pieces = ow.check(content, 1);
			ow.out(pieces);
		} else
			System.out.println("One parameter must be here at least...");
	} // main

	public String[] check(byte[] data) {
		return check(data, 1);
	}

	public String[] check(char[] content) {
		return check(content, 1);
	}

	public String[] check(byte[] data, int fffff) {
		return checkIt(null, data);
	}

	public String[] check(char[] content, int fffff) {
		return checkIt(content, null);
	}

	private int dlina(char[] tent, byte[] data) {
		return data != null ? data.length : tent.length;
	}

	private char nextIt(char[] tent, byte[] data, int i) {
		return (char) (data != null ? data[i + 1] : tent[i + 1]);
	}

	private char cureIt(char[] tent, byte[] data, int i) {
		return (char) (data != null ? data[i] : tent[i]);
	}

	private char prevIt(char[] tent, byte[] data, int i) {
		return (char) (data != null ? data[i - 1] : tent[i - 1]);
	}

	private String[] checkIt(char[] tent, byte[] data) {
		int dlin = dlina(tent, data);
		Spiska sp = new Spiska();
		char[] ch = new char[512];
		int[] z = new int[2];
		int idx = 0, i = 0 - 1;
		boolean db;
		char xchar;
		while (++i < dlin) {
			switch (xchar = cureIt(tent, data, i)) {
			case ' ':
			case '\t':
			case '\r':
			case '\n': {
				if (idx > 0) {
					sp.append(new String(ch, 0, idx));
					idx = 0;
				}
				continue;
			}
			case '(':
			case ')':
			case '[':
			case ']':
			case '.':
			case ':':
			case '{':
			case '}':
			case ';':
			case '?':
			case ',': {
				if (idx > 0) {
					sp.append(new String(ch, 0, idx));
					idx = 0;
				}
				ch[0] = xchar;
				sp.append(new String(ch, 0, 1));
				continue;
			}
			case '\'': {
				if (idx > 0) {
					sp.append(new String(ch, 0, idx));
					idx = 0;
				}
				extrCh(tent, data, i, ch, idx, z);
				sp.append(new String(ch, 0, z[1]));
				i = z[0];
				continue;
			}
			case '"': {
				if (idx > 0) {
					sp.append(new String(ch, 0, idx));
					idx = 0;
				}
				exStr(tent, data, i, ch, idx, z);
				sp.append(new String(ch, 0, z[1]));
				i = z[0];
				continue;
			}
			case '/': {
				if (idx > 0) {
					sp.append(new String(ch, 0, idx));
					idx = 0;
				}
				if (doblyaCom(tent, data, i)) {
					if (nextIt(tent, data, i) == '*') {
						extrMultyLineCom(tent, data, i, ch, idx, z);
						sp.append(new String(ch, 0, z[1]));
						i = z[0];
					} else if (nextIt(tent, data, i) == '/') {
						extrLineCom(tent, data, i, ch, idx, z);
						sp.append(new String(ch, 0, z[1]));
						i = z[0];
					}
				} else {
					db = doblya(tent, data, i);
					ch[0] = xchar;
					if (db)
						ch[1] = nextIt(tent, data, i++);
					sp.append(new String(ch, 0, db ? 2 : 1));
				}
				continue;
			}
			case '>':
			case '<':
			case '=':
			case '!':
			case '-':
			case '+':
			case '*':
			case '|':
			case '&': {
				if (idx > 0) {
					sp.append(new String(ch, 0, idx));
					idx = 0;
				}
				ch[0] = xchar;
				if (db = doblya(tent, data, i))
					ch[1] = nextIt(tent, data, i++);
				sp.append(new String(ch, 0, db ? 2 : 1));
				continue;
			}
			default: {
				ch[idx++] = xchar;
			}
			} // switch
		} // while
		String[] res = new String[(int) sp.size()];
		for (int ee = 0; ee < res.length; ee++)
			res[ee] = (String) sp.at(ee);
		return res;
	}

	void extrMultyLineCom(char[] tent, byte[] data, int c, char[] ch, int h, int[] z) {
		int dlin = dlina(tent, data);
		ch[h++] = cureIt(tent, data, c++);
		do {
			while (c < dlin && cureIt(tent, data, c) != '/')
				ch[h++] = cureIt(tent, data, c++);
		} while (prevIt(tent, data, c) != '*');
		if (c < dlin)
			ch[h++] = cureIt(tent, data, c++);
		z[0] = c - 1;
		z[1] = h;
	}

	private void extrLineCom(char[] tent, byte[] data, int c, char[] ch, int h, int[] z) {
		int dlin = dlina(tent, data);
		ch[h++] = cureIt(tent, data, c++);
		while (c < dlin && cureIt(tent, data, c) != '\n' && cureIt(tent, data, c) != '\r') {
			ch[h++] = cureIt(tent, data, c++);
		}
		z[0] = c - 1;
		z[1] = h;
	}

	private void exStr(char[] tent, byte[] data, int c, char[] ch, int h, int[] z) {
		int dlin = dlina(tent, data);
		ch[h++] = cureIt(tent, data, c++);
		while (c < dlin && cureIt(tent, data, c) != '"') {
			if (cureIt(tent, data, c) == '\\') {
				ch[h++] = cureIt(tent, data, c++);
				ch[h++] = cureIt(tent, data, c++);
				if (ch[h - 1] == '"') {
					ch[h - 2] = ch[--h];
				}
			} else {
				ch[h++] = cureIt(tent, data, c++);
			}
		}
		if (c < dlin)
			ch[h++] = cureIt(tent, data, c++);
		z[0] = c - 1;
		z[1] = h;
	}

	private void extrCh(char[] tent, byte[] data, int c, char[] ch, int h, int[] z) {
		int dlin = dlina(tent, data);
		ch[h++] = cureIt(tent, data, c++);
		while (c < dlin && cureIt(tent, data, c) != '\'') {
			if (cureIt(tent, data, c) == '\\') {
				ch[h++] = cureIt(tent, data, c++);
				ch[h++] = cureIt(tent, data, c++);
			} else {
				ch[h++] = cureIt(tent, data, c++);
			}
		}
		if (c < dlin)
			ch[h++] = cureIt(tent, data, c++);
		if (ch[0] == '\'' && ch[1] == '\'' && c < dlin)
			ch[h++] = cureIt(tent, data, c++);
		z[0] = c - 1;
		z[1] = h;
	}

	boolean doblyaCom(char[] tent, byte[] data, int i) {
		int dlin = dlina(tent, data);
		short count = 0;
		if (i + 1 < dlin) {
			char ch1 = cureIt(tent, data, i);
			char ch2 = nextIt(tent, data, i);
			if (ch1 == '/' && ch2 == '/')
				count++;
			if (ch1 == '/' && ch2 == '*')
				count++;
			if (ch1 == '*' && ch2 == '/')
				count++;
		}
		return count > 0;
	}

	boolean doblya(char[] tent, byte[] data, int i) {
		int dlin = dlina(tent, data);
		short count = 0;
		if (i + 1 < dlin) {
			char ch1 = cureIt(tent, data, i);
			char ch2 = nextIt(tent, data, i);
			if (ch1 == '=' && ch2 == '=')
				count++;
			if (ch1 == '!' && ch2 == '=')
				count++;
			if (ch1 == '>' && ch2 == '=')
				count++;
			if (ch1 == '<' && ch2 == '=')
				count++;
			if (ch1 == '+' && ch2 == '+')
				count++;
			if (ch1 == '-' && ch2 == '-')
				count++;
			if (ch1 == '|' && ch2 == '|')
				count++;
			if (ch1 == '&' && ch2 == '&')
				count++;
			if (ch1 == '/' && ch2 == '=')
				count++;
			if (ch1 == '*' && ch2 == '=')
				count++;
			if (ch1 == '+' && ch2 == '=')
				count++;
			if (ch1 == '-' && ch2 == '=')
				count++;
			if (ch1 == '|' && ch2 == '=')
				count++;
			if (ch1 == '&' && ch2 == '=')
				count++;
		}
		return count > 0;
	}

	public void out(String[] pieces) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < pieces.length; i++) {
			buf.append(pieces[i]).append("\n");
			if (i - i / 50 * 50 > 0) {
				System.out.print(buf.toString());
				buf.setLength(0);
			}
		}
		if (buf.length() > 0)
			System.out.print(buf.toString());
		buf.setLength(0);
	}

	public char[] read(String fPath) throws IOException {
		File fOpen = new File(fPath);
		FileInputStream fos = new FileInputStream(fOpen);
		Spiska ht = new Spiska();
		int vPower = 0;
		int count;
		byte[] buf = new byte[4096];
		do {
			count = fos.read(buf);
			if (count > 0)
				vPower += count;
			if (count < 1)
				continue;
			if (count <= buf.length) {
				byte[] zzz = new byte[count];
				System.arraycopy(buf, 0, zzz, 0, count);
				ht.append((Object) zzz);
			}
		} while (count > 0);
		fos.close();
		char[] total = new char[vPower];
		int totalIdx = 0;
		for (int i = 0; i < ht.size(); i++) {
			byte[] cur = (byte[]) ht.at(i);
			for (int j = 0; j < cur.length; j++)
				total[totalIdx++] = (char) cur[j];
		}
		return total;
	}
}