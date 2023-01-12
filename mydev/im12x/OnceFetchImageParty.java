package mydev.im12x; public class OnceFetchImageParty extends FetchImageParty { private boolean hasNext; private int[] allPix; private int width; private int total; public OnceFetchImageParty(ImageParty imageParty,BackupResolution br) { super(imageParty); this.total=imageParty.getTotal(); this.width=imageParty.getScanSize(); this.hasNext=true; this.allPix=ref.getAllPix(); if(br.needsConversion()) { short newWidth=br.getNewWidth(width); short newHeight=br.getNewHeight(total / width); short cutLeft=br.getCutLeft(); short cutTop=br.getCutTop(); short cutRight=br.getCutRight(); short cutBottom=br.getCutBottom(); allPix=DrawHelper.tvsetParallelAdapter(newWidth,newHeight,allPix,(short) width,(short)(total / width),cutLeft,cutTop,cutRight,cutBottom); width=newWidth; total=newWidth * newHeight;}} public boolean hasNext() { return hasNext;} public int[] next() { int[] tmp=allPix; allPix=null; hasNext=false; return tmp;} public int getTotalLength() { return total;} public int getWidth() { return width;}}