package mydev.about; public class Cilinder3D extends FillFullable implements Reposition { protected Circle3D firstBase; protected Circle3D secondBase; public Cilinder3D(Circle3D firstBase,Circle3D secondBase) { this(firstBase,secondBase,ColorsArray.blackColorInt,false);} public Cilinder3D(Circle3D firstBase,Circle3D secondBase,int color) { this(firstBase,secondBase,color,false);} public Cilinder3D(Circle3D firstBase,Circle3D secondBase,int color,boolean solid) { super(color,solid); this.firstBase=firstBase; this.secondBase=secondBase;} public void represent(Matrix2D matrix) { firstBase.represent(matrix); secondBase.represent(matrix);} public void toPenSolid(Canvas ics,Paint pn) { firstBase.toPenSolid(ics,pn); secondBase.toPenSolid(ics,pn);} public void toPenConture(Canvas ics,Paint pn) { firstBase.toPenConture(ics,pn); secondBase.toPenConture(ics,pn);} public void shift(Vector3 shVect) { firstBase.shift(shVect); secondBase.shift(shVect);} public void moveTo(Point2D newLoc) { firstBase.moveTo(newLoc); secondBase.moveTo(newLoc);} public void moveTo(Point3D newLoc) { firstBase.moveTo(newLoc); secondBase.moveTo(newLoc);} public void moveTo(int newX,int newY) { firstBase.moveTo(newX,newY); secondBase.moveTo(newX,newY);} public void moveTo(float newX,float newY) { firstBase.moveTo(newX,newY); secondBase.moveTo(newX,newY);} public void moveTo(double newX,double newY) { firstBase.moveTo(newX,newY); secondBase.moveTo(newX,newY);} public void moveTo(short newX,short newY) { firstBase.moveTo(newX,newY); secondBase.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { firstBase.moveTo(newX,newY,newZ); secondBase.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { firstBase.moveTo(newX,newY,newZ); secondBase.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { firstBase.moveTo(newX,newY,newZ); secondBase.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { firstBase.moveTo(newX,newY,newZ); secondBase.moveTo(newX,newY,newZ);} public Circle3D getFirstBase() { return firstBase;} public Circle3D getSecondBase() { return secondBase;} public String toString() { return "Cilinder3D [firstBase="+firstBase+", secondBase="+secondBase+"]";}}