package mydev.about; import java.util.Enumeration; import java.util.Vector; public class Line3D extends Colorfull implements Reposition,Decompose { protected Point3D p1; protected Vector3 vect; public Line3D(int x1,int y1,int z1,int x2,int y2,int z2) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),ColorsArray.blackColorInt);} public Line3D(int x1,int y1,int z1,int x2,int y2,int z2,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),color);} public Line3D(float x1,float y1,float z1,float x2,float y2,float z2) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),ColorsArray.blackColorInt);} public Line3D(float x1,float y1,float z1,float x2,float y2,float z2,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),color);} public Line3D(double x1,double y1,double z1,double x2,double y2,double z2) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),ColorsArray.blackColorInt);} public Line3D(double x1,double y1,double z1,double x2,double y2,double z2,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),color);} public Line3D(short x1,short y1,short z1,short x2,short y2,short z2) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),ColorsArray.blackColorInt);} public Line3D(short x1,short y1,short z1,short x2,short y2,short z2,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),color);} public Line3D(Point3D p1,Point3D p2) { super(Color.middleColor(p1,p2)); this.p1=p1; updateVector(p2);} public Line3D(Point3D p1,Vector3 v1) { super(p1.color); this.p1=p1; this.vect=v1;} public Line3D(Point3D p1,Point3D p2,int color) { super(color); this.p1=p1; updateVector(p2);} public Line3D(Line3D l) { super(l.color); this.p1=l.p1; this.vect=l.vect;} public void represent(Matrix2D matrix) { matrix.addLine(this);} public void toPen(Canvas ics,Paint pn) { Point3D p2tmp=getP2(); ics.drawLine(p1.getX(),p1.getY(),p1.getZ(),p2tmp.getX(),p2tmp.getY(),p2tmp.getZ(),pn);} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point3D getP1() { return p1;} public Point3D getP2() { return p1.destination(vect);} public Vector3 getVect() { return vect;} public Vector3 updateVector(Point2D p2) { this.vect=Vector3.shift(p2,p1); return vect;} public double length() { return vect.module();} public Point3D midpoint() { return p1.destination(vect.half());} public Point3D midpointProportion(double proport) { return p1.destination(vect.halfLikeProportion(proport));} public boolean belongs(Plane3D plane) { boolean result=getP1().belongs(plane) && getP2().belongs(plane); return result; } public boolean intersects(Plane3D plane) { return false;} public Point3D getIntersect(Plane3D plane) { return null;} public boolean intersects(Line3D line) { boolean result=new Triangle3D(getP1(),line).area()+new Triangle3D(getP2(),line).area()==new Triangle3D(line.getP1(),this).area()+new Triangle3D(line.getP2(),this).area(); return result;} public Point3D getIntersect(Line3D plane) { return null;} public boolean intersects(Cube cube) { return false;} public Point3D[] getIntersect(Cube cube) { return null;} public boolean intersects(Sphere sphere) { return sphere.intersects(this);} public Point3D[] getIntersect(Sphere sphere) { return null;} public boolean intersects(Circle3D circle) { return circle.intersects(this);} public Triangle3D triangle(Point3D point) { return new Triangle3D(point,this,getColor());} public boolean equals(Object o) { if(o !=null && o instanceof Line3D) { if(p1.equals(((Line3D) o).p1) && getP2().equals(((Line3D) o).getP2())) return true; if(p1.equals(((Line3D) o).getP2()) && getP2().equals(((Line3D) o).p1)) return true;} return false;} public String toString() { return "Line("+p1+", "+vect+")";} public Enumeration ribsIterator() { Vector ribs=new Vector(); ribs.addElement(this); return ribs.elements();} public Enumeration verticesIterator() { Vector verts=new Vector(); verts.addElement(p1); verts.addElement(p1.destination(vect)); return verts.elements();} public Enumeration planesIterator() { Vector planes=new Vector(); return planes.elements();}}