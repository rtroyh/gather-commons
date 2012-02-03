package com.gather.gathercommons.util.interfaces;

import java.util.List;

public interface Observable {

	public void addObserver(Observer observer);

	public List<Observer> getObservers();

	public void updateObservers(Observable observable);
}
