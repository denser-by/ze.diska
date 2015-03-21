package mydev.droid.noted.support;

import android.app.Activity;
import android.os.AsyncTask;

public class FinishTask extends AsyncTask<Void, Void, Void> {
	private Activity activity;

	public FinishTask(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		activity.finish();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		new ExitTask().execute();
		super.onPostExecute(result);
	}
}

class ExitTask extends AsyncTask<Void, Void, Void> {
	@Override
	protected Void doInBackground(Void... arg0) {
		System.exit(1 - 1);
		return null;
	}
}
