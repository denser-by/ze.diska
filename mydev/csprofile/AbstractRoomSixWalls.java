package mydev.csprofile; import java.util.Enumeration; import mydev.about.Canvas; import mydev.about.Matrix2D; import mydev.about.Paint; import mydev.about.Point3D; import mydev.about.Rectangle3D; import mydev.cs.ContureWall; import mydev.cs.WallsProfile; public abstract class AbstractRoomSixWalls extends Rectangle3D { protected WallsProfile walls; protected VisibilityProfile visible=new VisibilityProfile(); public AbstractRoomSixWalls(Point3D uc,int width,int height,int depth,int color,boolean solid,WallsProfile walls) { super(uc,width,height,depth,color,solid); this.walls=walls;} public WallsProfile getWalls() { return walls;} public VisibilityProfile getVisible() { return visible;} public void setVisible(VisibilityProfile proVisible) { if(proVisible !=null) this.visible=proVisible;} public void represent(Matrix2D matrix) { super.represent(matrix);} public void toPen(Canvas ics,Paint pn) { super.toPen(ics,pn); updateWalls(width / 2f,height / 2f); Enumeration itWalls=wallsIterator(); while(itWalls.hasMoreElements()) { ContureWall curWall=(ContureWall) itWalls.nextElement(); if(curWall !=null) { if(curWall.equals(walls.getLeftSide()) && visible.isWallLeft()) curWall.toPen(ics,pn.select(curWall)); else if(curWall.equals(walls.getRightSide()) && visible.isWallRight()) curWall.toPen(ics,pn.select(curWall)); else if(curWall.equals(walls.getCeiling()) && visible.isWallTop()) curWall.toPen(ics,pn.select(curWall)); else if(curWall.equals(walls.getFloor()) && visible.isWallDown()) curWall.toPen(ics,pn.select(curWall)); else if(curWall.equals(walls.getFarSide()) && visible.isWallFar()) curWall.toPen(ics,pn.select(curWall)); else if(curWall.equals(walls.getNearGlass()) && visible.isWallNear()) curWall.toPen(ics,pn.select(curWall));}}} public Enumeration wallsIterator() { return walls.wallsIterator();} protected void updateWalls(float middleX,float middleY) {((ReConture) walls.getLeftSide()).updateConture(verticesIterator(),middleX,middleY);((ReConture) walls.getRightSide()).updateConture(verticesIterator(),middleX,middleY);((ReConture) walls.getCeiling()).updateConture(verticesIterator(),middleX,middleY);((ReConture) walls.getCeiling()).updateContureAfter();((ReConture) walls.getFloor()).updateConture(verticesIterator(),middleX,middleY);((ReConture) walls.getFloor()).updateContureAfter();((ReConture) walls.getFarSide()).updateConture(verticesIterator(),middleX,middleY);((ReConture) walls.getFarSide()).updateContureAfter();}}