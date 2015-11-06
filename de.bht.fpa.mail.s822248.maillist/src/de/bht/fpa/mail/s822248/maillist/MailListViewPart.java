package de.bht.fpa.mail.s822248.maillist;

import java.util.Collection;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MailListViewPart  extends ViewPart{

  	private TableViewer tableViewer;

	@Override
	public void createPartControl(Composite parent) {
	  Composite tableComposite = new Composite(parent, SWT.NONE);
    TableViewerBuilder tvb = new MaillistTableViewerBuilder(tableComposite);
		Collection<Message> messages = new RandomTestDataProvider(50).getMessages();
		tvb.setInput(messages);
		this.tableViewer = tvb.getTableViewer();
		
		Text textfield = new Text(parent,SWT.SEARCH);
	}

	@Override
	public void setFocus() {
		this.tableViewer.getControl().setFocus();
	}
}
