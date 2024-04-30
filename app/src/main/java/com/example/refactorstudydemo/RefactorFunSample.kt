package com.example.refactorstudydemo

/**
 * Created by admin on 2024/4/30    15:00
 */
class RefactorFunSample {

    // Introduce Explaining Var (引入解释性变量)
    fun sample1(platFrom: String, browser: String, init: Boolean, resize: Int) {
        //重构前的表达式，复杂且难以阅读
        if (platFrom.indexOf("MAC") > -1 && browser.indexOf("IE") > -1 && init && resize > 0) {
            //do something
        }

        //重构后，逻辑清晰且易于阅读
        val isMacOs = platFrom.indexOf("MAC") > -1
        val isIEBrowser = browser.indexOf("MAC") > -1
        val resized = resize > 0
        if (isMacOs && isIEBrowser && init && resized) {
            //do something
        }
    }

    //Split Temporary Var (分解临时变量)
    fun sample2(width: Int, height: Int) {
        //重构前 temp承担了2个职责
        var temp = 2 * (width + height)
        println(temp)
        temp = width * height
        println(temp)

        //重构后 每个变量只有一个职责，易于阅读
        val tempRefactor = 2 * (width + height)
        println(tempRefactor)
        val area = width * height
        println(area)
    }

    //Replace Method with Method Object (用函数对象替换函数)
    fun sample3(inputValue: Int, quantity: Int, yearToDate: Int): Int {
        //这段代码没有业务上的含义，只为了模拟复杂长函数
        val importantValue1 = (inputValue * quantity) + delta()
        var importantValue2 = (inputValue * yearToDate) + 100
        if (yearToDate - importantValue1 > 100) importantValue2 -= 20
        val importantValue3 = importantValue2 * 7
        return importantValue3 - 2 * importantValue1

//        //重构后，通过对象调用
//        return Sample3(this, inputValue, quantity, yearToDate).compute()
    }

    private fun delta(): Int {
        return 10
    }

    //开始重构
    class Sample3(
        private val source: RefactorFunSample,
        private val inputValue: Int,
        private val quantity: Int,
        private val yearToDate: Int
    ) {
        private var importantValue1: Int = 0
        private var importantValue2: Int = 0
        private var importantValue3: Int = 0

        fun compute(): Int {
            importantValue1 = (inputValue * quantity) + source.delta()
            importantValue2 = (inputValue * yearToDate) + 100
            importantThing() //重构之后可以使用 Extract Method 来精简函数
            importantValue3 = importantValue2 * 7
            return importantValue3 - 2 * importantValue1
        }

        private fun importantThing() {
            if (yearToDate - importantValue1 > 100) importantValue2 -= 20
        }
    }
    //重构结束

}
