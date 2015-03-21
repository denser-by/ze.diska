package mydev.droid.noted;

import java.util.Date;
import mydev.droid.noted.support.CreateTask;
import mydev.droid.noted.support.FirstJob;
import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

public class ToBeDoneAct extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tobedone);
		EditText edt = (EditText) findViewById(R.id.myNote);
		final String start = edt.getText().toString();
		edt.selectAll();
		edt.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (start.compareTo(((EditText) findViewById(R.id.myNote))
						.getText().toString()) == 0)
					((EditText) findViewById(R.id.myNote)).setText("");
				return false;
			}
		});
	}

	@Override
	protected void onPause() {
		Date time = new Date();
		String myNoteStr = ((EditText) findViewById(R.id.myNote)).getText()
				.toString();
		FirstJob firstJob = new FirstJob(myNoteStr, time);
//		Log.i(FirstJob.APPINFO, FirstJob.DELIM + firstJob.wrap()
//				+ FirstJob.DELIM);
		new CreateTask(ToBeDoneAct.this, firstJob).execute();
		super.onPause();
	}
}
