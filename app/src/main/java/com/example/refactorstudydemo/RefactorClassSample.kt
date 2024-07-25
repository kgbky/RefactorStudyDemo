package com.example.refactorstudydemo

import java.sql.Date

/**
 * Created by zn-guest25 on 2024/7/24    9:58
 *
 * Move Field (移动字段)
 * Move Method (移动函数)
 * Extract Class (提取类：类功能比较多时，考虑分成多个类。组合使用 MoveField & MoveMethod 实现)
 * Inline Class (一个类没有什么工作，通过内联手法删除。是 Extract Class的反义词)
 * Hide Delegate (隐藏“委托关系”)
 * Remove Middle Man (Hide Delegate的反义词)
 * Introduce Foreign Method (无法直接修改源类时使用，Kotlin中的扩展函数)
 * Introduce Local Extension (通过子类 或者 包装类 去扩展源类)
 */
class Account {
    val type = AccountType()
    var daysOverdrawn = 7

    fun bankCharge(): Double {
        var result = 4.5
        //重构前
        if (daysOverdrawn > 0) result += overdraftCharge()
        //重构后
        if (daysOverdrawn > 0) result += type.overdraftCharge(daysOverdrawn)
        return result
    }

    //假如后面新增了几种账户，并且每一种都有自己的 算法
    //此时 overdraftCharge 需要搬移到 AccountType 类中
    private fun overdraftCharge(): Double {
        return if (type.isPremium) {
            var result = 10.0
            if (daysOverdrawn > 7) result += (daysOverdrawn - 7) * 0.85
            result
        } else {
            daysOverdrawn * 1.75
        }
    }

    //演示如何移动字段  重构之后要删除该字段
    var interestRate = 0.4

    fun useInterestRate() {
        interestRate
    }

    fun useInterestRate2() {
        interestRate
    }
}

class AccountType {

    var isPremium = false

    fun overdraftCharge(daysOverdrawn: Int): Double {
        return if (isPremium) {
            var result = 10.0
            if (daysOverdrawn > 7) result += (daysOverdrawn - 7) * 0.85
            result
        } else {
            daysOverdrawn * 1.75
        }
    }

    //演示如何移动字段
    var interestRate = 0.4

}

//演示 Extract Class
data class Person(val name: String, val officeAreaCode: String, val officeNumber: String)

//抽取电话号码相关字段 到 新类
data class TelephoneNumber(val areaCode: String, val number: String)
data class PersonAfter(val name: String, private val officeTelephone: TelephoneNumber) {
    fun getOfficeAreaCode() = officeTelephone.areaCode
    fun getOfficeNumber() = officeTelephone.number
}

//演示 hide Delegate
data class Worker(val name: String, val dep: Department?)
data class Department(val chargeCode: String, val manager: Worker)

data class WorkerAfter(val name: String, private val dep: Department?) {
    fun getManager(): Worker? = dep?.manager
}

//重构之后 获取经理只需调用 getManager()。隐藏了 Department 类。符合迪米特原则
fun test() {
    val andy = Worker("andy", Department("AutoTest", Worker("Boss", null)))
    andy.dep?.manager

    val andyAfter = WorkerAfter("andy", Department("AutoTest", Worker("Boss", null)))
    andyAfter.getManager()
}

//演示扩展函数
fun test2() {
    val date = Date(System.currentTimeMillis())
    date.nextDay()
}

fun Date.nextDay() = Date(year, month, day + 1)
