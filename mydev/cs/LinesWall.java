package mydev.cs; import mydev.about.CanvasAdapter; import mydev.about.Matrix2D; import mydev.about.PaintAdapter; import mydev.about.ScanLineDrawer; public abstract class LinesWall extends ContureWall { protected int linesColor; protected boolean vertLines; protected int stepPercentBetweenLines; public LinesWall(int borderColor,int linesColor,boolean vertLines,int stepPercentBetweenLines) { super(borderColor); this.linesColor=linesColor; this.vertLines=vertLines; this.stepPercentBetweenLines=stepPercentBetweenLines;} public LinesWall(int borderColor,int linesColor,boolean vertLines) { super(borderColor); this.linesColor=linesColor; this.vertLines=vertLines; this.stepPercentBetweenLines=ScanLineDrawer.DEF_STEP_PERCENT;} public void particularDrawing(CanvasAdapter ics,PaintAdapter pn) { ScanLineDrawer li=particular(); li.toPen(ics,pn.select(li));} public void particularDrawing(Matrix2D matrix) { ScanLineDrawer li=particular(); li.represent(matrix);} protected ScanLineDrawer particular() { ScanLineDrawer li=new ScanLineDrawer(linesColor); if(vertLines) li.setConturePoints(p1,p4,p2,p3); else { li.setStartLine(p1,p2); li.setFinishLine(p4,p3);} li.setStepPercent(stepPercentBetweenLines); return li;} public int getLinesColor() { return linesColor;} public void setLinesColor(int linesColor) { this.linesColor=linesColor;} public boolean isVertLines() { return vertLines;} public void setVertLines(boolean vertLines) { this.vertLines=vertLines;} public int getStepPercentBetweenLines() { return stepPercentBetweenLines;} public void setStepPercentBetweenLines(int stepPercentBetweenLines) { this.stepPercentBetweenLines=stepPercentBetweenLines;}}