package mydev.about; public class Triangle2D extends TriangleAbstract implements Decompose { protected Point2D p1; public Triangle2D(Point2D p1,Point2D p2,Point2D p3) { this(p1,p2,p3,Color.middleColor(p1,p2,p3));} public Triangle2D(Point2D p1,Point2D p2,Point2D p3,int color) { this(p1,p2,p3,color,false);} public Triangle2D(Point2D p1,Point2D p2,Point2D p3,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=Vector3.shift(p2,p1); this.b=Vector3.shift(p3,p1);} public Triangle2D(Point2D p,Line2D l) { this(p,l,Color.middleColor(p,l));} public Triangle2D(Point2D p,Line2D l,int color) { this(p,l,color,false);} public Triangle2D(Point2D p,Line2D l,int color,boolean solid) { super(color,solid); this.p1=l.p1; this.a=l.vect; this.b=Vector3.shift(p,l.p1);} public Triangle2D(Point3D p1,Vector3 a1,Vector3 b1) { this(p1,a1,b1,p1.color);} public Triangle2D(Point3D p1,Vector3 a1,Vector3 b1,int color) { this(p1,a1,b1,color,false);} public Triangle2D(Point3D p1,Vector3 a1,Vector3 b1,int color,boolean solid) { super(color,solid); this.p1=p1; this.a=a1; this.b=b1;} public Triangle2D(Triangle2D t) { this(t,t.color);} public Triangle2D(Triangle2D t,int color) { this(t,color,false); this.p1=t.p1; this.a=t.a; this.b=t.b;} public Triangle2D(Triangle2D t,int color,boolean solid) { super(color,solid); this.p1=t.p1; this.a=t.a; this.b=t.b;} public void represent(Matrix2D matrix) { matrix.addTriangle(this);} public void toPenSolid(Canvas ics,Paint pn) { Point2D p2=getP2(); Point2D p3=getP3(); ics.fillTriangle(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),pn);} public void toPenConture(Canvas ics,Paint pn) { Point2D p2=getP2(); Point2D p3=getP3(); ics.drawTriangle(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),pn);} public Conture2D conture() { Conture2D conture=new Conture2D(color); conture.addPoint(getP1()); conture.addPoint(getP2()); conture.addPoint(getP3()); return conture;} protected Reposition mainPoint() { return p1;} public Point2D getP1() { return p1;} public Point2D getP2() { return a.destination(p1);} public Point2D getP3() { return b.destination(p1);} public double c() { return a.module();} public double b() { return b.module();} public double a() { return BC().length();} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Point2D C() { return getP3();} public Point2D labelA(int between) { Vector3 backVector=bisectrA().getVect().backVector(); Vector3 reverse=backVector.halfLikeProportion((float) between / backVector.module()); Point2D labelPoint=reverse.getShiftedCopy(A()); return labelPoint;} public Point2D labelB(int between) { Vector3 backVector=bisectrB().getVect().backVector(); Vector3 reverse=backVector.halfLikeProportion((float) between / backVector.module()); Point2D labelPoint=reverse.getShiftedCopy(B()); return labelPoint;} public Point2D labelC(int between) { Vector3 backVector=bisectrC().getVect().backVector(); Vector3 reverse=backVector.halfLikeProportion((float) between / backVector.module()); Point2D labelPoint=reverse.getShiftedCopy(C()); return labelPoint;} public Line2D AB() { return new Line2D(A(),B());} public Line2D BC() { return new Line2D(B(),C());} public Line2D AC() { return new Line2D(A(),C());} public Line2D minLengthSide() { Line2D sideAB=AB(); Line2D sideBC=BC(); Line2D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab <= bc && ab <= ac) return sideAB; if(bc <= ab && bc <= ac) return sideBC; return sideAC;} public Line2D maxLengthSide() { Line2D sideAB=AB(); Line2D sideBC=BC(); Line2D sideAC=AC(); double ab=sideAB.length(); double bc=sideBC.length(); double ac=sideAC.length(); if(ab >= bc && ab >= ac) return sideAB; if(bc >= ab && bc >= ac) return sideBC; return sideAC;} public Point2D minAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab < bc && ab < ac) return C(); if(bc < ab && bc < ac) return A(); return B();} public Point2D maxAngleVertex() { double ab=AB().length(); double bc=BC().length(); double ac=AC().length(); if(ab > bc && ab > ac) return C(); if(bc > ab && bc > ac) return A(); return B();} public Line2D median(Point2D ex) { if(ex.equals(A())) return medianA(); else if(ex.equals(B())) return medianB(); else if(ex.equals(C())) return medianC(); return null;} public Line2D medianA() { Line2D result=new Line2D(A(),BC().midpoint()); return result;} public Line2D medianB() { Line2D result=new Line2D(B(),AC().midpoint()); return result;} public Line2D medianC() { Line2D result=new Line2D(C(),AB().midpoint()); return result;} public Line2D bisectr(Point2D ex) { if(ex.equals(A())) return bisectrA(); else if(ex.equals(B())) return bisectrB(); else if(ex.equals(C())) return bisectrC(); return null;} public Line2D bisectrA() { Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double c=AB.length(); double b=AC.length(); Line2D result=new Line2D(A(),BC.midpointProportion(c /(b+c))); return result;} public Line2D bisectrB() { Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double c=AB.length(); double a=BC.length(); Line2D result=new Line2D(B(),AC.midpointProportion(c /(a+c))); return result;} public Line2D bisectrC() { Line2D AB=AB(); Line2D AC=AC(); Line2D BC=BC(); double b=AC.length(); double a=BC.length(); Line2D result=new Line2D(C(),AB.midpointProportion(b /(a+b))); return result;} public String toString() { return "Triangle("+A()+", "+B()+", "+C()+")";} public Line3D[] ribs() { return null;} public Line2D[] ribsProjections() { Line2D[] result=new Line2D[3]; result[0]=AB(); result[1]=BC(); result[2]=AC(); return result;} public Point3D[] vertices() { return null;} public Point2D[] verticesProjections() { Point2D[] result=new Point2D[3]; result[0]=A(); result[1]=B(); result[2]=C(); return result;} public Triangle3D[] planes() { return null;} public Triangle2D[] planesProjections() { return null;} public boolean isInside(Point2D point) { boolean result=point.belongs(this); return result;} public boolean intersects(Line2D line) { Point2D p1=line.getP1(); Point2D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; else if(AB().intersects(line)) return true; else if(BC().intersects(line)) return true; else if(AC().intersects(line)) return true; return false;} public Line2D heightLine(Point2D point) { if(point.equals(A())) return heightLineA(); else if(point.equals(B())) return heightLineB(); else if(point.equals(C())) return heightLineC(); return null;} public Line2D heightLineA() { Line2D side=BC(); double c=c(); double a=a(); double cosB=cosB(); double basePart=c * cosB; Point2D heightBasePointA=side.midpointProportion(basePart / a); return new Line2D(A(),heightBasePointA);} public Line2D heightLineB() { Line2D side=AC(); double c=c(); double b=b(); double cosA=cosA(); double basePart=c * cosA; Point2D heightBasePointB=side.midpointProportion(basePart / b); return new Line2D(B(),heightBasePointB);} public Line2D heightLineC() { Line2D side=AB(); double c=c(); double b=b(); double cosA=cosA(); double basePart=b * cosA; Point2D heightBasePointC=side.midpointProportion(basePart / c); return new Line2D(C(),heightBasePointC);} public double height(Point2D point) { if(point.equals(A())) return heightA(); else if(point.equals(B())) return heightB(); else if(point.equals(C())) return heightC(); return 0;} public Point2D midpoint() { double sumX=0f; double sumY=0f; long size=0L; Point2D curPoint=A(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++; curPoint=B(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++; curPoint=C(); sumX+=curPoint.getX(); sumY+=curPoint.getY(); size++; Point2D avgPoint=new Point2D(sumX / size,sumY / size); return avgPoint;} public double innerCircleRadius() { double r=b() * c() * sinA() / perim(); return r;} public Circle2D innerCircle() { double r=innerCircleRadius(); Line2D bisectrA=bisectr(A()); double firstPart=r / Math.sqrt((1-cosA()) / 2.f); Point2D circleCenter=bisectrA.midpointProportion(firstPart / bisectrA.length()); Circle2D inCircle=new Circle2D(circleCenter,r,color); return inCircle;} public double outterCircleRadius() { double outR=a() /(2.f * sinA()); return outR;} public Circle2D outterCircle() { double R=outterCircleRadius(); Line2D side=AB(); double sideLength=side.length(); double halfSideLength=sideLength / 2.f; Point2D c=side.midpoint(); double AG=sideLength /(2.f * cosA()); Line2D AC=AC(); Point2D G=AC.midpointProportion(AG / AC.length()); double heightLength=Math.sqrt(R * R-halfSideLength * halfSideLength); Line2D heightSideCenter=Line2D.createLine(c,G,heightLength); Vector3 vectOfull=heightSideCenter.getVect(); Point2D circleCenter=vectOfull.getShiftedCopy(c); Circle2D outCircle=new Circle2D(circleCenter,R,color); return outCircle;} public Line2D midSideHeightAB() { double R=outterCircleRadius(); Line2D side=AB(); double sideLength=side.length(); Point2D c=side.midpoint(); double AG=sideLength /(2.f * cosA()); Line2D AC=AC(); Point2D G=AC.midpointProportion(AG / AC.length()); Line2D heightSideCenter=Line2D.createLine(c,G,R); Vector3 v=heightSideCenter.getVect(); Line2D result=new Line2D(v.getShiftedCopy(c),v.backVector().getShiftedCopy(c)); return result;} public Line2D midSideHeightBC() { double R=outterCircleRadius(); Line2D side=BC(); double sideLength=side.length(); Point2D c=side.midpoint(); double CG=sideLength /(2.f * cosC()); Line2D CA=new Line2D(C(),A()); Point2D G=CA.midpointProportion(CG / CA.length()); Line2D heightSideCenter=Line2D.createLine(c,G,R); Vector3 v=heightSideCenter.getVect(); Line2D result=new Line2D(v.getShiftedCopy(c),v.backVector().getShiftedCopy(c)); return result;} public Line2D midSideHeightAC() { double R=outterCircleRadius(); Line2D side=AC(); double sideLength=side.length(); Point2D c=side.midpoint(); double AG=sideLength /(2.f * cosA()); Line2D AB=AB(); Point2D G=AB.midpointProportion(AG / AB.length()); Line2D heightSideCenter=Line2D.createLine(c,G,R); Vector3 v=heightSideCenter.getVect(); Line2D result=new Line2D(v.getShiftedCopy(c),v.backVector().getShiftedCopy(c)); return result;} public void update(Point2D p1,Point2D p2,Point2D p3) { this.p1.moveTo(p1); this.a=Vector3.shift(p2,p1); this.b=Vector3.shift(p3,p1);} public int hashCode() { final int prime=31; int result=super.hashCode(); result=prime * result+((p1==null) ? 0 : p1.hashCode()); return result;} public boolean equals(Object obj) { if(this==obj) return true; if(!super.equals(obj)) return false; if(getClass() !=obj.getClass()) return false; Triangle2D other=(Triangle2D) obj; if(p1==null) { if(other.p1 !=null) return false;} else if(!p1.equals(other.p1)) return false; return true;}}