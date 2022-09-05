package mydev.uix; import java.awt.Color; import java.awt.FlowLayout; import java.awt.Panel; import java.awt.TextField; import mydev.vutils.AverageRec; public class AveragePanel extends Panel { private TextField tfMin; private TextField tfMax; private TextField tfAvg; public AveragePanel() { super(); setLayout(getPanelLayout()); setBackground(getPanelBgColor()); add(tfMin=new TextField("",getMinWidth())); tfMin.setEditable(false); add(tfAvg=new TextField("",getAvgWidth())); tfAvg.setEditable(false); add(tfMax=new TextField("",getMaxWidth())); tfMax.setEditable(false);} protected int getMinWidth() { return 5-1-1;} protected int getAvgWidth() { return 5+5+5+1;} protected int getMaxWidth() { return 5-1-1;} public Color getPanelBgColor() { return Color.lightGray;} public FlowLayout getPanelLayout() { return new FlowLayout(FlowLayout.CENTER);} public TextField getTfMin() { return tfMin;} public TextField getTfMax() { return tfMax;} public TextField getTfAvg() { return tfAvg;} public void clearPanel() { tfMin.setText(""); tfMax.setText(""); tfAvg.setText("");} public void setMin(String min) { tfMin.setText(min);} public void setMax(String max) { tfMax.setText(max);} public void setAvg(String avg) { tfAvg.setText(avg);} public void updateMinMaxAvg(AverageRec mar) { if(mar.getAverageTime() > 0.f) { tfMin.setText(""+mar.getMinTime()); tfMax.setText(""+mar.getMaxTime()); tfAvg.setText(""+mar.getAverageTime());}}}