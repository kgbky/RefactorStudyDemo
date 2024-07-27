package com.example.refactorstudydemo

import java.util.Date

/**
 * Created by zn-guest25 on 2024/7/26    9:22
 *
 * 学习条件表达式的重构
 *
 * 分解条件表达式
 * 合并条件表达式
 * 以 break 或 return 取代控制标记
 * 消灭嵌套表达式
 * 以多态(子类)取代条件表达式。适用于：多个条件表达式作用于同一个类型码
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


//以 break 取代控制标记
fun checkSecurity(names: List<String>) {
    var found = false
    names.forEach {
        if (!found) {
            if (it == "Don" || it == "John") {
                //do something
                found = true
            }
        }
    }

    //重构后
    names.forEach {
        if (it == "Don" || it == "John") {
            //do something
            return@forEach
        }
    }
}

//以 return 取代控制标记
fun checkSecurity2(names: List<String>) {
    var found = ""
    names.forEach {
        if (found.isEmpty()) {
            if (it == "Don" || it == "John") {
                //do something
                found = it
            }
        }
    }
    //do something with found

    //重构后
    val found2 = foundMiscreant(names)
    //do something with found2
}

private fun foundMiscreant(names: List<String>): String {
    names.forEach {
        if (it == "Don" || it == "John") {
            //do something
            return it
        }
    }
    return ""
}

//演示重构嵌套表达式
fun getPayAmount(data: MockData): Int {
    var result: Int
    if (data.isDead) {
        result = 100
    } else {
        if (data.isSeparated) {
            result = 200
        } else {
            if (data.isRetired) result = 300
            else result = 50
        }
    }
    return result
}

fun getPayAmountAfter(data: MockData): Int {
    if (data.isDead) {
        return 100
    }
    if (data.isSeparated) {
        return 200
    }
    if (data.isRetired) return 300
    return 50
}

fun getAdjustedCapital(data: MockData): Float {
//    var result = 0f
//    if (data.capital > 0) {
//        if (data.intRate > 0 && data.duration > 0)
//            result = (100 / data.duration) * 10
//    }

//    if (data.capital <= 0) return 0f
//    if (data.intRate <= 0) return 0f
//    if (data.duration <= 0) return 0f
    return if (data.isError()) 0f else (100 / data.duration) * 10
}

data class MockData(
    val isDead: Boolean, val isSeparated: Boolean, val isRetired: Boolean,
    val capital: Float, val intRate: Float, val duration: Float,
) {
    fun isError(): Boolean {
        return capital <= 0 || intRate <= 0 || duration <= 0
    }
}

//用多态替换条件表达式  源程序
class EmployeeT(val type: EmployeeType) {
    fun payAmount(): Int {
        return type.payAmount()
    }

}

abstract class EmployeeType {
    abstract fun getTypeCode(): Int
    abstract fun payAmount(): Int

    companion object {
        val ENGINEER = 0 //表示工程师
        val SALESMAN = 1 //表示销售
        val MANAGER = 2 //表示经理
    }
}

class EngineerT : EmployeeType() {
    override fun getTypeCode(): Int {
        return ENGINEER
    }

    override fun payAmount(): Int {
        return 13
    }
}

class SalesmanT : EmployeeType() {
    override fun getTypeCode(): Int {
        return SALESMAN
    }

    override fun payAmount(): Int {
        return 11
    }
}

class ManagerT : EmployeeType() {
    override fun getTypeCode(): Int {
        return MANAGER
    }

    override fun payAmount(): Int {
        return 10
    }
}