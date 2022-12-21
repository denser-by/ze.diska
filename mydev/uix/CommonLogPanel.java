package mydev.uix; import java.awt.BorderLayout; import java.awt.Color; import java.awt.LayoutManager; import java.awt.Panel; import java.awt.TextArea; import java.util.Date; import mydev.aaa.Sleeper; import mydev.vutils.AverageRec; public class CommonLogPanel extends Panel implements Runnable,LoggingVkusnjashki { protected TextArea taLogging; protected ProgressBar proBarCur; protected ProgressBar proBarTotal; protected Thread worker1; protected LogItem logItem=new LogItem(); protected boolean lunched; protected boolean working=false; private boolean refreshByOtherParties; public CommonLogPanel() { super(); setBackground(getPanelBgColor()); setLayout(getPanelLayout()); add("Center",taLogging=new TextArea(5,20)); taLogging.setEditable(false); proBarCur=getProgressBarCurrent(); if(proBarCur !=null) add("North",proBarCur); proBarTotal=getProgressBarTotal(); if(proBarTotal !=null) add("South",proBarTotal);} public ProgressBar getProgressBarCurrent() { return new ProgressBar(ProgressBar.DEF_HEIGHT,Color.blue,Color.yellow,Color.magenta);} public ProgressBar getProgressBarTotal() { return new ProgressBar(ProgressBar.DEF_HEIGHT,Color.green,Color.white,Color.green);} public Color getPanelBgColor() { return Color.green;} public LayoutManager getPanelLayout() { return new BorderLayout();} public void regTreckableTask(ProgressChecker progCheck) { if(proBarCur !=null) proBarCur.setProgressChecker(progCheck); if(proBarTotal !=null) proBarTotal.appendProgressChecker(progCheck);} public void prognoseTasks(int taskCount) { if(proBarTotal !=null) { short commonProgress=(short)(100 * taskCount); proBarTotal.prognoseTotal(commonProgress);}} public LoggingVkusnjashki getLogging() { return(LoggingVkusnjashki) this;} public void refreshUpdatable() { this.refreshByOtherParties=true; if(lunched) updateLogs(); if(proBarCur !=null) proBarCur.refreshUpdatable(); if(proBarTotal !=null) proBarTotal.refreshUpdatable();} private void updateLogs() { synchronized(logItem) { if(logItem.isNeedUpdate()) { taLogging.setText(logItem.getRes()); logItem.clearUpdateFlag();}}} public void run() { Sleeper sleeper=new Sleeper(); while(working) { updateLogs(); sleeper.sleep(130 * 2);}} public void addLog2(String item) { logItem.addLog2(item); this.lunched=true; this.working=true; if(!refreshByOtherParties && worker1==null) { worker1=new Thread(this); worker1.start();}} public void addLog(String item) { logItem.addLog(item); this.lunched=true; this.working=true; if(!refreshByOtherParties && worker1==null) { worker1=new Thread(this); worker1.start();}} public void addLogComplete(String item) { addLog(item.replace('_',':'));} public void addLogAvg(AverageRec ar) { addLogComplete("Average records{"+ar.getRecordsNum()+"} processing time is "+ar.getAverageTime()+"s also min time is "+ar.getMinTime()+"s and max time is "+ar.getMaxTime()+"s");}} class LogItem implements LoggingVkusnjashki { final static int LOG_LIMIT=(1+1) * 512 * 5 * 512 * 4; private String res=new String(); private boolean needUpdate; public String getRes() { return res;} public boolean isNeedUpdate() { return needUpdate;} public void addLogComplete(String item) { addLog(item.replace('_',':'));} public LoggingVkusnjashki getLogging() { return(LoggingVkusnjashki)(this);} public void addLog(String item) { if(item !=null && item.length() > 0) { synchronized(this) { item=timing(item); if(res.length() > LOG_LIMIT) res=res.substring(0,LOG_LIMIT / 2); res=item+'\n'+res;} needUpdate=true;}} public void addLog2(String item) { if(item !=null && item.length() > 0) { synchronized(this) { item=timing(item); if(res.length() > LOG_LIMIT) res=res.substring(0,LOG_LIMIT / 2); res=item+res;} needUpdate=true;}} private String timing(String item) { Date t1=new Date(); String hh=""+t1.getHours(); String mm=""+t1.getMinutes(); String ss=""+t1.getSeconds(); if(hh.length() < 2) hh="0"+hh; if(mm.length() < 2) mm="0"+mm; if(ss.length() < 2) ss="0"+ss; item=hh+":"+mm+":"+ss+", "+item+"."; return item;} public void clearUpdateFlag() { needUpdate=false;}}