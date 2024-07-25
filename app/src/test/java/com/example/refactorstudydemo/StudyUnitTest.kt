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
        val refact = Course("Refactoring", true)
        kentAfter.addCourse(refact)
        kentAfter.removeCourse(refact)

        val a = kentAfter.getCourses().count { it.isAdvanced }
        val b = kentAfter.numberOfAdvCourse()
        assertEquals(a, b)
    }

}