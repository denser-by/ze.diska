package mydev.aaa;
public class Slip {
String frameId;
int size;
byte[] data;
public Slip(byte[] data, String frameId) {
this.frameId = frameId;
this.size = data.length;
this.data = data;
}
public Slip(String dataStr, String frameId) {
this.frameId = frameId;
this.size = dataStr.length();
this.data = new byte[size];
for(int i = 0; i < size; i++) data[i] = (byte)dataStr.charAt(i);
}
public char[] symbols() {
char[] oni = new char[size];
for(int i = 0; i < size; i++) oni[i] = (char)data[i];
return oni;
}
}