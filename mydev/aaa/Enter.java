package mydev.aaa;
public class Enter {
private static Enter odin;
private static Object chtoto = new Object();
Enter() {  }
public static Enter instance() {
if(odin == null) synchronized(chtoto) { if(odin == null) odin = new Enter(); }
return odin;
}
public Okno okno() { return new Okno(); }
public Sleeper sleeper() { return new Sleeper(); }
public IO io() { return new IO(); }
public Slipstream slipstream() { return new Slipstream(); }
public Shorty shorty() { return new Shorty(); }
public Inta inta() { return new Inta(); }
public Longy longy() { return new Longy(); }
public Sometime sometime() { return new Sometime(); }
}