package mydev.sqproj; import mydev.about.Colorfull; import mydev.about.FourCornersConture2D; import mydev.about.Matrix2D; import mydev.about.Paint; import mydev.about.Point2D; public class SqProjStripe extends Colorfull { protected FourCornersConture2D secondConture; protected FourCornersConture2D firstConture; public SqProjStripe(int color) { super(color);} public boolean areYouReady() { return firstConture !=null && secondConture !=null;} public void regFirstConture(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4) { this.firstConture=new FourCornersConture2D(new Point2D(x1,y1),new Point2D(x2,y2),new Point2D(x3,y3),new Point2D(x4,y4),color);} public void regSecondConture(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4) { this.secondConture=new FourCornersConture2D(new Point2D(x1,y1),new Point2D(x2,y2),new Point2D(x3,y3),new Point2D(x4,y4),color);} public void represent(Matrix2D matrix) { firstConture.represent(matrix); secondConture.represent(matrix);} public void toPen(mydev.about.Canvas ics,Paint pn) { firstConture.toPen(ics,pn.select(firstConture)); secondConture.toPen(ics,pn.select(secondConture));} public void readyDraw(mydev.about.Canvas ics,Paint pn) { if(areYouReady()) toPen(ics,pn);}}