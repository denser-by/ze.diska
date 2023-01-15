package mydev.uix; import java.awt.BorderLayout; import java.awt.Color; import java.awt.LayoutManager; import java.awt.Panel; public abstract class CommonSouthPanel extends Panel implements LoggingVkusnjashki { protected CommonLogPanel logCommonPanel; protected CommonLogo commonLogo; protected CommonSouthPanel() { super(); setBackground(getPanelBgColor()); setLayout(getPanelLayout()); logCommonPanel=obtainLogPanel(); if(logCommonPanel !=null) { add("Center",logCommonPanel); if(!logCommonPanel.isShowProgress()) { ProgressBar progressBarCurrent=logCommonPanel.getProgressBarCurrent(); if(progressBarCurrent !=null) add("North",progressBarCurrent); ProgressBar progressBarTotal=logCommonPanel.getProgressBarTotal(); if(progressBarTotal !=null) add("South",progressBarTotal);}} commonLogo=obtainLogo(); if(commonLogo !=null) add("East",commonLogo);} protected abstract CommonLogo obtainLogo(); protected abstract CommonLogPanel obtainLogPanel(); public Color getPanelBgColor() { return Color.green;} public LayoutManager getPanelLayout() { return new BorderLayout();} public void refreshUpdatable() { if(logCommonPanel !=null) logCommonPanel.refreshUpdatable(); if(commonLogo !=null) commonLogo.refreshUpdatable();} public CommonLogPanel getLogCommonPanel() { return logCommonPanel;} public CommonLogo getCommonLogo() { return commonLogo;} public void addLog(String item) { logCommonPanel.addLog(item);} public void addLogComplete(String item) { logCommonPanel.addLogComplete(item);} public LoggingVkusnjashki getLogging() { return(LoggingVkusnjashki)(logCommonPanel);} public void addLog2(String item) { logCommonPanel.addLog2(item);}}