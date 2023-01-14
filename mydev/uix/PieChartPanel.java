package mydev.uix; import java.awt.BorderLayout; import java.awt.Choice; import java.awt.Color; import java.awt.Component; import java.awt.Panel; import java.awt.Scrollbar; import mydev.about.Canvas; import mydev.about.Paint; import mydev.about.PieChart2D; import mydev.about.PieChartLegend2D; import mydev.about.Point2D; import mydev.vutils.Ester; import mydev.vutils.Order; import mydev.vutils.Queue; public class PieChartPanel extends Panel { private Queue recordsCopyQueue=new Queue(); PieChartCanvas pieChartCanvas; PieChartLegendPanel pieChartLegendPanel; private PieChartUnity pieChartUnity; public PieChartPanel() { super(); setBackground(Props.bgChartColor); setLayout(new BorderLayout()); this.pieChartUnity=new PieChartUnity(); add("Center",pieChartCanvas=new PieChartCanvas(pieChartUnity,false)); add("East",pieChartLegendPanel=new PieChartLegendPanel(pieChartUnity));} public void refreshUpdatableLegend() { pieChartLegendPanel.getChartLegendCanvas().refreshUpdatable();} public void refreshUpdatableChart() { pieChartCanvas.updatePercents(); pieChartCanvas.refreshUpdatable();} public void appendItem(String name,PieChartRecord recItem) { String attrType=pieChartLegendPanel.getChTypeGroup().getSelectedItem(); recordsCopyQueue.enqueueQueueRecord(recItem); pieChartCanvas.appendItem(name,recItem,attrType); pieChartCanvas.refreshUpdatable(); pieChartLegendPanel.getChartLegendCanvas().refreshUpdatable();} public void clearItems() { recordsCopyQueue.clearQueue(); clearItemsInternal();} public Scrollbar getPieChartVertScroll() { return pieChartLegendPanel.getVertScroll();} public Choice getPieChartChoiceGroup() { return pieChartLegendPanel.getChTypeGroup();} public Choice getPieChartChoiceDispl() { return pieChartLegendPanel.getChTypeDispl();} public Choice getPieChartChoiceSort() { return pieChartLegendPanel.getChTypeSort();} public void changePieChartType() { clearItemsInternal(); String attrTypeGroup=pieChartLegendPanel.getChTypeGroup().getSelectedItem(); String attrTypeDispl=pieChartLegendPanel.getChTypeDispl().getSelectedItem(); resetDisplayType(attrTypeDispl,attrTypeGroup); String attrTypeSort=pieChartLegendPanel.getChTypeSort().getSelectedItem(); boolean amountSort=attrTypeGroup.equalsIgnoreCase(PieChartRecord.GR_AMOUNT); if(attrTypeSort.equalsIgnoreCase(PieChartRecord.SORT_FIRST)) { for(int i=0; i < recordsCopyQueue.sizeOfQueue(); i++) { PieChartRecord recItem=(PieChartRecord) recordsCopyQueue.getQueueRecord(i); pieChartCanvas.appendItem(recItem.getName(),recItem,attrTypeGroup);}} else if(attrTypeSort.equalsIgnoreCase(PieChartRecord.SORT_ASC)) { ChartRecordSortItem.setSortingArgs(amountSort,true); ChartRecordSortItem[] asc=new ChartRecordSortItem[(int) recordsCopyQueue.sizeOfQueue()]; for(int i=0; i < asc.length; i++) asc[i]=new ChartRecordSortItem((PieChartRecord) recordsCopyQueue.getQueueRecord(i)); new Order().order(asc); for(int i=0; i < asc.length; i++) pieChartCanvas.appendItem(asc[i].getRec().getName(),asc[i].getRec(),attrTypeGroup);} else if(attrTypeSort.equalsIgnoreCase(PieChartRecord.SORT_DESC)) { ChartRecordSortItem.setSortingArgs(amountSort,false); ChartRecordSortItem[] desc=new ChartRecordSortItem[(int) recordsCopyQueue.sizeOfQueue()]; for(int i=0; i < desc.length; i++) desc[i]=new ChartRecordSortItem((PieChartRecord) recordsCopyQueue.getQueueRecord(i)); new Order().order(desc); for(int i=0; i < desc.length; i++) pieChartCanvas.appendItem(desc[i].getRec().getName(),desc[i].getRec(),attrTypeGroup);} pieChartCanvas.refreshUpdatable(); pieChartLegendPanel.getChartLegendCanvas().refreshUpdatable();} private void resetDisplayType(String attrTypeDispl,String attrTypeGroup) { if(attrTypeDispl.equalsIgnoreCase(PieChartRecord.DISPL_PC)) { if(attrTypeGroup.equalsIgnoreCase(PieChartRecord.GR_AMOUNT)) pieChartUnity.getPieChartLegend().setDisplayMode(true,"%",'.'); else if(attrTypeGroup.equalsIgnoreCase(PieChartRecord.GR_SIZE)) pieChartUnity.getPieChartLegend().setDisplayMode(true,"%",'.');} if(attrTypeDispl.equalsIgnoreCase(PieChartRecord.DISPL_NUM)) { if(attrTypeGroup.equalsIgnoreCase(PieChartRecord.GR_AMOUNT)) pieChartUnity.getPieChartLegend().setDisplayMode(false,"item(s)",' '); else if(attrTypeGroup.equalsIgnoreCase(PieChartRecord.GR_SIZE)) pieChartUnity.getPieChartLegend().setDisplayMode(false,"byte(s)",',');}} private void clearItemsInternal() { pieChartCanvas.clearItems();} public void appendItem(String recordName,int countInt,long totalSizeLong) { PieChartRecord recItem=new PieChartRecord(recordName,countInt,totalSizeLong); appendItem(recordName,recItem);} public void nextPieChartType() { int num=getPieChartChoiceGroup().countItems(); int selIdx=getPieChartChoiceGroup().getSelectedIndex(); if(selIdx < num-1) selIdx=selIdx+1; else { selIdx=0; int num2=getPieChartChoiceDispl().countItems(); int selIdx2=getPieChartChoiceDispl().getSelectedIndex(); if(selIdx2 < num2-1) selIdx2=selIdx2+1; else { selIdx2=0; int num3=getPieChartChoiceSort().countItems(); int selIdx3=getPieChartChoiceSort().getSelectedIndex(); if(selIdx3 < num3-1) selIdx3=selIdx3+1; else selIdx3=0; getPieChartChoiceSort().select(selIdx3);} getPieChartChoiceDispl().select(selIdx2);} getPieChartChoiceGroup().select(selIdx); changePieChartType();}} class PieChartLegendPanel extends Panel { LabeledEditPair leGroup; LabeledEditPair leDispl; LabeledEditPair leSort; PieChartLegendCanvas chartLegendCanvas; Scrollbar vScroll; PropertySetPanel psp; public PieChartLegendPanel(PieChartUnity pieChartUnity) { super(); setBackground(Props.bgChartColor); setLayout(new BorderLayout()); this.vScroll=new Scrollbar(Scrollbar.VERTICAL,1,10,1,100); add("Center",chartLegendCanvas=new PieChartLegendCanvas(pieChartUnity,vScroll,false)); add("East",vScroll); psp=new PropertySetPanel(Props.bgChartColor,createPairs()); add("South",createPanelColorfullWrap(psp.cookingLabels(Props.fgChartPanelColor,Props.textChartPanelColor,Props.bgChartPanelColor),Color.white));} protected Panel createPanelColorfullWrap(Component comp,Color wrapColor) { LabeledPanel ignorePanelWrap=new LabeledPanel("",comp,LabeledPanel.DEF_WRAP_SIZE,wrapColor,wrapColor,wrapColor,true,true,false,true); return ignorePanelWrap;} private LabeledEditPair[] createPairs() { leGroup=new LabeledEditPair("Group:",obtainChoiceGroup()); leDispl=new LabeledEditPair("Display type:",obtainChoiceDispl()); leSort=new LabeledEditPair("Sorting by:",obtainChoiceSort()); LabeledEditPair[] pairs=new LabeledEditPair[1+1+1]; pairs[0]=leGroup; pairs[0+1]=leDispl; pairs[0+1+1]=leSort; return pairs;} protected Choice obtainChoiceGroup() { Choice chType=new Choice(); chType.addItem(PieChartRecord.GR_AMOUNT); chType.addItem(PieChartRecord.GR_SIZE); chType.select(0+1); return chType;} protected Choice obtainChoiceDispl() { Choice chType=new Choice(); chType.addItem(PieChartRecord.DISPL_PC); chType.addItem(PieChartRecord.DISPL_NUM); return chType;} protected Choice obtainChoiceSort() { Choice chType=new Choice(); chType.addItem(PieChartRecord.SORT_FIRST); chType.addItem(PieChartRecord.SORT_DESC); chType.addItem(PieChartRecord.SORT_ASC); chType.select(0+1); return chType;} public LabeledEditPair getLeType() { return leGroup;} public Choice getChTypeGroup() { return leGroup.getChoisesCtrl();} public Choice getChTypeDispl() { return leDispl.getChoisesCtrl();} public Choice getChTypeSort() { return leSort.getChoisesCtrl();} public PieChartLegendCanvas getChartLegendCanvas() { return chartLegendCanvas;} public Scrollbar getVertScroll() { return vScroll;}} class ChartRecordSortItem extends Ester { private static boolean amountSort; private static boolean ascend; private PieChartRecord rec; public ChartRecordSortItem(PieChartRecord rec) { super(); this.rec=rec;} public PieChartRecord getRec() { return rec;} public boolean less(Ester es) { ChartRecordSortItem other=(ChartRecordSortItem) es; if(ascend) { if(amountSort) return rec.getAmount() < other.rec.getAmount(); else return rec.getSize() < other.rec.getSize();} if(amountSort) return rec.getAmount() > other.rec.getAmount(); return rec.getSize() > other.rec.getSize();} public static void setSortingArgs(boolean amountSort,boolean ascend) { ChartRecordSortItem.amountSort=amountSort; ChartRecordSortItem.ascend=ascend;}} abstract class CommonPieChartCanvas extends CommonCanvas { protected PieChartUnity pieChartUnity; protected boolean withBorder; public CommonPieChartCanvas(PieChartUnity pieChartUnity,boolean withBorder) { super(); setBackground(Props.bgChartColor); resize(10,10); this.pieChartUnity=pieChartUnity; this.withBorder=withBorder;}} class PieChartCanvas extends CommonPieChartCanvas { public PieChartCanvas(PieChartUnity pieChartUnity,boolean withBorder) { super(pieChartUnity,withBorder);} public void updatePercents() { pieChartUnity.getPieChart().updateSectorsPercents();} public void clearItems() { pieChartUnity.getPieChart().clearSectors();} public void appendItem(String name,PieChartRecord recItem,String typeGroup) { if(typeGroup.equalsIgnoreCase(PieChartRecord.GR_AMOUNT)) pieChartUnity.getPieChart().addSector(name,recItem.getAmount()); else if(typeGroup.equalsIgnoreCase(PieChartRecord.GR_SIZE)) pieChartUnity.getPieChart().addSector(name,recItem.getSize());} protected void drawItems(Canvas ics,Paint pn) { if(pieChartUnity.getPieChart()==null) pieChartUnity.obtainPieChart(sCur.width / 2,sCur.height / 2,(sCur.width < sCur.height ? sCur.width : sCur.height) / 2 / 5 * 4); if(CommonCanvas.notEq(sCur,sPrev)) pieChartUnity.getPieChart().reshapeSectors(sCur.width / 2,sCur.height / 2,(sCur.width < sCur.height ? sCur.width : sCur.height) / 2 / 5 * 4); pieChartUnity.getPieChart().toPen(ics,pn.select(pieChartUnity.getPieChart()));}} class PieChartLegendCanvas extends CommonPieChartCanvas { Scrollbar vScroll; public PieChartLegendCanvas(PieChartUnity pieChartUnity,Scrollbar vScroll,boolean withBorder) { super(pieChartUnity,withBorder); this.vScroll=vScroll;} public void clearItems() {} protected void drawItems(Canvas ics,Paint pn) { if(pieChartUnity.getPieChartLegend()==null) pieChartUnity.obtainPieChartLegend(sCur.width,sCur.height,vScroll); if(CommonCanvas.notEq(sCur,sPrev)) pieChartUnity.getPieChartLegend().reshape(sCur.width,sCur.height); pieChartUnity.getPieChartLegend().toPen(ics,pn.select(pieChartUnity.getPieChartLegend()));}} class PieChartUnity { private PieChart2D pieChart; private PieChartLegend2D pieChartLegend; public PieChartUnity() { super();} public PieChart2D getPieChart() { return pieChart;} public PieChart2D obtainPieChart(int centerX,int centerY,int r) { pieChart=new PieChart2D(new Point2D(centerX,centerY),r,Props.bgChartColor.getRGB(),false); if(pieChart.getLegend()==null && pieChartLegend !=null) pieChart.setLegend(pieChartLegend); return pieChart;} public PieChartLegend2D getPieChartLegend() { return pieChartLegend;} public PieChartLegend2D obtainPieChartLegend(int width,int height,Scrollbar vScroll) { pieChartLegend=new PieChartLegend2D(new Point2D(0,0),width,height,Props.bgChartColor.getRGB(),Props.textPieChartLegendColor,vScroll); if(pieChart !=null && pieChart.getLegend()==null) pieChart.setLegend(pieChartLegend); return pieChartLegend;}} class PieChartRecord { public static final String GR_AMOUNT="by Amount"; public static final String GR_SIZE="by Size"; public static final String DISPL_PC="%"; public static final String DISPL_NUM="numbers"; public static final String SORT_FIRST="first Encounters"; public static final String SORT_DESC="Descend"; public static final String SORT_ASC="Ascend"; private String name; private int amount; private long size; private float pc; public PieChartRecord(String name,int amount,long size) { super(); this.name=name; this.amount=amount; this.size=size;} public String getName() { return name;} public int getAmount() { return amount;} public long getSize() { return size;} public float getPc() { return pc;} public void setPc(float pc) { this.pc=pc;}}