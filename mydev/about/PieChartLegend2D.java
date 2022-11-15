package mydev.about; import java.awt.Font; import java.awt.Scrollbar; import java.util.Enumeration; import java.util.Vector; import mydev.vutils.Metr; public class PieChartLegend2D extends Rectangle2D { protected final static int DEF_SIZE=25-5; protected final static int SPS=5+5-5-1-1; final static boolean D=false; protected int legendItemSide=DEF_SIZE; protected static int space=SPS; private int heightRecordWithSpace=obtainRecordHeightWithSpace(); protected Vector redrawItems=new Vector(); protected ColorsArray colors=new ColorsArray(); private int textColor; private int visibleItemNum; private int curSel; private boolean recounted; protected Scrollbar vScroll; private Font textFont; public PieChartLegend2D(Point2D uc,int width,int height,int color,int textColor,Scrollbar vScroll,boolean solid) { super(uc,width,height,color,solid); this.textColor=textColor; this.vScroll=vScroll;} public PieChartLegend2D(Point2D uc,int width,int height,int color,int textColor,Scrollbar vScroll) { this(uc,width,height,color,textColor,vScroll,true);} public void addRecord(String name,long representableAmount) { int colorIdx=(int) redrawItems.size(); PieChartLegendRecord2D item=new PieChartLegendRecord2D(new Point2D(0,0),legendItemSide,legendItemSide,colors.getColor(colorIdx),colorIdx,textColor,name,representableAmount,textFont); redrawItems.addElement(item); recounted=false; recountVisibleRecords();} public void addRecord(String name,float representableAmount) { int colorIdx=(int) redrawItems.size(); PieChartLegendRecord2D item=new PieChartLegendRecord2D(new Point2D(0,0),legendItemSide,legendItemSide,colors.getColor(colorIdx),colorIdx,textColor,name,representableAmount,textFont); redrawItems.addElement(item); recounted=false; recountVisibleRecords();} public void removeRecord(String name) { Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); if(name.compareTo(cf.getName())==0) { redrawItems.removeElement(cf); return;}} recounted=false; recountVisibleRecords();} public void clearRecords() { redrawItems.removeAllElements(); recounted=false; recountVisibleRecords();} public void reshape(int newWidth,int newHeight) { resize(newWidth,newHeight); Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); cf.resize(newWidth,newHeight);} recountVisibleRecords();} public void recountVisibleRecords() { recounted=false; int drawItemSize=(int) redrawItems.size(); int legendHeight=(height < heightRecordWithSpace ? heightRecordWithSpace * 5 : height)-1-1; int piecesTotalHeight=space+heightRecordWithSpace * drawItemSize; int visibleItemHeight=space; visibleItemNum=0; do { visibleItemNum++; visibleItemHeight+=heightRecordWithSpace;} while(visibleItemHeight < legendHeight); int hiddenItemNum=drawItemSize > visibleItemNum ? drawItemSize-visibleItemNum : 0; if(D) System.out.println(" height visible "+legendHeight+" of "+piecesTotalHeight+"    visible items "+visibleItemNum+" possible height "+visibleItemHeight+" hidden items "+hiddenItemNum+" sel="+curSel); recounted=true;} public void represent(Matrix2D matrix) { if(redrawItems.size() !=0) matrix.addPieChartLegend(this);} public void toPen(Canvas ics,Paint pn) { if(redrawItems.size() !=0) super.toPen(ics,pn); if(!recounted) recountVisibleRecords(); if(redrawItems.size() !=0) redrawLegendsRecords(ics,pn);} public void setPercents(String[] names,float[] percents) { for(int i=0; i < names.length && i < percents.length; i++) setPercent(names[i],percents[i]);} public void setPercent(String name,float pc) { if(name !=null) { Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D item=(PieChartLegendRecord2D) elements.nextElement(); if(name.compareTo(item.getName())==0) { item.setPercent(pc); return;}}}} protected void redrawLegendsRecords(Canvas canvas,Paint pn) { curSel=vScroll !=null ? vScroll.getValue() : 1; int drawItemSize=(int) redrawItems.size(); if(curSel < 0) curSel=0; else if(curSel > drawItemSize) curSel=drawItemSize; int dt=curSel-1; if(dt >-1) { for(int i=0; i < visibleItemNum && dt+i < drawItemSize; i++) { PieChartLegendRecord2D item=(PieChartLegendRecord2D) redrawItems.elementAt(dt+i); item.moveTo(space,space+heightRecordWithSpace * i);} for(int i=0; i < visibleItemNum && dt+i < drawItemSize; i++) { PieChartLegendRecord2D item=(PieChartLegendRecord2D) redrawItems.elementAt(dt+i); item.toPen(canvas,pn.select(item));}} if(vScroll !=null) vScroll.setValues(curSel,visibleItemNum,1,drawItemSize >(5-1-1-1) ? drawItemSize+(5-1-1-1) : drawItemSize);} public void setTextColor(int textColor) { this.textColor=textColor; Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); cf.setTextColor(textColor);}} public void setTextFont(Font textFont) { this.textFont=textFont; Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); cf.setTextFont(textFont);} recounted=false;} public void setScrollVert(Scrollbar vScroll) { this.vScroll=vScroll;} public void setSpace(int space) { PieChartLegend2D.space=space; updateItemsSizes();} private void updateItemsSizes() { this.heightRecordWithSpace=obtainRecordHeightWithSpace(); Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); cf.resize(legendItemSide,legendItemSide);} recounted=false;} public void setLegendItemSide(int legendItemSide) { this.legendItemSide=legendItemSide; updateItemsSizes();} public void setParams(int legendItemSide,int space,int textColor,Font font) { if(legendItemSide > 5) this.legendItemSide=legendItemSide; if(space > 0) PieChartLegend2D.space=space; if(font !=null) this.textFont=font; this.heightRecordWithSpace=obtainRecordHeightWithSpace(); Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); cf.setTextColor(textColor); cf.resize(legendItemSide,legendItemSide); cf.setTextFont(textFont);} recounted=false;} public void setColors(int[] newColors) { if(newColors !=null && newColors.length > 0) { this.colors.setColors(newColors); Enumeration elements=redrawItems.elements(); while(elements.hasMoreElements()) { PieChartLegendRecord2D cf=(PieChartLegendRecord2D) elements.nextElement(); cf.setColor(colors.getColor(cf.getColorIdx()));}}} public int getSpace() { return space;} public int getLegendItemSide() { return legendItemSide;} public int getBackgroundColor() { return getColor();} public void setBackgroundColor(int color) { setColor(color);} public int obtainRecordHeightWithSpace() { return legendItemSide+space;} public void setDisplayMode(boolean displayPc,String measures,char amountDelim) { PieChartLegendRecord2D.getDisplMode().setDisplayModeParams(displayPc,measures,amountDelim);}} class PieChartLegendRecordDisplayMode { private boolean displayPc; private String measures; private char amountDelim; public PieChartLegendRecordDisplayMode(boolean displayPc,String measures,char amountDelim) { super(); setDisplayModeParams(displayPc,measures,amountDelim);} public void setDisplayModeParams(boolean displayPc,String measures,char amountDelim) { this.displayPc=displayPc; this.measures=measures; this.amountDelim=amountDelim;} public PieChartLegendRecordDisplayMode() { super(); setDisplayModeParams(true,"%",'?');} public boolean isDisplayPc() { return displayPc;} public void setDisplayPc(boolean displayPc) { this.displayPc=displayPc;} public String getMeasures() { return measures;} public void setMeasures(String measures) { this.measures=measures;} public char getAmountDelim() { return amountDelim;} public void setAmountDelim(char amountDelim) { this.amountDelim=amountDelim;}} class PieChartLegendRecord2D extends Rectangle2D { private Text2D text; private int textColor; private String name; private long amount; private boolean amountLong; private float amountFloat; private float pc; private int colorIdx; private static PieChartLegendRecordDisplayMode displMode=new PieChartLegendRecordDisplayMode(); public PieChartLegendRecord2D(Point2D uc,int width,int height,int itemColor,int colorIdx,int textColor,String name,long amount,Font font) { this(uc,width,height,itemColor,colorIdx,textColor,name,font); this.amount=amount; this.amountLong=true;} public PieChartLegendRecord2D(Point2D uc,int width,int height,int itemColor,int colorIdx,int textColor,String name,float amount,Font font) { this(uc,width,height,itemColor,colorIdx,textColor,name,font); this.amountFloat=amount;} private PieChartLegendRecord2D(Point2D uc,int width,int height,int itemColor,int colorIdx,int textColor,String name,Font font) { super(uc,width,height,itemColor,true); this.text=new Text2D(new Point2D(uc),name,textColor,font); this.textColor=textColor; this.name=name; this.colorIdx=colorIdx;} public static PieChartLegendRecordDisplayMode getDisplMode() { return displMode;} public void toPenSolid(Canvas ics,Paint pn) { super.toPenSolid(ics,pn); toPenText(ics,pn);} private void toPenText(Canvas ics,Paint pn) { if(displMode.isDisplayPc()) { String pcText=""+pc+displMode.getMeasures(); text.setText(name+" "+pcText);} else { String amountText=amountLong ? new Metr(amount).repr(displMode.getAmountDelim()).toString() : ""+amountFloat; text.setText(name+" "+amountText+" "+displMode.getMeasures());} text.toPen(ics,pn.select(text));} public void toPenConture(Canvas ics,Paint pn) { super.toPenConture(ics,pn); toPenText(ics,pn);} public String getText() { return text.getText();} public int getTextColor() { return textColor;} public void moveTo(int xLeft,int yTop) { uc.moveTo(xLeft,yTop); text.getMiddleHeightLeftPoint().moveTo(xLeft+width+PieChartLegend2D.space,yTop+width / 4);} public void setPercent(float pc) { this.pc=pc;} public void setTextColor(int textColor) { this.textColor=textColor;} public void setTextFont(Font textFont) { this.text.setFont(textFont);} public String getName() { return name;} public int getColorIdx() { return colorIdx;}}