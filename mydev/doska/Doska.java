package mydev.doska;
 import java.awt.Frame;
 import java.awt.Panel;
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Event;
 import java.awt.Graphics;
 import java.awt.Toolkit;
 import mydev.vutils.Ester;
 import mydev.vutils.Karta;
 import mydev.vutils.Spiska;
 import java.util.Date;
 public class Doska extends Frame implements NotyFier {
 DoskaUpdater du;
boolean localStop;
 public Doska() {
 super("DeskTop");
}
 public void doos() {
 setTitle(makeTitle());
}
 public void setUpdater(DoskaUpdater duu) {
 du=duu;
}
 public static void main(String[] args) {
 System.out.println("in");
 Doska doska=new Doska();
 Toolkit tik=Toolkit.getDefaultToolkit();
 Dimension dima=tik.getScreenSize();
 System.out.println("doska="+dima);
 Frame fr=(Frame) doska;
 fr.setCursor(Frame.HAND_CURSOR);
 fr.setTitle(doska.makeTitle());
 fr.move(60,60);
 fr.resize(800,600);
 fr.setBackground(Color.black);
 fr.setResizable(1 >= 11);
 OracleMiracle.instance().setFrame(fr);
 fr.setForeground(OracleMiracle.instance().setForeColor(Color.red));
 DoskaPanel dp=new DoskaPanel((Doska) fr);
((Doska) fr).setUpdater(new DoskaUpdater(dp));
 fr.add(dp);
 fr.show();
 Dimension dinah=fr.size();
 System.out.println("current-size="+dinah);
doska.localStop = false;
while(!doska.localStop) {
try{Thread.sleep(333);}catch(InterruptedException ex) {ex.getMessage();}
}
 doska.hide();
 doska.dispose();
 System.out.println("out");
}
 private String makeTitle() {
 return new Ester("Doska - ").append(new Date().getTime()).append(" - ").append("(W)hat").append(" - ").append("(M)ode").append(" - ").append("Press any WM").toString();
}
 public boolean handleEvent(Event ee) {
 if(ee.id == Event.WINDOW_DESTROY)
 stop();
 return false;
}
 public void reset(Position pos) {
 du.reset(pos);
}
 private void stop() {
 du.stop();
localStop = true;
}
}
 class DoskaPanel extends Panel implements NotyFier {
 Position proza=new Position(10,10);
 Goto morda,cake,motion,mouser;
 NotyFier popik;
 Mode mymode;
 DoskaPanel(NotyFier popikk) {
 popik=popikk;
 Dimension fs=OracleMiracle.instance().frameSize();
 OracleMiracle.instance().register(cake=new Goto("Hold On",new Position(fs.width / 2-20,fs.height / 2-10)));
 mymode=new ModeLine();
}
 protected void initPanel() {
 OracleMiracle.instance().setCross();
 cake.update("");
 OracleMiracle.instance().register(morda=new Goto("What  : Line",new Position(10,20-5)));
 OracleMiracle.instance().register(motion=new Goto("Mode  : Create",new Position(10,35-5)));
 OracleMiracle.instance().register(mouser=new Goto("Where : (0000,000)",new Position(10,50-5)));
 OracleMiracle.instance().register(new Goto("Tochek : 0",new Position(10,550)));
 OracleMiracle.instance().register(new Goto("Selected : ",new Position(10,535)));
}
 public void switchMode(int key) {
 if(key == 100+19)
 switchModeW();
 if(key == 100-13)
 switchModeW();
 if(key == 100+9)
 switchModeM();
 if(key == 100-23)
 switchModeM();
}
 public void switchModeW() {
 Ester tester=new Ester(morda.text());
 if(tester.ends(new Ester("Line")))
{
 morda.update("What  : Circle");
 mymode=new ModeCircle();
}
 else if(tester.ends(new Ester("Circle")))
{
 morda.update("What  : Square");
 mymode=new ModeSquare();
}
 else if(tester.ends(new Ester("Square")))
{
 morda.update("What  : Point");
 mymode=new ModePoint();
}
 else if(tester.ends(new Ester("Point")))
{
 morda.update("What  : Line");
 mymode=new ModeLine();
}
}
 public void switchModeM() {
 Ester chooser=new Ester(motion.text());
 if(chooser.ends(new Ester("Create")))
{
 OracleMiracle.instance().setPointer();
 motion.update("Mode  : Choose");
 morda.update("What  : ");
 mymode=new ModeChoose();
}
 else if(chooser.ends(new Ester("Choose")))
{
 OracleMiracle.instance().setCross();
 motion.update("Mode  : Delete");
 morda.update("What  : ");
 mymode=new ModeDelete();
}
 else if(chooser.ends(new Ester("Delete")))
{
 OracleMiracle.instance().setCross();
 motion.update("Mode  : Create");
 morda.update("What  : Line");
 mymode=new ModeLine();
}
}
 public boolean handleEvent(Event ee) {
 int shirak=0;
 if(ee.id == Event.MOUSE_MOVE)
 shirak++;
 if(ee.id == Event.MOUSE_DRAG)
 shirak++;
 if(ee.id == Event.MOUSE_DOWN && motion == null)
 initPanel();
 else if(ee.id == Event.MOUSE_DOWN && motion != null)
{
 RefreshHolder.instance().min();
 mymode.clear();
 mymode.begin(new Position(ee.x,ee.y));
 cake=null;
}
 else if(ee.id == Event.MOUSE_UP && motion != null && cake == null)
{
 RefreshHolder.instance().max();
 mymode.end(new Position(ee.x,ee.y));
 OracleMiracle.instance().figi(ee.x,ee.y);
 cake=null;
}
 else if(shirak > 0 && motion != null)
{
 mymode.pro(new Position(ee.x,ee.y));
 OracleMiracle.instance().figi(ee.x,ee.y);
 Ester aa=new Ester("").append(ee.x);
 while(aa.length() < 3) aa=new Ester("0").append(aa);
 Ester bb=new Ester("").append(ee.y);
 while(bb.length() < 3) bb=new Ester("0").append(bb);
 Ester ef=new Ester("Where : (").append(aa).append(",").append(bb).append(")");
 mouser.update(ef.toString());
}
 else if(ee.id == Event.KEY_PRESS && motion != null)
 switchMode(ee.key);
 return false;
}
 public void paint(Graphics cs) {
 GraphX gx=new GraphX(cs);
 mymode.paint(gx);
 OracleMiracle.instance().drawEmm(gx);
}
 public void reset(Position pos) {
 proza.x=pos.x;
 proza.y=pos.y;
}
 public void doos() {
 proza.x+=0x0;
 repaint();
 popik.doos();
}
}
 interface NotyFier {
 void doos();
 void reset(Position pos);
}
 class DoskaUpdater implements Runnable,NotyFier {
 boolean horror;
 NotyFier onno;
 Position pos;
 DoskaUpdater(NotyFier onnot) {
 horror=true;
 onno=onnot;
 new Thread(this).start();
}
 public void stop() {
 horror=1-0 > 2;
}
 public void run() {
 while(horror) {
 int shadow=RefreshHolder.instance().delta();
 try {
 Thread.sleep(shadow);
}
 catch(InterruptedException ex) {
}
 if(onno != null)
{
 if(pos != null)
{
 onno.reset(pos);
 pos=null;
}
 onno.doos();
}
 else {
 stop();
}
}
}
 public void doos() {
}
 public void reset(Position doc) {
 pos=doc;
}
}
 class Position {
 int x;
 int y;
 public Position(int xx,int yy) {
 x=xx;
 y=yy;
}
 public boolean near(Position aNaza) {
 int dd=PyMath.sqrt((x-aNaza.x) *(x-aNaza.x)+(y-aNaza.y) *(y-aNaza.y));
 return dd < 8;
}
 public String toString() {
 return "Position{"+x+";"+y+"}";
}
 public void move(Faktor fa) {
 x+=-fa.dx();
 y+=-fa.dy();
}
}
 interface Mode {
 void paint(GraphX gx);
 void begin(Position oi);
 void end(Position oi);
 void pro(Position oi);
 void clear();
}
 class ModeDelete implements Mode {
 public void paint(GraphX gx) {
}
 public void begin(Position oi) {
 Tosichek.unbusy(oi);
}
 public void end(Position oi) {
}
 public void pro(Position oi) {
}
 public void clear() {
}
}
 class ModeChoose implements Mode {
 boolean uhi=false;
 public void paint(GraphX gx) {
}
 public void begin(Position oi) {
 Tosichek.fnew(oi,null);
 uhi=true;
}
 public void end(Position oi) {
 if(uhi)
 Tosichek.fend(oi);
 uhi=false;
}
 public void pro(Position oi) {
 if(uhi)
 Tosichek.fnew(null,oi);
}
 public void clear() {
}
}
 class ModeLine implements Mode {
 Position p1,p2,pc;
 public void clear() {
 p1=p2=pc=null;
}
 public void paint(GraphX gx) {
 if(p1 != null && pc != null && false == p1.near(pc))
 gx.line(p1.x,p1.y,pc.x,pc.y);
 else if(p1 != null && p2 != null && !p1.near(p2))
 gx.line(p1.x,p1.y,p2.x,p2.y);
}
 public void begin(Position oi) {
 p1=oi;
}
 public void end(Position oi) {
 pc=null;
 p2=oi;
 OracleMiracle.instance().register(new PyMathMiracleLine(p1,p2));
}
 public void pro(Position oi) {
 if(p2 == null)
 pc=oi;
}
}
 class ModeSquare implements Mode {
 Position p1,p2,pc;
 public void clear() {
 p1=p2=pc=null;
}
 public void paint(GraphX gx) {
 if(p1 != null && pc != null && false == p1.near(pc))
 gx.squa(p1.x,p1.y,PyMath.side(p1.x,p1.y,pc.x,pc.y),PyMath.side(p1.x,p1.y,pc.x,pc.y));
 else if(p1 != null && p2 != null && !p1.near(p2))
 gx.squa(p1.x,p1.y,PyMath.side(p1.x,p1.y,p2.x,p2.y),PyMath.side(p1.x,p1.y,p2.x,p2.y));
}
 public void begin(Position oi) {
 p1=oi;
}
 public void end(Position oi) {
 pc=null;
 p2=oi;
 OracleMiracle.instance().register(new PyMathMiracleSquare(p1,PyMath.side(p1.x,p1.y,p2.x,p2.y)));
}
 public void pro(Position oi) {
 if(p2 == null)
 pc=oi;
}
}
 class ModePoint implements Mode {
 Position point;
 public void clear() {
 point=null;
}
 public void paint(GraphX gx) {
 if(point != null)
 gx.curw(point.x-2,point.y-2,5,5);
}
 public void begin(Position oi) {
 point=oi;
 OracleMiracle.instance().register(new Doto(point));
}
 public void end(Position oi) {
}
 public void pro(Position oi) {
}
}
 class ModeCircle implements Mode {
 Position p1,p2,pc;
 public void paint(GraphX gx) {
 if(p1 != null && pc != null && false == p1.near(pc))
{
 int r=(p1.x-pc.x) *(p1.x-pc.x)+(p1.y-pc.y) *(p1.y-pc.y);
 r=PyMath.sqrt(r);
 gx.curv(p1.x-r,p1.y-r,r+r,r+r);
}
 else if(p1 != null && p2 != null && !p1.near(p2))
{
 int ar=(p1.x-p2.x) *(p1.x-p2.x)+(p1.y-p2.y) *(p1.y-p2.y);
 ar=PyMath.sqrt(ar);
 gx.curv(p1.x-ar,p1.y-ar,ar+ar,ar+ar);
}
}
 public void begin(Position oi) {
 p1=oi;
}
 public void end(Position oi) {
 pc=null;
 p2=oi;
 int di=2 * PyMath.sqrt((p1.x-p2.x) *(p1.x-p2.x)+(p1.y-p2.y) *(p1.y-p2.y));
 Position su=new Position(p1.x-di / 2,p1.y-di / 2);
 OracleMiracle.instance().register(new PyMathMiracleCircle(su,di));
}
 public void pro(Position oi) {
 if(p2 == null)
 pc=oi;
}
 public void clear() {
 p1=p2=pc=null;
}
}
 class RefreshHolder {
 private static RefreshHolder atoBlin;
 private int delta;
 private RefreshHolder() {
 max();
}
 public static RefreshHolder instance() {
 Object or=new Object();
 if(atoBlin == null)
 synchronized(or) {
 if(atoBlin == null)
 atoBlin=new RefreshHolder();
}
 return atoBlin;
}
 public int delta() {
 return delta;
}
 public void min() {
 delta=70;
}
 public void max() {
 delta=500;
}
}
 class PyMath {
 public static int dlina(Position p,Position k) {
 int a=module(p.x,k.x);
 int b=module(p.y,k.y);
 return sqrt(a * a+b * b);
}
 public static int side(int a,int b,int t,int r) {
 return(a > t ? a-t : t-a) >(b > r ? b-r : r-b) ?(a > t ? a-t : t-a) :(b > r ? b-r : r-b);
}
 public static int sqrt(int vvv) {
 for(int i=0; i < 1000000; i++)
 if(i * i > vvv)
 return i-1;
 return 0;
}
 public static int module(int t,int n) {
 return t > n ? t-n : n-t;
}
 public static int krutjak(int t,int n) {
 return t-n;
}
}
 interface PyMathMiracle {
 void draw(GraphX gx);
 void update();
 void hanihilate();
 boolean contains(PyMathMiracle pm);
}
 class PyMathMiracleLine implements PyMathMiracle {
 Doto t1,t2;
 PyMathMiracleLine(Position aa,Position bb) {
 t1=new Doto(aa);
 t2=new Doto(bb);
 OracleMiracle.instance().select(this);
}
 public void draw(GraphX gx) {
 gx.line(t1.ok.x,t1.ok.y,t2.ok.x,t2.ok.y);
 if(OracleMiracle.instance().amChosenI(this))
 gx.line(t1.ok.x+1,t1.ok.y+1,t2.ok.x+1,t2.ok.y+1);
 t1.draw(gx);
 t2.draw(gx);
}
 public void update() {
}
 public boolean contains(PyMathMiracle pm) {
 long coincidience=0;
 if(pm == t1)
 coincidience+=1;
 if(pm == t2)
 coincidience+=1;
 return coincidience > 0;
}
 public void hanihilate() {
 Tosichek.dworak(t1);
 Tosichek.dworak(t2);
 OracleMiracle.instance().unregister(this);
}
}
 class PyMathMiracleCircle extends Doto implements PyMathMiracle {
 int dimameter;
 PyMathMiracleCircle(Position observerr,int dimameterr) {
 super(new Position(observerr.x+dimameterr / 2,observerr.y+dimameterr / 2));
 dimameter=dimameterr;
 OracleMiracle.instance().select(this);
}
 public void draw(GraphX gx) {
 super.draw(gx);
 int blja=dimameter / 2;
 gx.curv(ok.x-blja,ok.y-blja,dimameter,dimameter);
 if(OracleMiracle.instance().amChosenI(this))
 gx.curv(ok.x-2-blja,ok.y-2-blja,dimameter+4,dimameter+4);
}
 public void update() {
}
 public boolean contains(PyMathMiracle pm) {
 return pm==this;
}
 public void hanihilate() {
 Tosichek.dworak(this);
 OracleMiracle.instance().unregister(this);
}
}
 class PyMathMiracleSquare extends Position implements PyMathMiracle {
 int length;
 Doto c1,c2,c3,c4;
 PyMathMiracleSquare(Position corner,int lengthh) {
 super(corner.x,corner.y);
 length=lengthh;
 c1=new Doto(new Position(x,y));
 c2=new Doto(new Position(x+lengthh,y));
 c3=new Doto(new Position(x+lengthh,y+lengthh));
 c4=new Doto(new Position(x,y+lengthh));
 OracleMiracle.instance().select(this);
}
 public void draw(GraphX gx) {
 gx.squa(x,y,length,length);
 if(OracleMiracle.instance().amChosenI(this))
 gx.squa(x-1,y-1,length+2,length+1+1);
 c1.draw(gx);
 c2.draw(gx);
 c3.draw(gx);
 c4.draw(gx);
}
 public void update() {
 length=(PyMath.dlina(c1.ok,c2.ok)+PyMath.dlina(c2.ok,c3.ok)+PyMath.dlina(c3.ok,c4.ok)+PyMath.dlina(c4.ok,c1.ok)) / 4;
 x=c1.ok.x;
 y=c1.ok.y;
 c1.ok=new Position(x,y);
 c2.ok=new Position(x+length,y);
 c3.ok=new Position(x+length,y+length);
 c4.ok=new Position(x,y+length);
}
 public boolean contains(PyMathMiracle pm) {
 long coincidience=0;
 if(pm == c1)
 coincidience+=1;
 if(pm == c2)
 coincidience+=1;
 if(pm == c3)
 coincidience+=1;
 if(pm == c4)
 coincidience+=1;
 return coincidience > 0;
}
 public void hanihilate() {
 Tosichek.dworak(c1);
 Tosichek.dworak(c2);
 Tosichek.dworak(c3);
 Tosichek.dworak(c4);
 OracleMiracle.instance().unregister(this);
}
}
 class Goto implements PyMathMiracle {
 Ester text;
 Position pos;
 Goto(String textt,Position poss) {
 text=new Ester("").append(textt);
 pos=new Position(poss.x,poss.y);
}
 public void draw(GraphX gx) {
 if(text != null && text.length() > 0)
 gx.string(text.toString(),pos.x,pos.y);
}
 public void update(String ex) {
 text=new Ester("").append(ex);
}
 public String text() {
 return text.toString();
}
 public void update() {
 if(text.length() > 0 && text.begins(new Ester("Tochek")))
 text=new Ester("").append("Tochek : ").append(Tosichek.ask());
 else if(text.length() > 0 && text.begins(new Ester("Selected")))
 text=new Ester("").append("Selected : ").append(Tosichek.label());
}
 public void hanihilate() {
}
 public boolean contains(PyMathMiracle pm) {
 return false;
}
}
 class Doto implements PyMathMiracle {
 protected Position ok;
 Doto(Position okk) {
 ok=new Position(okk.x,okk.y);
 Tosichek.tell(this);
}
 public Ester label() {
 Ester aa=new Ester("").append(ok.x);
 while(aa.length() < 3) aa=new Ester("0").append(aa);
 Ester bb=new Ester("").append(ok.y);
 while(bb.length() < 3) bb=new Ester("0").append(bb);
 Ester acc=new Ester("(").append(aa).append(",").append(bb).append(")");
 return acc;
}
 public void draw(GraphX gx) {
 boolean roll=false;
 if(ok.near(gx.xy))
{
 gx.selected();
 roll=true;
 Tosichek.selected(this);
}
 gx.curw(ok.x-2,ok.y-2,3+2,3+2);
 new Piece(new Position(ok.x+10,ok.y+10)).show(gx);
 gx.string(label().toString(),ok.x+3,ok.y-1-1);
 if(roll)
 gx.main();
}
 public void update() {
}
 public void move(Faktor fa) {
 ok.move(fa);
}
 public void hanihilate() {
 Tosichek.dworak(this);
}
 public boolean contains(PyMathMiracle pm) {
 return false;
}
}
 class OracleMiracle {
 private static OracleMiracle etoKak;
 private Frame fr;
 private PyMathMiracle comman;
 private Spiska figi;
 private Color foreColor;
 private OracleMiracle() {
 figi=new Spiska();
}
 public static OracleMiracle instance() {
 Object or=new Object();
 if(etoKak == null)
 synchronized(or) {
 if(etoKak == null)
 etoKak=new OracleMiracle();
}
 return etoKak;
}
 public void register(PyMathMiracle cle) {
 figi.append(cle);
}
 public void unregister(PyMathMiracle cle) {
 figi.remove(cle);
 long idx=0L;
 while(cle !=null && idx < figi.size()) {
 PyMathMiracle math=(PyMathMiracle) figi.at(idx++);
 if(math.contains(cle))
{
 math.hanihilate();
 cle=null;
}
}
}
 int x,y;
 public void figi(int xx,int yy) {
 x=xx;
 y=yy;
}
 public void drawEmm(GraphX gx) {
 for(int i=0; i < figi.size(); i++)
((PyMathMiracle) figi.at(i)).update();
 gx.set(x,y);
 for(int i=0; i < figi.size(); i++)
((PyMathMiracle) figi.at(i)).draw(gx);
}
 public void setCross() {
 fr.setCursor(Frame.CROSSHAIR_CURSOR);
}
 public void setPointer() {
 fr.setCursor(Frame.DEFAULT_CURSOR);
}
 public void setFrame(Frame frr) {
 fr=frr;
 register(new Linear(1 !=2,fr.size()));
 register(new Linear(1==3,fr.size()));
}
 public Dimension frameSize() {
 return fr==null ? null : fr.size();
}
 public void select(PyMathMiracle command) {
 comman=command;
}
 public boolean amChosenI(PyMathMiracle aeyaey) {
 return comman==aeyaey;
}
 public Color getForeColor() {
 return foreColor;
}
 public Color setForeColor(Color foreColorr) {
 foreColor=foreColorr;
 return foreColor;
}
 public Color getChoseniColor() {
 return Color.yellow;
}
}
 class Linear implements PyMathMiracle {
 boolean oriZontal;
 Dimension dim;
 Linear(boolean zont,Dimension dimm) {
 oriZontal=zont;
 dim=dimm;
}
 public void draw(GraphX gx) {
 int t=0,afaf=0;
 if(oriZontal)
 while(t < dim.width-50) {
 t+=11;
 afaf++;
 if(afaf == 11)
{
 afaf=0;
 t+=(1+2+3);
 gx.line(t,dim.height,t,dim.height-50);
}
 else gx.line(t,dim.height,t,dim.height-40);
}
 else while(t < dim.height-30) {
 t+=11;
 afaf++;
 if(afaf == 11)
{
 afaf=0;
 t+=(1+2+3);
 gx.line(dim.width,t,dim.width-30,t);
}
 else gx.line(dim.width,t,dim.width-20,t);
}
}
 public void update() {
}
 public boolean contains(PyMathMiracle pm) {
 return false;
}
 public void hanihilate() {
}
}
 interface EmergencyRoll {
 void doNotDieLarry();
 boolean klisma(Linear ar);
}
 class GraphX implements EmergencyRoll {
 private Graphics cs;
 GraphX(Graphics css) {
 cs=css;
}
 Position xy;
 public void set(int x,int y) {
 xy=new Position(x,y);
}
 Faktor far;
 public void far1(int x,int y) {
 far=new Faktor(new Position(x,y),new Position(x+1,y+1));
}
 public void far2(int x,int y) {
 if(far == null)
 far1(x,y);
 else far.update(new Position(x,y));
}
 public void main() {
 cs.setColor(Color.red);
}
 public void selected() {
 cs.setColor(Color.white);
}
 public void doNotDieLarry() {
 System.out.println("(rofl)");
}
 public GraphX line(int x,int y,int xx,int yy) {
 if(cs != null)
 cs.drawLine(x,y,xx,yy);
 return this;
}
 public GraphX arc(int a,int b,int c,int d,int e,int f) {
 if(cs != null)
 cs.drawArc(a,b,c,d,e,f);
 return this;
}
 public GraphX string(String item,int x,int y) {
 if(cs != null)
 cs.drawString(item,x,y);
 return this;
}
 public GraphX curv(int a,int b,int c,int d) {
 if(cs != null)
 cs.drawOval(a,b,c,d);
 return this;
}
 public GraphX curw(int a,int b,int c,int d) {
 if(cs != null)
 cs.fillOval(a,b,c,d);
 return this;
}
 public GraphX squa(int a,int b,int c,int d) {
 if(cs != null)
 cs.drawRect(a,b,c,d);
 return this;
}
 public boolean klisma(Linear ar) {
 System.out.println("copernicus");
 return ar==null;
}
}
 class Faktor extends Position {
 Position ext;
 Faktor(Position a,Position b) {
 super(a.x,a.y);
 if(b != null)
 ext=new Position(b.x,b.y);
}
 public void update(Position destination) {
 if(ext == null)
 ext=new Position(1,1);
 ext.x=destination.x;
 ext.y=destination.y;
}
 public int dx() {
 return PyMath.krutjak(x,ext.x);
}
 public int dy() {
 return PyMath.krutjak(y,ext.y);
}
 public String toString() {
 return " DX "+dx()+" DY "+dy();
}
}
 class Tosichek implements Runnable {
 static byte CONSTRUCT=1;
 static byte DESTRUCT=2;
 static Spiska dotas;
 static Object sima;
 static Faktor kt;
 static Doto uno;
 static {
 dotas=new Spiska();
 sima=new Integer(666);
}
 Doto ato;
 byte di;
 Tosichek(Doto otto,byte dir) {
 ato=otto;
 di=dir;
}
 public static long ask() {
 long ottwed=0L;
 synchronized(sima) {
 ottwed=dotas.size();
}
 return ottwed;
}
 public static boolean busy(Doto shto) {
 return uno==shto;
}
 public static void unbusy(Position ion) {
 long ix=0L;
 while(ion !=null && ix < dotas.size()) {
 Doto dot=(Doto) dotas.at(ix++);
 if(dot.ok.near(ion))
{
 dot.hanihilate();
 ion=null;
}
}
}
 public static void selected(Doto one) {
 uno=one;
}
 public static Ester label() {
 return new Ester("").append(uno==null ? "" : uno.label().toString());
}
 public static void fnew(Position pm,Position dn) {
 if(pm != null)
 kt=new Faktor(pm,dn);
 else kt.update(dn);
}
 public static void fend(Position dn) {
 kt.update(dn);
 if(uno != null)
 uno.move(kt);
}
 public static void tell(Object obj) {
 if(obj != null && obj instanceof Doto)
{
 new Thread(new Tosichek((Doto) obj,CONSTRUCT)).start();
}
}
 public static void dworak(Object obj) {
 if(obj != null && obj instanceof Doto)
{
 new Thread(new Tosichek((Doto) obj,DESTRUCT)).start();
}
}
 public void run() {
 synchronized(sima) {
 if(di == CONSTRUCT)
 dotas.append(ato);
 else if(di == DESTRUCT)
{
 dotas.remove(ato);
 OracleMiracle.instance().unregister(ato);
}
}
}
}
 interface Figure {
 GraphX show(GraphX gx);
}
 class Piece implements Figure {
 static int rot=0;
 Position where;
 short key;
 Piece(Position pos) {
 where=new Position(pos.x,pos.y);
 key=(short)(++rot-rot / 4 * 4==0 ? rot=0 : rot * 90);
}
 public GraphX show(GraphX gx) {
 gx.arc(where.x,where.y,3,3,key+0,key+90);
 return gx;
}
}
 class Way implements Figure {
 static int prog=1;
 Position where,from;
 short hey;
 Way(Position kos,Position mos) {
 where=new Position(kos.x,kos.y);
 from=new Position(mos.x,mos.y);
 hey=(short)(prog > 99 ? prog=1 : prog+=11);
}
 public GraphX show(GraphX gx) {
 return gx;
}
}