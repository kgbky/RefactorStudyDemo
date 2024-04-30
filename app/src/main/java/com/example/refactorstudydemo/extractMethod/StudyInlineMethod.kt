package com.example.refactorstudydemo.extractMethod

/**
 * Created by admin on 2024/2/8    14:48
 * 内联手法，可消除冗余的中间层
 */
class StudyInlineMethod {

    fun studyV1(): Boolean {
        val basePrice = 1000
        return basePrice > 99
    }

    /*
    内联临时变量
     */
    fun studyV2(): Boolean {
        return 1000 > 99
    }

    fun getPrice(): Double {
        val basePrice = 5 * 2.5
        val discountFactor = if (basePrice > 1000) 0.95 else 0.98
        return basePrice * discountFactor
    }

}