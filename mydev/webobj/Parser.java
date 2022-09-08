package mydev.webobj;

import mydev.vutils.Spiska;
import mydev.webobj.parser.Attribute;
import mydev.webobj.parser.AttributeList;
import mydev.webobj.parser.DataGrid;
import mydev.webobj.parser.Id;
import mydev.webobj.parser.Name;
import mydev.webobj.parser.Num;
import mydev.webobj.parser.PreviewResult;
import mydev.webobj.parser.Str;
import mydev.webobj.parser.Value;

/**
 * 
 * s_grid = '[' s_attr_list (',' s_attr_list)* ']'
 * 
 * s_attr_list = '{' s_attr (',' s_attr)* '}'
 * 
 * s_attr = s_name ':' s_Value
 * 
 * s_value = s_num | s_str | s_attr_list | s_grid | s_id//"(true|false)"
 * 
 * s_name = s_str | s_id
 * 
 */
public class Parser implements WebObjectsParser {

	private boolean D = false;

	public Parser() {
		super();
	}

	public AttributeList parseData(String objectsData) throws ParseException {
		AttributeList start = parseAttrList4Value(objectsData, 0,
				objectsData.length() - 1);
		return start;
	}

	protected AttributeList parseAttrList4Value(String data, int start, int stop)
			throws ParseException {

		Spiska resultList = new Spiska();
		PreviewResult pr = null;
		start = parseAttrListStart(data, start, stop);
		Attribute attr4List = parseAttr(data, start, stop);
		resultList.append(attr4List);
		start = attr4List.getCurrent();
		while ((pr = previewNextContinue(data, start, stop)).isWorking()) {
			start = parseContinue(data, start, stop);
			attr4List = parseAttr(data, start, stop);
			resultList.append(attr4List);
			start = attr4List.getCurrent();
		}
		start = parseAttrListEnd(data, start, stop);
		return new AttributeList(start, resultList);
	}

	protected AttributeList parseAttrList4Grid(String data, int start, int stop)
			throws ParseException {

		Spiska resultList = new Spiska();
		PreviewResult pr = null;
		start = parseAttrListStart(data, start, stop);
		Attribute attr4List = parseAttr(data, start, stop);
		resultList.append(attr4List);
		start = attr4List.getCurrent();
		while ((pr = previewNextContinue(data, start, stop)).isWorking()) {
			start = parseContinue(data, start, stop);
			attr4List = parseAttr(data, start, stop);
			resultList.append(attr4List);
			start = attr4List.getCurrent();
		}
		start = parseAttrListEnd(data, start, stop);
		return new AttributeList(start, resultList);
	}

	protected Attribute parseAttr(String data, int start, int stop)
			throws ParseException {
		Name attrName = parseName(data, start, stop);
		start = attrName.getCurrent();
		start = parseDelim(data, start, stop);
		Value attrValue = parseValue(data, start, stop);

		if (D) {
			if (attrValue.getValueDataGrid() != null)
				System.out.println("parseAttr=<<" + attrName.getValue()
						+ ">><<" + attrValue.getValueDataGrid() + ">>");
			else if (attrValue.getValueAttributeList() != null)
				System.out.println("parseAttr=<<" + attrName.getValue()
						+ ">><<" + attrValue.getValueAttributeList() + ">>");
			else
				System.out.println("parseAttr=<<" + attrName.getValue()
						+ ">><<" + attrValue.getValue() + ">>");
		}

		return new Attribute(attrValue.getCurrent(), attrName, attrValue);
	}

	private Name parseName(String data, int start, int stop)
			throws ParseException {
		PreviewResult pr = null;
		if ((pr = previewNextStrOpener(data, start, stop)).isWorking()) {

			Str result = parseStr4Name(data, start, stop);
			if (D)
				System.out.println("parseName=<<" + result.getValue() + ">>");

			return new Name(result.getCurrent(), result.getValue());

		} else if ((pr = previewNextIdPart(data, start, stop)).isWorking()) {

			Id result = parseId(data, start, stop);
			if (D)
				System.out.println("parseName=<<" + result.getValue() + ">>");

			return new Name(result.getCurrent(), result.getValue());

		} else
			throw new ParseException("Warning on parseName preview condition.");
	}

