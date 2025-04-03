package ru.itis.observer.pattern;

import ru.itis.observer.pattern.interfaces.Observer;


public class Main {
    public static void main(String[] args) {

        ConcreteSubject subject = new ConcreteSubject();

        Observer observer1 = new ConcreteObserver(subject);
        Observer observer2 = new ConcreteObserver2(subject);

        subject.attach(observer1);
        subject.attach(observer2);

        subject.changeAllFields();

    }
}
