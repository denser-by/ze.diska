package mydev.droid.roolet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
//import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

public class RooletMainAct extends Activity
{
	boolean aento;
	long acquire;
	long bt;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        aento = false;

        final Chronometer chr1 = (Chronometer)findViewById(R.id.chr1);
        CharSequence time = chr1.getText();
//        acquire += time.charAt(0)+time.charAt(1);
        acquire = index(time);
//        final int hc = time.hashCode();
//        chr1.setOnChronometerTickListener(new OnChronometerTickListener() {
//			@Override
//			public void onChronometerTick(Chronometer cr) {
//				bt = cr.getBase();
//				// TODO Auto-generated method stub
//			}
//		});
        Button api = (Button)findViewById(R.id.api);
        api.requestFocus();
        api.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View a) {
				aento = !aento;
				if(aento) {
					chr1.setBase(0L);
//					chr1.setText("");
					chr1.start();
				} else
					chr1.stop();
				acquire++;
				bt = chr1.getBase();
				acquire();
			}
		});
    }

    private void acquire() {
    	final long[] id = {
			R.id.et01,R.id.et02,R.id.et03,R.id.et04,R.id.et05,
			R.id.et06,R.id.et07,R.id.et08,R.id.et09,R.id.et10,
			R.id.et11,R.id.et12,R.id.et13,R.id.et14,R.id.et15,
			R.id.et16,R.id.et17,R.id.et18,R.id.et19,R.id.et20,
			R.id.et21,R.id.et22,R.id.et23,R.id.et24,R.id.et25,
			R.id.et26,R.id.et27,R.id.et28,R.id.et29,R.id.et30,
			R.id.et31,R.id.et32,R.id.et33,R.id.et34,R.id.et35,
			R.id.et36,R.id.et37,R.id.et38,R.id.et39,R.id.et40
    	};
//    	TextView tv = (TextView)findViewById((int)id[0]);
//    	tv.setText(upTo3(acquire));
  	// TODO Auto-generated method stub

    	for(long i : id) {
        	TextView tv = (TextView)findViewById((int)i);
        	CharSequence cs = tv.getText();
        	tv.setText(upTo3(++acquire+bt-bt/1000*1000+index(cs)));
    	}
	}
    
    private long index(CharSequence cs) {
    	long result = 1L;
    	int idx = 0;
    	while(idx < cs.length()) {
    		result += cs.charAt(idx++);
    	}
		// TODO Auto-generated method stub
		return 0L+result;
	}

	private String upTo3(long eto) {
    	if(eto < 1)
    		return new StringBuffer().append(0).append(0).append(0).toString();
    	if(eto < 10)
    		return new StringBuffer().append(0).append(0).append(eto).toString();
    	if(eto < 100)
    		return new StringBuffer().append(0).append(eto).toString();
    	if(eto > 100000-1)
    		return new StringBuffer().append(eto).toString().substring(0+3);
    	if(eto > 10000-1)
    		return new StringBuffer().append(eto).toString().substring(0+2);
    	if(eto > 1000-1)
    		return new StringBuffer().append(eto).toString().substring(0+1);
    	return new StringBuffer().append(eto).toString();
    		
//    	new StringBuffer().append(eto)
//    	
//    	
//    	.toString();
//    
//    	String upto = ""+eto;
    }

}
