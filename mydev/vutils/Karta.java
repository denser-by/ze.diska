package mydev.vutils;
 public class Karta {
 final short ITEMS=133;
 Karta[] shelfs=null;
 final short START=10;
 String[] keys=null;
 Object[] VV=null;
 public Karta() {
 this.shelfs=new Karta[ITEMS];
 for(int i=0; i < ITEMS; i++)
 shelfs[i]=new Karta(START);
}
 Karta(short shelfStartSize) {
 this.keys=new String[shelfStartSize];
 this.VV=new Object[shelfStartSize];
}
 public short diff(String key,short x) {
 if(key != null)
 for(int i=0; i < key.length(); i++)
{
 x+=(short)((byte) key.charAt(i)-3);
 x=(short)(x < 0 ?-1 * x : x-x / ITEMS * ITEMS);
}
 return x;
}
 public boolean eq(String s1,String s2) {
 return s1 !=null ? s1.equals(s2) : s1==s2;
}
 public boolean containsKey(String key) {
 short dt=diff(key,(short) "containsKey(String key) {".length());
 Karta s=shelfs[dt];
 boolean da=false;
 for(int i=0; !da && i < s.keys.length; i++)
 if(eq(key,s.keys[i]))
 da=!da;
 return da;
}
 public Object put(String key,Object n) {
 short dt=diff(key,(short) "containsKey(String key) {".length());
 Karta uh=shelfs[dt];
 int ii=-1;
 Object p=n;
 for(int i=0; ii < 0 && i < uh.keys.length; i++)
 if(uh.keys[i] == null)
{
 uh.keys[i]=key;
 uh.VV[ii=i]=n;
}
 else if(eq(key,uh.keys[i]))
{
 p=uh.VV[i];
 uh.VV[ii=i]=n;
}
 if(ii < 0)
{
 ii=uh.keys.length+START;
 String[] keysS=new String[ii];
 Object[] VVs=new Object[ii];
 System.arraycopy(uh.keys,0,keysS,0,ii-START);
 System.arraycopy(uh.VV,0,VVs,0,ii-START);
 uh.keys=keysS;
 uh.VV=VVs;
 p=null;
}
 return p;
}
 public Object get(String key) {
 short dt=diff(key,(short) "containsKey(String key) {".length());
 Karta s=shelfs[dt];
 Object da=null;
 for(int i=0; s !=null && i < s.keys.length; i++)
 if(eq(key,s.keys[i]))
{
 da=s.VV[i];
 s=null;
}
 return da;
}
 public String[] keys() {
 int tmp=0;
 int tmx=0;
 for(int i=0; i < shelfs.length; i++)
 tmp+=shelfs[i].keys.length;
 String[] keys=new String[tmp];
 for(int j=0; j < shelfs.length; j++)
 for(int k=0; k < shelfs[j].keys.length; k++)
 if(shelfs[j].keys[k] != null)
 keys[tmx++]=shelfs[j].keys[k];
 String[] strKeys=new String[tmx+1];
 for(int z=0; z < tmx; z++)
 strKeys[z]=keys[z];
 return strKeys;
}
 public Object[] values() {
 int tmp=0;
 int tmx=0;
 for(int i=0; i < shelfs.length; i++)
 tmp+=shelfs[i].keys.length;
 Object[] values=new Object[tmp];
 for(int j=0; j < shelfs.length; j++)
 for(int k=0; k < shelfs[j].keys.length; k++)
 if(shelfs[j].keys[k] != null)
 values[tmx++]=shelfs[j].VV[k];
 Object[] goodValues=new Object[tmx];
 for(int z=0; z < tmx; z++)
 goodValues[z]=values[z];
 return goodValues;
}
}