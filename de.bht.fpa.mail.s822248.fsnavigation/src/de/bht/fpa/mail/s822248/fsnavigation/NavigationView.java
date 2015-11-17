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

public class NavigationView extends ViewPart implements Observer, ISelectionChangedListener {
  private FileObservable file;
  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {

    Preferences prefs = SetBaseDirHandler.getPrefs();

    int index = 0;
    try {
      index = prefs.keys().length - 1;
    } catch (BackingStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    final String baseDir = prefs.get(index + "", System.getProperty("user.home"));
    viewer = new TreeViewer(parent);
    viewer.setContentProvider(new NsNavigationContentProvider());
    viewer.setLabelProvider(new FsNavigationLabel());
    viewer.addSelectionChangedListener(this);
    viewer.setInput(new FolderItem(new File(baseDir)));
    file = FileObservable.getInstance();
    file.addObserver(this);
    
    getSite().setSelectionProvider(viewer);
  }

  @Override
  public void setFocus() {

  }

  @Override
  public void update(Observable arg0, Object arg1) {
    String path = (String) arg1;
    if (path != null) {
      viewer.setInput(new FolderItem(new File(path)));

      Preferences pref = SetBaseDirHandler.getPrefs();

      try {
        if (path != null) {
          pref.put(pref.keys().length + "", path);
        }
        pref.flush();
      } catch (BackingStoreException e) {
      }
    }

  }

  @Override
  public void selectionChanged(SelectionChangedEvent event) {
//    if (event.getSelection() instanceof IStructuredSelection) {
//      IStructuredSelection ts = (IStructuredSelection) event.getSelection();
//      if (ts.getFirstElement() instanceof FileTreeItem) {
//
//        FileTreeItem ti = (FileTreeItem) ts.getFirstElement();
//        List<Message> messages = ti.getMessages();
//        System.out.println("Selected Directory: " + ((FileTreeItem) ti).file.getPath());
//        System.out.println("Number of Messages: " + messages.size());
//        for (Message msg : messages) {
//          System.out.println(msg.toShortString());
//
//        }
//      }
//
//    }
  }

}
