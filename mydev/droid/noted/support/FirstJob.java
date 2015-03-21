package mydev.droid.noted.support;

import java.util.Calendar;
import java.util.Date;

public class FirstJob {

	public static final String APPINFO = "NOTED";
	public static final String DELIM = "";
	final String DEFTEXT = "Sample...";
	final String TIBIDABOO = "..";
	final String SPACE = " - ";
	static final String STORE = "ovva";

	private String data;
	private Date time;

	public FirstJob(String data, Date time) {
		super();
		this.data = data;
		this.time = time;
	}

	public boolean less(FirstJob other) {
		return time.getTime() <= other.time.getTime();
	}

	public String wrap() {
		String msg = wrapMsg();
		String result = msg + SPACE + sometime();
		return result;
	}

	public String wrapfull() {
		// return data + SPACE + sometime();
		return data;
	}

	public String sometime() {
		Calendar c = Calendar.getInstance();
		if (time != null)
			c.setTime(time);
		return c.get(Calendar.DAY_OF_MONTH) + " "
				+ month(c.get(Calendar.MONTH)) + ", " + c.get(Calendar.YEAR)
				+ " " + c.get(Calendar.HOUR_OF_DAY) + "час "
				+ c.get(Calendar.MINUTE) + "мин " + c.get(Calendar.SECOND)
				+ "сек";
	}

	String wrapMsg() {
		if (data == null)
			return new String("");
		if (data.length() <= 20 - 2)
			return new String(data);
		return data.substring(0, 20 - 2) + TIBIDABOO;
	}

	String month(int pos) {
		switch (++pos) {
		case 1:
			return "Январь";
		case 2:
			return "Февраль";
		case 3:
			return "Март";
		case 4:
			return "Апрель";
		case 5:
			return "Май";
		case 6:
			return "Июнь";
		case 7:
			return "Июль";
		case 8:
			return "Август";
		case 9:
			return "Сентябрь";
		case 10:
			return "Октябрь";
		case 11:
			return "Ноябрь";
		}
		return "Декабрь";
	}

	public String getData() {
		return data;
	}

	public String getTime() {
		return "" + time.getTime();
	}

}
