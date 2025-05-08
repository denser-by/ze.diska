package mydev.vutils;
public class S {
final Ester A = new Ester("E4");
final Ester B = new Ester("E4");
final Ester C = new Ester("\n");
public S() {
  }
public S out(Ester obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(Ester obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(String obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(String obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(int obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(int obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(long obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(long obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(short obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(short obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(byte obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(byte obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(char obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(char obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(int[] obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(int[] obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(long[] obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(long[] obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(short[] obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(short[] obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(byte[] obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(byte[] obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
public S out(char[] obj) {
 dos(new Ester(A).append(obj).append(B).append(C));
 return this;
 }
public S outt(char[] obj) {
 dos(new Ester(A).append(obj).append(B));
 return this;
 }
private void dos(Ester R) {
 System.out.print(R.toString());
 }
}