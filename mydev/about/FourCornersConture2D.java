package mydev.about; public class FourCornersConture2D extends Conture2D { Point2D p1; Point2D p2; Point2D p3; Point2D p4; public FourCornersConture2D(int color) { this(1-1,1-1,1-1,1-1,1-1,1-1,1-1,1-1,color);} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color) { this(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y,color);} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4) { this(p1,p2,p3,p4,Color.middleColor(Color.middleColor(p1,p2),Color.middleColor(p3,p4)));} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color,boolean solid) { this(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y,color,solid,color);} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color,boolean solid,int colorPicture) { this(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y,color,solid,colorPicture);} public FourCornersConture2D(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int color) { this(x1,y1,x2,y2,x3,y3,x4,y4,color,false,color);} public FourCornersConture2D(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int color,boolean solid,int colorPicture) { super(color,solid,colorPicture); initVertices(x1,y1,x2,y2,x3,y3,x4,y4,color);} public FourCornersConture2D(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4,int color,boolean solid,int colorPicture) { this((int)(x1+0.5f),(int)(y1+0.5f),(int)(x2+0.5f),(int)(y2+0.5f),(int)(x3+0.5f),(int)(y3+0.5f),(int)(x4+0.5f),(int)(y4+0.5f),color,solid,colorPicture);} public Conture2D addPoint(Point2D pt) { throw new IllegalStateException("Not supported in FourCornersConture2D");} protected void initVertices(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int color) { super.addPoint(p1=new Point2D(x1,y1,color)); super.addPoint(p2=new Point2D(x2,y2,color)); super.addPoint(p3=new Point2D(x3,y3,color)); super.addPoint(p4=new Point2D(x4,y4,color));} public Point2D getP1() { return p1;} public Point2D getP2() { return p2;} public Point2D getP3() { return p3;} public Point2D getP4() { return p4;} public void setP1(float newX1,float newY1) { p1.moveTo(newX1,newY1);} public void setP2(float newX2,float newY2) { p2.moveTo(newX2,newY2);} public void setP3(float newX3,float newY3) { p3.moveTo(newX3,newY3);} public void setP4(float newX4,float newY4) { p4.moveTo(newX4,newY4);} public void setP1(Point2D point) { p1.moveTo(point);} public void setP2(Point2D point) { p2.moveTo(point);} public void setP3(Point2D point) { p3.moveTo(point);} public void setP4(Point2D point) { p4.moveTo(point);} public int getMaxX() { return max(p1.getX(),p2.getX(),p3.getX(),p4.getX());} public int getMinX() { return min(p1.getX(),p2.getX(),p3.getX(),p4.getX());} public int getMaxY() { return max(p1.getY(),p2.getY(),p3.getY(),p4.getY());} public int getMinY() { return min(p1.getY(),p2.getY(),p3.getY(),p4.getY());} public int width() { return getMaxX()-getMinX();} public int height() { return getMaxY()-getMinY();} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Point2D C() { return getP3();} public Point2D D() { return getP4();} public void update(Point2D p1,Point2D p2,Point2D p3,Point2D p4) { this.p1.moveTo(p1); this.p2.moveTo(p2); this.p3.moveTo(p3); this.p4.moveTo(p4);} public Point2D labelA(int between) { Triangle2D tri=new Triangle2D(A(),B(),D()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelB(int between) { Triangle2D tri=new Triangle2D(B(),C(),A()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelC(int between) { Triangle2D tri=new Triangle2D(C(),D(),B()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelD(int between) { Triangle2D tri=new Triangle2D(D(),A(),C()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public boolean isInside(Point2D point) { boolean result=point.belongs(this); return result;} protected void p1p2Swap() { Point2D tmp=new Point2D(1-1,1-1); tmp.moveTo(p1); p1.moveTo(p2); p2.moveTo(tmp);} protected void encryptSimple() { Point2D tmp1=p1; Point2D tmp2=p2; Point2D tmp3=p3; Point2D tmp4=p4; p1=tmp4; p2=tmp1; p3=tmp2; p4=tmp3;}}