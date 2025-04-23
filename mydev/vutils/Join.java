package mydev.vutils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class Join {
public Join() {
  }
public Ester[] sub(Ester[] a1, Ester[] a2) {
Spiska cektor = new Spiska();
for(int i = 0; i < a1.length; i++)
{
boolean s = false;
for(int j = 0; !s && j < a2.length; j++)
 if(a1[i] != null && a1[i].eq(a2[j]))
 s=!s;
if(s == false)
 cektor.append(a1[i]);
}
Ester[] result = new Ester[(int)cektor.size()];
for(int i = 0; i < result.length; i++)
 result[i] = (Ester)cektor.at(i);
return result;
}
public Ester[] join(Ester[] a1, Ester[] a2) {
Ester[] res = new Ester[a1.length + a2.length];
for(int i = 0; i < a1.length; i++)
 res[i] = a1[i];
for(int i = 0; i < a2.length; i++)
 res[i + a1.length] = a2[i];
return res;
}
public byte[] jread(Ester[] pathes) throws IOException {
byte[] jcontent = read(pathes[0]);
for(int i = 1; i < pathes.length; i++)
{
byte[] ff = read(pathes[i]);
byte[] tmp = new byte[jcontent.length + ff.length];
System.arraycopy(jcontent, 0, tmp, 0, jcontent.length);
System.arraycopy(ff, 0, tmp, jcontent.length, ff.length);
jcontent = tmp;
}
return jcontent;
}
public byte[] read(Ester fPath) throws IOException {
File fOpen = new File(fPath.toString());
FileInputStream fos = new FileInputStream(fOpen);
Spiska ht = new Spiska();
int htKey = 0;
int vPower = 0;
int count;
byte[] buf = new byte[4096];
do {
count = fos.read(buf);
if(count > 0)
 vPower+=count;
if(count < 1)
 continue;
if(count <= 4096)
{
byte[] zzz= new byte[count];
System.arraycopy(buf, 0, zzz, 0, count);
htKey++;
ht.append((Object)zzz);
}
}
 while(count > 0);
fos.close();
byte[] total = new byte[vPower];
int totalIdx=0;
for(int i = 0; i < htKey; i++)
{
byte[] cur = (byte[])ht.at((long)i);
int curLen = cur.length;
System.arraycopy(cur, 0, total, totalIdx, curLen);
totalIdx += curLen;
}
return total;
}
}