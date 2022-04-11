package mydev.olin; import java.awt.BorderLayout; import java.awt.Button; import java.awt.Color; import java.awt.Event; import java.awt.FlowLayout; import java.awt.Frame; import java.awt.Label; import java.awt.List; import java.awt.Panel; import java.awt.TextArea; import java.awt.TextField; import java.io.File; import java.io.IOException; import java.util.Date; import mydev.aaa.IO; import mydev.aaa.Sleeper; import mydev.vutils.Data; import mydev.vutils.Ester; import mydev.vutils.ExpectHere; import mydev.vutils.Files; import mydev.vutils.Filter; import mydev.vutils.Metr; import mydev.vutils.Spiska; import mydev.vutils.Time; public class OneLine extends Frame implements Runnable { final static boolean D=false; PathPanel pathPanel; LeftPanel leftPanel; TextArea taSource; LogPanel logPanel; private Spiska leftItems=new Spiska(); String pathParam; String leftPanelParam; public OneLine(String title) { super(title); setLayout(new BorderLayout()); setBackground(Color.gray);} public String loadSource(String path) throws IOException { return new Ester("").append(new mydev.oline.OneLine().read(path)).toString();} public String line(String path) throws IOException { return new mydev.oline.OneLine().line(path);} public static void main(String[] args) throws IOException { OneLine main=new OneLine("Leaves Days Publicity"); if(args.length > 0) { String param=args[0]; String line=main.line(param); main.setPathParam(param); System.out.println(line); return;} else { System.out.println("Please specify source file as a parameter.");} if(D) System.out.println("Publish Source Studio"); main.show(); main.reMoveRnd(); main.resize(1024,768); main.start(); main.pack(); Sleeper sleeper=new Sleeper(); while(WorkingHolder.working) { main.refreshUpdatable(); sleeper.sleep(123);} main.hide(); main.dispose(); System.exit(1-1);} public void setPathParam(String path) { this.pathParam=path; File file=new File(path); String fullPath=file.getAbsolutePath(); if(file.isDirectory()) updateInfo(null); else { String item=file.getName(); leftPanelParam=item; leftItems.append(fullPath);}} protected void reMoveRnd() { long x1=new Date().getTime(); if(x1 < 0) x1 *=-1; x1=10+3 *(x1 % 100); long y1=new Date().getTime(); if(y1 < 0) y1 *=-1; y1=10+2 *(y1 % 100); move((int) x1,(int) y1);} public boolean handleEvent(Event et) { if(et.id==Event.WINDOW_DESTROY) WorkingHolder.working=false; if(et.id==Event.ACTION_EVENT && et.target==this.pathPanel.btScan) updateInfo(null); if(et.id==Event.LIST_SELECT && et.target==this.leftPanel.lsItems) leftSelected(); if(et.id==Event.ACTION_EVENT && et.target==this.leftPanel.sizeSavePanel.savePanel.btOneLine) transform(); if(et.id==Event.ACTION_EVENT && et.target==this.leftPanel.sizeSavePanel.savePanel.btSave) saveLine(); if(et.id==Event.ACTION_EVENT && et.target==this.leftPanel.sizeSavePanel.savePanel.btAuto) { if(this.leftPanel.lsItems.countItems() < 1) { ExpectHere eh=new ExpectHere(); updateInfo(eh); eh.stopUntilExpected();} autoLines();} return super.handleEvent(et);} public void run() { Time t=new Time(); t.start(); if(leftPanel.lsItems.countItems() > 0) { int size1=leftPanel.lsItems.countItems(); for(int i=0; i < size1; i++) { leftPanel.lsItems.select(i); transform(); saveLine();}} t.trust(); logPanel.addLogComplete("Auto OneLine transform complete "+t.ess()); pathPanel.btScan.enable(true);} protected void autoLines() { leftPanel.sizeSavePanel.savePanel.btAuto.enable(false); pathPanel.btScan.enable(false); new Thread(this).start();} protected void saveLine() { int idxSel=leftPanel.lsItems.getSelectedIndex(); String curSel=leftPanel.lsItems.getSelectedItem(); String path=(String) leftItems.at(idxSel); String content=taSource.getText(); logPanel.addLog("Save line with "+new Metr(content.length()).repr(',')+" byte(s) to "+path); try { new Data().write(path,new Ester(content));} catch(IOException ex) { ex.printStackTrace(); logPanel.addLog("Can't write "+path+" . "+ex.getMessage());} leftPanel.sizeSavePanel.savePanel.btOneLine.enable(false); leftPanel.sizeSavePanel.savePanel.btSave.enable(false);} protected void transform() { int idxSel=leftPanel.lsItems.getSelectedIndex(); String curSel=leftPanel.lsItems.getSelectedItem(); String path=(String) leftItems.at(idxSel); logPanel.addLog("One line transformation complete "+curSel); String line=""; try { line=line(path);} catch(IOException e) { e.printStackTrace();} taSource.setText(line); leftPanel.sizeSavePanel.savePanel.btSave.enable(true);} protected void start() { add("North",pathPanel=new PathPanel(pathParam)); add("West",leftPanel=new LeftPanel()); add("Center",taSource=new TextArea()); taSource.setEditable(false); add("South",logPanel=new LogPanel()); if(leftPanelParam !=null) { leftPanel.lsItems.addItem(leftPanelParam); leftPanel.lsItems.select(0); this.leftPanel.ignorStatPanel.statPanel.tfAmount.setText("1"); this.leftPanel.sizeSavePanel.sizePanel.tfSize.setText(""+new File(pathPanel.pathParam).length()); transform();}} protected void refreshUpdatable() {} protected void updateInfo(ExpectHere eh) { String path=pathPanel.tfPath.getText(); leftPanel.lsItems.clear(); leftItems=new Spiska(); taSource.setText(""); leftPanel.sizeSavePanel.savePanel.dropBtnSkipPosition(); new Thread(new ScanLeft(leftPanel,path,leftItems,logPanel,eh)).start();} protected void leftSelected() { int idxSel=leftPanel.lsItems.getSelectedIndex(); String curSel=leftPanel.lsItems.getSelectedItem(); String path=(String) leftItems.at(idxSel); logPanel.addLog("Selected item "+path); String src=""; try { src=loadSource(path);} catch(IOException ex) { ex.printStackTrace(); logPanel.addLog("Can't read "+path+" . "+ex.getMessage());} taSource.setText(src); leftPanel.sizeSavePanel.savePanel.tfPos.setText(""+(idxSel+1)); leftPanel.sizeSavePanel.savePanel.btOneLine.enable(true);}} class LogPanel extends Panel implements Runnable { TextArea taLogging; private String res=new String(); private Object syncObj=new Object(); private Thread worker1; private boolean needUpdate; public LogPanel() { super(); setBackground(Color.green); setLayout(new BorderLayout()); add("Center",taLogging=new TextArea(5,20)); taLogging.setEditable(false);} public void run() { Sleeper sleeper=new Sleeper(); while(WorkingHolder.working) { synchronized(syncObj) { if(needUpdate) { taLogging.setText(res.toString()); needUpdate=false;}} sleeper.sleep(130 * 2);}} public void addLog(String item) { if(item !=null && item.length() > 0) { synchronized(syncObj) { res=new Ester(item).append('\n')+res; needUpdate=true;} if(worker1==null) { worker1=new Thread(this); worker1.start();}}} public void addLogComplete(String item) { addLog(item.replace('_',':'));}} class WorkingHolder { static boolean working=true;} class PathPanel extends Panel { Label lbPath; TextField tfPath; Button btScan; String pathParam; public PathPanel(String pathParam) { super(); this.pathParam=pathParam; setLayout(new FlowLayout()); setBackground(Props.bgTopColor); add(lbPath=new Label("Source location:")); add(tfPath=new TextField("",50+20+10+5-30-20+5)); tfPath.setText("C:\\Users\\fantom\\workspace\\Architecho"); tfPath.setText("C:\\Users\\fantom\\workspace"); if(pathParam !=null) tfPath.setText(pathParam); add(btScan=new Button("Scan"));}} class LeftPanel extends Panel { IgnorStatPanel ignorStatPanel; List lsItems; LeftSizeSavePanel sizeSavePanel; public LeftPanel() { super(); setBackground(Props.bgAlertColor); setLayout(new BorderLayout()); add("North",ignorStatPanel=new IgnorStatPanel()); add("Center",lsItems=new List(22,false)); add("South",sizeSavePanel=new LeftSizeSavePanel());}} class IgnorStatPanel extends Panel { TextArea taIgnoreList; StatPanel statPanel; public IgnorStatPanel() { super(); setLayout(new BorderLayout()); add("Center",taIgnoreList=new TextArea(4,4)); add("South",statPanel=new StatPanel()); add("West",new Label("Ignore list:")); taIgnoreList.setText("mydev."+"aaa;"+"\n"+"mydev."+"vutils;"+"\n"+"mydev."+"oneway;"+"\n"+"mydev."+"about;"+"\n"+"mydev."+"gforce;"+"\n"+"mydev."+"t3;"+"\n"+"mydev."+"oline;");}} class LeftSizeSavePanel extends Panel { LeftOneLineSavePanel savePanel; SizePanel sizePanel; public LeftSizeSavePanel() { super(); setBackground(Color.lightGray); setLayout(new BorderLayout()); add("North",savePanel=new LeftOneLineSavePanel()); add("Center",sizePanel=new SizePanel());}} class LeftOneLineSavePanel extends Panel { TextField tfPos; Button btOneLine; Button btSave; Button btAuto; public LeftOneLineSavePanel() { super(); setBackground(Props.bgDownColor); setLayout(new FlowLayout()); add(tfPos=new TextField(5-1)); tfPos.setEditable(false); add(btOneLine=new Button("OneLine")); add(btSave=new Button("Save")); add(new AutoButtonFrame(btAuto=new Button("Auto"))); dropBtnSkipPosition(); btAuto.enable();} public void dropBtnSkipPosition() { tfPos.setText(""); btOneLine.enable(false); btSave.enable(false); btAuto.enable(false);}} class AutoButtonFrame extends Panel { Button autoBtn; public AutoButtonFrame(Button autoBtn) { super(); this.autoBtn=autoBtn; FlowLayout fl; setLayout(fl=new FlowLayout()); fl.setHgap(2+1); fl.setVgap(2); setBackground(Color.yellow); add(autoBtn);}} class StatPanel extends Panel { Label lbDst; Label lbAmount; TextField tfAmount; public StatPanel() { super(); setBackground(Color.gray); setLayout(new FlowLayout()); add(lbDst=new Label("*.java")); add(tfAmount=new TextField("0",12)); add(lbAmount=new Label("file(s)")); tfAmount.setEditable(false);}} class SizePanel extends Panel { Label lbSize; TextField tfSize; public SizePanel() { super(); setBackground(Color.gray); setLayout(new FlowLayout()); add(tfSize=new TextField("0",25-1-2)); add(lbSize=new Label("byte(s)")); tfSize.setEditable(false);}} class ScanLeft implements Runnable { private LeftPanel leftPanel; private LogPanel logPanel; private String path; private Spiska leftItems; private long amount; private long size; private ExpectHere expectHere; public ScanLeft(LeftPanel leftPanel,String path,Spiska leftItems,LogPanel logPanel,ExpectHere eh) { super(); this.leftPanel=leftPanel; this.path=path; this.leftItems=leftItems; this.logPanel=logPanel; this.expectHere=eh;} void displayAmount(Metr m) { leftPanel.ignorStatPanel.statPanel.tfAmount.setText(""+m.repr(' '));} void displaySize(Metr m) { leftPanel.sizeSavePanel.sizePanel.tfSize.setText(""+m.repr(','));} void addItem(String item,String fullPath) { leftPanel.lsItems.addItem(item); leftItems.append(fullPath);} public void run() { Time t=new Time(); t.start(); updateStat(); scanItems(path,".java"); t.trust(); logPanel.addLogComplete("Source scan complete "+t.ess()); leftPanel.sizeSavePanel.savePanel.btAuto.enable(); if(expectHere !=null) expectHere.markAsReached();} protected void updateStat() { Metr m=new Metr(0); m.inc(amount); displayAmount(m); m=new Metr(0); m.inc(size); displaySize(m);} protected void scanItems(String path,String ext) { Ester[] start=new Files().tree(new Ester(path)); Ester[] select=new Filter().endsWithIncensitive(start,new Ester(ext)); String ignoreList=this.leftPanel.ignorStatPanel.taIgnoreList.getText(); for(int i=0; i < select.length; i++) { File file=new File(select[i].toString()); if(hasIgnoreCoincidence(file.getAbsolutePath(),ignoreList)) continue; addItem(file.getName(),file.getAbsolutePath()); amount+=1; size+=file.length(); updateStat();}} boolean hasIgnoreCoincidence(String absPath,String ignoreList) { Spiska ign=getLines(ignoreList); try { byte[] data=new IO().read(absPath); String content=new Ester(data).toString(); for(int i=0; i < ign.size(); i++) { String c=(String) ign.at(i); boolean contains=content.indexOf(c) >= 0; if(OneLine.D) System.out.println(" C <"+c+"> "+contains+" "+new File(absPath).getName()); if(contains) return true;}} catch(IOException e) { e.printStackTrace();} return false;} Spiska getLines(String ignoreList) { Spiska list=new Spiska(); int idx; do { idx=ignoreList.lastIndexOf('\n'); if(idx >= 0) { String cur=ignoreList.substring(idx+1); list.append(cur); ignoreList=ignoreList.substring(0,idx);}} while(idx >= 0); if(ignoreList.length() > 0) list.append(ignoreList); if(OneLine.D) System.out.println(" D "+new Data().dump(list)); return list;}} class Props { final static Color bgTopColor=Color.green; final static Color bgDownColor=Color.lightGray; final static Color bgAlertColor=Color.yellow;}