package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class HistoryDialog extends Dialog {

  private int index = -1;

  protected HistoryDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    
    final int width = 100;
    final int height = 100;

    Composite composite = (Composite) super.createDialogArea(parent);

    composite.getShell().setText("A dialog box with no buttons at all press 'ESC' to close");

    Composite com = new Composite(composite, SWT.SCROLLBAR_OVERLAY);

    ListViewer ls = new ListViewer(com, SWT.V_SCROLL);

    System.out.println(parent.getSize());

    ls.getList().setSize(width, height);

    for (String s : getEntries()) {
      ls.add(s);
    }
    
    if (ls.getList().getItemCount() == 0) {
      ls.add("No base directories in history");
      ls.getList().setEnabled(false);
    } 

    ls.addSelectionChangedListener(e -> index = ls.getList().getItemCount() - ls.getList().getSelectionIndex() - 1);

    return super.createDialogArea(parent);
  }

  @Override
  public int open() {
    super.open();
    return index;
  }

  private List<String> getEntries() {

    Preferences prefs = SetBaseDirHandler.getPrefs();

    List<String> choices = new ArrayList<>();
    try {

      for (String k : prefs.keys()) {
        choices.add(prefs.get(k, ""));
      }
      Collections.reverse(choices);
    } catch (BackingStoreException e) {
      e.getStackTrace();
    }

    return choices;
  }

}
