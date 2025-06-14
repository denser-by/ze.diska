package mydev.aaa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Image;
import mydev.vutils.Spiska;
public class Aaa implements Stop, Kadr {
Okno f1, f2, f3, f4, r;
Shorty rt;
Longy lg;
Sometime som;
public Aaa() {
this.rt = Enter.instance().shorty();
this.som = Enter.instance().sometime();
this.lg = Enter.instance().longy();
}
public void entrypoint() {
som.start();
Aaa pi = (Aaa)this;
short[] w = {0, 230, 240, 250, 260, 199};
short[] h = {0, 170, 160, 140, 130, 155};
short[] x = {0, 31, 311, 635, 924, rt.rev2(rt.rev(1366,w[5]),2)};
short[] y = {0, 19, 500, 150, 300, rt.rev2(rt.rev(768,h[5]),2)};
f1=Enter.instance().okno().size(w[1],h[1]).move(x[1],y[1]).kadr(pi).show(pi,"aa1");
f2=Enter.instance().okno().size(w[2],h[2]).move(x[2],y[2]).kadr(pi).show(pi,"aa2");
f3=Enter.instance().okno().size(w[3],h[3]).move(x[3],y[3]).kadr(pi).show(pi,"aa3");
f4=Enter.instance().okno().size(w[4],h[4]).move(x[4],y[4]).kadr(pi).show(pi,"aa4");
(r = Enter.instance().okno()).size(w[5],h[5]).move(x[5],y[5]).kadr(
 new Experiment(r)).show(pi,"aaa");
}
public static void main(String[] args) {
Aaa app = new Aaa();
System.out.print("HelloWindWorld");
app.entrypoint();
System.out.println("main!");
}
public void ka() {
Enter.instance().slipstream().push(f1.finish()).push(
 f2.finish()).push(f3.finish()).push(f4.finish()).push(r.finish());
som.stop();
}
public void make(Graphics ics, short mx, short my) {
ics.setColor(Color.orange);
ics.fillRect(0,0,mx,my);
ics.setColor(Color.magenta);
ics.drawRect(0,0,mx,my);
String met = "" + lg.ext(som.rlong(), System.currentTimeMillis());
short fs = 25;
ics.setFont(new Font("SansSerif", Font.BOLD, fs));
FontMetrics fm = ics.getFontMetrics();
short sw = (short)fm.stringWidth(met);
short sh = (short)fm.getHeight();
short delta = (short)((sh-fs)/2);
ics.setColor(Color.black);
ics.drawString(met, (mx-sw)/2, (my-sh)/2+sh-delta);
ics.setColor(Color.magenta);
ics.drawRect((mx-sw)/2, (my-sh)/2, sw, sh);
}
}
class Experiment extends Shorty implements Kadr {
Okno recha;
public Experiment(Okno r) { recha = r; }
public void make(Graphics ics, short mx, short my) {
Plane lane = new Plantronyx();
Square r;
r = new Fon(rev(mx,21), rev(my,21), Color.red).ercent(rev2(mx,2), rev2(my,2));
r.impl(lane);
Simple si = new Simple(Color.magenta);
r = new Rama(si, mx, my).ercent(rev2(mx,2), rev2(my,2));
r.impl(lane);
r = new Simple(Color.blue, ext(0)).ercent(ext(rev2(mx,2), 0), rev2(my,2));
r.impl(lane);
r = new Simple(Color.blue, ext(1)).ercent(ext(rev2(mx,2),10), rev2(my,2));
r.impl(lane);
r = new Simple(Color.blue, ext(2)).ercent(ext(rev2(mx,2),20), rev2(my,2));
r.impl(lane);
r = new Simple(Color.blue, ext(3)).ercent(ext(rev2(mx,2),30), rev2(my,2));
r.impl(lane);
r = new Simple(Color.blue, ext(4)).ercent(ext(rev2(mx,2),40), rev2(my,2));
r.impl(lane);
ics.drawImage(lane.agu(recha), 0, 0, null);
}
}
class Sometime extends Sleeper implements Runnable {
Thread th;
long doroga = 11111L;
public void run() { while(th != null) { doroga += 17L; sleep(13); } }
public void start() { (th = new Thread(this)).start(); }
public void stop() { th = null; }
public short rshort() { return (short)(doroga+0); }
public int rint() { return (int)(doroga-1); }
public long rlong() { return (long)(doroga+1L); }
public byte rbyte() { return (byte)(doroga-0); }
}
class Sandbox extends Window {
Stop op;
Move in;
Shift hi;
Shorty rt;
public Sandbox(Frame fre, Stop op, Move in) {
super(fre);
this.op = op;
this.in = in;
this.rt = Enter.instance().shorty();
}
public boolean mouseMove(Event evt, int x, int y) {
return false;
}
public boolean mouseDrag(Event evt, int x, int y) {
if(evt.metaDown() && in != null) {
if(hi == null) {
hi = new Shift(rt.ext(0,x), rt.ext(0,y));
} else {
in.in(new Shift(rt.rev(hi.dx,x), rt.rev(hi.dy,y)));
hi = new Shift(rt.ext(x,0), rt.ext(y,0));
}
}
return false;
}
public boolean mouseUp(Event evt, int x, int y) {
if(in != null) { in.out(); this.hi = null; }
if(evt.shiftDown()&& evt.controlDown() && evt.metaDown() && op != null) op.ka();
return false;
}
public boolean mouseDown(Event evt, int x, int y) {
return false;
}
public boolean mouseEnter(Event evt, int x, int y) {
return false;
}
public boolean mouseExit(Event evt, int x, int y) {
return false;
}
}
interface Plane {
Plane set(short x, short y, Color c);
Image agu(Okno win);
}
interface Square {
Square impl(Plane xy);
Square ercent(short cx, short cy);
Square stepb3h();
Square stepb6h();
Square stepb9h();
Square stepb0h();
short shor();
short sver();
short b3h();
short b6h();
short b9h();
short b0h();
}
interface Udot {
Udot ins(short x, short y, Color c);
short x(); short y(); Color c();
}
class Udotik implements Udot {
short x, y; Color c;
public Udot ins(short xx, short yy, Color cc) {x = xx; y = yy; c = cc; return this;}
public short x() { return x; }
public short y() { return y; }
public Color c() { return c; }
}
class Plantronyx extends Shorty implements Plane {
short minx, miny, maxx, maxy;
Spiska ska;
public Plantronyx() {
minx = miny = maxx = maxy = -1;
ska = new Spiska();
}
public Plane set(short x, short y, Color c) {
if(minx == -1 && miny == -1) {
minx = maxx = x;
maxx = maxy = y;
} else {
minx = x < minx ? x : minx;
maxx = x > maxx ? x : maxx;
miny = y < miny ? y : miny;
maxy = y > maxy ? y : maxy;
}
ska.append(new Udotik().ins(x, y, c)); 
return this;
}
public Image agu(Okno win) {
Image mage = win.canva(ext(rev(maxx, minx)), ext(rev(maxy, miny)));
if(mage != null) {
Graphics ics = mage.getGraphics();
for(int i = 0; i < ska.size(); i++) {
Udot cur = (Udot)ska.at((long)i);
ics.setColor(cur.c());
ics.drawLine(cur.x(), cur.y(), cur.x(), cur.y());
}
}
return mage;
}
}
abstract class Dot extends Shorty implements Square {
short cx, cy;
public Dot() {  }
public Square ercent(short cxx, short cyy) { cx = cxx; cy = cyy; return this; }
public Square stepb3h() { this.cx += shor(); return this; }
public Square stepb6h() { this.cy += sver(); return this; }
public Square stepb9h() { this.cx -= shor(); return this; }
public Square stepb0h() { this.cy -= sver(); return this; }
}
class Simple extends Dot {
Color col;
short dense = 1;
public Simple() { this.col = Color.yellow; }
public Simple(Color col) { this.col = col; }
public Simple(Color col, short dense) { this.col = col; this.dense = dense; }
public Simple col(Color col) { this.col = col; return this; }
public short shor() { return dense; }
public short sver() { return dense; }
public Square impl(Plane pl) {
short shift = rev2(dense, 2);
short delta = rev(dense, ext2(shift, 2));
short mx = rev(cx, shift);
short my = rev(cy, shift);
short wx = rev(ext(cx, shift), delta==0?1:0);
short wy = rev(ext(cy, shift), delta==0?1:0);
short i = mx, j = my;
while(i <= wx && j <= wy) {
pl.set(i, j, col);
i = ext(i);
if(i > wx) { j = ext(j); i = mx; }
}
return this;
}
public short b3h() { return ext(cx, rev2(dense, 2)); }
public short b6h() { return ext(cy, rev2(dense, 2)); }
public short b9h() { return rev(cx, rev2(dense, 2)); }
public short b0h() { return rev(cy, rev2(dense, 2)); }
}
class Fon extends Dot {
Color col;
short ww, hh;
public Fon(short w, short h) { col = Color.orange; ww = w; hh = h; }
public Fon(short w, short h, Color c) { col = c; ww = w; hh = h; }
public Fon col(Color col) { this.col = col; return this; }
public short shor() { return ww; }
public short sver() { return hh; }
public Square impl(Plane pl) {
short mx = rev(cx, rev2(ww, 2));
short my = rev(cy, rev2(hh, 2));
short wx = ext(mx, ww);
short wy = ext(my, hh);
short i = mx, j = my;
while(i <= wx && j <= wy) {
pl.set(i, j, col);
i = ext(i);
if(i > wx) { j = ext(j); i = mx; }
}
return this;
}
public short b3h() { return ext(cx, rev2(ww, 2)); }
public short b6h() { return ext(cy, rev2(hh, 2)); }
public short b9h() { return rev(cx, rev2(ww, 2)); }
public short b0h() { return rev(cy, rev2(hh, 2)); }
}
class Rama implements Square {
short b1, b2, b3, b4;
Dot type;
short uprava, uniz, cx, cy;
public Rama(Dot type, short uprava, short uniz) {
this.type = type; this.uprava = uprava; this.uniz = uniz;
}
public Square impl(Plane pl) {
short wx2 = (short)(type.shor()*uprava/2);
short wy2 = (short)(type.sver()*uniz/2);
type.ercent((short)(cx-wx2), (short)(cy-wy2));
short tmp;
b4 = type.b0h(); tmp = uprava; while(tmp-- > 1) type.impl(pl).stepb3h();
b1 = type.b3h(); tmp = uniz; while(tmp-- > 1) type.impl(pl).stepb6h();
b2 = type.b6h(); tmp = uprava; while(tmp-- > 1) type.impl(pl).stepb9h();
b3 = type.b9h(); tmp = uniz; while(tmp-- > 1) type.impl(pl).stepb0h();
return this;
}
public short shor() { return (short)(type.shor()*uprava); }
public short sver() { return (short)(type.sver()*uniz); }
public short b3h() { return b1; }
public short b6h() { return b2; }
public short b9h() { return b3; }
public short b0h() { return b4; }
public Square ercent(short cxx, short cyy) { cx = cxx; cy = cyy; return this; }
public Square stepb3h() { cx += shor(); return this; }
public Square stepb6h() { cy += sver(); return this; }
public Square stepb9h() { cx -= shor(); return this; }
public Square stepb0h() { cy -= sver(); return this; }
}