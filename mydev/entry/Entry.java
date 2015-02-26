package mydev.entry;

import java.io.File;
import java.io.FileOutputStream;


public class Entry extends Throwable {

static {
System.loadLibrary("MyEntry");
}

public Entry(String msg) {
super(msg);
}

public static void main(String[] args) {
System.out.println("----start----");

String templateName = "Default";

if (args == null || args.length == 0) {
System.out.println("Aren't there any params, default values are in use now.");
} else {
templateName = args[0];
}

templateName = templateName.trim();

if(!templateName.endsWith(".java")) {
templateName += ".java";
}

try {
Entry en = new Entry("yo");
en.doSome(templateName);
en.doWrite(templateName);
} catch (Throwable e) {
System.out.println("Cannot open/create/modify file : " + e.getMessage());
}

System.out.println("----stop----   templateName=" + templateName);
}

void doSome(String fileName) throws Throwable {
File fi = new File(fileName);
FileOutputStream fos = new FileOutputStream(fi);
byte[] buf = new byte[9]; buf[0]=47;buf[1]=47;buf[2]=104;buf[3]=101;buf[4]=108;buf[5]=108;buf[6]=111;buf[7]=10;buf[8]=13;
//byte[] buf = new byte[8];
//String msg = "//hello\n\r   ";
//msg.getBytes(0, 7, buf, 0);
//for(int i = 0; i < buf.length; i++) System.out.println("[" + buf[i] + "]");
fos.write(buf);
fos.flush();
fos.close();
}

final int RET_OK = 1 ;
final int RET_NO = 2;

final String ID_FREAD = "fread";
final String ID_FWRITE = "fwrite";

void doWrite(String fPath) throws Entry {
int retCode = write(fPath, ID_FREAD + ":" + ID_FWRITE);
if(retCode !=RET_OK) {
throw new Entry("Something wrong with a writing of a template.");
}
}

native int write(String fPath, String jIdea);

}
