package mydev.mnoga; import java.io.File; import java.io.IOException; import mydev.vutils.Data; import mydev.vutils.Ester; public class LoadSave implements MyNote { public boolean saveMultyFiltered(String param,String[] includeParams,String save_location) throws IOException { if(param !=null && param.length() > 0 && save_location !=null && save_location.length() > 0 && new Ester(save_location).ends(new Ester(MyNote.DEREVO))) { new File(new Data().replace(new Ester(save_location),new Ester(MyNote.DEREVO),new Ester("")).toString()).mkdirs(); IOException th=null; Save obj=new Save(); Help help=new Help(); Tree tre=new Oak(help.getLimiter()); String[] logs=tre.build(param); String[] nors=tre.normal(param); int zz=0; for(int i=0; i < logs.length; i++) { boolean found=false; if(includeParams==null) found=true; else if(includeParams !=null && includeParams.length < 1) found=true; else for(int j=0; !found && j < includeParams.length; j++) if(new Ester(nors[i]).begins(new Ester(includeParams[j]))) found=true; if(!found) { zz++; logs[i]=null; nors[i]=null;}} try { obj.write_start(save_location,logs.length-zz,StrBytes.STR_PLACE); for(int i=0; i < logs.length; i++) { if(logs[i] !=null && nors[i] !=null) { if(obj.D.booleanValue()) System.out.println(" "+logs[i]); obj.write_file(logs[i],nors[i]); if(obj.D.booleanValue()) System.out.println("   "+nors[i]);}}} catch(IOException ex) { th=ex; ex.printStackTrace();} finally { try { obj.write_finish();} catch(IOException ex) { th=ex; ex.printStackTrace();} if(th !=null) throw th;} if(th !=null) throw th; return true;} return false;} public boolean saveMulty(String[] params,String save_location) throws IOException { if(params !=null && save_location !=null && params.length > 0 && save_location.length() > 0 && new Ester(save_location).ends(new Ester(MyNote.DEREVO))) { new File(new Data().replace(new Ester(save_location),new Ester(MyNote.DEREVO),new Ester("")).toString()).mkdirs(); IOException th=null; Save obj=new Save(); Help help=new Help(); Tree tre=new Oak(help.getLimiter()); String[] logs=null; String[] nors=null; for(int i=0; i < params.length; i++) { String param=params[i]; String[] logs2=tre.build(param); String[] nors2=tre.normal(param); logs=(logs==null) ? logs2 : join(logs,logs2); nors=(nors==null) ? nors2 : join(nors,nors2);} try { obj.write_start(save_location,logs.length,StrBytes.STR_PLACE); for(int i=0; i < logs.length; i++) { if(obj.D.booleanValue()) System.out.println(" "+logs[i]); obj.write_file(logs[i],nors[i]); if(obj.D.booleanValue()) System.out.println("   "+nors[i]);}} catch(IOException ex) { th=ex; ex.printStackTrace();} finally { try { obj.write_finish();} catch(IOException ex) { th=ex; ex.printStackTrace();} if(th !=null) throw th;} if(th !=null) throw th; return true;} return false;} String[] join(String[] logs,String[] logs2) { String[] temp=new String[logs.length+logs2.length]; for(int j=0; j < logs.length; j++) temp[j]=logs[j]; for(int j=0; j < logs2.length; j++) temp[j+logs.length]=logs2[j]; return temp;} public boolean save(String param,String save_location) throws IOException { if(param !=null && save_location !=null && param.length() > 0 && save_location.length() > 0 && new Ester(save_location).ends(new Ester(MyNote.DEREVO))) { new File(new Data().replace(new Ester(save_location),new Ester(MyNote.DEREVO),new Ester("")).toString()).mkdirs(); IOException th=null; Save obj=new Save(); Help help=new Help(); Tree tre=new Oak(help.getLimiter()); String[] logs=tre.build(param); String[] nors=tre.normal(param); try { obj.write_start(save_location,logs.length,StrBytes.STR_PLACE); for(int i=0; i < logs.length; i++) { if(obj.D.booleanValue()) System.out.println(" "+logs[i]); obj.write_file(logs[i],nors[i]); if(obj.D.booleanValue()) System.out.println("   "+nors[i]);}} catch(IOException ex) { th=ex; ex.printStackTrace();} finally { try { obj.write_finish();} catch(IOException ex) { th=ex; ex.printStackTrace();} if(th !=null) throw th;} if(th !=null) throw th; return true;} return false;} public boolean load(String save_location,String param) throws IOException { if(param !=null && save_location !=null && param.length() > 0 && save_location.length() > 0 && new Ester(save_location).ends(new Ester(MyNote.DEREVO))) { IOException th=null; String last=null; Load obj=new Load(); System.out.println("   load_location="+save_location); try { obj.read_start(save_location); byte[] zBytes=obj.read_bytes(4); byte[] nBytes=obj.read_bytes(4); int Z=obj.decode(zBytes); int N=obj.decode(nBytes); System.out.println("   Z="+Z+"   N="+N); boolean yesterday=true; while(yesterday) { byte[] idNameBytes=obj.read_bytes(N); String idName=obj.bytes2str(idNameBytes); System.out.println(" "+idName); last=idName; byte[] lengthBytes=obj.read_bytes(4); int length=obj.decode(lengthBytes); System.out.println("   "+length); byte[] content=obj.read_bytes(length); String outPath=param+MyNote.SL+idName; System.out.println("     "+outPath); if(!outPath.equals(obj.os_path)) obj.write_start(outPath); obj.write(content);}} catch(IOException ex) { if(last !=null && last.length() > 6) last=new Ester(last).sub(0,6).toString(); if(ex.getMessage().equalsIgnoreCase("BytesOf ne hvataet!") && last !=null && new Ester(last).eq(new Ester("f-word"))) th=null; else { th=ex; ex.printStackTrace();}} finally { IOException th2=null; try { obj.write_finish();} catch(IOException ex) { th=ex; ex.printStackTrace();} try { obj.read_finish();} catch(IOException ex) { th2=ex; ex.printStackTrace();} if(th !=null) throw th; if(th2 !=null) throw th2;} if(th !=null) throw th; return true;} return false;}}