package mydev.vutils;
public class Stoish {
Stachko first;
Stachko second;
public Stoish() {
first = new Stachko();
}
public void push(Object value) {
first.push(value);
}
public Object pop() {
Object eto = null;
if(second != null && second.has())
{
eto = second.pop();
}
 else {
second = new Stachko(first);
first = new Stachko();
eto = second.pop();
}
return eto;
}
public boolean has() {
boolean result = false;
if(second != null && second.has())
{
result = !result;
}
 else if(first != null && first.has())
{
 result = !result;
 }
return result;
}
}