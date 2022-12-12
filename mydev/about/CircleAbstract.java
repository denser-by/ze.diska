package mydev.about; abstract class CircleAbstract extends FillFullable { protected int r; CircleAbstract(int r,int color) { this(r,color,false);} CircleAbstract(int r,int color,boolean solid) { super(color,solid); this.r=r;} CircleAbstract(float r,int color) { this((int)(r+0.5f),color,false);} CircleAbstract(float r,int color,boolean solid) { this((int)(r+0.5f),color,solid);} CircleAbstract(double r,int color) { this((float) r,color,false);} CircleAbstract(double r,int color,boolean solid) { this((float) r,color,solid);} CircleAbstract(short r,int color) { this((int) r,color,false);} CircleAbstract(short r,int color,boolean solid) { this((int) r,color,solid);} public int getR() { return r;} public void setR(int r) { this.r=r;} public void setR(float r) { this.r=(int)(r+0.5f);} public void setR(double r) { this.r=(int)(r+0.5f);} public void setR(short r) { this.r=(int) r;} public double area() { double result=Math.PI * r * r; return result;}}