package ru.itis.observer.pattern.interfaces;

public interface Subject {
    public void attach(Observer o);

    public void detach(Observer o);

    void notifyObservers();

    String getLine();
    int getCount();
}
