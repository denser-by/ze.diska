package mydev.droid.noted.support;

import android.app.Activity;
import android.os.AsyncTask;

public class CreateTask extends AsyncTask<Void, Void, Void> {
	private Activity act;
	private FirstJob firstJob;
	private MyNotes myNotes;

	public CreateTask(Activity act, FirstJob firstJob) {
		super();
		this.act = act;
		this.firstJob = firstJob;
		this.myNotes = new MyNotes(act);
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		myNotes.load();
		myNotes.create(firstJob);
		myNotes.save();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		new FinishTask(act).execute();
		super.onPostExecute(result);
	}
}
