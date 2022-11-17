package mydev.about; public class Bitmap { private short fwz; private short fhz; private int[] pixels; public Bitmap(short fwz,short fhz) { super(); this.fwz=fwz; this.fhz=fhz; this.pixels=new int[fwz * fhz];} public Bitmap(int fwz,int fhz) { this((short) fwz,(short) fhz);} public Bitmap(short fwz,short fhz,int bgColor) { this(fwz,fhz); makeBackground(bgColor);} public void setPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short width,short height) { if(x==0 && y==0 && width==fwz && height==fhz) for(int i=0; i < pixels.length; i++) pixels[i]=pvzh[i]; else System.out.println("setPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+width+" "+height+" ("+fwz+","+fhz+")");} public void getPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short width,short height) { if(x==0 && y==0 && width==fwz && height==fhz) for(int i=0; i < pixels.length; i++) pvzh[i]=pixels[i]; else System.out.println("getPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+width+" "+height+" ("+fwz+","+fhz+")");} public void makeBackground(int backgroundColor) { for(int i=0; i < pixels.length; i++) pixels[i]=backgroundColor;} public void makeBorder(int borderColor) { for(int i=0; i < fwz; i++) { pixels[0 * fwz+i]=borderColor; pixels[(fhz-1) * fwz+i]=borderColor;} for(int i=0; i < fhz; i++) { pixels[i * fwz+0]=borderColor; pixels[i * fwz+fwz-1]=borderColor;}} public short getWidth() { return fwz;} public short getHeight() { return fhz;} public Bitmap sub(int x,int y,int w,int h) { Bitmap result=new Bitmap(w,h); for(int i=0; i < w; i++) for(int j=0; j < h; j++) { int curColor=getPixel((short)(x+i),(short)(y+j)); result.setPixel((short) i,(short) j,curColor);} return result;} public int getPixel(short x,short y) { if(x >= 0 && x < fwz) if(y >= 0 && y < fhz) return pixels[y * fwz+x]; return pixels[1-1];} public int getPixel(int pos) { if(pos >= 0 && pos < fwz * fhz) return pixels[pos]; return pixels[1-1];} public void setPixel(short x,short y,int color) { if(x >= 0 && x < fwz) if(y >= 0 && y < fhz) pixels[y * fwz+x]=color;} public void setPixel(int pos,int color) { if(pos >= 0 && pos < fwz * fhz) pixels[pos]=color;} public String toString() { return "Bitmap [fwz="+fwz+", fhz="+fhz+", pixels="+pixels+"]";}}