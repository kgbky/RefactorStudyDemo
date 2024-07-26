package com.example.refactorstudydemo

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StudyUnitTest {

    @Test
    fun addition_isCorrect() {
        //重构后
        val kentAfter = StudentAfter("Andy")
        kentAfter.addCourse(Course("Smalltalk Programming", true))

        val k1 = kentAfter.getCourses().count { it.isAdvanced }
        val b1 = kentAfter.numberOfAdvCourse()
        assertEquals(k1, 1)
        assertEquals(k1, b1)

        val refact = Course("Refactoring", true)
        kentAfter.addCourse(refact)
        val k2 = kentAfter.getCourses().count { it.isAdvanced }
        val b2 = kentAfter.numberOfAdvCourse()
        assertEquals(k2, 2)
        assertEquals(k2, b2)

        kentAfter.removeCourse(refact)
        val k3 = kentAfter.getCourses().count { it.isAdvanced }
        val b3 = kentAfter.numberOfAdvCourse()
        assertEquals(k3, 1)
        assertEquals(k3, b3)
    }

    @Test
    fun refactorTypeCodeWithClass() {
        val type = EmployeeAfter.create(1).getType()
        assertEquals(type, 1)

        val type2 = EmployeeAfter.create(2).getType()
        assertEquals(type2, 2)

        assertThrows(IllegalArgumentException::class.java) {
            val type3 = EmployeeAfter.create(4).getType()
            assertEquals(type3, 4)
        }
    }

    @Test
    fun refactorConditionalExp() {
        val charge0 = getCharge(0)
        val charge1 = getCharge(1)
        val charge2 = getCharge(2)
        val charge10 = getCharge(10)
        val charge99 = getCharge(99)
        val charge100 = getCharge(100)

        assertEquals(3.0, charge0, 0.08)
        assertEquals(4.8, charge1, 0.08)
        assertEquals(6.6, charge2, 0.08)
        assertEquals(21.0, charge10, 0.08)
        assertEquals(181.2, charge99, 0.08)
        assertEquals(183.0, charge100, 0.08)
    }

}