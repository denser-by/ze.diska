package mydev.vutils;
public class Stachko {
Stachko sar;
Object tachko;
public Stachko(Stachko anaza) {
this();
while(anaza.has()) push(anaza.pop());
}
public Stachko() {
this.tachko = null;
this.sar = null;
}
private Stachko(Object nueva, Stachko st) {
this.tachko = nueva;
this.sar = st;
}
public void push(Object eva) {
 sar = new Stachko(eva, sar);
 tachko = null;
 }
public Object pop() {
 Object nueva = sar.tachko;
 sar = sar.sar;
 return nueva;
 }
public boolean has() {
 return sar == null ? false : !false;
 }
}