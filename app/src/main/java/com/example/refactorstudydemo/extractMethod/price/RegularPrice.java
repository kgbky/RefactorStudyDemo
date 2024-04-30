package com.example.refactorstudydemo.extractMethod.price;

import com.example.refactorstudydemo.extractMethod.Movie;

/**
 * Created by admin on 2024/1/9    10:58
 */
public class RegularPrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}
