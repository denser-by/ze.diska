package mydev.uix; import java.awt.Checkbox; import java.awt.Color; import java.awt.FlowLayout; import java.awt.LayoutManager; import java.awt.Panel; public class External3DPanel extends Panel { Checkbox cbExt; Checkbox chD3; final static String NAME_EXT="external"; final static String NAME_3D="3D"; public External3DPanel(boolean ext) { super(); setLayout(getPanelLayout()); setBackground(getPanelBgColor()); add(cbExt=new Checkbox(NAME_EXT)); cbExt.setState(ext);} public External3DPanel(boolean ext,boolean d3) { super(); setLayout(getPanelLayout()); setBackground(getPanelBgColor()); add(cbExt=new Checkbox(NAME_EXT)); cbExt.setState(ext); add(chD3=new Checkbox(NAME_3D)); chD3.setState(d3);} public void enableCtrls(boolean enable) { if(cbExt !=null) cbExt.enable(enable); if(chD3 !=null) chD3.enable(enable);} public boolean get3d() { return chD3.getState();} public boolean getExternal() { return cbExt.getState();} public Checkbox getChD3() { return chD3;} public Checkbox getCbExt() { return cbExt;} public Color getPanelBgColor() { return Props.bgAmountPanelColor;} public LayoutManager getPanelLayout() { int bs=getBorderSpace(); return new FlowLayout(FlowLayout.CENTER,bs,bs);} public int getBorderSpace() { return 1-1;}}