package mydev.about; public class Parallelepiped extends FillFullable implements Reposition,Plane3Scanner { protected Point3D uc; protected int width; protected int height; protected int depth; private CubeDrawSupport cds; private Rectangle3Changes cc; public Parallelepiped(int width,int height,int depth) { this(new Point3D(0,0,0),width,height,depth,ColorsArray.blackColorInt,false);} public Parallelepiped(Point3D uc,int width,int height,int depth) { this(uc,width,height,depth,uc.color,false);} public Parallelepiped(Point3D uc,int width,int height,int depth,int color) { this(uc,width,height,depth,color,false);} public Parallelepiped(Point3D uc,int width,int height,int depth,int color,boolean solid) { super(color,solid); this.uc=uc; this.width=width; this.height=height; this.depth=depth; init(color);} public Parallelepiped(Point3D uc,float width,float height,float depth) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,false);} public Parallelepiped(Point3D uc,float width,float height,float depth,int color) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,false);} public Parallelepiped(Point3D uc,float width,float height,float depth,int color,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,solid);} public Parallelepiped(Point3D uc,double width,double height,double depth) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,false);} public Parallelepiped(Point3D uc,double width,double height,double depth,int color) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,false);} public Parallelepiped(Point3D uc,double width,double height,double depth,int color,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,solid);} public Parallelepiped(Point3D uc,short width,short height,short depth) { this(uc,(int) width,(int) height,(int) depth,uc.color,false);} public Parallelepiped(Point3D uc,short width,short height,short depth,int color) { this(uc,(int) width,(int) height,(int) depth,color,false);} public Parallelepiped(Point3D uc,short width,short height,short depth,int color,boolean solid) { this(uc,(int) width,(int) height,(int) depth,color,solid);} public Parallelepiped(Parallelepiped r) { this(r.uc,r.width,r.height,r.depth,r.color,r.full);} private void init(int color) { if(cds==null) { cds=new CubeDrawSupport(color); cc=new Rectangle3Changes(uc,width,height,depth);}} public void represent(Matrix2D matrix) { matrix.addRectangle3D(this);} public void toPenSolid(Canvas ics,Paint pn) {} public void toPenConture(Canvas ics,Paint pn) { if(cc.hasChanges(this)==cc.hasChanges(this)) { rectangle3BeyondMeasures((short) uc.getX(),(short) uc.getY(),(short) uc.getZ(),(short) width,(short) height,(short) depth,pn); cc.assasign(uc,width,height,depth);} cds.getLeftCorners().toPen(ics,pn.select(cds.getLeftCorners())); cds.getRightCorners().toPen(ics,pn.select(cds.getRightCorners())); cds.ribLeftRight1.toPen(ics,pn.select(cds.ribLeftRight1)); cds.ribLeftRight2.toPen(ics,pn.select(cds.ribLeftRight2)); cds.ribLeftRight3.toPen(ics,pn.select(cds.ribLeftRight3)); cds.ribLeftRight4.toPen(ics,pn.select(cds.ribLeftRight4));} public void shift(Vector3 shVect) { uc.shift(shVect);} public void moveTo(Point2D newLoc) { uc.moveTo(newLoc);} public void moveTo(Point3D newLoc) { uc.moveTo(newLoc);} public void moveTo(int newX,int newY) { uc.moveTo(newX,newY);} public void moveTo(float newX,float newY) { uc.moveTo(newX,newY);} public void moveTo(double newX,double newY) { uc.moveTo(newX,newY);} public void moveTo(short newX,short newY) { uc.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { uc.moveTo(newX,newY,newZ);} public Point3D getUc() { return uc;} public int getWidth() { return width;} public void setWidth(int width) { this.width=width;} public int getHeight() { return height;} public void setHeight(int height) { this.height=height;} public int getDepth() { return depth;} public void setDepth(int depth) { this.depth=depth;} public void resize(int width,int height) { this.width=width; this.height=height;} public void resize(float width,float height) { this.width=(int)(width+0.5f); this.height=(int)(height+0.5f);} public void resize(int width,int height,int depth) { this.width=width; this.height=height; this.depth=depth;} public void resize(float width,float height,float depth) { this.width=(int)(width+0.5f); this.height=(int)(height+0.5f); this.depth=(int)(depth+0.5f);} public void update(Point3D uc,int width,int height,int depth) { this.uc.moveTo(uc); resize(width,height,depth);} public void update(Point3D uc,float width,float height,float depth) { this.uc.moveTo(uc); resize(width,height,depth);} public void update(Point3D uc,short width,short height,short depth) { this.uc.moveTo(uc); resize(width,height,depth);} public Sphere extSphere() { Line3D diagonal=diagLine(); return new Sphere(diagonal.midpoint(),diagonal.length() / 2f,color,full);} public Line3D diagLine() { Vector3 diagVector=Vector3.shiftXYZ(width,height,depth); Point3D rd=new Point3D(uc); rd.shift(diagVector); Line3D diagLine=new Line3D(uc,rd); return diagLine;} public Point3D getCenter() { Line3D diagonal=diagLine(); Point3D center=diagonal.midpoint(); return center;} public boolean equals(Object o) { if(o !=null && o instanceof Parallelepiped) if(uc.equals(((Parallelepiped) o).uc) && width==((Parallelepiped) o).width && height==((Parallelepiped) o).height && depth==((Parallelepiped) o).depth) return true; return false;} public String toString() { return "Rectangle("+uc+", "+width+", "+height+", "+depth+")";} public int x() { return uc.getX();} public int y() { return uc.getY();} public int z() { return uc.getZ();} public int w() { return width;} public int h() { return height;} public int d() { return depth;} public Point3D A() { return getP1();} public Point3D B() { return getP2();} public Point3D C() { return getP3();} public Point3D D() { return getP4();} public Point3D M() { return getP5();} public Point3D P() { return getP6();} public Point3D O() { return getP7();} public Point3D Q() { return getP8();} public Point3D getP1() { return uc;} public Point3D getP2() { return Vector3.shiftX(width).getShiftedCopy(uc);} public Point3D getP3() { return Vector3.shiftXY(width,height).getShiftedCopy(uc);} public Point3D getP4() { return Vector3.shiftY(height).getShiftedCopy(uc);} public Point3D getP5() { return Vector3.shiftZ(depth).getShiftedCopy(uc);} public Point3D getP6() { return Vector3.shiftXZ(width,depth).getShiftedCopy(uc);} public Point3D getP7() { return Vector3.shiftXYZ(width,height,depth).getShiftedCopy(uc);} public Point3D getP8() { return Vector3.shiftYZ(height,depth).getShiftedCopy(uc);} public Line3D AB() { return new Line3D(A(),B());} public Line3D BC() { return new Line3D(B(),C());} public Line3D CD() { return new Line3D(C(),D());} public Line3D DA() { return new Line3D(D(),A());} public Line3D MP() { return new Line3D(M(),P());} public Line3D PO() { return new Line3D(P(),O());} public Line3D OQ() { return new Line3D(O(),Q());} public Line3D QM() { return new Line3D(Q(),M());} public Line3D AM() { return new Line3D(A(),M());} public Line3D BP() { return new Line3D(B(),P());} public Line3D CO() { return new Line3D(C(),O());} public Line3D DQ() { return new Line3D(D(),Q());} public Line3D[] ribs() { Line3D[] ribs=new Line3D[12]; ribs[0]=AB(); ribs[1]=BC(); ribs[2]=CD(); ribs[3]=DA(); ribs[4]=MP(); ribs[5]=PO(); ribs[6]=OQ(); ribs[7]=QM(); ribs[8]=AM(); ribs[9]=BP(); ribs[10]=CO(); ribs[11]=DQ(); return ribs;} public Line2D[] ribsProjections() { Line2D[] ribs=new Line2D[12]; if(cds==null) init(color); ribs[0]=cds.ribLeftRight1; ribs[1]=cds.ribLeftRight2; ribs[2]=cds.ribLeftRight3; ribs[3]=cds.ribLeftRight4; ribs[4]=new Line2D(cds.getLeftCorners().p1,cds.getLeftCorners().p2,color); ribs[5]=new Line2D(cds.getLeftCorners().p2,cds.getLeftCorners().p3,color); ribs[6]=new Line2D(cds.getLeftCorners().p3,cds.getLeftCorners().p4,color); ribs[7]=new Line2D(cds.getLeftCorners().p4,cds.getLeftCorners().p1,color); ribs[8]=new Line2D(cds.getRightCorners().p1,cds.getRightCorners().p2,color); ribs[9]=new Line2D(cds.getRightCorners().p2,cds.getRightCorners().p3,color); ribs[10]=new Line2D(cds.getRightCorners().p3,cds.getRightCorners().p4,color); ribs[11]=new Line2D(cds.getRightCorners().p4,cds.getRightCorners().p1,color); return ribs;} public Point3D[] vertices() { Point3D[] verts=new Point3D[8]; verts[0]=A(); verts[1]=B(); verts[2]=C(); verts[3]=D(); verts[4]=M(); verts[5]=P(); verts[6]=O(); verts[7]=Q(); return verts;} public Point2D[] verticesProjections() { Point2D[] verts=new Point2D[8]; if(cds==null) init(color); verts[0]=cds.getLeftCorners().p1; verts[1]=cds.getLeftCorners().p2; verts[2]=cds.getLeftCorners().p3; verts[3]=cds.getLeftCorners().p4; verts[4]=cds.getRightCorners().p1; verts[5]=cds.getRightCorners().p2; verts[6]=cds.getRightCorners().p3; verts[7]=cds.getRightCorners().p4; return verts;} public FourCornersConture3D[] planes() { FourCornersConture3D[] result=new FourCornersConture3D[6]; result[0]=getPlaneLeft(); result[1]=getPlaneRight(); result[2]=getPlaneTop(); result[3]=getPlaneBottom(); result[4]=getPlaneBack(); result[5]=getPlaneFront(); return result;} public Conture2D[] planesProjections() { return null;} public FourCornersConture3D getPlaneLeft() { return new FourCornersConture3D(getP1(),getP4(),getP8(),getP5());} public FourCornersConture3D getPlaneRight() { return new FourCornersConture3D(getP2(),getP3(),getP7(),getP6());} public FourCornersConture3D getPlaneTop() { return new FourCornersConture3D(getP1(),getP2(),getP6(),getP5());} public FourCornersConture3D getPlaneBottom() { return new FourCornersConture3D(getP4(),getP3(),getP7(),getP8());} public FourCornersConture3D getPlaneBack() { return new FourCornersConture3D(getP5(),getP6(),getP7(),getP8());} public FourCornersConture3D getPlaneFront() { return new FourCornersConture3D(getP1(),getP2(),getP3(),getP4());} private void rectangle3BeyondMeasures(short objX,short objY,short objZ,short dx,short dy,short dz,Paint pn) { int STEP=(7-1+1) /(1+1); Rectangle2D area=pn.getArea(); float halfAreaW=area.w() / 2f; float halfAreaH=area.h() / 2f; float W12=(float)(area.w() / 100 *(STEP *(1+1))); float H12=(float)(area.h() / 100 *(STEP *(1+1))); float frontLeftX=0f; float frontTopY=0f; float frontRightX=0f; float frontBottomY=0f; float backLeftX=0f; float backTopY=0f; float backRightX=0f; float backBottomY=0f; float lx; float ly; float lx2; float ly2; float dl; int i; i=-objZ; lx=area.x()+halfAreaW; lx2=objX; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); frontLeftX=lx-lx2; lx=area.x()+halfAreaW; lx2=objX-dx; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); frontRightX=lx-lx2; ly=area.y()+halfAreaH; ly2=objY-dy; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); frontTopY=ly+ly2; ly=area.y()+halfAreaH; ly2=objY; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); frontBottomY=ly+ly2; do { dl=(float) dz * H12 / halfAreaH+(dz-(float) dz * H12 / halfAreaH) *(float)(300-i) / 300;} while(i <= 300 && i++<= dl-objZ); lx=area.x()+halfAreaW; lx2=objX; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); backLeftX=lx-lx2; lx=area.x()+halfAreaW; lx2=objX-dx; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); backRightX=lx-lx2; ly=area.y()+halfAreaH; ly2=objY-dy; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); backTopY=ly+ly2; ly=area.y()+halfAreaH; ly2=objY; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); backBottomY=ly+ly2; cds.getLeftCorners().p1.moveTo(frontLeftX,frontTopY); cds.getLeftCorners().p2.moveTo(backLeftX,backTopY); cds.getLeftCorners().p3.moveTo(backLeftX,backBottomY); cds.getLeftCorners().p4.moveTo(frontLeftX,frontBottomY); cds.getRightCorners().p1.moveTo(frontRightX,frontTopY); cds.getRightCorners().p2.moveTo(backRightX,backTopY); cds.getRightCorners().p3.moveTo(backRightX,backBottomY); cds.getRightCorners().p4.moveTo(frontRightX,frontBottomY); cds.ribLeftRight1.updateVector(cds.getRightCorners().p1); cds.ribLeftRight2.updateVector(cds.getRightCorners().p2); cds.ribLeftRight3.updateVector(cds.getRightCorners().p3); cds.ribLeftRight4.updateVector(cds.getRightCorners().p4);} public boolean isInside(Point3D point) { boolean result=point.belongs(this); return result;} public boolean intersects(Line3D line) { Point3D p1=line.getP1(); Point3D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; Line3D[] ribs=ribs(); for(int i=0; i < ribs.length; i++) if(ribs[i].intersects(line)) return true; return false;} public Point3D getCenter3D() { return getCenter();} public int xMin() { return getCenter().getX()-width / 2;} public int xMax() { return getCenter().getX()+width / 2;} public int yMin() { return getCenter().getY()-height / 2;} public int yMax() { return getCenter().getY()+height / 2;} public int zMin() { return getCenter().getZ()-depth / 2;} public int zMax() { return getCenter().getZ()+depth / 2;} public Plane3D getPlaneCenterZ() { int middleZ=(zMax()-zMin()) / 2; Plane3D planeZ=getPlaneZ(middleZ); return planeZ;} public Plane3D getPlaneCenterY() { int middleY=(yMax()-yMin()) / 2; Plane3D planeY=getPlaneY(middleY); return planeY;} public Plane3D getPlaneCenterX() { int middleX=(xMax()-xMin()) / 2; Plane3D planeX=getPlaneX(middleX); return planeX;} public Plane3D getPlaneZ(int z) { Point3D center=getCenter(); Point3D pp1=Vector3.shiftX(-width / 2).getShiftedCopy(center); Point3D pp2=Vector3.shiftX(width / 2).getShiftedCopy(center); Point3D pp3=Vector3.shiftY(height / 2).getShiftedCopy(center); return new Plane3D(pp1,pp2,pp3,color);} public Plane3D getPlaneY(int y) { Point3D center=getCenter(); Point3D pp1=Vector3.shiftX(-width / 2).getShiftedCopy(center); Point3D pp2=Vector3.shiftX(width / 2).getShiftedCopy(center); Point3D pp3=Vector3.shiftZ(depth / 2).getShiftedCopy(center); return new Plane3D(pp1,pp2,pp3,color);} public Plane3D getPlaneX(int x) { Point3D center=getCenter(); Point3D pp1=Vector3.shiftY(-height / 2).getShiftedCopy(center); Point3D pp2=Vector3.shiftY(height / 2).getShiftedCopy(center); Point3D pp3=Vector3.shiftZ(depth / 2).getShiftedCopy(center); return new Plane3D(pp1,pp2,pp3,color);} public int getMinX() { int lx=getPlaneLeft().getP1().getX(); int rx=getPlaneRight().getP1().getX(); return min(lx,rx);} public int getMaxX() { int lx=getPlaneLeft().getP1().getX(); int rx=getPlaneRight().getP1().getX(); return max(lx,rx);} public int getCenterX() { return(getMinX()+getMaxX()) / 2;} public int getMinY() { int ly=getPlaneTop().getP1().getY(); int ry=getPlaneBottom().getP1().getY(); return min(ly,ry);} public int getMaxY() { int ly=getPlaneTop().getP1().getY(); int ry=getPlaneBottom().getP1().getY(); return max(ly,ry);} public int getCenterY() { return(getMinY()+getMaxY()) / 2;} public int getMinZ() { int lz=getPlaneBack().getP1().getZ(); int rz=getPlaneFront().getP1().getZ(); return min(lz,rz);} public int getMaxZ() { int lz=getPlaneBack().getP1().getZ(); int rz=getPlaneFront().getP1().getZ(); return max(lz,rz);} public int getCenterZ() { return(getMinZ()+getMaxZ()) / 2;} public double volume() { double result=getWidth() * getHeight() * getDepth(); return result;}} class Rectangle3Changes { protected Point3D uc; protected int width; protected int height; protected int depth; public Rectangle3Changes(Point3D uc,int width,int height,int depth) { super(); assasign(uc,width,height,depth); uc=null;} public boolean hasChanges(Parallelepiped r3d) { if(uc==null) { uc=r3d.uc; return true;} if(r3d.uc.equals(uc)==false) return true; if(r3d.width !=width) return true; if(r3d.height !=height) return true; if(r3d.depth !=depth) return true; return false;} public void assasign(Point3D uc,int width,int height,int depth) { this.uc=uc; this.width=width; this.height=height; this.depth=depth;}}