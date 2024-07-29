package com.example.refactorstudydemo

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
    fun getPrice(quantity: Int, itemPrice: Double): Double {
        val basePrice = quantity * itemPrice
        val discountLevel = if (quantity > 100) 2 else 1
        return discountedPrice(basePrice, discountLevel)
    }

    fun discountedPrice(basePrice: Double, discountLevel: Int): Double {
        return if (discountLevel == 2) basePrice * 0.1 else basePrice * 0.05
    }
}

