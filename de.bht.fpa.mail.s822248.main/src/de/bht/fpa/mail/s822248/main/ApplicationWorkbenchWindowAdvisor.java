package de.bht.fpa.mail.s822248.main;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

  public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    super(configurer);
  }

  public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    return new ApplicationActionBarAdvisor(configurer);
  }

  public void preWindowOpen() {
    IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    final int width = 800;
    final int height = 600;
    configurer.setInitialSize(new Point(width, height));
    configurer.setShowCoolBar(false);
    configurer.setShowStatusLine(false);
    configurer.setTitle("EMailer"); //$NON-NLS-1$
  }
}
