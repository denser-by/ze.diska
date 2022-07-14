package mydev.bbb; import java.awt.Color; import java.awt.Event; import java.awt.Graphics; import java.awt.Panel; import mydev.vutils.Spiska; public class InternalPlane extends Panel { final static boolean D=false; protected Graphics ics; protected Color bgColor=Color.lightGray; protected Spiska items; SandboxB bs; private boolean markedRepaint; public InternalPlane(Color bgColor) { super(); this.bgColor=bgColor; this.items=new Spiska(); setBackground(bgColor);} public void paint(Graphics ics) { if(D) System.out.println("InternalPlane.paint()"); this.ics=ics; super.paint(ics);} public boolean mouseDown(Event evt,int x,int y) { if(D) System.out.println("InternalPlane.mouseDown()__ "+x+", "+y); bs=looking4(x,y); if(bs !=null) bs.mouseDown(evt,x,y); return super.mouseDown(evt,x,y);} public boolean mouseDrag(Event evt,int x,int y) { if(D) System.out.println("InternalPlane.mouseDrag()__ "+x+", "+y); if(bs !=null) bs.mouseDrag(evt,x,y); return super.mouseDrag(evt,x,y);} public boolean mouseUp(Event evt,int x,int y) { if(D) System.out.println("InternalPlane.mouseUp()__ "+x+", "+y); if(bs !=null) bs.mouseUp(evt,x,y); return super.mouseUp(evt,x,y);} public boolean mouseMove(Event evt,int x,int y) { if(D) System.out.println("InternalPlane.mouseMove()__ "+x+", "+y+" "+items.size()+" "+(looking4(x,y) !=null ? "ABOVE BOkno" : ".")); if(bs !=null) bs.mouseMove(evt,x,y); return super.mouseMove(evt,x,y);} public boolean mouseEnter(Event evt,int x,int y) { if(D) System.out.println("InternalPlane.mouseEnter()__ "+x+", "+y); if(bs !=null) bs.mouseEnter(evt,x,y); return super.mouseEnter(evt,x,y);} public boolean mouseExit(Event evt,int x,int y) { if(D) System.out.println("InternalPlane.mouseExit()__ "+x+", "+y); if(bs !=null) bs.mouseExit(evt,x,y); return super.mouseExit(evt,x,y);} public void regBOkno(BOkno bo,SandboxB bs) { this.bs=bs; this.items.append(new RePair(bo,bs));} public boolean hasBOknos() { return items !=null && items.size() > 0;} public boolean checkRepaint() { if(markedRepaint) { markedRepaint=false; return true;} return bs !=null ? bs.peekRepaint() : false;} public void performRepaint() { invalidate(); repaint();} SandboxB looking4(int x,int y) { for(int i=0; i < items.size(); i++) { RePair prs=(RePair) items.at(i); if(prs.turbo(x,y)) return prs.nebo;} return null;} public void clearBOknos() { this.items=new Spiska();} public void markRepaint() { markedRepaint=true;}} class RePair { BOkno bochock; SandboxB nebo; public RePair(BOkno bochock,SandboxB nebo) { super(); this.bochock=bochock; this.nebo=nebo;} public boolean turbo(int x,int y) { return bochock.lit(x,y);}}