package mydev.vutils;
public class Spiska {
private Spiska nehst;
private Object item;
public Spiska() {
 this.nehst = null;
 this.item = null;
 }
private Spiska(Object nev, Spiska sled) {
 this.nehst = sled;
 this.item = nev;
 }
public void append(Object nev) {
Spiska tmp = nehst;
if(tmp != null)
{
while(tmp.nehst != null) tmp = tmp.nehst;
tmp.nehst = new Spiska(nev, tmp.nehst);
}
 else nehst = new Spiska(nev, tmp);
}
public boolean remove(Object obj) {
Spiska tmp = this;
boolean dd = false;
while(!dd && tmp.nehst != null) {
if(tmp.nehst.item != null && tmp.nehst.item.equals(obj))
{
tmp.nehst = tmp.nehst.nehst;
dd=!dd;
}
 else tmp = tmp.nehst;
}
return dd;
}
public void insert(long index, Object nev) {
Spiska tmp = this;
for(int i = 0; i <= index; i++)
{
if(tmp.nehst == null)
 tmp.nehst = new Spiska();
tmp = tmp.nehst;
if(i == index)
 tmp.item = nev;
}
}
public Object at(long index) {
Object result = null;
Spiska tmp = this;
for(int i = 0; i <= index; i++)
{
if(tmp.nehst == null)
 tmp.nehst = new Spiska();
tmp = tmp.nehst;
if(i == index)
 result = tmp.item;
}
return result;
}
public long size() {
long size = 0L;
Spiska tmp = nehst;
if(tmp != null)
{
 size++;
 while(tmp.nehst != null) {
 size++;
 tmp = tmp.nehst;
 }
 }
return size;
}
}