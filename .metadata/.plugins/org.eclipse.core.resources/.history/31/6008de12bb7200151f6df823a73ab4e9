package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;

public class FolderItem extends FileTreeItem {

	public FolderItem(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Image getImage() {
		// TODO imges erstellen
		return new Image(null, "img/folder.png");
	}
	
	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return file.list().length > 0;
	}
	
	@Override
	public List<IMessageTreeItem> getChildren() {
		ArrayList<IMessageTreeItem> children = new ArrayList<>();
		
		for(File item : file.listFiles()){
			if (item.isDirectory()){
				children.add(new FolderItem(item));
			}else{
				children.add(new FileItem(item));
			}
		}
		
		return children;
	}
	
}
