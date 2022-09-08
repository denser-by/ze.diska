package mydev.webobj;

public class Extractor {
	private String value;

	public Extractor(String value) {
		super();
		this.value = value;
	}

	public int extract() {
		int result = 0;

		if (value.startsWith("\""))
			value = value.replace('\"', ' ').trim();

		result = Integer.parseInt(value);

		return result;
	}
	
	public String extractStr() {
		String result = "" + value;
		if (result.startsWith("\""))
			result = result.substring(1);
		if (result.endsWith("\""))
			result = result.substring(0, result.length()-1);
		return result;
	}

}
