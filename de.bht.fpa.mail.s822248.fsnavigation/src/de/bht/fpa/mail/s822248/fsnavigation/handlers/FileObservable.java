package de.bht.fpa.mail.s822248.fsnavigation.handlers;

import java.util.Observable;


public class FileObservable extends Observable {
	private String path;
	private  static FileObservable fileObservable=null;

	
	private FileObservable(){
		
	}
	
	public static  FileObservable getInstance(){
		if(fileObservable == null) fileObservable= new FileObservable();
		return fileObservable;
	}

	public void setPath(String path){
		setChanged();
		notifyObservers(path);
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}

}
