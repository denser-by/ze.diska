package mydev.about; import java.util.Enumeration; import java.util.Vector; public class Triangle3D extends FillFullable implements Reposition,Decompose { protected Point3D p1; protected Vector3 a; protected Vector3 b; public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(Point3D p1,Point3D p2,Point3D p3) { this(p1,p2,p3,Color.middleColor(p1,Color.middleColor(p2,p3)));} public Triangle3D(Point3D p1,Point3D p2,Point3D p3,int color) { this(p1,p2,p3,color,false);} public Triangle3D(Point3D p1,Point3D p2,Point3D p3,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=Vector3.shift(p1,p2); this.b=Vector3.shift(p1,p3);} public Triangle3D(Point3D p1,Vector3 a1,Vector3 b1) { super(p1.color); this.p1=p1; this.a=a1; this.b=b1;} public Triangle3D(Point3D p,Line3D l) { super(l.color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle3D(Point3D p,Line3D l,int color) { super(color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle3D(Point3D p,Line3D l,int color,boolean solid) { super(color,solid); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle3D(Triangle3D t) { super(t.color,t.full); this.p1=t.p1; this.a=t.a; this.b=t.b;} public void represent(Matrix2D matrix) { matrix.addTriangle(this);} public void toPenSolid(Canvas ics,Paint pn) { Point3D p2=getP2(); Point3D p3=getP3(); ics.fillTriangle(p1.getX(),p1.getY(),p1.getZ(),p2.getX(),p2.getY(),p2.getZ(),p3.getX(),p3.getY(),p3.getZ(),pn);} public void toPenConture(Canvas ics,Paint pn) { Point3D p2=getP2(); Point3D p3=getP3(); ics.drawTriangle(p1.getX(),p1.getY(),p1.getZ(),p2.getX(),p2.getY(),p2.getZ(),p3.getX(),p3.getY(),p3.getZ(),pn);} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point3D getP1() { return p1;} public Point3D getP2() { return p1.destination(a);} public Point3D getP3() { return p1.destination(b);} public String toString() { return "Triangle("+p1+", "+a+", "+b+")";} public Enumeration ribsIterator() { Vector ribs=new Vector(); ribs.addElement(new Line3D(p1,a)); ribs.addElement(new Line3D(p1,b)); ribs.addElement(new Line3D(p1.destination(a),p1.destination(b))); return ribs.elements();} public Enumeration verticesIterator() { Vector verts=new Vector(); verts.addElement(p1); verts.addElement(p1.destination(a)); verts.addElement(p1.destination(b)); return verts.elements();} public Enumeration planesIterator() { Vector planes=new Vector(); planes.addElement(getPlane()); return planes.elements();} public Plane3D getPlane() { return new Plane3D(p1,a.single(),b.single());}}