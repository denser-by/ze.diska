package mydev.lezka;
import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import mydev.vutils.Spiska;
 public class LezKa {
 public LezKa() {
}
 public static void main(String[] args) {
 try {
 LezKa lz=new LezKa();
 if(args == null || args.length != 2)
{
 System.out.println("Two parameters are required:\n - page size;\n - file name.");
 return;
}
 int pgSz=Integer.parseInt(args[0]);
 String file=args[1];
 byte[] content=lz.read(file);
 System.out.println(""+content.length+" bytes from "+file);
 String mode="-orig";
 int cur=0;
 int PAGE_SIZE=pgSz;
 MyBuf page=new MyBuf(mode);
 boolean janna=12 > 14;
 if(janna)
 System.out.print("----------------------------------------------------------\n");
 else System.out.print("----------------------------------------------------------");
 char LN='\n';
 char RT='\r';
 byte bLN=(byte) LN;
 byte bRT=(byte) RT;
 int rwNum=0;
 int lineNo=1-1;
// int bilinear=1;
 page.appendLineNo(++lineNo);
 while(cur < content.length) {
 byte b=content[cur++];
 if(b == bRT)
 continue;
 if(b == bLN)
{
rwNum=rwNum+1;
 if(rwNum == PAGE_SIZE)
{
 page.append('-').append('>');
 lz.show(page.toString());
//System.out.print("kijja");
 page.setLength(0);
 rwNum=0;
 lz.next();
 page.appendLineNo(++lineNo);
 continue;
}
 else {
 page.append('-').append('>');
 page.appendLineNo(++lineNo);
 continue;
}
}
 page.append((char) b);
}
 page.commit(true);
 String rest=page.toString();
 if(rest.length() > 0)
{
 lz.show(rest);
}
}
 catch(IOException e) {
 System.out.println("Err:"+e.getMessage());
}
}
 public void next() throws IOException {
 int avail=0-1;
 byte[] taran=new byte[999];
 try {
 System.in.reset();
}
 catch(IOException ex) {
 ex.getMessage();
}
 boolean here=true;
 while(here) {
 avail=System.in.read(taran);
 if(avail > 0)
 here=false;
 try {
 Thread.sleep(199);
}
 catch(InterruptedException ex) {
 ex.getMessage();
}
}
}
 public void show(String page) {
 try {
 Thread.sleep(111);
}
 catch(Throwable th) {
}
 System.out.print(page);
//System.out.print("kijja2");
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
 class MyBuf {
 String mode;
 public MyBuf(String mode) {
 this.mode=mode;
}
 int curPos=0;
 char[] data=new char[256];
 public MyBuf append2(char[] ch) {
 append2(ch[0]);
 append2(ch[1]);
 return this;
}
 public MyBuf append2(char ch) {
 data[curPos++]=ch;
 if(curPos == data.length)
{
 char[] tmp=new char[data.length * 2];
 System.arraycopy(data,0,tmp,0,data.length);
 data=tmp;
}
 return this;
}
 int col=0;
 public MyBuf append(char ch) {
 if(ch != '\n')
{
 col++;
 append2(ch);
}
 else if(ch == '\n')
{
 append2(ch);
 commit(false);
}
 return this;
}
 public void commit(boolean lastOne) {
 if(lastOne)
 append('-').append('>');
 if(true)
 return;
 if(lastOne)
 append2('\n');
 int lastPos=curPos-1;
 append2('.');
 append2('.');
 append2('.');
 append2('.');
 append2(' ');
 for(; col > 0; col--)
{
 append2(hex(data[lastPos-col]));
}
 append2(hex(data[lastPos+1]));
 append2('\n');
}
 char[] hex(char ch) {
 int e=(int) ch;
 byte b2=(byte)(e % 16);
 e=(e-b2) / 16;
 byte b1=(byte)(e % 16);
 String map="0123456789abcdef";
 char ch2=map.charAt(b2);
 char ch1=map.charAt(b1);
 char[] res=new char[2];
 res[0]=ch1;
 res[1]=ch2;
 return res;
}
 public void appendLineNo(int lineNo) {
 String str=getLine(lineNo);
 append2((char) 0x0A);
 for(int i=0; i < str.length(); i++)
{
 char ch=str.charAt(i);
 append2(ch);
}
 append2('|');
 append2('|');
}
 public String getLine(int ll) {
 String pt="0123456789";
 int b4=ll % 10;
 ll=(ll-b4) / 10;
 int b3=ll % 10;
 ll=(ll-b3) / 10;
 int b2=ll % 10;
 ll=(ll-b2) / 10;
 int b1=ll % 10;
 ll=(ll-b1) / 10;
 char[] buf=new char[5];
 buf[4]=' ';
 buf[3]=pt.charAt(b4);
 buf[2]=pt.charAt(b3);
 buf[1]=pt.charAt(b2);
 buf[0]=pt.charAt(b1);
 String res=new String(buf);
 return res;
}
 public void setLength(int ll) {
 curPos=0;
 data=new char[256];
}
 public String toString() {
 String res=new String(data,0,curPos);
 return res;
}
}