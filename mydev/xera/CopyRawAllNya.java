package mydev.xera;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.lang.StringBuffer;
 import mydev.vutils.Files;
 import mydev.vutils.Filter;
 import mydev.vutils.S;
 import mydev.vutils.Ester;
 import mydev.vutils.Time;
 import mydev.vutils.Metr;
 public class CopyRawAllNya {
 Files files;
 Filter ter;
 public CopyRawAllNya() {
 this.files=new Files();
 this.ter=new Filter();
}
 public long start(Ester src,Ester dst) throws IOException {
 Ester dst2=new Ester(dst);
 Time tt=new Time();
 tt.start();
 long total=0L;
 long num=0L;
 src=files.absPath(src);
 dst=files.absPath(dst);
 if(files.isDir(src))
 dst=files.cleanFolderPath(dst);
 if(files.isDir(src) == false)
{
 if(files.isDir(dst2))
 dst=dst2;
 dst=files.isDir(dst) ? new Ester(dst).append(files.name(src)) : dst;
 total+=readWrite(src,dst);
 num+=1;
}
 else {
 Ester[] srcItems=files.tree(src);
 Ester[] relNamesSrc=ter.cutStart(srcItems,src);
 Ester[] ptp=new Ester[srcItems.length];
 for(int i=0; i < ptp.length; i++)
 ptp[i]=files.absPath(new Ester(dst).append(relNamesSrc[i]));
 for(int i=0; i < ptp.length; i++)
{
 files.mkFolder(ptp[i]);
 total+=readWrite(srcItems[i],ptp[i]);
 num+=1;
}
}
 tt.trust();
 if(total >= 0L)
{
 System.out.println("__Total_:_"+new Metr(total).repr(',')+" bytes ("
+num+" file(s)) copied from <"+src+"> to <"+dst+">__");
 System.out.println("            Zatrachana_:_"+tt.ess());
}
 return total;
}
 private long readWrite(Ester srcPath,Ester dstPath) throws IOException {
 new S().outt(new Ester("_cp_start:_{").append(srcPath).append("}=>=>{"
).append(dstPath).append("}"));
 files.mkFolder(dstPath);
 File srcFile=new File(srcPath.toString());
 File dstFile=new File(dstPath.toString());
 FileInputStream is=new FileInputStream(srcFile);
 FileOutputStream os=new FileOutputStream(dstFile);
 byte[] buf=new byte[8888];
 long total=0L;
 long delta=0L;
 int count=1;
 while(count > 0) {
 count=is.read(buf);
 if(count > 0)
{
 if(count == buf.length)
 os.write(buf);
 else {
 byte[] xxx=new byte[count];
 for(int x=0; x < count; x++)
 xxx[x]=buf[x];
 os.write(xxx);
}
 total+=(long) count;
 delta+=(long) count;
}
 if(delta > 3L * 1024L * 1024L)
{
 new S().outt(new Ester("__").append(total / 1024L).append("k"));
 delta=0L;
}
}
 os.flush();
 os.close();
 is.close();
 new S().out(new Ester("__").append(new Metr(total).repr(',')
).append("_baytouvz_:)"));
 return total;
}
 public static void main(String[] args) {
 CopyRawAllNya cp=new CopyRawAllNya();
 Ester src=null;
 Ester dst=null;
 for(int i=0; args !=null && i < args.length; i++)
 if(new Ester(args[i]).nteq(new Ester("CopyRawAllNya.class")))
{
 if(src == null)
 src=new Ester(args[i]);
 else if(dst == null)
 dst=new Ester(args[i]);
}
 try {
 if(src != null && dst != null)
 cp.start(src,dst);
 else new S().out("Two parameters are required: source and destination.");
}
 catch(IOException ex) {
 new S().out(ex.getMessage());
}
}
}