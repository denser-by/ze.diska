package mydev.about; public class FourCornersConture2D extends Conture2D { protected Point2D p1; protected Point2D p2; protected Point2D p3; protected Point2D p4; public FourCornersConture2D(int color) { this(1-1,1-1,1-1,1-1,1-1,1-1,1-1,1-1,color);} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color) { this(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y,color);} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4) { this(p1,p2,p3,p4,Color.middleColor(Color.middleColor(p1,p2),Color.middleColor(p3,p4)));} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color,boolean solid) { this(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y,color,solid,color);} public FourCornersConture2D(Point2D p1,Point2D p2,Point2D p3,Point2D p4,int color,boolean solid,int colorPicture) { this(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y,color,solid,colorPicture);} public FourCornersConture2D(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int color) { this(x1,y1,x2,y2,x3,y3,x4,y4,color,false,color);} public FourCornersConture2D(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int color,boolean solid,int colorPicture) { super(color,solid,colorPicture); initVertices(x1,y1,x2,y2,x3,y3,x4,y4,color); initConture();} public FourCornersConture2D(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4,int color,boolean solid,int colorPicture) { this((int)(x1+0.5f),(int)(y1+0.5f),(int)(x2+0.5f),(int)(y2+0.5f),(int)(x3+0.5f),(int)(y3+0.5f),(int)(x4+0.5f),(int)(y4+0.5f),color,solid,colorPicture);} protected void initVertices(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int color) { this.p1=new Point2D(x1,y1,color); this.p2=new Point2D(x2,y2,color); this.p3=new Point2D(x3,y3,color); this.p4=new Point2D(x4,y4,color);} private void initConture() { addPoint(p1); addPoint(p2); addPoint(p3); addPoint(p4);} public Point2D getP1() { return p1;} public Point2D getP2() { return p2;} public Point2D getP3() { return p3;} public Point2D getP4() { return p4;} public void setP1(float newX1,float newY1) { p1.moveTo(newX1,newY1);} public void setP2(float newX2,float newY2) { p2.moveTo(newX2,newY2);} public void setP3(float newX3,float newY3) { p3.moveTo(newX3,newY3);} public void setP4(float newX4,float newY4) { p4.moveTo(newX4,newY4);} public void setP1(Point2D point) { p1.moveTo(point);} public void setP2(Point2D point) { p2.moveTo(point);} public void setP3(Point2D point) { p3.moveTo(point);} public void setP4(Point2D point) { p4.moveTo(point);} public int getMaxX() { return max(p1.getX(),p2.getX(),p3.getX(),p4.getX());} public int getMinX() { return min(p1.getX(),p2.getX(),p3.getX(),p4.getX());} public int getMaxY() { return max(p1.getY(),p2.getY(),p3.getY(),p4.getY());} public int getMinY() { return min(p1.getY(),p2.getY(),p3.getY(),p4.getY());} public int width() { return getMaxX()-getMinX();} public int height() { return getMaxY()-getMinY();} public String toString() { return "FourCornersConture2D [p1="+p1+", p2="+p2+", p3="+p3+", p4="+p4+"]";} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Point2D C() { return getP3();} public Point2D D() { return getP4();} public void update(Point2D p1,Point2D p2,Point2D p3,Point2D p4) { this.p1.moveTo(p1); this.p2.moveTo(p2); this.p3.moveTo(p3); this.p4.moveTo(p4);} public Point2D labelA(int between) { Triangle2D tri=new Triangle2D(A(),B(),D()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelB(int between) { Triangle2D tri=new Triangle2D(B(),C(),A()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelC(int between) { Triangle2D tri=new Triangle2D(C(),D(),B()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelD(int between) { Triangle2D tri=new Triangle2D(D(),A(),C()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public boolean isInside(Point2D point) { boolean result=point.belongs(this); return result;} public int hashCode() { final int prime=31; int result=super.hashCode(); result=prime * result+((p1==null) ? 0 : p1.hashCode()); result=prime * result+((p2==null) ? 0 : p2.hashCode()); result=prime * result+((p3==null) ? 0 : p3.hashCode()); result=prime * result+((p4==null) ? 0 : p4.hashCode()); return result;} public boolean equals(Object obj) { if(this==obj) return true; if(!super.equals(obj)) return false; if(getClass() !=obj.getClass()) return false; FourCornersConture2D other=(FourCornersConture2D) obj; if(p1==null) { if(other.p1 !=null) return false;} else if(!p1.equals(other.p1)) return false; if(p2==null) { if(other.p2 !=null) return false;} else if(!p2.equals(other.p2)) return false; if(p3==null) { if(other.p3 !=null) return false;} else if(!p3.equals(other.p3)) return false; if(p4==null) { if(other.p4 !=null) return false;} else if(!p4.equals(other.p4)) return false; return true;}}