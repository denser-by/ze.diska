package mydev.uix; import java.awt.BorderLayout; import java.awt.Checkbox; import java.awt.Color; import java.awt.Component; import java.awt.List; import java.awt.Panel; import mydev.vutils.AverageRec; public abstract class CommonDataArrayPanel extends Panel { private IgnorePanel ignorePanelCda; private ListPanel listPanelCda; private Panel controlsPanelCda; private SizePanel sizePanelCda; private AveragePanel averagePanelCda; public CommonDataArrayPanel() { super(); setLayout(new BorderLayout()); setBackground(getPanelBgColor()); createLayout();} public void setPosition(String pos) { if(listPanelCda !=null) listPanelCda.setPosition(pos);} public void setAmount(String amount) { if(listPanelCda !=null) listPanelCda.setAmount(amount);} public void setItemSize(String itemSize) { if(sizePanelCda !=null) sizePanelCda.setItemSize(itemSize);} public void setSize(String totalSize) { if(sizePanelCda !=null) sizePanelCda.setTotalSize(totalSize);} public Checkbox getCbCancelIgnore() { if(ignorePanelCda !=null) return ignorePanelCda.getCbCancelIgnore(); return null;} public void onCancelIgnore() { if(ignorePanelCda !=null) ignorePanelCda.onCancelIgnore();} public void clearAvgPanel() { if(averagePanelCda !=null) averagePanelCda.clearPanel();} public String getIgnoreList() { if(ignorePanelCda !=null) return ignorePanelCda.getIgnoreList(); return "";} public void updateMinMaxAvg(AverageRec mar) { if(averagePanelCda !=null) averagePanelCda.updateMinMaxAvg(mar);} private void createLayout() { addNorth(); addCenter(); addSouth();} protected void addNorth() { Panel layoutPanel=createPanelWrap(); this.ignorePanelCda=obtainIgnorePanel(); if(ignorePanelCda !=null) { Color bgIgnoreColor=ignorePanelCda.getPanelBgColor(); layoutPanel.add("Center",createPanelColorfullWrap(ignorePanelCda,bgIgnoreColor)); add("North",layoutPanel);} else System.out.println("Empty nors");} private Panel createPanelWrap() { Panel layoutPanel=new Panel(); layoutPanel.setLayout(new BorderLayout()); layoutPanel.setBackground(getPanelBgColor()); return layoutPanel;} protected Panel createPanelColorfullWrap(Component comp,Color wrapColor) { LabeledPanel ignorePanelWrap=new LabeledPanel("",comp,getWrapPanelWidth(),wrapColor,wrapColor,wrapColor,true,!true,true,true); return ignorePanelWrap;} protected Color getWrapPanelColor() { return Color.gray;} protected int getWrapPanelWidth() { return LabeledPanel.DEF_WRAP_SIZE;} protected void addCenter() { this.listPanelCda=obtainListPanel(); if(listPanelCda !=null) add("Center",createPanelColorfullWrap(listPanelCda,getWrapPanelColor()));} protected void addSouth() { Panel layoutPanel=createPanelWrap(); this.controlsPanelCda=obtainControlsPanel(); if(controlsPanelCda !=null) layoutPanel.add("North",controlsPanelCda); this.sizePanelCda=obtainSizePanel(); if(sizePanelCda !=null) layoutPanel.add("Center",sizePanelCda); this.averagePanelCda=obtainAveragePanel(); if(averagePanelCda !=null) layoutPanel.add("South",createPanelColorfullWrap(averagePanelCda,getWrapPanelColor())); if(controlsPanelCda !=null) add("South",layoutPanel); else if(sizePanelCda !=null) add("South",layoutPanel); else if(averagePanelCda !=null) add("South",layoutPanel); else System.out.println("Empty sous");} protected abstract Panel obtainControlsPanel(); protected abstract ListPanel obtainListPanel(); protected abstract SizePanel obtainSizePanel(); protected AveragePanel obtainAveragePanel() { return null;} protected IgnorePanel obtainIgnorePanel() { return null;} public Color getPanelBgColor() { return Color.lightGray;} public List getLsItems() { return listPanelCda.getLsItems();} public void selectItem(int idx) { if(listPanelCda !=null) listPanelCda.selectItem(idx);} public void selectNothing() { if(listPanelCda !=null) { int idxSel=listPanelCda.getSelectedIndex(); if(idxSel >-1) { listPanelCda.selectNothing(); if(sizePanelCda !=null && sizePanelCda.getTfItemSize() !=null) sizePanelCda.setItemSize("");}}} public String getItemSelected() { if(listPanelCda !=null) return listPanelCda.getItemSelected(); return null;} public String getItem(int idx) { if(listPanelCda !=null) return listPanelCda.getItem(idx); return null;} public int getSelectedIdx() { if(listPanelCda !=null) return listPanelCda.getSelectedIdx(); return-1;} public int itemsNum() { if(listPanelCda !=null) return listPanelCda.itemsNum(); return 0;}}