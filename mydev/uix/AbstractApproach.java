package mydev.uix; import java.awt.Panel; import mydev.aaa.Sleeper; import mydev.vutils.AverageRec; public abstract class AbstractApproach extends Panel implements Runnable { protected AverageRec ar=new AverageRec(); protected Thread worker1; protected Sleeper sleeper=new Sleeper(); public AbstractApproach() { super();} public AverageRec getAr() { return ar;} public Sleeper getSleeper() { return sleeper;} public abstract boolean isReadyFree(); public abstract void initSource(String name,String path); public abstract void readSource(String name,String path); public abstract void readSource(String name,String path,String start,String relPath); public abstract void readSource(String curSel,String path,String looking,String relPath,int idxSel); public abstract void clearPanel(); public abstract void initBackup(String name,String path); public abstract void readBackup(String name,String path);}