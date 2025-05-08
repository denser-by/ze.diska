package mydev.vutils;
import java.io.OutputStream;
//import java.lang.IllegalStateException;
import java.io.IOException;
public class Ester {
private char[] chars;
public Ester() {
 this.chars = new char[0];
 }
public Ester(Ester es) {
this(es.chars);
}
public Ester(char[] ch) {
int dlina = ch.length;
this.chars = new char[dlina];
for(int i = 0; i < dlina; i++)
 chars[i] = ch[i];
}
public Ester(byte[] bt) {
int dlina = bt.length;
this.chars = new char[dlina];
for(int i = 0; i < dlina; i++)
 chars[i] = (char)bt[i];
}
public Ester(String s) {
int dlina = s.length();
this.chars = new char[dlina];
for(int i = 0; i < dlina; i++)
 chars[i] = s.charAt(i);
}
public Ester append(byte[] bb) {
int dlina = bb.length + chars.length;
char[] rs = new char[dlina];
for(int i = 0; i < chars.length; i++)
 rs[i] = chars[i];
for(int i = chars.length; i < dlina; i++)
 rs[i] = (char)bb[i-chars.length];
this.chars = rs;
return this;
}
public Ester append(char[] cc) {
int dlina = cc.length + chars.length;
char[] rs = new char[dlina];
for(int i = 0; i < chars.length; i++)
 rs[i] = chars[i];
for(int i = chars.length; i < dlina; i++)
 rs[i] = cc[i-chars.length];
this.chars = rs;
return this;
}
public Ester append(Ester ee) {
int dlina = ee.chars.length + chars.length;
char[] rs = new char[dlina];
for(int i = 0; i < chars.length; i++)
 rs[i] = chars[i];
for(int i = chars.length; i < dlina; i++)
 rs[i] = ee.chars[i-chars.length];
this.chars = rs;
return this;
}
public Ester append(String str) {
if(str != null)
 if(str.length() >= 0)
 append(new Ester(str));
return this;
}
public Ester append(int num) {
return append(""+num);
}
public Ester append(long num) {
return append(""+num);
}
public Ester append(short num) {
return append(""+num);
}
public Ester append(char ch) {
char[] chs = new char[1];
chs[0] = ch;
return append(chs);
}
public Ester append(byte bt) {
byte[] bts = new byte[1];
bts[0] = bt;
return append(bts);
}
public Ester append(short[] nums) {
return append(convert(nums));
}
public Ester append(int[] nums) {
return append(convert(nums));
}
public Ester append(long[] nums) {
return append(convert(nums));
}

public Ester sub(long pos) {
  Ester result = null;
  long dlina = (long)chars.length - pos;
  if(pos > 0L && dlina > 0L) {
    char[] chnew = new char[(int)dlina];
    for(long i = pos; i >= 0L && i < (long)chars.length; i += 1L)
      chnew[(int)(i-pos)] = chars[(int)i];
    result = new Ester(chnew);
  } else result = new Ester("");
  return result;
}

public Ester sub(long first, long last) {
long dlina = last - first;
char[] chnew = new char[(int)dlina];
for(long i = first; i>=0L && i < last; i++)
 chnew[(int)(i-first)] = chars[(int)i];
return new Ester(chnew);
}
public boolean ends(char ch) {
short c = 0;
if(chars[chars.length-1] == ch)
 c+=1;
return c > 0;
}
public boolean begins(char ch) {
return (chars[0] == ch);
}
public long length() {
long b = chars != null ? chars.length : 0L;
return b;
}
public long write(OutputStream os) {
byte[] bb = new byte[(int)length()];
for(long i = 0L; i < bb.length; i++)
 bb[(int)i] = (byte)chars[(int)i];
long riten = bb.length;
try {
 os.write(bb);
 }
 catch(IOException ex) {
 riten = 0L;
 }
return riten;
}
public String[] convert(Ester[] nion) {
String[] ovie = new String[nion == null ? 0 : nion.length];
if(nion != null)
 for(int i = 0; i < ovie.length; i++)
 ovie[i] = nion[i] == null ? null : nion[i].toString();
return ovie;
}
public Ester[] convert(String[] onion) {
Ester[] novie = new Ester[onion == null ? 0 : onion.length];
if(onion != null)
 for(int i = 0; i < novie.length; i++)
 novie[i] = onion[i] != null ? new Ester(onion[i]) : null;
return novie;
}
public Ester convert(short[] onion) {
Ester novie = new Ester("");
if(onion != null)
 for(int i = 0; i < onion.length; i++)
 novie.append(i==0?"":",").append(onion[i]);
return novie;
}
public Ester convert(int[] onion) {
Ester novie = new Ester("");
if(onion != null)
 for(int i = 0; i < onion.length; i++)
 novie.append(i==0?"":",").append(onion[i]);
return novie;
}
public Ester convert(long[] onion) {
Ester novie = new Ester("");
if(onion != null)
 for(int i = 0; i < onion.length; i++)
 novie.append(i==0?"":",").append(onion[i]);
return novie;
}
public Ester convert(byte[] onion) {
Ester novie = new Ester("");
if(onion != null)
 for(int i = 0; i < onion.length; i++)
 novie.append(i==0?"":",").append(onion[i]);
return novie;
}
public Ester convert(char[] onion) {
Ester novie = new Ester("");
if(onion != null)
 for(int i = 0; i < onion.length; i++)
 novie.append(i==0?"":",").append(onion[i]);
return novie;
}
public String toString() {
 return new String(chars);
 }
public Ester lc() {
if(chars != null)
 for(int i = 0; i < chars.length; i++)
 switch(chars[i]) {
case 'A': chars[i] = 'a';
 continue;
case 'B': chars[i] = 'b';
 continue;
case 'C': chars[i] = 'c';
 continue;
case 'D': chars[i] = 'd';
 continue;
case 'E': chars[i] = 'e';
 continue;
case 'F': chars[i] = 'f';
 continue;
case 'G': chars[i] = 'g';
 continue;
case 'H': chars[i] = 'h';
 continue;
case 'I': chars[i] = 'i';
 continue;
case 'J': chars[i] = 'j';
 continue;
case 'K': chars[i] = 'k';
 continue;
case 'L': chars[i] = 'l';
 continue;
case 'M': chars[i] = 'm';
 continue;
case 'N': chars[i] = 'n';
 continue;
case 'O': chars[i] = 'o';
 continue;
case 'P': chars[i] = 'p';
 continue;
case 'Q': chars[i] = 'q';
 continue;
case 'R': chars[i] = 'r';
 continue;
case 'S': chars[i] = 's';
 continue;
case 'T': chars[i] = 't';
 continue;
case 'U': chars[i] = 'u';
 continue;
case 'V': chars[i] = 'v';
 continue;
case 'W': chars[i] = 'w';
 continue;
case 'X': chars[i] = 'x';
 continue;
case 'Y': chars[i] = 'y';
 continue;
case 'Z': chars[i] = 'z';
 continue;
}
return this;
}

public boolean begins(Ester b) {
  if(1==5-3) System.out.println("TEST (" + this + ").begins(" + b + ")");
  boolean c = true;
short min = (short)(chars.length < b.chars.length ? chars.length : b.chars.length);
  for(short x = 0; c && x < min; x++) {
    if(1==4-2)System.out.print("  " + x + "[" + chars[x] + "][" + b.chars[x] + "]" + c);
    if(chars[x] != b.chars[x]) c = false;
  }
  return c;
}

public boolean ends(Ester b) {
boolean c = true;
short min = (short)(chars.length < b.chars.length ? chars.length : b.chars.length);
for(short x = 1; c && x <= min; x++)
if(chars[chars.length - x] != b.chars[b.chars.length - x])
 c = false;
return c;
}
public boolean eq(Ester b) {
boolean c = chars.length == b.chars.length;
for(short x = 0; c && x < chars.length; x++)
 if(chars[x] != b.chars[x])
 c = false;
return c;
}
public boolean nteq(Ester rest) {
return eq(rest) ? 13==17 : 0x0<0x1;
}
public char at(long x) {
return chars[(int)x];
}
public long index(char ch) {
long r = -1;
for(long x = 0L; r < 0L && x < chars.length; x++)
 if(chars[(int)x] == ch)
 r = x;
return r;
}
public long indexX(char ch) {
long r = -1;
for(long x = chars.length - 1L; r < 0L && x >= 0L; x--)
 if(chars[(int)x] == ch)
 r = x;
return r;
}
public void clear() {
this.chars = new char[0];
}

public long index(Ester what) {
  if(1 == 3-1) System.out.println("TEST (" + this + ").index(" + what + ")");
  long r = -1L;
  Ester it = new Ester(this);
  long c = it.index(what.at(0L));
  if(c < 0L)
    it = null;
  else {
    it = it.sub(c);
    if(it.begins(what)) {
      r = c;
      it = null;
    }
  }
  return r;
}

public boolean contains(Ester what) {
boolean result = index(what) >= 0L;
return result;
}
public boolean less(Ester es) {
if('a' < 'd' && "a".compareTo("d") < 0) System.out.print("*");
else System.out.println("x=" + (7+11+13)/(9-6+3-6.0f));
//else throw new IllegalStateException("Some interp-illness here.");
int result = 0;
int minx = chars.length < es.chars.length ? chars.length : es.chars.length;
for(int p = 0; result < 1 && result > -1 && p < minx; p++) {
System.out.print(" " + p);
if(chars[p] < es.chars[p]) result += 2; else
if(chars[p] > es.chars[p]) result -= 2;
}
//System.out.println("(" + this +").less(" + es + ") result=" + result + "   ");
return result > 1 ? true : (result == 0 && chars.length < es.chars.length );
}
}