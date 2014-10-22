package mydev.ex;

import java.awt.Frame;
import java.awt.Color;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Event;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.Dimension;

public class Sample {
public static void main(String[] args) {
System.out.println("Hello Wind World!");
if(args != null && args.length > 0) {
String filePath = null;
for(int i = 0; filePath==null && i < args.length; i++)
if(args[i] != null && !args[i].equalsIgnoreCase(new Sample().getClass().getName()))
filePath = args[i];
if(filePath != null && filePath.length() > 0) {
Context ctx = new Context();
MyFrame mf = new Builder().build(ctx,filePath);
mf.pack();
mf.show();
mf.move(100,100);
MyThread mt = new MyThread(ctx);
mt.start();
MyThreadX mtx = new MyThreadX(ctx);
mtx.start();
ctx.mta.requestFocus();
int counter = 2;
while(counter-->=0 && ctx.nonStop())
try{Thread.sleep(1000);}catch(InterruptedException ex) {ex.printStackTrace();}
counter = 9999;
while(counter-->=0 && ctx.nonStop())
try{Thread.sleep(1000);}catch(InterruptedException ex) {ex.printStackTrace();}
ctx.stop();
counter = 1;
while(counter-->=0)
try{Thread.sleep(1000);}catch(InterruptedException ex) {ex.printStackTrace();}
mf.hide();
mt.myExit();
mtx.myExit();
mf.dispose();
System.exit(0);
}
} else System.out.println("It requires one parameter: - editable file location.");
}
}

class Context {
boolean stop = false;
MyTextArea mta;
String fileName;
MyFrame mf;
void stop() {
stop = true;
}
boolean nonStop() {
return !stop;
}
void setup(MyTextArea mta,String fileName,MyFrame mf) {
this.mta = mta;
this.fileName = fileName;
this.mf = mf;
}
}

class MyThread extends Thread {
boolean stop = false;
int heavy = 1;
Context ctx;
public MyThread(Context ctx) {
this.ctx = ctx;
}
public void myExit() {
stop=true;
}
public void run() {
String path = ctx.fileName;
try {
File fi = new File(ctx.fileName);
path = fi.getAbsolutePath();
FileInputStream in = new FileInputStream(fi);
StringBuffer buf = new StringBuffer();
int code = 0;
while((code = in.read()) >= 0) {
char ch = (char)code;
if(ch != '\r') buf.append(ch);
}
in.close();
String editing = buf.toString();
ctx.mta.setText(editing);
} catch(IOException ex) {
ex.printStackTrace();
} finally {
ctx.mf.setTitle(ctx.mf.getTitle() +" :: "+ path);
}
while(!stop) {
System.out.print(heavy-heavy++/2*2==1?"1":"2");
try{Thread.sleep(113);}catch(InterruptedException ex) {ex.printStackTrace();}
}
}
}

class MyThreadX extends Thread {
boolean stop = false;
Context ctx;
public MyThreadX(Context ctx) {
this.ctx = ctx;
}
public void myExit() {
stop=true;
}
public void run() {
while(!stop) {
if(!ctx.nonStop()) {
String editing = ctx.mta.getText();
try{
File fi = new File(ctx.fileName);
FileOutputStream out = new FileOutputStream(fi);
for(int i = 0; i < editing.length(); i++) {
char ch = editing.charAt(i);
if(ch == '\n') {out.write((int)'\r');out.write((int)'\n');}
else
out.write((int)ch);
}
out.close();
} catch(IOException ex) {ex.printStackTrace();}
stop = true;
}
System.out.print("2");
try{Thread.sleep(117);}catch(InterruptedException ex) {ex.printStackTrace();}
}
}
}

class MyTextArea extends TextArea {
public MyTextArea() {
super(30,80);
setBackground(Color.black);
setForeground(Color.white);
//setFont(new Font("SansSerif",Font.ITALIC,17));
}
}

class MyPanel extends Panel {
MyTextArea mta;
public MyPanel(MyTextArea mta) {
super();
this.mta = mta;
setBackground(Color.white);
setLayout(new FlowLayout());
add(mta);
}
}

class MyFrame extends Frame {
MyPanel mp;
Context ctx;
public MyFrame(MyPanel mp,Context ctx) {
super();
this.mp = mp;
this.ctx = ctx;
setTitle("MyFrame");
setBackground(Color.gray);
setLayout(new FlowLayout());
add(mp);
}
public boolean handleEvent(Event evt) {
//System.out.println(""+evt);
if(evt.id == Event.WINDOW_DESTROY) ctx.stop();
return false;
}
}

class Builder {
public MyFrame build(Context ctx,String fileName) {
MyTextArea mta = new MyTextArea();
MyPanel mp = new MyPanel(mta);
MyFrame mf = new MyFrame(mp,ctx);
ctx.setup(mta,fileName,mf);
return mf;
}
}