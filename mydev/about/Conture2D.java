package mydev.about; import java.util.Date; import java.util.Enumeration; import java.util.Vector; public class Conture2D extends Colorfull { protected Vector points=new Vector(); protected Point2D lastRnd; protected int cv; protected int d1; public Conture2D() { this(Color.rgb(0,0,0));} public Conture2D(int color) { super(color); this.lastRnd=null; this.cv=0; this.d1=(int) new Date().getTime();} Point2D nextPoint() { if(cv >= points.size()) cv=0; return(Point2D) points.elementAt(cv++);} public Point2D nextRandomPoint() { Point2D sec=null; int commonly=0; Point2D a=null; Point2D b; Point2D first=null; Enumeration elements=points.elements(); while(elements.hasMoreElements()) { if(first==null) { first=(Point2D) elements.nextElement(); a=first;} else { b=(Point2D) elements.nextElement(); commonly+=a.distance(b); a=b;}} b=first; if(a !=null && b !=null) commonly+=a.distance(b); int d=d1; d-=d / commonly * commonly; a=nextPoint(); while(d > 0) { b=nextPoint(); double superSmik=a.distance(b); if(d < superSmik) { sec=Line2D.createLine(a,b,d).getP2(); d1+=d; d=0;} else d-=superSmik; a=b;} return sec;} public Conture2D addPoint(Point2D pt) { points.addElement(pt); return this;} public void represent(Matrix2D matrix) { matrix.addPath(this);} public void toPen(Canvas ics,Paint pn) { Point2D a=null; Point2D b; Point2D first=null; Enumeration elements=points.elements(); while(elements.hasMoreElements()) { if(first==null) { first=(Point2D) elements.nextElement(); a=first;} else { b=(Point2D) elements.nextElement(); ics.drawLine(a.getX(),a.getY(),b.getX(),b.getY(),pn); a=b;}} b=first; if(a !=null && b !=null) ics.drawLine(a.getX(),a.getY(),b.getX(),b.getY(),pn);}}