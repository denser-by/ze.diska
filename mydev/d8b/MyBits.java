package mydev.d8b; public class MyBits { static short ONE=1; public static void main(String[] args) { System.out.println("Hello Wind World!"); Field f=new Field("Bits on demand - here and now!"); f.prepare(); f.show(); f.resize(400-ONE,300-ONE); f.move(100+ONE,100+ONE); f.init(); for(int i=0; i < 30; i++) { f.repaint(); try { Thread.sleep(999);} catch(InterruptedException ex) { ex.getMessage();} if(i > 0) System.out.print(i > 1 ? " "+i : ""+i);} f.finit(); f.hide(); f.dispose(); System.exit(1-1);}} class Field extends java.awt.Window { short STEP=20; Paralel b1,b2,b3,b4,b5,b6,b7,b8; Paralel c,d,e,f,g,h,i; Anomaly an; public Field(String name) { super(new java.awt.Frame(name));} short queue(int ord) { ord=STEP * ord+ord * STEP; return(short) ord;} short aff(int ord) { float mozg=0.9f *(STEP * ord+ord * STEP) *.9f; return(short) mozg;} private void b1init() { b1=new Paralel((short)(STEP+STEP),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b2=new Paralel(queue(2),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b3=new Paralel(queue(3),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b4=new Paralel(queue(4),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b5=new Paralel(queue(5),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b6=new Paralel(queue(6),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b7=new Paralel(queue(7),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); b8=new Paralel(queue(8),(short)(STEP *.8f+STEP * 0.81-MyBits.ONE)); if(an==null) an=new Anomaly(); c=new Paralel((short)(STEP+STEP),aff(2)); d=new Paralel((short)(STEP+STEP),aff(3)); e=new Paralel((short)(STEP+STEP),aff(4)); f=new Paralel((short)(STEP+STEP),aff(5)); g=new Paralel((short)(STEP+STEP),aff(6)); h=new Paralel((short)(STEP+STEP),aff(7)); i=new Paralel((short)(STEP+STEP),aff(8)); an.initialize();} public boolean mouseDown(java.awt.Event et,int x1,int y1) { new Thread(new Worker(x1,y1,b1,b2,b3,b4,b5,b6,b7,b8,c,d,e,f,g,h,i,an)).start(); return super.mouseDown(et,x1,y1);} public void paint(java.awt.Graphics ics) { if(b1==null) b1init(); b1.redirect(ics); b2.redirect(ics); b3.redirect(ics); b4.redirect(ics); b5.redirect(ics); b6.redirect(ics); b7.redirect(ics); b8.redirect(ics); c.redirect(ics); d.redirect(ics); e.redirect(ics); f.redirect(ics); g.redirect(ics); h.redirect(ics); i.redirect(ics); ics.setColor(java.awt.Color.white); new Rest(an).ref(ics,b2,c).ref(ics,b2,d).ref(ics,b2,e).ref(ics,b2,f).ref(ics,b2,g).ref(ics,b2,h).ref(ics,b2,i); new Rest(an).ref(ics,b3,c).ref(ics,b3,d).ref(ics,b3,e).ref(ics,b3,f).ref(ics,b3,g).ref(ics,b3,h).ref(ics,b3,i); new Rest(an).ref(ics,b4,c).ref(ics,b4,d).ref(ics,b4,e).ref(ics,b4,f).ref(ics,b4,g).ref(ics,b4,h).ref(ics,b4,i); new Rest(an).ref(ics,b5,c).ref(ics,b5,d).ref(ics,b5,e).ref(ics,b5,f).ref(ics,b5,g).ref(ics,b5,h).ref(ics,b5,i); new Rest(an).ref(ics,b6,c).ref(ics,b6,d).ref(ics,b6,e).ref(ics,b6,f).ref(ics,b6,g).ref(ics,b6,h).ref(ics,b6,i); new Rest(an).ref(ics,b7,c).ref(ics,b7,d).ref(ics,b7,e).ref(ics,b7,f).ref(ics,b7,g).ref(ics,b7,h).ref(ics,b7,i); new Rest(an).ref(ics,b8,c).ref(ics,b8,d).ref(ics,b8,e).ref(ics,b8,f).ref(ics,b8,g).ref(ics,b8,h).ref(ics,b8,i);} public void prepare() { setBackground(java.awt.Color.gray);} public void init() { if(an==null) an=new Anomaly(); an.load();} public void finit() { an.save();}} class Paralel { short x,y; Galochka admit; public Paralel(short xx,short yy) { this.x=xx; this.y=yy;} public void check() { if(admit !=null) admit=null; else admit=new Galochka();} public boolean isOnMy(int ex,int ey) { return ex >= x && ex <= x+20 && ey >= y && ey <= y+20;} public boolean isOnMyX(int ex) { return ex >= x && ex <= x+20;} public boolean isOnMyY(int ey) { return ey >= y && ey <= y+20;} public void redirect(java.awt.Graphics ics) { ics.setColor(java.awt.Color.black); ics.drawRect(x+1,y+1,20,20); ics.setColor(java.awt.Color.white); ics.drawRect(x,y,20,20); if(admit !=null) admit.redirect(ics,x,y);} public short getOmX() { return x;} public short getOmY() { return y;}} class Rest { Anomaly an; Rest(Anomaly ann) { this.an=ann;} static int horoshij=11; public Rest ref(java.awt.Graphics ics,Paralel un,Paralel de) { horoshij++; horoshij=horoshij-horoshij / 4 * 4; int ex=horoshij * 20 /(100-20); int ey=20-(3-horoshij) * 20 / 100; ics.drawString(an.whatItem(un,de),(int) un.getOmX()+ex,(int) de.getOmY()+ey); return this;}} class Anomaly { static String fname="mybits.d8b"; int B0PTRN=0x00000001; int B1PTRN=B0PTRN << 1; int B2PTRN=B0PTRN << 1+1; int B3PTRN=B0PTRN << 1+1+1; int B4PTRN=B0PTRN << 1+1+1+1; int B5PTRN=B0PTRN << 1+1+1+1+1; int B6PTRN=B0PTRN << 1+1+1+1+1+1; int B7PTRN=B0PTRN << 1+1+1+1+1+1+1; private byte[] d8b; public void initialize() { if(d8b !=null && d8b.length==8) return; d8b=new byte[1+1+1+1+1+1+1+1]; d8b[0]=0x0; d8b[1]=0x0; d8b[2]=0x0; d8b[3]=0x0; d8b[4]=0x0; d8b[5]=0x0; d8b[6]=0x0; d8b[7]=0x0;} public void load() { byte[] data=new byte[8]; java.io.File milefan=new java.io.File(fname); if(!milefan.exists()) return; java.io.FileInputStream instr; try { instr=new java.io.FileInputStream(milefan); int count=instr.read(data); if(count < 8) System.out.println("Not enough bytes read from file "+fname); instr.close();} catch(java.io.IOException ex) { ex.getMessage();} if(d8b==null) initialize(); d8b[0]=data[0]; d8b[1]=data[1]; d8b[2]=data[2]; d8b[3]=data[3]; d8b[4]=data[4]; d8b[5]=data[5]; d8b[6]=data[6]; d8b[7]=data[7];} public void save() { if(d8b !=null) { java.io.FileOutputStream onstr; try { onstr=new java.io.FileOutputStream(new java.io.File(fname)); byte[] data=new byte[8]; data[0]=d8b[0]; data[1]=d8b[1]; data[2]=d8b[2]; data[3]=d8b[3]; data[4]=d8b[4]; data[5]=d8b[5]; data[6]=d8b[6]; data[7]=d8b[7]; onstr.write(data); onstr.flush(); onstr.close();} catch(java.io.IOException ex) { ex.getMessage();}}} public String whatItem(Paralel c,Paralel r) { int row=0; int col=0; switch(c.getOmX() / 20) { case 4 : col=1; break; case 6 : col=2; break; case 8 : col=3; break; case 10 : col=4; break; case 12 : col=5; break; case 14 : col=6; break; case 16 : col=7; break;} switch(r.getOmY() / 20) { case 3 : row=1; break; case 4 : row=2; break; case 6 : row=3; break; case 8 : row=4; break; case 9 : row=5; break; case 11 : row=6; break; case 12 : row=7; break;} byte current=d8b[row]; short et=0; switch(col) { case 1 : if((current & B1PTRN) > 0x0) et=1; break; case 2 : if((current & B2PTRN) > 0x0) et=1; break; case 3 : if((current & B3PTRN) > 0x0) et=1; break; case 4 : if((current & B4PTRN) > 0x0) et=1; break; case 5 : if((current & B5PTRN) > 0x0) et=1; break; case 6 : if((current & B6PTRN) > 0x0) et=1; break; case 7 : if((current & B7PTRN) > 0x0) et=1; break;} return ""+et;} public void invertColumn(int c) { if(c <= 1) { for(int i=2; i <= 8; i++) invertColumn(i); return;} int PTRN=0; byte current; switch(c) { case 2 : PTRN=B1PTRN; break; case 3 : PTRN=B2PTRN; break; case 4 : PTRN=B3PTRN; break; case 5 : PTRN=B4PTRN; break; case 6 : PTRN=B5PTRN; break; case 7 : PTRN=B6PTRN; break; case 8 : PTRN=B7PTRN; break;} current=d8b[1]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[1]=current; current=d8b[2]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[2]=current; current=d8b[3]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[3]=current; current=d8b[4]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[4]=current; current=d8b[5]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[5]=current; current=d8b[6]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[6]=current; current=d8b[7]; if((current & PTRN) > 0x0) current ^=PTRN; else current |= PTRN; d8b[7]=current;} public void invertRow(int r) { if(r <= 1) return; byte current=d8b[r-1]; if((current & B1PTRN) > 0x0) current ^=B1PTRN; else current |= B1PTRN; if((current & B2PTRN) > 0x0) current ^=B2PTRN; else current |= B2PTRN; if((current & B3PTRN) > 0x0) current ^=B3PTRN; else current |= B3PTRN; if((current & B4PTRN) > 0x0) current ^=B4PTRN; else current |= B4PTRN; if((current & B5PTRN) > 0x0) current ^=B5PTRN; else current |= B5PTRN; if((current & B6PTRN) > 0x0) current ^=B6PTRN; else current |= B6PTRN; if((current & B7PTRN) > 0x0) current ^=B7PTRN; else current |= B7PTRN; d8b[r-1]=current;}} class Galochka { public void redirect(java.awt.Graphics ics,int cx,int cy) { ics.setColor(java.awt.Color.yellow); ics.drawLine(cx+1+1,cy-1-1,cx+1+1+1+1+1,cy+20-1-1); ics.drawLine(cx+1+1+1+1+1,cy+20-1-1,cx+20+1+1,cy+1+1); ics.drawLine(cx+1+1+1,cy-1-1,cx+1+1+1+1+1+1,cy+20-1-1); ics.drawLine(cx+1+1+1+1+1+1,cy+20-1-1,cx+20+1+1+1,cy+1+1); ics.drawLine(cx+1,cy-1-1,cx+1+1+1+1,cy+20-1-1); ics.drawLine(cx+1+1+1+1,cy+20-1-1,cx+20+1,cy+1+1);}} class Worker implements Runnable { Paralel b1,b2,b3,b4,b5,b6,b7,b8; Paralel c,d,e,f,g,h,i; Anomaly an; int x,y; Worker(int x2,int y2,Paralel b1,Paralel b2,Paralel b3,Paralel b4,Paralel b5,Paralel b6,Paralel b7,Paralel b8,Paralel c,Paralel d,Paralel e,Paralel f,Paralel g,Paralel h,Paralel i,Anomaly an) { this.x=x2; this.y=y2; this.b1=b1; this.b2=b2; this.b3=b3; this.b4=b4; this.b5=b5; this.b6=b6; this.b7=b7; this.b8=b8; this.c=c; this.d=d; this.e=e; this.f=f; this.g=g; this.h=h; this.i=i; this.an=an;} public void run() { if(b1.isOnMy(x,y)) { b1.check(); an.invertColumn(1);} if(b2.isOnMy(x,y)) { b2.check(); an.invertColumn(2);} if(b3.isOnMy(x,y)) { b3.check(); an.invertColumn(3);} if(b4.isOnMy(x,y)) { b4.check(); an.invertColumn(4);} if(b5.isOnMy(x,y)) { b5.check(); an.invertColumn(5);} if(b6.isOnMy(x,y)) { b6.check(); an.invertColumn(6);} if(b7.isOnMy(x,y)) { b7.check(); an.invertColumn(7);} if(b8.isOnMy(x,y)) { b8.check(); an.invertColumn(8);} if(c.isOnMy(x,y)) { c.check(); an.invertRow(2);} if(d.isOnMy(x,y)) { d.check(); an.invertRow(3);} if(e.isOnMy(x,y)) { e.check(); an.invertRow(4);} if(f.isOnMy(x,y)) { f.check(); an.invertRow(5);} if(g.isOnMy(x,y)) { g.check(); an.invertRow(6);} if(h.isOnMy(x,y)) { h.check(); an.invertRow(7);} if(i.isOnMy(x,y)) { i.check(); an.invertRow(8);}}}
