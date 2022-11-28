package mydev.csprofile; import mydev.about.Canvas; import mydev.about.Matrix2D; import mydev.about.Paint; import mydev.cs.Bounds; import mydev.cs.ConturePlane; import mydev.cs.PlanesProfile; public class SphereCubeSelectorProfile extends PlanesProfile { public SphereCubeSelectorProfile(int contureColor) { super(contureColor);} public void represent(Matrix2D matrix) {} public void toPen(Canvas ics,Paint pn) {} protected ConturePlane obtainPlaneBack(int contureColor) { return new CubePlaneWrap(contureColor,new SpherePlaneBack(contureColor));} protected ConturePlane obtainPlaneTop(int contureColor) { return new CubePlaneWrap(contureColor,new SpherePlaneTop(contureColor));} protected ConturePlane obtainPlaneRight(int contureColor) { return new CubePlaneWrap(contureColor,new SpherePlaneRight(contureColor));} protected ConturePlane obtainPlaneBottom(int contureColor) { return new CubePlaneWrap(contureColor,new SpherePlaneBottom(contureColor));} protected ConturePlane obtainPlaneLeft(int contureColor) { return new CubePlaneWrap(contureColor,new SpherePlaneLeft(contureColor));} protected ConturePlane obtainPlaneFront(int contureColor) { return new CubePlaneWrap(contureColor,new SpherePlaneFront(contureColor));}} class SpherePlaneBack extends ConturePlane { public SpherePlaneBack(int color) { super(color);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {} public void update(Bounds front,Bounds back) { p1.moveTo(back.getLeftX(),back.getTopY()); p2.moveTo(back.getRightX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(back.getLeftX(),back.getBottomY());} public String toString() { return "SpherePlaneBack []";}} class SpherePlaneFront extends ConturePlane { public SpherePlaneFront(int color) { super(color);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(front.getRightX(),front.getTopY()); p3.moveTo(front.getRightX(),front.getBottomY()); p4.moveTo(front.getLeftX(),front.getBottomY());} public String toString() { return "SpherePlaneFront []";}} class SpherePlaneLeft extends ConturePlane { public SpherePlaneLeft(int color) { super(color);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(back.getLeftX(),back.getTopY()); p3.moveTo(back.getLeftX(),back.getBottomY()); p4.moveTo(front.getLeftX(),front.getBottomY());} public String toString() { return "SpherePlaneLeft []";}} class SpherePlaneBottom extends ConturePlane { public SpherePlaneBottom(int color) { super(color);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getBottomY()); p2.moveTo(back.getLeftX(),back.getBottomY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(front.getRightX(),front.getBottomY());} public String toString() { return "SpherePlaneBottom []";}} class SpherePlaneRight extends ConturePlane { public SpherePlaneRight(int color) { super(color);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {} public void update(Bounds front,Bounds back) { p1.moveTo(front.getRightX(),front.getTopY()); p2.moveTo(back.getRightX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getBottomY()); p4.moveTo(front.getRightX(),front.getBottomY());} public String toString() { return "SpherePlaneRight []";}} class SpherePlaneTop extends ConturePlane { public SpherePlaneTop(int color) { super(color);} public void particularDrawing(Canvas ics,Paint pn) {} public void particularDrawing(Matrix2D matrix) {} public void update(Bounds front,Bounds back) { p1.moveTo(front.getLeftX(),front.getTopY()); p2.moveTo(back.getLeftX(),back.getTopY()); p3.moveTo(back.getRightX(),back.getTopY()); p4.moveTo(front.getRightX(),front.getTopY());} public String toString() { return "SpherePlaneTop []";}}