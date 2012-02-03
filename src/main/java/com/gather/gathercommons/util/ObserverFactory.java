package com.gather.gathercommons.util;

import com.gather.gathercommons.util.interfaces.ObservableHandler;
import com.gather.gathercommons.util.interfaces.Observer;

public class ObserverFactory {

    public static ObservableHandler createObservableHandler() {
        return new ObservableHandler();
    }

    public static ObservableHandler createObservableHandler(Observer observer) {
        return new ObservableHandler(observer);
    }
}
