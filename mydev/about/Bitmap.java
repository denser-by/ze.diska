package mydev.about; import java.awt.Component; import java.awt.Graphics; import java.awt.Image; import java.awt.image.ColorModel; import java.awt.image.ImageConsumer; import java.awt.image.ImageProducer; import java.util.Hashtable; public class Bitmap { static final boolean D=false; private int[] pixels; private short width; private short height; private Graphics ics; private Image image; public Bitmap(short w,short h) { super(); this.width=w; this.height=h; this.pixels=new int[width * height];} public Bitmap(int w,int h) { this((short) w,(short) h);} public Bitmap(short w,short h,int bgColor,int borderColor,Component comp) { this(w,h); if(comp !=null) { image=comp.createImage(width,height); ics=image.getGraphics(); ics.setColor(new java.awt.Color(bgColor)); ics.fillRect(0,0,width,height); ics.setColor(new java.awt.Color(borderColor)); ics.drawRect(0,0,width,height);} else { makeBackground(bgColor); makeBorder(borderColor);}} private void extractPixels(short w,short h) { ImageProducer imageProducer=image.getSource(); BitmapDecoder bitmapDecoder=new BitmapDecoder(pixels,w,h,imageProducer); imageProducer.addConsumer(bitmapDecoder);} public void setPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short w,short h) { if(x==0 && y==0 && width==w && height==h) for(int i=0; i < pixels.length; i++) pixels[i]=pvzh[i]; else System.out.println("setPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+w+" "+h+" ("+width+","+height+")");} public void getPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short w,short h) { if(x==0 && y==0 && width==w && height==h) { extractPixels(width,height); for(int i=0; i < pixels.length; i++) pvzh[i]=pixels[i];} else System.out.println("getPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+w+" "+h+" ("+width+","+height+")");} public void makeBackground(int backgroundColor) { for(int i=0; i < pixels.length; i++) pixels[i]=backgroundColor;} public void makeBorder(int borderColor) { for(int i=0; i < width; i++) { pixels[0 * width+i]=borderColor; pixels[(height-1) * width+i]=borderColor;} for(int i=0; i < height; i++) { pixels[i * width+0]=borderColor; pixels[i * width+width-1]=borderColor;}} public short getWidth() { return width;} public short getHeight() { return height;} public Bitmap sub(int x,int y,int w,int h) { Bitmap result=new Bitmap(w,h); for(int i=0; i < w; i++) for(int j=0; j < h; j++) { int curColor=getPixel((short)(x+i),(short)(y+j)); result.setPixel((short) i,(short) j,curColor);} return result;} public int getPixel(short x,short y) { if(x >= 0 && x < width && y >= 0 && y < height) return pixels[y * width+x]; return pixels[1-1];} public int getPixel(int pos) { if(pos >= 0 && pos < width * height) return pixels[pos]; return pixels[1-1];} public void setPixel(short x,short y,int color) { if(x >= 0 && x < width && y >= 0 && y < height) pixels[y * width+x]=color;} public void setPixel(int pos,int color) { if(pos >= 0 && pos < width * height) pixels[pos]=color;} public String toString() { return "Bitmap [width="+width+", height="+height+", pixels="+pixels+"]";}} class BitmapDecoder implements ImageConsumer { private int[] allPix; private short width; private short height; private ImageProducer imageProducer; public BitmapDecoder(int[] pixels,short width,short height,ImageProducer imageProducer) { super(); this.allPix=pixels; this.width=width; this.height=height; this.imageProducer=imageProducer;} public void setDimensions(int width,int height) { if(Bitmap.D) System.out.println(" width="+width+" height="+height);} public void setProperties(Hashtable props) { if(Bitmap.D) System.out.println(" props="+props);} public void setColorModel(ColorModel cm) { if(Bitmap.D) System.out.println(" cm="+cm);} public void setHints(int flags) { if(Bitmap.D) System.out.println(" flags="+flags);} public void setPixels(int x,int y,int w,int h,ColorModel cm,byte[] pixels,int off,int scansize) { if(Bitmap.D) System.out.println(" x="+x+" y="+y+" w="+w+" h="+h+" cm="+cm+" pixels.length="+pixels.length+" pixels="+pixels+" off="+off+" scansize="+scansize);} public void setPixels(int x,int y,int w,int h,ColorModel cm,int[] pixels,int off,int scansize) { for(int i=x; i < x+w; i++) for(int j=y; j < y+h; j++) allPix[j * width+i]=pixels[(j-y) * w+(i-x)]; if(Bitmap.D) System.out.println(" x="+x+" y="+y+" w="+w+" h="+h+" cm="+cm+" pixels.length="+pixels.length+" pixels="+pixels+" off="+off+" scansize="+scansize);} public short getWidth() { return width;} public short getHeight() { return height;} public void imageComplete(int status) { if(Bitmap.D) System.out.println("imageComplete status="+status); if(imageProducer !=null) imageProducer.removeConsumer(this);}}