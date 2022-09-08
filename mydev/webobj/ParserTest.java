package mydev.webobj;

import mydev.webobj.parser.Attribute;
import mydev.webobj.parser.AttributeList;
import mydev.webobj.parser.DataGrid;
import mydev.webobj.parser.Value;

public class ParserTest {
	public static void main(String[] args) {

		System.out.println("------------------------------");

		WebObjectsParser parser = new Parser();

		String objectsData = "" +

		"{" + "data : ["

		+ "{" + "type:gif," + "images: {" + "fixed_width_small: {"
				+ "url: \"http://sdfsdfsdf_small\"," + "width: 100,"
				+ "height: 50," + "size: 22121" + "}," + "original : {"
				+ "url: \"http://wafwdvxcb_original\"," + "width: 400,"
				+ "height: 140," + "size: 92121" + "}" + "},"
				+ "title: \"Some explanation\"" + "}," + "{"

				+ "type:gif," + "images: {" + "fixed_width_small: {"
				+ "url: \"http://sdfsdfsdf1_small\"," + "width: 101,"
				+ "height: 51," + "size: 22122" + "}," + "original : {"
				+ "url: \"http://wafwdvxc1_original\"," + "width: 401,"
				+ "height: 141," + "size: 92123" + "}" + "},"
				+ "title: \"Some explanation 2\""

				+ "}" + "]," + "meta : {" + "  status:200," + "  msg: true"
				+ "}," + "pagination: {" + "  total_count: \"199\","
				+ "  count:	25," + "  offset:0" + "}" + "}";

		AttributeList parseData = null;
		try {
			parseData = parser.parseData(objectsData);

			Attribute attrPagination = parseData.getAttribute("\"pagination\"");
			if (attrPagination == null)
				attrPagination = parseData.getAttribute("pagination");

			Value value = attrPagination.getValue();

			AttributeList attributeList = value.getValueAttributeList();

			Attribute attrTotalCount = attributeList
					.getAttribute("\"total_count\"");
			if (attrTotalCount == null)
				attrTotalCount = attributeList.getAttribute("total_count");
			int totalCount = new Extractor(attrTotalCount.getValue().getValue())
					.extract();
			assertEq(totalCount, 199);

			Attribute attrCount = attributeList.getAttribute("\"count\"");
			if (attrCount == null)
				attrCount = attributeList.getAttribute("count");
			int count = new Extractor(attrCount.getValue().getValue())
					.extract();
			assertEq(count, 25);

			Attribute attrOffset = attributeList.getAttribute("\"offset\"");
			if (attrOffset == null)
				attrOffset = attributeList.getAttribute("offset");
			int offset = new Extractor(attrOffset.getValue().getValue())
					.extract();
			assertEq(offset, 0);

			Attribute attrData = parseData.getAttribute("\"data\"");
			if (attrData == null)
				attrData = parseData.getAttribute("data");

			value = attrData.getValue();

			DataGrid dataGrid = value.getValueDataGrid();

			long dataGridSize = dataGrid.size();
			assertEq((int) dataGridSize, 2);

			checkFirst(dataGrid);
			checkSecond(dataGrid);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("------------------------------");

	}

	private static void checkFirst(DataGrid dataGrid) throws ParseException {
		AttributeList first = dataGrid.get(0);

		Attribute title = first.getAttribute("\"title\"");
		if (title == null)
			title = first.getAttribute("title");
		String titleValue = title.getValue().getValue();
		assertEq("\"Some explanation\"", titleValue);

		Attribute images = first.getAttribute("\"images\"");
		if (images == null)
			images = first.getAttribute("images");

		AttributeList imageSet = images.getValue().getValueAttributeList();

		Attribute fixedWidthSmall = imageSet
				.getAttribute("\"fixed_width_small\"");
		if (fixedWidthSmall == null)
			fixedWidthSmall = imageSet.getAttribute("fixed_width_small");
		Attribute original = imageSet.getAttribute("\"original\"");
		if (original == null)
			original = imageSet.getAttribute("original");

		AttributeList fixedWidthSmallSet = fixedWidthSmall.getValue()
				.getValueAttributeList();
		AttributeList originalSet = original.getValue().getValueAttributeList();

		Attribute url = fixedWidthSmallSet.getAttribute("\"url\"");
		if (url == null)
			url = fixedWidthSmallSet.getAttribute("url");
		Attribute urlOriginal = originalSet.getAttribute("\"url\"");
		if (urlOriginal == null)
			urlOriginal = originalSet.getAttribute("url");
		String urlValue = url.getValue().getValue();
		assertEq(urlValue, "\"http://sdfsdfsdf_small\"");
		String urlOriginalValue = urlOriginal.getValue().getValue();
		assertEq(urlOriginalValue, "\"http://wafwdvxcb_original\"");

		Attribute width = fixedWidthSmallSet.getAttribute("\"width\"");
		if (width == null)
			width = fixedWidthSmallSet.getAttribute("width");
		int widthValue = new Extractor(width.getValue().getValue()).extract();
		assertEq(widthValue, 100);

		Attribute height = fixedWidthSmallSet.getAttribute("\"height\"");
		if (height == null)
			height = fixedWidthSmallSet.getAttribute("height");
		int heightValue = new Extractor(height.getValue().getValue()).extract();
		assertEq(heightValue, 50);

		Attribute size = fixedWidthSmallSet.getAttribute("\"size\"");
		if (size == null)
			size = fixedWidthSmallSet.getAttribute("size");
		int sizeValue = new Extractor(size.getValue().getValue()).extract();
		assertEq(sizeValue, 22121);
	}

	private static void checkSecond(DataGrid dataGrid) throws ParseException {
		AttributeList first = dataGrid.get(1);

		Attribute title = first.getAttribute("\"title\"");
		if (title == null)
			title = first.getAttribute("title");
		String titleValue = title.getValue().getValue();
		assertEq("\"Some explanation 2\"", titleValue);

		Attribute images = first.getAttribute("\"images\"");
		if (images == null)
			images = first.getAttribute("images");

		AttributeList imageSet = images.getValue().getValueAttributeList();

		Attribute fixedWidthSmall = imageSet
				.getAttribute("\"fixed_width_small\"");
		if (fixedWidthSmall == null)
			fixedWidthSmall = imageSet.getAttribute("fixed_width_small");
		Attribute original = imageSet.getAttribute("\"original\"");
		if (original == null)
			original = imageSet.getAttribute("original");

		AttributeList fixedWidthSmallSet = fixedWidthSmall.getValue()
				.getValueAttributeList();
		AttributeList originalSet = original.getValue().getValueAttributeList();

		Attribute url = fixedWidthSmallSet.getAttribute("\"url\"");
		if (url == null)
			url = fixedWidthSmallSet.getAttribute("url");
		Attribute urlOriginal = originalSet.getAttribute("\"url\"");
		if (urlOriginal == null)
			urlOriginal = originalSet.getAttribute("url");
		String urlValue = url.getValue().getValue();
		assertEq(urlValue, "\"http://sdfsdfsdf1_small\"");
		String urlOriginalValue = urlOriginal.getValue().getValue();
		assertEq(urlOriginalValue, "\"http://wafwdvxc1_original\"");

		Attribute width = fixedWidthSmallSet.getAttribute("\"width\"");
		if (width == null)
			width = fixedWidthSmallSet.getAttribute("width");
		int widthValue = new Extractor(width.getValue().getValue()).extract();
		assertEq(widthValue, 101);

		Attribute height = fixedWidthSmallSet.getAttribute("\"height\"");
		if (height == null)
			height = fixedWidthSmallSet.getAttribute("height");
		int heightValue = new Extractor(height.getValue().getValue()).extract();
		assertEq(heightValue, 51);

		Attribute size = fixedWidthSmallSet.getAttribute("\"size\"");
		if (size == null)
			size = fixedWidthSmallSet.getAttribute("size");
		int sizeValue = new Extractor(size.getValue().getValue()).extract();
		assertEq(sizeValue, 22122);
	}

	static void assertEq(int f1, int f2) throws ParseException {
		if (f1 != f2)
			throw new ParseException("" + f1 + " != " + f2 + " values");
	}

	static void assertEq(String f1, String f2) throws ParseException {
		if (f1.compareTo(f2) != 0)
			throw new ParseException("" + f1 + " != " + f2 + " values");
	}

}
