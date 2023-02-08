package mydev.uix;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;

import mydev.vutils.Queue;

public final class MultyButtonTiersBuilder {
	private Queue queueRows;

	public static MultyButtonTiersBuilder create() {
		return new MultyButtonTiersBuilder();
	}

	public MultyButtonTiersBuilder build(Panel panel) {
		panel.setLayout(getPanelLayout());
		panel.setBackground(getPanelBgColor());
		Object[] queueRecordsArray = queueRows.getQueueRecordsArray();
		for (int i = 0; i < queueRecordsArray.length; i++)
			panel.add((Component) queueRecordsArray[i]);
		return this;
	}

	protected MultyButtonTiersBuilder() {
		super();
		this.queueRows = new Queue();
	}

	public MultyButtonTiersBuilder appendControlsPanel(ControlsPanel rowPanel) {
		return appendPanel(rowPanel);
	}

	public MultyButtonTiersBuilder appendPanel(Panel rowPanel) {
		if (availableInsertionSpace())
			queueRows.enqueueQueueRecord(rowPanel);
		return this;
	}

	public MultyButtonTiersBuilder appendElement(Component comp) {
		if (availableInsertionSpace())
			queueRows.enqueueQueueRecord(comp);
		return this;
	}

	public ControlsPanel getControlsPanel(int rowIdx) {
		if (hasRowInPlace(rowIdx)) {
			ControlsPanel cp = (ControlsPanel) queueRows.getQueueRecord(rowIdx);
			return cp;
		}
		return null;
	}

	public Panel getPanel(int rowIdx) {
		if (hasRowInPlace(rowIdx)) {
			Panel cp = (Panel) queueRows.getQueueRecord(rowIdx);
			return cp;
		}
		return null;
	}

	public Component getElement(int rowIdx) {
		if (hasRowInPlace(rowIdx)) {
			Component cp = (Component) queueRows.getQueueRecord(rowIdx);
			return cp;
		}
		return null;
	}

	public int getRows() {
		return (int) queueRows.sizeOfQueue();
	}

	public Color getPanelBgColor() {
		return Props.bgCtrlPanelColor;
	}

	public LayoutManager getPanelLayout() {
		int bs = getBorderSpace();
		int multy = (int) queueRows.sizeOfQueue();
		return new GridLayout(multy, 1, bs + 1, bs);
	}

	public int getBorderSpace() {
		return 1 - 1;
	}

	private boolean availableInsertionSpace() {
		return queueRows.sizeOfQueue() >= 0 && queueRows.sizeOfQueue() < 123;
	}

	private boolean hasRowInPlace(int rowIdx) {
		return rowIdx >= 0 && rowIdx < queueRows.sizeOfQueue();
	}
}