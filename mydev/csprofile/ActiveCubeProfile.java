package mydev.csprofile; import mydev.about.CanvasAdapter; import mydev.about.ColorsArray; import mydev.about.Matrix2D; import mydev.about.PaintAdapter; import mydev.cs.Bounds; import mydev.cs.ConturePlane; import mydev.cs.LinesPlane; import mydev.cs.PlaneType; import mydev.cs.PlanesProfile; import mydev.cs.SolidColorPlane; public class ActiveCubeProfile extends PlanesProfile { public ActiveCubeProfile(int contureColor) { super(contureColor);} public void represent(Matrix2D matrix) {} public void toPen(CanvasAdapter ics,PaintAdapter pn) {} protected ConturePlane obtainPlaneBack(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneBack(contureColor));} protected ConturePlane obtainPlaneTop(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneTop(contureColor,ColorsArray.winPoint.ic(),true,11));} protected ConturePlane obtainPlaneRight(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneRight(contureColor,ColorsArray.lightGrayPoint.ic()));} protected ConturePlane obtainPlaneBottom(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneBottom(contureColor));} protected ConturePlane obtainPlaneLeft(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneLeft(contureColor,ColorsArray.lightGrayPoint.ic()));} protected ConturePlane obtainPlaneFront(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneFront(contureColor));}} abstract class CubeSolidColorPlane extends SolidColorPlane { public CubeSolidColorPlane(int borderColor,int fillColor,PlaneType planeType) { super(borderColor,fillColor,planeType);} public void particularDrawing(CanvasAdapter ics,PaintAdapter pn) {} public void particularDrawing(Matrix2D matrix) {}} class ActiveCubePlaneBack extends CubeConturePlane { public ActiveCubePlaneBack(int color) { super(color,PlaneType.PT_BACK);} public void update(Bounds front,Bounds back) { p1.moveTo(back.getLeftX(),back.getTopY()); p2.moveTo(back.getRightX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(back.getLeftX(),back.getBottomY());}} class ActiveCubePlaneFront extends CubeConturePlane { public ActiveCubePlaneFront(int color) { super(color,PlaneType.PT_FRONT);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(front.getRightX(),front.getTopY()); p3.moveTo(front.getRightX(),front.getBottomY()); p4.moveTo(front.getLeftX(),front.getBottomY());}} class ActiveCubePlaneLeft extends CubeSolidColorPlane { public ActiveCubePlaneLeft(int borderColor,int fillColor) { super(borderColor,fillColor,PlaneType.PT_LEFT);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(back.getLeftX(),back.getTopY()); p3.moveTo(back.getLeftX(),back.getBottomY()); p4.moveTo(front.getLeftX(),front.getBottomY());}} class ActiveCubePlaneBottom extends CubeConturePlane { public ActiveCubePlaneBottom(int color) { super(color,PlaneType.PT_BOTTOM);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getBottomY()); p2.moveTo(back.getLeftX(),back.getBottomY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(front.getRightX(),front.getBottomY());}} class ActiveCubePlaneRight extends CubeSolidColorPlane { public ActiveCubePlaneRight(int borderColor,int fillColor) { super(borderColor,fillColor,PlaneType.PT_RIGHT);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getRightX(),front.getTopY()); p2.moveTo(back.getRightX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(front.getRightX(),front.getBottomY());}} class ActiveCubePlaneTop extends LinesPlane { public ActiveCubePlaneTop(int borderColor,int linesColor,boolean vertLines,int stepPercentBetweenLines) { super(borderColor,linesColor,vertLines,stepPercentBetweenLines,PlaneType.PT_TOP);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(back.getLeftX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getTopY()); p4.moveTo(front.getRightX(),front.getTopY());}}