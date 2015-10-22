package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s822248.fsnavigation.handlers.FileObservable;

public class NavigationView extends ViewPart implements Observer{
	
	private static String NAVIGATION_VIEW_ID;
	FileObservable file;
	TreeViewer viewer;
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent);
		viewer.setContentProvider(new NsNavigationContentProvider());
		viewer.setLabelProvider(new FsNavigationLabel());
		viewer.setInput(new FolderItem(new File(System.getProperty("user.home"))));
		file = FileObservable.getInstance();
		file.addObserver(this);
	}
	
	@Override
	public void setFocus() {
		
	}

	@Override
	public void update(Observable observable, Object object) {
		String path = (String) object;
		
		if(path!= null){
			viewer.setInput(new FolderItem(new File(path)));
			
			Preferences prefs = getPrefs();
			prefs.put("h1", path);
			try {
				prefs.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("kann nicht speichern");
			}
		}
	}
	
	private static Preferences getPrefs() {
        IPreferencesService service = Platform.getPreferencesService();
        IEclipsePreferences root = service.getRootNode();
        Preferences prefs = (Preferences) root.node(ConfigurationScope.SCOPE).node(NavigationView.NAVIGATION_VIEW_ID);
        return prefs;
    }

}
