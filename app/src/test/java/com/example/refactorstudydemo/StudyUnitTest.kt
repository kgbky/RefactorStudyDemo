package com.example.refactorstudydemo

import org.junit.Assert.assertEquals
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

    }

}