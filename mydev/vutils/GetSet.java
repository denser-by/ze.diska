package mydev.vutils;
public class GetSet {
long minIndex;
long maxIndex;
Object[] vse;
public GetSet() {
 minIndex = maxIndex = -1;
 }
public Object get(long index) {
Object geta = null;
int otkuda = (int)(index - minIndex);
if(index > minIndex-1)
 if(index < maxIndex+1)
 geta = vse[otkuda];
return geta;
}
public void set(long index, Object et) {
if(vse == null)
{
 minIndex = maxIndex = index;
 vse = new Object[(int)minSize()];
 }
int kuda = 0;
if(index < minIndex)
{
long start = minIndex - index;
minIndex = index;
kuda = 0;
Object[] vsene = new Object[(int)minSize()];
System.arraycopy(vse, 0, vsene, (int)start, vse.length);
vse = vsene;
}
 else if(index > maxIndex)
{
maxIndex = index;
kuda = (int)(index - minIndex);
Object[] vsehda = new Object[(int)minSize()];
System.arraycopy(vse, 0, vsehda, 0, vse.length);
vse = vsehda;
}
 else {
kuda = (int)(index - minIndex);
}
vse[kuda] = et;
}
public long minSize() {
 return maxIndex - minIndex + 1;
 }
}