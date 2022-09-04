package mydev.datel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.Hashtable;
import mydev.aaa.IO;
import mydev.aaa.Enter;


public class Parser implements Getter {

public Parser() {  }

void ah(String path) {
Table t1 = get(path);
System.out.println("t1=" + t1);
}

public static void main(String[] args) {
Parser pr = new Parser();
pr.ah(args[0]);
}

public Table get(String path) {
IO io = Enter.instance().io();
byte[] iodata = null;
try { iodata = io.read(path); } catch(IOException ex) {  }
Table t = null;
if(iodata!=null) {
t = new ChtoTa(iodata);
}
return t;
}
}


interface Item {
String scont();
byte[] bcont();
boolean enclosed();
}


interface Row {
int columns();
boolean enclosed();
Item[] items();
}


interface Table {
char cR = '\r';
char cN = '\n';
char cQ = '"';
char cC = ',';
byte bR = (byte)cR;
byte bN = (byte)cN;
byte bQ = (byte)cQ;
byte bC = (byte)cC;
int columns();
int rows();
Row[] items();
void items(Row[] init);
}


interface Getter {
Table get(String path);
}


interface Setter {
boolean set(String path, Table table);
}


class ChtoTa implements Table {
Row[] vse;
ChtoTa(byte[] data) {
System.out.println("..........................................................");

char[] all = new char[data.length];
for(int i = 0; i < data.length; i++) all[i] = (char)data[i];
System.out.println(new String(all));

System.out.println("..........................................................");

StringBuffer item = new StringBuffer();
boolean encl = false;
Vector cektor = new Vector();
Vector cektora = new Vector();

for(int i = 0; i < data.length; i++)
switch(data[i]) {

case bR: case bN:
if(encl) item.append((char)data[i]); else if(item.toString().length() > 0) {
cektor.addElement(new ShtuKo(item.toString()));
item.setLength(0);
cektora.addElement(new GdeTa(cektor));
cektor = new Vector();
}
continue;

case bQ:
if(item.toString().length()==0) {
encl = true;
item.append((char)data[i]);
} else {
if(encl) {
encl = false;
item.append((char)data[i]);
} else {
encl = true;
//item.append((char)data[i]);
}
}
continue;

case bC: 
if(encl) item.append((char)data[i]); else {
cektor.addElement(new ShtuKo(item.toString()));
item.setLength(0);
}
continue;

default: item.append((char)data[i]); continue;
}

if(item.toString().length() > 0) {
cektor.addElement(new ShtuKo(item.toString()));
item.setLength(0);
cektora.addElement(new GdeTa(cektor));
cektor = null; //cektor = new Vector();
}

this.vse = new GdeTa[cektora.size()];
for(int i = 0; i<vse.length; i++) vse[i] = (Row)cektora.elementAt(i);

System.out.println("..........................................................");
}
public int columns() { return vse[0].columns(); }
public int rows() { return vse.length; }
public Row[] items() { return vse; }
public void items(Row[] init) { this.vse = init; }
public String toString() {
StringBuffer a = new StringBuffer();
a.append("Table (").append(vse.length).append(") {").append(cN);
for(int i  = 0; i < vse.length; i++) a.append(vse[i]).append(cN);
a.append("} Table;");
return a.toString();
}
}


class GdeTa implements Row {
Item[] eta;
GdeTa(Vector r) {
this.eta = new Item[r.size()];
for(int i = 0; i<eta.length; i++) eta[i] = (Item)r.elementAt(i);
}
public int columns() { return eta.length; }
public boolean enclosed() { return eta[0].enclosed(); }
public Item[] items() { return eta; }
public String toString() {
StringBuffer a = new StringBuffer();
a.append(" (").append(eta.length).append(") {");
for(int i  = 0; i < eta.length; i++) { if(i>0) a.append(Table.cC); a.append(eta[i]); }
a.append("};");
return a.toString();
}
}


class ShtuKo implements Item {
boolean enc;
byte[] cont;
ShtuKo(String inp) {
this.enc = inp.startsWith("\"");
this.cont = new byte[enc ? inp.length()-2 : inp.length()];
if(enc) inp = inp.substring(1, inp.length()-1);
for(int i = 0; i < cont.length; i++) cont[i] = (byte)inp.charAt(i);
}
ShtuKo(byte[] inp) {
this.cont = inp;
this.enc = inp[0] == '"';
}
public String scont() {
char[] cha = new char[cont.length];
for(int i = 0; i < cont.length; i++) cha[i] = (char)cont[i];
return new String(cha);
}
public byte[] bcont() { return cont; }
public boolean enclosed() { return enc; }
public String toString() {
StringBuffer a = new StringBuffer();
for(int i = 0; i < cont.length; i++)
if(cont[i]==Table.bR) a.append("\\r"); else
if(cont[i]==Table.bN) a.append("\\n"); else a.append((char)cont[i]);
return a.toString();
}
}