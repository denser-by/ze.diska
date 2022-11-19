package mydev.about; public class Sphere extends FillFullable implements Reposition,Plane3Scanner { protected Point3D center; protected int r; public Sphere(Point3D center,int r) { this(center,r,center.color,false);} public Sphere(Point3D center,int r,int color) { this(center,r,color,false);} public Sphere(Point3D center,int r,int color,boolean solid) { super(color,solid); this.center=center; this.r=r;} public Sphere(Point3D center,float r) { this(center,(int)(r+0.5f),center.color);} public Sphere(Point3D center,float r,int color) { this(center,(int)(r+0.5f),color);} public Sphere(Point3D center,float r,int color,boolean solid) { this(center,(int)(r+0.5f),color,solid);} public Sphere(Point3D center,double r) { this(center,(float) r,center.color);} public Sphere(Point3D center,double r,int color) { this(center,(float) r,color);} public Sphere(Point3D center,double r,int color,boolean solid) { this(center,(float) r,color,solid);} public Sphere(Point3D center,short r) { this(center,(int) r,center.color);} public Sphere(Point3D center,short r,int color) { this(center,(int) r,color);} public Sphere(Point3D center,short r,int color,boolean solid) { this(center,(int) r,color,solid);} public Sphere(Sphere c) { this(c.center,c.r,c.color,c.full);} public void represent(Matrix2D matrix) { matrix.addSphere(this);} public void toPenSolid(Canvas ics,Paint pn) { ics.fillSphere(center.getX(),center.getY(),center.getZ(),r,pn);} public void toPenConture(Canvas ics,Paint pn) { ics.drawSphere(center.getX(),center.getY(),center.getZ(),r,pn);} public void shift(Vector3 shVect) { center.shift(shVect);} public void moveTo(Point2D newLoc) { center.moveTo(newLoc);} public void moveTo(Point3D newLoc) { center.moveTo(newLoc);} public void moveTo(int newX,int newY) { center.moveTo(newX,newY);} public void moveTo(float newX,float newY) { center.moveTo(newX,newY);} public void moveTo(double newX,double newY) { center.moveTo(newX,newY);} public void moveTo(short newX,short newY) { center.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { center.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { center.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { center.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { center.moveTo(newX,newY,newZ);} public Point3D getCenter() { return center;} public int getR() { return r;} public void setR(int r) { this.r=r;} public void update(Point3D center,int r) { this.center.moveTo(center); this.r=r;} public void update(Point3D center,float r) { this.center.moveTo(center); this.r=(int)(r+0.5f);} public void update(Point3D center,short r) { this.center.moveTo(center); this.r=r;} public Sphere intCube() { return new Cube(center,(float)((double) r / Math.sqrt(1+1+1)),color,full);} public Cube extCube() { return new Cube(center,r,color,full);} public boolean intersects(Plane3D plane) { return false;} public Circle3D getIntersect(Plane3D plane) { return null;} public boolean isInside(Point3D point) { return center.distance(point) <= r;} public boolean intersects(Line3D line) { Point3D p1=line.getP1(); Point3D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; return false;} public double distance(Plane3D plane) { return 0f;} public Line3D distanceLine(Plane3D plane) { return null;} public boolean equals(Object o) { if(o !=null && o instanceof Sphere) if(center.equals(((Sphere) o).center) && r==((Sphere) o).r) return true; return false;} public String toString() { return "Sphere("+center+", "+r+")";} public Point3D getCenter3D() { return Vector3.NOL.getShiftedCopy(center);} public int xMin() { return center.getX()-r;} public int xMax() { return center.getX()+r;} public int yMin() { return center.getY()-r;} public int yMax() { return center.getY()+r;} public int zMin() { return center.getZ()-r;} public int zMax() { return center.getZ()+r;} public Plane3D getPlaneCenterZ() { int middleZ=(zMax()-zMin()) / 2; Plane3D planeZ=getPlaneZ(middleZ); return planeZ;} public Plane3D getPlaneCenterY() { int middleY=(yMax()-yMin()) / 2; Plane3D planeY=getPlaneY(middleY); return planeY;} public Plane3D getPlaneCenterX() { int middleX=(xMax()-xMin()) / 2; Plane3D planeX=getPlaneX(middleX); return planeX;} public Plane3D getPlaneZ(int z) { Point3D pp1=Vector3.shiftX(-r).getShiftedCopy(center); Point3D pp2=Vector3.shiftX(r).getShiftedCopy(center); Point3D pp3=Vector3.shiftY(r).getShiftedCopy(center); return new Plane3D(pp1,pp2,pp3,color);} public Plane3D getPlaneY(int y) { Point3D pp1=Vector3.shiftX(-r).getShiftedCopy(center); Point3D pp2=Vector3.shiftX(r).getShiftedCopy(center); Point3D pp3=Vector3.shiftZ(r).getShiftedCopy(center); return new Plane3D(pp1,pp2,pp3,color);} public Plane3D getPlaneX(int x) { Point3D pp1=Vector3.shiftY(-r).getShiftedCopy(center); Point3D pp2=Vector3.shiftY(r).getShiftedCopy(center); Point3D pp3=Vector3.shiftZ(r).getShiftedCopy(center); return new Plane3D(pp1,pp2,pp3,color);}}