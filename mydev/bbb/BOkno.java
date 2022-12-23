package mydev.bbb; import java.awt.Event; import java.awt.Frame; import java.awt.Graphics; import java.awt.Image; import java.awt.Window; import mydev.aaa.Enter; import mydev.aaa.Kadr; import mydev.aaa.Okno; import mydev.aaa.Shorty; import mydev.aaa.Slip; import mydev.aaa.Stop; public class BOkno extends Okno implements BMove { short mss=500; short ww=10,hh=10,xx=10,yy=10; InternalPlane plane; Kadr kkadr; String name; BFrame frr; SandboxB wndd; Graphics ics; Shorty rtt; public BOkno(InternalPlane plane) { super(); this.plane=plane; this.rtt=Enter.instance().shorty();} public void dump() { System.out.println("BOkno "+xx+","+yy+","+ww+","+hh);} public void run() { Shorty or=Enter.instance().shorty(); while(plane !=null && kkadr !=null) { if(plane.hasBOknos()) { kkadr.make(plane.getGraphics(),or.rev(ww,0),or.rev(hh,0)); if(!plane.hasBOknos()) plane.markRepaint();} sleep(mss);}} public Image canva(short i,short e) { return plane !=null ? plane.createImage(i,e) : null;} public Okno kadr(Kadr kadr) { this.kkadr=kadr; super.kadr(kadr); return this;} public Okno size(short w,short h) { this.ww=w; this.hh=h; super.size(w,h); return this;} public Okno move(short x,short y) { this.xx=x; this.yy=y; super.move(x,y); return this;} public Slip memo() { return new Slip(""+ww+","+hh+","+xx+","+yy,name);} public void out() { this.hi=null;} private BShift hi; public void in(BShift hi) { if(!(this.hi !=null && this.hi.dx==-hi.dx && this.hi.dy==-hi.dy)) { move(rtt.rev(xx,hi.dx),rtt.rev(yy,hi.dy)); this.hi=hi; wndd.reshape(xx,yy,ww,hh);}} public boolean recall(Slip item) { String ati=new String(item.symbols()); int idx=ati.indexOf(","); if(idx < 0) return false; ww=(short) Long.parseLong(ati.substring(0,idx)); ati=ati.substring(idx+1); idx=ati.indexOf(","); if(idx < 0) return false; hh=(short) Long.parseLong(ati.substring(0,idx)); ati=ati.substring(idx+1); idx=ati.indexOf(","); if(idx < 0) return false; xx=(short) Long.parseLong(ati.substring(0,idx)); ati=ati.substring(idx+1); yy=(short) Long.parseLong(ati); return true && super.recall(item);} public Okno show(Stop op,String id) { Slip slip=Enter2.instance().slipstream().get(id); if(slip !=null) recall(slip); if(frr==null) { this.name=id; plane.regBOkno(this,wndd=new SandboxB(frr=new BFrame(id,plane),op,this)); wndd.reshape(xx,yy,ww,hh); wndd.show(); this.ics=wndd.getGraphics(); new Thread(this).start();} else { wndd.reshape(xx,yy,ww,hh); wndd.show();} return this;} public Okno hide() { if(frr !=null) wndd.hide(); return this;} public Slip finish() { if(frr !=null) { wndd.hide(); frr.dispose(); frr=null; kkadr=null;} return memo();} public Okno delay(short ms) { this.mss=ms; super.delay(ms); return this;} public boolean lit(int x,int y) { if(x >= xx && x < xx+ww) if(y >= yy && y < yy+hh) return true; return false;}} class BFrame extends Window { String id; InternalPlane internalPlane; public BFrame(String id,InternalPlane internalPlane) { super(new Frame("")); this.id=id; this.internalPlane=internalPlane;} public void dispose() {} public Image createImage(short i,short e) { return internalPlane.createImage(i,e);}} class SandboxB { Stop op; BMove in; BShift hi; Shorty rt; BFrame fre; private boolean needRepaint; public SandboxB(BFrame fre,Stop op,BMove in) { super(); this.fre=fre; this.op=op; this.in=in; this.rt=Enter.instance().shorty();} public void hide() {} public Graphics getGraphics() { return null;} public void show() {} public void reshape(short xx,short yy,short ww,short hh) { needRepaint=true;} public boolean mouseMove(Event evt,int x,int y) { return false;} public boolean mouseDrag(Event evt,int x,int y) { if(evt.metaDown() && in !=null) { if(hi==null) { hi=new BShift(rt.ext(0,x),rt.ext(0,y));} else { in.in(new BShift(rt.rev(hi.dx,x),rt.rev(hi.dy,y))); hi=new BShift(rt.ext(x,0),rt.ext(y,0));}} return false;} public boolean mouseUp(Event evt,int x,int y) { if(in !=null) { in.out(); this.hi=null;} if(evt.shiftDown() && evt.controlDown() && evt.metaDown() && op !=null) op.ka(); return false;} public boolean mouseDown(Event evt,int x,int y) { return false;} public boolean mouseEnter(Event evt,int x,int y) { return false;} public boolean mouseExit(Event evt,int x,int y) { return false;} public boolean peekRepaint() { if(needRepaint) { needRepaint=false; return true;} return false;}}