package mydev.zetup;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import mydev.vutils.Spiska;
 import java.util.StringTokenizer;
 public class ZetUp {
 final char CC='=';
 final char NL='\n';
 final char RT='\r';
 final char SP=' ';
 final char TB='\t';
 final byte bCC=(byte) CC;
 final byte bNL=(byte) NL;
 final byte bRT=(byte) RT;
 final byte bSP=(byte) SP;
 final byte bTB=(byte) TB;
 public ZetUp() {
}
 public String getS(byte[] buf) {
 StringBuffer bb=new StringBuffer();
 for(int i=0; i < buf.length; i++)
{
 bb.append((char) buf[i]);
}
 String res=bb.toString().trim();
 return res;
}
 public Spiska parse(byte[] content) {
 Spiska res=new Spiska();
 int start=0;
 int cur=0;
 int middle=0;
 do {
 byte cCur=bNL;
 if(cur < content.length)
{
 cCur=content[cur];
}
 if(cCur == bSP || cCur == bTB)
{
 cur++;
 continue;
}
 if(cCur == bRT)
{
 cur++;
 continue;
}
 if(cCur != bCC && cCur != bNL)
{
 cur++;
 continue;
}
 if(cCur == bCC)
{
 middle=cur;
 cur++;
 continue;
}
 if(cCur == bNL)
{
 int len=middle-start;
 if(len < 0)
{
 cur++;
 start=cur;
 middle=0;
 continue;
}
 byte[] bufKey=new byte[len];
 System.arraycopy(content,start,bufKey,0,len);
 len=cur-middle;
 byte[] bufVal=new byte[len];
 System.arraycopy(content,middle+1,bufVal,0,len-1);
 res.append(bufVal);
 cur++;
 start=cur;
 middle=0;
}
}
 while(cur <= content.length);
 return res;
}
 public static void main(String[] args) {
 System.out.println("Go hell");
 try {
 ZetUp zt=new ZetUp();
 byte[] content=zt.read("zetupka");
 System.out.println(
"zt.read(\"zetupka\") completed...content.length="+content.length);
 Spiska pairs=zt.parse(content);
 System.out.println("zt.parse(content) completed...pairs.size()="+pairs.size());
 for(int i=0; i < pairs.size() / 2; i++)
{
 System.out.println("---------------------------------------------------");
 String files=zt.getS((byte[]) pairs.at(2 * i));
 String mezto=zt.getS((byte[]) pairs.at(2 * i+1));
 System.out.println("__files<"+files+">");
 System.out.println("__mezto<"+mezto+">");
 StringTokenizer tk=new StringTokenizer(files," ");
 while(tk.hasMoreTokens()) {
 String token=tk.nextToken();
 String file=token.trim();
 if(file.length() < 1)
{
 continue;
}
 zt.copyFile(file,mezto);
}
}
 System.out.println("---------------------------------------------------");
 }
 catch(IOException e) {
 System.out.println("Err:"+e.getMessage());
}
}
 public void copyFile(String file,String mezto) throws IOException {
 String dst=mezto+"/"+file;
 System.out.println("CF...<"+file+">...<"+mezto+">");
 byte[] data=read(file);
 File fi=new File(mezto);
 fi.mkdirs();
 write(dst,data);
}
 public byte[] read(String fPath) throws IOException {
 File fOpen=new File(fPath);
 FileInputStream fos=new FileInputStream(fOpen);
 Spiska ht=new Spiska();
 int vPower=0;
 int count;
 byte[] buf=new byte[4096];
 do {
 count=fos.read(buf);
 if(count > 0)
 vPower+=count;
 if(count < 1)
 continue;
 if(count <= 4096)
{
 byte[] zzz=new byte[count];
 System.arraycopy(buf,0,zzz,0,count);
 ht.append((Object) zzz);
}
}
 while(count > 0);
 fos.close();
 byte[] total=new byte[vPower];
 int totalIdx=0;
 for(int i=0; i < ht.size(); i++)
{
 byte[] cur=(byte[]) ht.at(i);
 int curLen=cur.length;
 System.arraycopy(cur,0,total,totalIdx,curLen);
 totalIdx+=curLen;
}
 return total;
}
 public void write(String fPath,byte[] bCont) throws IOException {
 File wOpen=new File(fPath);
 FileOutputStream os=new FileOutputStream(wOpen);
 os.write(bCont);
 os.flush();
 os.close();
}
}