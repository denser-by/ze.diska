package mydev.csprofile; import mydev.about.Canvas; import mydev.about.ColorsArray; import mydev.about.Matrix2D; import mydev.about.Paint; import mydev.cs.Bounds; import mydev.cs.ConturePlane; import mydev.cs.LinesPlane; import mydev.cs.PlanesProfile; import mydev.cs.SolidColorPlane; public class ActiveCubeProfile extends PlanesProfile { public ActiveCubeProfile(int contureColor) { super(contureColor);} public void represent(Matrix2D matrix) {} public void toPen(Canvas ics,Paint pn) {} protected ConturePlane obtainPlaneBack(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneBack(contureColor));} protected ConturePlane obtainPlaneTop(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneTop(contureColor,ColorsArray.winPoint.ic(),true,11));} protected ConturePlane obtainPlaneRight(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneRight(contureColor,ColorsArray.lightGrayPoint.ic()));} protected ConturePlane obtainPlaneBottom(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneBottom(contureColor));} protected ConturePlane obtainPlaneLeft(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneLeft(contureColor,ColorsArray.lightGrayPoint.ic()));} protected ConturePlane obtainPlaneFront(int contureColor) { return new CubePlaneWrap(contureColor,new ActiveCubePlaneFront(contureColor));}} abstract class CubeSolidColorPlane extends SolidColorPlane { public CubeSolidColorPlane(int borderColor,int fillColor) { super(borderColor,fillColor);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {}} class ActiveCubePlaneBack extends CubeConturePlane { public ActiveCubePlaneBack(int color) { super(color);} public void update(Bounds front,Bounds back) { p1.moveTo(back.getLeftX(),back.getTopY()); p2.moveTo(back.getRightX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(back.getLeftX(),back.getBottomY());} public String toString() { return "ActiveCubePlaneBack []";}} class ActiveCubePlaneFront extends CubeConturePlane { public ActiveCubePlaneFront(int color) { super(color);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(front.getRightX(),front.getTopY()); p3.moveTo(front.getRightX(),front.getBottomY()); p4.moveTo(front.getLeftX(),front.getBottomY());} public String toString() { return "ActiveCubePlaneFront []";}} class ActiveCubePlaneLeft extends CubeSolidColorPlane { public ActiveCubePlaneLeft(int borderColor,int fillColor) { super(borderColor,fillColor);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(back.getLeftX(),back.getTopY()); p3.moveTo(back.getLeftX(),back.getBottomY()); p4.moveTo(front.getLeftX(),front.getBottomY());} public String toString() { return "ActiveCubePlaneLeft []";}} class ActiveCubePlaneBottom extends CubeConturePlane { public ActiveCubePlaneBottom(int color) { super(color);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getBottomY()); p2.moveTo(back.getLeftX(),back.getBottomY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(front.getRightX(),front.getBottomY());} public String toString() { return "ActiveCubePlaneBottom []";}} class ActiveCubePlaneRight extends CubeSolidColorPlane { public ActiveCubePlaneRight(int borderColor,int fillColor) { super(borderColor,fillColor);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getRightX(),front.getTopY()); p2.moveTo(back.getRightX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(front.getRightX(),front.getBottomY());} public String toString() { return "ActiveCubePlaneRight []";}} class ActiveCubePlaneTop extends LinesPlane { public ActiveCubePlaneTop(int borderColor,int linesColor,boolean vertLines,int stepPercentBetweenLines) { super(borderColor,linesColor,vertLines,stepPercentBetweenLines);} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(back.getLeftX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getTopY()); p4.moveTo(front.getRightX(),front.getTopY());} public String toString() { return "ActiveCubePlaneTop []";}}