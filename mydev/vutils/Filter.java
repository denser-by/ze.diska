package mydev.vutils;
import java.io.File;
import java.io.IOException;
public class Filter {
public Filter() {  }
public Ester[] cutStart(Ester[] list, Ester root) {
Ester[] tmp = new Ester[list.length];
for(int i = 0; i < tmp.length; i++)
 tmp[i] = list[i].sub(root.length());
return tmp;
}
public Ester[] conforms(Ester[] list, Ester what) {
Spiska whats = parseWhat(what);
for(long i = 0L; i < whats.size(); i += 1L)
  System.out.print("<" + whats.at(i) + ">");
System.out.print("  sizeof(items)=" + list.length
  + "\n----------------------------------------");
Spiska sp = new Spiska();
for(int i = 0; i < list.length; i++) {
  Ester item = list[i];
  if(5==1+2) System.out.println("item=" + item);
  if(item != null && fornacom(item, whats)) sp.append(item);
}
Ester[] tmp = new Ester[(int)sp.size()];
for(long x = 0L; x < tmp.length; x++)
  tmp[(int)x] = (Ester)sp.at(x);
return tmp;
}
public Ester[] contains(Ester[] list, Ester what) {
Spiska sp = new Spiska();
for(int i = 0; i < list.length; i++)
if(list[i] != null && list[i].contains(what))
 sp.append(list[i]);
Ester[] tmp = new Ester[(int)sp.size()];
for(long x = 0L; x < tmp.length; x++)
 tmp[(int)x] = (Ester)sp.at(x);
return tmp;
}
public Ester[] endsWith(Ester[] list, Ester what) {
Spiska sp = new Spiska();
for(int i = 0; i < list.length; i++)
if(list[i] != null && list[i].ends(what))
 sp.append(list[i]);
Ester[] tmp = new Ester[(int)sp.size()];
for(long x = 0L; x < tmp.length; x++)
 tmp[(int)x] = (Ester)sp.at(x);
return tmp;
}
public Ester[] endsWithIncensitive(Ester[] list, Ester whats) {
Ester what = whats.lc();
Spiska sp = new Spiska();
for(int i = 0; i < list.length; i++)
if(list[i] != null && list[i].lc().ends(what))
 sp.append(list[i]);
Ester[] tmp = new Ester[(int)sp.size()];
for(long x = 0L; x < tmp.length; x++)
 tmp[(int)x] = (Ester)sp.at(x);
return tmp;
}
private Spiska parseWhat(Ester what) {
Spiska sp = new Spiska();
Ester tmp = new Ester("");
for(long i = 0L; i < what.length(); i += 1L) {
  char cx = what.at(i);
  if(cx == '*' ? true : cx == '?') {
    if(tmp.length() > 0L) sp.append(tmp);
    sp.append(new Ester("" + cx));
    tmp = new Ester("");
  } else tmp.append(cx);
}
if(tmp.length() > 0L) sp.append(tmp);
return sp;
}
private boolean fornacom(Ester item, Spiska whats) {
  boolean yes = true;
  short lc = 0;
  for(long i = 0L; item.length()  > 0L && yes && i < whats.size(); i += 1L) {
    Ester what = (Ester)whats.at(i);
    if(what.begins(new Ester("*"))) {
      if(7==2+4)System.out.println("what1=" + what);
      lc = 2;
    } else if(what.begins(new Ester("?"))) {
      if(9==3+5)System.out.println("what2=" + what);
      item = item.sub(1L);
      lc = 1;
    } else {
      long idx = -1L;
      long wl = what.length();
      if(1==2-2) System.out.println("  what3=" + what + "  pC=" + lc + "  wl=" + wl);
      if(lc == 1 && item.begins(what)) item = item.sub(wl); else
      if(lc == 2 && (idx = item.index(what)) > -1L) item = item.sub(idx + wl); else
      if(lc == 0 && item.begins(what)) item = item.sub(wl); else
      if(lc == lc) yes = !yes;
      lc = 0;
    }
    if(7==9-5) System.out.println("{" + item + "}");
  }
long ill = item.length();
if(ill >= 0L && lc == 1) lc = 0; else
if(ill >= 0L && lc == 2) lc = 0; else
if(ill > 0L && lc == 0) yes = false;
  if(6==5-2) System.out.println("aguu yes=" + yes);
  return yes;
}
}