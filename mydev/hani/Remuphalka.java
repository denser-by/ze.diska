package mydev.hani;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import mydev.vutils.Spiska;
 import mydev.vutils.Files;
 import mydev.vutils.Ester;
 import mydev.vutils.S;
 import mydev.vutils.Time;
import mydev.vutils.Ask;
 public class Remuphalka {
 Files files;
 public Remuphalka() {
 this.files=new Files();
}
 public static void main(String[] args) {
 Remuphalka rm=new Remuphalka();
 Ester param=null;
 for(int i=0; args !=null && param==null && i < args.length; i++)
 if(new Ester(args[i]).nteq(new Ester("Remuphalka.class")))
 param=new Ester(args[i]);
 try {
 if(param != null)
 rm.start(param);
 else if(30 == 30)
 new S().out("Tuta patha ne hapaet...");
 else new S().out("Please specify path, this resource will be deleted.");
}
 catch(IOException ex) {
 new S().out(ex.getMessage());
}
}
 public void start(Ester target) throws IOException {
 long fc=0L,ff=0L;
 Time tt=new Time();
 tt.start();
 target=files.absPath(target);
 if(files.isDir(target))
{
 Ester[] items=files.tree(target);
 Ask aaa = new Ask("You are about to hanihilate " + items.length + " files "
   + "near the " + target);
 if(aaa.areYouSure()) {
   new S().out("");
   for(int i=0; i < items.length; i++) delResource(items[i]);
   fc=(long) items.length;
   Ester[] folders=files.ftree(target);
   sort(folders);
   for(int i=0; i < folders.length; i++) delFolder(folders[i]);
   ff=(long) folders.length;
 }
}
 else {
  Ask bbb = new Ask("You are about to hanihilate 1 file like " + target);
  if(bbb.areYouSure()) {
    delResource(target);
    fc += 1L;
  }
}
 tt.trust();
 System.out.println("__Totally_hanihilated_:_"+fc+"_files_"+ff+"_folders__");
 System.out.println("____________Zatrachana_:_"+tt.ess());
}
 private void delFolder(Ester target) throws IOException {
 new S().outt(new Ester("_del_folder:").append(target));
 new File(target.toString()).delete();
 new S().out("");
}
 private void delResource(Ester target) throws IOException {
 new S().outt(new Ester("_start_to_del:").append(target));
 partialOverwrite(target);
 new S().out("");
}
 private void partialOverwrite(Ester target) throws IOException {
 String t=target.toString();
 File fOpen=new File(t);
 FileInputStream is=new FileInputStream(fOpen);
 byte[] buf=new byte[1021 * 1023+(t.hashCode()
-t.hashCode() / 99 * 99-t.length())];
 int count=is.read(buf);
 for(int i=0; i < count; i++)
 buf[i]=(byte)(0xef |(byte) i);
 FileOutputStream os=new FileOutputStream(fOpen);
 os.write(buf);
 os.flush();
 os.close();
 is.close();
 fOpen.delete();
}
 private void sort(Ester[] aa) {
 for(int i=0; i < aa.length-1; i++)
 for(int j=i+1; j < aa.length; j++)
 if(aa[i].length() < aa[j].length())
{
 Ester tmp=aa[i];
 aa[i]=aa[j];
 aa[j]=tmp;
}
}
}