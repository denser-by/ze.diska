package mydev.about; import java.util.Enumeration; import java.util.Vector; public class Triangle3D extends FillFullable implements Reposition,Decompose { protected Point3D p1; protected Vector3 a; protected Vector3 b; public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(Point3D p1,Point3D p2,Point3D p3) { this(p1,p2,p3,Color.middleColor(p1,Color.middleColor(p2,p3)));} public Triangle3D(Point3D p1,Point3D p2,Point3D p3,int color) { this(p1,p2,p3,color,false);} public Triangle3D(Point3D p1,Point3D p2,Point3D p3,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=Vector3.shift(p1,p2); this.b=Vector3.shift(p1,p3);} public Triangle3D(Point3D p1,Vector3 a1,Vector3 b1) { super(p1.color); this.p1=p1; this.a=a1; this.b=b1;} public Triangle3D(Point3D p,Line3D l) { super(l.color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle3D(Point3D p,Line3D l,int color) { super(color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle3D(Point3D p,Line3D l,int color,boolean solid) { super(color,solid); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle3D(Triangle3D t) { super(t.color,t.full); this.p1=t.p1; this.a=t.a; this.b=t.b;} public void represent(Matrix2D matrix) { matrix.addTriangle(this);} public void toPenSolid(Canvas ics,Paint pn) { Point3D p2=getP2(); Point3D p3=getP3(); ics.fillTriangle(p1.getX(),p1.getY(),p1.getZ(),p2.getX(),p2.getY(),p2.getZ(),p3.getX(),p3.getY(),p3.getZ(),pn);} public void toPenConture(Canvas ics,Paint pn) { Point3D p2=getP2(); Point3D p3=getP3(); ics.drawTriangle(p1.getX(),p1.getY(),p1.getZ(),p2.getX(),p2.getY(),p2.getZ(),p3.getX(),p3.getY(),p3.getZ(),pn);} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point3D getP1() { return p1;} public Point3D getP2() { return p1.destination(a);} public Point3D getP3() { return p1.destination(b);} public double c() { return a.module();} public double b() { return b.module();} public double a() { return BC().length();} public Point3D A() { return getP1();} public Point3D B() { return getP2();} public Point3D C() { return getP3();} public Line3D AB() { return new Line3D(A(),B());} public Line3D BC() { return new Line3D(B(),C());} public Line3D AC() { return new Line3D(A(),C());} public Line3D minLengthSide() { Line3D sideAB=AB(); Line3D sideBC=BC(); Line3D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab <= bc && ab <= ac) return sideAB; if(bc <= ab && bc <= ac) return sideBC; return sideAC;} public Line3D maxLengthSide() { Line3D sideAB=AB(); Line3D sideBC=BC(); Line3D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab >= bc && ab >= ac) return sideAB; if(bc >= ab && bc >= ac) return sideBC; return sideAC;} public double perim() { return a.module()+b.module()+new Line3D(p1.getShiftCopy(a),p1.getShiftCopy(b)).length();} public double perimHalf() { return perim() / 2f;} public Point3D minAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab < bc && ab < ac) return C(); if(bc < ab && bc < ac) return A(); return B();} public Point3D maxAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab > bc && ab > ac) return C(); if(bc > ab && bc > ac) return A(); return B();} public Line3D median(Point3D ex) { Line3D oppositeSide; Point3D A=getP1(); Point3D B=getP2(); Point3D C=getP3(); if(ex.equals(A)) { oppositeSide=new Line3D(B,C); return new Line3D(A,oppositeSide.midpoint());} else if(ex.equals(B)) { oppositeSide=new Line3D(C,A); return new Line3D(B,oppositeSide.midpoint());} else if(ex.equals(C)) { oppositeSide=new Line3D(A,B); return new Line3D(C,oppositeSide.midpoint());} return null;} public String toString() { return "Triangle("+p1+", "+a+", "+b+")";} public Enumeration ribsIterator() { Vector ribs=new Vector(); ribs.addElement(AB()); ribs.addElement(BC()); ribs.addElement(AC()); return ribs.elements();} public Enumeration verticesIterator() { Vector verts=new Vector(); verts.addElement(A()); verts.addElement(B()); verts.addElement(C()); return verts.elements();} public Enumeration planesIterator() { Vector planes=new Vector(); planes.addElement(getPlane()); return planes.elements();} public Plane3D getPlane() { return new Plane3D(p1,a.single(),b.single());} public boolean isInside(Point3D point) { Plane3D plane=new Plane3D(this); return point.belongs(plane) && false;} public boolean intersects(Line3D line) { Point3D p1=line.getP1(); Point3D p2=line.getP2(); Plane3D plane=new Plane3D(this); if(p1.belongs(plane) && p2.belongs(plane)) { if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true;} return false;} public Line3D heightLine(Point3D point) { Line3D side=null; double c=AB().length(); double b=AC().length(); double a=BC().length(); if(point.equals(A())) { side=BC(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); Point3D heightBasePointA=new Line3D(B(),C()).midpointProportion(basePart / a); return new Line3D(A(),heightBasePointA);} else if(point.equals(B())) { side=AC(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); Point3D heightBasePointB=new Line3D(A(),C()).midpointProportion(basePart / b); return new Line3D(B(),heightBasePointB);} else if(point.equals(C())) { side=AB(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); Point3D heightBasePointC=new Line3D(A(),B()).midpointProportion(basePart / c); return new Line3D(C(),heightBasePointC);} return null;} public double height(Point3D point) { Line3D side=null; double c=AB().length(); double b=AC().length(); double a=BC().length(); if(point.equals(A())) { side=BC(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); return heightA; } else if(point.equals(B())) { side=AC(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); return heightB; } else if(point.equals(C())) { side=AB(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); return heightC; } return 0;}}