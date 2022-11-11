package mydev.about; import java.util.Enumeration; import java.util.Vector; public class Triangle2D extends TriangleAbstract implements Reposition,Decompose { protected Point2D p1; public Triangle2D(int x1,int y1,int x2,int y2,int x3,int y3) { this(new Point2D(x1,y1),new Point2D(x2,y2),new Point2D(x3,y3),ColorsArray.blackColorInt);} public Triangle2D(int x1,int y1,int x2,int y2,int x3,int y3,int color) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color);} public Triangle2D(int x1,int y1,int x2,int y2,int x3,int y3,int color,boolean solid) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color,solid);} public Triangle2D(float x1,float y1,float x2,float y2,float x3,float y3) { this(new Point2D(x1,y1),new Point2D(x2,y2),new Point2D(x3,y3),ColorsArray.blackColorInt);} public Triangle2D(float x1,float y1,float x2,float y2,float x3,float y3,int color) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color);} public Triangle2D(float x1,float y1,float x2,float y2,float x3,float y3,int color,boolean solid) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color,solid);} public Triangle2D(double x1,double y1,double x2,double y2,double x3,double y3) { this(new Point2D(x1,y1),new Point2D(x2,y2),new Point2D(x3,y3),ColorsArray.blackColorInt);} public Triangle2D(double x1,double y1,double x2,double y2,double x3,double y3,int color) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color);} public Triangle2D(double x1,double y1,double x2,double y2,double x3,double y3,int color,boolean solid) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color,solid);} public Triangle2D(short x1,short y1,short x2,short y2,short x3,short y3) { this(new Point2D(x1,y1),new Point2D(x2,y2),new Point2D(x3,y3),ColorsArray.blackColorInt);} public Triangle2D(short x1,short y1,short x2,short y2,short x3,short y3,int color) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color);} public Triangle2D(short x1,short y1,short x2,short y2,short x3,short y3,int color,boolean solid) { this(new Point2D(x1,y1,color),new Point2D(x2,y2,color),new Point2D(x3,y3,color),color,solid);} public Triangle2D(Point2D p1,Point2D p2,Point2D p3) { this(p1,p2,p3,Color.middleColor(p1,p2,p3));} public Triangle2D(Point2D p1,Point2D p2,Point2D p3,int color) { this(p1,p2,p3,color,false);} public Triangle2D(Point2D p1,Point2D p2,Point2D p3,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=Vector3.shift(p1,p2); this.b=Vector3.shift(p1,p3);} public Triangle2D(Point2D p,Line2D l) { super(l.color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle2D(Point2D p,Line2D l,int color) { super(color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle2D(Point2D p,Line2D l,int color,boolean solid) { super(color,solid); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(l.p1,p);} public Triangle2D(Triangle2D t) { super(t.color,t.full); this.p1=t.p1; this.a=t.a; this.b=t.b;} public void represent(Matrix2D matrix) { matrix.addTriangle(this);} public void toPenSolid(Canvas ics,Paint pn) { Point2D p2=getP2(); Point2D p3=getP3(); ics.fillTriangle(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),pn);} public void toPenConture(Canvas ics,Paint pn) { Point2D p2=getP2(); Point2D p3=getP3(); ics.drawTriangle(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),pn);} public Conture2D conture() { Conture2D conture=new Conture2D(color); conture.addPoint(getP1()); conture.addPoint(getP2()); conture.addPoint(getP3()); return conture;} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point2D getP1() { return p1;} public Point2D getP2() { return p1.destination(a);} public Point2D getP3() { return p1.destination(b);} public double c() { return a.module();} public double b() { return b.module();} public double a() { return BC().length();} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Point2D C() { return getP3();} public Line2D AB() { return new Line2D(A(),B());} public Line2D BC() { return new Line2D(B(),C());} public Line2D AC() { return new Line2D(A(),C());} public Line2D minLengthSide() { Line2D sideAB=AB(); Line2D sideBC=BC(); Line2D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab <= bc && ab <= ac) return sideAB; if(bc <= ab && bc <= ac) return sideBC; return sideAC;} public Line2D maxLengthSide() { Line2D sideAB=AB(); Line2D sideBC=BC(); Line2D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab >= bc && ab >= ac) return sideAB; if(bc >= ab && bc >= ac) return sideBC; return sideAC;} public double perim() { return a.module()+b.module()+new Line2D(p1.getShiftCopy(a),p1.getShiftCopy(b)).length();} public double perimHalf() { return perim() / 2f;} public Point2D minAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab < bc && ab < ac) return C(); if(bc < ab && bc < ac) return A(); return B();} public Point2D maxAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab > bc && ab > ac) return C(); if(bc > ab && bc > ac) return A(); return B();} public Line2D median(Point2D ex) { Point2D A=A(); Point2D B=B(); Point2D C=C(); Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); if(ex.equals(A)) return new Line2D(A,BC.midpoint()); else if(ex.equals(B)) return new Line2D(B,AC.midpoint()); else if(ex.equals(C)) return new Line2D(C,AB.midpoint()); return null;} public Line2D bisectr(Point2D ex) { Point2D A=A(); Point2D B=B(); Point2D C=C(); Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double c=AB.length(); double b=AC.length(); double a=BC.length(); if(ex.equals(A)) return new Line2D(ex,BC.midpointProportion(c /(b+c))); else if(ex.equals(B)) return new Line2D(ex,AC.midpointProportion(c /(a+c))); else if(ex.equals(C)) return new Line2D(ex,AB.midpointProportion(b /(a+b))); return null;} public Line2D bisect(Point2D point) { Point2D leftPoint=null; Point2D rightPoint=null; if(point.equals(A())) { leftPoint=B(); rightPoint=C();} else if(point.equals(B())) { leftPoint=A(); rightPoint=C();} else if(point.equals(C())) { leftPoint=A(); rightPoint=B();} if(leftPoint !=null && rightPoint !=null) { Vector3 leftVectSingle=Vector3.shift(point,leftPoint).single(); Vector3 rightVectSingle=Vector3.shift(point,rightPoint).single(); Point2D leftPointDst=leftPoint.getShiftCopy(leftVectSingle); Point2D rightPointDst=rightPoint.getShiftCopy(rightVectSingle); Point2D bisectPoint=leftPointDst.middleDistancePoint(rightPointDst); double da=point.distance(leftPoint); double db=point.distance(rightPoint); double min=da < db ? da : db; Line2D lineA=Line2D.createLine(point,leftPoint,min); Line2D lineB=Line2D.createLine(point,rightPoint,min); Line2D abEqSide=new Line2D(lineA.getP2(),lineB.getP2(),color); return new Line2D(point,abEqSide.midpoint(),color);} return null;} public Line2D bisect2(Point2D p) { Point2D a; Point2D b; if(p.equals(p1)) { a=getP2(); b=getP3();} else if(p.equals(getP2())) { a=getP3(); b=p1;} else { a=p1; b=getP2();} double da=p.distance(a); double db=p.distance(b); double ab=a.distance(b); double xda=da * ab /(da+db); Point2D q=new Line2D(a,b,Color.middleColor(a,b)).findPoint(xda,ab-xda); return new Line2D(p,q,color);} public String toString() { return "Triangle("+p1+", "+a+", "+b+")";} public Enumeration ribsIterator() { Vector ribs=new Vector(); ribs.addElement(AB()); ribs.addElement(BC()); ribs.addElement(AC()); return ribs.elements();} public Enumeration verticesIterator() { Vector verts=new Vector(); verts.addElement(A()); verts.addElement(B()); verts.addElement(C()); return verts.elements();} public Enumeration planesIterator() { Vector planes=new Vector(); return planes.elements();} public boolean isInside(Point2D point) { boolean result=point.belongs(this); return result;} public boolean intersects(Line2D line) { Point2D p1=line.getP1(); Point2D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; else if(AB().intersects(line)) return true; else if(BC().intersects(line)) return true; else if(AC().intersects(line)) return true; return false;} public Line2D heightLine(Point2D point) { Line2D side=null; double c=AB().length(); double b=AC().length(); double a=BC().length(); if(point.equals(A())) { side=BC(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); Point2D heightBasePointA=new Line2D(B(),C()).midpointProportion(basePart / a); return new Line2D(A(),heightBasePointA);} else if(point.equals(B())) { side=AC(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); Point2D heightBasePointB=new Line2D(A(),C()).midpointProportion(basePart / b); return new Line2D(B(),heightBasePointB);} else if(point.equals(C())) { side=AB(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); Point2D heightBasePointC=new Line2D(A(),B()).midpointProportion(basePart / c); return new Line2D(C(),heightBasePointC);} return null;} public double height(Point2D point) { Line2D side=null; double c=AB().length(); double b=AC().length(); double a=BC().length(); if(point.equals(A())) { side=BC(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); return heightA;} else if(point.equals(B())) { side=AC(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); return heightB;} else if(point.equals(C())) { side=AB(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); return heightC;} return 0;} public Point2D midpoint() { double sumX=0f; double sumY=0f; long size=0L; Enumeration elements=verticesIterator(); while(elements.hasMoreElements()) { Point2D curPoint=(Point2D) elements.nextElement(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++;} Point2D avgPoint=new Point2D(sumX / size,sumY / size); return avgPoint;} public double innerCircleRadius() { double r=b() * c() * sinA() / perim(); return r;} public Circle2D innerCircle() { double r=innerCircleRadius(); Line2D bisectrA=bisectr(A()); double firstPart=r / Math.sqrt((1-cosA()) / 2f); Point2D circleCenter=bisectrA.midpointProportion(firstPart / bisectrA.length()); Circle2D inCircle=new Circle2D(circleCenter,r,color); return inCircle;} public double area() { double result=b() * c() * sinA() / 2f; return result;}}