	private Value parseValue(String data, int start, int stop)
			throws ParseException {
		PreviewResult pr = null;
		if ((pr = previewNextGridOpener(data, start, stop)).isWorking()) {

			DataGrid gridResult = parseGrid(data, start, stop);
			if (D)
				System.out.println("parseValue=<<" + gridResult + ">>");

			return new Value(gridResult.getCurrent(), gridResult);

		} else if ((pr = previewNextAttrListOpener(data, start, stop))
				.isWorking()) {

			AttributeList attrListResult = parseAttrList4Value(data, start,
					stop);
			if (D)
				System.out.println("parseValue=<<" + attrListResult + ">>");

			return new Value(attrListResult.getCurrent(), attrListResult);

		} else if ((pr = previewNextStrOpener(data, start, stop)).isWorking()) {

			Str result = parseStr4Value(data, start, stop);
			if (D)
				System.out.println("parseValue=<<" + result.getValue() + ">>");

			return new Value(result.getCurrent(), result.getValue());

		} else if ((pr = previewNextDigit(data, start, stop)).isWorking()) {

			Num result = parseNum(data, start, stop);
			if (D)
				System.out.println("parseValue=<<" + result.getValue() + ">>");

			return new Value(result.getCurrent(), result.getValue());

		} else if ((pr = previewNextIdPart(data, start, stop)).isWorking()) {

			Id result = parseId(data, start, stop);
			if (D)
				System.out.println("parseValue=<<" + result.getValue() + ">>");

			return new Value(result.getCurrent(), result.getValue());

		} else
			throw new ParseException("Warning on parseValue preview condition.");
	}

	private Str parseStr4Name(String data, int start, int stop) {
		int current = start;
		char ch = data.charAt(current);
		while (ch != '\"')
			ch = data.charAt(++current);
		int strBegin = current;
		do
			ch = data.charAt(++current);
		while (ch != '\"');
		int strEnd = ++current;
		String result = data.substring(strBegin, strEnd);
		if (D)
			System.out.println("parseStr4Name=" + result);
		return new Str(current, result);
	}

	private Str parseStr4Value(String data, int start, int stop) {
		int current = start;
		char ch = data.charAt(current);
		while (ch != '\"')
			ch = data.charAt(++current);
		int strBegin = current;
		do
			ch = data.charAt(++current);
		while (ch != '\"');
		int strEnd = ++current;
		String result = data.substring(strBegin, strEnd);
		if (D)
			System.out.println("parseStr4Value=" + result);
		return new Str(current, result);
	}

	private Id parseId(String data, int start, int stop) {
		int current = start;
		char ch = data.charAt(current);
		while (!("ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz"
				.indexOf(ch) > -1))
			// while (!Character.isJavaIdentifierStart(ch))
			ch = data.charAt(++current);
		int strBegin = current;
		boolean search = true;
		do {
			ch = data.charAt(++current);
			if (ch == ' ')
				search = false;
			else if (ch == '\t')
				search = false;
			else if (ch == '\r')
				search = false;
			else if (ch == '\n')
				search = false;
			else if (ch == ':')
				search = false;

			else if (ch == ',')
				search = false;
			else if (ch == ']')
				search = false;
			else if (ch == '}')
				search = false;

		} while (search);
		int strEnd = current;
		String result = data.substring(strBegin, strEnd);
		if (D)
			System.out.println("parseId=" + result);
		return new Id(current, result);
	}

	protected DataGrid parseGrid(String data, int start, int stop)
			throws ParseException {

		Spiska resultList = new Spiska();
		PreviewResult pr = null;
		start = parseGridStart(data, start, stop);
		AttributeList attrList = parseAttrList4Grid(data, start, stop);
		resultList.append(attrList);
		start = attrList.getCurrent();
		while ((pr = previewNextContinue(data, start, stop)).isWorking()) {
			start = parseContinue(data, start, stop);
			attrList = parseAttrList4Grid(data, start, stop);
			resultList.append(attrList);
			start = attrList.getCurrent();
		}
		int current = parseGridEnd(data, start, stop);
		DataGrid result = new DataGrid(current, resultList);
		return result;
	}

	private Num parseNum(String data, int start, int stop) {
		int current = start;
		char ch = data.charAt(current);
		while (!Character.isDigit(ch))
			ch = data.charAt(++current);
		int strBegin = current;
		boolean search = true;
		do {
			ch = data.charAt(++current);

			if (!("0123456789.".indexOf(ch) > -1))
				search = false;

			// if (!Character.isDigit(ch))
			// search = false;
		} while (search);
		int strEnd = current;
		String result = data.substring(strBegin, strEnd);
		if (D)
			System.out.println("parseNum=" + result);
		return new Num(current, result);
	}

	private int parseAttrListStart(String data, int start, int stop)
			throws ParseException {
		int current = start;
		char ch = data.charAt(current);
		while (ch != '{') {
			if (ch != ' ' && ch != '\t' && ch != '\r' && ch != '\n')
				throw new ParseException("Warning on parseAttrListStart issue.");
			ch = data.charAt(++current);
		}
		int strBegin = current;
		int strEnd = ++current;
		if (D)
			System.out.println("parseAttrListStart="
					+ data.substring(strBegin, strEnd));
		return current;
	}

