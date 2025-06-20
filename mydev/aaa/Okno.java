package mydev.aaa;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Graphics;
import java.awt.Image;
public class Okno extends Sleeper implements Runnable, Move {
short ms = 500;
short w = 10, h = 10, x = 10, y = 10;
Kadr kadr;
Frame fr;
String id;
Graphics ics;
Window wnd;
Shorty rt;
public Okno() { this.rt = Enter.instance().shorty(); }
public void run() {
Shorty or = Enter.instance().shorty();
while(fr != null && kadr != null) {
kadr.make(ics, or.rev(w, 0), or.rev(h, 0));
sleep(ms);
}
}
public Image canva(short i, short e) { return fr != null ? fr.createImage(i,e) : null; }
public Okno kadr(Kadr kadr) { this.kadr = kadr; return this; }
public Okno size(short w, short h) { this.w = w; this.h = h; return this; }
public Okno move(short x, short y) { this.x = x; this.y = y; return this; }
public Slip memo() { return new Slip("" + w + "," + h + "," + x + "," + y, id); }
public boolean recall(Slip item) {
String ati = new String(item.symbols());
int idx = ati.indexOf(","); if(idx<0)return false;
w = (short)Long.parseLong(ati.substring(0, idx));
ati = ati.substring(idx + 1);
idx = ati.indexOf(","); if(idx<0)return false;
h = (short)Long.parseLong(ati.substring(0, idx));
ati = ati.substring(idx + 1);
idx = ati.indexOf(","); if(idx<0)return false;
x = (short)Long.parseLong(ati.substring(0, idx));
ati = ati.substring(idx + 1);
y = (short)Long.parseLong(ati);
return true;
}
public void out() { this.hi = null; }
private Shift hi;
public void in(Shift hi) {
if( !(this.hi != null && this.hi.dx == -hi.dx && this.hi.dy == -hi.dy) ) {
move(rt.rev(x,hi.dx), rt.rev(y, hi.dy));
this.hi = hi;
wnd.reshape(x, y, w, h);
}
}
public Okno show(Stop op, String id) {
Slip slip = Enter.instance().slipstream().get(id);
if(slip != null) recall(slip);
if(fr == null) {
this.id = id;
wnd = new Sandbox(fr = new Frame(id), op, this);
wnd.reshape(x, y, w, h);
wnd.show();
this.ics = wnd.getGraphics();
new Thread(this).start();
} else {
wnd.reshape(x, y, w, h);
wnd.show();
}
return this;
}
public Okno hide() {
if(fr != null) wnd.hide();
return this;
}
public Slip finish() {
if(fr != null) { wnd.hide(); fr.dispose(); fr = null; kadr = null; }
return memo();
}
public Okno delay(short ms) { this.ms = ms; return this; }
}