package mydev.planelook; import java.awt.Frame; public class CursorChanger { private Frame mainFrame; private boolean arrow; public CursorChanger(Frame fr) { super(); this.mainFrame=fr;} public void setArrow() { arrow=true; mainFrame.setCursor(Frame.DEFAULT_CURSOR);} public void setHand() { arrow=false; mainFrame.setCursor(Frame.MOVE_CURSOR);} public boolean isArrow() { return arrow;} public Frame getMainFrame() { return mainFrame;} public String toString() { return "CursorChanger [mainFrame="+mainFrame+", arrow="+arrow+"]";}}