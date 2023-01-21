package mydev.ccc; import java.awt.BorderLayout; import java.awt.Button; import java.awt.Checkbox; import java.awt.Color; import java.awt.Event; import java.awt.FlowLayout; import java.awt.Font; import java.awt.Frame; import java.awt.Graphics; import java.awt.Image; import java.awt.Panel; import java.awt.Point; import java.awt.TextField; import mydev.aaa.Aaa; import mydev.aaa.Kadr; import mydev.aaa.Longy; import mydev.aaa.Shorty; import mydev.aaa.Sleeper; import mydev.aaa.Slip; import mydev.aaa.Stop; import mydev.about.Canvas; import mydev.about.ColorsArray; import mydev.about.Paint; import mydev.about.Point2D; import mydev.about.Rectangle2D; import mydev.about.Text2D; import mydev.bbb.Enter2; import mydev.bbb.Location; import mydev.uix.Better; import mydev.uix.External3DPanel; import mydev.uix.Props; import mydev.uix.Timer; import mydev.uix.WidthHeight; import mydev.uix.YellowAutoButton; import mydev.vutils.Spiska; public class Ccc extends Frame implements Runnable,Stop,Kadr { final short fin=18; private WindowsBackgrounds windowsBackground=new WindowsBackgrounds(); COkno f1,f2,f3,f4,r; Shorty rt=Enter3D.instance().shorty(); Longy lg; CSometime som; boolean autoEnabled=false; Internal3DPlane oknosPlane; CccRightPanel topPanel; ToolbarCccLeftPanel leftPanel; protected Aaa app; short[] w={ 0,230,240,250,260,199}; short[] h={ 0,170,160,140,130,155}; short[] x={ 0,31,311,635,924,rt.rev2(rt.rev(1366,w[5]),2)}; short[] y={ 0,19,500,150,300,rt.rev2(rt.rev(768,h[5]),2)}; public Ccc() { super("CccBbbAaa"); this.som=Enter3D.instance().sometime(); this.lg=Enter3D.instance().longy();} void prepare() { setLayout(new BorderLayout()); setBackground(Color.white); add("East",topPanel=new CccRightPanel()); add("Center",oknosPlane=new Internal3DPlane(Color.darkGray,Color.yellow)); add("West",leftPanel=new ToolbarCccLeftPanel());} public static void main(String[] args) { Ccc main=new Ccc(); System.out.print("HelloWindWorld"); main.prepare(); main.resize((short)(800 *(1.f+1.f-.4f+.15f-.06f+0.044f-.0056f+.0017f)),(short)(600 * 1.1953f)); main.show(); main.reMoveRnd(); main.start(); System.out.println("main!"); Sleeper sleeper=new Sleeper(); while(Better.working) { main.refreshUpdatable(); sleeper.sleep(Timer.DEF_REF_TIME);} main.ka(); main.hide(); main.dispose(); System.exit(1-1);} public boolean handleEvent(Event et) { if(et.id==Event.WINDOW_DESTROY) new Better().exitApplicationLoop(); if(et.id==Event.MOUSE_DOWN) windowsBackground.next(); if(et.id==Event.ACTION_EVENT && et.target==leftPanel.getDemoAuto().getBtAuto()) auto(); if(et.id==Event.ACTION_EVENT && et.target==leftPanel.getCbExt()) extInternal(leftPanel.getExternal()); if(et.id==Event.ACTION_EVENT && et.target==leftPanel.getCbD3()) withD3(leftPanel.getD3()); if(et.id==Event.ACTION_EVENT && et.target==leftPanel.getDefBtnPanel().getBtRepo()) reposition(leftPanel.getExternal()); if(et.id==Event.ACTION_EVENT && et.target==topPanel.getBtStop()) topPanel.disableStop(); if(et.id==Event.ACTION_EVENT && et.target==leftPanel.getDefBtnPanel().getBtDef()) { leftPanel.getDefBtnPanel().getWhPanel().whReset(); reset(leftPanel.getExternal());} Object tg=et.target; if(tg !=null && tg.equals(leftPanel.getDefBtnPanel().getWhPanel().getTfWidth()) && et.id==Event.LOST_FOCUS) widthChange(leftPanel.getDefBtnPanel().getWhPanel()); if(tg !=null && tg.equals(leftPanel.getDefBtnPanel().getWhPanel().getTfHeight()) && et.id==Event.LOST_FOCUS) heightChange(leftPanel.getDefBtnPanel().getWhPanel()); return super.handleEvent(et);} private void heightChange(WidthHeight whPanel) { commonWidthHeightChanges(whPanel);} private void widthChange(WidthHeight whPanel) { commonWidthHeightChanges(whPanel);} private void commonWidthHeightChanges(WidthHeight whPanel) { short commonWidth=(short) Integer.parseInt(whPanel.getWidthTxt()); short commonHeight=(short) Integer.parseInt(whPanel.getHeightTxt()); updateCommonWidthHeightAllWindows(commonWidth,commonHeight); extInternal(leftPanel.getExternal());} private void updateCommonWidthHeightAllWindows(short commonWidth,short commonHeight) { f1.size(commonWidth,commonHeight); f2.size(commonWidth,commonHeight); f3.size(commonWidth,commonHeight); f4.size(commonWidth,commonHeight); r.size(commonWidth,commonHeight);} public void start() { reloadInternal();} private void extInternal(boolean ext) { hideAllWindows(); if(ext) reloadExternal(); else reloadInternal();} private void withD3(boolean d3) { System.out.println("reload-3D "+d3); oknosPlane.enableMode3D(d3); oknosPlane.markRepaint(); refreshUpdatable();} private void hideAllWindows() { ka();} private void reloadInternal() { som.start(); Ccc pi=(Ccc) this;(f1=Enter3D.instance().okno(oknosPlane)).size(w[1],h[1]).move(x[1],y[1]).kadr(new CccOknoViewKadr(f1,pi)).show(pi,"aa1");(f2=Enter3D.instance().okno(oknosPlane)).size(w[2],h[2]).move(x[2],y[2]).kadr(new CccOknoViewKadr(f2,pi)).show(pi,"aa2");(f3=Enter3D.instance().okno(oknosPlane)).size(w[3],h[3]).move(x[3],y[3]).kadr(new CccOknoViewKadr(f3,pi)).show(pi,"aa3");(f4=Enter3D.instance().okno(oknosPlane)).size(w[4],h[4]).move(x[4],y[4]).kadr(new CccOknoViewKadr(f4,pi)).show(pi,"aa4");(r=Enter3D.instance().okno(oknosPlane)).size(w[5],h[5]).move(x[5],y[5]).kadr(new CccOknoViewKadr(r,new CExperiment(r))).show(pi,"aaa");} private void reloadExternal() { app=new Aaa(); app.entrypoint();} private void reset(boolean ext) { if(ext) { hideAllWindows(); reloadInternal();} f1.size(w[1],h[1]).move(x[1],y[1]); f2.size(w[1+1],h[1+1]).move(x[1+1],y[1+1]); f3.size(w[1+1+1],h[1+1+1]).move(x[1+1+1],y[1+1+1]); f4.size(w[4],h[4]).move(x[4],y[4]); r.size(w[5],h[5]).move(x[5],y[5]); extInternal(leftPanel.getExternal()); oknosPlane.performRepaint();} private void reposition(boolean ext) { if(ext) repositionExternal(); else repositionInternal(); extInternal(leftPanel.getExternal()); oknosPlane.performRepaint();} private void repositionInternal() { LocationC olc=Enter3D.instance().location(); short mw=10; short mh=10; for(int i=0; i < w.length; i++) { if(mw < w[i]) mw=w[i]; if(mh < h[i]) mh=h[i];} Point p=olc.getRandomInternalPos(mw,mh); f1.move((short) p.x,(short) p.y); p=olc.getRandomInternalPos(mw,mh); f2.move((short) p.x,(short) p.y); p=olc.getRandomInternalPos(mw,mh); f3.move((short) p.x,(short) p.y); p=olc.getRandomInternalPos(mw,mh); f4.move((short) p.x,(short) p.y); p=olc.getRandomInternalPos(mw,mh); r.move((short) p.x,(short) p.y);} private void repositionExternal() { hideAllWindows(); reloadInternal(); LocationC olc=Enter3D.instance().location(); short mw=10; short mh=10; for(int i=0; i < w.length; i++) { if(mw < w[i]) mw=w[i]; if(mh < h[i]) mh=h[i];} Point p=olc.getRandomExternalPos(mw,mh); f1.move((short) p.x,(short) p.y); p=olc.getRandomExternalPos(mw,mh); f2.move((short) p.x,(short) p.y); p=olc.getRandomExternalPos(mw,mh); f3.move((short) p.x,(short) p.y); p=olc.getRandomExternalPos(mw,mh); f4.move((short) p.x,(short) p.y); p=olc.getRandomExternalPos(mw,mh); r.move((short) p.x,(short) p.y); hideAllWindows(); reloadExternal();} public void run() { topPanel.enableStop(); Sleeper sleeper=new Sleeper(); int i; for(i=0; topPanel.hasLongTermContin() && i < fin; i++) { topPanel.setProgText(""+(fin-i)); reposition(false); sleeper.sleep(1997 * 2);} if(topPanel.hasLongTermContin()) { topPanel.setProgText(""+(fin-i)); reset(false); sleeper.sleep(1997 * 2);} if(topPanel.getPreview() && topPanel.hasLongTermContin()) new Better().exitApplicationLoop(); leftPanel.enableCtrls(true); topPanel.disableStop(); topPanel.setProgText("");} private void auto() { leftPanel.enableCtrls(false); new Thread(this).start();} private void refreshUpdatable() { if(oknosPlane.checkRepaint()) oknosPlane.performRepaint();} protected void reMoveRnd() { Location ol=Enter2.instance().location(); Point p=ol.getRandomExternalPos((short) 1024,(short) 768); move(p.x,p.y);} public void ka() { if(app !=null) { app.ka(); app=null;} else Enter3D.instance().slipstream().push(f1.finish()).push(f2.finish()).push(f3.finish()).push(f4.finish()).push(r.finish()); oknosPlane.clearCOknos(); oknosPlane.performRepaint(); som.stop();} public void make(Graphics cs1,short mx,short my) { Canvas ics=new Canvas(cs1); Paint pn=new Paint(); Rectangle2D bg=new Rectangle2D(new Point2D(0,0),mx,my,windowsBackground.getCurrent().getRGB(),true); bg.toPen(ics,pn.select(bg)); Rectangle2D border=new Rectangle2D(new Point2D(0,0),mx,my,ColorsArray.magentaPoint.ic()); border.toPen(ics,pn.select(border)); String met=""+lg.ext(som.rlong(),System.currentTimeMillis()); short fs=25; Font font=new Font("SansSerif",Font.BOLD,fs); short sw=(short) ics.loadTextFont(font).drawTextWidth(met); short sh=(short) ics.drawTextHeight(met); Text2D content=new Text2D(new Point2D((mx-sw) / 2,(my-sh) / 2+sh / 3),met,ColorsArray.blackPoint.ic(),font); content.toPen(ics,pn.select(content)); Rectangle2D internal=new Rectangle2D(new Point2D((mx-sw) / 2,(my-sh) / 2),sw+1,sh,ColorsArray.magentaPoint.ic()); internal.toPen(ics,pn.select(internal));}} class CExperiment extends Shorty implements Kadr { COkno recha; public CExperiment(COkno r) { recha=r;} public void make(Graphics ics,short mx,short my) { CPlane lane=new CPlantronyx(); CSquare r; r=new CFon(rev(mx,21),rev(my,21),Color.red).ercent(rev2(mx,2),rev2(my,2)); r.impl(lane); CSimple si=new CSimple(Color.magenta); r=new CRama(si,mx,my).ercent(rev2(mx,2),rev2(my,2)); r.impl(lane); r=new CSimple(Color.blue,ext(0)).ercent(ext(rev2(mx,2),0),rev2(my,2)); r.impl(lane); r=new CSimple(Color.blue,ext(1)).ercent(ext(rev2(mx,2),10),rev2(my,2)); r.impl(lane); r=new CSimple(Color.blue,ext(2)).ercent(ext(rev2(mx,2),20),rev2(my,2)); r.impl(lane); r=new CSimple(Color.blue,ext(3)).ercent(ext(rev2(mx,2),30),rev2(my,2)); r.impl(lane); r=new CSimple(Color.blue,ext(4)).ercent(ext(rev2(mx,2),40),rev2(my,2)); r.impl(lane); ics.drawImage(lane.agu(recha),0,0,null);}} class CSometime extends Sleeper implements Runnable { Thread th; long doroga=11111L; public void run() { while(th !=null) { doroga+=17L; sleep(13);}} public void start() {(th=new Thread(this)).start();} public void stop() { th=null;} public short rshort() { return(short)(doroga+0);} public int rint() { return(int)(doroga-1);} public long rlong() { return(long)(doroga+1L);} public byte rbyte() { return(byte)(doroga-0);}} interface CPlane { CPlane set(short x,short y,Color c); Image agu(COkno win);} interface CSquare { CSquare impl(CPlane xy); CSquare ercent(short cx,short cy); CSquare stepb3h(); CSquare stepb6h(); CSquare stepb9h(); CSquare stepb0h(); short shor(); short sver(); short b3h(); short b6h(); short b9h(); short b0h();} interface CUdot { CUdot ins(short x,short y,Color c); short x(); short y(); Color c();} class CUdotik implements CUdot { short x,y; Color c; public CUdot ins(short xx,short yy,Color cc) { x=xx; y=yy; c=cc; return this;} public short x() { return x;} public short y() { return y;} public Color c() { return c;}} class CPlantronyx extends Shorty implements CPlane { short minx,miny,maxx,maxy; Spiska ska; public CPlantronyx() { minx=miny=maxx=maxy=-1; ska=new Spiska();} public CPlane set(short x,short y,Color c) { if(minx==-1 && miny==-1) { minx=maxx=x; maxx=maxy=y;} else { minx=x < minx ? x : minx; maxx=x > maxx ? x : maxx; miny=y < miny ? y : miny; maxy=y > maxy ? y : maxy;} ska.append(new CUdotik().ins(x,y,c)); return this;} public Image agu(COkno win) { Image mage=win.canva(ext(rev(maxx,minx)),ext(rev(maxy,miny))); if(mage !=null) { Graphics ics=mage.getGraphics(); for(int i=0; i < ska.size(); i++) { CUdot cur=(CUdot) ska.at((long) i); ics.setColor(cur.c()); ics.drawLine(cur.x(),cur.y(),cur.x(),cur.y());}} return mage;}} abstract class CDot extends Shorty implements CSquare { short cx,cy; public CDot() {} public CSquare ercent(short cxx,short cyy) { cx=cxx; cy=cyy; return this;} public CSquare stepb3h() { this.cx+=shor(); return this;} public CSquare stepb6h() { this.cy+=sver(); return this;} public CSquare stepb9h() { this.cx-=shor(); return this;} public CSquare stepb0h() { this.cy-=sver(); return this;}} class CSimple extends CDot { Color col; short dense=1; public CSimple() { this.col=Color.yellow;} public CSimple(Color col) { this.col=col;} public CSimple(Color col,short dense) { this.col=col; this.dense=dense;} public CSimple col(Color col) { this.col=col; return this;} public short shor() { return dense;} public short sver() { return dense;} public CSquare impl(CPlane pl) { short shift=rev2(dense,2); short delta=rev(dense,ext2(shift,2)); short mx=rev(cx,shift); short my=rev(cy,shift); short wx=rev(ext(cx,shift),delta==0 ? 1 : 0); short wy=rev(ext(cy,shift),delta==0 ? 1 : 0); short i=mx,j=my; while(i <= wx && j <= wy) { pl.set(i,j,col); i=ext(i); if(i > wx) { j=ext(j); i=mx;}} return this;} public short b3h() { return ext(cx,rev2(dense,2));} public short b6h() { return ext(cy,rev2(dense,2));} public short b9h() { return rev(cx,rev2(dense,2));} public short b0h() { return rev(cy,rev2(dense,2));}} class CFon extends CDot { Color col; short ww,hh; public CFon(short w,short h) { col=Color.orange; ww=w; hh=h;} public CFon(short w,short h,Color c) { col=c; ww=w; hh=h;} public CFon col(Color col) { this.col=col; return this;} public short shor() { return ww;} public short sver() { return hh;} public CSquare impl(CPlane pl) { short mx=rev(cx,rev2(ww,2)); short my=rev(cy,rev2(hh,2)); short wx=ext(mx,ww); short wy=ext(my,hh); short i=mx,j=my; while(i <= wx && j <= wy) { pl.set(i,j,col); i=ext(i); if(i > wx) { j=ext(j); i=mx;}} return this;} public short b3h() { return ext(cx,rev2(ww,2));} public short b6h() { return ext(cy,rev2(hh,2));} public short b9h() { return rev(cx,rev2(ww,2));} public short b0h() { return rev(cy,rev2(hh,2));}} class CRama implements CSquare { short b1,b2,b3,b4; CDot type; short uprava,uniz,cx,cy; public CRama(CDot type,short uprava,short uniz) { this.type=type; this.uprava=uprava; this.uniz=uniz;} public CSquare impl(CPlane pl) { short wx2=(short)(type.shor() * uprava / 2); short wy2=(short)(type.sver() * uniz / 2); type.ercent((short)(cx-wx2),(short)(cy-wy2)); short tmp; b4=type.b0h(); tmp=uprava; while(tmp--> 1) type.impl(pl).stepb3h(); b1=type.b3h(); tmp=uniz; while(tmp--> 1) type.impl(pl).stepb6h(); b2=type.b6h(); tmp=uprava; while(tmp--> 1) type.impl(pl).stepb9h(); b3=type.b9h(); tmp=uniz; while(tmp--> 1) type.impl(pl).stepb0h(); return this;} public short shor() { return(short)(type.shor() * uprava);} public short sver() { return(short)(type.sver() * uniz);} public short b3h() { return b1;} public short b6h() { return b2;} public short b9h() { return b3;} public short b0h() { return b4;} public CSquare ercent(short cxx,short cyy) { cx=cxx; cy=cyy; return this;} public CSquare stepb3h() { cx+=shor(); return this;} public CSquare stepb6h() { cy+=sver(); return this;} public CSquare stepb9h() { cx-=shor(); return this;} public CSquare stepb0h() { cy-=sver(); return this;}} class ToolbarCccLeftPanel extends Panel { LeftDefModeBtnPanel defBtnPanel; External3DPanel layoutPanel; DemoCccAuto demoAuto; public ToolbarCccLeftPanel() { super(); setLayout(new BorderLayout()); setBackground(Color.gray); add("North",layoutPanel=new External3DPanel(false,true)); add("Center",defBtnPanel=new LeftDefModeBtnPanel()); add("South",new CenterCccAuto(demoAuto=new DemoCccAuto()));} public Checkbox getCbExt() { return layoutPanel.getCbExt();} public boolean getExternal() { return layoutPanel.getCbExt().getState();} public Checkbox getCbD3() { return layoutPanel.getChD3();} public boolean getD3() { return layoutPanel.getChD3().getState();} public void enableCtrls(boolean enable) { demoAuto.enableCtrl(enable); defBtnPanel.enableBtns(enable);} public LeftDefModeBtnPanel getDefBtnPanel() { return defBtnPanel;} public DemoCccAuto getDemoAuto() { return demoAuto;}} class LeftDefModeBtnPanel extends Panel { Button btDef; Button btRepo; WidthHeight whPanel; public LeftDefModeBtnPanel() { super(); setLayout(new BorderLayout()); setBackground(Color.gray); add("North",btDef=new Button("Default")); add("Center",btRepo=new Button("Reposition")); add("South",whPanel=WidthHeight.createPanel(new FlowLayout(FlowLayout.CENTER,1-1,1-1)));} public void enableBtns(boolean enable) { btDef.enable(enable); btRepo.enable(enable); whPanel.enableCtrls(enable);} public Button getBtDef() { return btDef;} public Button getBtRepo() { return btRepo;} public WidthHeight getWhPanel() { return whPanel;}} class DemoCccAuto extends YellowAutoButton { public DemoCccAuto() { super();}} class CenterCccAuto extends Panel { public CenterCccAuto(DemoCccAuto demoAuto) { setBackground(Props.bgCtrlPanelColor); setLayout(new FlowLayout(FlowLayout.CENTER,1-1+1,1-1+1)); add(demoAuto);}} class COknoFrame { short w; short h; short x; short y; public boolean recall(Slip item) { String ati=new String(item.symbols()); int idx=ati.indexOf(","); if(idx < 0) return false; w=(short) Long.parseLong(ati.substring(0,idx)); ati=ati.substring(idx+1); idx=ati.indexOf(","); if(idx < 0) return false; h=(short) Long.parseLong(ati.substring(0,idx)); ati=ati.substring(idx+1); idx=ati.indexOf(","); if(idx < 0) return false; x=(short) Long.parseLong(ati.substring(0,idx)); ati=ati.substring(idx+1); y=(short) Long.parseLong(ati); return true;}} class CccOknoViewKadr implements Kadr { COkno clOkno; Kadr kadranosecc; public CccOknoViewKadr(COkno clOkno,Kadr kadranosecc) { super(); this.clOkno=clOkno; this.kadranosecc=kadranosecc;} public void make(Graphics ics,short mx,short my) { if(ics !=null) { Image im1=clOkno.canva((short)(mx+1),(short)(my+1)); Graphics ics2=im1.getGraphics(); kadranosecc.make(ics2,mx,my); COknoFrame frame=new COknoFrame(); frame.recall(clOkno.memo()); ics.drawImage(im1,frame.x,frame.y,null);}}} class CccRightPanel extends Panel { Button btStop; Checkbox cbPreview; static boolean longTermOp; TextField tfProg; public CccRightPanel() { super(); setLayout(new BorderLayout()); setBackground(Props.bgTopColor); add("South",tfProg=new TextField()); tfProg.enable(false); add("Center",btStop=new Button("Stop")); btStop.enable(false); add("North",cbPreview=new Checkbox("Preview&Close")); cbPreview.setState(false);} public Button getBtStop() { return btStop;} public Checkbox getCbPreview() { return cbPreview;} public TextField getTfProg() { return tfProg;} public boolean getPreview() { return cbPreview.getState();} public void setProgText(String txt) { tfProg.setText(txt);} public void disableStop() { btStop.enable(!true); cbPreview.enable(true); longTermOp=!true;} public void enableStop() { btStop.enable(true); cbPreview.enable(!true); longTermOp=true;} public boolean hasLongTermContin() { return longTermOp;}} class WindowsBackgrounds { private Color first=Color.orange; private Color second=Color.blue; private int cur=0; private Color current=first; public WindowsBackgrounds() { super();} public Color getCurrent() { return current;} public void next() { cur+=1; if(cur-cur / 2 * 2==1-1) current=first; else current=second;}}