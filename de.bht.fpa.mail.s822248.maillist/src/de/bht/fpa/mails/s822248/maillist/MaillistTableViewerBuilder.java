package de.bht.fpa.mails.s822248.maillist;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.table.MessageValues;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MaillistTableViewerBuilder extends TableViewerBuilder {

	public MaillistTableViewerBuilder(Composite parent) {
		super(parent);
		createColumnImportance(this);
		createColumnSubject(this);
	}

	private void createColumnImportance(TableViewerBuilder tableCreator) {
		ColumnBuilder importance = tableCreator.createColumn("Imp");
		importance.bindToValue(MessageValues.IMPORTANCE);
		importance.setPixelWidth(24);
		importance.setCustomLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				Object o = cell.getElement();
				if (o instanceof Message) {
					Message msg = (Message) o;
					String img = "icons/folder.png";
					if (msg.getImportance() == Importance.HIGH) {
						img = "icons/file.png";
					} else if (msg.getImportance() == Importance.LOW) {
						img = "icons/folder.png";
					}
					Image image = Activator.imageDescriptorFromPlugin(
							Activator.PLUGIN_ID, img).createImage();
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
