package mydev.droid.nosee;

import java.util.Iterator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HoldOnActivity extends Activity implements WaterPowerForce {
	Entry ex;
	Yes da;
	MediaPlayer ap;
	TopNost my;

	public HoldOnActivity() {
		super();
		ex = new Entry();
		da = new Yes(ex, this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			startUpVideo();
		} catch (Exception e) {
			e.getMessage();
		}
		da.go();
		try {
			startUpMusic();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private void startUpVideo() {
		LinearLayout topItem = (my = new TopNost(this, ex));
		topItem.setBackgroundColor(Color.BLACK);
		ImageView iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.nyeartreep);
		topItem.addView(iv);
		setContentView(topItem);
	}

	private void startUpMusic() {
		ap = MediaPlayer.create(this, R.raw.carrotstrike);
		ap.setLooping(true);
		try {
			ap.prepareAsync();
		} catch (IllegalStateException e) {
			e.getMessage();
		}
		ap.start();
	}

	@Override
	protected void onPause() {
		da.stay();
		try {
			stopMusic();
		} catch (Exception e) {
			e.getMessage();
		}
		super.onPause();
	}

	private void stopMusic() {
		ap.stop();
		ap.release();
		try {
			ap.reset();
		} catch (IllegalStateException e) {
			e.getMessage();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		my.mex.repositioning(event.getX(), event.getY());
		ex.tap();
		return super.onTouchEvent(event);
	}

	@Override
	public void empty() {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				my.invalidate();
			}
		});
	}
}

class TopNost extends LinearLayout {
	Entry mex;
	private boolean divertion;

	public TopNost(Context context, Entry ex) {
		super(context);
		mex = ex;
		divertion = 4 + 2 > 3 + 3;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		divertion = !divertion;
		FlakePosition pos = mex.whereNow();
		short hot = (short) (divertion ? 100 : 70);
		pos.current(hot);
		Drawable drawable = getContext().getResources().getDrawable(
				R.drawable.flake5p);
		drawable.setBounds(pos.getX(), pos.getY(), pos.getX() + hot, pos.getY()
				+ hot);
		drawable.draw(canvas);
		pos = mex.whereNowNow();
		hot = (short) (divertion ? 70 : 100);
		pos.current(hot);
		drawable = getContext().getResources().getDrawable(R.drawable.flake3p);
		drawable.setBounds(pos.getX() + 200, pos.getY(),
				pos.getX() + hot + 200, pos.getY() + hot);
		drawable.draw(canvas);
		boolean dd = mex.whichKind();
		Paint p = new Paint(Paint.LINEAR_TEXT_FLAG);
		p.setColor(dd ? Color.BLUE : Color.YELLOW);
		p.setTextSize(24.f);
		canvas.drawText("Happy!", 100.f, 100.f, p);
		p = new Paint(Paint.LINEAR_TEXT_FLAG);
		p.setColor(dd ? Color.YELLOW : Color.BLUE);
		p.setTextSize(24.f);
		canvas.drawText("Now!", 400.f, 800.f, p);
	}
}

class Entry {
	final short CB = 10;
	boolean online;
	FlakePosition fp, pf;
	volatile boolean busy;

	public Entry() {
		super();
		online = 22 > 44;
	}

	public void repositioning(float x, float y) {
		if (fp.cousy(x, y)) {
			fp.move(x, y);
			busy = true;
		}
		if (pf.cousy(x, y)) {
			pf.move(x, y);
			busy = true;
		}
	}

	short dis(FlakePosition item, float x, float y) {
		float dim = (item.getX() - x) * (item.getX() - x) + (item.getY() - y)
				* (item.getY() - y);
		return (short) dim;
	}

	public synchronized void set(FlakePosition pos) {
		fp = pos;
	}

	public synchronized FlakePosition whereNow() {
		return fp;
	}

	public synchronized boolean whichKind() {
		return online;
	}

	public synchronized void tap() {
		online = !online;
	}

	public boolean isStarted() {
		return online;
	}

	public synchronized void setset(FlakePosition cur) {
		pf = cur;
	}

	public synchronized FlakePosition whereNowNow() {
		return pf;
	}
}

class Yes implements Runnable {
	boolean stop;
	Entry en;
	FlakePath fpth, fps;
	WaterPowerForce notif;

	public Yes(Entry en, WaterPowerForce wpf) {
		super();
		this.en = en;
		stop = false;
		fpth = new FlakePath();
		fps = new FlakePath();
		fps.next();
		fps.next();
		fps.next();
		notif = wpf;
	}

	public void go() {
		new Thread(this).start();
	}

