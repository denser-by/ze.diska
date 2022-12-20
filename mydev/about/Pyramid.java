package mydev.about; public class Pyramid extends Triangle3D implements Reposition,Decompose { protected Point3D top; public Pyramid(Triangle3D triangle,Point3D top,int color,boolean solid) { super(triangle,color,solid); this.top=top;} public Pyramid(Triangle3D triangle,Point3D top,int color) { this(triangle,top,color,false);} public Pyramid(Triangle3D triangle,Point3D top) { this(triangle,top,ColorsArray.blackColorInt,false);} public void represent(Matrix2D matrix) { Triangle3D[] planes=planes(); for(int i=0; i < planes.length; i++) planes[i].represent(matrix);} public void toPen(Canvas ics,Paint pn) { Triangle3D[] planes=planes(); for(int i=0; i < planes.length; i++) planes[i].toPen(ics,pn);} public Point3D getTop() { return top;} public void shift(Vector3 shVect) { top.shift(shVect); super.shift(shVect);} public void moveTo(Point2D newLoc) { top.moveTo(newLoc); super.moveTo(newLoc);} public void moveTo(Point3D newLoc) { top.moveTo(newLoc); super.moveTo(newLoc);} public void moveTo(int newX,int newY) { top.moveTo(newX,newY); super.moveTo(newX,newY);} public void moveTo(float newX,float newY) { top.moveTo(newX,newY); super.moveTo(newX,newY);} public void moveTo(double newX,double newY) { top.moveTo(newX,newY); super.moveTo(newX,newY);} public void moveTo(short newX,short newY) { top.moveTo(newX,newY); super.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { top.moveTo(newX,newY,newZ); super.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { top.moveTo(newX,newY,newZ); super.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { top.moveTo(newX,newY,newZ); super.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { top.moveTo(newX,newY,newZ); super.moveTo(newX,newY,newZ);} public Point3D D() { return getTop();} public Line3D DA() { return new Line3D(D(),A());} public Line3D DB() { return new Line3D(D(),B());} public Line3D DC() { return new Line3D(D(),C());} public Triangle3D ABC() { return new Triangle3D(this,color,full);} public Triangle3D DAB() { return new Triangle3D(B(),DA(),color,full);} public Triangle3D DBC() { return new Triangle3D(C(),DB(),color,full);} public Triangle3D DCA() { return new Triangle3D(A(),DC(),color,full);} public Line3D[] ribs() { Line3D[] result=new Line3D[6]; result[0]=AB(); result[1]=BC(); result[2]=AC(); result[3]=DA(); result[4]=DB(); result[5]=DC(); return result;} public Line2D[] ribsProjections() { return null;} public Point3D[] vertices() { Point3D[] result=new Point3D[4]; result[0]=A(); result[1]=B(); result[2]=C(); result[3]=D(); return result;} public Point2D[] verticesProjections() { return null;} public Triangle3D[] planes() { Triangle3D[] result=new Triangle3D[4]; result[0]=ABC(); result[1]=DAB(); result[2]=DBC(); result[3]=DCA(); return result;} public Triangle2D[] planesProjections() { return null;} public boolean isInside(Point3D point) { boolean result=point.belongs(this); return result;} public Line3D heightLine(Point3D point) { if(point.equals(A())) return heightLineA(); else if(point.equals(B())) return heightLineB(); else if(point.equals(C())) return heightLineC(); else if(point.equals(D())) return heightLineD(); return null;} public Line3D heightLineA() { Plane3D plane=new Plane3D(DBC()); return A().distanceLine(plane);} public Line3D heightLineB() { Plane3D plane=new Plane3D(DCA()); return B().distanceLine(plane);} public Line3D heightLineC() { Plane3D plane=new Plane3D(DAB()); return C().distanceLine(plane);} public Line3D heightLineD() { Plane3D plane=new Plane3D(ABC()); return D().distanceLine(plane);} public double height(Point3D point) { if(point.equals(A())) return heightA(); else if(point.equals(B())) return heightB(); else if(point.equals(C())) return heightC(); else if(point.equals(D())) return heightD(); return 0;} public double heightA() { Plane3D plane=new Plane3D(DBC()); double heightA=A().distance(plane); return heightA;} public double heightB() { Plane3D plane=new Plane3D(DCA()); double heightB=B().distance(plane); return heightB;} public double heightC() { Plane3D plane=new Plane3D(DAB()); double heightC=C().distance(plane); return heightC;} public double heightD() { Plane3D plane=new Plane3D(ABC()); double heightD=D().distance(plane); return heightD;} public void update(Point3D p1,Point3D p2,Point3D p3,Point3D top) { super.update(p1,p2,p3); this.top.moveTo(top);} public Point3D midpoint() { Line3D ren=new Line3D(top,super.midpoint()); return ren.midpoint();}}