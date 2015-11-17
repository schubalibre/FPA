package de.bht.fpa.mail.s822248.statusbar;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.bht.fpa.mail.s822248.fsnavigation.FolderItem;

public class StatusBar implements IStartup {

	@Override
	public void earlyStartup() {

		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {

					ISelectionListener listener = new ISelectionListener() {
						@Override
						public void selectionChanged(IWorkbenchPart part, ISelection selection) {
							if (!(selection instanceof IStructuredSelection))
								return;

							IStructuredSelection ss = (IStructuredSelection) selection;
							Object o = ss.getFirstElement();

							if (o instanceof FolderItem) {
								IWorkbenchPartSite site = part.getSite();
								
								IViewSite vSite = ( IViewSite ) site;

								IActionBars actionBars =  vSite.getActionBars();
								
								actionBars.getStatusLineManager().setMessage("Directory: '" + ((FolderItem) o).file.getPath() + "' was selected.");
							}

						}
					};
					window.getSelectionService().addSelectionListener(listener);
				}
			}
		});
	}
}