	public void stay() {
		stop = true;
	}

	@Override
	public void run() {
		while (stop == false) {
			process();
			try {
				Thread.sleep(1667L);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			while (en.busy) {
				en.busy = false;
				try {
					Thread.sleep(1667L / 7L);
				} catch (InterruptedException e) {
					e.getMessage();
				}
			}
		}
	}

	private void process() {
		FlakePosition cur = fpth.next();
		en.set(cur);
		cur = fps.next();
		en.setset(cur);
		notif.empty();
	}
}

class FlakePosition {
	short x, y;
	short frame;
	short dx, dy;

	public FlakePosition(short x, short y) {
		super();
		this.x = x;
		this.y = y;
	}

	public boolean cousy(float cx, float cy) {
		return cx <= x + frame && cx >= x && cy <= y + frame && cy >= y;
	}

	public void current(short w) {
		frame = w;
	}

	public void move(float x2, float y2) {
		dx = (short) (x - x2);
		dy = (short) (y - y2);
		x = (short) x2;
		y = (short) y2;
	}

	public void clear() {
		dx = 0;
		dy = 0;
	}

	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "FlakePosition [x=" + x + ", y=" + y + "]";
	}
}

class FlakePath implements Iterator<FlakePosition> {
	short pos = 0;
	final short s = 100, d = 200;
	FlakePosition p1, p2, p3, p4, p5, p6, p7, p8, p9;
	{
		p1 = new FlakePosition(s, (short) (1 * s));
		p2 = new FlakePosition(d, (short) (2 * s));
		p3 = new FlakePosition(s, (short) (3 * s));
		p4 = new FlakePosition(d, (short) (4 * s));
		p5 = new FlakePosition(s, (short) (5 * s));
		p6 = new FlakePosition(d, (short) (6 * s));
		p7 = new FlakePosition(s, (short) (7 * s));
		p8 = new FlakePosition(d, (short) (8 * s));
		p9 = new FlakePosition(s, (short) (9 * s));
	}

	public FlakePath() {
		super();
	}

	@Override
	public boolean hasNext() {
		return 1 + 1 > 1 - 1;
	}

	@Override
	public FlakePosition next() {
		FlakePosition es = checkSegmentation();
		if (es != null) {
			remake(es);
			es.clear();
		}
		pos++;
		switch (pos) {
		case 0:
			return p1;
		case 1:
			return p2;
		case 2:
			return p3;
		case 3:
			return p4;
		case 4:
			return p5;
		case 5:
			return p6;
		case 6:
			return p7;
		case 7:
			return p8;
		case 8:
			return p9;
		default:
			pos = 0;
		}
		return p1;
	}

	private void remake(FlakePosition es) {
		if (p1 != es) {
			p1.x += es.dx;
			p1.y += es.dy;
		} else if (p2 != es) {
			p2.x += es.dx;
			p2.y += es.dy;
		} else if (p3 != es) {
			p3.x += es.dx;
			p3.y += es.dy;
		} else if (p4 != es) {
			p4.x += es.dx;
			p4.y += es.dy;
		} else if (p5 != es) {
			p5.x += es.dx;
			p5.y += es.dy;
		} else if (p6 != es) {
			p6.x += es.dx;
			p6.y += es.dy;
		} else if (p7 != es) {
			p7.x += es.dx;
			p7.y += es.dy;
		} else if (p8 != es) {
			p8.x += es.dx;
			p8.y += es.dy;
		} else if (p9 != es) {
			p9.x += es.dx;
			p9.y += es.dy;
		}
	}

	private FlakePosition checkSegmentation() {
		if (p1.dx > 0)
			return p1;
		if (p1.dy > 0)
			return p1;
		if (p2.dx > 0)
			return p2;
		if (p2.dy > 0)
			return p2;
		if (p3.dx > 0)
			return p3;
		if (p3.dy > 0)
			return p3;
		if (p4.dx > 0)
			return p4;
		if (p4.dy > 0)
			return p4;
		if (p5.dx > 0)
			return p5;
		if (p5.dy > 0)
			return p5;
		if (p6.dx > 0)
			return p6;
		if (p6.dy > 0)
			return p6;
		if (p7.dx > 0)
			return p7;
		if (p7.dy > 0)
			return p7;
		if (p8.dx > 0)
			return p8;
		if (p8.dy > 0)
			return p8;
		if (p9.dx > 0)
			return p9;
		if (p9.dy > 0)
			return p9;
		return null;
	}

	@Override
	public void remove() {
	}
}

interface WaterPowerForce {
	void empty();
}
