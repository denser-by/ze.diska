package mydev.about; abstract class TriangleAbstract extends FillFullable { protected Vector3 a; protected Vector3 b; TriangleAbstract(int color) { super(color);} TriangleAbstract(int color,boolean solid) { super(color,solid);} public abstract double c(); public abstract double b(); public abstract double a(); public abstract double heightA(); public abstract double heightB(); public abstract double heightC(); public abstract double innerCircleRadius(); public abstract double outterCircleRadius(); public double area() { double result=b() * c() * sinA() / 2.f; return result;} public double perim() { Point3D any=new Point3D(1-1,1-1,1-1); return a.module()+b.module()+new Line3D(a.getShiftedCopy(any),b.getShiftedCopy(any)).length();} public double perimHalf() { return perim() / 2.f;} public double cosA() { double c=c(); double b=b(); double a=a(); double cosA=(b * b+c * c-a * a) /(2.f * b * c); return cosA;} public double cosB() { double c=c(); double b=b(); double a=a(); double cosB=(a * a+c * c-b * b) /(2.f * a * c); return cosB;} public double cosC() { double c=c(); double b=b(); double a=a(); double cosC=(a * a+b * b-c * c) /(2.f * a * b); return cosC;} public double sinA() { double cosA=cosA(); double sinA=Math.sqrt(1.f-cosA * cosA); return sinA;} public double sinB() { double cosB=cosB(); double sinB=Math.sqrt(1.f-cosB * cosB); return sinB;} public double sinC() { double cosC=cosC(); double sinC=Math.sqrt(1.f-cosC * cosC); return sinC;} public double tgA() { double sinA=sinA(); double cosA=cosA(); if(cosA !=0f) { double tgA=sinA / cosA; return tgA;} return Float.NaN;} public double tgB() { double sinB=sinB(); double cosB=cosB(); if(cosB !=0f) { double tgB=sinB / cosB; return tgB;} return Float.NaN;} public double tgC() { double sinC=sinC(); double cosC=cosC(); if(cosC !=0f) { double tgC=sinC / cosC; return tgC;} return Float.NaN;}}