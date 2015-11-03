package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
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
    FileObservable file = FileObservable.getInstance();
    
    int i = hd.open();
    
    if (i > -1) {
     file.setPath(SetBaseDirHandler.getPrefs().get(i + "", System.getProperty("user.home")));
    }

    return null;
  }
}