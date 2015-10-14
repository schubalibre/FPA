package de.bht.fpa.mail.s822248.main;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView("de.bht.fpa.mail.s822248.fsnavigation.view1",
				IPageLayout.LEFT, 
				0.7f, 
				layout.getEditorArea());
	}
}
