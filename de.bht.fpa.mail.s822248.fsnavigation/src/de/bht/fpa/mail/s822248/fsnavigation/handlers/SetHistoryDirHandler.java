package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.swt.widgets.DirectoryDialog;
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
		

		return null;
	}
	
	public static Preferences getPrefs() {
        IPreferencesService service = Platform.getPreferencesService();
        IEclipsePreferences root = service.getRootNode();
        Preferences prefs = root.node(ConfigurationScope.SCOPE).node("de.bht.fpa.mail.s822248.fsnavigation.view1");
        return prefs;
    }

}