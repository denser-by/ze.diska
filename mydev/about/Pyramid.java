package mydev.about; public class Pyramid extends Triangle3D implements Reposition,Decompose { protected Point3D top; public Pyramid(Triangle3D triangle,Point3D top,int color,boolean solid) { super(triangle,color,solid); this.top=top;} public Pyramid(Triangle3D triangle,Point3D top,int color) { this(triangle,top,color,false);} public Pyramid(Triangle3D triangle,Point3D top) { this(triangle,top,ColorsArray.blackColorInt,false);} public void represent(Matrix2D matrix) {} public void toPen(Canvas ics,Paint pn) { Triangle3D[] planes=planes(); for(int i=0; i < planes.length; i++) planes[i].toPen(ics,pn);} public void shift(Vector3 shVect) {} public void moveTo(Point2D newLoc) {} public void moveTo(Point3D newLoc) {} public void moveTo(int newX,int newY) {} public void moveTo(float newX,float newY) {} public void moveTo(double newX,double newY) {} public void moveTo(short newX,short newY) {} public void moveTo(int newX,int newY,int newZ) {} public void moveTo(float newX,float newY,float newZ) {} public void moveTo(double newX,double newY,double newZ) {} public void moveTo(short newX,short newY,short newZ) {} public Line3D[] ribs() { Line3D[] result=new Line3D[6]; result[0]=AB(); result[1]=BC(); result[2]=AC(); result[3]=new Line3D(top,A(),color); result[4]=new Line3D(top,B(),color); result[5]=new Line3D(top,C(),color); return result;} public Line2D[] ribsProjections() { return null;} public Point3D[] vertices() { Point3D[] result=new Point3D[4]; result[0]=A(); result[1]=B(); result[2]=C(); result[3]=top; return result;} public Point2D[] verticesProjections() { return null;} public Triangle3D[] planes() { Triangle3D[] result=new Triangle3D[4]; result[0]=new Triangle3D(this,color,full); result[1]=new Triangle3D(top,AB(),color,full); result[2]=new Triangle3D(top,BC(),color,full); result[3]=new Triangle3D(top,AC(),color,full); return result;} public Triangle2D[] planesProjections() { return null;}}