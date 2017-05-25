package io.github.ajoz.workshop.intro;

public class Car {
    private int year = 0;

    public static void test() {
        CarsKt.getYear(new Car()); //Kotlin extension is just a static method
    }
}
