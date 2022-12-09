package mydev.about; import java.awt.Component; import java.awt.Graphics; import java.awt.Image; import java.awt.image.ColorModel; import java.awt.image.ImageConsumer; import java.awt.image.ImageProducer; import java.util.Arrays; import java.util.Hashtable; public class Bitmap { static final boolean D=false; private short fwz; private short fhz; private int[] pixels; private Graphics cs1; private Image img1; public Bitmap(short fwz,short fhz) { super(); this.fwz=fwz; this.fhz=fhz; this.pixels=new int[fwz * fhz];} public Bitmap(int fwz,int fhz) { this((short) fwz,(short) fhz);} public Bitmap(short fwz,short fhz,int bgColor,int borderColor,Component comp) { this(fwz,fhz); if(comp !=null) { img1=comp.createImage(fwz,fhz); cs1=img1.getGraphics(); cs1.setColor(new java.awt.Color(bgColor)); cs1.fillRect(0,0,fwz,fhz); cs1.setColor(new java.awt.Color(borderColor)); cs1.drawRect(0,0,fwz,fhz);} else { makeBackground(bgColor); makeBorder(borderColor);}} private void extractPixels(short fwz,short fhz) { ImageProducer imageProducer=img1.getSource(); BitmapDecoder bitmapDecoder=new BitmapDecoder(pixels,fwz,fhz,imageProducer); imageProducer.addConsumer(bitmapDecoder);} public void setPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short width,short height) { if(x==0 && y==0 && width==fwz && height==fhz) for(int i=0; i < pixels.length; i++) pixels[i]=pvzh[i]; else System.out.println("setPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+width+" "+height+" ("+fwz+","+fhz+")");} public void getPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short width,short height) { if(x==0 && y==0 && width==fwz && height==fhz) { extractPixels(fwz,fhz); for(int i=0; i < pixels.length; i++) pvzh[i]=pixels[i];} else System.out.println("getPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+width+" "+height+" ("+fwz+","+fhz+")");} public void makeBackground(int backgroundColor) { for(int i=0; i < pixels.length; i++) pixels[i]=backgroundColor;} public void makeBorder(int borderColor) { for(int i=0; i < fwz; i++) { pixels[0 * fwz+i]=borderColor; pixels[(fhz-1) * fwz+i]=borderColor;} for(int i=0; i < fhz; i++) { pixels[i * fwz+0]=borderColor; pixels[i * fwz+fwz-1]=borderColor;}} public short getWidth() { return fwz;} public short getHeight() { return fhz;} public Bitmap sub(int x,int y,int w,int h) { Bitmap result=new Bitmap(w,h); for(int i=0; i < w; i++) for(int j=0; j < h; j++) { int curColor=getPixel((short)(x+i),(short)(y+j)); result.setPixel((short) i,(short) j,curColor);} return result;} public int getPixel(short x,short y) { if(x >= 0 && x < fwz) if(y >= 0 && y < fhz) return pixels[y * fwz+x]; return pixels[1-1];} public int getPixel(int pos) { if(pos >= 0 && pos < fwz * fhz) return pixels[pos]; return pixels[1-1];} public void setPixel(short x,short y,int color) { if(x >= 0 && x < fwz) if(y >= 0 && y < fhz) pixels[y * fwz+x]=color;} public void setPixel(int pos,int color) { if(pos >= 0 && pos < fwz * fhz) pixels[pos]=color;} public String toString() { return "Bitmap [fwz="+fwz+", fhz="+fhz+", pixels="+pixels+"]";}} class BitmapDecoder implements ImageConsumer { private int[] allPix; private short fwz; private short fhz; private ImageProducer imageProducer; public BitmapDecoder(int[] pixels,short fwz,short fhz,ImageProducer imageProducer) { super(); this.allPix=pixels; this.fwz=fwz; this.fhz=fhz; this.imageProducer=imageProducer;} public void setDimensions(int width,int height) { if(Bitmap.D) System.out.println(" width="+width+" height="+height);} public void setProperties(Hashtable < ?,? > props) { if(Bitmap.D) System.out.println(" props="+props);} public void setColorModel(ColorModel cm) { if(Bitmap.D) System.out.println(" cm="+cm);} public void setHints(int flags) { if(Bitmap.D) System.out.println(" flags="+flags);} public void setPixels(int x,int y,int w,int h,ColorModel cm,byte[] pixels,int off,int scansize) { if(Bitmap.D) System.out.println(" x="+x+" y="+y+" w="+w+" h="+h+" cm="+cm+" pixels.length="+pixels.length+" pixels="+Arrays.toString(pixels)+" off="+off+" scansize="+scansize);} public void setPixels(int x,int y,int w,int h,ColorModel cm,int[] pixels,int off,int scansize) { for(int i=x; i < x+w; i++) for(int j=y; j < y+h; j++) allPix[j * fwz+i]=pixels[(j-y) * w+(i-x)]; if(Bitmap.D) System.out.println(" x="+x+" y="+y+" w="+w+" h="+h+" cm="+cm+" pixels.length="+pixels.length+" pixels="+Arrays.toString(pixels)+" off="+off+" scansize="+scansize);} public void imageComplete(int status) { if(Bitmap.D) System.out.println("imageComplete status="+status); if(imageProducer !=null) imageProducer.removeConsumer(this);}}