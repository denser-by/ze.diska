package mydev.uix; import java.awt.BorderLayout; import java.awt.Checkbox; import java.awt.Color; import java.awt.Component; import java.awt.List; import java.awt.Panel; import mydev.vutils.AverageRec; public abstract class CommonDataArrayPanel extends Panel { protected List lsItems; private IgnorePanel ignorePanelCda; private AmountPanel amountPanelCda; private Panel controlsPanelCda; private SizePanel sizePanelCda; private AveragePanel averagePanelCda; public CommonDataArrayPanel() { super(); setLayout(new BorderLayout()); setBackground(getPanelBgColor()); createLayout();} public void setPosition(String pos) { if(amountPanelCda !=null) amountPanelCda.setPos(pos);} public void setAmount(String amount) { if(amountPanelCda !=null) amountPanelCda.setAmount(amount);} public void setItemSize(String itemSize) { if(sizePanelCda !=null) sizePanelCda.setItemSize(itemSize);} public void setSize(String totalSize) { if(sizePanelCda !=null) sizePanelCda.setTotalSize(totalSize);} public Checkbox getCbCancelIgnore() { if(ignorePanelCda !=null) return ignorePanelCda.getCbCancelIgnore(); return null;} public void onCancelIgnore() { if(ignorePanelCda !=null) ignorePanelCda.onCancelIgnore();} public void clearAvgPanel() { if(averagePanelCda !=null) averagePanelCda.clearPanel();} public String getIgnoreList() { if(ignorePanelCda !=null) return ignorePanelCda.getIgnoreList(); return "";} public void updateMinMaxAvg(AverageRec mar) { if(averagePanelCda !=null) averagePanelCda.updateMinMaxAvg(mar);} private void createLayout() { addNorth(); addCenter(); addSouth();} protected void addNorth() { Panel layoutPanel=createPanelWrap(); this.ignorePanelCda=obtainIgnorePanel(); if(ignorePanelCda !=null) { Color bgIgnoreColor=ignorePanelCda.getPanelBgColor(); LabeledPanel ignorePanelWrap=new LabeledPanel("",ignorePanelCda,5-1-1,bgIgnoreColor,bgIgnoreColor,bgIgnoreColor); layoutPanel.add("Center",ignorePanelWrap);} this.amountPanelCda=obtainAmountPanel(); if(amountPanelCda !=null) layoutPanel.add("South",amountPanelCda); if(amountPanelCda !=null) add("North",layoutPanel); else if(ignorePanelCda !=null) add("North",layoutPanel); else System.out.println("Empty nors");} private Panel createPanelWrap() { Panel layoutPanel=new Panel(); layoutPanel.setLayout(new BorderLayout()); layoutPanel.setBackground(getPanelBgColor()); return layoutPanel;} private Panel createPanelColorfullWrap(Component comp) { LabeledPanel ignorePanelWrap=new LabeledPanel("",comp,getWrapPanelWidth(),getWrapPanelBgColor(),getWrapPanelBorderColor(),getWrapPanelTextColor()); return ignorePanelWrap;} protected Color getWrapPanelBgColor() { return Color.gray;} protected Color getWrapPanelBorderColor() { return Color.gray;} protected Color getWrapPanelTextColor() { return Color.black;} protected int getWrapPanelWidth() { return 5-1-1;} protected void addCenter() { this.lsItems=new List(22,false); add("Center",createPanelColorfullWrap(lsItems));} protected void addSouth() { Panel layoutPanel=createPanelWrap(); this.controlsPanelCda=obtainControlsPanel(); if(controlsPanelCda !=null) layoutPanel.add("North",controlsPanelCda); this.sizePanelCda=obtainSizePanel(); if(sizePanelCda !=null) layoutPanel.add("Center",sizePanelCda); this.averagePanelCda=obtainAveragePanel(); if(averagePanelCda !=null) layoutPanel.add("South",averagePanelCda); if(controlsPanelCda !=null) add("South",layoutPanel); else if(sizePanelCda !=null) add("South",layoutPanel); else if(averagePanelCda !=null) add("South",layoutPanel); else System.out.println("Empty sous");} protected abstract Panel obtainControlsPanel(); protected abstract SizePanel obtainSizePanel(); protected AveragePanel obtainAveragePanel() { return null;} protected abstract AmountPanel obtainAmountPanel(); protected IgnorePanel obtainIgnorePanel() { return null;} public Color getPanelBgColor() { return Color.lightGray;} public List getLsItems() { return lsItems;} public void selectItem(int idx) { lsItems.select(idx); if(amountPanelCda !=null && amountPanelCda.getTfPos() !=null) amountPanelCda.setPos(""+(idx+1));} public void selectNothing() { int idxSel=lsItems.getSelectedIndex(); if(idxSel >-1) { if(amountPanelCda !=null && amountPanelCda.getTfPos() !=null) amountPanelCda.setPos(""); if(sizePanelCda !=null && sizePanelCda.getTfItemSize() !=null) sizePanelCda.setItemSize(""); lsItems.deselect(idxSel);}} public String getItemSelected() { String curSel=lsItems.getSelectedItem(); return curSel;} public String getItem(int idx) { return lsItems.getItem(idx);} public int getSelectedIdx() { int idxSel=lsItems.getSelectedIndex(); return idxSel;} public int itemsNum() { return lsItems.countItems();}}