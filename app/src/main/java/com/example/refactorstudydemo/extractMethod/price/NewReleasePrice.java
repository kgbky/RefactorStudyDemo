package com.example.refactorstudydemo.extractMethod.price;

import com.example.refactorstudydemo.extractMethod.Movie;

/**
 * Created by admin on 2024/1/9    10:58
 */
public class NewReleasePrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if (daysRented > 1) return 2;
        else return super.getFrequentRenterPoints(daysRented);
    }
}
