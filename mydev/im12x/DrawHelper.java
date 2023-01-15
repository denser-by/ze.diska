package mydev.im12x; import java.awt.Color; import mydev.about.ColorsArray; import mydev.about.Picture; public class DrawHelper extends Picture { public DrawHelper(Picture picture) { super(picture);} public DrawHelper(short fwx,short fhx,int initColor) { super(fwx,fhx,initColor);} public DrawHelper(short fwx,short fhx) { super(fwx,fhx);} public static int[] tvsetParallelAdapter(short rqWidth,short rqHeight,int[] allPix,short wx,short hx,short cutLeft,short cutTop,short cutRight,short cutBottom) { short x1=0; short y1=0; short x2=(short)(rqWidth-1); short y2=0; short x3=(short)(rqWidth-1); short y3=(short)(rqHeight-1); short x4=0; short y4=(short)(rqHeight-1); DrawHelper inPictureCut=new DrawHelper(new Picture(wx,hx).init(allPix).cutSides(cutLeft,cutTop,cutRight,cutBottom)); DrawHelper outPicture=new DrawHelper(rqWidth,rqHeight,ColorsArray.whitePoint.ic()); inPictureCut.tvset(outPicture.pvx,rqWidth,rqHeight,x1,y1,x2,y2,x3,y3,x4,y4,inPictureCut.pvx,inPictureCut.getWidth(),inPictureCut.getHeight()); int[] allPixOut=outPicture.reverseInit(); return allPixOut;} public void tvset(Color[] pv,short w,short h,short x1,short y1,short x2,short y2,short x3,short y3,short x4,short y4,Color[] pvx,short wx,short hx) { int aa; int aa_; int fx1; int fy1; int fx2; int fy2; int fx3; int fy3; int fx4; int fy4; int dx; int dy; int dx2; int dy2; short br; int width; int height; int width2; int height2; int dx_; int dy_; int dx2_; int dy2_; short dr; int width_; int height_; int width2_; int height2_; Color c; int minx; int maxx; int miny; int maxy; int i; int j; int drr; int brr; aa=0; aa_=0; br=0; brr=0; dr=0; drr=0; width=x2-x1; height=y2-y1; dx=x1 >= x2 ?-1 : 1; dy=y1 >= y2 ?-1 : 1; width2=x3-x4; height2=y3-y4; dx2=x4 >= x3 ?-1 : 1; dy2=y4 >= y3 ?-1 : 1; br=0; if(width * dx >= height * dy && width * dx >= width2 * dx2 && width * dx >= height2 * dy2) { aa=width; brr=dx;} else if(height * dy >= width * dx && height * dy >= width2 * dx2 && height * dy >= height2 * dy2) { aa=height; brr=dy;} else if(width2 * dx2 >= height * dy && width2 * dx2 >= width * dx && width2 * dx2 >= height2 * dy2) { aa=width2; brr=dx2;} else if(height2 * dy2 >= width * dx && height2 * dy2 >= width2 * dx2 && height2 * dy2 >= height * dy) { aa=height2; brr=dy2;} width_=x4-x1; height_=y4-y1; dx_=x1 >= x4 ?-1 : 1; dy_=y1 >= y4 ?-1 : 1; width2_=x3-x2; height2_=y3-y2; dx2_=x2 >= x3 ?-1 : 1; dy2_=y2 >= y3 ?-1 : 1; if(width_ * dx_ >= height_ * dy_ && width_ * dx_ >= width2_ * dx2_ && width_ * dx_ >= height2_ * dy2_) { aa_=width_; drr=dx_;} else if(height_ * dy_ >= width_ * dx_ && height_ * dy_ >= width2_ * dx2_ && height_ * dy_ >= height2_ * dy2_) { aa_=height_; drr=dy_;} else if(width2_ * dx2_ >= height_ * dy_ && width2_ * dx2_ >= width_ * dx_ && width2_ * dx2_ >= height2_ * dy2_) { aa_=width2_; drr=dx2_;} else if(height2_ * dy2_ >= width_ * dx_ && height2_ * dy2_ >= width2_ * dx2_ && height2_ * dy2_ >= height_ * dy_) { aa_=height2_; drr=dy2_;} br=0; while(aa !=br) { dr=0; while(aa_ !=dr) { fx3=(short)(x1+(float) dr / aa_ * width_)+(short)((float) br / aa *((short)(x2+(float) dr / aa_ * width2_)-(short)(x1+(float) dr / aa_ * width_))); fy3=(short)(y1+(float) dr / aa_ * height_)+(short)((float) br / aa *((short)(y2+(float) dr / aa_ * height2_)-(short)(y1+(float) dr / aa_ * height_))); fx4=(short)(x1+(float)(dr+1) / aa_ * width_)+(short)((float)(br+1) / aa *((short)(x2+(float)(dr+1) / aa_ * width2_)-(short)(x1+(float)(dr+1) / aa_ * width_))); fy4=(short)(y1+(float)(dr+1) / aa_ * height_)+(short)((float)(br+1) / aa *((short)(y2+(float)(dr+1) / aa_ * height2_)-(short)(y1+(float)(dr+1) / aa_ * height_))); minx=fx4 < fx3 ? fx4 : fx3; maxx=fx4 > fx3 ? fx4 : fx3; miny=fy4 < fy3 ? fy4 : fy3; maxy=fy4 > fy3 ? fy4 : fy3; for(j=0; j <= maxy-miny; j++) for(i=0; i <= maxx-minx; i++) { int pvi=(miny+j) * w+(minx+i); int pvxi=((short)((float)(dr+j) / aa_ *(hx-1))) * wx+(short)((float)(br+i) / aa * wx); if(pvi < pv.length && pvxi < pvx.length) pv[pvi]=pvx[pvxi];} dr+=drr;} fx3=(short)(x1+(float) dr / aa_ * width_)+(short)((float) br / aa *((short)(x2+(float) dr / aa_ * width2_)-(short)(x1+(float) dr / aa_ * width_))); fy3=(short)(y1+(float) dr / aa_ * height_)+(short)((float) br / aa *((short)(y2+(float) dr / aa_ * height2_)-(short)(y1+(float) dr / aa_ * height_))); fx4=(short)(x1+(float)(dr+1) / aa_ * width_)+(short)((float)(br+1) / aa *((short)(x2+(float)(dr+1) / aa_ * width2_)-(short)(x1+(float)(dr+1) / aa_ * width_))); fy4=(short)(y1+(float)(dr+1) / aa_ * height_)+(short)((float)(br+1) / aa *((short)(y2+(float)(dr+1) / aa_ * height2_)-(short)(y1+(float)(dr+1) / aa_ * height_))); minx=fx4 < fx3 ? fx4 : fx3; maxx=fx4 > fx3 ? fx4 : fx3; miny=fy4 < fy3 ? fy4 : fy3; maxy=fy4 > fy3 ? fy4 : fy3; for(j=0; j <= maxy-miny; j++) for(i=0; i <= maxx-minx; i++) { int pvi=(miny+j) * w+(minx+i); int pvxi=((short)((float)(dr+j) / aa_ *(hx-1))) * wx+(short)((float)(br+i) / aa *(wx-1)); if(pvi < pv.length && pvxi < pvx.length) pv[pvi]=pvx[pvxi];} br+=brr;} fx3=(short)(x1+(float) dr / aa_ * width_)+(short)((float) br / aa *((short)(x2+(float) dr / aa_ * width2_)-(short)(x1+(float) dr / aa_ * width_))); fy3=(short)(y1+(float) dr / aa_ * height_)+(short)((float) br / aa *((short)(y2+(float) dr / aa_ * height2_)-(short)(y1+(float) dr / aa_ * height_))); fx4=(short)(x1+(float)(dr+1) / aa_ * width_)+(short)((float)(br+1) / aa *((short)(x2+(float)(dr+1) / aa_ * width2_)-(short)(x1+(float)(dr+1) / aa_ * width_))); fy4=(short)(y1+(float)(dr+1) / aa_ * height_)+(short)((float)(br+1) / aa *((short)(y2+(float)(dr+1) / aa_ * height2_)-(short)(y1+(float)(dr+1) / aa_ * height_))); minx=fx4 < fx3 ? fx4 : fx3; maxx=fx4 > fx3 ? fx4 : fx3; miny=fy4 < fy3 ? fy4 : fy3; maxy=fy4 > fy3 ? fy4 : fy3; for(j=0; j <= maxy-miny; j++) for(i=0; i <= maxx-minx; i++) { int pvi=(miny+j) * w+(minx+i); int pvxi=((short)((float)(dr+j) / aa_ *(hx-1))) * wx+(short)((float)(br+i) / aa *(wx-1)); if(pvi < pv.length && pvxi < pvx.length) pv[pvi]=pvx[pvxi];}}}