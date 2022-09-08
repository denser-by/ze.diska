package mydev.webobj;

import mydev.webobj.parser.AttributeList;

public interface WebObjectsParser {

	AttributeList parseData(String objectsData) throws ParseException;

}