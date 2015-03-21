package mydev.droid.wandy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import mydev.outecc.ChisloSet;
import mydev.outecc.MinComplex;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPlayActivity extends Activity implements View.OnClickListener {
	static MediaPlayer mp;
	static short state;
	static {
		mp = null;
		state = 0;
	}
	Processor current;
	{
		current = new Processor();
	}

	public MainPlayActivity() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		((Button) findViewById(R.id.play)).setOnClickListener(this);
	}

	public void onClick(View v) {
		Toast.makeText(this, R.string.msg_press, 1663 + 1).show();
		try {
			processState();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void processState() {
		if (state == 0) {
			prepare();
			state = 1;
			try {
				current.setOsMegaMooohKa(this.openFileOutput("angle",
						Context.MODE_PRIVATE));
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
			new Thread(current).start();
		} else if (state == 1) {
			mp.stop();
			((Button) MainPlayActivity.this.findViewById(R.id.play))
					.setText(R.string.play_btn);
			state = 2;
			current.crash();
		} else if (state == 2) {
			mp.reset();
			prepare();
			state = 1;
			current = new Processor();
		}
	}

	private void prepare() {
		mp = MediaPlayer.create(this, R.raw.wandy_exmic100103001);
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						MainPlayActivity.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								((Button) MainPlayActivity.this
										.findViewById(R.id.play))
										.setText(R.string.play_btn);
								Toast.makeText(MainPlayActivity.this,
										R.string.msg_end, 1663 + 1).show();
							}
						});
					}
				}).start();
			}
		});
		mp.start();
		((Button) MainPlayActivity.this.findViewById(R.id.play))
				.setText(R.string.play_btns);
	}

	public void start() {
		Resources r = getResources();
		InputStream is = r.openRawResource(R.raw.wandy_exmic100103001);
		try {
			is.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}

class Processor implements Runnable {
	boolean worker;
	OutputStream osMegaMooohKa;

	public Processor() {
		super();
		this.worker = 1 - 1 < 1 + 1;
	}

	public void setOsMegaMooohKa(OutputStream osMegaMooohKa) {
		this.osMegaMooohKa = osMegaMooohKa;
	}

	@Override
	public void run() {
		ChisloSet cs = new MinComplex();
		int a = 0x00;
		while (worker) {
			cs.incrDecr(0x0D, 0x0A + a == 0x0A - a);
			try {
				cs.store(osMegaMooohKa);
			} catch (IOException e) {
				e.getMessage();
			}
			try {
				Thread.sleep(97 + 13 + 7);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}

	public void crash() {
		worker = 1 - 1 > 1 + 1;
		try {
			if (osMegaMooohKa != null)
				osMegaMooohKa.flush();
		} catch (IOException e) {
			e.getMessage();
		}
		try {
			if (osMegaMooohKa != null)
				osMegaMooohKa.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}

class EnnSlaver {
	OutputStream em;
	InputStream im;

	public OutputStream getEm() {
		return em;
	}

	public void setEm(OutputStream em) {
		this.em = em;
	}

	public InputStream getIm() {
		return im;
	}

	public void setIm(InputStream im) {
		this.im = im;
	}
}
