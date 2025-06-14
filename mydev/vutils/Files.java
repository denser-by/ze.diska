package mydev.vutils;
 import java.io.File;
 import java.io.IOException;
 public class Files {
 public Files() {
}
 public Ester mkFolder(Ester newItemPath) {
 Ester folder = folder(newItemPath);
File newItem = new File(folder.toString());
 newItem.mkdirs();
 return folder;
}
 public Ester ext(Ester absPath) {
 Ester name = name(absPath);
 long idx = name.indexX('.');
 if(idx >= 0L)
 name = name.sub(idx);
 else if(!name.eq(new Ester("zetupka")))
 name = new Ester("");
 return name;
}
 public Ester folder(Ester absPath) {
 Ester name = name(absPath);
 Ester folder = absPath.sub(0L, absPath.length() - name.length());
 return folder;
}
 public Ester name(Ester absPath) {
 long idx = absPath.indexX('/');
 long idx2 = absPath.indexX('\\');
 long max = idx2 > idx ? idx2 : idx;
Ester name = max > 0L ? absPath.sub(max + 1L) : new Ester(absPath);
 return name;
}
public Ester absPath(Ester relPath) {
File f = new File(relPath.toString());
Ester path = new Ester(f.getAbsolutePath());
boolean b1 = path.ends('/');
boolean b2 = path.ends('\\');
if(f.isDirectory() && !b1 && !b2) path = path.append(c());
return path;
}
 public char c() {
 Ester dir = dir();
 return dir.at(dir.length() - 1L);
}
 public Ester parent() {
 Ester cur = dir();
 long idx = cur.indexX(c());
 cur = cur.sub(0L, cur.length() - 1L);
 idx = cur.indexX(c());
 if(idx > 0L)
 cur = cur.sub(0L, idx + 1L);
 return cur;
}
public Ester cleanFolderPath(Ester path) {
  if(path.ends(new Ester("/")))
 path = path.sub(0L, path.length() - 2L);
 if(path.ends(new Ester("\\")))
 path = path.sub(0L, path.length() - 2L);
 if(path.ends(new Ester(".")))
 path = path.sub(0L, path.length() - 2L);
  if(path.ends(new Ester("./")))
 path = path.sub(0L, path.length() - 3L);
 if(path.ends(new Ester(".\\")))
 path = path.sub(0L, path.length() - 3L);
char c = path.indexX('/') >= 0L ? '/' : '\\';
 return path.append(c);
}
 public Ester dir() {
 File fi = new File("./");
 Ester path = new Ester(fi.getAbsolutePath());
 return cleanFolderPath(path);
}
 public boolean isDir(Ester path) {
 File item = new File(path.toString());
 String[] ll;
 short points = 0;
 if(path.ends(new Ester("/")))
 points += 1;
else if(path.ends(new Ester("\\")))
 points += 1;
else if(item.isDirectory() && (ll=item.list()) != null && ll.length >= 0)
 points += 1;
 return points > 0;
}
public Ester[] files(Ester path) {
 Ester[] response=new Ester[0];
if(isDir(path)) {
  path = absPath(path);
  File fi = new File(path.toString());
  String[] items = fi.list();
  if(items != null && items.length > 0) {
    response=new Ester[items.length];
    for(int i = 0; i < items.length; i++) {
      Ester tmp = new Ester(path).append(items[i]);
      response[i] = isDir(tmp) ? tmp.append(c()) : tmp;      
    } //for
  } //if
} //if
 return response;
}
public Ester[] files() {
 return files(dir());
}
 boolean t1,t2;
 public Ester[] tree(Ester root) {
 System.out.print((t1=!t1 ? !t1 : !t1 &(t2=!t2 ? !t2 : !t1 & t2)) ? "^" : t2 ? ">" : "<");
 Spiska sp = new Spiska();
if(isDir(root))
{
  Ester[] items = files(root);
  for(long i = 0L; i < items.length; i++)
  if(isDir(items[(int)i]))
{
    Ester[] subitems = tree(items[(int)i]);
    for(long j = 0L; j < subitems.length; j++)
 sp.append(subitems[(int)j]);
  }
 else sp.append(items[(int)i]);
}
 else sp.append(root);
long size = sp.size();
Ester[] list = new Ester[(int)size];
for(long x = 0L; x < size; x++)
 list[(int)x] = (Ester)sp.at(x);
return list;
}
public Ester[] tree() {
 return tree(dir());
}
public Ester[] ftree(Ester root) {
Spiska sp = new Spiska();
root = absPath(root);
if(isDir(root)) {
sp.append(root);
String[] items = new File(root.toString()).list();
for(long i = 0L; i < items.length; i++) {
Ester cur = absPath(new Ester(root).append(items[(int)i]));
if(isDir(cur)) {
Ester[] subitems = ftree(cur);
for(long j = 0L; j < subitems.length; j++) sp.append(subitems[(int)j]);
}
}
}
long size = sp.size();
Ester[] list = new Ester[(int)size];
for(long x = 0L; x < size; x++)
 list[(int)x] = (Ester)sp.at(x);
return list;
}
}