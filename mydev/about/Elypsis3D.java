package mydev.about; public class Elypsis3D extends Rectangle3D { protected int angleStart; protected int angleDt; public Elypsis3D(Point3D uc,int width,int height,int depth,int angleStart,int angleDt) { this(uc,width,height,depth,uc.color,angleStart,angleDt,false);} public Elypsis3D(Point3D uc,int width,int height,int depth,int color,int angleStart,int angleDt) { this(uc,width,height,depth,color,angleStart,angleDt,false);} public Elypsis3D(Point3D uc,int width,int height,int depth,int color,int angleStart,int angleDt,boolean solid) { super(uc,width,height,depth,color,solid); this.angleStart=angleStart; this.angleDt=angleDt;} public Elypsis3D(Point3D uc,int width,int height,int depth,int color,float angleStart,float angleDt,boolean fill) { this(uc,width,height,depth,color,(int)(angleStart+0.5f),(int)(angleDt+0.5f),fill);} public Elypsis3D(Point3D uc,int width,int height,int depth,int color,double angleStart,double angleDt,boolean fill) { this(uc,width,height,depth,color,(float) angleStart,(float) angleDt,fill);} public Elypsis3D(Point3D uc,float width,float height,float depth,int angleStart,int angleDt) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,angleStart,angleDt);} public Elypsis3D(Point3D uc,float width,float height,float depth,float angleStart,float angleDt) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),uc.color,(int)(angleStart+0.5f),(int)(angleDt+0.5f));} public Elypsis3D(Point3D uc,float width,float height,float depth,int color,int angleStart,int angleDt) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,angleStart,angleDt);} public Elypsis3D(Point3D uc,float width,float height,float depth,int color,float angleStart,float angleDt) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,(int)(angleStart+0.5f),(int)(angleDt+0.5f));} public Elypsis3D(Point3D uc,float width,float height,float depth,int color,int angleStart,int angleDt,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,angleStart,angleDt,solid);} public Elypsis3D(Point3D uc,float width,float height,float depth,int color,float angleStart,float angleDt,boolean solid) { this(uc,(int)(width+0.5f),(int)(height+0.5f),(int)(depth+0.5f),color,(int)(angleStart+0.5f),(int)(angleDt+0.5f),solid);} public Elypsis3D(Point3D uc,double width,double height,double depth,int angleStart,int angleDt) { this(uc,(float) width,(float) height,(float) depth,uc.color,angleStart,angleDt);} public Elypsis3D(Point3D uc,double width,double height,double depth,int color,int angleStart,int angleDt) { this(uc,(float) width,(float) height,(float) depth,color,angleStart,angleDt);} public Elypsis3D(Point3D uc,double width,double height,double depth,int color,int angleStart,int angleDt,boolean solid) { this(uc,(float) width,(float) height,(float) depth,color,angleStart,angleDt,solid);} public Elypsis3D(Point3D uc,short width,short height,short depth,int angleStart,int angleDt) { this(uc,(int) width,(int) height,(int) depth,uc.color,angleStart,angleDt);} public Elypsis3D(Point3D uc,short width,short height,short depth,int color,int angleStart,int angleDt) { this(uc,(int) width,(int) height,(int) depth,color,angleStart,angleDt);} public Elypsis3D(Point3D uc,short width,short height,short depth,int color,int angleStart,int angleDt,boolean solid) { this(uc,(int) width,(int) height,(int) depth,color,angleStart,angleDt,solid);} public Elypsis3D(Elypsis3D s,int angleStart,int angleDt) { this(s.uc,s.width,s.height,s.depth,s.color,angleStart,angleDt,s.full);} public Elypsis3D(Elypsis3D s,float angleStart,float angleDt) { this(s.uc,s.width,s.height,s.depth,s.color,angleStart,angleDt,s.full);} public Elypsis3D(Elypsis3D s,double angleStart,double angleDt) { this(s.uc,s.width,s.height,s.depth,s.color,angleStart,angleDt,s.full);} public void represent(Matrix2D matrix) {} public void toPenSolid(Canvas ics,Paint pn) {} public void toPenConture(Canvas ics,Paint pn) {} public int getAngleStart() { return angleStart;} public int getAngleDt() { return angleDt;} public void setAngleStart(int angleStart) { this.angleStart=angleStart;} public void setAngleDt(int angleDt) { this.angleDt=angleDt;}}