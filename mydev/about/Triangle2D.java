package mydev.about; public class Triangle2D extends TriangleAbstract implements Reposition,Decompose { protected Point2D p1; public Triangle2D(Point2D p1,Point2D p2,Point2D p3) { this(p1,p2,p3,Color.middleColor(p1,p2,p3));} public Triangle2D(Point2D p1,Point2D p2,Point2D p3,int color) { this(p1,p2,p3,color,false);} public Triangle2D(Point2D p1,Point2D p2,Point2D p3,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=Vector3.shift(p2,p1); this.b=Vector3.shift(p3,p1);} public Triangle2D(Point2D p,Line2D l) { this(p,l,Color.middleColor(p,l));} public Triangle2D(Point2D p,Line2D l,int color) { this(p,l,color,false);} public Triangle2D(Point2D p,Line2D l,int color,boolean solid) { super(color,solid); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(p,l.p1);} public Triangle2D(Point3D p1,Vector3 a1,Vector3 b1) { this(p1,a1,b1,p1.color);} public Triangle2D(Point3D p1,Vector3 a1,Vector3 b1,int color) { this(p1,a1,b1,color,false);} public Triangle2D(Point3D p1,Vector3 a1,Vector3 b1,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=a1; this.b=b1;} public Triangle2D(Triangle2D t) { this(t,t.color);} public Triangle2D(Triangle2D t,int color) { this(t,color,false); this.p1=t.p1; this.a=t.a; this.b=t.b;} public Triangle2D(Triangle2D t,int color,boolean solid) { super(color,solid); this.p1=t.p1; this.a=t.a; this.b=t.b;} public void represent(Matrix2D matrix) { matrix.addTriangle(this);} public void toPenSolid(Canvas ics,Paint pn) { Point2D p2=getP2(); Point2D p3=getP3(); ics.fillTriangle(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),pn);} public void toPenConture(Canvas ics,Paint pn) { Point2D p2=getP2(); Point2D p3=getP3(); ics.drawTriangle(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),pn);} public Conture2D conture() { Conture2D conture=new Conture2D(color); conture.addPoint(getP1()); conture.addPoint(getP2()); conture.addPoint(getP3()); return conture;} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point2D getP1() { return p1;} public Point2D getP2() { return a.destination(p1);} public Point2D getP3() { return b.destination(p1);} public double c() { return a.module();} public double b() { return b.module();} public double a() { return BC().length();} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Point2D C() { return getP3();} public Line2D AB() { return new Line2D(A(),B());} public Line2D BC() { return new Line2D(B(),C());} public Line2D AC() { return new Line2D(A(),C());} public Line2D minLengthSide() { Line2D sideAB=AB(); Line2D sideBC=BC(); Line2D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab <= bc && ab <= ac) return sideAB; if(bc <= ab && bc <= ac) return sideBC; return sideAC;} public Line2D maxLengthSide() { Line2D sideAB=AB(); Line2D sideBC=BC(); Line2D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab >= bc && ab >= ac) return sideAB; if(bc >= ab && bc >= ac) return sideBC; return sideAC;} public double perim() { return a.module()+b.module()+new Line2D(a.getShiftedCopy(p1),b.getShiftedCopy(p1)).length();} public double perimHalf() { return perim() / 2f;} public Point2D minAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab < bc && ab < ac) return C(); if(bc < ab && bc < ac) return A(); return B();} public Point2D maxAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab > bc && ab > ac) return C(); if(bc > ab && bc > ac) return A(); return B();} public Line2D median(Point2D ex) { if(ex.equals(A())) return medianA(); else if(ex.equals(B())) return medianB(); else if(ex.equals(C())) return medianC(); return null;} public Line2D medianA() { Line2D result=new Line2D(A(),BC().midpoint()); return result;} public Line2D medianB() { Line2D result=new Line2D(B(),AC().midpoint()); return result;} public Line2D medianC() { Line2D result=new Line2D(C(),AB().midpoint()); return result;} public Line2D bisectr(Point2D ex) { if(ex.equals(A())) return bisectrA(); else if(ex.equals(B())) return bisectrB(); else if(ex.equals(C())) return bisectrC(); return null;} public Line2D bisectrA() { Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double c=AB.length(); double b=AC.length(); Line2D result=new Line2D(A(),BC.midpointProportion(c /(b+c))); return result;} public Line2D bisectrB() { Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double c=AB.length(); double a=BC.length(); Line2D result=new Line2D(B(),AC.midpointProportion(c /(a+c))); return result;} public Line2D bisectrC() { Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double b=AC.length(); double a=BC.length(); Line2D result=new Line2D(C(),AB.midpointProportion(b /(a+b))); return result;} public Line2D bisect(Point2D point) { Point2D leftPoint=null; Point2D rightPoint=null; if(point.equals(A())) { leftPoint=B(); rightPoint=C();} else if(point.equals(B())) { leftPoint=A(); rightPoint=C();} else if(point.equals(C())) { leftPoint=A(); rightPoint=B();} if(leftPoint !=null && rightPoint !=null) { Vector3 leftVectSingle=Vector3.shift(point,leftPoint).single(); Vector3 rightVectSingle=Vector3.shift(point,rightPoint).single(); Point2D leftPointDst=leftVectSingle.getShiftedCopy(leftPoint); Point2D rightPointDst=rightVectSingle.getShiftedCopy(rightPoint); Point2D bisectPoint=leftPointDst.middleDistancePoint(rightPointDst); double da=point.distance(leftPoint); double db=point.distance(rightPoint); double min=da < db ? da : db; Line2D lineA=Line2D.createLine(point,leftPoint,min); Line2D lineB=Line2D.createLine(point,rightPoint,min); Line2D abEqSide=new Line2D(lineA.getP2(),lineB.getP2(),color); return new Line2D(point,abEqSide.midpoint(),color);} return null;} public Line2D bisect2(Point2D p) { Point2D a; Point2D b; if(p.equals(p1)) { a=getP2(); b=getP3();} else if(p.equals(getP2())) { a=getP3(); b=p1;} else { a=p1; b=getP2();} double da=p.distance(a); double db=p.distance(b); double ab=a.distance(b); double xda=da * ab /(da+db); Point2D q=new Line2D(a,b,Color.middleColor(a,b)).findPoint(xda,ab-xda); return new Line2D(p,q,color);} public String toString() { return "Triangle("+p1+", "+a+", "+b+")";} public Line3D[] ribs() { return null;} public Line2D[] ribsProjections() { Line2D[] result=new Line2D[3]; result[0]=AB(); result[1]=BC(); result[2]=AC(); return result;} public Point3D[] vertices() { return null;} public Point2D[] verticesProjections() { Point2D[] result=new Point2D[3]; result[0]=A(); result[1]=B(); result[2]=C(); return result;} public Triangle3D[] planes() { return null;} public Triangle2D[] planesProjections() { return null;} public boolean isInside(Point2D point) { boolean result=point.belongs(this); return result;} public boolean intersects(Line2D line) { Point2D p1=line.getP1(); Point2D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; else if(AB().intersects(line)) return true; else if(BC().intersects(line)) return true; else if(AC().intersects(line)) return true; return false;} public Line2D heightLine(Point2D point) { if(point.equals(A())) return heightLineA(); else if(point.equals(B())) return heightLineB(); else if(point.equals(C())) return heightLineC(); return null;} public Line2D heightLineA() { Line2D side=BC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); Point2D heightBasePointA=new Line2D(B(),C()).midpointProportion(basePart / a); return new Line2D(A(),heightBasePointA);} public Line2D heightLineB() { Line2D side=AC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); Point2D heightBasePointB=new Line2D(A(),C()).midpointProportion(basePart / b); return new Line2D(B(),heightBasePointB);} public Line2D heightLineC() { Line2D side=AB(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); Point2D heightBasePointC=new Line2D(A(),B()).midpointProportion(basePart / c); return new Line2D(C(),heightBasePointC);} public double height(Point2D point) { if(point.equals(A())) return heightA(); else if(point.equals(B())) return heightB(); else if(point.equals(C())) return heightC(); return 0;} public double heightA() { Line2D side=BC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosB=(a * a+c * c-b * b) / 2f * a * c; double basePart=c * cosB; double heightA=c * Math.sqrt(1-cosB * cosB); return heightA;} public double heightB() { Line2D side=AC(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=c * cosA; double heightB=c * Math.sqrt(1-cosA * cosA); return heightB;} public double heightC() { Line2D side=AB(); double c=AB().length(); double b=AC().length(); double a=BC().length(); double cosA=(b * b+c * c-a * a) / 2f * b * c; double basePart=b * cosA; double heightC=b * Math.sqrt(1-cosA * cosA); return heightC;} public Point2D midpoint() { double sumX=0f; double sumY=0f; long size=0L; Point2D curPoint=A(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++; curPoint=B(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++; curPoint=C(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++; Point2D avgPoint=new Point2D(sumX / size,sumY / size); return avgPoint;} public double innerCircleRadius() { double r=b() * c() * sinA() / perim(); return r;} public Circle2D innerCircle() { double r=innerCircleRadius(); Line2D bisectrA=bisectr(A()); double firstPart=r / Math.sqrt((1-cosA()) / 2f); Point2D circleCenter=bisectrA.midpointProportion(firstPart / bisectrA.length()); Circle2D inCircle=new Circle2D(circleCenter,r,color); return inCircle;} public double area() { double result=b() * c() * sinA() / 2f; return result;} public void update(Point2D p1,Point2D p2,Point2D p3) { this.p1.moveTo(p1); this.a=Vector3.shift(p2,p1); this.b=Vector3.shift(p3,p1);}}