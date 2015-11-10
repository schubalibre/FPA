package de.bht.fpa.mail.s822248.maillist;

import java.util.Collection;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MailListViewPart  extends ViewPart{

  	private TableViewer tableViewer;

	@Override
	public void createPartControl(Composite parent) {
	  
	  GridLayout layout = new GridLayout(2,false);
    parent.setLayout(layout);
	  final Label label = new Label(parent, SWT.NONE);
	  label.setText("Search");
	  final Text text = new Text(parent, SWT.SEARCH | SWT.BORDER);
	  text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  
	  text.addKeyListener(new KeyListener() {
      
      @Override
      public void keyReleased(KeyEvent e) {
        SearchFilter.filterText = text.getText().toLowerCase();
        tableViewer.refresh();
      }
      
      @Override
      public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
      }
    });
	  
	  
	  GridData gridData = new GridData();
	  gridData.horizontalAlignment = GridData.FILL;
	  gridData.horizontalSpan = 2;
	  
	  Composite tableComposite = new Composite(parent, SWT.NONE);
    TableViewerBuilder tvb = new MaillistTableViewerBuilder(tableComposite);
		Collection<Message> messages = new RandomTestDataProvider(50).getMessages();
		tvb.setInput(messages);
		this.tableViewer = tvb.getTableViewer();
		tableComposite.setLayoutData(gridData);
		tableComposite.addControlListener(new ControlAdapter() {
		  public void controlResized(ControlEvent e){
		    tableComposite.setSize(tableComposite.getSize().x,parent.getParent().getSize().y-40);
		    TableColumn[] colmns = tableViewer.getTable().getColumns();
		    for(int i = 2; i < colmns.length; i++){
		      colmns[i].setWidth(
		          (tableComposite.getSize().x - colmns[0].getWidth() - colmns[1].getWidth() - 30) 
		          / (colmns.length - 2));
		    }
		  }
    });
		
    ViewerFilter searchfilter = new SearchFilter();
    tableViewer.addFilter(searchfilter);
	}

	@Override
	public void setFocus() {
		this.tableViewer.getControl().setFocus();
	}
}
