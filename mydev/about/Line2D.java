package mydev.about; public class Line2D extends LineAbstract implements Reposition { protected Point2D p1; public Line2D(Point2D p1,Point2D p2) { this(p1,p2,Color.middleColor(p1,p2));} public Line2D(Point2D p1,Point2D p2,int color) { super(color); this.p1=p1; updateVector(p2);} public Line2D(Point2D p1,Vector3 v1) { this(p1,v1,p1.color);} public Line2D(Point2D p1,Vector3 v1,int color) { super(color); this.p1=p1; this.vect=v1;} public Line2D(Line2D l) { this(l,l.color);} public Line2D(Line2D l,int color) { super(l.color); this.p1=l.p1; this.vect=l.vect;} public void represent(Matrix2D matrix) { matrix.addLine(this);} public void toPen(Canvas ics,Paint pn) { ics.drawLine(p1.getX(),p1.getY(),p1.getX()+vect.dx,p1.getY()+vect.dy,pn);} public void shift(Vector3 shVect) { p1.shift(shVect);} public void moveTo(Point2D newLoc) { p1.moveTo(newLoc);} public void moveTo(Point3D newLoc) { p1.moveTo(newLoc);} public void moveTo(int newX,int newY) { p1.moveTo(newX,newY);} public void moveTo(float newX,float newY) { p1.moveTo(newX,newY);} public void moveTo(double newX,double newY) { p1.moveTo(newX,newY);} public void moveTo(short newX,short newY) { p1.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { p1.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { p1.moveTo(newX,newY,newZ);} public Point2D getP1() { return p1;} public Point2D getP2() { return vect.destination(p1);} public Vector3 updateVector(Point2D p2) { this.vect=Vector3.shift(p2,p1); return vect;} public Triangle2D triangle(Point2D point) { return new Triangle2D(point,this,getColor());} public Point2D midpoint() { return vect.half().destination(p1);} public Point2D midpointProportion(double proport) { return vect.halfLikeProportion(proport).destination(p1);} public boolean equals(Object o) { if(o !=null && o instanceof Line2D) { if(p1.equals(((Line2D) o).p1) && getP2().equals(((Line2D) o).getP2())) return true; if(p1.equals(((Line2D) o).getP2()) && getP2().equals(((Line2D) o).p1)) return true;} return false;} public boolean intersects(Line2D line) { boolean result=new Triangle2D(getP1(),line).area()+new Triangle2D(getP2(),line).area()==new Triangle2D(line.getP1(),this).area()+new Triangle2D(line.getP2(),this).area(); return result;} public Point2D findIntersection(Line2D line) { if(intersects(line)) { if(line.length() > 1+1 && length() > 1+1) { Line2D[] AA=line.halves(); Line2D[] BB=halves(); Point2D r1=AA[0].findIntersection(BB[0]); if(r1 !=null) return r1; Point2D r2=AA[0].findIntersection(BB[1]); if(r2 !=null) return r2; Point2D r3=AA[1].findIntersection(BB[0]); if(r3 !=null) return r3; Point2D r4=AA[1].findIntersection(BB[1]); if(r4 !=null) return r4;} else return new Line2D(midpoint(),line.midpoint()).midpoint();} return null;} public Line2D[] halves() { Point2D mp=midpoint(); Line2D first=new Line2D(getP1(),mp); Line2D second=new Line2D(getP2(),mp); Line2D[] result=new Line2D[1+1]; result[0]=first; result[1]=second; return result;} public boolean intersects(Circle2D circle) { return circle.intersects(this);} public Circle2D circle() { Circle2D result=new Circle2D(midpoint(),halfLength(),color); return result;} public String toString() { return "Line("+p1+", "+vect+")";} public static Line2D createLine(Point2D start,Point2D direction,long length) { Line2D result=createLine(start,direction,(double) length); return result;} public static Line2D createLine(Point2D start,Point2D direction,double length) { Line2D patternLine=new Line2D(start,direction); Vector3 ptrnVect=patternLine.getVect(); Vector3 resultVect=ptrnVect.halfLikeProportion(length / ptrnVect.module()); Point2D finish=resultVect.getShiftedCopy(start); Line2D result=new Line2D(start,finish); return result;} public Point2D findPoint(double xda,double xdb) { Point2D a=p1; Point2D b=getP2(); if(xdb !=0f) { double k=xda / xdb; double x3=a.x+k *(b.x-a.x); double y3=a.y+k *(b.y-a.y); return new Point2D((float) x3,(float) y3,color);} return b;} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Line2D AB() { return new Line2D(A(),B());} public Line2D BA() { return new Line2D(B(),A());} public Vector3 lineVector(Point2D startPoint) { if(startPoint.equals(p1)) return getVect(); return getBackVect();} public void update(Point2D p1,Point2D p2) { this.p1.moveTo(p1); this.vect=Vector3.shift(p2,p1);} public Point2D labelA(int between) { Vector3 abVectBack=new Line2D(A(),B()).getVect().backVector(); Vector3 reverse=abVectBack.halfLikeProportion((float) between / abVectBack.module()); Point2D labelPoint=reverse.getShiftedCopy(A()); return labelPoint;} public Point2D labelB(int between) { Vector3 baVectBack=new Line2D(B(),A()).getVect().backVector(); Vector3 reverse=baVectBack.halfLikeProportion((float) between / baVectBack.module()); Point2D labelPoint=reverse.getShiftedCopy(B()); return labelPoint;} public Line2D midSideHeight(int length) { Point2D thirdPoint=null; if(vect.dx==0) thirdPoint=Vector3.shiftX(length).getShiftedCopy(midpoint()); else if(vect.dy==0) thirdPoint=Vector3.shiftY(length).getShiftedCopy(midpoint()); else if(vect.dx==0 && vect.dy==0) return null; else { if(vect.dx > vect.dy) thirdPoint=Vector3.shiftX(A().x).getShiftedCopy(midpoint()); else thirdPoint=Vector3.shiftY(A().y).getShiftedCopy(midpoint());} Triangle2D tri=new Triangle2D(thirdPoint,this,color); Line2D triMidHeight=tri.midSideHeightAB(); Point2D linePoint=triMidHeight.midpoint(); Vector3 heightVect=triMidHeight.getVect().halfLikeProportion((double) length / triMidHeight.getVect().module()); return new Line2D(linePoint,heightVect,color);} public Line2D heightLineA(int length) { Line2D heightLine=midSideHeight(length); Vector3 shiftVect=Vector3.shift(midpoint(),B()); heightLine.shift(shiftVect); return heightLine;} public Line2D heightLineB(int length) { Line2D heightLine=midSideHeight(length); Vector3 shiftVect=Vector3.shift(midpoint(),A()); heightLine.shift(shiftVect); return heightLine;}}