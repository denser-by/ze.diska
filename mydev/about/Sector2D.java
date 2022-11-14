package mydev.about; public class Sector2D extends Circle2D { protected int angleStart; protected int angleDt; public Sector2D(Point2D center,int r,int angleStart,int angleDt) { this(center,r,center.color,angleStart,angleDt,false);} public Sector2D(Point2D center,int r,int color,int angleStart,int angleDt) { this(center,r,color,angleStart,angleDt,false);} public Sector2D(Point2D center,int r,int color,int angleStart,int angleDt,boolean solid) { super(center,r,color,solid); this.angleStart=angleStart; this.angleDt=angleDt;} public Sector2D(Point2D center,int r,int color,float angleStart,float angleDt,boolean fill) { this(center,r,color,(int)(angleStart+0.5f),(int)(angleDt+0.5f),fill);} public Sector2D(Point2D center,int r,int color,double angleStart,double angleDt,boolean fill) { this(center,r,color,(float) angleStart,(float) angleDt,fill);} public Sector2D(Point2D center,float r,int angleStart,int angleDt) { this(center,(int)(r+0.5f),center.color,angleStart,angleDt);} public Sector2D(Point2D center,float r,float angleStart,float angleDt) { this(center,(int)(r+0.5f),center.color,(int)(angleStart+0.5f),(int)(angleDt+0.5f));} public Sector2D(Point2D center,float r,int color,int angleStart,int angleDt) { this(center,(int)(r+0.5f),color,angleStart,angleDt);} public Sector2D(Point2D center,float r,int color,float angleStart,float angleDt) { this(center,(int)(r+0.5f),color,(int)(angleStart+0.5f),(int)(angleDt+0.5f));} public Sector2D(Point2D center,float r,int color,int angleStart,int angleDt,boolean solid) { this(center,(int)(r+0.5f),color,angleStart,angleDt,solid);} public Sector2D(Point2D center,float r,int color,float angleStart,float angleDt,boolean solid) { this(center,(int)(r+0.5f),color,(int)(angleStart+0.5f),(int)(angleDt+0.5f),solid);} public Sector2D(Point2D center,double r,int angleStart,int angleDt) { this(center,(float) r,center.color,angleStart,angleDt);} public Sector2D(Point2D center,double r,int color,int angleStart,int angleDt) { this(center,(float) r,color,angleStart,angleDt);} public Sector2D(Point2D center,double r,int color,int angleStart,int angleDt,boolean solid) { this(center,(float) r,color,angleStart,angleDt,solid);} public Sector2D(Point2D center,short r,int angleStart,int angleDt) { this(center,(int) r,center.color,angleStart,angleDt);} public Sector2D(Point2D center,short r,int color,int angleStart,int angleDt) { this(center,(int) r,color,angleStart,angleDt);} public Sector2D(Point2D center,short r,int color,int angleStart,int angleDt,boolean solid) { this(center,(int) r,color,angleStart,angleDt,solid);} public Sector2D(Sector2D s) { this(s.center,s.r,s.color,s.angleStart,s.angleDt,s.full);} public Sector2D(Circle2D s,int angleStart,int angleDt) { this(s.center,s.r,s.color,angleStart,angleDt,s.full);} public Sector2D(Circle2D s,float angleStart,float angleDt) { this(s.center,s.r,s.color,angleStart,angleDt,s.full);} public Sector2D(Circle2D s,double angleStart,double angleDt) { this(s.center,s.r,s.color,angleStart,angleDt,s.full);} public void represent(Matrix2D matrix) { if(angleDt !=0) matrix.addSector(this);} public void toPenSolid(Canvas ics,Paint pn) { if(angleDt !=0) ics.fillSector(center.getX(),center.getY(),r,angleStart,angleDt,pn);} public void toPenConture(Canvas ics,Paint pn) { if(angleDt !=0) ics.drawSector(center.getX(),center.getY(),r,angleStart,angleDt,pn);} public int getAngleStart() { return angleStart;} public int getAngleDt() { return angleDt;} public String toString() { return "Sector2D [angleStart="+angleStart+", angleDt="+angleDt+"]";}}