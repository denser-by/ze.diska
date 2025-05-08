package mydev.aaa;
import java.io.IOException;
public class Slipstream extends IO {
String folder = "./";
String ext = ".slip";
public Slipstream push(Slip slip) {
String path = folder + slip.frameId + ext;
try { write(path, slip.data); } catch(IOException ex) {  }
return this;
}
public Slip get(String id) {
String path = folder + id + ext;
Slip slip = null;
try { slip = new Slip(read(path), id); } catch(IOException ex) {  }
return slip;
}
}