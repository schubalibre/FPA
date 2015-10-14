package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;


public abstract class FileTreeItem implements IMessageTreeItem {
	
	protected final File file;

	public FileTreeItem(File file) {
		this.file = file;
	}

	@Override
	public String getText() {
		return file.getName();
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IMessageTreeItem> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
