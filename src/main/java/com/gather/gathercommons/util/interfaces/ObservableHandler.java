package com.gather.gathercommons.util.interfaces;

import java.util.ArrayList;
import java.util.List;

public class ObservableHandler implements Observable {

    private List<Observer> observers;

    public ObservableHandler(Observer observer) {
        this.getObservers().add(observer);
    }

    public ObservableHandler() {

    }

    @Override
    public List<Observer> getObservers() {
        if (this.observers == null) {
            this.observers = new ArrayList<Observer>();
        }

        return this.observers;
    }

    @Override
    public void addObserver(Observer observer) {
        if (this.getObservers().indexOf(observer) == -1) {
            this.getObservers().add(observer);
        }

    }

    @Override
    public void updateObservers(Observable observable) {
        if (!this.getObservers().isEmpty()) {
            for (Observer a : this.getObservers()) {
                a.update(observable);
            }
        }

    }

}
