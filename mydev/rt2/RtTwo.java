package mydev.rt2; import java.awt.BorderLayout; import java.awt.Button; import java.awt.Canvas; import java.awt.Color; import java.awt.Event; import java.awt.FileDialog; import java.awt.FlowLayout; import java.awt.Frame; import java.awt.Graphics; import java.awt.Image; import java.awt.Label; import java.awt.Panel; import java.awt.Toolkit; import java.awt.image.ColorModel; import java.awt.image.ImageConsumer; import java.awt.image.MemoryImageSource; import java.util.Hashtable; public class RtTwo { public static void main(String[] args) { RtTwoFrame fr=new RtTwoFrame(); fr.prepare(); fr.show();}} class RtTwoFrame extends Frame { public RtTwoFrame() { super("arkbook");} public void prepare() { MainCanvas mc=new MainCanvas(); mc.resize(800,600); setLayout(new BorderLayout()); add("Center",mc); OpenDelegate opDel=new OpenDelegate(this); ShowFirstDelegate d1=new ShowFirstDelegate(mc); ShowSecondDelegate d2=new ShowSecondDelegate(mc); ShowThirdDelegate d3=new ShowThirdDelegate(mc,d1,d2); ShowFourthDelegate d4=new ShowFourthDelegate(mc,d1,d2); ShowFifthDelegate d5=new ShowFifthDelegate(mc,d1,d2); SecondaryPanel sp=new SecondaryPanel(opDel,d1,d2,d3,d4,d5); add("South",sp); pack();} public boolean handleEvent(Event evt) { if(evt.id==Event.WINDOW_DESTROY) { hide(); dispose();} return super.handleEvent(evt);}} class MainCanvas extends Canvas { private Image img; private ImgBox bx; public MainCanvas() { super(); setBackground(Color.gray);} public void paint(Graphics ics) { super.paint(ics); if(img !=null) ics.drawImage(img,0,0,this); if(bx !=null) ics.drawImage(bx.image4drawing(),0,0,this);} public void setImage(ImgBox bx1) { this.bx=bx1;} public void setImage1(Image img1) { this.img=img1;} public void update() { repaint();}} class SecondaryPanel extends Panel { private Button open1; private Button open2; private Button next; private Button prev; private OpenDelegate opDel; private Label file1; private Label file2; private Label prepare; private ShowFirstDelegate firstDel; private ShowSecondDelegate secondDel; private ShowThirdDelegate thirdDel; private ShowFourthDelegate fourthDel; private ShowFifthDelegate fifthDel; final int CV_First=1; final int CV_Second=2; final int CV_AminusB=3; final int CV_BminusA=4; final int CV_orr_=5; final int CV_orr1=5+1; final int CV_orr2=5+1+1; final int CV_orr3=5+1+1+1; final int CV_orr4=5+1+1+1+1; final int CV_orr5=5+1+1+1+1+1; final int CV_orr6=5+1+1+1+1+1+1; final int CV_orr7=5+1+1+1+1+1+1+1; final int CV_orr8=5+1+1+1+1+1+1+1+1; final int CV_orr9=5+1+1+1+1+1+1+1+1+1; int currentView; public SecondaryPanel(OpenDelegate opDel,ShowFirstDelegate firstDel,ShowSecondDelegate secondDel,ShowThirdDelegate thirdDel,ShowFourthDelegate fourthDel,ShowFifthDelegate fifthDel) { super(); setLayout(new FlowLayout()); setBackground(Color.green); open1=new Button("Open"); add(open1); file1=new Label("Select first file"); add(file1); open2=new Button("ReOpen"); add(open2); file2=new Label("Select second file"); add(file2); prev=new Button("Previous"); add(prev); prev.enable(false); next=new Button("Next"); add(next); next.enable(false); prepare=new Label("Open two image files."); add(prepare); this.opDel=opDel; this.firstDel=firstDel; this.secondDel=secondDel; this.thirdDel=thirdDel; this.fourthDel=fourthDel; this.fifthDel=fifthDel; currentView=0;} public boolean handleEvent(Event et) { boolean rs=super.handleEvent(et); if(et.target==open1 && et.id==Event.ACTION_EVENT) openFirstFile(); if(et.target==open2 && et.id==Event.ACTION_EVENT) openSecondFile(); if(et.target==next && et.id==Event.ACTION_EVENT) moveNextView(); if(et.target==prev && et.id==Event.ACTION_EVENT) movePreviousView(); return rs;} private void movePreviousView() { currentView--; if(currentView < CV_First) currentView=CV_First; next.enable(true); prev.enable(currentView==CV_First); view(); review();} private void view() { if(currentView==CV_First) firstDel.restart(); if(currentView==CV_Second) secondDel.restart(); if(currentView==CV_AminusB) thirdDel.restart(); if(currentView==CV_BminusA) fourthDel.restart(); if(currentView >= CV_orr_ && currentView <= CV_orr9) fifthDel.restart(currentView);} private void moveNextView() { currentView++; if(currentView > CV_orr9) currentView=CV_orr9; prev.enable(true); next.enable(currentView==CV_orr9); view(); review();} private void openSecondFile() { currentView=CV_Second; String secondPath=opDel.openSecondFile(); file2.setText(secondPath); secondDel.reviewImg1(opDel.getSecondPath()); review();} private void openFirstFile() { currentView=CV_First; String firstPath=opDel.openFirstFile(); file1.setText(firstPath); firstDel.reviewImg1(opDel.getFirstPath()); review();} private void review() { if(file1.getText().compareTo("Select first file") !=0 && file2.getText().compareTo("Select second file") !=0) { next.enable(currentView !=CV_orr9); prev.enable(currentView !=CV_First); prepare.setText("Previously nexting difference.");}}} class OpenDelegate { private Frame fr; private String firstPath; private String secondPath; public OpenDelegate(Frame fr) { super(); this.fr=fr;} public String getFirstPath() { return firstPath;} public String getSecondPath() { return secondPath;} public String openSecondFile() { FileDialog fd=new FileDialog(fr,"Open"); fd.show(true); String dir=fd.getDirectory(); String file=fd.getFile(); secondPath=dir+file; return file;} public String openFirstFile() { FileDialog fd=new FileDialog(fr,"Open"); fd.show(true); String dir=fd.getDirectory(); String file=fd.getFile(); firstPath=dir+file; return file;}} class ShowFirstDelegate implements Runnable { MainCanvas mcs; String firstPath; Image preload; ImgBox imgBox; public ShowFirstDelegate(MainCanvas mcs) { super(); this.mcs=mcs;} public void reviewImg1(String firstPath) { if(this.firstPath !=null && this.firstPath.compareTo(firstPath) !=0) preload=null; this.firstPath=firstPath; run();} public void run() { if(preload==null) preload=Toolkit.getDefaultToolkit().getImage(firstPath); mcs.setImage(imgBox=new ImgBox(firstPath,preload)); mcs.update();} public void restart() { run();} public ImgBox getImgBox() { return imgBox;}} class ShowSecondDelegate implements Runnable { MainCanvas mcs; String secondPath; Image preload; ImgBox imgBox; public ShowSecondDelegate(MainCanvas mcs) { super(); this.mcs=mcs;} public void reviewImg1(String secondPath) { if(this.secondPath !=null && this.secondPath.compareTo(secondPath) !=0) preload=null; this.secondPath=secondPath; run();} public void run() { if(preload==null) preload=Toolkit.getDefaultToolkit().getImage(secondPath); mcs.setImage(imgBox=new ImgBox(secondPath,preload)); mcs.update();} public void restart() { run();} public ImgBox getImgBox() { return imgBox;}} class ShowThirdDelegate implements Runnable { MainCanvas mcs; ShowFirstDelegate d1; ShowSecondDelegate d2; public ShowThirdDelegate(MainCanvas mcs,ShowFirstDelegate d1,ShowSecondDelegate d2) { super(); this.mcs=mcs; this.d1=d1; this.d2=d2;} public void run() { mcs.setImage(new ImgBox(d1.getImgBox(),d2.getImgBox(),mcs)); mcs.update();} public void restart() { run();}} class ShowFourthDelegate implements Runnable { MainCanvas mcs; ShowFirstDelegate d1; ShowSecondDelegate d2; public ShowFourthDelegate(MainCanvas mcs,ShowFirstDelegate d1,ShowSecondDelegate d2) { super(); this.mcs=mcs; this.d1=d1; this.d2=d2;} public void run() { mcs.setImage(new ImgBox(d2.getImgBox(),d1.getImgBox(),mcs)); mcs.update();} public void restart() { run();}} class ShowFifthDelegate implements Runnable { MainCanvas mcs; ShowFirstDelegate d1; ShowSecondDelegate d2; int idx; public ShowFifthDelegate(MainCanvas mcs,ShowFirstDelegate d1,ShowSecondDelegate d2) { super(); this.mcs=mcs; this.d1=d1; this.d2=d2;} public void run() { mcs.setImage(new ImgBox(d1.getImgBox(),d2.getImgBox(),new Integer(idx),mcs)); mcs.update();} public void restart(int idx) { this.idx=idx; run();}} class NskConsumer implements ImageConsumer { ColorModel cm1; int w1; int h1; int hs1; Hashtable ht1; int status1; int px1[][]; byte xp1[][]; public NskConsumer() { super();} public Image createImage(Canvas com) { int bits=32; int redMask=16711680; int greenMask=65280; int blueMask=255; java.awt.image.DirectColorModel dcm=new java.awt.image.DirectColorModel(bits,redMask,greenMask,blueMask); MemoryImageSource mw1=new MemoryImageSource(w1,h1,dcm,extract(px1),0,w1); Image img=com.createImage(mw1); return img;} int[] extract(int[][] pk12) { int[] rs=new int[w1 * h1]; int ix=0; for(int j=0; j < h1; j++) for(int i=0; i < w1; i++) rs[ix++]=pk12[i][j]; return rs;} public void imageComplete(int status) { this.status1=status;} public void setColorModel(ColorModel cm) { this.cm1=cm;} public void setDimensions(int w,int h) { this.w1=w; this.h1=h;} public void setHints(int hs) { this.hs1=hs;} public void setPixels(int x,int y,int w,int h,ColorModel cm,byte[] px,int of,int scs) { if(xp1==null) xp1=new byte[w1][h1]; for(int i=0; i < w; i++) for(int j=0; j < h; j++) xp1[x+i][y+j]=px[j * w+i];} public void setPixels(int x,int y,int w,int h,ColorModel cm,int[] px,int of,int scs) { if(px1==null) px1=new int[w1][h1]; for(int i=0; i < w; i++) for(int j=0; j < h; j++) px1[x+i][y+j]=px[j * w+i];} public void setProperties(Hashtable ht) { this.ht1=ht;}} class ImgBox { private Canvas mcs; private Image img; private String path; private NskConsumer nskConsumer; private ImgBox a; private ImgBox b; private Integer a1; public ImgBox() { super();} public ImgBox(ImgBox a,ImgBox b,Canvas mcs) { super(); this.a=a; this.b=b; this.mcs=mcs;} public ImgBox(ImgBox a,ImgBox b,Integer a1,Canvas mcs) { super(); this.a=a; this.b=b; this.a1=a1; this.mcs=mcs;} public ImgBox(String fPath,Image im) { super(); this.path=fPath; this.img=im;} public Image image4drawing() { if(img==null) { int[] apx=a.nskConsumer.extract(a.nskConsumer.px1); int[] bpx=b.nskConsumer.extract(b.nskConsumer.px1); for(int i=0; i < apx.length; i++) if(a1 !=null) switch(a1.intValue()) { default : case 0 : apx[i]=apx[i] | bpx[i]-apx[i] & bpx[i]; break; case 1 : apx[i]=apx[i] | bpx[i]; break; case 2 : apx[i] |= apx[i] | bpx[i]; break; case 3 : apx[i] &= apx[i] | bpx[i]; break; case 4 : apx[i]=apx[i] & bpx[i]; break; case 5 : apx[i] |= apx[i] & bpx[i]; break; case 6 : apx[i] &= apx[i] & bpx[i]; break; case 7 : apx[i]=apx[i] ^ bpx[i]; break; case 8 : apx[i] |= apx[i] ^ bpx[i]; break; case 9 : apx[i] &= apx[i] ^ bpx[i]; break;} else apx[i]-=bpx[i]; int bits=32; int redMask=16711680; int greenMask=65280; int blueMask=255; java.awt.image.DirectColorModel dcm=new java.awt.image.DirectColorModel(bits,redMask,greenMask,blueMask); MemoryImageSource mw1=new MemoryImageSource(a.nskConsumer.w1,a.nskConsumer.h1,dcm,apx,0,a.nskConsumer.w1); img=mcs.createImage(mw1);} else if(nskConsumer==null) img.getSource().startProduction(nskConsumer=new NskConsumer()); return img;}}
