package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class HistoryDialog extends Dialog {

	protected HistoryDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite composite = ( Composite )super.createDialogArea(parent);
		
		composite.getShell().setText("A dialog box with no buttons at all press 'ESC' to close");
		
		try 
		{
			composite.setLayout(new FormLayout());
			{
				createList(composite);
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//Set the size of the parent shell
		//composite.getShell().setSize(300, 100);
		return composite;
	}

	private void createList(Composite composite) {
		
		ListViewer lv = new ListViewer(composite);

		Preferences prefs = SetBaseDirHandler.getPrefs();
		
		String[] keys = null;
		try {
			keys = prefs.keys();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String k : keys){
			lv.add(prefs.get(k, ""));
		}
		
	}
}
