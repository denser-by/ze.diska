package mydev.about; import java.util.Enumeration; import java.util.Vector; public class Rectangle3D extends FillFullable implements Reposition,Decompose,Turner { protected Point3D uc; protected int width; protected int height; protected int depth; private CubeDrawSupport cds; private Rectangle3Changes cc; public Rectangle3D(int width,int height,int depth) { this(new Point3D(0,0,0),width,height,depth,ColorsArray.blackColorInt,false);} public Rectangle3D(Point3D uc,int width,int height,int depth) { this(uc,width,height,depth,uc.color,false);} public Rectangle3D(Point3D uc,int width,int height,int depth,int color) { this(uc,width,height,depth,color,false);} public Rectangle3D(Point3D uc,int width,int height,int depth,int color,boolean solid) { super(color,solid); this.uc=uc; this.width=width; this.height=height; this.depth=depth; init(color);} public Rectangle3D(Point3D uc,float width,float height,float depth) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,false);} public Rectangle3D(Point3D uc,float width,float height,float depth,int color) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,false);} public Rectangle3D(Point3D uc,float width,float height,float depth,int color,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,solid);} public Rectangle3D(Point3D uc,double width,double height,double depth) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,false);} public Rectangle3D(Point3D uc,double width,double height,double depth,int color) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,false);} public Rectangle3D(Point3D uc,double width,double height,double depth,int color,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,solid);} public Rectangle3D(Point3D uc,short width,short height,short depth) { this(uc,(int) width,(int) height,(int) depth,uc.color,false);} public Rectangle3D(Point3D uc,short width,short height,short depth,int color) { this(uc,(int) width,(int) height,(int) depth,color,false);} public Rectangle3D(Point3D uc,short width,short height,short depth,int color,boolean solid) { this(uc,(int) width,(int) height,(int) depth,color,solid);} public Rectangle3D(Rectangle3D r) { this(r.uc,r.width,r.height,r.depth,r.color,r.full);} private void init(int color) { if(cds==null) { cds=new CubeDrawSupport(color); cc=new Rectangle3Changes(uc,width,height,depth);}} public void represent(Matrix2D matrix) { matrix.addRectangle3D(this);} public void toPenSolid(Canvas ics,Paint pn) {} public void toPenConture(Canvas ics,Paint pn) { if(cc.hasChanges(this)==cc.hasChanges(this)) { rectangle3BeyondMeasures((short) uc.getX(),(short) uc.getY(),(short) uc.getZ(),(short) width,(short) height,(short) depth,pn); cc.assasign(uc,width,height,depth);} cds.getLeftCorners().toPen(ics,pn.select(cds.getLeftCorners())); cds.getRightCorners().toPen(ics,pn.select(cds.getRightCorners())); cds.ribLeftRight1.toPen(ics,pn.select(cds.ribLeftRight1)); cds.ribLeftRight2.toPen(ics,pn.select(cds.ribLeftRight2)); cds.ribLeftRight3.toPen(ics,pn.select(cds.ribLeftRight3)); cds.ribLeftRight4.toPen(ics,pn.select(cds.ribLeftRight4));} public void turnAroundX(float dtAngleX) {} public void turnAroundY(float dtAngleY) {} public void turnAroundZ(float dtAngleZ) {} public void turnStartX(float angleX) {} public void turnStartY(float angleY) {} public void turnStartZ(float angleZ) {} public float getAngleX() { return 0;} public float getAngleY() { return 0;} public float getAngleZ() { return 0;} public void shift(Vector3 shVect) { uc.shift(shVect);} public void moveTo(Point2D newLoc) { uc.moveTo(newLoc);} public void moveTo(Point3D newLoc) { uc.moveTo(newLoc);} public void moveTo(int newX,int newY) { uc.moveTo(newX,newY);} public void moveTo(float newX,float newY) { uc.moveTo(newX,newY);} public void moveTo(double newX,double newY) { uc.moveTo(newX,newY);} public void moveTo(short newX,short newY) { uc.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { uc.moveTo(newX,newY,newZ);} public Point3D getUc() { return uc;} public int getWidth() { return width;} public void setWidth(int width) { this.width=width;} public int getHeight() { return height;} public void setHeight(int height) { this.height=height;} public int getDepth() { return depth;} public void setDepth(int depth) { this.depth=depth;} public void resize(int width,int height) { this.width=width; this.height=height;} public void resize(float width,float height) { this.width=(int)(width+0.5f); this.height=(int)(height+0.5f);} public void resize(int width,int height,int depth) { this.width=width; this.height=height; this.depth=depth;} public void resize(float width,float height,float depth) { this.width=(int)(width+0.5f); this.height=(int)(height+0.5f); this.depth=(int)(depth+0.5f);} public void update(Point3D uc,int width,int height,int depth) { this.uc.moveTo(uc); resize(width,height,depth);} public void update(Point3D uc,float width,float height,float depth) { this.uc.moveTo(uc); resize(width,height,depth);} public void update(Point3D uc,short width,short height,short depth) { this.uc.moveTo(uc); resize(width,height,depth);} public Sphere extSphere() { Vector3 diag=Vector3.shiftXYZ(width,height,depth); Point3D rd=new Point3D(uc); rd.shift(diag); Line3D diagonal=new Line3D(uc,rd); Point3D center=diagonal.middlePoint(); float r=(float)(diagonal.length() / 2f); return new Sphere(center,r,color,full);} public boolean equals(Object o) { if(o !=null && o instanceof Rectangle3D) if(uc.equals(((Rectangle3D) o).uc) && width==((Rectangle3D) o).width && height==((Rectangle3D) o).height && depth==((Rectangle3D) o).depth) return true; return false;} public String toString() { return "Rectangle("+uc+", "+width+", "+height+", "+depth+")";} public int x() { return uc.getX();} public int y() { return uc.getY();} public int z() { return uc.getZ();} public int w() { return width;} public int h() { return height;} public int d() { return depth;} public Enumeration ribsIterator() { Vector ribs=new Vector(); if(cds==null) init(color); ribs.addElement(cds.ribLeftRight1); ribs.addElement(cds.ribLeftRight2); ribs.addElement(cds.ribLeftRight3); ribs.addElement(cds.ribLeftRight4); ribs.addElement(new Line2D(cds.getLeftCorners().p1,cds.getLeftCorners().p2,color)); ribs.addElement(new Line2D(cds.getLeftCorners().p2,cds.getLeftCorners().p3,color)); ribs.addElement(new Line2D(cds.getLeftCorners().p3,cds.getLeftCorners().p4,color)); ribs.addElement(new Line2D(cds.getLeftCorners().p4,cds.getLeftCorners().p1,color)); ribs.addElement(new Line2D(cds.getRightCorners().p1,cds.getRightCorners().p2,color)); ribs.addElement(new Line2D(cds.getRightCorners().p2,cds.getRightCorners().p3,color)); ribs.addElement(new Line2D(cds.getRightCorners().p3,cds.getRightCorners().p4,color)); ribs.addElement(new Line2D(cds.getRightCorners().p4,cds.getRightCorners().p1,color)); return ribs.elements();} public Enumeration verticesIterator() { Vector verts=new Vector(); if(cds==null) init(color); verts.addElement(cds.getLeftCorners().p1); verts.addElement(cds.getLeftCorners().p2); verts.addElement(cds.getLeftCorners().p3); verts.addElement(cds.getLeftCorners().p4); verts.addElement(cds.getRightCorners().p1); verts.addElement(cds.getRightCorners().p2); verts.addElement(cds.getRightCorners().p3); verts.addElement(cds.getRightCorners().p4); return verts.elements();} public Enumeration planesIterator() { Vector planes=new Vector(); planes.addElement(getPlane(1)); planes.addElement(getPlane(2)); planes.addElement(getPlane(3)); planes.addElement(getPlane(4)); planes.addElement(getPlane(5)); planes.addElement(getPlane(6)); return planes.elements();} private Plane3D getPlane(int idx) { return null;} private void rectangle3BeyondMeasures(short objX,short objY,short objZ,short dx,short dy,short dz,Paint pn) { int STEP=(7-1+1) /(1+1); Rectangle2D area=pn.getArea(); float halfAreaW=area.w() / 2f; float halfAreaH=area.h() / 2f; float W12=(float)(area.w() / 100 *(STEP *(1+1))); float H12=(float)(area.h() / 100 *(STEP *(1+1))); float frontLeftX=0f; float frontTopY=0f; float frontRightX=0f; float frontBottomY=0f; float backLeftX=0f; float backTopY=0f; float backRightX=0f; float backBottomY=0f; float lx; float ly; float lx2; float ly2; float dl; int i; i=-objZ; lx=area.x()+halfAreaW; lx2=objX; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); frontLeftX=lx-lx2; lx=area.x()+halfAreaW; lx2=objX-dx; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); frontRightX=lx-lx2; ly=area.y()+halfAreaH; ly2=objY-dy; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); frontTopY=ly+ly2; ly=area.y()+halfAreaH; ly2=objY; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); frontBottomY=ly+ly2; do { dl=(float) dz * H12 / halfAreaH+(dz-(float) dz * H12 / halfAreaH) *(float)(300-i) / 300;} while(i <= 300 && i++<= dl-objZ); lx=area.x()+halfAreaW; lx2=objX; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); backLeftX=lx-lx2; lx=area.x()+halfAreaW; lx2=objX-dx; lx2=lx2-(float) i / 300 *(lx2-W12 / halfAreaW * lx2); backRightX=lx-lx2; ly=area.y()+halfAreaH; ly2=objY-dy; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); backTopY=ly+ly2; ly=area.y()+halfAreaH; ly2=objY; ly2=ly2-(float) i / 300 *(ly2-H12 / halfAreaH * ly2); backBottomY=ly+ly2; cds.getLeftCorners().p1.moveTo(frontLeftX,frontTopY); cds.getLeftCorners().p2.moveTo(backLeftX,backTopY); cds.getLeftCorners().p3.moveTo(backLeftX,backBottomY); cds.getLeftCorners().p4.moveTo(frontLeftX,frontBottomY); cds.getRightCorners().p1.moveTo(frontRightX,frontTopY); cds.getRightCorners().p2.moveTo(backRightX,backTopY); cds.getRightCorners().p3.moveTo(backRightX,backBottomY); cds.getRightCorners().p4.moveTo(frontRightX,frontBottomY); cds.ribLeftRight1.updateVector(cds.getRightCorners().p1); cds.ribLeftRight2.updateVector(cds.getRightCorners().p2); cds.ribLeftRight3.updateVector(cds.getRightCorners().p3); cds.ribLeftRight4.updateVector(cds.getRightCorners().p4);}} class Rectangle3Changes { protected Point3D uc; protected int width; protected int height; protected int depth; public Rectangle3Changes(Point3D uc,int width,int height,int depth) { super(); assasign(uc,width,height,depth); uc=null;} public boolean hasChanges(Rectangle3D r3d) { if(uc==null) { uc=r3d.uc; return true;} if(r3d.uc.equals(uc)==false) return true; if(r3d.width !=width) return true; if(r3d.height !=height) return true; if(r3d.depth !=depth) return true; return false;} public void assasign(Point3D uc,int width,int height,int depth) { this.uc=uc; this.width=width; this.height=height; this.depth=depth;} public boolean isInside(Point3D point) { return false;} public boolean intersects(Line3D line) { Point3D p1=line.getP1(); Point3D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; return false;}}