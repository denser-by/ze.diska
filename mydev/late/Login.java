package mydev.late; import java.awt.BorderLayout; import java.awt.Button; import java.awt.Color; import java.awt.Dialog; import java.awt.Dimension; import java.awt.Event; import java.awt.FlowLayout; import java.awt.FontMetrics; import java.awt.Frame; import java.awt.Graphics; import java.awt.Label; import java.awt.Panel; import java.awt.Point; import java.awt.Toolkit; import java.awt.Window; public class Login { public static void main(String[] args) { boolean first=true; Acquisition acq=new Acquisition(); acq.resize(640-1,480-1); Dimension s1=Toolkit.getDefaultToolkit().getScreenSize(); acq.move((s1.width-640) / 5 * 2,(s1.height-480) / 7 * 3); acq.show(); while(!acq.stop) try { Thread.sleep(300); if(first) { first=false; acq.resize(640,480);}} catch(InterruptedException e) {} acq.hide(); acq.dispose(); System.exit(1-1);}} class Acquisition extends Window { Top top; boolean stop; Center en; public Acquisition() { super(new Frame("")); stop=false; setLayout(new BorderLayout()); add("North",top=new Top(this)); add("Center",en=new Center());} public boolean mouseMove(Event et,int x,int y) { return super.mouseMove(et,x,y);} public boolean handleEvent(Event et) { if(et.id==Event.WINDOW_DESTROY) stop=true; if(et.id==Event.ACTION_EVENT && et.target==top.exit) stop=true; if(et.id==Event.ACTION_EVENT && et.target==top.help) showHelpDialog(); if(et.id==Event.KEY_PRESS && et.key==27) stop=true; if(et.id==Event.KEY_ACTION && et.key==Event.F1) showHelpDialog(); return super.handleEvent(et);} void showHelpDialog() { Frame f1=new Frame("Information"); Reading reading=new Reading(f1); Point p=location(); Dimension d=size(); int ww=190; int hh=120; f1.reshape(p.x+(d.width-ww) / 2,p.y+(d.height-200) / 2,ww,hh); reading.show(); reading.reshape(p.x+(d.width-ww) / 2,p.y+(d.height-hh) / 2,ww,hh); reading.setTitle("Helper");}} class Top extends Panel { Button exit; Button help; boolean capt; Window self; int x1; int y1; int dx1; int dy1; public Top(Window w) { super(); self=w; capt=false; setBackground(Color.blue); setLayout(new BorderLayout()); add("East",exit=new Button("Exit")); add("West",help=new Button("F1"));} public boolean mouseDown(Event et,int x,int y) { capt=true; x1=x; y1=y; dx1=x-x1; dy1=y-y1; Point p=self.location(); self.move(p.x+dx1,p.y+dy1); invalidate(); repaint(); return super.mouseDown(et,x,y);} public boolean mouseUp(Event et,int x,int y) { if(capt) { dx1=x-x1; dy1=y-y1; Point p=self.location(); self.move(p.x+dx1,p.y+dy1); capt=false; invalidate(); repaint();} return super.mouseUp(et,x,y);} public boolean mouseMove(Event et,int x,int y) { Translate.report(x,y); x1=x; y1=y; return super.mouseMove(et,x,y);}} class Center extends Panel { int x; int y; int w; int h; boolean capt; int px; int py; int mx; int my; int active; String username; String password; private boolean passed; public Center() { super(); setBackground(Color.lightGray); x=-1; capt=false; active=1; username=""; password=""; passed=false;} public boolean keyDown(Event et,int key) { if(key==(int) '\t') { active=active==1 ? 2 : 1; invalidate(); repaint();} char ch=(char) key; String passing="abcdefghijklmnopqrstuvwxyz_-!+=<>{}[];',.ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; if(passing.indexOf(ch) >= 0) { if(active==1) username+=ch; else if(active==2) password+=ch; invalidate(); repaint();} else if(key==(int) '\b') { if(active==1 && username.length() > 0) username=username.substring(0,username.length()-1); else if(active==2 && password.length() > 0) password=password.substring(0,password.length()-1); invalidate(); repaint();} else if(key==(int) '\n') { if(username.equals("request") && password.equals("everything1")) passed=true; else { username=""; password="";} invalidate(); repaint();} return super.keyDown(et,key);} public boolean mouseDown(Event evt,int x1,int y1) { mx=x1; my=y1; if(x < mx && mx < x+w && y < my && my < y+h / 7) { capt=true; px=x1; py=y1;} if(x+w / 4 <= mx && mx <= x+w / 4+200 && y+h / 12 * 3 <= my && my <= y+h / 12 * 3+20) active=1; if(x+w / 4 <= mx && mx <= x+w / 4+200 && y+h / 12 * 6 <= my && my <= y+h / 12 * 6+20) active=2; invalidate(); repaint(); return super.mouseDown(evt,x1,y1);} public boolean mouseUp(Event evt,int x1,int y1) { mx=x1; my=y1; if(capt) { x+=mx-px; y+=my-py; capt=false; invalidate(); repaint();} return super.mouseUp(evt,x1,y1);} public boolean mouseMove(Event evt,int x1,int y1) { mx=x1; my=y1; return super.mouseMove(evt,x1,y1);} public void paint(Graphics g) { if(x==-1) { Dimension d=size(); w=d.width / 2; h=d.height / 4; x=(d.width-w) / 11 * 5; y=(d.height-h) / 5 * 2;} if(!passed) { setForeground(Color.orange); g.fillRect(x,y,w,h); g.setColor(Color.black); g.drawLine(x,y,x+w,y); g.drawLine(x+w,y,x+w,y+h); g.drawLine(x+w,y+h,x,y+h); g.drawLine(x,y+h,x,y); g.fillRect(x,y,w,h / 7); g.setColor(Color.white); g.drawString(Translate.getString(Translate.AUTHN),x+w / 15,y+h / 11); g.setColor(Color.black); g.drawString(Translate.getString(Translate.NAME),x+w / 15,y+h / 12 * 5); g.drawString(Translate.getString(Translate.PASS),x+w / 15,y+h / 12 * 8); g.setColor(Color.white); g.fillRect(x+w / 4,y+h / 12 * 3,200,20); g.fillRect(x+w / 4,y+h / 12 * 6,200,20); g.setColor(Color.black); if(active==1) g.drawRect(x+w / 4,y+h / 12 * 3,200,20); else if(active==2) g.drawRect(x+w / 4,y+h / 12 * 6,200,20); FontMetrics fm=g.getFontMetrics(); int lx=fm.getAscent()+fm.getDescent(); g.drawString(""+username,x+w / 4+3,y+h / 12 * 3+lx); if(active==1) { int w1=fm.stringWidth(username); g.drawLine(x+w / 4+3+w1,y+h / 12 * 3+lx,x+w / 4+3+w1,y+h / 12 * 3);} g.drawString(""+password,x+w / 4+3,y+h / 12 * 6+lx); if(active==2) { int w1=fm.stringWidth(password); g.drawLine(x+w / 4+3+w1,y+h / 12 * 6+lx,x+w / 4+3+w1,y+h / 12 * 6);}} else g.drawString(new Congratulations(Color.blue,"Congratulations!").represent(g),100,100); super.paint(g);}} class Translate { static int dx=439857; final static int AUTHN=111; final static int NAME=121; final static int PASS=131; public static String getString(int code) { if(code==AUTHN) return Translate.is() ? "Login" : "Authn"; if(code==NAME) return Translate.is() ? "username" : "nickname"; if(code==PASS) return Translate.is() ? "password" : "keyword"; return "";} private static boolean is() { return(""+dx).length() >= 2;} public static void report(int x,int y) { int f=dx+x * 1023+y * 4; dx+=f-f / 755 * 755;}} class Reading extends Dialog { public Reading(Frame fr) { super(fr,true); setBackground(Color.lightGray); setLayout(new FlowLayout()); add(new Label("username: request")); add(new Label("password: everything1")); pack();} public boolean handleEvent(Event et) { if(et.id==Event.WINDOW_DESTROY) { hide(); dispose();} return super.handleEvent(et);}} class Congratulations { String message; Color color; public Congratulations(Color color,String message) { super(); this.color=color; this.message=message;} public String represent(Graphics cs) { cs.setColor(color); return message;}}
