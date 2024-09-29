package ru.kholmogorova.Banks.TIme;

import java.util.GregorianCalendar;


public class MyDate {
    public MyDate(int year, int month, int day) {
        date = new GregorianCalendar(year, month - 1, day);
    }
    public long Subtract(MyDate otherDate) {
        long difference = ((date.getTime().getTime() - otherDate.date.getTime().getTime()) / 1000 / 60 / 60 / 24);
        return difference;
    }

    private final GregorianCalendar date;
}
