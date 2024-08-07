package com.example.refactorstudydemo

import android.util.Log
import java.util.Collections


/**
 * Created by zn-guest25 on 2024/7/24    17:24
 *
 * 重构数据结构
 * Replace Data Value with Object (把基本类型参数替换为对象参数)
 *
 * Change Value to Reference
 * Change Reference to Value
 *
 * 单向关联改为双向关联 (两个类相互访问)
 * 双向关联改为单向关联
 *
 * 用常量(val)代替魔法数
 *
 * 封装集合(array/list/set/map)
 *
 * 以类取代类型码
 * 以子类取代类型码（适用于因为类型 需运行不同分支代码的情况）
 *
 * 以字段取代子类
 */

data class Order(var customer: String)

//业务越来越复杂,String 类型不够用 Buyers 类表示买家
data class Buyers(var customerName: String)

data class OrderAfter(var customer: Buyers)

fun demo() {
//    val order = Order("Andy")
//    val customerName = order.customer
//    Log.d("abc", "customer name : $customerName")

    //重构后
    val order = OrderAfter(Buyers("Andy"))
    val customerName = order.customer.customerName
    Log.d("abc", "customer name : $customerName")

}

// 变成双向关联
data class BuyersAfter(var customerName: String) {
    private val orderSet = mutableSetOf<OrderAfter>()

    fun addOrder(order: OrderAfter) {
        orderSet.add(order)
    }

    fun removeOrder(order: OrderAfter) {
        orderSet.remove(order)
    }
}

//封装集合
data class Course(val name: String, val isAdvanced: Boolean = false)

data class Student(val courses: MutableSet<Course>)

data class StudentAfter(val name: String) {

    //其他类操作 courses 字段只能通过 addCourse() & removeCourse()
    private val courses: MutableSet<Course> = mutableSetOf()

    fun addCourse(arg: Course) {
        courses.add(arg)
    }

    fun removeCourse(arg: Course) {
        courses.remove(arg)
    }

    /**
     * 返回只读副本
     */
    fun getCourses(): Set<Course> {
        return Collections.unmodifiableSet(courses)
    }

    fun numberOfAdvCourse(): Int {
        return courses.count { it.isAdvanced }
    }
}

fun study() {
    val set = mutableSetOf<Course>()
    set.add(Course("Smalltalk Programming", false))
    set.add(Course("App Single Malts", true))
    val kent = Student(set)

    //操作集合
    val refact = Course("Refactoring", true)
    kent.courses.add(refact)
    kent.courses.remove(refact)

    val count = kent.courses.count { it.isAdvanced }

    //重构后
    val kentAfter = StudentAfter("Andy")
    kentAfter.addCourse(Course("Smalltalk Programming", false))
    kentAfter.addCourse(refact)
    kentAfter.removeCourse(refact)

    kentAfter.numberOfAdvCourse()
}

data class People(var bloodType: Int) {
    companion object {
        val O = 0
        val A = 1
        val B = 2
        val AB = 3
    }
}

//表示 雇员
class Employee(val type: Int) {
    companion object {
        val ENGINEER = 0 //表示工程师
        val SALESMAN = 1 //表示销售
        val MANAGER = 2 //表示经理
    }
}

abstract class EmployeeAfter {
    abstract fun getType(): Int

    companion object {
        val ENGINEER = 0 //表示工程师
        val SALESMAN = 1 //表示销售
        val MANAGER = 2 //表示经理

        fun create(type: Int): EmployeeAfter {
            return when (type) {
                ENGINEER -> Engineer()
                SALESMAN -> Salesman()
                MANAGER -> Manager()
                else -> throw IllegalArgumentException("未定义的员工类型")
            }
        }
    }
}

class Engineer : EmployeeAfter() {
    override fun getType(): Int {
        return ENGINEER
    }
}

class Salesman : EmployeeAfter() {
    override fun getType(): Int {
        return SALESMAN
    }
}

class Manager : EmployeeAfter() {
    override fun getType(): Int {
        return MANAGER
    }
}

//Replace Subclass with Fields
abstract class PersonT {
    abstract fun isMale(): Boolean
    abstract fun getCode(): Char
}

class Male : PersonT() {
    override fun isMale(): Boolean {
        return true
    }

    override fun getCode(): Char {
        return 'M'
    }

}

class Female : PersonT() {
    override fun isMale(): Boolean {
        return false
    }

    override fun getCode(): Char {
        return 'F'
    }

}

class PersonTAfter(val isMale: Boolean, val code: Char) {
    companion object {
        fun createMale() = PersonTAfter(true, 'M')
        fun createFemale() = PersonTAfter(false, 'F')
    }

}


