package ru.itis.generics.ex8;

public class Gen2 extends Gen<String> {
    public Gen2(String obj) {
        super(obj);
    }

    // компилятор сгенерирует в байт коде мостовой метод
    @Override
    String getObj() {
        return obj;
    }
}
