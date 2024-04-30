package com.example.refactorstudydemo.extractMethod.price;

/**
 * Created by admin on 2024/1/9    10:56
 */
public abstract class Price {
    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

}
