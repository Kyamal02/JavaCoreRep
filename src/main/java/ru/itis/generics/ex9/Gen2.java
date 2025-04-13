package ru.itis.generics.ex9;

public class Gen2 extends Gen<String> {

    public Gen2(String obj) {
        super(obj);
    }
    // компилятором будет создам мостовой метод, так как при стирании типов
    // у класса родителя будет возвращаемый тип Object, а сигнатура должна совпадать
    // такие методы не обязательно генерируются только с дженериками
    @Override
    public String getObj() {
        return super.getObj();
    }
}
