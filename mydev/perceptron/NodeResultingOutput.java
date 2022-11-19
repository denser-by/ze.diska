package mydev.perceptron; public class NodeResultingOutput extends CommonNeuronicNode { protected ClassificationResult result; public NodeResultingOutput() { super();} public float processSignal() { double totalSignalSummary=0f; Object[] queueRecordsArray=signalProviders.getQueueRecordsArray(); for(int i=0; i < queueRecordsArray.length; i++) { float inputNodeOutputSignal=((NeuronicWrap) queueRecordsArray[i]).getRefNode().processSignal(); totalSignalSummary+=inputNodeOutputSignal;} return(float) totalSignalSummary;} public void initClearState() { result=null;} public ClassificationResult getResult() { if(result==null) { float lookSignal=processSignal();} return result;}} class LookRegistry { public RegisteredResult search(float lookSignal) { return null;}} class RegisteredResult {} class RegisteredLook {}