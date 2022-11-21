package mydev.about; import java.awt.Font; public class Text2D extends Colorfull implements Reposition { protected Point2D middleHeightLeftPoint; protected String text; protected Font font; public Text2D(Point2D uc,String text,int color) { super(color); this.middleHeightLeftPoint=uc; this.text=text;} public Text2D(Point2D uc,String text,int color,Font font) { super(color); this.middleHeightLeftPoint=uc; this.text=text; this.font=font;} public void represent(Matrix2D matrix) { matrix.addText(this);} public void toPen(Canvas ics,Paint pn) { if(font !=null) ics.drawText(text,middleHeightLeftPoint.x,middleHeightLeftPoint.y,pn,font); else ics.drawText(text,middleHeightLeftPoint.x,middleHeightLeftPoint.y,pn);} public void shift(Vector3 shVect) { middleHeightLeftPoint.shift(shVect);} public void moveTo(Point2D newLoc) { middleHeightLeftPoint.moveTo(newLoc);} public void moveTo(Point3D newLoc) { middleHeightLeftPoint.moveTo(newLoc);} public void moveTo(int newX,int newY) { middleHeightLeftPoint.moveTo(newX,newY);} public void moveTo(float newX,float newY) { middleHeightLeftPoint.moveTo(newX,newY);} public void moveTo(double newX,double newY) { middleHeightLeftPoint.moveTo(newX,newY);} public void moveTo(short newX,short newY) { middleHeightLeftPoint.moveTo(newX,newY);} public void moveTo(int newX,int newY,int newZ) { middleHeightLeftPoint.moveTo(newX,newY,newZ);} public void moveTo(float newX,float newY,float newZ) { middleHeightLeftPoint.moveTo(newX,newY,newZ);} public void moveTo(double newX,double newY,double newZ) { middleHeightLeftPoint.moveTo(newX,newY,newZ);} public void moveTo(short newX,short newY,short newZ) { middleHeightLeftPoint.moveTo(newX,newY,newZ);} public String getText() { return text;} public void setText(String text) { this.text=text;} public Point2D getMiddleHeightLeftPoint() { return middleHeightLeftPoint;} public void setFont(Font font) { this.font=font;} public int getWidth(Canvas ics) { int textWidth=ics.drawTextWidth(text); return textWidth;} public int getHeight(Canvas ics) { int textHeight=ics.drawTextHeight(text); return textHeight;}}