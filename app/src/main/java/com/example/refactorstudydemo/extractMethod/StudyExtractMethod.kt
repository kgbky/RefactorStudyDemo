package com.example.refactorstudydemo.extractMethod

/**
 * Created by admin on 2024/2/8    14:15
 */
class StudyExtractMethod {

    //原始代码
    fun printOwing(amount: Double) {
        printBanner()

        println("name: Andy")
        println("amount: $amount")
    }


    private fun printBanner() {
    }


    //重构后
    fun printOwingV2(amount: Double) {
        printBanner()

        printDetails(amount)
    }

    /*
    小函数更容易被复用和修改。同时使高层函数易被阅读
     */
    private fun printDetails(amount: Double) {
        println("name: Andy")
        println("amount: $amount")
    }


    //demo
    fun printOwingV3(initAmount: Double) {
        printBanner()

        val outStanding = getOutStanding(initAmount)

        printDetails(outStanding)
    }

    private fun getOutStanding(initAmount: Double): Double {
        val list = listOf(2, 3, 7, 4)
        var outStanding = initAmount * 1.2
        list.forEach { outStanding += it }
        return outStanding
    }


}