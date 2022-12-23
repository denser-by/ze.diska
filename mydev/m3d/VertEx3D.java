package mydev.m3d; import mydev.about.Canvas; import mydev.about.Cube; import mydev.about.Line2D; import mydev.about.Matrix2D; import mydev.about.Paint; import mydev.about.Point2D; import mydev.about.Point3D; import mydev.about.Vector3; import mydev.cs.Bounds; import mydev.cs.ConturePlane; import mydev.cs.PlanesProfile; import mydev.csprofile.ActiveCubeProfile; import mydev.csprofile.CubeProfile; import mydev.sqproj.SqProjsRepo; public class VertEx3D extends Cube implements OrderedZ { protected Bounds front=new Bounds(); protected Bounds back=new Bounds(); protected SqProjsRepo projsRepo; protected short currentX; protected short currentY; protected short startX; protected short startY; protected boolean rellocation; protected String name; protected PlanesProfile planesRepo; protected ActivationListener activationListener; protected static VertEx3D activeObj; protected static int activeColor; private static PlanesProfile commonVertExProfile; private static PlanesProfile activeVertExProfile; public VertEx3D(String pointName,Point3D point,int halfSide,int objColor,int objActivationColor,int projectionsColor) { this(pointName,point,halfSide,objColor,projectionsColor); VertEx3D.activeColor=activeColor;} public VertEx3D(String pointName,Point3D point,int halfSide,int objColor,int projectionsColor) { super(point,halfSide,objColor,false); this.planesRepo=getCommonPlanesProfile(objColor); this.projsRepo=new SqProjsRepo(projectionsColor); this.name=pointName !=null ? pointName : "";} public PlanesProfile getCommonPlanesProfile(int objColor) { if(commonVertExProfile==null) commonVertExProfile=obtainCommonPlanesProfile(objColor); return commonVertExProfile;} public PlanesProfile getActivePlanesProfile(int objActivationColor) { if(activeVertExProfile==null) activeVertExProfile=obtainActivePlanesProfile(objActivationColor); return activeVertExProfile;} protected PlanesProfile obtainCommonPlanesProfile(int objColor) { return new CubeProfile(objColor);} protected PlanesProfile obtainActivePlanesProfile(int objActivationColor) { return new ActiveCubeProfile(objActivationColor);} public static int getActiveColor() { return activeColor;} public static void setActiveColor(int activeColor) { VertEx3D.activeColor=activeColor;} public String getName() { return name;} protected void changePlanesProfile(boolean active) { this.planesRepo=active ? activeVertExProfile : commonVertExProfile;} public Point2D getFrontBackViewCenter() { Point2D viewCenter=new Line2D(front.getAvg(),back.getAvg()).midpoint(); return viewCenter;} public SqProjsRepo getProjsRepo() { return projsRepo;} public PlanesProfile getPlanesRepo() { return planesRepo;} public ActivationListener getListener() { return activationListener;} public void setListener(ActivationListener listener) { this.activationListener=listener;} public static VertEx3D getActive() { return VertEx3D.activeObj;} public void activate() { if(VertEx3D.activeObj !=this) { VertEx3D.activeObj=this; changePlanesProfile(true); if(activationListener !=null) activationListener.onActivate(this);}} public void deactivate() { if(VertEx3D.activeObj==this) { VertEx3D.activeObj=null; changePlanesProfile(false); if(activationListener !=null) activationListener.onDeactivate(this);}} public short xLn() { return(short)(r);} public short yLn() { return(short)(r);} public short zLn() { return(short)(r);} public void moveX(int dx) { center.shift(Vector3.shiftX(dx));} public void moveY(int dy) { center.shift(Vector3.shiftY(dy));} public void moveZ(int dz) { center.shift(Vector3.shiftZ(dz));} public boolean gt(OrderedZ mg) { return z() >((VertEx3D) mg).z();} public int getZ() { return z();} public boolean isRellocation() { return rellocation;} public void rellocate(boolean oz,int w2) { this.rellocation=true; if(oz) { if(startX >= w2) this.moveZ(currentX-startX); else this.moveZ(-(currentX-startX));} else { this.moveX(-(currentX-startX)); this.moveY(currentY-startY);} this.startX=this.currentX; this.startY=this.currentY;} public void setCaptureRelocPoint(short mx,short my) { this.startX=mx; this.startY=my; this.currentX=mx; this.currentY=my;} public void updateCaptureRelocPoint(short mx,short my) { this.currentX=mx; this.currentY=my;} boolean isBetween(short e,short a1,short a2) { if(e >= a1 && e <= a2) return true; else if(e >= a2 && e <= a1) return true; return false;} public boolean mouseClickIntercept(short mx,short my) { if(isBetween(mx,front.getLeftX(),front.getRightX()) && isBetween(my,front.getTopY(),front.getBottomY())) return true; else if(isBetween(mx,back.getLeftX(),back.getRightX()) && isBetween(my,back.getTopY(),back.getBottomY())) return true; return false;} public void represent(Matrix2D matrix) { super.represent(matrix);} public void toPen(Canvas ics,Paint pn) { ConturePlane[] planes=planesRepo.planesIterator(); for(int i=0; i < planes.length; i++) { ConturePlane plane=planes[i]; plane.update(front,back); plane.toPen(ics,pn.select(plane));}} public void regDistPlane(float leftX,float topY,float rightX,float bottomY) { back.setLeftX((short)(leftX+0.5f)); back.setTopY((short)(topY+0.5f)); back.setRightX((short)(rightX+0.5f)); back.setBottomY((short)(bottomY+0.5f));} public void regNearPlane(float leftX,float topY,float rightX,float bottomY) { front.setLeftX((short)(leftX+0.5f)); front.setTopY((short)(topY+0.5f)); front.setRightX((short)(rightX+0.5f)); front.setBottomY((short)(bottomY+0.5f));}}