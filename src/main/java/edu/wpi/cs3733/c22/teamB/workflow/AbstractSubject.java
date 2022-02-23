package edu.wpi.cs3733.c22.teamB.workflow;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject<T> {

    private final List<AbstractObserver<T>> observers = new ArrayList<>();
    private T state;

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(AbstractObserver<T> observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (AbstractObserver<T> observer : observers) {
            observer.update();

        }
    }
}
