package mydev.about; import java.util.Date; import java.util.Enumeration; import java.util.Vector; public class Conture2D extends FillFullable implements Decompose { protected Vector points=new Vector(); protected Point2D lastRnd; protected int cv; protected int d1; protected int colorPicture; public Conture2D() { this(ColorsArray.blackColorInt);} public Conture2D(int color) { this(color,false,ColorsArray.lightGrayPoint.ic());} public Conture2D(int color,int colorPicture) { this(color,false,colorPicture);} public Conture2D(int colorConture,boolean solid,int colorPicture) { super(colorConture,solid); this.lastRnd=null; this.cv=0; this.d1=(int) new Date().getTime(); this.colorPicture=colorPicture;} Point2D nextPoint() { if(cv >= points.size()) cv=0; return(Point2D) points.elementAt(cv++);} public Point2D nextRandomPoint() { Point2D sec=null; int commonly=0; Point2D a=null; Point2D b; Point2D first=null; Enumeration elements=points.elements(); while(elements.hasMoreElements()) { if(first==null) { first=(Point2D) elements.nextElement(); a=first;} else { b=(Point2D) elements.nextElement(); commonly+=a.distance(b); a=b;}} b=first; if(a !=null && b !=null) commonly+=a.distance(b); int d=d1; d-=d / commonly * commonly; a=nextPoint(); while(d > 0) { b=nextPoint(); double superKub=a.distance(b); if(d < superKub) { sec=Line2D.createLine(a,b,d).getP2(); d1+=d; d=0;} else d-=superKub; a=b;} return sec;} public Conture2D addPoint(Point2D pt) { points.addElement(pt); return this;} public void represent(Matrix2D matrix) { matrix.addPath(this);} public void toPen(Canvas ics,Paint pn) { if(full) toPenSolid(ics,pn); if(color !=colorPicture) toPenConture(ics,pn); else if(!full) toPenConture(ics,pn);} public void toPenSolid(Canvas ics,Paint pn) { int xx[]=new int[points.size()]; int yy[]=new int[xx.length]; Point2D b; int i=0; Enumeration elements=points.elements(); while(elements.hasMoreElements()) { b=(Point2D) elements.nextElement(); xx[i]=b.getX(); yy[i++]=b.getY();} pn.setColor(colorPicture); ics.fillConture(xx,yy,pn);} public void toPenConture(Canvas ics,Paint pn) { Point2D a=null; Point2D b; Point2D first=null; Enumeration elements=points.elements(); while(elements.hasMoreElements()) { if(first==null) { first=(Point2D) elements.nextElement(); a=first;} else { b=(Point2D) elements.nextElement(); ics.drawLine(a.getX(),a.getY(),b.getX(),b.getY(),pn); a=b;}} b=first; if(a !=null && b !=null) ics.drawLine(a.getX(),a.getY(),b.getX(),b.getY(),pn);} public String toString() { return "Conture2D [points="+points+", lastRnd="+lastRnd+", cv="+cv+", d1="+d1+"]";} public Enumeration ribsIterator() { Vector result=new Vector(); Point2D a=null; Point2D b; Point2D first=null; Enumeration elements=points.elements(); while(elements.hasMoreElements()) { if(first==null) { first=(Point2D) elements.nextElement(); a=first;} else { b=(Point2D) elements.nextElement(); result.addElement(new Line2D(a,b)); a=b;}} b=first; if(a !=null && b !=null) result.addElement(new Line2D(a,b)); return result.elements();} public Enumeration verticesIterator() { return points.elements();} public Enumeration planesIterator() { Vector planes=new Vector(); return planes.elements();} public double perim() { double result=0f; Enumeration ribs=ribsIterator(); while(ribs.hasMoreElements()) { Line2D element=(Line2D) ribs.nextElement(); result+=element.length();} return result;} public Enumeration trianglesIterator(Point2D mainVertex) { Vector result=new Vector(); Enumeration ribs=ribsIterator(); while(ribs.hasMoreElements()) { Line2D element=(Line2D) ribs.nextElement(); Triangle2D triangle=new Triangle2D(mainVertex,element); result.addElement(triangle);} return result.elements();}}