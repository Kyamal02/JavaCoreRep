package ru.itis.observer.pattern;

import ru.itis.observer.pattern.interfaces.Observer;


public class ConcreteObserver implements Observer {
    ConcreteSubject subject;
    String observerLine;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        observerLine = subject.getLine();
    }

    @Override
    public void update() {
        if (!observerLine.equals(subject.getLine())) {
            observerLine = subject.getLine();
            System.out.println("Наблюдатель 1 согласовал данные");
        } else{
            System.out.println("Данные уже согласованы");
        }
    }
}
