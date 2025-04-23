package mydev.vutils;
import java.io.IOException;
public class Ask {
final Ester OK = new Ester("yes");
final Ester STD = new Ester(" ?  {Yes or another} + [Enter]");
final short BS = 4;
Ester question;
public Ask(String question) {
this.question = new Ester(question);
}
public Ask(Ester question) {
this.question = new Ester(question);
}
public boolean areYouSure() {
boolean result = false;
System.out.println(new Ester(question).append(STD));
byte[] buf = new byte[BS];
try {
 System.in.read(buf);
 }
 catch(IOException ex) {
  }
char[] ch = new char[BS];
int idx = 0;
ch[idx]=(char)buf[idx++];
ch[idx]=(char)buf[idx++];
ch[idx]=(char)buf[idx++];
ch[idx]=(char)buf[idx++];
if(new Ester(ch).lc().begins(OK))
 result = !result;
return result;
}
}