package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class NavigationView extends ViewPart{

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent);
		viewer.setContentProvider(new NsNavigationContentProvider());
		viewer.setLabelProvider(new FsNavigationLabel());
		viewer.setInput(new FolderItem(new File(System.getProperty("user.home"))));
	}

	@Override
	public void setFocus() {
	
	}

}
