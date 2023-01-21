package mydev.about; public class Circle2D extends CircleAbstract implements Reposition { protected Point2D center; public Circle2D(Point2D center,int r) { this(center,r,center.color,false);} public Circle2D(Point2D center,int r,int color) { this(center,r,color,false);} public Circle2D(Point2D center,int r,int color,boolean solid) { super(r,color,solid); this.center=center;} public Circle2D(Point2D center,float r) { this(center,r,center.color,false);} public Circle2D(Point2D center,float r,int color) { this(center,r,color,false);} public Circle2D(Point2D center,float r,int color,boolean solid) { super(r,color,solid); this.center=center;} public Circle2D(Point2D center,double r) { this(center,r,center.color);} public Circle2D(Point2D center,double r,int color) { this(center,r,color,false);} public Circle2D(Point2D center,double r,int color,boolean solid) { super(r,color,solid); this.center=center;} public Circle2D(Point2D center,short r) { this(center,r,center.color);} public Circle2D(Point2D center,short r,int color) { this(center,r,color,false);} public Circle2D(Point2D center,short r,int color,boolean solid) { super(r,color,solid); this.center=center;} public Circle2D(Circle2D c) { this(c.center,c.r,c.color,c.full);} public Circle2D(Circle2D c,int color) { this(c.center,c.r,color,c.full);} public Circle2D(Circle2D c,int color,boolean solid) { this(c.center,c.r,color,solid);} public void represent(Matrix2D matrix) { matrix.addCircle(this);} public void toPenSolid(Canvas ics,Paint pn) { ics.fillCircle(center.getX(),center.getY(),r,pn);} public void toPenConture(Canvas ics,Paint pn) { ics.drawCircle(center.getX(),center.getY(),r,pn);} public void shift(Vector3 shVect) { center.shift(shVect);} public void moveTo(Point2D newLoc) { center.moveTo(newLoc);} public void moveTo(Point3D newLoc) { center.moveTo(newLoc);} public void moveTo(int newX,int newY) { center.moveTo(newX,newY);} public void moveTo(float newX,float newY) { center.moveTo(newX,newY);} public void moveTo(double newX,double newY) { center.moveTo(newX,newY);} public void moveTo(short newX,short newY) { center.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { center.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { center.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { center.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { center.moveTo(newX,newY,newZ);} public Point2D getCenter() { return center;} public void update(Point2D center,int r) { this.center.moveTo(center); this.r=r;} public void update(Point2D center,float r) { this.center.moveTo(center); this.r=(int)(r+0.5f);} public void update(Point2D center,short r) { this.center.moveTo(center); this.r=r;} public String toString() { return "Circle("+center+", "+r+")";} public boolean intersects(Circle2D circle) { if(r+circle.r >= center.distance(circle.center)) return true; return false;} public boolean isInside(Point2D point) { boolean result=point.belongs(this); return result;} public boolean intersects(Line2D line) { Triangle2D tri=new Triangle2D(center,line); double centerHeight=tri.height(center); Point2D p1=line.getP1(); Point2D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; else if(!isInside(p1) && !isInside(p2)) return centerHeight <= r; return false;} public Sector2D sector(int angleStart,int angleDt) { return new Sector2D(this,angleStart,angleDt);} public Sector2D sector(short angleStart,short angleDt) { return new Sector2D(this,angleStart,angleDt);} public Sector2D sector(float angleStart,float angleDt) { return new Sector2D(this,angleStart,angleDt);} public Sector2D sector(double angleStart,double angleDt) { return new Sector2D(this,angleStart,angleDt);} public Triangle2D innerTriangle() { Triangle2D result=innerTriangle(color); return result;} public Triangle2D innerTriangle(int triColor) { Point2D p1=Vector3.shiftY(-r).getShiftedCopy(center); Point2D p2=Vector3.shiftY(r).getShiftedCopy(center); Point2D p3=Vector3.shiftX(r).getShiftedCopy(center); Triangle2D result=new Triangle2D(p1,p2,p3,triColor); return result;} public Square innerSquare() { Square result=innerSquare(color); return result;} public Square innerSquare(int sqColor) { double a=r / Math.sqrt(2); Square result=new Square(new Point2D(center),a,sqColor); return result;} public Square outterSquare() { Square result=outterSquare(color); return result;} public Square outterSquare(int sqColor) { Square result=new Square(new Point2D(center),r,sqColor); return result;} public Conture2D innerConture() { Conture2D result=innerConture(color); return result;} public Conture2D innerConture(int sqColor) { Conture2D result=new Conture2D(sqColor); result.addPoint(Vector3.shiftY(-r).getShiftedCopy(center)).addPoint(Vector3.shiftX(r).getShiftedCopy(center)).addPoint(Vector3.shiftY(r).getShiftedCopy(center)).addPoint(Vector3.shiftX(-r).getShiftedCopy(center)); return result;} public int hashCode() { final int prime=31; int result=super.hashCode(); result=prime * result+((center==null) ? 0 : center.hashCode()); return result;} public boolean equals(Object obj) { if(this==obj) return true; if(!super.equals(obj)) return false; if(getClass() !=obj.getClass()) return false; Circle2D other=(Circle2D) obj; if(center==null) { if(other.center !=null) return false;} else if(!center.equals(other.center)) return false; return true;}}