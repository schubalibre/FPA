package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s822248.fsnavigation.handlers.FileObservable;
import de.bht.fpa.mail.s822248.fsnavigation.handlers.SetBaseDirHandler;

public class NavigationView extends ViewPart implements Observer, ISelectionChangedListener{

	TreeViewer viewer;
	@Override
	public void createPartControl(Composite parent) {	
		
		Preferences prefs = SetBaseDirHandler.getPrefs();
		
		int lastIndex = 0;
		
		try {
			lastIndex = prefs.keys().length - 1;
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final String baseDir = prefs.get(lastIndex + "", System.getProperty("user.home"));
		
		viewer = new TreeViewer(parent);
		viewer.setContentProvider(new NsNavigationContentProvider());
		viewer.setLabelProvider(new FsNavigationLabel());
		viewer.addSelectionChangedListener(this);
		viewer.setInput(new FolderItem(new File(baseDir)));
		
		FileObservable file = FileObservable.getInstance();
		file.addObserver(this);
	}
	
	@Override
	public void setFocus() {}

	@Override
	public void update(Observable observable, Object object) {
		String path = (String) object;
		
		if(path!= null) 
			viewer.setInput(new FolderItem(new File(path)));
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection ts = (IStructuredSelection)event.getSelection();
		
		if(ts.getFirstElement() instanceof FileTreeItem){
			FileTreeItem ti = (FileTreeItem)ts.getFirstElement();
			List<Message> msgs = ti.getMessages();
			
			System.out.println("Selected: " + ti.file.getName());
			System.out.println("Selected directory: " + ti.file.getAbsolutePath());
			System.out.println("Numbers of messages: " + msgs.size());
			
			for(Message mgs : msgs){
				System.out.println(mgs.toString());
			}
		}
	}
}
