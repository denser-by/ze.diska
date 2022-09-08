package mydev.slave;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.lang.StringBuffer;
 import mydev.vutils.Files;
 import mydev.vutils.Filter;
 import mydev.vutils.Spiska;
 import mydev.vutils.Ester;
 public class Metriko {
 final String orig=".orig";
 final char[] quotes={'\'','"'};
 final char[] skips={'\r','\n'};
 final char[] ends={'{','}',';'};
 Files files;
 Filter filter;
 long shtukins;
 public Metriko() {
 this.files=new Files();
 this.filter=new Filter();
 this.shtukins=0L;
}
 public boolean skipTo(byte bch) {
 boolean res=false;
 for(int i=0; !res && i < skips.length; i++)
 if((char) bch == skips[i])
 res=true;
 return res;
}
 public boolean competeEnd(byte bch) {
 boolean res=false;
 for(int i=0; !res && i < ends.length; i++)
 if((char) bch == ends[i])
 res=true;
 return res;
}
 public boolean hotCom(byte[] data,int prev) {
 boolean dd=true;
 int i=prev;
 while(dd && i >= 0 && data[i] !=(byte) '\n') {
 if(data[i] ==(byte) '/' && data[i+1] ==(byte) '/')
 dd=!dd;
 i-=1;
}
 return dd;
}
 public boolean hotQuot(byte[] data,int prev) {
 boolean dd=true;
 int kw=0;
 int i=prev;
 while(i >= 0 && data[i] !=(byte) '\n') {
 if(data[i] ==(byte) '\'' || data[i] ==(byte) '\"')
 kw+=1;
 i-=1;
}
 if(kw-kw / 2 * 2 > 0)
 dd=!dd;
 return dd;
}
 boolean commLine=false;
 boolean commMult=false;
 private boolean commento(byte[] data,int i) {
 if(commLine)
{
 if(data[i] ==(byte) '\r' || data[i] ==(byte) '\n')
{
 commLine=!commLine;
 nl=true;
}
}
 else {
 if(data[i] ==(byte) '/' && data[i+1] ==(byte) '/')
 commLine=!commLine;
}
 if(commMult)
{
 if(data[i] ==(byte) '*' && data[i+1] ==(byte) '/')
 commMult=!commMult;
}
 else {
 if(data[i] ==(byte) '/' && data[i+1] ==(byte) '*')
 commMult=!commMult;
}
 return commLine || commMult;
}
 boolean ifMode=false;
 int ifModeCnt=0;
 private boolean ifento(byte[] data,int i) {
 if(ifMode)
{
 if(data[i] ==(byte) '(') ifModeCnt+=1; if(data[i] ==(byte) ')')
{
 ifModeCnt-=1;
 if(ifModeCnt == 0)
{
 nl=true;
 ifMode=false;
}
}
}
 else {
 if(data[i] ==(byte) 'i' && data[i+1] ==(byte) 'f' && isfuncend(data[i+2]))
 ifMode=true;
}
 return ifMode;
}
 boolean forMode=false;
 int forModeCnt=0;
 private boolean forento(byte[] data,int i) {
 if(forMode)
{
 if(data[i] ==(byte) '(') forModeCnt+=1; if(data[i] ==(byte) ')')
{
 forModeCnt-=1;
 if(forModeCnt == 0)
{
 nl=true;
 forMode=false;
}
}
}
 else {
 if(data[i] ==(byte) 'f' && data[i+1] ==(byte) 'o' && data[i+2] ==(byte) 'r' 
&& isfuncend(data[i+3]))
 forMode=true;
}
 return forMode;
}
 private boolean nlendo(byte[] data,int i) {
 boolean dd=true;
 while(dd && i < data.length) if(izblocksta(data[i]))
 i+=1;
 else dd=!dd;
 return nl && data[i]==(byte) '{';
}
 public boolean izblocksta(byte b) {
 return b==(byte) ' ' || b==(byte) '\t' || b==(byte) ')';
}
 public boolean isfuncend(byte b) {
 return b==(byte) ' ' || b==(byte) '\t' || b==(byte) '(';
}
 public boolean isdoossym(byte[] data,int i) {
 return false ||(char) data[i]=='&' && '&'==(char) data[i+1] 
||(char) data[i]=='|' && '|'==(char) data[i+1] ||(char) data[i]=='=' 
&& '='==(char) data[i+1] ||(char) data[i]=='!' && '='==(char) data[i+1] 
||(char) data[i]=='>' && '='==(char) data[i+1] ||(char) data[i]=='<' 
&& '='==(char) data[i+1];
}
 public boolean lsgt(byte[] data,int i) {
 return false ||(char) data[i]=='>' && '=' !=(char) data[i+1] 
||(char) data[i]=='<' && '=' !=(char) data[i+1];
}
 boolean doIndent=false;
 int indent=0;
 private void up(StringBuffer buf,char ch,String skip) {
 up(buf,(byte) ch,skip);
}
 private void up(StringBuffer buf,byte b,String skip) {
 char ch=(char) b;
 if(ch == '{')
 indent++;
 if(ch == '}')
{
 indent--;
 if(doIndent)
 buf.setLength(buf.toString().length()-4);
}
 if(b ==(byte) '\n')
{
 buf.append(ch);
 for(int i=0; doIndent && i < indent; i++)
 buf.append("    ");
}
 else {
 if(skip.indexOf((char) b) < 0)
 buf.append(ch);
}
}
 private int printIfs(StringBuffer buf,byte[] data,int i) {
 int x=0;
 if(isdoossym(data,i))
{
 if(!isfuncend(data[i-1]))
 up(buf,(byte) ' ',"");
 up(buf,data[i],"");
 up(buf,data[i+1],"");
 if(!isfuncend(data[i+2]))
 up(buf,(byte) ' ',"");
 x+=1;
}
 else if(lsgt(data,i))
{
 if(!isfuncend(data[i-1]))
 up(buf,(byte) ' ',"");
 up(buf,data[i],"");
 if(!isfuncend(data[i+1]))
 up(buf,(byte) ' ',"");
}
 else {
 up(buf,data[i],"\r\n");
}
 return x;
}
 boolean nl=false;
 public byte[] process(byte[] data) {
 StringBuffer buf=new StringBuffer();
 int i=0;
 while(i < data.length) {
 if(commento(data,i))
{
 up(buf,data[i],"\r");
}
 else if(nl)
{
 nl=false;
 up(buf,'\n',"");
}
 else if(ifento(data,i))
{
 int x=printIfs(buf,data,i);
 if(x > 0)
 i+=1;
}
 else if(nlendo(data,i))
{
 up(buf,data[i],"\r\n");
}
 else if(nl)
{
 up(buf,data[i],"");
 nl=false;
 up(buf,'\n',"");
}
 else if(forento(data,i))
{
 up(buf,data[i],"\r\n");
}
 else if(nlendo(data,i))
{
 up(buf,data[i],"\r\n");
}
 else if(nl)
{
 up(buf,data[i],"");
 nl=false;
 up(buf,'\n',"");
}
 else if(skipTo(data[i]))
{
 i+=1;
 continue;
}
 else if(competeEnd(data[i]))
{
 up(buf,data[i],"");
 if(hotCom(data,i-1) && hotQuot(data,i-1))
 up(buf,'\n',"");
}
 else {
 up(buf,data[i],"");
}
 i+=1;
}
 byte[] newData=converta(buf.toString());
 return newData;
}
 public byte[] converta(String ona) {
 byte[] chtota=new byte[ona.length()];
 for(int i=0; i < chtota.length; i++)
 chtota[i]=(byte) ona.charAt(i);
 return chtota;
}
 public void onput(Ester path) throws IOException {
 path=files.absPath(path);
 System.out.println(" "+ ++shtukins+") "+files.folder(path));
 Ester newPath=new Ester(path).append(orig);
 Ester name=files.name(path);
 Ester newName=new Ester(name).append(orig);
 System.out.print("("+name+" => "+newName+")");
 long size=copy(path,newPath);
 System.out.print(" "+size+":  format("+name+")");
 byte[] data=read(path);
 data=process(data);
 new File(path.toString()).delete();
 write(path,data);
 System.out.println(" "+data.length+"..");
}
 public void offputs(Ester[] all) {
 all=filter.endsWith(all,new Ester(".java"));
 System.out.println("");
 for(int i=0; i < all.length; i++)
 System.out.println(""+all[i]);
 for(int i=0; i < all.length; i++)
{
 try {
 onput(all[i]);
}
 catch(IOException ex) {
 System.out.println(ex.getMessage());
}
}
}
 public void offputs() {
 Ester[] all=files.tree();
 offputs(all);
}
 public void offput(Ester path) {
 if(files.isDir(files.absPath(path)))
{
 Ester[] all=files.tree(path);
 offputs(all);
}
 else {
 Ester[] all=files.tree(files.folder(files.absPath(path)));
 all=filter.endsWith(all,files.absPath(path));
 offputs(all);
}
}
 public static void main(String[] args) {
 Metriko triko=new Metriko();
 if(args.length > 0)
 triko.offput(new Ester(args[0]));
 else triko.offputs();
}
 public byte[] read(Ester fPath) throws IOException {
 File fOpen=new File(fPath.toString());
 Spiska sp=new Spiska();
 FileInputStream fos=new FileInputStream(fOpen);
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
 sp.append((Object) zzz);
}
}
 while(count > 0);
 fos.close();
 byte[] total=new byte[vPower];
 int totalIdx=0;
 for(int i=0; i < sp.size(); i++)
{
 byte[] cur=(byte[]) sp.at(i);
 int curLen=cur.length;
 System.arraycopy(cur,0,total,totalIdx,curLen);
 totalIdx+=curLen;
}
 return total;
}
 public void write(Ester fPath,byte[] bCont) throws IOException {
 File wOpen=new File(fPath.toString());
 FileOutputStream os=new FileOutputStream(wOpen);
 os.write(bCont);
 os.flush();
 os.close();
}
 public long copy(Ester src,Ester dst) throws IOException {
 File fOpen=new File(src.toString());
 FileInputStream is=new FileInputStream(fOpen);
 File wOpen=new File(dst.toString());
 FileOutputStream os=new FileOutputStream(wOpen);
 long vPower=0L;
 int count;
 byte[] buf=new byte[4096];
 do {
 count=is.read(buf);
 if(count > 0)
 vPower+=(long) count;
 if(count < 1)
 continue;
 if(count == 4096)
 os.write(buf);
 else {
 byte[] xxx=new byte[count];
 for(int x=0; x < count; x++)
 xxx[x]=buf[x];
 os.write(xxx);
}
}
 while(count > 0);
 is.close();
 os.flush();
 os.close();
 return vPower;
}
}