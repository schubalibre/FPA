package de.bht.fpa.mail.s822248.main;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

  public void createInitialLayout(IPageLayout layout) {
    final float width = 0.7f;
    layout.addView("de.bht.fpa.mail.s822248.fsnavigation.view1", IPageLayout.LEFT, width, layout.getEditorArea());
  }
}
