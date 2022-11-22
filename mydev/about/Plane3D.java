package mydev.about; public class Plane3D extends Triangle3D { public Plane3D(Point3D p,Line3D l) { super(p,l);} public Plane3D(Point3D p,Line3D l,int color) { super(p,l,color);} public Plane3D(Point3D p,Line3D l,int color,boolean solid) { super(p,l,color,solid);} public Plane3D(Point3D p1,Point3D p2,Point3D p3) { super(p1,p2,p3);} public Plane3D(Point3D p1,Point3D p2,Point3D p3,int color) { super(p1,p2,p3,color);} public Plane3D(Point3D p1,Point3D p2,Point3D p3,int color,boolean solid) { super(p1,p2,p3,color,solid);} public Plane3D(Point3D p1,Vector3 a1,Vector3 b1) { super(p1,a1,b1);} public Plane3D(Triangle3D t,int color) { super(t,color);} public Plane3D(Plane3D t) { super(t);} public Plane3D(Triangle3D triangle) { super(triangle.p1,triangle.a.single(),triangle.b.single());} public void represent(Matrix2D matrix) {} public void toPenSolid(Canvas ics,Paint pn) {} public void toPenConture(Canvas ics,Paint pn) {} public String toString() { return "Plane("+p1+", "+a+", "+b+")";}}