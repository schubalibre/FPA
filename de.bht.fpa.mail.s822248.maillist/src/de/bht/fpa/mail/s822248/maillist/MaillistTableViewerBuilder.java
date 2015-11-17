package de.bht.fpa.mail.s822248.maillist;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.table.MessageValues;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MaillistTableViewerBuilder extends TableViewerBuilder {

  public MaillistTableViewerBuilder(Composite parent) {
    super(parent);
    createColumnImportance(this);
    createColumnRead(this);
    createColumnReceived(this);
    createColumnSender(this);
    createColumnRecipients(this);
    createColumnSubject(this);
    
    this.getTable().setSortColumn(this.getTable().getColumn(2));
  }

  private void createColumnRecipients(TableViewerBuilder tableCreator) {
    ColumnBuilder recipients = tableCreator.createColumn("Recipients");
    recipients.bindToValue(MessageValues.RECIPIENT);
    
    recipients.setCustomLabelProvider(new CellLabelProvider() {

      @Override
      public void update(ViewerCell cell) {
        Object o = cell.getElement();
        if (o instanceof Message) {
          Message msg = (Message) o;

          if(msg.getRecipients().size() > 0){
            StringBuilder recipients = new StringBuilder();
            for (Recipient recipient : msg.getRecipients()){
              recipients.append(recipient.getPersonal() + " <" + recipient.getEmail() + ">, "); 
            }
            cell.setText(recipients.toString().substring(0, recipients.length()-2));
          }
        }
      }
    });
    
    recipients.build();
  }

  private void createColumnSender(TableViewerBuilder tableCreator) {
    ColumnBuilder sender = tableCreator.createColumn("Sender");
    sender.bindToValue(MessageValues.SENDER);
    
    sender.setCustomLabelProvider(new CellLabelProvider() {

      @Override
      public void update(ViewerCell cell) {
        Object o = cell.getElement();
        if (o instanceof Message) {
          Message msg = (Message) o;
          cell.setText(msg.getSender().getPersonal() + " <" + msg.getSender().getEmail() + ">");
        }
      }
    });
    sender.build();

  }

  private void createColumnReceived(TableViewerBuilder tableCreator) {
    ColumnBuilder received = tableCreator.createColumn("Received");
    received.bindToValue(MessageValues.RECEIVED);

    received.setCustomLabelProvider(new CellLabelProvider() {

      @Override
      public void update(ViewerCell cell) {
        Object o = cell.getElement();
        if (o instanceof Message) {
          Message msg = (Message) o;
          String datum = new SimpleDateFormat("dd.MM.yyyy").format(msg.getReceived());
          cell.setText(datum);
        }
      }
    });
    received.build();
  }

  private void createColumnRead(TableViewerBuilder tableCreator) {
    ColumnBuilder read = tableCreator.createColumn("R");
    read.bindToValue(MessageValues.READ);
    read.setPixelWidth(33);
    read.setCustomLabelProvider(new CellLabelProvider() {

      @Override
      public void update(ViewerCell cell) {
        Object o = cell.getElement();
        if (o instanceof Message) {
          Message msg = (Message) o;
          String img = "icons/unread.png";
          if (msg.isRead()) {
            img = "icons/read.png";
          }
          Image image = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, img).createImage();
          cell.setImage(image);
        }
      }
    });

    read.build();
  }

  private void createColumnImportance(TableViewerBuilder tableCreator) {
    ColumnBuilder importance = tableCreator.createColumn("Imp");
    importance.bindToValue(MessageValues.IMPORTANCE);
    importance.setPixelWidth(33);
    importance.setCustomLabelProvider(new CellLabelProvider() {

      @Override
      public void update(ViewerCell cell) {
        Object o = cell.getElement();
        if (o instanceof Message) {
          Message msg = (Message) o;
          String img = "icons/important2.png";
          if (msg.getImportance() == Importance.HIGH) {
            img = "icons/important3.png";
          } else if (msg.getImportance() == Importance.LOW) {
            img = "icons/important1.png";
          }
          Image image = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, img).createImage();
          cell.setImage(image);
        }
      }

    });
    importance.build();
  }

  private void createColumnSubject(TableViewerBuilder tableCreator) {
    ColumnBuilder subject = tableCreator.createColumn("Subject");
    subject.bindToValue(MessageValues.SUBJECT);
    subject.build();
  }

}
