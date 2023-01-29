package mydev.about; public class ScanLineDuo extends Colorfull { public static final int DEF_STEP_PERCENT=7; protected ScanLineDrawer scanLine; protected ScanLineDrawer scanLine2; protected boolean lines; public ScanLineDuo(Triangle2D t) { this(t.A(),t.B(),t.C(),t.getColor());} public ScanLineDuo(Triangle2D t,boolean lines) { this(t.A(),t.B(),t.C(),t.getColor(),lines);} public ScanLineDuo(FourCornersConture2D fc) { this(fc.A(),fc.B(),fc.C(),fc.D(),fc.getColor());} public ScanLineDuo(FourCornersConture2D fc,boolean lines) { this(fc.A(),fc.B(),fc.C(),fc.D(),fc.getColor(),lines);} public ScanLineDuo(Point2D p1,Point2D p2,Point2D p3,int color) { this(p1,p2,p3,color,false);} public ScanLineDuo(Point2D p1,Point2D p2,Point2D p3,int color,boolean lines) { super(color); this.lines=lines; this.scanLine=new ScanLineDrawer(color); scanLine.setStepPercent(DEF_STEP_PERCENT); this.scanLine2=new ScanLineDrawer(color); scanLine2.setStepPercent(DEF_STEP_PERCENT); setConturePoints(p1,p2,p3);} public ScanLineDuo(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color) { this(p1,p2,p3,p4,color,false);} public ScanLineDuo(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color,boolean lines) { super(color); this.lines=lines; this.scanLine=new ScanLineDrawer(color); scanLine.setStepPercent(DEF_STEP_PERCENT); this.scanLine2=new ScanLineDrawer(color); scanLine2.setStepPercent(DEF_STEP_PERCENT); setConturePoints(p1,p2,p3,p4);} public void setConturePoints(Point2D p1,Point2D p2,Point2D p3,Point2D p4) { if(p1 !=null && p2 !=null && p3 !=null && p4 !=null) { scanLine.setStartLine(p2,p3); scanLine.setFinishLine(p1,p4); scanLine2.setStartLine(p3,p4); scanLine2.setFinishLine(p2,p1);}} public void setConturePoints(Point2D p1,Point2D p2,Point2D p3) { if(p1 !=null && p2 !=null && p3 !=null) { scanLine.setStartLine(p1,p3); scanLine.setFinishLine(p2,p3); scanLine2.setStartLine(p2,p1); scanLine2.setFinishLine(p3,p1);}} public boolean isLines() { return lines;} public void setLines() { this.lines=true;} public boolean isQuery() { return !lines;} public void setQuery() { this.lines=false;} public void represent(Matrix2D matrix) {} public void toPen(Canvas ics,Paint pn) { if(lines) toPenContureLines(ics,pn); else toPenContureQuery(ics,pn);} private void toPenContureLines(Canvas ics,Paint pn) { scanLine.toPen(ics,pn);} private void toPenContureQuery(Canvas ics,Paint pn) { scanLine.toPen(ics,pn); scanLine2.toPen(ics,pn);} public short getStepPercent() { return scanLine.getStepPercent();} public void setStepPercent(short stepPercent) { scanLine.setStepPercent(stepPercent); scanLine2.setStepPercent(stepPercent);} public Conture2D conture() { Conture2D result=new Conture2D(scanLine); return result;}}