package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;

public class FolderItem extends FileTreeItem {

	public FolderItem(File file) {
		super(file);
	}
	
	@Override
	public Image getImage() {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				"icons/folder.png").createImage();
	}
	
	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return file.list() != null;
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
