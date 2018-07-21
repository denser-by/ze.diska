package mydev.webobj;

public class QueryEncode {
	private String q;

	public QueryEncode(String q) {
		super();
		this.q = q;
	}

	public String encode() {
		StringBuffer buf = new StringBuffer();
		buf.setLength(0);

		for (int i = 0; i < q.length(); i++) {
			char ch = q.charAt(i);

			switch (ch) {
			case '\\':
				buf.append("%5C");
				break;
			case '/':
				buf.append("%2F");
				break;
			case ':':
				buf.append("%3A");
				break;
			case ' ':
				buf.append("+");
				break;
			case '+':
				buf.append("%2B");
				break;
			case '?':
				buf.append("%3F");
				break;
			case '&':
				buf.append("%26");
				break;
			case '%':
				buf.append("%25");
				break;
			case '\'':
				buf.append("%27");
				break;
			case '`':
				buf.append("%60");
				break;
			case '@':
				buf.append("%40");
				break;
			case '#':
				buf.append("%23");
				break;
			case '$':
				buf.append("%24");
				break;
			case '!':
				buf.append("%21");
				break;
			case '^':
				buf.append("%5E");
				break;
			case '(':
				buf.append("%28");
				break;
			case ')':
				buf.append("%29");
				break;
			case '=':
				buf.append("%3D");
				break;
			case '|':
				buf.append("%7C");
				break;
			case '[':
				buf.append("%5B");
				break;
			case ']':
				buf.append("%5D");
				break;
			case '{':
				buf.append("%7B");
				break;
			case '}':
				buf.append("%7D");
				break;
			case ';':
				buf.append("%3B");
				break;
			default:
				buf.append(ch);
				break;
			}
		}
		return buf.toString();
	}

}