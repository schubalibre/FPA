package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;


/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SetHistoryDirHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SetHistoryDirHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		HistoryDialog hd = new HistoryDialog(window.getShell());
		
		System.out.println(hd.open());
		
		String input = null;
		
		if(input != null){
		    FileObservable file = FileObservable.getInstance();
			file.setPath(input);
	    }
		
//		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		
//		ListDialog dlg = new ListDialog(window.getShell());
//		dlg.setMessage("message");
		
		Preferences prefs = SetBaseDirHandler.getPrefs();
		
		String[] keys = null;
		try {
			keys = prefs.keys();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] choices = new String[keys.length];
		int i=0;
		for(String k : keys){
			choices[i] = prefs.get(k, "");
			i++;
		}
		
//	    String input = (String) JOptionPane.showInputDialog(
//	    		null, 
//	    		"Choose now...",
//	    		"HistoryDialog", 
//	    		JOptionPane.QUESTION_MESSAGE, 
//	    		null, // Use default icon
//	    		choices, // Array of choices
//	    		choices[0]
//	    ); // Initial choice
//	    
//	    if(input != null){
//		    FileObservable file = FileObservable.getInstance();
//			file.setPath(input);
//	    }

		return null;
	}
}