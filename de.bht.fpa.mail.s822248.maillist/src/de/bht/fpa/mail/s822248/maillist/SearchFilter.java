package de.bht.fpa.mail.s822248.maillist;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class SearchFilter extends ViewerFilter {
  
  public static String filterText = "";

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    String date = null;
    
    Message msg = (Message) element;
    if(msg.getSubject().toLowerCase().contains(filterText)) return true;
    if(msg.getText().toLowerCase().contains(filterText)) return true;
    
    date = new SimpleDateFormat("dd.MM.YYYY").format(msg.getReceived());
    if(date.contains(filterText)) return true;
    
    date = new SimpleDateFormat("dd.MM.YYYY").format(msg.getSent());
    if(date.contains(filterText)) return true;
    
    if(msg.getSender().getEmail().toLowerCase().contains(filterText)) return true;
    if(msg.getSender().getPersonal().toLowerCase().contains(filterText)) return true;
    
    for(Recipient r : msg.getRecipients()){
      if(r.getEmail().toLowerCase().contains(filterText)) return true;
      if(r.getPersonal().toLowerCase().contains(filterText)) return true;
    }
    
   
    return false;
  }

}
