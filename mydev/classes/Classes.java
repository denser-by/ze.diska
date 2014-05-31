package mydev.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import mydev.vutils.Files;
import mydev.vutils.Filter;
import mydev.vutils.Join;
import mydev.vutils.Time;
import mydev.oneway.OneWay;
import mydev.vutils.Spiska;
import mydev.vutils.Karta;
import mydev.vutils.Ester;
import mydev.vutils.S;


public class Classes {

final String START = "48,80677,1380057488548";

Files files;
Filter filter;
Join join;
byte[] data;

public Classes() {
this.files = new Files();
this.filter = new Filter();
this.join = new Join();
this.data = new byte[0];
}

public Ester[] allSrc(Ester srcRoot) {
Ester[] all = files.tree(srcRoot);
Ester[] srcs1 = filter.endsWith(all, new Ester(".java"));
//String[] srcs2 = filter.endsWith(all, ".c");
//String[] srcs3 = filter.endsWith(all, ".h");
//String[] srcs4 = filter.endsWith(all, ".cpp");
//String[] srcs5 = filter.endsWith(all, ".bat");
//String[] srcs = 
//join.join(srcs5, join.join(join.join(srcs1, srcs2), join.join(srcs3, srcs4)));
return srcs1;
}

public Ester[] allCls(Ester srcRoot) {
Ester[] all = files.tree(srcRoot);
Ester[] srcs5 = filter.endsWith(all, new Ester(".class"));
return srcs5;
}

public long sizeof(Ester[] files) {
long onno = 0L;
for(int k = 0; k < files.length; k++) onno += sizeof_worthy(files[k]);
return onno;
}

private Long sum(Long a, Long b) {
long h = 0L;
h += a == null ? 0L : a.longValue();
h += b == null ? 0L : b.longValue();
return new Long(h);
}

private Long keyStrartsWith(Karta rt, String start) {
String[] keys = rt.keys();
long total = 0L;
for(int i = 0; i < keys.length; i++)
if(keys[i] != null && keys[i].startsWith(start)) {
Long vv = (Long)rt.get(keys[i]);
total += vv != null ? vv.longValue() : 0L;
}
return new Long(total);
}

public static void main(String[] args) throws IOException {
Classes trend = new Classes();
if(args.length >= 1) {
Ester param = null;
for(int k = 0; k < args.length; k++)
if(new Ester(args[k]).nteq(new Ester("Classes.class")))
if(param == null) param = new Ester(args[k]);

Time time = new Time();
time.start();
ClassesMeta cmt = new ClassesMeta();
cmt.srcRoot = trend.files.absPath(param);
if(!cmt.srcRoot.ends(trend.files.c())) cmt.srcRoot.append(trend.files.c());
cmt.srcFiles = trend.allSrc(cmt.srcRoot);
cmt.clsFiles = trend.allCls(cmt.srcRoot);
cmt.srcFilesSize = trend.sizeof(cmt.srcFiles);
cmt.clsFilesSize = trend.sizeof(cmt.clsFiles);
trend.calc(cmt.srcFiles, cmt);
System.out.println(
"K------------------------------------------------------------------------------");

String on = trend.build(trend.data, cmt.clsFiles.length, cmt.clsFilesSize);
System.out.println("" + on);

time.trust();
System.out.println("                                                 Zatrachana : " + time.ess());

} else System.out.println("No params at all...");
}

private void calc(Ester[] srcs, ClassesMeta cm) throws IOException {
Karta ht = new Karta();
int pe = 0;

System.out.println("");

for(int i = 0; i < srcs.length; i++) {
//System.out.print("  " + srcs[i]);
OneWay ow = new OneWay();
byte[] data = read(srcs[i]);
String[] pieces = null;
try {
pieces = ow.check(data, 1);
//System.out.println("  OK");
} catch(Exception ex) {
pe++;
new S().out("  " + srcs[i] + "  FAIL");
}
if(pieces!=null) for(int j = 0; j < pieces.length; j++) {
String item = pieces[j];
if(item != null) {
Long num = (Long)ht.get(item);
long next = num!=null?num.longValue():0L;
ht.put(item, new Long(next+1));
} //if
} //if
} //for

if(pe > 0) new S().out("Parsing errors : " + pe);

System.out.println(
"K------------------------------------------------------------------------------");
cm.print();
System.out.println(
"K------------------------------------------------------------------------------");

Long ifNum = (Long)ht.get("if");
Long caseNum = (Long)ht.get("case");
Long forNum = (Long)ht.get("for");
Long whileNum = (Long)ht.get("while");
Long returnNum = (Long)ht.get("return");
Long incrNum = (Long)ht.get("++");
Long decrNum = (Long)ht.get("--");
Long andNum = (Long)ht.get("&&");
Long orNum = (Long)ht.get("||");

System.out.print("| if:" + ifNum);
System.out.print("  case:" + caseNum);
System.out.print("  for:" + forNum);
System.out.print("  while:" + whileNum);
System.out.print("  ++:" + incrNum);
System.out.print("  --:" + decrNum);
System.out.print("  &&:" + andNum);
System.out.print("  ||:" + orNum);
System.out.println("");

Long c1Num = (Long)ht.get("==");
Long c2Num = (Long)ht.get("!=");
Long c3Num = (Long)ht.get(">");
Long c4Num = (Long)ht.get(">=");
Long c5Num = (Long)ht.get("<");
Long c6Num = (Long)ht.get("<=");
Long c7Num = (Long)ht.get("=");
Long c8Num = (Long)ht.get("!");
Long opNum = (Long)ht.get(";");

System.out.print("| = :" + c7Num);
System.out.print("  ==:" + c1Num);
System.out.print("  !=:" + c2Num);
System.out.print("  > :" + c3Num);
System.out.print("  >=:" + c4Num);
System.out.print("  < :" + c5Num);
System.out.print("  <=:" + c6Num);
System.out.print("  ! :" + c8Num);
System.out.println("");

Long ebNum = sum((Long)ht.get("}"), (Long)ht.get("{"));
Long be1Num = sum((Long)ht.get("]"), (Long)ht.get("["));
Long be2Num = sum((Long)ht.get(")"), (Long)ht.get("("));
Long p1Num = (Long)ht.get("+");
Long p2Num = (Long)ht.get("-");
Long p3Num = (Long)ht.get("*");
Long p4Num = (Long)ht.get("/");
Long p14 = sum(sum(p1Num, p2Num), sum(p3Num, p4Num));
Long importNum = (Long)ht.get("import");
Long classNum = (Long)ht.get("class");
Long interfaceNum = (Long)ht.get("interface");
Long qNum = (Long)ht.get("?");
Long commaNum = (Long)ht.get(",");

Long dqNum = keyStrartsWith(ht, "\"");
Long sqNum = keyStrartsWith(ht, "'");

System.out.print("| {}:" + ebNum);
System.out.print("  []:" + be1Num);
System.out.print("  ():" + be2Num);
System.out.print("  */+-:" + p14);
System.out.print("  ? :" + qNum);
System.out.print("  , :" + commaNum);
System.out.print("  \" :" + dqNum);
System.out.print("  ' :" + sqNum);
System.out.println("");

Long mainNum = (Long)ht.get("main");

System.out.print("| ; :" + opNum);
System.out.print("  main:" + mainNum);
System.out.print("  return:" + returnNum);
System.out.print("  import:" + importNum);
System.out.print("  class:" + classNum);
System.out.print("  interface:" + interfaceNum);
System.out.println("");

Long dotNum = (Long)ht.get(".");
Long byteNum = (Long)ht.get("byte");
Long shortNum = (Long)ht.get("short");
Long intNum = (Long)ht.get("int");
Long longNum = (Long)ht.get("long");
Long StringNum = (Long)ht.get("String");

Long num = new Long(0L);
for(int i = 0; i <= 9; i++) {
Long tmp = keyStrartsWith(ht, "" + i + "");
num = sum(num, tmp);
}

System.out.print("| . :" + dotNum);
System.out.print("  [0-9]+:" + num);
System.out.print("  byte:" + byteNum);
System.out.print("  short:" + shortNum);
System.out.print("  int:" + intNum);
System.out.print("  long:" + longNum);
System.out.print("  String:" + StringNum);
System.out.println("");

}

String prepare(String path) {
int idx = path.lastIndexOf("/");
int idx2 = path.lastIndexOf("\\");
int max = idx2 > idx ? idx2 : idx;
int dot = path.lastIndexOf(".class");
return path.substring(max+1,dot);
}

public byte[] read(Ester path) throws IOException {
File fi = new File(path.toString());
FileInputStream fos = new FileInputStream(fi);
Spiska ht = new Spiska();
int vPower = 0;
int count;
byte[] buf = new byte[2244];
do {
count = fos.read(buf);
if(count > 0) vPower+=count;
if(count < 1) continue;
if(count <= buf.length) {
byte[] zzz= new byte[count];
System.arraycopy(buf, 0, zzz, 0, count);
ht.append((Object)zzz);
}
} while(count > 0);
fos.close();
byte[] total = new byte[vPower];
int totalIdx=0;
for(long i = 0; i < ht.size(); i++) {
byte[] cur = (byte[])ht.at(i);
int curLen = cur.length;
System.arraycopy(cur, 0, total, totalIdx, curLen);
totalIdx += curLen;
}
return total;
}

public void write(String path, byte[] bCont) throws IOException {
File fi = new File(path);
FileOutputStream os = new FileOutputStream(fi);
os.write(bCont);
os.flush();
os.close();
}

public long sizeof_worthy(Ester path) {
File fi = new File(path.toString());
FileInputStream fos = null;
try { fos = new FileInputStream(fi); } catch(IOException ex) { fos = null; }
long vPower = 0L;
if(fos != null) {
int count = 0;
byte[] buf = new byte[4096*4096];
do {
try { count = fos.read(buf); } catch(IOException ex) {  }
if(count > 0) vPower+=(long)count;
} while(count > 0);
try { fos.close(); } catch(IOException ex) {  }
} //if
return vPower;
}

public String  build(byte[] data, int allClasses, long allSize) {
StringBuffer buf = new StringBuffer();
buf.append("  Trend : ").append("\n");

char[] dataCh = new char[data.length];
for(int i = 0; i < data.length; i++) {
dataCh[i]=(char)data[i];
}
//String dataStr = new String(dataCh);
String dataStr = START;
int idx = dataStr.indexOf(",");
int idx2 = dataStr.lastIndexOf(",");
Long prevShtuk= new Long(dataStr.substring(0,idx));
Long prevBytez= new Long(dataStr.substring(idx+1, idx2));
Long prevTime= new Long(dataStr.substring(idx2+1));

long timeDeltaMin = (System.currentTimeMillis() 
- prevTime.longValue())/(60*1000);

if(allClasses != prevShtuk.longValue()) {
long shtuk1000Min = 1000L*timeDeltaMin/((long)allClasses
-prevShtuk.longValue());
buf.append("    1000 shtuchek in approx. ")
.append(shtuk1000Min).append(" min");
} else {
buf.append("    1000 shtuchek in approx. ").append(" limitless...");
}

buf.append("\n");

if(allSize != prevBytez.longValue()) {
long size1000000Min = 1000000L*timeDeltaMin/((long)allSize
-prevBytez.longValue());
buf.append("    1000000 bytevz in approx. ").append(size1000000Min)
.append(" min");
} else {
buf.append("    1000000 bytevz in approx. ").append(" limitless...");
}
return buf.toString();
}

} //class


class ClassesMeta {
Ester srcRoot;
Ester[] srcFiles;
long srcFilesSize;
Ester[] clsFiles;
long clsFilesSize;

ClassesMeta() {  }

void print() {
System.out.println("| srcRoot " + srcRoot);
System.out.println("| *.java  " + srcFiles.length 
+ " files " + srcFilesSize + " bytes");
System.out.println("| *.class " + clsFiles.length 
+ " files " + clsFilesSize + " bytes");
}
}