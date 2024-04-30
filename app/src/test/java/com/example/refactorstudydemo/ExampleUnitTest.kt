package com.example.refactorstudydemo

import com.example.refactorstudydemo.extractMethod.Customer
import com.example.refactorstudydemo.extractMethod.Movie
import com.example.refactorstudydemo.extractMethod.Movie.CHILDRENS
import com.example.refactorstudydemo.extractMethod.Movie.NEW_RELEASE
import com.example.refactorstudydemo.extractMethod.Movie.REGULAR
import com.example.refactorstudydemo.extractMethod.Movie.SEX
import com.example.refactorstudydemo.extractMethod.Rental
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        var result = testRefactorAndy()
        assertEquals(result, true)
        result = testRefactorLucy()
        assertEquals(result, true)
        result = testRefactorAndyWithSex()
        assertEquals(result, true)
    }

    private fun testRefactorAndy(): Boolean {
        val customer = Customer("Andy")
        customer.addRental(Rental(Movie("Move1", NEW_RELEASE), 7))
        customer.addRental(Rental(Movie("Move2", CHILDRENS), 4))
        customer.addRental(Rental(Movie("Move3", REGULAR), 3))
        val result = customer.statement()
        val rightResult = """Rental Record for Andy
	Move1	21.0
	Move2	3.0
	Move3	3.5
Amount owed is 27.5
You earned 4 frequent renter points"""
        return rightResult == result
    }

    private fun testRefactorLucy(): Boolean {
        val customer = Customer("Lucy")
        customer.addRental(Rental(Movie("Move1", NEW_RELEASE), 1))
        customer.addRental(Rental(Movie("Move2", CHILDRENS), 1))
        customer.addRental(Rental(Movie("Move3", REGULAR), 1))
        val result = customer.statement()
        val rightResult = """Rental Record for Lucy
	Move1	3.0
	Move2	1.5
	Move3	2.0
Amount owed is 6.5
You earned 3 frequent renter points"""
        return rightResult == result
    }

    private fun testRefactorAndyWithSex(): Boolean {
        val customer = Customer("Andy")
        customer.addRental(Rental(Movie("Move1", NEW_RELEASE), 7))
        customer.addRental(Rental(Movie("Move2", CHILDRENS), 4))
        customer.addRental(Rental(Movie("Move3", REGULAR), 3))
        customer.addRental(Rental(Movie("MoveSex", SEX), 2))
        val result = customer.statement()
        val rightResult = """Rental Record for Andy
	Move1	21.0
	Move2	3.0
	Move3	3.5
	MoveSex	4.0
Amount owed is 31.5
You earned 7 frequent renter points"""
        return rightResult == result
    }

}