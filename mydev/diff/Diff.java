package mydev.diff; import java.awt.BorderLayout; import java.awt.Button; import java.awt.Canvas; import java.awt.Color; import java.awt.Dimension; import java.awt.Event; import java.awt.FontMetrics; import java.awt.Frame; import java.awt.Graphics; import java.awt.Label; import java.awt.Panel; import java.awt.Rectangle; import java.awt.Scrollbar; import java.awt.TextField; import java.io.File; import java.io.FileInputStream; import java.io.IOException; import java.util.Vector; public class Diff extends Frame { boolean exit; TopPanel tPanel; LeftPanel lPanel; RightPanel rPanel; FlatPanel fPanel; public Diff() { super("File Diff"); this.exit=false;} void prepare() { setLayout(new BorderLayout()); add("West",lPanel=new LeftPanel()); add("East",rPanel=new RightPanel()); add("North",tPanel=new TopPanel()); add("Center",fPanel=new FlatPanel());} public static void main(String[] args) { Diff diff=new Diff(); diff.prepare(); diff.resize(800,600); diff.show(); while(!diff.exit) { try { Thread.sleep(300);} catch(InterruptedException e) { e.getMessage();}} diff.hide(); System.exit(1-1);} public boolean handleEvent(Event et) { if(et.id==Event.WINDOW_DESTROY) exit=true; if(et.id==Event.ACTION_EVENT && et.target==tPanel.loadDiff) { String lPath=tPanel.tfLeft.getText(); String rPath=tPanel.tfRight.getText(); LoadProc.load(lPanel,rPanel,lPath,rPath);} if(et.target==lPanel.hScroll) { lPanel.lText.scrollH(lPanel.hScroll.getValue());} if(et.target==lPanel.vScroll) { lPanel.lText.scroll(lPanel.vScroll.getValue()); lPanel.lNum.scroll(lPanel.vScroll.getValue());} if(et.target==rPanel.hScroll) { rPanel.rText.scrollH(rPanel.hScroll.getValue());} if(et.target==rPanel.vScroll) { rPanel.rText.scroll(rPanel.vScroll.getValue()); rPanel.rNum.scroll(rPanel.vScroll.getValue());} return super.handleEvent(et);}} class FirstFile { String path; Vector lines; StringBuffer buf; int longestLine; public FirstFile(String path) { super(); this.path=path; this.lines=new Vector(); this.buf=new StringBuffer(); this.buf.setLength(0); this.longestLine=0;} public void compare(FirstFile secondFile) { for(int i=0; i < lines.size(); i++) { LineOfCode firstLine=(LineOfCode) lines.elementAt(i); boolean found=false; for(int j=0; j < secondFile.getNumberOfLines() && !found; j++) { LineOfCode secondLine=secondFile.getLine(j); if(!secondLine.hasReferred) { if(firstLine.hc==secondLine.hc) { if(firstLine.str.compareTo(secondLine.str)==0) { firstLine.bind(secondLine); found=true;}}}}} for(int i=0; i < lines.size(); i++) { LineOfCode prevLine=null; if(i > 0) prevLine=(LineOfCode) lines.elementAt(i-1); LineOfCode firstLine=(LineOfCode) lines.elementAt(i); if(prevLine !=null && prevLine.bound !=null && firstLine.bound==null) { boolean found=false; for(int j=0; j < secondFile.getNumberOfLines() && !found; j++) { LineOfCode secondLine=secondFile.getLine(j); if(secondLine==prevLine.bound) { found=true; LineOfCode lineOfCode=new LineOfCode(); secondFile.insertLine(j,lineOfCode); firstLine.bindEmpty(lineOfCode);}}}} LineOfCode prevLine=null; for(int j=0; j < secondFile.getNumberOfLines(); j++) { LineOfCode secondLine=secondFile.getLine(j); if(secondLine.str==null) { boolean found=false; for(int i=0; i < lines.size() && !found; i++) { LineOfCode firstLine=(LineOfCode) lines.elementAt(i); if(firstLine.bound==secondLine) { prevLine=firstLine; found=true;}} continue;} else if(secondLine.hasReferred) { boolean found=false; for(int i=0; i < lines.size() && !found; i++) { LineOfCode firstLine=(LineOfCode) lines.elementAt(i); if(firstLine.bound==secondLine) { prevLine=firstLine; found=true;}} continue;} else { int found=-1; for(int i=0; i < lines.size() && found < 0; i++) { LineOfCode firstLine=(LineOfCode) lines.elementAt(i); if(firstLine==prevLine) found=i;} FirstFile firstFile=this; if(found >-1) { LineOfCode lineOfCode=new LineOfCode(); firstFile.insertLine(found,lineOfCode); secondLine.bindEmpty(lineOfCode);}}}} public void read() throws IOException { FileInputStream is=new FileInputStream(new File(path)); int item; int curLine=0; do { item=is.read(); if(item >-1) { char ch=(char)(byte) item; switch(ch) { case '\r' : break; case '\n' : addLine(new LineOfCode(++curLine,buf.toString())); buf.setLength(0); break; default : buf.append(ch); break;}}} while(item >-1); if(buf.length() > 0) { addLine(new LineOfCode(++curLine,buf.toString())); buf.setLength(0);} is.close();} public void addLine(LineOfCode lineOfCode) { lines.addElement(lineOfCode); int ln=lineOfCode.str.length(); if(ln > longestLine) longestLine=ln;} public LineOfCode getLine(int index) { LineOfCode line=(LineOfCode) lines.elementAt(index); return line;} public void insertLine(int index,LineOfCode lineOfCode) { lines.insertElementAt(lineOfCode,index+1);} public int getNumberOfLines() { return lines.size();} public int getLongestLine() { return longestLine;}} class LineOfCode { int num; int hc; String str; boolean hasReferred; LineOfCode bound; boolean boundEmpty; boolean empty; public LineOfCode() { super(); this.empty=true;} public LineOfCode(int num,String str) { super(); this.num=num; this.hc=Math.abs(str.hashCode()) % 1000; this.str=str; this.hasReferred=false; this.empty=false; this.boundEmpty=false;} public void bind(LineOfCode line) { line.hasReferred=true; bound=line;} public void bindEmpty(LineOfCode line) { line.hasReferred=true; bound=line; boundEmpty=true;} public String toString() { return "LineOfCode [num="+num+", hc="+hc+", str="+str+", hasReferred="+hasReferred+", bound="+bound+"]";}} class LoadProc { public static void load(LeftPanel lPanel,RightPanel rPanel,String lPath,String rPath) { if(lPath !=null && lPath.length() > 0 && rPath !=null && rPath.length() > 0) { FirstFile firstFile=new FirstFile(lPath); try { firstFile.read();} catch(IOException e) { e.printStackTrace();} FirstFile secondFile=new FirstFile(rPath); try { secondFile.read();} catch(IOException e) { e.printStackTrace();} firstFile.compare(secondFile); lPanel.loadClicked(firstFile); rPanel.loadClicked(secondFile);}}} class LeftPanel extends Panel { LeftText lText; LeftNumbers lNum; LeftTop lTop; Scrollbar vScroll; Scrollbar hScroll; public LeftPanel() { super(); setBackground(Color.gray); setLayout(new BorderLayout()); add("Center",lText=new LeftText()); add("East",vScroll=new Scrollbar(Scrollbar.VERTICAL,1,10,1,100)); add("South",hScroll=new Scrollbar(Scrollbar.HORIZONTAL,1,10,1,100)); add("West",lNum=new LeftNumbers()); add("North",lTop=new LeftTop());} public void loadClicked(FirstFile firstFile) { lText.clicked(firstFile); lNum.clicked(firstFile); lTop.clicked(firstFile); hScroll.setValues(1,10,1,firstFile.getLongestLine()); vScroll.setValues(1,10,1,firstFile.getNumberOfLines());}} class RightPanel extends Panel { RightText rText; RightNumbers rNum; RightTop rTop; Scrollbar vScroll; Scrollbar hScroll; public RightPanel() { super(); setBackground(Color.gray); setLayout(new BorderLayout()); add("Center",rText=new RightText()); add("East",vScroll=new Scrollbar(Scrollbar.VERTICAL,1,10,1,100)); add("South",hScroll=new Scrollbar(Scrollbar.HORIZONTAL,1,10,1,100)); add("West",rNum=new RightNumbers()); add("North",rTop=new RightTop());} public void loadClicked(FirstFile secondFile) { rText.clicked(secondFile); rNum.clicked(secondFile); rTop.clicked(secondFile); hScroll.setValues(1,10,1,secondFile.getLongestLine()); vScroll.setValues(1,10,1,secondFile.getNumberOfLines());}} class TopPanel extends Panel { Dimension d; Button loadDiff; TextField tfLeft; TextField tfRight; public TopPanel() { super(); setBackground(Color.green); add(new Label("First file:")); tfLeft=new TextField(35); tfLeft.setBackground(Color.white); add(tfLeft); add(new Label("Second file:")); tfRight=new TextField(35); tfRight.setBackground(Color.white); add(tfRight); add(loadDiff=new Button("Load Diff"));}} class FlatPanel extends Panel { Dimension d; public FlatPanel() { super(); setBackground(Color.gray); add(new Label("Left")); add(new Label("Right"));} public void paint(Graphics cs) { super.paint(cs);}} class TextPanel extends Canvas { final int PANEL_HEIGHT=480; final int PANEL_WIDTH=295; public TextPanel() { super();} void drawString(String str,int x,int y,Graphics cs,FontMetrics fmcs) { int i=0; boolean stop=false; do { char ch=str.charAt(i); if(ch !=' ' && ch !='\t') stop=true; i++;} while(!stop); String str2=str.substring(i-1); int strWidth=fmcs.stringWidth(str); int strWidth2=fmcs.stringWidth(str2); cs.drawString(""+str2,x+(strWidth-strWidth2),y);} String five(String num) { if(num.length()==1) return "0000"+num; if(num.length()==2) return "000"+num; if(num.length()==3) return "00"+num; if(num.length()==4) return "0"+num; return num;}} class LeftText extends TextPanel { boolean load; FirstFile firstFile; int curVScroll; int curHScroll; public LeftText() { super(); this.load=false; this.curVScroll=1; this.curHScroll=1; resize(PANEL_WIDTH,PANEL_HEIGHT); setBackground(Color.white);} public void scroll(int value) { this.curVScroll=value; repaint();} public void scrollH(int value) { this.curHScroll=value; repaint();} public void clicked(FirstFile firstFile) { load=true; this.firstFile=firstFile; repaint();} public void paint(Graphics cs) { super.paint(cs); Rectangle r=bounds(); if(r.width > 100 && r.height > 100) { cs.setColor(Color.red); cs.drawRect(0,0,r.width-1,r.height-1);} if(load) { cs.setColor(Color.black); FontMetrics fmcs=cs.getFontMetrics(); int strHeight=fmcs.getHeight(); int lines=PANEL_HEIGHT / strHeight+1; for(int i=0+(curVScroll-1); i < lines+(curVScroll-1) && i < firstFile.getNumberOfLines(); i++) { LineOfCode line=firstFile.getLine(i); String str=""+line.str; if(line.str !=null && str.length() >= curHScroll) { str=str.substring(curHScroll-1); int strWidth=fmcs.stringWidth(str); int x=3; int y=strHeight+(i-(curVScroll-1)) * strHeight; drawString(str,x,y,cs,fmcs); cs.drawRect(x-1,y-strHeight+1+1,strWidth+1+1,strHeight);}}}}} class RightText extends TextPanel { boolean load; FirstFile secondFile; int curVScroll; int curHScroll; public RightText() { super(); this.load=false; this.curVScroll=1; this.curHScroll=1; resize(PANEL_WIDTH,PANEL_HEIGHT); setBackground(Color.white);} public void scroll(int value) { this.curVScroll=value; repaint();} public void scrollH(int value) { this.curHScroll=value; repaint();} public void clicked(FirstFile secondFile) { load=true; this.secondFile=secondFile; repaint();} public void paint(Graphics cs) { super.paint(cs); Rectangle r=bounds(); if(r.width > 100 && r.height > 100) { cs.setColor(Color.red); cs.drawRect(0,0,r.width-1,r.height-1); } if(load) { cs.setColor(Color.black); FontMetrics fmcs=cs.getFontMetrics(); int strHeight=fmcs.getHeight(); int lines=PANEL_HEIGHT / strHeight+1; for(int i=0+(curVScroll-1); i < lines+(curVScroll-1) && i < secondFile.getNumberOfLines(); i++) { LineOfCode line=secondFile.getLine(i); String str=""+line.str; if(line.str !=null && str.length() >= curHScroll) { str=str.substring(curHScroll-1); int strWidth=fmcs.stringWidth(str); int x=3; int y=strHeight+(i-(curVScroll-1)) * strHeight; drawString(str,x,y,cs,fmcs); cs.drawRect(x-1,y-strHeight+1+1,strWidth+1+1,strHeight);}}}}} class LeftNumbers extends TextPanel { boolean load; FirstFile firstFile; int curVScroll; public LeftNumbers() { super(); this.load=false; this.curVScroll=1; resize(50,480); setBackground(Color.white);} public void scroll(int value) { this.curVScroll=value; repaint();} public void clicked(FirstFile firstFile) { load=true; this.firstFile=firstFile; repaint();} public void paint(Graphics cs) { super.paint(cs); Rectangle r=bounds(); if(r.width > 30 && r.height > 30) { cs.setColor(Color.blue); cs.drawRect(0,0,r.width-1,r.height-1);} if(load) { cs.setColor(Color.black); FontMetrics fmcs=cs.getFontMetrics(); int strHeight=fmcs.getHeight(); int lines=PANEL_HEIGHT / strHeight+1; for(int i=0+(curVScroll-1); i < lines+(curVScroll-1) && i < firstFile.getNumberOfLines(); i++) { LineOfCode line=firstFile.getLine(i); String num=""+(i+1); num=five(num); if(line.str==null) num+="--"; else if(line.boundEmpty) num+="+"; else if(line.bound !=null) num+="="; else num+="+"; int strWidth=fmcs.stringWidth(num); int x=3; int y=strHeight+(i-(curVScroll-1)) * strHeight; cs.drawString(num,x,y); cs.drawRect(x-1,y-strHeight+1+1,strWidth+1+1,strHeight);}}}} class RightNumbers extends TextPanel { boolean load; FirstFile secondFile; int curVScroll; public RightNumbers() { super(); this.load=false; this.curVScroll=1; resize(50,480); setBackground(Color.white);} public void scroll(int value) { this.curVScroll=value; repaint();} public void clicked(FirstFile secondFile) { load=true; this.secondFile=secondFile; repaint();} public void paint(Graphics cs) { super.paint(cs); Rectangle r=bounds(); if(r.width > 30 && r.height > 30) { cs.setColor(Color.blue); cs.drawRect(0,0,r.width-1,r.height-1);} if(load) { cs.setColor(Color.black); FontMetrics fmcs=cs.getFontMetrics(); int strHeight=fmcs.getHeight(); int lines=PANEL_HEIGHT / strHeight+1; for(int i=0+(curVScroll-1); i < lines+(curVScroll-1) && i < secondFile.getNumberOfLines(); i++) { LineOfCode line=secondFile.getLine(i); String num=""+(i+1); num=five(num); if(line.str==null) num+="--"; else if(line.hasReferred) num+="="; else num+="+"; int strWidth=fmcs.stringWidth(num); int x=3; int y=strHeight+(i-(curVScroll-1)) * strHeight; cs.drawString(num,x,y); cs.drawRect(x-1,y-strHeight+1+1,strWidth+1+1,strHeight);}}}} class LeftTop extends Canvas { boolean load; FirstFile firstFile; public LeftTop() { super(); this.load=false; resize(300,17); setBackground(Color.gray);} public void clicked(FirstFile firstFile) { load=true; this.firstFile=firstFile; repaint();} public void paint(Graphics cs) { super.paint(cs); Rectangle r=bounds(); if(r.width > 30 && r.height > 30) { cs.setColor(Color.yellow); cs.drawRect(0,0,r.width-1,r.height-1);} if(load) { cs.setColor(Color.white); FontMetrics fmcs=cs.getFontMetrics(); int strHeight=fmcs.getHeight(); String num=firstFile.path; int x=3; int y=strHeight+0 * strHeight; cs.drawString(num,x,y);}}} class RightTop extends Canvas { boolean load; FirstFile secondFile; public RightTop() { super(); this.load=false; resize(300,17); setBackground(Color.gray);} public void clicked(FirstFile secondFile) { load=true; this.secondFile=secondFile; repaint();} public void paint(Graphics cs) { super.paint(cs); Rectangle r=bounds(); if(r.width > 30 && r.height > 30) { cs.setColor(Color.yellow); cs.drawRect(0,0,r.width-1,r.height-1);} if(load) { cs.setColor(Color.white); FontMetrics fmcs=cs.getFontMetrics(); int strHeight=fmcs.getHeight(); String num=secondFile.path; int x=3; int y=strHeight+0 * strHeight; cs.drawString(num,x,y);}}}