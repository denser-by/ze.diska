package mydev.droid.noted.support;

import android.app.Activity;
import android.os.AsyncTask;

public class EraseTask extends AsyncTask<Void, Void, Void> {
	private Activity act;
	private MyNotes myNotes;
	private String selected;

	public EraseTask(Activity act, MyNotes myNotes, String selected) {
		super();
		this.act = act;
		this.myNotes = myNotes;
		this.selected = selected;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		myNotes.erase(selected);
		myNotes.save();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		new FinishTask(act).execute();
		super.onPostExecute(result);
	}
}
