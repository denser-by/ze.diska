package mydev.vutils;
public class Progress {
final short STEP = 13;
boolean print;
short percentage;
short updateCount;
long curValue;
long upperLimit;
public Progress(long upperLimit, boolean print) {
if(print)
 System.out.print(new Ester("__Progress(0,").append(upperLimit).append(")__"));
this.upperLimit = upperLimit;
this.curValue = 0L;
this.updateCount = 0;
this.print = print;
}
public long upperLimit() {
 return upperLimit;
 }
public void touch() {
curValue++;
updateCount++;
if(updateCount > STEP)
{
updateCount -= STEP;
short tmp = (short)(curValue*100L/upperLimit);
if(tmp > percentage)
{
percentage = tmp;
if(print)
 System.out.print(new Ester("").append(tmp).append('.').toString());
}
}
}
public short get() {
 return percentage;
 }
}