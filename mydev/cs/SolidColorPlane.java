package mydev.cs; import mydev.about.CanvasAdapter; import mydev.about.PaintAdapter; public abstract class SolidColorPlane extends ConturePlane { public SolidColorPlane(int borderColor,int fillColor,PlaneType planeType) { super(borderColor,planeType); this.full=true; this.colorPicture=fillColor;} public void particularDrawing(CanvasAdapter ics,PaintAdapter pn) { full=true;} public int getFillColor() { return colorPicture;} public void setFillColor(int fillColor) { this.colorPicture=fillColor;}}