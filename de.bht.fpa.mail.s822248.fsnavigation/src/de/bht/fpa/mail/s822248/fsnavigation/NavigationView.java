package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s822248.fsnavigation.handlers.FileObservable;
import de.bht.fpa.mail.s822248.fsnavigation.handlers.SetBaseDirHandler;

public class NavigationView extends ViewPart implements Observer{

	FileObservable file;
	TreeViewer viewer;
	@Override
	public void createPartControl(Composite parent) {	
		
		final String baseDir = SetBaseDirHandler.getPrefs().get("baseDirectory", System.getProperty("user.home"));
		
		viewer = new TreeViewer(parent);
		viewer.setContentProvider(new NsNavigationContentProvider());
		viewer.setLabelProvider(new FsNavigationLabel());
		viewer.setInput(new FolderItem(new File(baseDir)));
		
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
		}
	}
}
