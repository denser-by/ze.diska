package mydev.za; import java.util.Vector; import mydev.vutils.Data; import mydev.vutils.Ester; public class Zetupka { private Vector lines; public Zetupka() { super(); this.lines=new Vector();} public boolean isReadyFork() { if(hasLine(LineType.TypeFiles0)) return false; if(hasLine(LineType.TypeEE)) return false; if(hasLine(LineType.TypeItems)) return false; if(!(hasLine(LineType.TypeFiles) && hasLine(LineType.TypeMezto))) return false; return true;} public Zetupka loadLines(String content) { Ester curLine=new Ester(""); int lineNum=0; for(int i=0; i < content.length(); i++) { char ch=content.charAt(i); if(ch=='\n') { lines.addElement(new ZetupkaLine(++lineNum,curLine)); curLine=new Ester("");} else curLine.append(ch);} return this;} public String ambu(String content) { loadLines(content); transformLines(); Ester result=new Ester(""); for(int i=0; i < lines.size(); i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); result.append(line.getContent()).append('\n');} return result.toString();} void transformLines() { if(hasLine(LineType.TypeFiles0) && hasLine(LineType.TypeItems)) apply(new ZeroFilesRule(this)); if(hasLine(LineType.TypeEE)) apply(new EELineRule(this)); if(hasLine(LineType.TypeEmpty)) apply(new ReduceEmptiesRule(this)); if(hasLine(LineType.TypeEmpty)) apply(new RemoveLastEmptyRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleVutilsRule.REM_PTRN)) apply(new RemoveDoubleVutilsRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleM3dRule.REM_PTRN)) apply(new RemoveDoubleM3dRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleSqProjRule.REM_PTRN)) apply(new RemoveDoubleSqProjRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleCsRule.REM_PTRN)) apply(new RemoveDoubleCsRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleCsProfileRule.REM_PTRN)) apply(new RemoveDoubleCsProfileRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleUixRule.REM_PTRN)) apply(new RemoveDoubleUixRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleCndataRule.REM_PTRN)) apply(new RemoveDoubleCndataRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleAboutRule.REM_PTRN)) apply(new RemoveDoubleAboutRule(this)); if(hasCustomLine(LineType.TypeFiles,RemoveDoubleOneWayConvRule.REM_PTRN)) apply(new RemoveDoubleOneWayConvRule(this));} private void apply(ZetupkaTransformRule rule) { rule.apply();} boolean hasLine(LineType type) { for(int i=0; i < lines.size(); i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.kindOf(type)) return true;} return false;} public ZetupkaLine[] getLines(LineType type) { Vector result=new Vector(); for(int i=0; i < lines.size(); i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.kindOf(type)) result.addElement(line);} ZetupkaLine[] arr=new ZetupkaLine[(int) result.size()]; for(int i=0; i < arr.length; i++) arr[i]=(ZetupkaLine) result.elementAt(i); return arr;} public ZetupkaLine get(LineType type) { for(int i=0; i < lines.size(); i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.kindOf(type)) return line;} return null;} public void replace(ZetupkaLine itemsLine,LineType newLineType) { for(int i=0; i < lines.size(); i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.getNumber()==itemsLine.getNumber()) { line.transform(newLineType); return;}}} public void replace(ZetupkaLine files0Line,LineType newLineType,Ester content) { for(int i=0; i < lines.size(); i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.getNumber()==files0Line.getNumber()) { line.transform(newLineType,content); return;}}} public boolean hasCustomLine(LineType type,String pattern) { for(int i=0; i < lines.size()-1; i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.kindOf(type) && line.getContent().contains(new Ester(pattern))) return true;} return false;} public boolean hasDoubleLine(LineType type) { for(int i=0; i < lines.size()-1; i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); ZetupkaLine line2=(ZetupkaLine) lines.elementAt(i+1); if(line.kindOf(type) && line2.kindOf(type)) return true;} return false;} public ZetupkaLine getDouble(LineType type) { for(int i=0; i < lines.size()-1; i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); ZetupkaLine line2=(ZetupkaLine) lines.elementAt(i+1); if(line.kindOf(type) && line2.kindOf(type)) return line;} return null;} public void replace(ZetupkaLine zetupkaLine,LineType type,LineType type2) { if(zetupkaLine.kindOf(type) && zetupkaLine.kindOf(type2)) lines.removeElement(zetupkaLine);} public boolean hasLastLine(LineType type) { ZetupkaLine lastLine=(ZetupkaLine) lines.elementAt(lines.size()-1); return lastLine.kindOf(type);} public void ommitLastLine(LineType type) { ZetupkaLine lastLine=(ZetupkaLine) lines.elementAt(lines.size()-1); if(lastLine.kindOf(type)) lines.removeElement(lastLine);} public ZetupkaLine getCustomLine(LineType type,String pattern) { for(int i=0; i < lines.size()-1; i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); if(line.kindOf(type) && line.getContent().contains(new Ester(pattern))) return line;} return null;} public void removeDouble(ZetupkaLine zetupkaLine,LineType typefiles,LineType typemezto) { for(int i=0; i < lines.size()-1; i++) { ZetupkaLine line=(ZetupkaLine) lines.elementAt(i); ZetupkaLine line2=(ZetupkaLine) lines.elementAt(i+1); if(line.equals(zetupkaLine) && line.kindOf(typefiles) && line2.kindOf(typemezto)) { lines.removeElement(line); lines.removeElement(line2); return;}}}} class LineType { public final static LineType TypeFiles=new LineType(0,"<files>"); public final static LineType TypeFiles0=new LineType(0+1,"<files-0>"); public final static LineType TypeMezto=new LineType(1+1,"<mezto>"); public final static LineType TypeEE=new LineType(2+1,"<EE>"); public final static LineType TypeEmpty=new LineType(2+1+1,"<empty>"); public final static LineType TypeItems=new LineType(2+1+1+1,"<all items>"); private int id; private String descr; private LineType(int id,String descr) { this.id=id; this.descr=descr;} public int getId() { return id;} public String getDescr() { return descr;} public String getStrk() { return "K"+id;} public static LineType checkLineType(Ester content) { if(content.length() >= 2) { if(content.begins(new Ester("files=")) && content.length() <= new Ester("files=").length()+1+1+1) return TypeFiles0; if(content.begins(new Ester("files="))) return TypeFiles; if(content.begins(new Ester("mezto="))) return TypeMezto; if(content.begins(new Ester("EE"))) return TypeEE;} if(Data.replace(Data.replace(Data.replace(content,new Ester(" "),new Ester("")),new Ester("\t"),new Ester("")),new Ester("\r"),new Ester("")).length() < 1+1) return TypeEmpty; return TypeItems;} public static int getTypeFilesId() { return TypeFiles.getId();}} class ZetupkaLine { int number; private Ester content; LineType type; public ZetupkaLine(int number,Ester content) { super(); this.number=number; this.content=content; this.type=LineType.checkLineType(content);} public void transform(LineType newLineType,Ester newContent) { if(newLineType.getId()==LineType.getTypeFilesId()) { this.content=new Ester("files=").append(' ').append(newContent); this.type=newLineType;}} public void transform(LineType newLineType) { this.content=new Ester(""); this.type=newLineType;} public boolean kindOf(LineType type2) { return type.getId()==type2.getId();} public int getNumber() { return number;} public Ester getContent() { return content;} public Ester repr() { Ester result=new Ester(""); result.append(number).append(") ").append(type.getDescr()).append(content); return result;} public String toString() { return repr().toString();}} interface ZetupkaTransformRule { void apply();} class ZetupkaHolder { protected Zetupka zetupka; public ZetupkaHolder(Zetupka zetupka) { super(); this.zetupka=zetupka;} public Zetupka getZetupka() { return zetupka;}} class RemoveLastEmptyRule extends ZetupkaHolder implements ZetupkaTransformRule { public RemoveLastEmptyRule(Zetupka zetupka) { super(zetupka);} public void apply() { if(zetupka.hasLastLine(LineType.TypeEmpty)) zetupka.ommitLastLine(LineType.TypeEmpty);}} class ReduceEmptiesRule extends ZetupkaHolder implements ZetupkaTransformRule { public ReduceEmptiesRule(Zetupka zetupka) { super(zetupka);} public void apply() { while(zetupka.hasDoubleLine(LineType.TypeEmpty)) { ZetupkaLine zetupkaLine=zetupka.getDouble(LineType.TypeEmpty); zetupka.replace(zetupkaLine,LineType.TypeEmpty,LineType.TypeEmpty);}}} abstract class RemoveEmptyLunchRule extends ZetupkaHolder implements ZetupkaTransformRule { public RemoveEmptyLunchRule(Zetupka zetupka) { super(zetupka);} public void apply() { while(zetupka.hasCustomLine(LineType.TypeFiles,getRemPattern())) { ZetupkaLine zetupkaLine=zetupka.getCustomLine(LineType.TypeFiles,getRemPattern()); zetupka.removeDouble(zetupkaLine,LineType.TypeFiles,LineType.TypeMezto);}} abstract String getRemPattern();} class RemoveDoubleVutilsRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= vutils.bat"; public RemoveDoubleVutilsRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleM3dRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= m3d.bat"; public RemoveDoubleM3dRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleSqProjRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= sqproj.bat"; public RemoveDoubleSqProjRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleCsRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= cs.bat"; public RemoveDoubleCsRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleCsProfileRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= csprofile.bat"; public RemoveDoubleCsProfileRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleCndataRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= cndata.bat"; public RemoveDoubleCndataRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleUixRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= uix.bat"; public RemoveDoubleUixRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleAboutRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= about.bat"; public RemoveDoubleAboutRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class RemoveDoubleOneWayConvRule extends RemoveEmptyLunchRule { final static String REM_PTRN="es= onewayc.bat"; public RemoveDoubleOneWayConvRule(Zetupka zetupka) { super(zetupka);} String getRemPattern() { return REM_PTRN;}} class EELineRule extends ZetupkaHolder implements ZetupkaTransformRule { public EELineRule(Zetupka zetupka) { super(zetupka);} public void apply() { while(zetupka.hasLine(LineType.TypeEE)) { ZetupkaLine zetupkaLine=zetupka.get(LineType.TypeEE); zetupka.replace(zetupkaLine,LineType.TypeEmpty);}}} class ZeroFilesRule extends ZetupkaHolder implements ZetupkaTransformRule { public ZeroFilesRule(Zetupka zetupka) { super(zetupka);} public void apply() { ZetupkaLine files0Line=zetupka.get(LineType.TypeFiles0); ZetupkaLine itemsLine=zetupka.get(LineType.TypeItems); Ester items=itemsLine.getContent(); zetupka.replace(itemsLine,LineType.TypeEmpty); zetupka.replace(files0Line,LineType.TypeFiles,items);}}