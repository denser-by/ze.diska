package mydev.vutils;
import mydev.vutils.Ester;
public class Order {
private static void testString() {
String shluha = " ";
String[] aa = {"ddd", "aaaa", "1111", "zzzz", "aguu", "buuaaa", 
 "hhh", "qqq", "q", "qqqq", "333", "555", "222"};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
private static void testInt() {
String shluha = "  ";
int[] aa = {9, 1, 44, 7, -5, 0, 22};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
private static void testLong() {
String shluha = "   ";
long[] aa = {109, 101, 144, 107, -105, 0, 122};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
private static void testShort() {
String shluha = "    ";
short[] aa = {-109, -101, -144, -107, 105, 0, -122, 99, 32456};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
private static void testFloatMore() {
String shluha = "  ";
float[] aa = {-109.1f, -101.2f, -144.3f, -107.4f, 105.5f, 0.6f, -122.7f, 99.9f, 99.8f};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
private static void testDouble() {
String shluha = " ";
double[] aa = {-109.3, -109.9, -144.3, -107.4, 105.5, 0.6, -122.7, 99.9, 99.8};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
private static void testEster() {
String shluha = "  ";
Ester[] aa = { new Ester("ddd"), new Ester("aaaa"), new Ester("1111"), 
 new Ester("zzzz"), new Ester("aguu"), new Ester("buuaaa"), 
 new Ester("hhh"), new Ester("qqq"), new Ester("q"), new Ester("qqqq"),
 new Ester("333"), new Ester("555"), new Ester("222")};
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
Order or = new Order();
or.order(aa);
System.out.println("");
for(int a = 0; a < aa.length; a++) System.out.print(shluha + aa[a]);
System.out.println("");
}
public static void main(String[] args) {
System.out.println("Hello");
testString();
testInt();
testLong();
testShort();
testFloatMore();
testDouble();
testEster();
System.out.println("Finish");
Ester s1 = new Ester("ddd");
Ester s2 = new Ester("aguu");
System.out.println("(" + s1 + ").less(" + s2 + ")==" + s1.less(s2));
System.out.println("(" + s2 + ").less(" + s1 + ")==" + s2.less(s1));
}
public void order(String[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(0 < values[a].compareTo(values[b])) {
String tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
public void order(int[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(0 < values[a] - values[b]) {
int tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
public void order(long[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(0L < values[a] - values[b]) {
long tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
public void order(short[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(0 < values[a] - values[b]) {
short tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
public void order(float[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(0.0f < values[a] - values[b]) {
float tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
public void order(double[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(0.0 < values[a] - values[b]) {
double tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
public void order(Ester[] values) {
long N = (long)values.length;
for(int a = 0; a < N - 1; a++)
for(int b = a + 1; b < N; b++)
if(values[b].less(values[a])) {
Ester tmp = values[a];
values[a] = values[b];
values[b] = tmp;
}
}
}