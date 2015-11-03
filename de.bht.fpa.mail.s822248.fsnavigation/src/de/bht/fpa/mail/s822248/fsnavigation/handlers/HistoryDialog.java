package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class HistoryDialog extends Dialog {

  private String path;
  private final String empty = "No base directories in history";

  protected HistoryDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  protected void okPressed() {
    if (path != null && !path.equals(empty)) {
      path = path.substring(path.indexOf(" ") + 1);
      FileObservable.getInstance().setPath(path);

    }
    close();
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite composite = (Composite) super.createDialogArea(parent);
    final int width = 500;
    final int height = 200;

    Composite c = new Composite(composite, SWT.SCROLLBAR_OVERLAY);
    ListViewer list = new ListViewer(c);
    list.getList().setSize(width, height);
    for (String s : getEntries()) {
      list.add(s);
    }
    if (list.getList().getItemCount() == 0) {
      list.getList().add(empty);
      list.getList().setEnabled(false);
    }

    list.addSelectionChangedListener(a -> path = list.getList().getItem(list.getList().getSelectionIndex()));

    Button btnClearHistory = new Button(composite, SWT.PUSH);
    btnClearHistory.setText("Clear History");
    btnClearHistory.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        try {
          SetBaseDirHandler.getPrefs().clear();
          SetBaseDirHandler.getPrefs().flush();
          list.getList().removeAll();
          list.getList().add(empty);
        } catch (BackingStoreException e1) {
        }
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
      }

    });

    return super.createDialogArea(parent);
  }

  private List<String> getEntries() {
    Preferences pref = SetBaseDirHandler.getPrefs();

    List<String> choices = new ArrayList<>();
    try {
      for (int i = 0; i < pref.keys().length; i++) {
        choices.add(i + ": " + pref.get(i + "", ""));
      }
      Collections.reverse(choices);
    } catch (BackingStoreException e) {
    }

    return choices;
  }

}
