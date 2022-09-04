package mydev.extension;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import mydev.vutils.Files;
 import mydev.vutils.Filter;
 import mydev.vutils.Time;
 import mydev.vutils.Progress;
 import mydev.vutils.Karta;
 import mydev.vutils.Metr;
 import mydev.vutils.Ester;
 import mydev.vutils.S;
 public class Extensions {
 Files files;
 Ester extList;
 public Extensions() {
 this.files=new Files();
}
 private Ester enr(Ester orig,long width,char c) {
 Ester buf=new Ester(orig);
 for(long i=0L; i < width-orig.length(); i++)
 buf.append(c);
 return buf;
}
 public void showEmm(Ester[] oni) {
 Ester tmp=new Ester("");
 int count=0;
 for(int i=0; i < oni.length; i++)
{
 tmp.append('\n').append(oni[i]);
 count++;
 if(count > 10)
{
 System.out.print(tmp.toString());
 tmp=new Ester("");
 count=0;
}
}
 if(count > 0)
 System.out.print(tmp.toString());
}
 private Ester size(Karta store,Ester key) {
 return new Metr(((Long) store.get(key.toString())).longValue()).repr(',');
}
 public Ester[] assort(Ester where,boolean asta) {
 Ester[] result=new Ester[0];
 if(files.isDir(where))
{
 Karta htSize=new Karta();
 Karta htNum=new Karta();
 Ester[] chtoto=files.tree(where);
 Progress pro=new Progress(chtoto.length,true);
 long totalSize=0L;
 for(int i=0; i < chtoto.length; i++)
{
 pro.touch();
 if(files.isDir(chtoto[i]))
 continue;
 Ester ext=files.ext(chtoto[i]).lc();
 long size=asta ? sizeof_worthy(chtoto[i]) : sizeof_lavista(chtoto[i]);
 totalSize+=size;
 Long valueSize=(Long) htSize.get(ext.toString());
 Integer valueNum=(Integer) htNum.get(ext.toString());
 if(valueSize == null)
 valueSize=new Long(0L);
 if(valueNum == null)
 valueNum=new Integer(0);
 htSize.put(ext.toString(),new Long(size+valueSize.longValue()));
 htNum.put(ext.toString(),new Integer(1+valueNum.intValue()));
}
 long maxLen1=0L,maxLen2=0L,maxLen3=0L;
 int t=0;
 String[] allExt=htSize.keys();
 for(int i=0; i < allExt.length; i++)
{
 if(allExt[i] == null)
 continue;
 else t+=1;
 Ester extKey=new Ester(allExt[i]);
 maxLen1=extKey.length() > maxLen1 ? extKey.length() : maxLen1;
 Ester value2=new Ester("").append(""+(Integer) htNum.get(extKey.toString()));
 maxLen2=value2.length() > maxLen2 ? value2.length() : maxLen2;
 Ester value3=size(htSize,new Ester(extKey));
 maxLen3=value3.length() > maxLen3 ? value3.length() : maxLen3;
}
 Ester tot1=new Ester("__Total_:_").append(t);
 Ester tot2=new Ester("").append(pro.upperLimit());
 Ester tot3=new Ester("").append(new Metr(totalSize).repr(','));
 maxLen1=tot1.length() > maxLen1 ? tot1.length() : maxLen1;
 maxLen2=tot2.length() > maxLen2 ? tot2.length() : maxLen2;
 maxLen3=tot3.length() > maxLen3 ? tot3.length() : maxLen3;
 chtoto=new Ester[t+1];
 int chIdx=0;
 Ester extBuf=new Ester();
 for(int i=0; i < allExt.length; i++)
{
 if(allExt[i] == null)
 continue;
 Ester extKey=new Ester(allExt[i]);
 extBuf.append(extBuf.length()==0 ? extKey : new Ester(";").append(extKey));
 Ester extVolume=size(htSize,new Ester(extKey));
 Integer extNum=(Integer) htNum.get(extKey.toString());
 chtoto[chIdx++]=new Ester(" "+enr(extKey,maxLen1,' ')+"  "
+enr(new Ester(""+extNum),maxLen2,' ')+" shtukens"+"  "
+enr(extVolume,maxLen3,' ')+" baytouvz :)");
}
 chtoto[chIdx++]=new Ester(""+enr(tot1,maxLen1+3,'_')+enr(tot2,maxLen2+1,'_')
+"shtukens__"+enr(tot3,maxLen3+1,'_')+"baytouvz_:)");
 this.extList=new Ester(extBuf);
 if(chtoto != null && chtoto.length > 0)
 result=chtoto;
}
 return result;
}
 public void work(Ester param,boolean asta) {
 Ester location=files.absPath(param);
 if(!location.ends(files.c()))
 location.append(files.c());
 System.out.print("____Extensions_(where="+location+")");
 Ester[] oni=assort(location,asta);
 showEmm(oni);
}
 public static void main(String[] args) {
 Extensions ext=new Extensions();
 boolean asta=false;
 Ester param=null;
 if(args != null)
 for(int i=0; i < args.length; i++)
 if(new Ester(args[i]).nteq(new Ester("Extensions.class")) && param == null)
 param=new Ester(args[i]);
 if(param == null)
{
 asta=!asta;
 param=new Files().dir();
}
 Time tt=new Time();
 tt.start();
 ext.work(param,asta);
 tt.trust();
System.out.print("\n "+ext.extList+"\n");
 System.out.println("____________Zatrachana_:_"+tt.ess());
 //System.out.print("\n "+ext.extList+"\n");
}
 public long sizeof_lavista(Ester path) {
 File fi=new File(path.toString());
 return fi.length();
}
 public long sizeof_worthy(Ester path) {
 long ping=0L;
 try {
 File fi=new File(path.toString());
 FileInputStream fos=new FileInputStream(fi);
 byte[] buf=new byte[1024];
 int count=1;
 while(count > 0) {
 count=fos.read(buf);
 if(count > 0)
 ping+=(long) count;
}
 fos.close();
}
 catch(IOException ex) {
 new S().out(ex.getMessage());
}
 return ping;
}
}