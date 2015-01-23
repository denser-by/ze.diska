package mydev.outecc;
 import java.io.OutputStream;
 import java.io.IOException;
 public interface ChisloSet {
 boolean hasNextLo();
 boolean hasNextHi();
 ChisloSet minValue();
 ChisloSet copyValue();
 int toIntDec() throws NumberFormatException;
 long toLongDec() throws NumberFormatException;
 String toStrDec();
 String toStrHex();
 byte[] toBytes();
 void store(OutputStream os) throws IOException;
 void setValue(byte[] valueBytes);
 void setValue(int value);
 void setValue(long value);
 boolean ls(ChisloSet an);
 boolean gt(ChisloSet an);
 boolean lseq(ChisloSet an);
 boolean gteq(ChisloSet an);
 boolean eq(ChisloSet an);
 boolean eqno(ChisloSet an);
 int cmp(ChisloSet an);
 void incr();
 void decr();
 void incr2();
 void decr2();
 void incrDecr(int step,boolean up);
 ChisloSet add(ChisloSet an);
 ChisloSet sub(ChisloSet an);
 ChisloSet mlt(ChisloSet an);
 ChisloSetPair div(ChisloSet an);
 int minCellValue();
 int maxCellValue();
 String minCellValueHex();
 String maxCellValueHex();
 int expand(int repix);
 boolean save4b(byte[] a4b);
 boolean load4b(byte[] a4b);
}
 interface ChisloSetPair extends ChisloSet {
 ChisloSet rest();
}
