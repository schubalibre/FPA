package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import java.util.Observable;


public class FileObservable extends Observable {
	
	private String path;
	private final static FileObservable fileObservable = new FileObservable();

	private FileObservable(){}
	
	public static FileObservable getInstance(){
		return fileObservable;
	}

	public void setPath(String path){
		setChanged();
		this.path = path;
		notifyObservers(path);
	}
	
	public String getPath(){
		return path;
	}

}
