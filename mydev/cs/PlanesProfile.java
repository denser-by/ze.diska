package mydev.cs; import java.util.Enumeration; import java.util.Vector; import mydev.about.Colorfull; public abstract class PlanesProfile extends Colorfull { private Vector planeSet=new Vector(); protected ConturePlane back; protected ConturePlane top; protected ConturePlane right; protected ConturePlane bottom; protected ConturePlane left; protected ConturePlane front; public PlanesProfile(int contureColor) { super(contureColor); createPlanesSet(contureColor);} protected void createPlanesSet(int contureColor) { planeSet.addElement(back=obtainPlaneBack(contureColor)); planeSet.addElement(top=obtainPlaneTop(contureColor)); planeSet.addElement(right=obtainPlaneRight(contureColor)); planeSet.addElement(bottom=obtainPlaneBottom(contureColor)); planeSet.addElement(left=obtainPlaneLeft(contureColor)); planeSet.addElement(front=obtainPlaneFront(contureColor));} protected abstract ConturePlane obtainPlaneBack(int contureColor); protected abstract ConturePlane obtainPlaneTop(int contureColor); protected abstract ConturePlane obtainPlaneRight(int contureColor); protected abstract ConturePlane obtainPlaneBottom(int contureColor); protected abstract ConturePlane obtainPlaneLeft(int contureColor); protected abstract ConturePlane obtainPlaneFront(int contureColor); public ConturePlane getBack() { return back;} public ConturePlane getTop() { return top;} public ConturePlane getRight() { return right;} public ConturePlane getBottom() { return bottom;} public ConturePlane getLeft() { return left;} public ConturePlane getFront() { return front;} public Enumeration planesIterator() { Vector planesCopy=new Vector(); planesCopy.addAll(planeSet); return planesCopy.elements();} public void updatePlanes(Bounds front,Bounds back) { Enumeration itPlanes=planesIterator(); while(itPlanes.hasMoreElements()) { ConturePlane curPlane=(ConturePlane) itPlanes.nextElement(); curPlane.update(front,back);}}}