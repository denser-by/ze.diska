package mydev.uix; public abstract class ProgressMultiTasking implements Runnable,ProgressChecker { protected short learners; private boolean necessities; protected ProgressMultiTasking() { super();} public void run() { learners=0; necessities=false; performTask();} protected abstract void performTask(); protected void recountComplete(long pos,long num) { if(pos==num) necessities=true; learners=(short)((float) pos /(float) num * 100.1f);} public short getCompleteProgress() { return learners;} public boolean isComplete() { return necessities==true;}}