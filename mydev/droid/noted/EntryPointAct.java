package mydev.droid.noted;

import mydev.droid.noted.support.FinishTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EntryPointAct extends Activity {
	private WentAway wentAway;

	public EntryPointAct() {
		super();
		this.wentAway = new WentAway();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entrypoint);
		((Button) findViewById(R.id.make_btn))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						wentAway.yep();
						Intent intent = new Intent(EntryPointAct.this,
								ToBeDoneAct.class);
						startActivity(intent);
					}
				});
		((Button) findViewById(R.id.erase_btn))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						wentAway.yep();
						Intent intent = new Intent(EntryPointAct.this,
								ListedObsAct.class);
						startActivity(intent);
					}
				});
	}

	@Override
	protected void onResume() {
		if (wentAway.check()) {
			wentAway.skip();
			new FinishTask(this).execute();
		}
		super.onResume();
	}
}

class WentAway extends Object {
	private boolean item = false;

	synchronized void yep() {
		item = true;
	}

	synchronized boolean check() {
		boolean ck = item;
		return ck;
	}

	synchronized void skip() {
		item = false;
	}
}
