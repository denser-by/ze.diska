package mydev.vutils;
public class Time {
long t1;
long t2;
public Time() {
  }
long ms() {
 return System.currentTimeMillis();
 }
void sleep() {
 try {
 Thread.sleep(13);
 }
 catch(InterruptedException ex) {
  }
 }
public void drop() {
 t1 = 0L;
 t2 = 0L;
 }
public void start() {
 if(t1 <= 0L)
 t1 = ms();
 sleep();
 }
public long trust() {
 if(t2 <= 0L)
 t2 = ms();
 sleep();
 return (t2-t1);
 }
public Ester sec() {
 return new Ester("").append((t2-t1)/1000L).append('s');
 }
public Ester min() {
 return new Ester("").append((t2-t1)/1000L/60L).append('m');
 }
public Ester hrs() {
 return new Ester("").append((t2-t1)/1000L/60L/60L).append('h');
 }
public Ester vse() {
 return hrs().append('_').append(min()).append('_').append(sec());
 }
public Ester ess() {
return hrs().append('_').append((t2-t1)/1000L/60L - ((t2-t1)/1000L/60L/60L)*60L) .append("m_").append((t2-t1)/1000L - ((t2-t1)/1000L/60L)*60L).append('s');
}
}