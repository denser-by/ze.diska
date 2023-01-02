package mydev.about; abstract class LineAbstract extends Colorfull implements Reposition { protected Vector3 vect; LineAbstract(Color color) { super(color);} LineAbstract(int color) { super(color);} public Vector3 getVect() { return vect.copyVector();} public Vector3 getBackVect() { return vect.backVector();} public double length() { return vect.module();} public double halfLength() { return length() / 2.f;} protected abstract Reposition mainPoint(); public void shift(Vector3 shVect) { mainPoint().shift(shVect);} public void moveTo(Point2D newLoc) { mainPoint().moveTo(newLoc);} public void moveTo(Point3D newLoc) { mainPoint().moveTo(newLoc);} public void moveTo(int newX,int newY) { mainPoint().moveTo(newX,newY);} public void moveTo(float newX,float newY) { mainPoint().moveTo(newX,newY);} public void moveTo(double newX,double newY) { mainPoint().moveTo(newX,newY);} public void moveTo(short newX,short newY) { mainPoint().moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { mainPoint().moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { mainPoint().moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { mainPoint().moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { mainPoint().moveTo(newX,newY,newZ);}}