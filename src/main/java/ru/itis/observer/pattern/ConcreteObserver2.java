package ru.itis.observer.pattern;

import ru.itis.observer.pattern.interfaces.Observer;
import ru.itis.observer.pattern.interfaces.Subject;

public class ConcreteObserver2 implements Observer {
    private ConcreteSubject subject;
    private int observerCount;

    public ConcreteObserver2(ConcreteSubject subject) {
        this.subject = subject;
        this.observerCount = subject.getCount();
    }


    @Override
    public void update() {
        if (subject.getCount() != observerCount) {
            observerCount = subject.getCount();
            System.out.println("Наблюдатель 2 согласовал данные");
        }else{
            System.out.println("Данные уже согласованные");
        }
    }
}
