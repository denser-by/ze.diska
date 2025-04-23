package mydev.vutils;
public class Tablichko {
final String KEY = "AA";
Karta zapisi;
long minx, miny, maxx, maxy;
boolean first;
public Tablichko() {
this.zapisi = new Karta();
this.first = true;
}
public void put(long x, long y, Object chto) {
if(first)
{
 minx = maxx = x;
 miny = maxy = y;
 first = !first;
 }
 else {
if(x < minx)
 minx = x;
 else if(x > maxx)
 maxx = x;
if(y < miny)
 miny = y;
 else if(y > maxy)
 maxy = y;
}
Karta zapis = (Karta)zapisi.get(KEY + y);
if(zapis == null)
{
 zapis = new Karta();
 zapisi.put(KEY + y, zapis);
 }
zapis.put(KEY + x, chto);
}
public Object get(long x, long y) {
Object result = null;
Karta zapis = (Karta)zapisi.get(KEY + y);
if(zapis != null)
 result = zapis.get(KEY + x);
return result;
}
public String[][] dayka(long x1, long y1, long x2, long y2) {
long shir = x2 - x1 + 1;
long viso = y2 - y1 + 1;
String[][] use = new String[(int)viso][(int)shir];
long i = y1;
while(i <= y2) {
long j = x1;
while(j <= x2) {
Object ob = get(j, i);
use[(int)(i-y1)][(int)(j-x1)] = (String)(ob == null ? "" : ob);
j++;
}
i++;
}
return use;
}
public String[][] dayka() {
 return dayka(minx, miny, maxx, maxy);
 }
public long miX() {
 return minx;
 }
public long maX() {
 return maxx;
 }
public long miY() {
 return miny;
 }
public long maY() {
 return maxy;
 }
}