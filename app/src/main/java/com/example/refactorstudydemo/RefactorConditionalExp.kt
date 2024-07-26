package com.example.refactorstudydemo

import java.util.Date

/**
 * Created by zn-guest25 on 2024/7/26    9:22
 *
 * 学习条件表达式的重构
 *
 * 分解条件表达式
 */

val summerStart = Date(4654564L)
val summerEnd = Date(4654564L)
const val winterRate = 1.8
const val summerRate = 2.8
const val winterServiceCharge = 3

fun getCharge(quantity: Int): Double {
    val date = Date(System.currentTimeMillis())
    //计算总价
    val charge = if (date.before(summerStart) || date.after(summerEnd)) {
        quantity * winterRate + winterServiceCharge
    } else {
        quantity * summerRate
    }
    return charge
}