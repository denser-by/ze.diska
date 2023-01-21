package mydev.about; abstract class LineAbstract extends Colorfull implements Reposition { protected Vector3 vect; LineAbstract(Color color) { super(color);} LineAbstract(int color) { super(color);} public Vector3 getVect() { return vect.copyVector();} public Vector3 getBackVect() { return vect.backVector();} public double length() { return vect.module();} public double halfLength() { return length() / 2.f;} protected abstract Reposition mainPoint(); public void shift(Vector3 shVect) { mainPoint().shift(shVect);} public void moveTo(Point2D newLoc) { mainPoint().moveTo(newLoc);} public void moveTo(Point3D newLoc) { mainPoint().moveTo(newLoc);} public void moveTo(int newX,int newY) { mainPoint().moveTo(newX,newY);} public void moveTo(float newX,float newY) { mainPoint().moveTo(newX,newY);} public void moveTo(double newX,double newY) { mainPoint().moveTo(newX,newY);} public void moveTo(short newX,short newY) { mainPoint().moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { mainPoint().moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { mainPoint().moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { mainPoint().moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { mainPoint().moveTo(newX,newY,newZ);} public boolean isMostDx() { int dxm=vect.mod(vect.dx); return dxm >= vect.mod(vect.dy) && dxm >= vect.mod(vect.dz);} public boolean isMostDy() { int dym=vect.mod(vect.dy); return dym >= vect.mod(vect.dx) && dym >= vect.mod(vect.dz);} public boolean isMostDz() { int dzm=vect.mod(vect.dz); return dzm >= vect.mod(vect.dx) && dzm >= vect.mod(vect.dy);} public int hashCode() { final int prime=31; int result=super.hashCode(); result=prime * result+((vect==null) ? 0 : vect.hashCode()); return result;} public boolean equals(Object obj) { if(this==obj) return true; if(!super.equals(obj)) return false; if(getClass() !=obj.getClass()) return false; LineAbstract other=(LineAbstract) obj; if(vect==null) { if(other.vect !=null) return false;} else if(!vect.equals(other.vect)) return false; return true;}}