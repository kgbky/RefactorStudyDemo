package com.example.refactorstudydemo.extractMethod.price;

import com.example.refactorstudydemo.extractMethod.Movie;

/**
 * Created by admin on 2024/1/9    10:58
 */
public class ChildrensPrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
