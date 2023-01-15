package mydev.uix; import java.awt.Checkbox; import java.awt.Color; import java.awt.Component; import java.awt.Label; public abstract class HeaderPathPanel extends CommonPathPanel { public static final int THIN_WRAP_SIZE=1+1-1; protected LabeledPanel panelWrap; private Color wrapTextColor; protected HeaderPathPanel(String panelName,boolean withCommonCtrls,boolean withMulty,int minSize,Color bgColor,Color borderColor,Color textColor,Color contentSplitterColor) { this(panelName,withCommonCtrls,withMulty,!false,minSize,bgColor,borderColor,textColor,contentSplitterColor);} protected HeaderPathPanel(String panelName,boolean withCommonCtrls,boolean withMulty,boolean withTimer,int minSize,Color bgColor,Color borderColor,Color textColor,Color contentSplitterColor) { super(withCommonCtrls,withMulty,withTimer); if(contentSplitterColor !=null) this.panelWrap=createPanelColorfullWrap(new LabeledPanel(panelName,this,minSize,bgColor,borderColor,textColor),contentSplitterColor); else this.panelWrap=new LabeledPanel(panelName,this,minSize,bgColor,borderColor,textColor);} protected HeaderPathPanel(String panelName,boolean withCommonCtrls,boolean withMulty,int minSize,Color bgColor,Color borderColor,Color textColor) { this(panelName,withCommonCtrls,withMulty,minSize,bgColor,borderColor,textColor,Props.northContentSplitterColor);} protected HeaderPathPanel(String panelName,boolean withCommonCtrls,boolean withMulty,boolean withTimer,int minSize,Color bgColor,Color borderColor,Color textColor) { this(panelName,withCommonCtrls,withMulty,withTimer,minSize,bgColor,borderColor,textColor,Props.northContentSplitterColor);} protected LabeledPanel createPanelColorfullWrap(Component comp,Color wrapColor) { LabeledPanel ignorePanelWrap=new LabeledPanel("",comp,THIN_WRAP_SIZE,wrapColor,wrapColor,wrapColor,false,false,false,true); return ignorePanelWrap;} public LabeledPanel getWrapPanel() { return panelWrap;} protected Color obtainWrapTextColor() { return Props.northTextColor;} public void setWrapTextColor(Color wrapTextColor) { this.wrapTextColor=wrapTextColor;} public Color getWrapTextColor() { return wrapTextColor;} protected ControlsPanel append(String name,Component labeledCtrl) { ControlsPanel result=super.append(name,labeledCtrl); if(labeledCtrl !=null) { if(wrapTextColor==null) wrapTextColor=obtainWrapTextColor(); if(labeledCtrl instanceof Label || labeledCtrl instanceof Checkbox) labeledCtrl.setForeground(wrapTextColor); else if(labeledCtrl instanceof LabeledEditPair)((LabeledEditPair) labeledCtrl).getLbName().setForeground(wrapTextColor);} return result;}}