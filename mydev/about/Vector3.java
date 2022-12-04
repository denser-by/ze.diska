package mydev.about; public class Vector3 { public final static Vector3 NOL=new Vector3(0,0,0); public final static Vector3 ONE=new Vector3(1,1,1); public final static Vector3 DX1=new Vector3(1,0,0); public final static Vector3 DX1m=new Vector3(-1,0,0); public final static Vector3 DY1=new Vector3(0,1,0); public final static Vector3 DY1m=new Vector3(0,-1,0); public final static Vector3 DZ1=new Vector3(0,0,1); public final static Vector3 DZ1m=new Vector3(0,0,-1); protected int dx; protected int dy; protected int dz; public Vector3(int dx,int dy) { super(); this.dx=dx; this.dy=dy;} public Vector3(float dx,float dy) { this((int)(dx+0.5f),(int)(dy+0.5f));} public Vector3(short dx,short dy) { this((int) dx,(int) dy);} public Vector3(int dx,int dy,int dz) { this(dx,dy); this.dz=dz;} public Vector3(float dx,float dy,float dz) { this((int)(dx+0.5f),(int)(dy+0.5f),(int)(dz+0.5f));} public Vector3(short dx,short dy,short dz) { this((int) dx,(int) dy,(int) dz);} public Vector3(Vector3 v) { this(v.dx,v.dy,v.dz);} public Vector3() { super();} public int getDx() { return dx;} public void setDx(int dx) { this.dx=dx;} public int getDy() { return dy;} public void setDy(int dy) { this.dy=dy;} public int getDz() { return dz;} public void setDz(int dz) { this.dz=dz;} public void setDxy(int dx,int dy) { this.dx=dx; this.dy=dy;} public void setDxz(int dx,int dz) { this.dx=dx; this.dz=dz;} public void setDyz(int dy,int dz) { this.dy=dy; this.dz=dz;} public void setDxy(int dx,int dy,int dz) { this.dx=dx; this.dy=dy; this.dz=dz;} public boolean has() { if(dx !=0) return true; if(dy !=0) return true; if(dz !=0) return true; return false;} public boolean hasX() { if(dx !=0) return true; return false;} public boolean hasY() { if(dy !=0) return true; return false;} public boolean hasZ() { if(dz !=0) return true; return false;} public boolean hasXY() { if(dx !=0) return true; if(dy !=0) return true; return false;} public boolean hasXZ() { if(dx !=0) return true; if(dz !=0) return true; return false;} public boolean hasYZ() { if(dy !=0) return true; if(dz !=0) return true; return false;} public static Vector3 shiftX(int dx) { return new Vector3(dx,0,0);} public static Vector3 shiftXY(int dx,int dy) { return new Vector3(dx,dy,0);} public static Vector3 shiftY(int dy) { return new Vector3(0,dy,0);} public static Vector3 shiftYZ(int dy,int dz) { return new Vector3(0,dy,dz);} public static Vector3 shiftZ(int dz) { return new Vector3(0,0,dz);} public static Vector3 shiftXZ(int dx,int dz) { return new Vector3(dx,0,dz);} public static Vector3 shiftXYZ(int dx,int dy,int dz) { return new Vector3(dx,dy,dz);} public static Vector3 shift(Point2D p1,Point2D p2) { return new Vector3(p1.x-p2.x,p1.y-p2.y);} public static Vector3 shift(Point3D p1,Point3D p2) { return new Vector3(p1.x-p2.x,p1.y-p2.y,p1.z-p2.z);} public Vector3 add(Vector3 v) { if(v !=null) { if(v.hasX()) this.dx+=v.dx; if(v.hasY()) this.dy+=v.dy; if(v.hasZ()) this.dz+=v.dz;} return this;} public Vector3 sub(Vector3 v) { if(v !=null) { if(v.hasX()) this.dx-=v.dx; if(v.hasY()) this.dy-=v.dy; if(v.hasZ()) this.dz-=v.dz;} return this;} public double mult(Vector3 v) { if(v !=null) return(long) dx *(long) v.dx+(long) dy *(long) v.dy+(long) dz *(long) v.dz; return 0f;} public double module() { return Math.sqrt(((long) dx) *((long) dx)+((long) dy) *((long) dy)+((long) dz) *((long) dz));} public Vector3 half() { return shiftXYZ((int)((double) dx / 2f),(int)((double) dy / 2f),(int)((double) dz / 2f));} public Vector3 halfLikeProportion(double proport) { return shiftXYZ((int)((double) dx * proport),(int)((double) dy * proport),(int)((double) dz * proport));} public String toString() { return "Shift [dx="+dx+", dy="+dy+", dz="+dz+"]";} public Vector3 copyVector() { return new Vector3(this);} public Vector3 backVector() { return new Vector3(-dx,-dy,-dz);} public Point2D getShiftedCopy(Point2D originalPoint) { Point2D pointCp=new Point2D(originalPoint); pointCp.shift(this); return pointCp;} public Point3D getShiftedCopy(Point3D originalPoint) { Point3D pointCp=new Point3D(originalPoint); pointCp.shift(this); return pointCp;} public Point2D destination(Point2D originalPoint) { return new Point2D(originalPoint.x+dx,originalPoint.y+dy);} public Point3D destination(Point3D originalPoint) { return new Point3D(originalPoint.x+dx,originalPoint.y+dy,originalPoint.z+dz);} public void update(Vector3 other) { this.dx=other.dx; this.dy=other.dy; this.dz=other.dz;}}