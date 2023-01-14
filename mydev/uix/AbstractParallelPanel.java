package mydev.uix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;

import mydev.vutils.AverageRec;
import mydev.vutils.MegaAverageRec;
import mydev.vutils.Stack;

public abstract class AbstractParallelPanel extends Panel {
	protected Stack sps = new Stack();
	private AbstractApproach[] spsArray;
	protected MegaAverageRec mar = new MegaAverageRec();

	protected AbstractParallelPanel(int nWidth, int nHeight) {
		super();
		setLayout(new BorderLayout());
		setBackground(getBgColor());
		Panel parallelPanel = new Panel();
		parallelPanel.setLayout(getParallelLayout(nWidth, nHeight));
		parallelPanel.setBackground(getBgColor());
		AbstractApproachProvider panelProvider = obtainProvider();
		for (int i = 0; i < nWidth; i++)
			for (int j = 0; j < nHeight; j++) {
				AbstractApproach ap = panelProvider.provideApproach();
				parallelPanel.add(ap);
				mar.addItem(ap.ar);
				sps.push(ap);
			}
		this.spsArray = makeCopyCash();
		add("Center", createPanelColorfullWrap(parallelPanel));
	}

	private Panel createPanelColorfullWrap(Component comp) {
		LabeledPanel ignorePanelWrap = new LabeledPanel("", comp,
				getWrapPanelWidth(), getWrapPanelBgColor(),
				getWrapPanelBorderColor(), getWrapPanelTextColor());
		return ignorePanelWrap;
	}

	protected Color getWrapPanelBgColor() {
		return Props.bgParallelWrapColor;
	}

	protected Color getWrapPanelBorderColor() {
		return Props.borderParallelWrapColor;
	}

	protected Color getWrapPanelTextColor() {
		return Props.textParallelWrapColor;
	}

	protected int getWrapPanelWidth() {
		return LabeledPanel.DEF_WRAP_SIZE - 1;
	}

	private AbstractApproach[] makeCopyCash() {
		Object[] stackRecordsArray = sps.getStackRecordsArray();
		AbstractApproach[] spsCopy = new AbstractApproach[stackRecordsArray.length];
		for (int i = 0; i < stackRecordsArray.length; i++)
			spsCopy[i] = (AbstractApproach) stackRecordsArray[i];
		return spsCopy;
	}

	public AbstractApproach getFirst() {
		return spsArray[0];
	}

	public void initBackup() {
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			sp.initBackup("", "");
		}
	}

	public AbstractApproach assignBackup(String name, String path) {
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			if (sp.isReadyFree()) {
				sp.readBackup(name, path);
				return sp;
			}
		}
		return null;
	}

	public void initSource() {
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			sp.initSource("", "");
		}
	}

	public AbstractApproach assignSource(String curSel, String path,
			String looking, String relPath, int idxSel) {
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			if (sp.isReadyFree()) {
				sp.readSource(curSel, path, looking, relPath, idxSel);
				return sp;
			}
		}
		return null;
	}

	public void clearPanels() {
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			sp.clearPanel();
		}
	}

	public AverageRec getAverageRec() {
		return mar;
	}

	public void clearAverage() {
		mar.clearItems();
	}

	protected abstract AbstractApproachProvider obtainProvider();

	public boolean hasInProgress() {
		boolean result = false;
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			if (sp.isReadyFree() == false)
				result = true;
		}
		return result;
	}

	public boolean hasAvailable() {
		for (int i = 0; i < spsArray.length; i++) {
			AbstractApproach sp = spsArray[i];
			if (sp.isReadyFree())
				return true;
		}
		return false;
	}

	public int getNum() {
		return (int) sps.sizeOfStack();
	}

	public Color getBgColor() {
		return Props.bgParallelColor;
		// return Color.white;
	}

	public LayoutManager getParallelLayout(int nWidth, int nHeight) {
		return new GridLayout(nWidth, nHeight, getBorderSpace() + 1,
				getBorderSpace());
	}

	public int getBorderSpace() {
		return 1 + 1;
	}
}