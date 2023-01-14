package mydev.uix; import java.awt.Color; import java.awt.Dimension; import java.awt.Graphics; import mydev.about.Canvas; import mydev.about.Colorfull; import mydev.about.ColorsArray; import mydev.about.Paint; import mydev.about.Point2D; import mydev.about.Rectangle2D; public abstract class CommonCanvas extends java.awt.Canvas { private boolean changes; protected Dimension sPrev; protected Dimension sCur; protected Color bgColor; protected Color borderColor; protected Point2D uc; protected CommonCanvas() { this(10,10,Color.white,null);} protected CommonCanvas(Color bgColor) { this(10,10,bgColor,null);} protected CommonCanvas(Color bgColor,Color borderColor) { this(10,10,bgColor,borderColor);} protected CommonCanvas(int width,int height,Color bgColor,Color borderColor) { super(); this.bgColor=bgColor; this.borderColor=borderColor; this.uc=new Point2D(0,0); setBackground(bgColor); resize(width,height);} public void paint(Graphics cs1) { super.paint(cs1); sCur=size(); if(sPrev==null) { sPrev=sCur; markChanges(); newSizeArrive((short) sCur.width,(short) sCur.height);} else if(notEq(sCur,sPrev)) { markChanges(); newSizeArrive((short) sCur.width,(short) sCur.height);} Paint pn=new Paint(); Canvas ics=new Canvas(cs1); drawItems(ics,pn); if(borderColor !=null) drawBorder(ics,pn); sPrev=sCur; clearChangesMark();} protected void newSizeArrive(short width,short height) {} public static boolean notEq(Dimension d1,Dimension other) { if(d1.width !=other.width) return true; if(d1.height !=other.height) return true; return false;} public void markChanges() { changes=true;} public void clearChangesMark() { changes=false;} public boolean hasChanges() { return changes;} public Color getBgColor() { return bgColor;} public void setBgColor(Color bgColor) { this.bgColor=bgColor; setBackground(bgColor);} public Color getBorderColor() { return borderColor;} public void setBorderColor(Color borderColor) { this.borderColor=borderColor;} public abstract void clearItems(); protected abstract void drawItems(Canvas ics,Paint pn); protected void drawBorder(Canvas ics,Paint pn) { Colorfull cf=new Rectangle2D(uc,getCcWidth(),getCcHeight(),borderColor.getRGB()); cf.toPen(ics,pn.select(cf));} public short getCcWidth() { if(sCur==null) return(short) 0; return(short) sCur.width;} public short getCcHeight() { if(sCur==null) return(short) 0; return(short) sCur.height;} public short getCcMinWidthOrHeight() { if(sCur==null) return(short) 0; return(short)(sCur.width < sCur.height ? sCur.width : sCur.height);} public Point2D obtainCenter() { return new Point2D(getCcHalfWidth(),getCcHalfHeight(),ColorsArray.blackPoint.ic());} public short getCcHalfWidth() { if(sCur==null) return(short) 0; return(short)(sCur.width / 2);} public short getCcHalfHeight() { if(sCur==null) return(short) 0; return(short)(sCur.height / 2);} public short getCcHalfMinWidthOrHeight() { if(sCur==null) return(short) 0; return(short)((sCur.width < sCur.height ? sCur.width : sCur.height) / 2);} public void refreshUpdatable() { invalidate(); repaint();}}