package com.example.refactorstudydemo

import java.util.Date

/**
 * Created by zn-guest25 on 2024/7/26    9:22
 *
 * 学习条件表达式的重构
 *
 * 分解条件表达式
 * 合并条件表达式
 */

val summerStart = Date(4654564L)
val summerEnd = Date(4654564L)
const val winterRate = 1.8
const val summerRate = 2.8
const val winterServiceCharge = 3

fun getCharge(quantity: Int): Double {
    val date = Date(System.currentTimeMillis())
    //计算总价 重构后 代码就像注释一样清楚明白
    val charge = if (notSummer(date)) {
        winterCharge(quantity)
    } else {
        summerCharge(quantity)
    }
    return charge

    //计算总价 重构前代码
//    val charge = if (date.before(summerStart) || date.after(summerEnd)) {
//        quantity * winterRate + winterServiceCharge
//    } else {
//        quantity * summerRate
//    }
}

private fun summerCharge(quantity: Int) = quantity * summerRate

private fun winterCharge(quantity: Int) = quantity * winterRate + winterServiceCharge

private fun notSummer(date: Date) = date.before(summerStart) || date.after(summerEnd)

//合并条件表达式
fun disabilityAmount(seniority: Int, monthsDisable: Int, isPartTime: Boolean): Int {
    if (isNotEligibleForDisability(seniority, monthsDisable, isPartTime)) return 0
    return 11

    //重构前
//    if (seniority < 2) return 0
//    if (monthsDisable > 12) return 0
//    if (isPartTime) return 0
//    return 11
}

//见名知意 方法名就是注释
fun isNotEligibleForDisability(seniority: Int, monthsDisable: Int, isPartTime: Boolean) =
    seniority < 2 || monthsDisable > 12 || isPartTime