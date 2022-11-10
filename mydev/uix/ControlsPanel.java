package mydev.uix; import java.awt.Color; import java.awt.Component; import java.awt.FlowLayout; import java.awt.LayoutManager; import java.awt.Panel; import mydev.vutils.Karta; public abstract class ControlsPanel extends Panel { protected Karta uiMap; private YellowAutoButton autoButton; public ControlsPanel() { super(); setLayout(getPanelLayout()); setBackground(getPanelBgColor()); this.uiMap=new Karta(); buildPanel(); dropBtnSkipPosition(); enableAutoBtn();} public Color getPanelBgColor() { return Color.lightGray;} public LayoutManager getPanelLayout() { return new FlowLayout(FlowLayout.CENTER);} protected abstract void buildPanel(); public abstract void enableCtrls(boolean enable); public void enableAutoBtn() { if(autoButton !=null && autoButton.getBtAuto() !=null) autoButton.enableCtrl(true);} public YellowAutoButton getAutoBtn() { return autoButton;} protected ControlsPanel append(String name,Component ctrl) { if(ctrl !=null && ctrl instanceof YellowAutoButton) autoButton=(YellowAutoButton) ctrl; uiMap.put(name,ctrl); add(ctrl); return this;} public Component getCtrl(String name) { return(Component) uiMap.get(name);} public void dropBtnSkipPosition() { enableCtrls(false);}}