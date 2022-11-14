package mydev.about; import java.awt.Color; import mydev.vutils.Queue; public class Matrix2D implements Pencil { final int SPS=3; private int width; private int height; private int x; private int y; private int[] colors; private Queue objects; protected boolean needsUpdate; public Matrix2D(int width,int height,int x,int y) { this.width=width; this.height=height; this.x=x; this.y=y; this.colors=new int[width * height]; this.objects=new Queue(); this.needsUpdate=true;} public void toPen(Canvas ics,Paint pn) { if(needsUpdate) { Object[] queueRecordsArray=objects.getQueueRecordsArray(); for(int i=0; i < queueRecordsArray.length; i++) { Colorfull cf=(Colorfull) queueRecordsArray[i]; if(cf instanceof Line2D) { Line2D o=(Line2D) cf; line(colors,(short) width,(short) height,o.getP1().getX(),o.getP1().getY(),o.getP2().getX(),o.getP2().getY(),o.getColor());} else if(cf instanceof Point2D) { Point2D p=(Point2D) cf; point(colors,(short) width,(short) height,p.getX(),p.getY(),p.getColor());} else if(cf instanceof Triangle2D) { Triangle2D tri=(Triangle2D) cf; line(colors,(short) width,(short) height,tri.getP1().getX(),tri.getP1().getY(),tri.getP2().getX(),tri.getP2().getY(),tri.getColor()); line(colors,(short) width,(short) height,tri.getP2().getX(),tri.getP2().getY(),tri.getP3().getX(),tri.getP3().getY(),tri.getColor()); line(colors,(short) width,(short) height,tri.getP3().getX(),tri.getP3().getY(),tri.getP1().getX(),tri.getP1().getY(),tri.getColor());} else if(cf instanceof Circle2D) { Circle2D cr=(Circle2D) cf; circle(colors,(short) width,(short) height,cr.getCenter().getX(),cr.getCenter().getY(),(short) cr.getR(),cr.getColor(),cr.getFill());} else if(cf instanceof Clock2DTextHours2D) { Clock2DTextHours2D h=(Clock2DTextHours2D) cf; h.update(pn); hours(colors,(short) width,(short) height,h.pvzh,h.fwz,h.fhz,h.ast,h.c.x,h.c.y,h.r);} else if(cf instanceof Clock2DTextMinutes2D) { Clock2DTextMinutes2D m=(Clock2DTextMinutes2D) cf; m.update(pn); minutes(colors,(short) width,(short) height,m.pvzh,m.fwz,m.fhz,m.ast,m.c.x,m.c.y,m.r);} else if(cf instanceof Clock2DTextSeconds2D) { Clock2DTextSeconds2D s=(Clock2DTextSeconds2D) cf; s.update(pn); seconds(colors,(short) width,(short) height,s.pvzh,s.fwz,s.fhz,s.ast,s.c.x,s.c.y,s.r);} else if(cf instanceof Conture2D) { Conture2D path=(Conture2D) cf; sidePath(path);} else if(cf instanceof Pticca3D) { Pticca3D pti=(Pticca3D) cf; pticca(colors,(short) width,(short) height,pti.pvzh,pti.fwz,pti.fhz,pti.a.x,pti.a.y,pti.b.x,pti.b.y,pti.c.x,pti.c.y,pti.d.x,pti.d.y);}} needsUpdate=false;} ics.drawBitmap(colors,0,width,x,y,width,height,false,pn);} private void sidePath(Conture2D path) { Point2D a=null; Point2D b; Point2D first=null; Object[] queueRecordsArray=path.points.getQueueRecordsArray(); for(int i=0; i < queueRecordsArray.length; i++) { if(first==null) { first=(Point2D) queueRecordsArray[i]; a=first;} else { b=(Point2D) queueRecordsArray[i]; line(colors,(short) width,(short) height,a.getX(),a.getY(),b.getX(),b.getY(),path.getColor()); a=b;}} b=first; if(a !=null && b !=null) line(colors,(short) width,(short) height,a.getX(),a.getY(),b.getX(),b.getY(),path.getColor());} public void represent(Matrix2D matrix) { matrix.addMatrix(this,0,0);} public void addMatrix(Matrix2D matrix,int xStart,int yStart) { for(int i=0; i < matrix.width; i++) for(int j=0; j < matrix.height; j++) {}} public void addColorObj(Colorfull cf) { objects.enqueueQueueRecord(cf); needsUpdate=true;} void point(int[] pv,short w,short h,int x1,int y1,int c) { if(x1 >= 0 && x1 < w && y1 >= 0 && y1 < h) { pv[y1 * w+x1]=c;}} public static PairXY[] line(int x1,int y1,int x2,int y2) { int dx; int dy; short br; int width; int height; Queue result=new Queue(); if(1+1 > 1) { width=x2-x1; height=y2-y1; dx=x1 >= x2 ?-1 : 1; dy=y1 >= y2 ?-1 : 1; br=0; if(dx * width > dy * height) { while(width !=br) { result.enqueueQueueRecord(new PairXY((short)(x1+br),(short)(y1+(float) br / width * height))); br+=dx;} result.enqueueQueueRecord(new PairXY((short)(x1+br),(short)(y1+(float) br / width * height)));} else { while(height !=br) { result.enqueueQueueRecord(new PairXY((short)(x1+(float) br / height * width),(short)(y1+br))); br+=dy;} result.enqueueQueueRecord(new PairXY((short)(x1+(float) br / height * width),(short)(y1+br)));}} Object[] queueRecordsArray=result.getQueueRecordsArray(); PairXY[] resArr=new PairXY[queueRecordsArray.length]; for(int i=0; i < resArr.length; i++) resArr[i++]=(PairXY) queueRecordsArray[i]; return resArr;} void tvset(int[] pv,short w,short h,short x1,short y1,short x2,short y2,short x3,short y3,short x4,short y4,int[] pvx,short wx,short hx) { int aa; int aa_; int fx1; int fy1; int fx2; int fy2; int fx3; int fy3; int fx4; int fy4; int dx; int dy; int dx2; int dy2; short br; int width; int height; int width2; int height2; int dx_; int dy_; int dx2_; int dy2_; short dr; int width_; int height_; int width2_; int height2_; Color c; int minx; int maxx; int miny; int maxy; int i; int j; int drr; int brr; aa=0; aa_=0; br=0; brr=0; dr=0; drr=0; width=x2-x1; height=y2-y1; dx=x1 >= x2 ?-1 : 1; dy=y1 >= y2 ?-1 : 1; width2=x3-x4; height2=y3-y4; dx2=x4 >= x3 ?-1 : 1; dy2=y4 >= y3 ?-1 : 1; br=0; if(width * dx >= height * dy && width * dx >= width2 * dx2 && width * dx >= height2 * dy2) { aa=width; brr=dx;} else if(height * dy >= width * dx && height * dy >= width2 * dx2 && height * dy >= height2 * dy2) { aa=height; brr=dy;} else if(width2 * dx2 >= height * dy && width2 * dx2 >= width * dx && width2 * dx2 >= height2 * dy2) { aa=width2; brr=dx2;} else if(height2 * dy2 >= width * dx && height2 * dy2 >= width2 * dx2 && height2 * dy2 >= height * dy) { aa=height2; brr=dy2;} width_=x4-x1; height_=y4-y1; dx_=x1 >= x4 ?-1 : 1; dy_=y1 >= y4 ?-1 : 1; width2_=x3-x2; height2_=y3-y2; dx2_=x2 >= x3 ?-1 : 1; dy2_=y2 >= y3 ?-1 : 1; if(width_ * dx_ >= height_ * dy_ && width_ * dx_ >= width2_ * dx2_ && width_ * dx_ >= height2_ * dy2_) { aa_=width_; drr=dx_;} else if(height_ * dy_ >= width_ * dx_ && height_ * dy_ >= width2_ * dx2_ && height_ * dy_ >= height2_ * dy2_) { aa_=height_; drr=dy_;} else if(width2_ * dx2_ >= height_ * dy_ && width2_ * dx2_ >= width_ * dx_ && width2_ * dx2_ >= height2_ * dy2_) { aa_=width2_; drr=dx2_;} else if(height2_ * dy2_ >= width_ * dx_ && height2_ * dy2_ >= width2_ * dx2_ && height2_ * dy2_ >= height_ * dy_) { aa_=height2_; drr=dy2_;} br=0; while(aa !=br) { dr=0; while(aa_ !=dr) { fx3=(short)(x1+(float) dr / aa_ * width_)+(short)((float) br / aa *((short)(x2+(float) dr / aa_ * width2_)-(short)(x1+(float) dr / aa_ * width_))); fy3=(short)(y1+(float) dr / aa_ * height_)+(short)((float) br / aa *((short)(y2+(float) dr / aa_ * height2_)-(short)(y1+(float) dr / aa_ * height_))); fx4=(short)(x1+(float)(dr+1) / aa_ * width_)+(short)((float)(br+1) / aa *((short)(x2+(float)(dr+1) / aa_ * width2_)-(short)(x1+(float)(dr+1) / aa_ * width_))); fy4=(short)(y1+(float)(dr+1) / aa_ * height_)+(short)((float)(br+1) / aa *((short)(y2+(float)(dr+1) / aa_ * height2_)-(short)(y1+(float)(dr+1) / aa_ * height_))); minx=fx4 < fx3 ? fx4 : fx3; maxx=fx4 > fx3 ? fx4 : fx3; miny=fy4 < fy3 ? fy4 : fy3; maxy=fy4 > fy3 ? fy4 : fy3; for(j=0; j <= maxy-miny; j++) for(i=0; i <= maxx-minx; i++) { int pvi=(miny+j) * w+(minx+i); int pvxi=((short)((float)(dr+j) / aa_ *(hx-1))) * wx+(short)((float)(br+i) / aa * wx); if(pvi < pv.length && pvxi < pvx.length) pv[pvi]=pvx[pvxi];} dr+=drr;} fx3=(short)(x1+(float) dr / aa_ * width_)+(short)((float) br / aa *((short)(x2+(float) dr / aa_ * width2_)-(short)(x1+(float) dr / aa_ * width_))); fy3=(short)(y1+(float) dr / aa_ * height_)+(short)((float) br / aa *((short)(y2+(float) dr / aa_ * height2_)-(short)(y1+(float) dr / aa_ * height_))); fx4=(short)(x1+(float)(dr+1) / aa_ * width_)+(short)((float)(br+1) / aa *((short)(x2+(float)(dr+1) / aa_ * width2_)-(short)(x1+(float)(dr+1) / aa_ * width_))); fy4=(short)(y1+(float)(dr+1) / aa_ * height_)+(short)((float)(br+1) / aa *((short)(y2+(float)(dr+1) / aa_ * height2_)-(short)(y1+(float)(dr+1) / aa_ * height_))); minx=fx4 < fx3 ? fx4 : fx3; maxx=fx4 > fx3 ? fx4 : fx3; miny=fy4 < fy3 ? fy4 : fy3; maxy=fy4 > fy3 ? fy4 : fy3; for(j=0; j <= maxy-miny; j++) for(i=0; i <= maxx-minx; i++) { int pvi=(miny+j) * w+(minx+i); int pvxi=((short)((float)(dr+j) / aa_ *(hx-1))) * wx+(short)((float)(br+i) / aa *(wx-1)); if(pvi < pv.length && pvxi < pvx.length) pv[pvi]=pvx[pvxi];} br+=brr;} fx3=(short)(x1+(float) dr / aa_ * width_)+(short)((float) br / aa *((short)(x2+(float) dr / aa_ * width2_)-(short)(x1+(float) dr / aa_ * width_))); fy3=(short)(y1+(float) dr / aa_ * height_)+(short)((float) br / aa *((short)(y2+(float) dr / aa_ * height2_)-(short)(y1+(float) dr / aa_ * height_))); fx4=(short)(x1+(float)(dr+1) / aa_ * width_)+(short)((float)(br+1) / aa *((short)(x2+(float)(dr+1) / aa_ * width2_)-(short)(x1+(float)(dr+1) / aa_ * width_))); fy4=(short)(y1+(float)(dr+1) / aa_ * height_)+(short)((float)(br+1) / aa *((short)(y2+(float)(dr+1) / aa_ * height2_)-(short)(y1+(float)(dr+1) / aa_ * height_))); minx=fx4 < fx3 ? fx4 : fx3; maxx=fx4 > fx3 ? fx4 : fx3; miny=fy4 < fy3 ? fy4 : fy3; maxy=fy4 > fy3 ? fy4 : fy3; for(j=0; j <= maxy-miny; j++) for(i=0; i <= maxx-minx; i++) { int pvi=(miny+j) * w+(minx+i); int pvxi=((short)((float)(dr+j) / aa_ *(hx-1))) * wx+(short)((float)(br+i) / aa *(wx-1)); if(pvi < pv.length && pvxi < pvx.length) pv[pvi]=pvx[pvxi];}} void hours(int[] pv,short fw,short fh,int[] pvzh,short fwz,short fhz,Time ast,int cx,int cy,int r) { if(ast.hh % 12 > 6 && ast.hh % 12 <= 12) tvset(pv,fw,fh,(short)(cx+(r-20-SPS * SPS * SPS * 2) * Math.sin(ast.hh *(2 * 3.14f) / 12+3.14f / 29)),(short)(cy-(r-20-SPS * SPS * SPS * 2) * Math.cos(ast.hh *(2 * 3.14f) / 12+3.14f / 29)),(short) cx,(short) cy,(short) cx,(short) cy,(short)(cx+(r-20-SPS * SPS * SPS * 2) * Math.sin(ast.hh *(2 * 3.14f) / 12-3.14f / 29)),(short)(cy-(r-20-SPS * SPS * SPS * 2) * Math.cos(ast.hh *(2 * 3.14f) / 12-3.14f / 29)),pvzh,fwz,fhz); else tvset(pv,fw,fh,(short) cx,(short) cy,(short)(cx+(r-20-SPS * SPS * SPS * 2) * Math.sin(ast.hh *(2 * 3.14f) / 12-3.14f / 29)),(short)(cy-(r-20-SPS * SPS * SPS * 2) * Math.cos(ast.hh *(2 * 3.14f) / 12-3.14f / 29)),(short)(cx+(r-20-SPS * SPS * SPS * 2) * Math.sin(ast.hh *(2 * 3.14f) / 12+3.14f / 29)),(short)(cy-(r-20-SPS * SPS * SPS * 2) * Math.cos(ast.hh *(2 * 3.14f) / 12+3.14f / 29)),(short) cx,(short) cy,pvzh,fwz,fhz);} void minutes(int[] pv,short fw,short fh,int[] pvzm,short fwz,short fhz,Time ast,int cx,int cy,int r) { if(ast.mm > 30 && ast.mm <= 60) tvset(pv,fw,fh,(short)(cx+(r-10-SPS * SPS * SPS) * Math.sin((ast.mm+1) *(2 * 3.14f) / 60)),(short)(cy-(r-10-SPS * SPS * SPS) * Math.cos((ast.mm+1) *(2 * 3.14f) / 60)),(short) cx,(short) cy,(short) cx,(short) cy,(short)(cx+(r-10-SPS * SPS * SPS) * Math.sin((ast.mm-1) *(2 * 3.14f) / 60)),(short)(cy-(r-10-SPS * SPS * SPS) * Math.cos((ast.mm-1) *(2 * 3.14f) / 60)),pvzm,fwz,fhz); else tvset(pv,fw,fh,(short) cx,(short) cy,(short)(cx+(r-10-SPS * SPS * SPS) * Math.sin((ast.mm-1) *(2 * 3.14f) / 60)),(short)(cy-(r-10-SPS * SPS * SPS) * Math.cos((ast.mm-1) *(2 * 3.14f) / 60)),(short)(cx+(r-10-SPS * SPS * SPS) * Math.sin((ast.mm+1) *(2 * 3.14f) / 60)),(short)(cy-(r-10-SPS * SPS * SPS) * Math.cos((ast.mm+1) *(2 * 3.14f) / 60)),(short) cx,(short) cy,pvzm,fwz,fhz);} void seconds(int[] pv,short fw,short fh,int[] pvzs,short fwz,short fhz,Time ast,int cx,int cy,int r) { if(ast.ss > 30 && ast.ss <= 60) tvset(pv,fw,fh,(short)(cx+(r-SPS+SPS) * Math.sin((ast.ss+1) *(2 * 3.14f) / 60)),(short)(cy-(r-SPS+SPS) * Math.cos((ast.ss+1) *(2 * 3.14f) / 60)),(short) cx,(short) cy,(short) cx,(short) cy,(short)(cx+(r-SPS+SPS) * Math.sin((ast.ss-1) *(2 * 3.14f) / 60)),(short)(cy-(r-SPS+SPS) * Math.cos((ast.ss-1) *(2 * 3.14f) / 60)),pvzs,fwz,fhz); else tvset(pv,fw,fh,(short) cx,(short) cy,(short)(cx+(r-SPS+SPS) * Math.sin((ast.ss-1) *(2 * 3.14f) / 60)),(short)(cy-(r-SPS+SPS) * Math.cos((ast.ss-1) *(2 * 3.14f) / 60)),(short)(cx+(r-SPS+SPS) * Math.sin((ast.ss+1) *(2 * 3.14f) / 60)),(short)(cy-(r-SPS+SPS) * Math.cos((ast.ss+1) *(2 * 3.14f) / 60)),(short) cx,(short) cy,pvzs,fwz,fhz);} void pticca(int[] pv,short fw,short fh,int[] pvzs,short fwz,short fhz,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) { try { tvset(pv,fw,fh,(short) x1,(short) y1,(short) x2,(short) y2,(short) x3,(short) y3,(short) x4,(short) y4,pvzs,fwz,fhz);} catch(Throwable th) { th.printStackTrace();}} void circle(int[] pv,short fw,short fh,int x1,int y1,short r,int c1,boolean fill) { int i; short px; short py; short cx; short cy; px=-1; py=-1; for(i=0; i < 360; i++) if(fill) line(pv,fw,fh,x1,y1,(int)(x1+r * Math.sin(i *(2 * 3.14f) / 360)),(int)(y1-r * Math.cos(i *(2 * 3.14f) / 360)),c1); else { cx=(short)(x1+r * Math.sin(i *(2 * 3.14f) / 360)); cy=(short)(y1-r * Math.cos(i *(2 * 3.14f) / 360)); if(px > 0 && py > 0) line(pv,fw,fh,px,py,cx,cy,c1); px=cx; py=cy;} cx=(short)(x1+r * Math.sin(i *(2 * 3.14f) / 360)); cy=(short)(y1-r * Math.cos(i *(2 * 3.14f) / 360)); if(px > 0 && py > 0) line(pv,fw,fh,px,py,cx,cy,c1);} void line(int[] pv,short w,short h,int x1,int y1,int x2,int y2,int c) { int dx; int dy; short br; int width; int height; if(x1 >= 0 && x1 < w && x2 >= 0 && x2 < w && y1 >= 0 && y1 < h && y2 >= 0 && y2 < h) { width=x2-x1; height=y2-y1; dx=x1 >= x2 ?-1 : 1; dy=y1 >= y2 ?-1 : 1; br=0; if(dx * width > dy * height) { while(width !=br) { pv[((short)(y1+(float) br / width * height)) * w+(x1+br)]=c; br+=dx;} pv[((short)(y1+(float) br / width * height)) * w+(x1+br)]=c;} else { while(height !=br) { pv[(y1+br) * w+(short)(x1+(float) br / height * width)]=c; br+=dy;} pv[(y1+br) * w+(short)(x1+(float) br / height * width)]=c;}}} public void addPoint(Point2D point) { addColorObj(point);} public void addPoint(Point3D point) { addColorObj(point);} public void addLine(Line2D line) { addColorObj(line);} public void addLine(Line3D line) { addColorObj(line);} public void addTriangle(Triangle2D triangle) { addColorObj(triangle);} public void addTriangle(Triangle3D triangle) { addColorObj(triangle);} public void addRectangle(Rectangle2D rectangle) { addColorObj(rectangle);} public void addCube(Cube cube) { addColorObj(cube);} public void addCircle(Circle2D circle) { addColorObj(circle);} public void addSphere(Sphere sphere) { addColorObj(sphere);} public void addSector(Sector2D sector) { addColorObj(sector);} public void addText(Text2D text) { addColorObj(text);} public void addText(Clock2DTextHours2D textHours) { addColorObj(textHours);} public void addText(Clock2DTextMinutes2D textMinutes) { addColorObj(textMinutes);} public void addText(Clock2DTextSeconds2D textSeconds) { addColorObj(textSeconds);} public void addPath(Conture2D path) { addColorObj(path);} public void addPticca(Pticca3D pticc) { addColorObj(pticc);} public void addCircle3D(Circle3D circle3d) { addColorObj(circle3d);} public void addRectangle3D(Parallelepiped rectangle3d) { addColorObj(rectangle3d);} public void addPieChart(PieChart2D pieChart2D) { addColorObj(pieChart2D);} public void addPieChartLegend(PieChartLegend2D pieChartLegend2D) { addColorObj(pieChartLegend2D);} public void addScanLine(Colorfull scanLine) { addColorObj(scanLine);}}