package mydev.cs; import mydev.about.Colorfull; import mydev.vutils.Queue; public abstract class PlanesProfile extends Colorfull { private Queue planeSet=new Queue(); protected ConturePlane back; protected ConturePlane top; protected ConturePlane right; protected ConturePlane bottom; protected ConturePlane left; protected ConturePlane front; public PlanesProfile(int contureColor) { super(contureColor); createPlanesSet(contureColor);} protected void createPlanesSet(int contureColor) { planeSet.enqueueQueueRecord(back=obtainPlaneBack(contureColor)); planeSet.enqueueQueueRecord(top=obtainPlaneTop(contureColor)); planeSet.enqueueQueueRecord(right=obtainPlaneRight(contureColor)); planeSet.enqueueQueueRecord(bottom=obtainPlaneBottom(contureColor)); planeSet.enqueueQueueRecord(left=obtainPlaneLeft(contureColor)); planeSet.enqueueQueueRecord(front=obtainPlaneFront(contureColor));} protected abstract ConturePlane obtainPlaneBack(int contureColor); protected abstract ConturePlane obtainPlaneTop(int contureColor); protected abstract ConturePlane obtainPlaneRight(int contureColor); protected abstract ConturePlane obtainPlaneBottom(int contureColor); protected abstract ConturePlane obtainPlaneLeft(int contureColor); protected abstract ConturePlane obtainPlaneFront(int contureColor); public ConturePlane getBack() { return back;} public ConturePlane getTop() { return top;} public ConturePlane getRight() { return right;} public ConturePlane getBottom() { return bottom;} public ConturePlane getLeft() { return left;} public ConturePlane getFront() { return front;} public ConturePlane[] planesIterator() { Object[] queueRecordsArray=planeSet.getQueueRecordsArray(); ConturePlane[] result=new ConturePlane[queueRecordsArray.length]; for(int i=0; i < result.length; i++) result[i]=(ConturePlane) queueRecordsArray[i]; return result;} public void updatePlanes(Bounds front,Bounds back) { ConturePlane[] itPlanes=planesIterator(); for(int i=0; i < itPlanes.length; i++) { ConturePlane curPlane=(ConturePlane) itPlanes[i]; curPlane.update(front,back);}}}