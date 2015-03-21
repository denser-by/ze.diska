package mydev.droid.hawai;

import java.util.Vector;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WaiWaiHaHaActivity extends Activity implements OnClickListener {
	private boolean onDestroyOnce = false;
	protected Vector<String> story;

	public WaiWaiHaHaActivity() {
		super();
		story = new Vector<String>();
	}

	@Override
	protected void onDestroy() {
		if (onDestroyOnce) {
			super.onDestroy();
			System.exit(1 - 1);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		onDestroyOnce = true;
		((ImageButton) findViewById(R.id.st_btn_1)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_2)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_3)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_4)).setOnClickListener(this);
		((ImageButton) findViewById(R.id.st_btn_5)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_6)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_7)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_8)).setOnClickListener(this);
		((ImageButton) findViewById(R.id.st_btn_9)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_0)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_000)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_plus)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_minus)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_multiply)).setOnClickListener(this);
		((Button) findViewById(R.id.st_btn_divide)).setOnClickListener(this);
		((Button) findViewById(R.id.dot_btn)).setOnClickListener(this);
		((Button) findViewById(R.id.escape_btn)).setOnClickListener(this);
		((Button) findViewById(R.id.clear_btn)).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		onClickB(arg0);
		onClickIB(arg0);
	}

	public void onClickB(View arg0) {
		Button curBtn = null;
		if (arg0 != null && arg0 instanceof android.widget.Button) {
			curBtn = (android.widget.Button) arg0;
			CharSequence btnFace = curBtn.getText();
			story.addElement(btnFace.toString());
			int btnId = curBtn.getId();
			switch (btnId) {
			case R.id.st_btn_1:
				oneButtonPressed();
				break;
			case R.id.st_btn_2:
				twoButtonPressed();
				break;
			case R.id.st_btn_3:
				threeButtonPressed();
				break;
			case R.id.st_btn_4:
				fourButtonPressed();
				break;
			case R.id.st_btn_5:
				fiveButtonPressed();
				break;
			case R.id.st_btn_6:
				sixButtonPressed();
				break;
			case R.id.st_btn_7:
				sevenButtonPressed();
				break;
			case R.id.st_btn_8:
				eightButtonPressed();
				break;
			case R.id.st_btn_9:
				nineButtonPressed();
				break;
			case R.id.st_btn_0:
				ouButtonPressed();
				break;
			case R.id.st_btn_000:
				ouououButtonPressed();
				break;
			case R.id.st_btn_plus:
				plusButtonPressed();
				break;
			case R.id.st_btn_minus:
				minusButtonPressed();
				break;
			case R.id.st_btn_multiply:
				multiplyButtonPressed();
				break;
			case R.id.st_btn_divide:
				divideButtonPressed();
				break;
			case R.id.dot_btn:
				dotButtonPressed();
				break;
			case R.id.clear_btn:
				clearButtonPressed();
				break;
			case R.id.escape_btn:
				escapeButtonPressed();
				break;
			default:
				break;
			}
		}
	}

	public void onClickIB(View arg0) {
		ImageButton curBtn = null;
		if (arg0 != null && arg0 instanceof android.widget.ImageButton) {
			curBtn = (android.widget.ImageButton) arg0;
			int btnId = curBtn.getId();
			switch (btnId) {
			case R.id.st_btn_1:
				oneButtonPressed();
				break;
			case R.id.st_btn_2:
				twoButtonPressed();
				break;
			case R.id.st_btn_3:
				threeButtonPressed();
				break;
			case R.id.st_btn_4:
				fourButtonPressed();
				break;
			case R.id.st_btn_5:
				fiveButtonPressed();
				break;
			case R.id.st_btn_6:
				sixButtonPressed();
				break;
			case R.id.st_btn_7:
				sevenButtonPressed();
				break;
			case R.id.st_btn_8:
				eightButtonPressed();
				break;
			case R.id.st_btn_9:
				nineButtonPressed();
				break;
			case R.id.st_btn_0:
				ouButtonPressed();
				break;
			case R.id.st_btn_000:
				ouououButtonPressed();
				break;
			case R.id.st_btn_plus:
				plusButtonPressed();
				break;
			case R.id.st_btn_minus:
				minusButtonPressed();
				break;
			case R.id.st_btn_multiply:
				multiplyButtonPressed();
				break;
			case R.id.st_btn_divide:
				divideButtonPressed();
				break;
			case R.id.dot_btn:
				dotButtonPressed();
				break;
			case R.id.clear_btn:
				clearButtonPressed();
				break;
			case R.id.escape_btn:
				escapeButtonPressed();
				break;
			default:
				break;
			}
		}
	}

	public void oneButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "1");
	}

	public void twoButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "2");
	}

	public void threeButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "3");
	}

	public void fourButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "4");
	}

	public void fiveButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "5");
	}

	public void sixButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "6");
	}

	public void sevenButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "7");
	}

	public void eightButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "8");
	}

	public void nineButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "9");
	}

	public void ouButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "0");
	}

	public void ouououButtonPressed() {
		((TextView) findViewById(R.id.tv_1))
				.setText(((TextView) findViewById(R.id.tv_1)).getText()
						.toString() + "000");
	}

	private boolean digitalEnd() {
		short problem = 0;
		String sequence = ((TextView) findViewById(R.id.tv_1)).getText()
				.toString();
		if (sequence.endsWith("0"))
			problem++;
		if (sequence.endsWith("1"))
			problem++;
		if (sequence.endsWith("2"))
			problem++;
		if (sequence.endsWith("3"))
			problem++;
		if (sequence.endsWith("4"))
			problem++;
		if (sequence.endsWith("5"))
			problem++;
		if (sequence.endsWith("6"))
			problem++;
		if (sequence.endsWith("7"))
			problem++;
		if (sequence.endsWith("8"))
			problem++;
		if (sequence.endsWith("9"))
			problem++;
		if (sequence.endsWith("0."))
			problem++;
		if (sequence.endsWith("1."))
			problem++;
		if (sequence.endsWith("2."))
			problem++;
		if (sequence.endsWith("3."))
			problem++;
		if (sequence.endsWith("4."))
			problem++;
		if (sequence.endsWith("5."))
			problem++;
		if (sequence.endsWith("6."))
			problem++;
		if (sequence.endsWith("7."))
			problem++;
		if (sequence.endsWith("8."))
			problem++;
		if (sequence.endsWith("9."))
			problem++;
		return problem > 0;
	}

	public void plusButtonPressed() {
		if (digitalEnd()) {
			((TextView) findViewById(R.id.tv_1))
					.setText(((TextView) findViewById(R.id.tv_1)).getText()
							.toString() + "+");
			recalculate();
		}
		DotKeeper.instance().clearIt();
	}

	public void minusButtonPressed() {
		if (digitalEnd()) {
			((TextView) findViewById(R.id.tv_1))
					.setText(((TextView) findViewById(R.id.tv_1)).getText()
							.toString() + "-");
			recalculate();
		}
		DotKeeper.instance().clearIt();
	}

	public void multiplyButtonPressed() {
		if (digitalEnd()) {
			((TextView) findViewById(R.id.tv_1))
					.setText(((TextView) findViewById(R.id.tv_1)).getText()
							.toString() + "*");
			recalculate();
		}
		DotKeeper.instance().clearIt();
	}

	public void divideButtonPressed() {
		if (digitalEnd()) {
			((TextView) findViewById(R.id.tv_1))
					.setText(((TextView) findViewById(R.id.tv_1)).getText()
							.toString() + "/");
			recalculate();
		}
		DotKeeper.instance().clearIt();
	}

	private boolean endsOp(String sequence) {
		short counter = 0;
		if (sequence.endsWith("+"))
			counter++;
		if (sequence.endsWith("-"))
			counter++;
		if (sequence.endsWith("*"))
			counter++;
		if (sequence.endsWith("/"))
			counter++;
		return counter > 0;
	}

	private boolean containsOp(String sequence) {
		short counter = 0;
		if (sequence.indexOf('+') > 0 - 1)
			counter++;
		if (sequence.indexOf('-') > 0 - 1)
			counter++;
		if (sequence.indexOf('*') > 0 - 1)
			counter++;
		if (sequence.indexOf('/') > 0 - 1)
			counter++;
		return counter > 0;
	}

	private void recalculate() {
		String sequence = ((TextView) findViewById(R.id.tv_1)).getText()
				.toString();
		if (endsOp(sequence)) {
			String seqVal = sequence.substring(0, sequence.length() - 1);
			String seqNewOp = sequence.substring(sequence.length() - 1);
			if (containsOp(seqVal)) {
				seqVal = process(seqVal);
				sequence = seqVal + seqNewOp;
				((TextView) findViewById(R.id.tv_1)).setText(sequence);
				if (sequence.indexOf('.') < 0)
					DotKeeper.instance().clearIt();
				else
					DotKeeper.instance().isItSet();
			}
		}
	}

	private boolean doubleProc(String dig1, String digOp, String dig2) {
		short marker = 0;
		if (dig1.indexOf('.') > -1)
			marker++;
		if ("/".equals(digOp))
			marker++;
		if (dig2.indexOf('.') > -1)
			marker++;
		return marker > 0;
	}

	private String process(String seqVal) {
		String result = "0";
		try {
			int idxOp = findOp(seqVal);
			String dig1 = seqVal.substring(0, idxOp);
			String dig2 = seqVal.substring(idxOp + 1);
			String digOp = seqVal.substring(idxOp, idxOp + 1);
			if (doubleProc(dig1, digOp, dig2)) {
				Double d1 = new Double(dig1.startsWith(".") ? "0" + dig1 : dig1);
				Double d2 = new Double(dig2.startsWith(".") ? "0" + dig2 : dig2);
				if ("+".equals(digOp)) {
					result = new Double(d1.doubleValue() + d2.doubleValue())
							.toString();
				} else if ("-".equals(digOp)) {
					result = new Double(d1.doubleValue() - d2.doubleValue())
							.toString();
				} else if ("*".equals(digOp)) {
					result = new Double(d1.doubleValue() * d2.doubleValue())
							.toString();
				} else if ("/".equals(digOp)) {
					if (new Double(0.f).equals(d2)) {
						result = "Horosho";
						lockButtons();
					} else
						result = new Double(d1.doubleValue() / d2.doubleValue())
								.toString();
				}
			} else {
				Long s1 = new Long(dig1);
				Long s2 = new Long(dig2);
				if ("+".equals(digOp)) {
					result = Long.valueOf(s1.longValue() + s2.longValue())
							.toString();
				} else if ("-".equals(digOp)) {
					result = Long.valueOf(s1.longValue() - s2.longValue())
							.toString();
				} else if ("*".equals(digOp)) {
					result = Long.valueOf(s1.longValue() * s2.longValue())
							.toString();
				} else if ("/".equals(digOp)) {
					if (s2.longValue() == 0L) {
						result = "Horosho";
						lockButtons();
					} else
						result = Long.valueOf(s1.longValue() / s2.longValue())
								.toString();
				}
			}
		} catch (java.lang.NumberFormatException ex) {
			ex.printStackTrace();
		}
		return result.toString();
	}

	private void lockButtons() {
		((ImageButton) findViewById(R.id.st_btn_1)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_2)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_3)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_4)).setEnabled(false);
		((ImageButton) findViewById(R.id.st_btn_5)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_6)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_7)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_8)).setEnabled(false);
		((ImageButton) findViewById(R.id.st_btn_9)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_0)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_000)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_plus)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_minus)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_multiply)).setEnabled(false);
		((Button) findViewById(R.id.st_btn_divide)).setEnabled(false);
		((Button) findViewById(R.id.dot_btn)).setEnabled(false);
	}

	private void unLockButtons() {
		((ImageButton) findViewById(R.id.st_btn_1)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_2)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_3)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_4)).setEnabled(!false);
		((ImageButton) findViewById(R.id.st_btn_5)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_6)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_7)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_8)).setEnabled(!false);
		((ImageButton) findViewById(R.id.st_btn_9)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_0)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_000)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_plus)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_minus)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_multiply)).setEnabled(!false);
		((Button) findViewById(R.id.st_btn_divide)).setEnabled(!false);
		((Button) findViewById(R.id.dot_btn)).setEnabled(!false);
	}

	private int findOp(String seqVal) {
		int idx = -1;
		if (idx < 0)
			idx = seqVal.indexOf("+");
		if (idx < 0)
			idx = seqVal.indexOf("-");
		if (idx < 0)
			idx = seqVal.indexOf("*");
		if (idx < 0)
			idx = seqVal.indexOf("/");
		return idx;
	}

	public void dotButtonPressed() {
		if (DotKeeper.instance().isItSet() == false)
			((TextView) findViewById(R.id.tv_1))
					.setText(((TextView) findViewById(R.id.tv_1)).getText()
							.toString() + ".");
	}

	public void clearButtonPressed() {
		DotKeeper.instance().clearIt();
		((TextView) findViewById(R.id.tv_1)).setText("");
		story.clear();
		unLockButtons();
	}

	public void escapeButtonPressed() {
		story.clear();
		WaiWaiHaHaActivity.this.finish();
		WaiWaiHaHaActivity.this.onDestroy();
	}
}

class DotKeeper extends Object implements Runnable {
	private Object sync;
	private Boolean value;
	private static DotKeeper instance;
	static {
		instance = new DotKeeper();
	}

	DotKeeper() {
		super();
		run();
	}

	@Override
	public void run() {
		value = null;
		sync = new Object();
	}

	public static DotKeeper instance() {
		return instance;
	}

	public boolean isIt() {
		synchronized (sync) {
			return Boolean.TRUE.equals(value);
		}
	}

	public boolean isItSet() {
		synchronized (sync) {
			boolean result = Boolean.TRUE.equals(value);
			if (!result)
				value = Boolean.valueOf(1 + 2 > 2 - 1);
			return result;
		}
	}

	public void clearIt() {
		synchronized (sync) {
			value = null;
		}
	}
}
