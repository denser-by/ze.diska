package mydev.outecc;
 import java.io.OutputStream;
 import java.io.IOException;
 public class MinComplex extends MinComplexConstsUtils implements ChisloSet,MinComplexConsts {
 private byte[] repa;
 public MinComplex() {
 this.repa=new byte[1];
 repa[0]=tob(B_MIN);
}
 public MinComplex(ChisloSet cs) {
 this((MinComplex) cs);
}
 public MinComplex(MinComplex mc) {
 int pos=0;
 while(pos < mc.repa.length && toi(mc.repa[pos])==B_MIN) {
 pos+=1;
}
 int shirina=mc.repa.length-pos;
 this.repa=new byte[shirina];
 System.arraycopy(mc.repa,pos,repa,0,shirina);
 if(D)
 System.out.println("MinComplex() mc="+mc.toStrDec()+" this="+toStrDec());
}
 public MinComplex(MinComplex mc,int first) {
 byte[] xrep=new byte[first];
 System.arraycopy(mc.repa,0,xrep,0,first);
 this.repa=xrep;
}
 public boolean save4b(byte[] a4b) {
 boolean res=false;
 if(repa != null && a4b != null)
 if(repa.length == a4b.length)
{
 for(int i=0; i < a4b.length; i++)
 a4b[i]=repa[i];
 res=true;
}
 return res;
}
 public boolean load4b(byte[] a4b) {
 this.repa=new MinComplex().repa;
 expand(4);
 boolean res=false;
 while(repa.length==4 && !res) {
 if(repa[0] == a4b[0] && repa[1] == a4b[1] && repa[2] == a4b[2] && repa[3] == a4b[3])
 res=true;
 else incr();
}
 res=repa.length==a4b.length && repa[0]==a4b[0] && repa[1]==a4b[1] && repa[2]==a4b[2] && repa[3]==a4b[3];
 return res;
}
 public void store(OutputStream os) throws IOException {
 os.write(repa);
}
 public boolean hasNextLo() {
 boolean full=false;
 if(repa[repa.length] == tob(B_MIN))
{
 if(repa.length == 1)
 full=true;
 else {
 full=true;
 for(int i=0; i < repa.length-2; i++)
 if(repa[i] != repa[i+1])
{
 full=false;
 break;
}
}
}
 return full;
}
 public boolean hasNextHi() {
 return true;
}
 public ChisloSet minValue() {
 return new MinComplex();
}
 public ChisloSet copyValue() {
 MinComplex obj=new MinComplex();
 byte[] r=new byte[repa.length];
 System.arraycopy(repa,0,r,0,r.length);
 obj.repa=r;
 return obj;
}
 public int toIntDec() throws NumberFormatException {
 int r=0;
 for(int i=0; i < repa.length; i++)
 r=r * B_ASE+toi(repa[i])-B_MIN;
 return r;
}
 public long toLongDec() throws NumberFormatException {
 long r=0L;
 for(int i=0; i < repa.length; i++)
 r=r * B_ASE+tol(repa[i])-B_MIN;
 return r;
}
 public String toStrDec() {
 StringBuffer buf=new StringBuffer();
 for(int i=0; i < repa.length; i++)
 buf.append(toi(repa[i])-B_MIN).append(".");
 return buf.toString();
}
 public String toStrHex() {
 return "";
}
 public byte[] toBytes() {
 return null;
}
 public void setValue(byte[] valueBytes) {
 this.repa=new byte[valueBytes.length];
 System.arraycopy(valueBytes,0,repa,0,repa.length);
}
 public void setValue(int value) {
 setValue((long) value);
}
 public void setValue(long initial) {
 int len=1;
 long tmp=initial / B_ASE;
 while(tmp > 0L) {
 tmp /=B_ASE;
 len++;
}
 repa=new byte[len];
 int pos=len-1;
 tmp=initial;
 while(pos >=0) {
 long k=tmp / B_ASE;
 long d=tmp-k * B_ASE;
 tmp=k;
 repa[pos--]=tob(d+B_MIN);
}
}
 public boolean ls(ChisloSet an) {
 return cmp(an) < 0;
}
 public boolean gt(ChisloSet an) {
 return cmp(an) > 0;
}
 public boolean lseq(ChisloSet an) {
 return cmp(an) <=0;
}
 public boolean gteq(ChisloSet an) {
 return cmp(an) >=0;
}
 public boolean eq(ChisloSet an) {
 return cmp(an)==0;
}
 public boolean eqno(ChisloSet an) {
 return cmp(an) !=0;
}
 public int cmp(ChisloSet cs) {
 MinComplex an=(MinComplex) cs;
 if(repa.length == an.repa.length)
{
 for(int i=0; i < repa.length; i++)
 if(toi(repa[i]) > toi(an.repa[i]))
 return 1;
 else if(toi(repa[i]) < toi(an.repa[i]))
 return-1;
 return 0;
}
 else {
 int min=repa.length < an.repa.length ? repa.length : an.repa.length;
 if(repa.length < an.repa.length)
{
 int delta=an.repa.length-min;
 for(int i=0; i < delta; i++)
 if(toi(an.repa[i]) > B_MIN)
 return-1;
 for(int i=0; i < min; i++)
 if(toi(repa[i]) > toi(an.repa[i+delta]))
 return 1;
 else if(toi(repa[i]) < toi(an.repa[i+delta]))
 return-1;
}
 else {
 int delta=repa.length-min;
 for(int i=0; i < delta; i++)
 if(toi(repa[i]) > B_MIN)
 return 1;
 for(int i=0; i < min; i++)
 if(toi(repa[i+delta]) > toi(an.repa[i]))
 return 1;
 else if(toi(repa[i+delta]) < toi(an.repa[i]))
 return-1;
}
}
 return 0;
}
 public int cmp_(ChisloSet cs) {
 MinComplex an=(MinComplex) cs;
 if(repa.length > an.repa.length)
{
 an.expand(repa.length);
}
 else if(repa.length < an.repa.length)
{
 expand(an.repa.length);
}
 for(int i=0; i < repa.length; i++)
{
 if(D)
 System.out.print(" ("+toi(repa[i])+","+toi(an.repa[i])+") ");
 if(toi(repa[i]) > toi(an.repa[i]))
 return 1;
 else if(toi(repa[i]) < toi(an.repa[i]))
 return-1;
}
 return 0;
}
 public void incr() {
 incrDecr(B_STP,true);
}
 public void decr() {
 incrDecr(B_STP,!true);
}
 public void incr2() {
 incrDecr(2 * B_STP,true);
}
 public void decr2() {
 incrDecr(2 * B_STP,!true);
}
 public void incrDecr(int step,boolean up) {
 int lastLastNew=toi(repa[repa.length-1]);
 lastLastNew=up ? lastLastNew+step : lastLastNew-step;
 boolean radix=lastLastNew < B_MIN || lastLastNew > B_MAX;
 if(radix)
{
 boolean full=false;
 if(repa.length == 1)
 full=true;
 else {
 full=true;
 for(int i=0; i < repa.length-2; i++)
 if(repa[i] != repa[i+1])
{
 full=false;
 break;
}
}
 if(full && up)
{
 expand(repa.length+1);
}
 else if(full && !up)
{
 return;
}
 if(up)
{
 repa[repa.length-1]=tob(lastLastNew-B_ASE);
}
 else {
 repa[repa.length-1]=tob(lastLastNew+B_ASE);
}
 int pos=(repa.length-1)-1;
 while(pos >=0) {
 if(up)
{
 if(toi(repa[pos]) < B_MAX)
{
 repa[pos]=tob(toi(repa[pos])+B_STP);
 radix=false;
}
 else {
 repa[pos]=tob(B_MIN);
 pos--;
}
}
 else {
 if(toi(repa[pos]) > B_MIN)
{
 repa[pos]=tob(toi(repa[pos])-B_STP);
 radix=false;
}
 else {
 repa[pos]=tob(B_MAX);
 pos--;
}
}
 if(!radix)
 pos=-1;
}
}
 else {
 repa[repa.length-1]=tob(lastLastNew);
}
}
 public ChisloSet add(ChisloSet cs) {
 MinComplex an=(MinComplex) cs;
 int max=repa.length > an.repa.length ? an.expand(repa.length) : expand(an.repa.length);
 max-=1;
 int d=0;
 for(int i=max; i >=0; i--)
{
 int x2=toi(an.repa[i])-B_MIN;
 int x1=toi(repa[i])-B_MIN;
 int nx=x1+x2+d;
 d=0;
 if(nx > B_ASE)
{
 nx-=B_ASE;
 d=1;
}
 nx+=B_MIN;
 repa[i]=tob(nx);
}
 if(d == 1)
{
 expand(repa.length+1);
 repa[0]=tob(toi(repa[0])+1);
}
 return this;
}
 public ChisloSet sub(ChisloSet cs) {
 MinComplex an=(MinComplex) cs;
 int max=repa.length > an.repa.length ? an.expand(repa.length) : expand(an.repa.length);
 max-=1;
 if(ls(an))
{
 for(int i=max; i >=0;--i)
 repa[i]=tob(B_MIN);
 return this;
}
 int d=0;
 for(int i=max; i >=0; i--)
{
 int x2=toi(repa[i]);
 int x1=toi(an.repa[i]);
 int nx=x2-x1+d;
 d=0;
 if(nx < 0)
{
 nx+=B_ASE;
 d=-1;
}
 repa[i]=tob(nx+B_MIN);
}
 if(d == -1)
{
 expand(repa.length+1);
 repa[0]=tob(toi(repa[0])-1);
}
 return this;
}
 void shift(int n) {
 byte[] nr=new byte[repa.length+n];
 System.arraycopy(repa,0,nr,0,repa.length);
 for(int i=repa.length; i < repa.length+n; i++)
 nr[i]=tob(B_MIN);
 this.repa=nr;
}
 void mlt_2(int mx) {
 expand(repa.length+1);
 int d=0;
 for(int i=repa.length-1; i >=1; i--)
{
 int n=toi(repa[i])-B_MIN;
 n=n * mx+d;
 d=n / B_ASE;
 n=n-d * B_ASE;
 repa[i]=tob(n+B_MIN);
}
 if(d > 0)
 repa[0]=tob(d+B_MIN);
}
 public ChisloSet mlt(ChisloSet cs) {
 MinComplex an=(MinComplex) cs;
 MinComplex result=new MinComplex();
 int first=0;
 for(int i=an.repa.length-1; i >=0; i--)
{
 int mplier=toi(an.repa[i])-B_MIN;
 MinComplex mc=new MinComplex();
 mc.setValue(this.repa);
 mc.mlt_2(mplier);
 if(first == 0)
 first=1;
 else mc.shift(first++);
 result.add(mc);
}
 this.repa=result.repa;
 return this;
}
 boolean D=!true;
 MinComplex div_d(MinComplex an,MinComplex tmp) {
 MinComplex r=new MinComplex(an);
 MinComplex d=new MinComplex();
 int dd=1;
 while(r.ls(tmp)) {
 r.add(an);
 dd++;
}
 while(r.gt(tmp)) {
 r.sub(an);
 dd--;
}
 d.setValue(dd);
 tmp.sub(r);
 if(D)
 System.out.println(" tmp="+tmp.toStrDec()+" r="+r.toStrDec()+" an="+an.toStrDec()+" d="+d.toStrDec());
 return d;
}
 public ChisloSetPair div(ChisloSet cs) {
 MinComplexPair result=new MinComplexPair();
 MinComplex an=(MinComplex) cs;
 if(repa.length < an.repa.length)
{
 result.smth=new MinComplex(an);
 return result;
}
 int idx=an.repa.length,shift=0;
 MinComplex tmp=new MinComplex(this,an.repa.length);
 if(D)
{
 System.out.println("SUB");
 System.out.println(" idx="+idx+"  tmp="+tmp.toStrDec()+"  this="+toStrDec()+"  an="+an.toStrDec()+"  result="+result.toStrDec());
}
 while(idx < repa.length) {
 if(tmp.gteq(an))
{
 MinComplex d=div_d(an,tmp);
 shift=0;
 result.shift(1);
 result.add(d);
}
 else {
 int newValue=toi(repa[idx++])-B_MIN;
 MinComplex nv=new MinComplex();
 nv.setValue(newValue);
 tmp.shift(1);
 tmp.add(nv);
 if(D)
 System.out.println(" idx="+idx+"  tmp="+tmp.toStrDec()+" afteradd");
 shift++;
}
}
 if(D)
 System.out.println(" idx="+idx+" shift="+shift+"  tmp="+tmp.toStrDec()+"  result="+result.toStrDec());
 if(tmp.gteq(an))
{
 MinComplex d=div_d(an,tmp);
 result.shift(1+shift-1);
 result.add(d);
 shift=0;
}
 else if(shift > 0)
{
 result.shift(shift);
}
 if(D)
 System.out.println("_  result="+result.toStrDec());
 result.smth=tmp;
 return result;
}
 public int minCellValue() {
 return B_MIN;
}
 public int maxCellValue() {
 return B_MAX;
}
 public String minCellValueHex() {
 return Integer.toHexString(minCellValue());
}
 public String maxCellValueHex() {
 return Integer.toHexString(maxCellValue());
}
 public int expand(int repix) {
 int delta=repix-repa.length;
 if(delta < 1)
 return repa.length;
 byte[] repn=new byte[repa.length+delta];
 System.arraycopy(repa,0,repn,delta,repa.length);
 for(int i=0; i < delta; i++)
 repn[i]=tob(B_MIN);
 this.repa=repn;
 return repix;
}
}
 interface MinComplexConsts {
 int MAX_=0xFF;
 int B_STP=0x01;
 int B_ASE=251;
 int B_MIN=0x03;
 int B_MAX=B_MIN+B_ASE-B_STP;
 MinComplex MC_0=new MinComplex();
}
 class MinComplexConstsUtils implements MinComplexConsts {
 public boolean ls(byte b1,byte b2) {
 return(b1 < 0 ? b1+MAX_ : b1) <(b2 < 0 ? b2+MAX_ : b2);
}
 public boolean lseq(byte b1,byte b2) {
 return(b1 < 0 ? b1+MAX_ : b1) <=(b2 < 0 ? b2+MAX_ : b2);
}
 public boolean gt(byte b1,byte b2) {
 return(b1 < 0 ? b1+MAX_ : b1) >(b2 < 0 ? b2+MAX_ : b2);
}
 public boolean gteq(byte b1,byte b2) {
 return(b1 < 0 ? b1+MAX_ : b1) >=(b2 < 0 ? b2+MAX_ : b2);
}
 public int toi(byte b1) {
 return(int)(b1 < 0 ? b1+MAX_ : b1);
}
 public long tol(byte b1) {
 return(long)(b1 < 0 ? b1+MAX_ : b1);
}
 public byte tob(int d) {
 return(byte)(d > 127 ? d-MAX_ : d);
}
 public byte tob(long d) {
 return(byte)(d > 127 ? d-MAX_ : d);
}
}
 class MinComplexPair extends MinComplex implements ChisloSetPair {
 MinComplex smth;
 public MinComplexPair() {
 this.smth=new MinComplex();
}
 public ChisloSet rest() {
 return smth;
}
}
