package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s822248.fsnavigation.handlers.FileObservable;

public class NavigationView extends ViewPart implements Observer{

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
	public void update(Observable arg0, Object arg1) {
		String path = (String) arg1;
		if(path!= null)viewer.setInput(new FolderItem(new File(path)));
	}

}
