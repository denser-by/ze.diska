package mydev.about; public class Rectangle3D extends FillFullable implements Reposition { protected Point3D uc; protected int width; protected int height; protected int depth; public Rectangle3D(int width,int height,int depth) { this(new Point3D(1-1,1-1,1-1),width,height,depth,ColorsArray.blackColorInt,false);} public Rectangle3D(Point3D uc,int width,int height,int depth) { this(uc,width,height,depth,uc.color,false);} public Rectangle3D(Point3D uc,int width,int height,int depth,int color) { this(uc,width,height,depth,color,false);} public Rectangle3D(Point3D uc,int width,int height,int depth,int color,boolean solid) { super(color,solid); this.uc=uc; this.width=width; this.height=height; this.depth=depth;} public Rectangle3D(Point3D uc,float width,float height,float depth) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,false);} public Rectangle3D(Point3D uc,float width,float height,float depth,int color) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,false);} public Rectangle3D(Point3D uc,float width,float height,float depth,int color,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,solid);} public Rectangle3D(Point3D uc,double width,double height,double depth) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,false);} public Rectangle3D(Point3D uc,double width,double height,double depth,int color) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,false);} public Rectangle3D(Point3D uc,double width,double height,double depth,int color,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,solid);} public Rectangle3D(Point3D uc,short width,short height,short depth) { this(uc,(int) width,(int) height,(int) depth,uc.color,false);} public Rectangle3D(Point3D uc,short width,short height,short depth,int color) { this(uc,(int) width,(int) height,(int) depth,color,false);} public Rectangle3D(Point3D uc,short width,short height,short depth,int color,boolean solid) { this(uc,(int) width,(int) height,(int) depth,color,solid);} public Rectangle3D(Rectangle3D r) { this(r.uc,r.width,r.height,r.depth,r.color,r.full);} public void represent(Matrix2D matrix) {} public void toPenSolid(Canvas ics,Paint pn) {} public void toPenConture(Canvas ics,Paint pn) {} public int getWidth() { return width;} public void setWidth(int width) { this.width=width;} public int getHeight() { return height;} public void setHeight(int height) { this.height=height;} public int getDepth() { return depth;} public void setDepth(int depth) { this.depth=depth;} public String toString() { return "Rectangle3D [uc="+uc+", width="+width+", height="+height+", depth="+depth+"]";} public void shift(Vector3 shVect) { uc.shift(shVect);} public void moveTo(Point2D newLoc) { uc.moveTo(newLoc);} public void moveTo(Point3D newLoc) { uc.moveTo(newLoc);} public void moveTo(int newX,int newY) { uc.moveTo(newX,newY);} public void moveTo(float newX,float newY) { uc.moveTo(newX,newY);} public void moveTo(double newX,double newY) { uc.moveTo(newX,newY);} public void moveTo(short newX,short newY) { uc.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { uc.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { uc.moveTo(newX,newY,newZ);} public double volume() { double result=getWidth() * getHeight() * getDepth(); return result;}}