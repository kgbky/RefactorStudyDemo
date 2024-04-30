package com.example.refactorstudydemo

import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

/**
 * Created by admin on 2024/2/7    13:56
 */
class FileReaderTest {

    private var input: FileReader? = null

    @Before
    fun before() {
        println("invoke before")
        try {
            input = FileReader("E:\\aiart\\art\\src\\test\\java\\com\\example\\aiart\\my_file.text")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    @After
    fun after() {
        println("invoke after")
        try {
            input?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testRead() {
        println("invoke testRead")
        var char: Char? = '&'
        for (key in 1..4) {
            char = input?.read()?.toChar()
        }
        assertEquals(char, 'd')
    }

    @Test
    fun testReadAtEnd() {
        println("invoke testReadAtEnd")
        var char: Char? = '&'
        for (key in 0 until 1000) {
            char = input?.read()?.toChar()
        }
//        assertEquals(char, -1)
    }

    /**
     * 测试边界条件
     */
    @Test
    fun testReadBoundaries() {
        println("invoke testReadBoundaries")
        assertEquals("read first char", 'B', input?.read()?.toChar())
    }

    @Test
    fun testEmptyRead() {
        println("invoke testEmptyRead")
        val emptyRead =
            FileReader("E:\\aiart\\art\\src\\test\\java\\com\\example\\aiart\\empty.text")
        val end = emptyRead.read()
        assertEquals(-1, end)

        assertEquals(-1, emptyRead.read())
    }

}