package com.example.refactorstudydemo.extractMethod.price;

import com.example.refactorstudydemo.extractMethod.Movie;

/**
 * Created by admin on 2024/1/9    10:58
 */
public class SexPrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.SEX;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 4;
        if (daysRented > 2) {
            result += (daysRented - 2) * 2;
        }
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 3;
    }
}
