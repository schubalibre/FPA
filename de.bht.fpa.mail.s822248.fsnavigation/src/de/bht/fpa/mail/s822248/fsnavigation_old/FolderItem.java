package de.bht.fpa.mail.s822248.fsnavigation;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.JAXB;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class FolderItem extends FileTreeItem {

  public FolderItem(File file) {
    super(file);
  }

  @Override
  public Image getImage() {
    return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.png").createImage();
  }

  @Override
  public boolean hasChildren() {
    if (file.list() == null) {
      return false;
    }

    for (File file : file.listFiles()) {
      if (file.isDirectory()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<IMessageTreeItem> getChildren() {

    ArrayList<IMessageTreeItem> children = new ArrayList<>();

    for (File item : file.listFiles()) {
      if (item.isDirectory()) {
        children.add(new FolderItem(item));
      }
      // else {
      // children.add(new FileItem(item));
      // }
    }

    return children;
  }

  public List<Message> getMessages() {

    List<Message> msgs = new ArrayList<Message>();

    for (File item : file.listFiles()) {
      try {
        Message msg = JAXB.unmarshal(item, Message.class);
        if (msg.getId() != null) {
          msgs.add(msg);
        }
      } catch (Exception e) {
      }
    }
    return msgs;
  }

}
