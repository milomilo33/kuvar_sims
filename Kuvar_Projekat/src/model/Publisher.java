package model;

import event.Observer;

public interface Publisher {			
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}