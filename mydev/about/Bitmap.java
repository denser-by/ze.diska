package mydev.about; import java.awt.Component; import java.awt.Graphics; import java.awt.Image; import java.awt.image.ColorModel; import java.awt.image.DirectColorModel; import java.awt.image.ImageConsumer; import java.awt.image.ImageObserver; import java.awt.image.ImageProducer; import java.awt.image.MemoryImageSource; import java.util.Hashtable; public class Bitmap { int[] pixels; private short width; private short height; private Graphics ics; private Image image; public Bitmap(short w,short h) { super(); this.width=w; this.height=h; this.pixels=new int[width * height];} public Bitmap(int w,int h) { this((short) w,(short) h);} public Bitmap(short w,short h,int bgColor,int borderColor,Component comp) { this(w,h); if(comp !=null) { image=comp.createImage(width,height); ics=image.getGraphics(); ics.setColor(new java.awt.Color(bgColor)); ics.fillRect(0,0,width,height); ics.setColor(new java.awt.Color(borderColor)); ics.drawRect(0,0,width,height);} else { makeBackground(bgColor); makeBorder(borderColor);}} public Bitmap(int[] colors,short w,short h) { super(); this.width=w; this.height=h; this.pixels=colors;} public Image getImage(Component comp) { int bits=32; int redMask=16711680; int greenMask=65280; int blueMask=255; DirectColorModel dcm=new DirectColorModel(bits,redMask,greenMask,blueMask); int shift=0; int online=width; this.image=comp.createImage(new MemoryImageSource(width,height,dcm,pixels,shift,online)); return image;} private void extractPixels(short w,short h) { ImageProducer imageProducer=image.getSource(); BitmapDecoder bitmapDecoder=new BitmapDecoder(pixels,w,h,imageProducer); imageProducer.addConsumer(bitmapDecoder);} public static Bitmap create(Image img) { BitmapObserver bitmapObserver=new BitmapObserver(); short w=(short) img.getWidth(bitmapObserver); short h=(short) img.getHeight(bitmapObserver); Bitmap bitmap=new Bitmap(w,h); bitmap.image=img; bitmap.extractPixels(w,h); return bitmap;} public void setPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short w,short h) { if(x==0 && y==0 && width==w && height==h) for(int i=0; i < pixels.length; i++) pixels[i]=pvzh[i]; else System.out.println("setPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+w+" "+h+" ("+width+","+height+")");} public void getPixels(int[] pvzh,int shift,short totalWidth,int x,int y,short w,short h) { if(x==0 && y==0 && width==w && height==h) { extractPixels(width,height); for(int i=0; i < pixels.length; i++) pvzh[i]=pixels[i];} else System.out.println("getPixels() "+shift+" "+totalWidth+" "+x+" "+y+" "+w+" "+h+" ("+width+","+height+")");} public void makeBackground(int backgroundColor) { for(int i=0; i < pixels.length; i++) pixels[i]=backgroundColor;} public void makeBorder(int borderColor) { for(int i=0; i < width; i++) { pixels[0 * width+i]=borderColor; pixels[(height-1) * width+i]=borderColor;} for(int i=0; i < height; i++) { pixels[i * width+0]=borderColor; pixels[i * width+width-1]=borderColor;}} public short getWidth() { return width;} public short getHeight() { return height;} public Bitmap sub(int x,int y,int w,int h) { Bitmap result=new Bitmap(w,h); for(int i=0; i < w; i++) for(int j=0; j < h; j++) { int curColor=getPixel((short)(x+i),(short)(y+j)); result.setPixel((short) i,(short) j,curColor);} return result;} public int getPixel(short x,short y) { if(x >= 0 && x < width && y >= 0 && y < height) return pixels[y * width+x]; return pixels[1-1];} public int getPixel(int pos) { if(pos >= 0 && pos < width * height) return pixels[pos]; return pixels[1-1];} public void setPixel(short x,short y,int color) { if(x >= 0 && x < width && y >= 0 && y < height) pixels[y * width+x]=color;} public void setPixel(int pos,int color) { if(pos >= 0 && pos < width * height) pixels[pos]=color;} public String toString() { return "Bitmap [width="+width+", height="+height+", pixels="+pixels+"]";}} class BitmapObserver implements ImageObserver { public boolean imageUpdate(Image img,int infoflags,int x,int y,int width,int height) { return false;}} class BitmapDecoder implements ImageConsumer { private int[] allPix; private short width; private short height; private ImageProducer imageProducer; public BitmapDecoder(int[] pixels,short width,short height,ImageProducer imageProducer) { super(); this.allPix=pixels; this.width=width; this.height=height; this.imageProducer=imageProducer;} public void setDimensions(int width,int height) {} public void setProperties(Hashtable props) {} public void setColorModel(ColorModel cm) {} public void setHints(int flags) {} public void setPixels(int x,int y,int w,int h,ColorModel cm,byte[] pixels,int off,int scansize) {} public void setPixels(int x,int y,int w,int h,ColorModel cm,int[] pixels,int off,int scansize) { for(int i=x; i < x+w; i++) for(int j=y; j < y+h; j++) allPix[j * width+i]=pixels[(j-y) * w+(i-x)];} public short getWidth() { return width;} public short getHeight() { return height;} public void imageComplete(int status) { if(imageProducer !=null) imageProducer.removeConsumer(this);}}