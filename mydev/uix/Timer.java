package mydev.uix; import java.awt.TextField; import mydev.aaa.Sleeper; import mydev.vutils.Ester; public class Timer extends TextField implements Runnable { final static String DEF_TIME="00:00:00"; public final static short DEF_REF_TIME=(short)(321+123); protected boolean lunched; protected Thread worker1; protected boolean working=false; protected long begin; protected long lastUpdateEnd; protected Ester passTime; private boolean refreshByOtherParties; public Timer() { super(); initTime(DEF_TIME);} public Timer(int columns) { super(columns); initTime(DEF_TIME);} protected void initTime(String time) { setEditable(false); setText(time);} public String getTime() { return passTime !=null ? passTime.toString() : DEF_TIME;} public void startTimer() { this.lunched=true; this.working=true; this.begin=System.currentTimeMillis(); if(!refreshByOtherParties && worker1==null) { worker1=new Thread(this); worker1.start();}} public void stopTimer() { if(lunched) updateTimer(); this.lunched=false;} protected short getRefreshTime() { return DEF_REF_TIME;} public void run() { Sleeper sleeper=new Sleeper(); while(working) { if(lunched) updateTimer(); sleeper.sleep(getRefreshTime());} this.worker1=null;} protected void updateTimer() { long end=System.currentTimeMillis(); if(end-lastUpdateEnd > 997) { long dtSec=(end-begin) / 1000; short hrs=(short)(dtSec / 60 / 60); short mins=(short)((dtSec-hrs * 60 * 60) / 60); short secs=(short)(dtSec-hrs * 60 * 60-mins * 60); String ht=""+hrs; String mt=""+mins; String st=""+secs; ht=ht.length() < 2 ? "0"+ht : ht; mt=mt.length() < 2 ? "0"+mt : mt; st=st.length() < 2 ? "0"+st : st; passTime=new Ester(ht).append(':').append(mt).append(':').append(st); setText(passTime.toString()); lastUpdateEnd=end;}} public void killTimer() { this.working=false;} public void refreshUpdatable() { this.refreshByOtherParties=true; if(lunched) updateTimer();}}