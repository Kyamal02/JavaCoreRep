package ru.itis.generics.task;


import java.util.ArrayList;
import java.util.List;

public class Basket<T extends Fruit> {
    private ArrayList<T> basket;

    public Basket(ArrayList<T> basket) {
        this.basket = basket;
    }

    public Basket() {
        basket = new ArrayList<>();
    }

    public double getWeight() {
        double result = 0;
        for (T t : basket) {
            result += t.getWeight().doubleValue();
        }
        return result;
    }

    public void add(T fruit) {
        basket.add(fruit);
    }

    public int compareTo(Basket<?> otherBasket) {
        Double result = this.getWeight() - otherBasket.getWeight();
        return result.intValue();
    }

    public ArrayList<T> getBasket() {
        return basket;
    }

    public static <T extends Fruit> void transfer(Basket<? extends T> src, Basket<? super T> dsc) {
        dsc.basket.addAll(src.basket);
        src.basket.clear();
    }


}
