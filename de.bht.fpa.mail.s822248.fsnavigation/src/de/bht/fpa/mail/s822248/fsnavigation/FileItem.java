package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;

public class FileItem extends FileTreeItem {

	public FileItem(File file) {
		super(file);
	}
	
	@Override
	public Image getImage() {
		// TODO imges erstellen
		return new Image(null, "img/file.png");
	}
	
	@Override
	public List<IMessageTreeItem> getChildren() {
		return new ArrayList<>();
	}

}
