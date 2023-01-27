package mydev.uix;

import java.awt.Choice;

import mydev.vutils.Ester;

public final class ChoiceFactory {
	private Choice chCases;

	public ChoiceFactory(String[] variants, int firstChoice) {
		super();
		this.chCases = new Choice();
		if (variants != null) {
			for (int i = 0; i < variants.length; i++)
				chCases.addItem(variants[i]);
			if (firstChoice > -1)
				chCases.select(firstChoice);
		}
	}

	public boolean isSelected(String question) {
		return question != null
				&& new Ester(chCases.getSelectedItem()).eq(new Ester(question));
	}

	public Choice getChCases() {
		return chCases;
	}
}