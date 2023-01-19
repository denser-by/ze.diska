package mydev.about; import java.util.Date; import mydev.vutils.Ester; import mydev.vutils.Join; import mydev.vutils.Karta; import mydev.vutils.Queue; public class PieChart2D extends Circle2D { public final static int MIN_DRAWABLE=1; private int defStartAngle=(int)(System.currentTimeMillis() % 166+new Date().getTime() % 90); protected Karta participants=new Karta(); protected Ester[] participantsRemoved; protected int participantsNum; protected int nextColorIdx; protected ColorsArray colors=new ColorsArray(); protected long totalSumLong; protected double totalSumDouble; private boolean recounted; private Queue redrawItems=new Queue(); private Queue redrawSkipItems=new Queue(); private float skipPcSum; private PieChartLegend2D legend; private float curPosStartAngle; public PieChart2D(Point2D center,int r,int color,boolean solid) { super(center,r,color,solid);} public PieChart2D(Point2D center,int r,int color) { this(center,r,color,true);} public PieChartLegend2D getLegend() { return legend;} public void setLegend(PieChartLegend2D legend) { this.legend=legend;} public void addSector(String sectorName,long representableAmount) { totalSumLong+=representableAmount; float angleDt=0.f; if(totalSumLong !=0L) angleDt=(float) representableAmount /(float) totalSumLong * 360.f; participants.put(sectorName,new PieChartSector2D(center,r,colors.getColor(nextColorIdx),nextColorIdx,defStartAngle,angleDt,true,sectorName,representableAmount)); nextColorIdx+=1; participantsNum++; if(legend !=null) legend.addRecord(sectorName,representableAmount); recounted=false;} public void addSector(String sectorName,int representableAmount) { addSector(sectorName,(long) representableAmount);} public void addSector(String sectorName,short representableAmount) { addSector(sectorName,(int) representableAmount);} public void addSector(String sectorName,byte representableAmount) { addSector(sectorName,(short) representableAmount);} public void addSector(String sectorName,float representableAmount) { totalSumDouble+=representableAmount; float angleDt=0.f; if(totalSumDouble !=0.f) angleDt=(float)(((double) representableAmount / totalSumDouble) * 360.f); participants.put(sectorName,new PieChartSector2D(center,r,colors.getColor(nextColorIdx),nextColorIdx,defStartAngle,angleDt,true,sectorName,representableAmount)); nextColorIdx+=1; participantsNum++; if(legend !=null) legend.addRecord(sectorName,representableAmount); recounted=false;} public void removeSector(String sectorName) { participants.put(sectorName,null); if(participantsRemoved==null) participantsRemoved=new Ester[1]; else { Ester[] temp=new Ester[participantsRemoved.length+1]; for(int i=0; i < participantsRemoved.length; i++) temp[i]=participantsRemoved[i]; participantsRemoved=temp;} participantsRemoved[participantsRemoved.length-1]=new Ester(sectorName); participantsNum--; if(legend !=null) legend.removeRecord(sectorName); recounted=false;} public void clearSectors() { participants=new Karta(); participantsRemoved=null; participantsNum=0; nextColorIdx=0; totalSumLong=0L; totalSumDouble=0.f; redrawItems.clearQueue(); redrawSkipItems.clearQueue(); skipPcSum=0.f; recounted=false; if(legend !=null) legend.clearRecords();} public int getNumberOfSectors() { return participantsNum;} public float getSectorPercent(String sectorName) { PieChartSector2D recItem=(PieChartSector2D) participants.get(sectorName); if(recItem !=null) return recItem.getPc(); return 0.f;} public String[] getSectorsPercents() { String[] percents=new String[participantsNum * 2]; String[] names=participants.keys(); for(int j=0; j < names.length; j++) if(names[j] !=null) { PieChartSector2D recItem=(PieChartSector2D) participants.get(names[j]); if(recItem !=null) { percents[2 * j]=names[j]; percents[2 * j+1]=""+recItem.getPc()+"%";}} return percents;} public String getSectorAmount(String sectorName) { PieChartSector2D recItem=(PieChartSector2D) participants.get(sectorName); if(recItem !=null) return recItem.getAmountResume(); return null;} public short getSectorAngle(String sectorName) { PieChartSector2D recItem=(PieChartSector2D) participants.get(sectorName); if(recItem !=null) return(short) recItem.getAngle(); return 0;} public void updateSectorsPercents() { recounted=false; Queue newItems=new Queue(); Queue newSkipItems=new Queue(); curPosStartAngle=0.f; int busyAngle=0; PieChartSector2D lastItem=null; this.skipPcSum=0.f; String[] names=participants.keys(); if(participantsRemoved !=null && participantsRemoved.length > 0) names=new Ester().convert(new Join().sub(new Ester().convert(names),participantsRemoved)); for(int j=0; j < names.length; j++) if(names[j] !=null) { PieChartSector2D recItem=(PieChartSector2D) participants.get(names[j]); if(recItem !=null) { float pc=recItem.recountPc(totalSumDouble,totalSumLong); float angleDt=pc * 360.f / 100.f; recItem.updatePosition(center,r,curPosStartAngle,angleDt); if(legend !=null) legend.setPercent(recItem.getName(),pc); if(recItem.angleDt > 0) { newItems.enqueueQueueRecord(recItem); lastItem=recItem; curPosStartAngle+=recItem.angleDt; busyAngle+=recItem.angleDt;} else { newSkipItems.enqueueQueueRecord(recItem); skipPcSum+=pc;}}} if(busyAngle < 360) { if(newSkipItems.sizeOfQueue() > 0) redistroBeautyFull(newItems,360-busyAngle,newSkipItems); else if(lastItem !=null) lastItem.incrAngleDt(360-busyAngle);} this.redrawItems=newItems; this.redrawSkipItems=newSkipItems; recounted=true;} private void redistroBeautyFull(Queue newEnlargements,int havings,Queue misses) { while(havings > 0 && misses.sizeOfQueue() > 0) { float availableAverage=(float) havings /(float) misses.sizeOfQueue(); PieChartSector2D recItem=(PieChartSector2D) misses.dequeueQueueRecord(); if(availableAverage < 1.f) { recItem.updatePosition(center,r,curPosStartAngle,MIN_DRAWABLE); havings-=MIN_DRAWABLE;} else { recItem.updatePosition(center,r,curPosStartAngle,MIN_DRAWABLE+MIN_DRAWABLE); havings-=MIN_DRAWABLE; havings-=MIN_DRAWABLE;} newEnlargements.enqueueQueueRecord(recItem); curPosStartAngle+=recItem.angleDt;}} public void reshapeSectors(int newCenterX,int newCenterY,int newR) { getCenter().moveTo(newCenterX,newCenterY); setR(newR); updateSectors(newR);} public void setStartAngle(int startAngle) { this.defStartAngle=startAngle; recounted=false;} public void setColors(int[] newColors) { if(newColors !=null && newColors.length > 0) { this.colors.setColors(newColors); Object[] queueRecordsArray=redrawItems.getQueueRecordsArray(); for(int i=0; i < queueRecordsArray.length; i++) { PieChartSector2D cf=(PieChartSector2D) queueRecordsArray[i]; cf.setColor(colors.getColor(cf.getColorIdx()));} if(legend !=null) legend.setColors(newColors);}} public String[] getSkippedItems() { Object[] queueRecordsArray=redrawSkipItems.getQueueRecordsArray(); String[] skipped=new String[queueRecordsArray.length]; for(int i=0; i < skipped.length; i++) { PieChartSector2D cf=(PieChartSector2D) queueRecordsArray[i]; skipped[i++]=cf.getName();} return skipped;} public float getSkippedItemsSum() { return skipPcSum;} public int getBackgroundColor() { return getColor();} public void setBackgroundColor(int color) { setColor(color);} public void setR(int newR) { super.setR(newR); updateSectors(newR);} public void update(Point2D newCenter,int newR) { if(newCenter !=null) { super.update(newCenter,newR); updateSectors(newR);}} private void updateSectors(int newR) { Point2D curCenter=getCenter(); Object[] queueRecordsArray=redrawItems.getQueueRecordsArray(); for(int i=0; i < queueRecordsArray.length; i++) { Sector2D cf=(Sector2D) queueRecordsArray[i]; cf.update(curCenter,newR);}} public void represent(Matrix2D matrix) { if(participantsNum > 0) matrix.addPieChart(this);} public void toPen(Canvas ics,Paint pn) { if(participantsNum > 0) super.toPen(ics,pn); if(!recounted) updateSectorsPercents(); if(participantsNum > 0) redrawSectors(ics,pn);} private void redrawSectors(Canvas canvas,Paint pn) { Object[] queueRecordsArray=redrawItems.getQueueRecordsArray(); for(int i=0; i < queueRecordsArray.length; i++) { Colorfull cf=(Colorfull) queueRecordsArray[i]; cf.toPen(canvas,pn.select(cf));}}} class PieChartSector2D extends Sector2D { private String name; private long amount; private float amountFloat; private boolean floatAmount; private float pc; private int colorIdx; private PieChartSector2D(Point2D center,int r,int color,int colorIdx,float angleStart,float angleDt,boolean fill,String sectorName) { super(center,r,color,angleStart,angleDt,fill); this.name=sectorName; this.colorIdx=colorIdx;} public PieChartSector2D(Point2D center,int r,int color,int colorIdx,float angleStart,float angleDt,boolean fill,String sectorName,long representableAmount) { this(center,r,color,colorIdx,angleStart,angleDt,fill,sectorName); this.amount=representableAmount; this.floatAmount=false;} public PieChartSector2D(Point2D center,int r,int color,int colorIdx,float angleStart,float angleDt,boolean fill,String sectorName,float representableAmount) { this(center,r,color,colorIdx,angleStart,angleDt,fill,sectorName); this.amountFloat=representableAmount; this.floatAmount=true;} public void updatePosition(Point2D center,int r,float curPos,float angleDt) { update(center,r); this.angleStart=(int)(curPos+0.5f); this.angleDt=(int)(angleDt+0.5f);} public String getName() { return name;} public long getAmount() { return amount;} public String getAmountResume() { return floatAmount ? ""+amountFloat : ""+amount;} public boolean isFloatAmount() { return floatAmount;} public float getAmountFloat() { return amountFloat;} public float getPc() { return pc;} public void setPc(float pc) { this.pc=pc;} public float recountPc(double totalSumDouble,long totalSumLong) { if(floatAmount) { if(totalSumDouble !=0.f) this.pc=(float)(100.f * amountFloat / totalSumDouble);} else { if(totalSumLong !=0L) this.pc=(float) amount /(float) totalSumLong * 100.f;} return pc;} public int getAngleStart() { return angleStart;} public int getAngle() { return angleDt;} public int getColorIdx() { return colorIdx;}}