package mydev.wrg; import java.awt.BorderLayout; import java.awt.Button; import java.awt.Canvas; import java.awt.Color; import java.awt.Event; import java.awt.FlowLayout; import java.awt.FontMetrics; import java.awt.Frame; import java.awt.Graphics; import java.awt.Label; import java.awt.Panel; import java.awt.Rectangle; import java.awt.TextField; import java.io.File; import java.io.FileInputStream; import java.io.IOException; import java.util.Vector; public class Wrng1 extends Frame { boolean compl; LunchPanel pn1; MyReport rp; FilterPanel pnx; public Wrng1() { super("Extended"); this.compl=false;} public static void main(String[] args) { Wrng1 fr=new Wrng1(); fr.prepare1(); fr.show(); while(!fr.completed()) { fr.timeout(); fr.doSome();} fr.destroy();} void prepare1() { setBackground(Color.lightGray); setLayout(new BorderLayout()); add("North",pn1=new LunchPanel()); add("Center",rp=new MyReport()); add("South",pnx=new FilterPanel()); pack();} private boolean completed() { return compl;} public boolean handleEvent(Event et1) { if(et1.id==Event.WINDOW_DESTROY) compl=true; if(et1.id==Event.ACTION_EVENT && et1.target==pn1.lnch) startProcessing(); if(et1.id==Event.ACTION_EVENT && et1.target==pnx.b1) addFltChar(); if(et1.id==Event.ACTION_EVENT && et1.target==pnx.b2) removeFltChars(); return super.handleEvent(et1);} private void removeFltChars() { String str=pnx.ch1.getText(); while(str.length() > 0) { char ch=str.charAt(0); if(ch !='\r' && ch !='\n' && ch !=' ' && ch !='\t') { if(pnx.tx.getText().indexOf(ch) >= 0) pnx.tx.setText(pnx.tx.getText().replace(ch,' '));} str=str.substring(1);} pnx.ch1.setText(""); String rc=""; str=pnx.tx.getText(); for(int i=0; i < str.length(); i++) { char ch=str.charAt(i); if(ch !=' ') rc+=ch;} pnx.tx.setText(rc);} private void addFltChar() { String str=pnx.ch1.getText(); boolean selected=false; while(str.length() > 0 && !selected) { char ch=str.charAt(0); if(ch !='\r' && ch !='\n' && ch !=' ' && ch !='\t') { if(pnx.tx.getText().indexOf(ch) < 0) { pnx.tx.setText(pnx.tx.getText()+ch); selected=true;}} str=str.substring(1);} pnx.ch1.setText("");} void startProcessing() { FileInputStream is1=null; int count; byte[] b1=new byte[1]; int content; rp.rg.clear(); rp.rg.submitFlt(pnx.tx.getText()); int mlCount=0; try { is1=new FileInputStream(new File(pn1.tf.getText())); content=0; do { b1[0]=(byte) 0; count=is1.read(b1); char ch=(char) b1[0]; if(ch !='\r') { if(ch !='\n') { if(ch !=' ' && ch !='\t' && b1[0] !=0) { content++; rp.rg.submitCh(ch);}} else { if(content > 0) mlCount++; content=0;}}} while(count >= 0); if(content > 0) mlCount++; content=0; pn1.ml.setText(""+mlCount); rp.repaint();} catch(Exception e) { is1=null;} if(is1 !=null) try { is1.close();} catch(IOException ex) { ex.getMessage();}} void timeout() { try { Thread.sleep(95);} catch(Throwable e) { e.getMessage();}} private void doSome() {} void destroy() { hide(); dispose(); System.exit(1-1);}} class LunchPanel extends Panel { TextField tf; TextField ml; Button lnch; public LunchPanel() { setLayout(new FlowLayout()); add(new Label("Path:")); add(tf=new TextField(32)); add(lnch=new Button("Lunch")); tf.setText("path\\to\\source\\file\\for\\reflection"); add(new Label("Meaningful lines:")); add(ml=new TextField(5)); ml.setText(""+0); ml.enable(false);}} class MyReport extends Canvas { Rectangle r1; Reg rg; public MyReport() { r1=null; resize(400,300); setBackground(Color.white); this.rg=new Reg();} public Reg getRg() { return rg;} public void paint(Graphics cs) { super.paint(cs); if(r1==null) r1=bounds(); cs.setColor(Color.black); cs.drawRect(0,0,r1.width-1,r1.height-1); int x=1; int y=1; int columnWidth=0; int DT=3-2-1; String flt=rg.getFlt(); FontMetrics f1=cs.getFontMetrics(); int h1=f1.getAscent()+f1.getDescent()+f1.getLeading(); int w1=f1.stringWidth("bbb"); int w2=f1.stringWidth("bbbbb"); boolean stop=false; for(int i=0; !stop && i <= flt.length(); i++) { y+=h1; if(y+h1 > r1.height-1) { x+=w1+DT+w2; y=1; y+=h1; cs.setColor(Color.magenta); cs.drawLine(x,y,x,r1.height-y); cs.setColor(Color.black);} char ch='\n'; if(i < flt.length()) ch=flt.charAt(i); int sz=rg.itemz(ch); String s1="al"; if(ch !='\n') s1=""+ch; String s2=""+sz; int wx=f1.stringWidth(s1); if(w1+w2+DT > columnWidth) columnWidth=w1+w2+DT; cs.drawString(s1,x+(w1-wx) / 2,y); cs.drawString(s2,x+w1+DT,y);}}} class FilterPanel extends Panel { TextField tx; TextField ch1; Button b1; Button b2; public FilterPanel() { setLayout(new FlowLayout()); add(new Label("Filter:")); add(tx=new TextField(36)); tx.enable(false); tx.setText("(){};iforwhlecharnt[]"); add(new Label("One:")); add(ch1=new TextField(2)); ch1.setText(""+1); add(b1=new Button("Add")); add(b2=new Button("Remove"));}} class Rec { char ch; int enc; public Rec(char ch,int enc) { this.ch=ch; this.enc=enc;} public Rec(char ch) { this.ch=ch; this.enc=0;} public char getCh() { return ch;} public int getEnc() { return enc;} public void enc() { enc++;}} class Reg { Vector v1; String flt; public Reg() { this.v1=new Vector(); this.flt="";} public void clear() { this.v1=new Vector(); this.flt="";} public void submitFlt(String flt) { this.flt=flt; for(int i=0; i < flt.length(); i++) v1.addElement(new Rec(flt.charAt(i))); v1.addElement(new Rec('\n'));} public void submitCh(char ch) { boolean stop=false; for(int i=0; !stop && i < v1.size(); i++) { Rec r=(Rec) v1.elementAt(i); if(r.getCh()==ch) { r.enc(); stop=true;}} if(!stop) { boolean dva=false; for(int i=0; !dva && i < v1.size(); i++) { Rec r=(Rec) v1.elementAt(i); if(r.getCh()=='\n') { r.enc(); dva=true;}}}} public int size1() { return v1.size();} public int itemz(char ch) { int res=0; for(int i=0; res==0 && i < v1.size(); i++) { Rec r=(Rec) v1.elementAt(i); if(r.getCh()==ch) res=r.getEnc();} return res;} public String getFlt() { return flt;}}
