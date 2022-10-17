package mydev.about; public class Circle2D extends FillFullable { protected Point2D center; protected int r; public Circle2D(Point2D center,int r) { this(center,r,center.color,false);} public Circle2D(Point2D center,int r,int color) { this(center,r,color,false);} public Circle2D(Point2D center,int r,int color,boolean solid) { super(color,solid); this.center=center; this.r=r;} public Circle2D(Point2D center,float r) { this(center,(int)(r+0.5f),center.color);} public Circle2D(Point2D center,float r,int color) { this(center,(int)(r+0.5f),color);} public Circle2D(Point2D center,float r,int color,boolean solid) { this(center,(int)(r+0.5f),color,solid);} public Circle2D(Point2D center,short r) { this(center,(int) r,center.color);} public Circle2D(Point2D center,short r,int color) { this(center,(int) r,color);} public Circle2D(Point2D center,short r,int color,boolean solid) { this(center,(int) r,color,solid);} public void represent(Matrix2D matrix) { matrix.addCircle(this);} public void toPenSolid(Canvas ics,Paint pn) { ics.fillCircle(center.getX(),center.getY(),r,pn);} public void toPenConture(Canvas ics,Paint pn) { ics.drawCircle(center.getX(),center.getY(),r,pn);} public Point2D getCenter() { return center;} public int getR() { return r;} public void setR(int r) { this.r=r;} public void update(Point2D center,int r) { this.center.move(center); this.r=r;}}