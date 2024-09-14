package com.example.refactorstudydemo.extractMethod.price;

import static org.junit.Assert.assertEquals;

import com.example.refactorstudydemo.extractMethod.Movie;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zn-guest25 on 2024/9/12    14:51
 * <p>
 * 用通义灵码写单元测试
 */
public class SexPriceTest {

    private Price sexPrice;

    @Before
    public void setUp() {
        sexPrice = new SexPrice();
    }

    @Test
    public void getPriceCode() {
        assertEquals(Movie.SEX, sexPrice.getPriceCode());
    }

    @Test
    public void getCharge() {
        double result = sexPrice.getCharge(0);
        assertEquals(4.0, result, 0.01);
        result = sexPrice.getCharge(1);
        assertEquals(4.0, result, 0.01);
        result = sexPrice.getCharge(2);
        assertEquals(4.0, result, 0.01);
        result = sexPrice.getCharge(3);
        assertEquals(6.0, result, 0.01);
        result = sexPrice.getCharge(5);
        assertEquals(10.0, result, 0.01);
        result = sexPrice.getCharge(-1);
        assertEquals(4.0, result, 0.01);
    }

    @Test
    public void getFrequentRenterPoints() {
        int result = sexPrice.getFrequentRenterPoints(0);
        assertEquals(3, result);

        result = sexPrice.getFrequentRenterPoints(1);
        assertEquals(3, result);

        result = sexPrice.getFrequentRenterPoints(2);
        assertEquals(3, result);

        result = sexPrice.getFrequentRenterPoints(3);
        assertEquals(3, result);

        result = sexPrice.getFrequentRenterPoints(5);
        assertEquals(3, result);

        result = sexPrice.getFrequentRenterPoints(-1);
        assertEquals(3, result);
    }
}