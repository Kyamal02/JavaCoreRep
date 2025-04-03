package ru.itis.immutable;

import java.util.Date;

public final class ImmutableWithMutableFields {
    private final int id;
    private final Date date; // изменяемый объект

    public ImmutableWithMutableFields(int id, Date date) {
        this.id = id;
        this.date = new Date(date.getTime());// защитили ссылку, теперь изменение извне date не поменяет тут ничего
    }

    public int getId() {
        return id;
    }

    // тут тоже требуется вернуть копию
    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public String toString() {
        return "ImmutableWithMutableFields{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();
        ImmutableWithMutableFields immutableWithMutableFields = new ImmutableWithMutableFields(1, date1);
        System.out.println(immutableWithMutableFields);

        Date date2 = immutableWithMutableFields.getDate();

        Thread.sleep(3000);
        date1.setTime(new Date().getTime());
        date2.setTime(new Date().getTime());
        System.out.println(immutableWithMutableFields);
    }
}
