package com.example.refactorstudydemo

import java.util.Date
import kotlin.math.min

/**
 * Created by zn-guest25 on 2024/7/27    10:15
 *
 * 学习如何简化函数调用(10章)
 *
 * ReName/add Par/remove Par
 * Separate Query from modifier (查询函数 与 修改函数 分离)
 * Parameterize method (合并函数-类似的)
 * Replace Par with Explicit Methods (新增函数已取代参数)
 * Preserve Whole Object (方法参数使用整个对象)
 * Replace Par with Methods (以函数代替参数)
 * Introduce Par object (把经常一起出现的参数封装为对象)
 * Hide Method（未被其他类使用的 函数和字段 用private修饰 lint自带该检查）
 * Replace Constructor with Factory Method  (以工厂方法代替构成函数)
 * Encapsulate Downcast (封装向下转型)
 * Replace Error code With Exception (用异常取代错误码)
 */

//查询和修改分离
fun demo(names: List<String>) {
    sendAlert(names)
    val f = foundPeople(names)
    //do something with f
}

private fun sendAlert(names: List<String>) {
//    names.forEach {
//        if (it == "Don" || it == "John") {
//            sendAlert()
//        }
//    }
    val result = foundPeople(names)
    if (result.isNotEmpty()) sendAlert()
}

fun sendAlert() {
    //do something
}

//重构后
private fun foundPeople(names: List<String>): String {
    names.forEach {
        if (it == "Don" || it == "John") {
            return it
        }
    }
    return ""
}

//合并类似函数
fun baseCharge(): Float {
//    var result = min(lastUsage(), 100f) * 0.03f
//    if (lastUsage() > 100) {
//        result += (min(lastUsage(), 200f) - 100f) * 0.05f
//    }
//    if (lastUsage() > 200) {
//        result += (lastUsage() - 200f) * 0.07f
//    }
//    return result

    //重构后
    var result = usageInRange(0, 100) * 0.03f
    result += usageInRange(100, 200) * 0.05f
    result += usageInRange(200, Int.MAX_VALUE) * 0.07f
    return result
}

fun lastUsage() = 80f

//重构后
fun usageInRange(start: Int, end: Int): Float =
    if (lastUsage() > start) min(lastUsage(), end.toFloat()) - start else 0f

//Replace Par with Explicit Methods (新增函数已取代参数)
class S {
    private var height = 0
    private var width = 0

    //重构前
    fun setValue(name: String, value: Int) {
        if (name == "height") height = value
        else if (name == "width") width = value
    }

    //重构后
    fun setHeight(height: Int) {
        this.height = height
    }

    fun setWidth(width: Int) {
        this.width = width
    }
}

//Preserve Whole Object (方法参数使用整个对象)
data class TempRange(val low: Int, val high: Int) {
    fun includes(args: TempRange) = args.low >= this.low && args.high <= this.high
}

class HeatingPlan {
    private var range: TempRange = TempRange(10, 20)

    fun withinRange(daysTempRange: TempRange): Boolean {
        return range.includes(daysTempRange)
    }
}

class Room {
    fun withinPlan(plan: HeatingPlan): Boolean {
        return plan.withinRange(daysTempRange())
    }

    private fun daysTempRange(): TempRange {
        return TempRange(3, 30)
    }
}

//Replace Par with Methods (以函数代替参数)
class P {
    private val quantity: Int = 1
    private val itemPrice: Double = 20.0

    fun getPrice(): Double {
        val basePrice = quantity * itemPrice
        return discountedPrice(basePrice)
    }

    fun discountedPrice(basePrice: Double): Double {
        return if (getDiscountLevel() == 2) basePrice * 0.1 else basePrice * 0.05
    }

    private fun getDiscountLevel(): Int {
        val discountLevel = if (quantity > 100) 2 else 1
        return discountLevel
    }

}

//Introduce Par object (参数封装为对象)
data class Entry(val value: Double, val chargeDate: Date)

class AccountT {
    //mock data
    val entryList = listOf<Entry>()

    //可以使用Range模式重构
    fun getFlowBetween(range: DateRange): Double {
        var result = 0.0
        entryList.forEach {
            if (range.include(it.chargeDate)) {
                result += it.value
            }
        }
        return result
    }


}

//开始重构
data class DateRange(val start: Date, val end: Date) {
    fun include(arg: Date): Boolean {
        return arg == start || arg == end || arg.after(start) || arg.before(end)
    }
}

//Replace Constructor with Factory Method  (以工厂方法代替构成函数，工厂方法模式)

//Encapsulate Downcast (封装向下转型)
class Reading //表示书籍

val readings = listOf<Any>() //阅读历史

//重构前 函数调用者需要进行向下转型
fun lastReading(): Any {
    return readings.last()
}

//重构后 向下转型搬移到该函数中
fun lastReadingAfter(): Reading {
    return readings.last() as Reading
}
