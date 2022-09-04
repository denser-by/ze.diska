package mydev.datel;

import java.io.IOException;
import mydev.aaa.IO;
import mydev.aaa.Enter;


public class Checker {
StringBuffer ing;
String path;

Checker(String path) {
this.path = path;
this.ing = new StringBuffer();
}

static String[] s11 = {"aaa","bbb","ccc"};
static String[] s12 = {"zzz","yyy","xxx"};
static boolean f = false;

static String[] s61 = {"a,a","bbb","ccc"};
static String[] s62 = {"ddd","e\"e","fff"};
static String[] s63 = {"zz\rz","yy\ny","xxx"};
static String[] s64 = {"mmm","n\r\nnn","k\n\rkk"};
static String[] s65 = {"iii","ooo","ppp"};

public static void main(String[] args) throws IOException{
Checker test1 = new Checker("test1.out");
test1.add(s11,f).crlf().add(s12,f).crlf().set();
Checker test2 = new Checker("test2.out");
test2.add(s11,f).lfcr().add(s12,f).set();
Checker test5 = new Checker("test5.out");
test5.add(s11,!f).crlf().add(s12,f).set();
Checker test6 = new Checker("test6.out");
test6.add(s61,f).crlf().add(s62,f).crlf().add(s63,f).crlf()
 .add(s64,f).crlf().add(s65,f).crlf().set();

}

public void set() throws IOException {
IO io = Enter.instance().io();
String str = ing.toString();
byte[] data = new byte[str.length()];
for(int i = 0; i < data.length; i++) data[i] = (byte)str.charAt(i);
io.write(path, data);
}

Checker add(String[] item, boolean forceEnc) {
boolean has2 = false;
boolean enc = forceEnc ? forceEnc : false;
for(int i=0; !has2&&i < item.length; i++) if(has2(item[i])) has2=!has2;
if(has2) { for(int i=0; i<item.length; i++) item[i] = dublya(item[i]); enc=true; }
for(int i=0; !enc&&i < item.length; i++) if(has(item[i])) enc=!enc;
if(enc)for(int i=0; i<item.length; i++) item[i] = enc(item[i]);
for(int i=0; i<item.length; i++) {
if(i > 0) ing.append(",");
ing.append(item[i]);
}
return this;
}
String enc(String it) { return '"' + it + '"'; }
String dublya(String it) {
StringBuffer n = new StringBuffer();
for(int i = 0; i < it.length(); i++) {
char ch = it.charAt(i);
if(ch=='"') n.append(ch).append(ch); else n.append(ch);
}
return n.toString();
}
boolean has2(String it) {
int idx1 = it.indexOf("\"");
return idx1>0;
}
boolean has(String it) {
int idx1 = it.indexOf("\"");
int idx2 = it.indexOf("\r");
int idx3 = it.indexOf("\n");
int idx4 = it.indexOf(",");
return idx1>0||idx2>0||idx3>0||idx4>0;
}
void cr() { ing.append('\r'); }
void lf() { ing.append('\n'); }
Checker crlf() { cr(); lf(); return this; }
Checker lfcr() { lf(); cr(); return this; }
}