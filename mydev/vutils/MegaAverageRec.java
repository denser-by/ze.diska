package mydev.vutils; import java.util.Enumeration; import java.util.Vector; public class MegaAverageRec extends AverageRec { protected Vector ars; public MegaAverageRec() { super(); this.ars=new Vector();} public void clearItems() { if(ars !=null) { Enumeration elements=ars.elements(); while(elements.hasMoreElements()) { AverageRec ar=(AverageRec) elements.nextElement(); ar.clearItems();}}} public void addItem(AverageRec ar) { if(ar !=null) ars.addElement(ar);} public double getAverageTime() { double avgTime=0.f; int i=0; Enumeration elements=ars.elements(); while(elements.hasMoreElements()) { AverageRec ar=(AverageRec) elements.nextElement(); double at=ar.getAverageTime(); if(at > 0.f) { avgTime *= i; avgTime+=at; avgTime /=(i+1); i++;}} return avgTime;} public long getRecordsNum() { int num=0; Enumeration elements=ars.elements(); while(elements.hasMoreElements()) { AverageRec ar=(AverageRec) elements.nextElement(); num+=ar.getRecordsNum();} return num;} public double getMinTime() { double minTime=Float.MAX_VALUE; Enumeration elements=ars.elements(); while(elements.hasMoreElements()) { AverageRec ar=(AverageRec) elements.nextElement(); double mt=ar.getMinTime(); double at=ar.getAverageTime(); if(at > 0.f) if(mt < minTime) minTime=mt;} return minTime;} public double getMaxTime() { double maxTime=0.f; Enumeration elements=ars.elements(); while(elements.hasMoreElements()) { AverageRec ar=(AverageRec) elements.nextElement(); double mt=ar.getMaxTime(); double at=ar.getAverageTime(); if(at > 0.f) if(mt > maxTime) maxTime=mt;} return maxTime;}}