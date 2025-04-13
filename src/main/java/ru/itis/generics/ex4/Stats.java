package ru.itis.generics.ex4;

public class Stats<T extends Number> {

    T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    // во всех случаях возвращаем результат double
    double average() {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i].doubleValue();

        return sum / nums.length;
    }

    boolean isSameAvg(Stats<T> ob) {
        return average() == ob.average();
    }

    boolean isSameAvg2(Stats<?> ob) {
        return average() == ob.average();
    }


    public static void main(String[] args) {
        Stats<Integer> stats1 = new Stats<>(new Integer[]{5, 6, 7, 8, 10});

        Stats<Double> stats2 = new Stats<>(new Double[]{5.0, 6.0, 7.0, 76.0, 10.0});

//        System.out.println(stats1.isSameAvg(stats2)); // такое нельзя сделать, так как Double
        System.out.println(stats1.isSameAvg2(stats2)); // теперь работает верно
    }
}
