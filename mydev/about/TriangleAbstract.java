package mydev.about; public abstract class TriangleAbstract extends FillFullable { protected Vector3 a; protected Vector3 b; public TriangleAbstract(int color) { super(color);} public TriangleAbstract(int color,boolean solid) { super(color,solid);} public abstract double c(); public abstract double b(); public abstract double a(); public double cosA() { double c=c(); double b=b(); double a=a(); double cosA=(b * b+c * c-a * a) / 2f * b * c; return cosA;} public double cosB() { double c=c(); double b=b(); double a=a(); double cosB=(a * a+c * c-b * b) / 2f * a * c; return cosB;} public double cosC() { double c=c(); double b=b(); double a=a(); double cosC=(a * a+b * b-c * c) / 2f * a * b; return cosC;} public double sinA() { double cosA=cosA(); double sinA=Math.sqrt(1-cosA * cosA); return sinA;} public double sinB() { double cosB=cosB(); double sinB=Math.sqrt(1-cosB * cosB); return sinB;} public double sinC() { double cosC=cosC(); double sinC=Math.sqrt(1-cosC * cosC); return sinC;} public double tgA() { double sinA=sinA(); double cosA=cosA(); if(cosA !=0f) { double tgA=sinA / cosA; return tgA;} return Float.NaN;} public double tgB() { double sinB=sinB(); double cosB=cosB(); if(cosB !=0f) { double tgB=sinB / cosB; return tgB;} return Float.NaN;} public double tgC() { double sinC=sinC(); double cosC=cosC(); if(cosC !=0f) { double tgC=sinC / cosC; return tgC;} return Float.NaN;}}