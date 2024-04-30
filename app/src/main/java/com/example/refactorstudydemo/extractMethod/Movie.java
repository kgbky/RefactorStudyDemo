package com.example.refactorstudydemo.extractMethod;

import com.example.refactorstudydemo.extractMethod.price.ChildrensPrice;
import com.example.refactorstudydemo.extractMethod.price.NewReleasePrice;
import com.example.refactorstudydemo.extractMethod.price.Price;
import com.example.refactorstudydemo.extractMethod.price.RegularPrice;
import com.example.refactorstudydemo.extractMethod.price.SexPrice;

/**
 * Created by admin on 2024/1/8    11:59
 */
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int SEX = 3;

    private String title;
    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDRENS:
                price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case SEX:
                price = new SexPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

}