	private int parseAttrListEnd(String data, int start, int stop)
			throws ParseException {
		int current = start;
		char ch = data.charAt(current);
		while (ch != '}') {
			if (ch != ' ' && ch != '\t' && ch != '\r' && ch != '\n')
				throw new ParseException(
						"Warning on parseAttrListEnd issue. current=" + current
								+ " char=" + ch + " --char="
								+ data.charAt(current - 1) + " ++char="
								+ data.charAt(current + 1));
			ch = data.charAt(++current);
		}
		int strBegin = current;
		int strEnd = ++current;
		if (D)
			System.out.println("parseAttrListEnd="
					+ data.substring(strBegin, strEnd));
		return current;
	}

	private int parseGridStart(String data, int start, int stop)
			throws ParseException {
		int current = start;
		char ch = data.charAt(current);
		while (ch != '[') {
			if (ch != ' ' && ch != '\t' && ch != '\r' && ch != '\n')
				throw new ParseException("Warning on parseGridStart issue.");
			ch = data.charAt(++current);
		}
		int strBegin = current;
		int strEnd = ++current;
		if (D)
			System.out.println("parseGridStart="
					+ data.substring(strBegin, strEnd));
		return current;
	}

	private int parseGridEnd(String data, int start, int stop)
			throws ParseException {
		int current = start;
		char ch = data.charAt(current);
		while (ch != ']') {
			if (ch != ' ' && ch != '\t' && ch != '\r' && ch != '\n')
				throw new ParseException("Warning on parseGridEnd issue.");
			ch = data.charAt(++current);
		}
		int strBegin = current;
		int strEnd = ++current;
		if (D)
			System.out.println("parseGridEnd="
					+ data.substring(strBegin, strEnd));
		return current;
	}

	private int parseContinue(String data, int start, int stop)
			throws ParseException {
		int current = start;
		char ch = data.charAt(current);
		while (ch != ',') {
			if (ch != ' ' && ch != '\t' && ch != '\r' && ch != '\n')
				throw new ParseException("Warning on parseContinue issue.");
			ch = data.charAt(++current);
		}
		int strBegin = current;
		int strEnd = ++current;
		if (D)
			System.out.println("parseContinue="
					+ data.substring(strBegin, strEnd));
		return current;
	}

	private int parseDelim(String data, int start, int stop)
			throws ParseException {
		int current = start;
		char ch = data.charAt(current);
		while (ch != ':') {
			if (ch != ' ' && ch != '\t' && ch != '\r' && ch != '\n')
				throw new ParseException("Warning on parseDelim issue.");
			ch = data.charAt(++current);
		}
		int strBegin = current;
		int strEnd = ++current;
		if (D)
			System.out
					.println("parseDelim=" + data.substring(strBegin, strEnd));
		return current;
	}

	private PreviewResult previewNextContinue(String data, int start, int stop) {

		int current = start;
		char ch;
		do {
			ch = data.charAt(current);
			switch (ch) {
			case ',':
				return new PreviewResult(true, current);
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				current++;
				break;
			default:
				return new PreviewResult(false, current);
			}
		} while (true);
	}

	private PreviewResult previewNextGridOpener(String data, int start, int stop) {

		int current = start;
		char ch;
		do {
			ch = data.charAt(current);
			switch (ch) {
			case '[':
				return new PreviewResult(true, current);
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				current++;
				break;
			default:
				return new PreviewResult(false, current);
			}
		} while (true);
	}

	private PreviewResult previewNextAttrListOpener(String data, int start,
			int stop) {

		int current = start;
		char ch;
		do {
			ch = data.charAt(current);
			switch (ch) {
			case '{':
				return new PreviewResult(true, current);
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				current++;
				break;
			default:
				return new PreviewResult(false, current);
			}
		} while (true);
	}

	private PreviewResult previewNextStrOpener(String data, int start, int stop) {

		int current = start;
		char ch;
		do {
			ch = data.charAt(current);
			switch (ch) {
			case '\"':
				return new PreviewResult(true, current);
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				current++;
				break;
			default:
				return new PreviewResult(false, current);
			}
		} while (true);
	}

	private PreviewResult previewNextDigit(String data, int start, int stop) {

		int current = start;
		char ch;
		do {
			ch = data.charAt(current);
			switch (ch) {
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				current++;
				break;
			default:
				if (Character.isDigit(ch))
					return new PreviewResult(true, current);
				return new PreviewResult(false, current);
			}
		} while (true);
	}

	private PreviewResult previewNextIdPart(String data, int start, int stop) {

		int current = start;
		char ch;
		do {
			ch = data.charAt(current);
			switch (ch) {
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				current++;
				break;
			default:
				if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz"
						.indexOf(ch) > -1)
					// if (Character.isJavaIdentifierStart(ch))
					return new PreviewResult(true, current);
				return new PreviewResult(false, current);
			}
		} while (true);
	}

}
