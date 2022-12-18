package mydev.cndata; import mydev.vutils.Queue; public class DailyObservationRepo { private Queue recQueue; private static DailyObservationRepo inst; private DailyObservationRepo() { recQueue=new Queue();} protected void initDays() { new DaysLoader().loadDays();} public static DailyObservationRepo instance() { if(inst==null) { inst=new DailyObservationRepo(); inst.initDays();} return inst;} public void addDaily(AbstractDailyObservation rec) { if(rec !=null) recQueue.enqueueQueueRecord(rec);} public long getDailyNum() { return recQueue.sizeOfQueue();} public AbstractDailyObservation getDaily(int idx) { return(AbstractDailyObservation) recQueue.getQueueRecord(idx);}}