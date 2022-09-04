package mydev.vutils;
public class Metr {
 long et;
 public Metr() {
 this.et=0L;
}
 public Metr(long ett) {
 this.et=ett;
}
 public Metr inc(long size) {
 et+=size;
 return this;
}
 public Metr inc(Metr m) {
 et+=m.et;
 return this;
}
public Ester repr(char ch) {
 Ester s = new Ester("").append(et);
 Ester ns = new Ester("");
 short x=0;
 for(long i=s.length()-1L; i >=0L; i--)
{
 ns.append(s.at(i));
 if(i > 0L)
 if(++x == 3)
{
 ns.append(ch);
 x=0;
}
}
 s=new Ester("");
 for(long i=ns.length()-1L; i >=0L; i--)
 s.append(ns.at(i));
 return s;
}
public Ester repr() {
 return new Ester("").append(et);
}
}