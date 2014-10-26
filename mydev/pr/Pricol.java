package mydev.pr;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Date;
public class Pricol extends Panel {
Pricol(Dimension d) {
super();
setBackground(Color.black);
resize(-1+d.width-1,-1+d.height-1);
move(1,1);
}
static int DW = 1364;
static int DH = 728;
public static void main(String[] args) {
Window wnd = new Window(new Frame("Pricol"));
wnd.resize(-10+DW-10,-10+DH-10);
Dimension s = wnd.size();
Pricol pricol = new Pricol(s);
wnd.add(pricol);
wnd.show();
wnd.move(10,10);
//wnd.show();
try{Thread.sleep(10000/3);}catch(InterruptedException ex){ex.getMessage();}
wnd.hide();
wnd.dispose();
System.exit(0);
}
public void paint(Graphics cs) {
super.paint(cs);
cs.setColor(Color.white);
cs.drawString(""+new Date().toString(),100+200+900-50,20);
}
}