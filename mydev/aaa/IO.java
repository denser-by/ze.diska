package mydev.aaa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import mydev.vutils.Spiska;
public class IO {
public IO() {  }
public long copy(String srcFile, String dstFile) throws IOException {
File fOpen = new File(srcFile);
FileInputStream is = new FileInputStream(fOpen);
File wOpen = new File(dstFile);
FileOutputStream os = new FileOutputStream(wOpen);
long total = 0L;
int count;
byte[] buf = new byte[2048];
do {
count = is.read(buf);
if(count > 0) total += (long)count;
if(count < 1) continue;
if(count == buf.length) os.write(buf); else
if(count < buf.length) {
byte[] zzz = new byte[count];
System.arraycopy(buf, 0, zzz, 0, count);
os.write(zzz);
}
} while(count > 0);
os.flush();
os.close();
is.close();
return total;
}
public byte[] read(String fPath) throws IOException {
File fOpen = new File(fPath);
FileInputStream fos = new FileInputStream(fOpen);
Spiska ska = new Spiska(); long htKey = 0L;
int vPower = 0;
int count;
byte[] buf = new byte[64];
do {
count = fos.read(buf);
if(count > 0) vPower+=count;
if(count < 1) continue;
if(count <= buf.length) {
byte[] zzz= new byte[count];
System.arraycopy(buf, 0, zzz, 0, count);
ska.insert(htKey++, (Object)zzz);
}
} while(count > 0);
fos.close();
byte[] total = new byte[vPower];
int totalIdx=0;
for(int i = 0; i < htKey; i++) {
byte[] cur = (byte[])ska.at((long)i);
int curLen = cur.length;
System.arraycopy(cur, 0, total, totalIdx, curLen);
totalIdx += curLen;
}
return total;
}
public void write(String fPath, byte[] bCont) throws IOException {
File wOpen = new File(fPath);
FileOutputStream os = new FileOutputStream(wOpen);
os.write(bCont);
os.flush();
os.close();
}
}