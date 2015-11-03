package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import java.util.Observable;

public final class FileObservable extends Observable {
  private String path;
  private static final FileObservable FILE_OBSERVABLE = new FileObservable();

  private FileObservable() {

  }

  public static FileObservable getInstance() {
    return FILE_OBSERVABLE;
  }

  public void setPath(String path) {
    setChanged();
    notifyObservers(path);
    this.path = path;
  }

  public String getPath() {
    return path;
  }

}
