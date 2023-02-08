package mydev.about; public class Square extends Circle2D implements Decompose { public Square(Square s) { super(s);} public Square(Point2D center,double r,int color,boolean solid) { super(center,r,color,solid);} public Square(Point2D center,double r,int color) { super(center,r,color);} public Square(Point2D center,double r) { super(center,r);} public Square(Point2D center,float r,int color,boolean solid) { super(center,r,color,solid);} public Square(Point2D center,float r,int color) { super(center,r,color);} public Square(Point2D center,float r) { super(center,r);} public Square(Point2D center,int r,int color,boolean solid) { super(center,r,color,solid);} public Square(Point2D center,int r,int color) { super(center,r,color);} public Square(Point2D center,int r) { super(center,r);} public Square(Point2D center,short r,int color,boolean solid) { super(center,r,color,solid);} public Square(Point2D center,short r,int color) { super(center,r,color);} public Square(Point2D center,short r) { super(center,r);} public void represent(Matrix2D matrix) { matrix.addSquare(this);} public void toPenSolid(CanvasAdapter ics,PaintAdapter pn) { ics.fillSquare(center.getX(),center.getY(),r,pn);} public void toPenConture(CanvasAdapter ics,PaintAdapter pn) { ics.drawSquare(center.getX(),center.getY(),r,pn);} public Conture2D conture() { Conture2D conture=new Conture2D(color); conture.addPoint(A()); conture.addPoint(B()); conture.addPoint(C()); conture.addPoint(D()); return conture;} public int getWidth() { return 2 * r;} public void setWidth(int width) { this.r=width / 2;} public int getHeight() { return 2 * r;} public void setHeight(int height) { this.r=height / 2;} public void resize(int width,int height) { this.r=(width+height) / 4;} public void resize(float width,float height) { this.r=(int)((width+height) / 4+0.5f);} public void update(Point2D uc,int width,int height) { this.center.moveTo(uc); resize(width,height);} public void update(Point2D uc,float width,float height) { this.center.moveTo(uc); resize(width,height);} public void update(Point2D uc,short width,short height) { this.center.moveTo(uc); resize(width,height);} public Circle2D innerCircle() { Circle2D result=new Circle2D(center,r,color,full); return result;} public Circle2D outterCircle() { Circle2D result=new Circle2D(center,diagLine().halfLength(),color,full); return result;} public Line2D diagLine() { Line2D diagLine=new Line2D(A(),C()); return diagLine;} public Point2D getCenter() { return center;} public boolean equals(Object o) { if(o !=null && o instanceof Square) if(center.equals(((Square) o).center) && r==((Square) o).r) return true; return false;} public String toString() { return "Square [center="+center+", r="+r+", full="+full+", color="+color+"]";} public int x() { return center.getX();} public int y() { return center.getY();} public int w() { return 2 * r;} public int h() { return 2 * r;} public Point2D A() { return getP1();} public Point2D B() { return getP2();} public Point2D C() { return getP3();} public Point2D D() { return getP4();} public Point2D getP1() { return Vector3.shiftXY(-r,-r).getShiftedCopy(center);} public Point2D getP2() { return Vector3.shiftXY(r,-r).getShiftedCopy(center);} public Point2D getP3() { return Vector3.shiftXY(r,r).getShiftedCopy(center);} public Point2D getP4() { return Vector3.shiftXY(-r,r).getShiftedCopy(center);} public Line2D AB() { return new Line2D(A(),B());} public Line2D BC() { return new Line2D(B(),C());} public Line2D CD() { return new Line2D(C(),D());} public Line2D DA() { return new Line2D(D(),A());} public Line3D[] ribs() { return null;} public Line2D[] ribsProjections() { Line2D[] result=new Line2D[4]; result[0]=AB(); result[1]=BC(); result[2]=CD(); result[3]=DA(); return result;} public Point3D[] vertices() { return null;} public Point2D[] verticesProjections() { Point2D[] result=new Point2D[4]; result[0]=A(); result[1]=B(); result[2]=C(); result[3]=D(); return result;} public Triangle3D[] planes() { return null;} public Triangle2D[] planesProjections() { Triangle2D[] result=new Triangle2D[1]; result[1-1]=new Triangle2D(A(),B(),C()); return result;} public boolean isInside(Point2D point) { return point.getX() >= center.getX()-r && point.getX() <= center.getX()+r && point.getY() >= center.getY()-r && point.getY() <= center.getY()+r;} public boolean intersects(Line2D line) { Point2D p1=line.getP1(); Point2D p2=line.getP2(); if(isInside(p1) && !isInside(p2)) return true; else if(!isInside(p1) && isInside(p2)) return true; Line2D[] ribsProjections=ribsProjections(); for(int i=0; i < ribsProjections.length; i++) if(ribsProjections[i].intersects(line)) return true; return false;} public double perim() { double result=0f; Line2D[] ribsProjections=ribsProjections(); for(int i=0; i < ribsProjections.length; i++) result+=ribsProjections[i].length(); return result;} public Triangle2D[] trianglesIterator(Point2D mainVertex) { Conture2D conture=conture(); Triangle2D[] trianglesIterator=conture.trianglesIterator(mainVertex); return trianglesIterator;} public Triangle2D[] midpointTriangles() { Point2D mainVertex=getCenter(); Triangle2D[] triangles=trianglesIterator(mainVertex); return triangles;} public double area() { double result=getWidth() * getHeight(); return result;} public Point2D labelA(int between) { Triangle2D tri=new Triangle2D(A(),B(),D()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelB(int between) { Triangle2D tri=new Triangle2D(B(),C(),A()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelC(int between) { Triangle2D tri=new Triangle2D(C(),D(),B()); Point2D labelPoint=tri.labelA(between); return labelPoint;} public Point2D labelD(int between) { Triangle2D tri=new Triangle2D(D(),A(),C()); Point2D labelPoint=tri.labelA(between); return labelPoint;}}