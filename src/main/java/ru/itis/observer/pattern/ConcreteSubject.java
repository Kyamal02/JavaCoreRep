package ru.itis.observer.pattern;

import ru.itis.observer.pattern.interfaces.Observer;
import ru.itis.observer.pattern.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    public List<Observer> observers = new ArrayList<>();

    private String line = "Базовая строка субъекта";
    private int count = 0; // Базовое число

    @Override
    public String getLine() {
        return line;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void changeAllFields() {
        line = "Строка изменена";
        count++;
        System.out.println("Поменяли все поля в объекте");
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
