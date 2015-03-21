package mydev.droid.noted;

import mydev.droid.noted.support.EraseTask;
import mydev.droid.noted.support.FinishTask;
import mydev.droid.noted.support.FirstJob;
import mydev.droid.noted.support.MyNotes;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListedObsAct extends ListActivity {
	private MyNotes myNotes;

	public ListedObsAct() {
		super();
		this.myNotes = new MyNotes(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myNotes.load();
		String[] values = myNotes.arryacht();
		ListAdapter adapter = new ArrayAdapter<String>(this,
				R.layout.listedobs, values);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TextView tv = (TextView) v;
		final String selected = tv.getText().toString();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true)
				.setTitle(R.string.title_dlgerase)
				.setIcon(R.drawable.erase)
				.setMessage(FirstJob.DELIM + selected + FirstJob.DELIM)
				.setPositiveButton(R.string.btn_eraseok, new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						myNotes.erase(selected);
						new EraseTask(ListedObsAct.this, myNotes, selected)
								.execute();
					}
				})
				.setNegativeButton(R.string.btn_eraserecon,
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								FirstJob obtain = myNotes.obtain(selected);
								final String nikogda = obtain.sometime();
								final String novel = obtain.wrapfull();
								new AlertDialog.Builder(ListedObsAct.this)
										.setTitle(R.string.btn_eraseok)
										.setCancelable(true)
										.setIcon(R.drawable.erase)
										.setMessage(
												FirstJob.DELIM + novel
														+ FirstJob.DELIM)
										.setPositiveButton(nikogda,
												new OnClickListener() {
													@Override
													public void onClick(
															DialogInterface arg0,
															int arg1) {
//														Log.i(FirstJob.APPINFO,
//																FirstJob.DELIM
//																		+ selected
//																		+ FirstJob.DELIM);
													}
												}).create().show();
							}
						}).create().show();
		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onPause() {
		new FinishTask(this).execute();
		super.onPause();
	}
}
