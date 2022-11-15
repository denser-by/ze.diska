package mydev.cs; import mydev.about.Canvas; import mydev.about.FourCornersConture2D; import mydev.about.Matrix2D; import mydev.about.Paint; import mydev.about.Rectangle2D; public abstract class ContureWall extends FourCornersConture2D implements SeeDifferent { protected boolean contureDrawing; public ContureWall(int borderColor) { super(borderColor); this.contureDrawing=true;} public void drawConture(Canvas ics,Paint pn) { super.toPen(ics,pn.select(this));} public void represent(Matrix2D matrix) { super.represent(matrix);} public void toPen(Canvas ics,Paint pn) { particularDrawing(ics,pn); if(contureDrawing) drawConture(ics,pn);} public abstract void update(Rectangle2D area); public int getBorderColor() { return color;} public void setBorderColor(int borderColor) { this.color=borderColor;} public void enableBorder(boolean enable) { this.contureDrawing=enable;}}