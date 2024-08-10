package com.example.refactorstudydemo

import java.util.Collections

/**
 * Created by zn-guest25 on 2024/8/1    13:47
 *
 * 重构 继承关系 (11章)
 *
 * Pull Up Field (字段上移)
 * Pull Up Method (函数上移)
 *
 * Pull Down Field (字段下移)
 * Push Down Method (函数下移)
 *
 * Extract Subclass (提炼子类)
 * Extract Superclass (提炼父类)
 * Extract Interface (提炼接口)
 *
 * Collapse Hierarchy (折叠继承关系：删除父保留子 or 删除子保留父) //377
 */

//Extract Subclass (提炼子类)
class WorkerCar(val rate: Int)

open class JobItem(
    val unitPrice: Int, val quantity: Int, val isLabor: Boolean, val employee: WorkerCar
) {
    fun getTotalPrice() = unitPrice * quantity
}

class LaborItem(
    val unitPriceSub: Int, val quantitySub: Int, val isLaborSub: Boolean, val employeeSub: WorkerCar
) : JobItem(unitPriceSub, quantitySub, isLaborSub, employeeSub) {

}

//重构后
open class JobItemAfter(
    val unitPrice: Int,
    val quantity: Int,
) {
    fun getTotalPrice() = unitPrice * quantity

    open fun isLabor() = false
}

class LaborItemAfter(
    val unitPriceSub: Int, val quantitySub: Int, val employeeSub: WorkerCar
) : JobItemAfter(unitPriceSub, quantitySub) {

    override fun isLabor() = true
}

//Extract Superclass (提炼父类)
//可消灭重复代码
class EmployeeS(val name: String, val id: Int, val annualCost: Int)//表示员工

class DepartmentS(val name: String) {
    //表示部门
    private val memberList = mutableListOf<EmployeeS>()

    fun addMember(arg: EmployeeS) {
        memberList.add(arg)
    }

    fun removeMember(arg: EmployeeS) {
        memberList.remove(arg)
    }

    fun getHeadCount() = memberList.size

    fun getMemberList(): List<EmployeeS> = Collections.unmodifiableList(memberList)

    fun getTotalAnnualCost(): Int {
        var result = 0
        memberList.forEach {
            result += it.annualCost
        }
        return result
    }

}

//重构后
abstract class Party {
    abstract fun getAnnualCost(): Int
}

class EmployeeParty(val name: String, val id: Int, private val annualCost: Int) : Party() {
    //表示员工
    override fun getAnnualCost(): Int {
        return annualCost
    }
}

class DepartmentParty(val name: String) : Party() {
    //表示部门
    private val memberList = mutableListOf<EmployeeS>()

    fun addMember(arg: EmployeeS) {
        memberList.add(arg)
    }

    fun removeMember(arg: EmployeeS) {
        memberList.remove(arg)
    }

    fun getHeadCount() = memberList.size

    fun getMemberList(): List<EmployeeS> = Collections.unmodifiableList(memberList)

    override fun getAnnualCost(): Int {
        var result = 0
        memberList.forEach {
            result += it.annualCost
        }
        return result
    }

}

//接口
interface Named {
    val name: String
}

interface PersonM : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Child(
    // 不必实现“name”
    override val firstName: String,
    override val lastName: String,
) : PersonM