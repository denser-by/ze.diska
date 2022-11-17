package mydev.about; public class Triangle3D extends TriangleAbstract implements Reposition,Decompose { protected Point3D p1; public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3) { this(new Point3D(x1,y1,z1),new Point3D(x2,y2,z2),new Point3D(x3,y3,z3),ColorsArray.blackColorInt);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3,int color) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color);} public Triangle3D(short x1,short y1,short z1,short x2,short y2,short z2,short x3,short y3,short z3,int color,boolean solid) { this(new Point3D(x1,y1,z1,color),new Point3D(x2,y2,z2,color),new Point3D(x3,y3,z3,color),color,solid);} public Triangle3D(Point3D p1,Point3D p2,Point3D p3) { this(p1,p2,p3,Color.middleColor(p1,Color.middleColor(p2,p3)));} public Triangle3D(Point3D p1,Point3D p2,Point3D p3,int color) { this(p1,p2,p3,color,false);} public Triangle3D(Point3D p1,Point3D p2,Point3D p3,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=Vector3.shift(p2,p1); this.b=Vector3.shift(p3,p1);} public Triangle3D(Point3D p1,Vector3 a1,Vector3 b1) { super(p1.color); this.p1=p1; this.a=a1; this.b=b1;} public Triangle3D(Point3D p,Line3D l) { super(l.color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(p,l.p1);} public Triangle3D(Point3D p,Line3D l,int color) { super(color); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(p,l.p1);} public Triangle3D(Point3D p,Line3D l,int color,boolean solid) { super(color,solid); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(p,l.p1);} public Triangle3D(Triangle3D t) { super(t.color,t.full); this.p1=t.p1; this.a=t.a; this.b=t.b;} public Triangle3D(Triangle3D t,int color,boolean solid) { super(color,solid); this.p1=t.p1; this.a=t.a; this.b=t.b;} public void represent(Matrix2D matrix) { matrix.addTriangle(this);} public void toPenSolid(Canvas ics,Paint pn) { Point3D p2=getP2(); Point3D p3=getP3(); ics.fillTriangle(p1.getX(),p1.getY(),p1.getZ(),p2.getX(),p2.getY(),p2.getZ(),p3.getX(),p3.getY(),p3.getZ(),pn);} public void toPenConture(Canvas ics,Paint pn) { Point3D p2=getP2(); Point3D p3=getP3(); ics.drawTriangle(p1.getX(),p1.getY(),p1.getZ(),p2.getX(),p2.getY(),p2.getZ(),p3.getX(),p3.getY(),p3.getZ(),pn);} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point3D getP1() { return p1;} public Point3D getP2() { return a.destination(p1);} public Point3D getP3() { return b.destination(p1);} public double c() { return a.module();} public double b() { return b.module();} public double a() { return BC().length();} public Point3D A() { return getP1();} public Point3D B() { return getP2();} public Point3D C() { return getP3();} public Line3D AB() { return new Line3D(A(),B());} public Line3D BC() { return new Line3D(B(),C());} public Line3D AC() { return new Line3D(A(),C());} public Line3D minLengthSide() { Line3D sideAB=AB(); Line3D sideBC=BC(); Line3D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab <= bc && ab <= ac) return sideAB; if(bc <= ab && bc <= ac) return sideBC; return sideAC;} public Line3D maxLengthSide() { Line3D sideAB=AB(); Line3D sideBC=BC(); Line3D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab >= bc && ab >= ac) return sideAB; if(bc >= ab && bc >= ac) return sideBC; return sideAC;} public double perim() { return a.module()+b.module()+new Line3D(a.getShiftedCopy(p1),b.getShiftedCopy(p1)).length();} public double perimHalf() { return perim() / 2f;} public Point3D minAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab < bc && ab < ac) return C(); if(bc < ab && bc < ac) return A(); return B();} public Point3D maxAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab > bc && ab > ac) return C(); if(bc > ab && bc > ac) return A(); return B();} public Line3D median(Point3D ex) { if(ex.equals(A())) return medianA(); else if(ex.equals(B())) return medianB(); else if(ex.equals(C())) return medianC(); return null;} public Line3D medianA() { Line3D result=new Line3D(A(),BC().midpoint()); return result;} public Line3D medianB() { Line3D result=new Line3D(B(),AC().midpoint()); return result;} public Line3D medianC() { Line3D result=new Line3D(C(),AB().midpoint()); return result;} public Line3D bisectr(Point3D ex) { if(ex.equals(A())) return bisectrA(); else if(ex.equals(B())) return bisectrB(); else if(ex.equals(C())) return bisectrC(); return null;} public Line3D bisectrA() { Line3D AB=AB(); Line3D AC=AC(); Line3D BC=BC(); double c=AB.length(); double b=AC.length(); Line3D result=new Line3D(A(),BC.midpointProportion(c /(b+c))); return result;} public Line3D bisectrB() { Line3D AB=AB(); Line3D AC=AC(); Line3D BC=BC(); double c=AB.length(); double a=BC.length(); Line3D result=new Line3D(B(),AC.midpointProportion(c /(a+c))); return result;} public Line3D bisectrC() { Line3D AB=AB(); Line3D AC=AC(); Line3D BC=BC(); double b=AC.length(); double a=BC.length(); Line3D result=new Line3D(C(),AB.midpointProportion(b /(a+b))); return result;} public String toString() { return "Triangle("+p1+", "+a+", "+b+")";} public Line3D[] ribs() { Line3D[] result=new Line3D[3]; result[0]=AB(); result[1]=BC(); result[2]=AC(); return result;} public Line2D[] ribsProjections() { return null;} public Point3D[] vertices() { Point3D[] result=new Point3D[3]; result[0]=A(); result[1]=B(); result[2]=C(); return result;} public Point2D[] verticesProjections() { return null;} public Triangle3D[] planes() { return null;} public Triangle2D[] planesProjections() { return null;} public Plane3D getPlane() { return new Plane3D(p1,a.single(),b.single());} public boolean isInside(Point3D point) { boolean result=point.belongs(this); return result;} public boolean intersects(Line3D line) { Point3D p1=line.getP1(); Point3D p2=line.getP2(); Plane3D plane=new Plane3D(this); if(line.belongs(plane)) { if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; else if(AB().intersects(line)) return true; else if(BC().intersects(line)) return true; else if(AC().intersects(line)) return true;} return false;} public Line3D heightLine(Point3D point) { if(point.equals(A())) return heightLineA(); else if(point.equals(B())) return heightLineB(); else if(point.equals(C())) return heightLineC(); return null;} public Line3D heightLineA() { Line3D side=BC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); Point3D heightBasePointA=new Line3D(B(),C()).midpointProportion(basePart / a); return new Line3D(A(),heightBasePointA);} public Line3D heightLineB() { Line3D side=AC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); Point3D heightBasePointB=new Line3D(A(),C()).midpointProportion(basePart / b); return new Line3D(B(),heightBasePointB);} public Line3D heightLineC() { Line3D side=AB(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); Point3D heightBasePointC=new Line3D(A(),B()).midpointProportion(basePart / c); return new Line3D(C(),heightBasePointC);} public double height(Point3D point) { if(point.equals(A())) return heightA(); else if(point.equals(B())) return heightB(); else if(point.equals(C())) return heightC(); return 0;} public double heightA() { Line3D side=BC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); return heightA;} public double heightB() { Line3D side=AC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); return heightB;} public double heightC() { Line3D side=AB(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); return heightC;} public Point3D midpoint() { double sumX=0f; double sumY=0f; double sumZ=0f; long size=0L; Point3D curPoint=A(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); sumZ+=curPoint.getZ(); size++; curPoint=B(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); sumZ+=curPoint.getZ(); size++; curPoint=C(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); sumZ+=curPoint.getZ(); size++; Point3D avgPoint=new Point3D(sumX / size,sumY / size,sumZ / size); return avgPoint;} public double innerCircleRadius() { double r=b() * c() * sinA() / perim(); return r;} public Circle3D innerCircle() { double r=innerCircleRadius(); Line3D bisectrA=bisectr(A()); double firstPart=r / Math.sqrt((1-cosA()) / 2f); Point3D circleCenter=bisectrA.midpointProportion(firstPart / bisectrA.length()); Circle3D inCircle=new Circle3D(circleCenter,r,new Plane3D(this),color); return inCircle;} public double area() { double result=b() * c() * sinA() / 2f; return result;} public Triangle2D getProjection(Rectangle3D plane3D,FourCornersConture2D screenProj) { Point3D A=A(); Point3D B=B(); Point3D C=C(); Point2D A2=A.getProjection(plane3D,screenProj); Point2D B2=B.getProjection(plane3D,screenProj); Point2D C2=C.getProjection(plane3D,screenProj); Triangle2D result=new Triangle2D(A2,B2,C2); return result;}}