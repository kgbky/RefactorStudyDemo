package com.example.refactorstudydemo

import com.example.refactorstudydemo.extractMethod.Customer
import com.example.refactorstudydemo.extractMethod.Movie
import com.example.refactorstudydemo.extractMethod.Movie.CHILDRENS
import com.example.refactorstudydemo.extractMethod.Movie.NEW_RELEASE
import com.example.refactorstudydemo.extractMethod.Movie.REGULAR
import com.example.refactorstudydemo.extractMethod.Movie.SEX
import com.example.refactorstudydemo.extractMethod.Rental
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        var result = testRefactorAndy()
        assertEquals(result, true)
        result = testRefactorLucy()
        assertEquals(result, true)
        result = testRefactorAndyWithSex()
        assertEquals(result, true)
    }

    private fun testRefactorAndy(): Boolean {
        val customer = Customer("Andy")
        customer.addRental(Rental(Movie("Move1", NEW_RELEASE), 7))
        customer.addRental(Rental(Movie("Move2", CHILDRENS), 4))
        customer.addRental(Rental(Movie("Move3", REGULAR), 3))
        val result = customer.statement()
        val rightResult = """Rental Record for Andy
	Move1	21.0
	Move2	3.0
	Move3	3.5
Amount owed is 27.5
You earned 4 frequent renter points"""
        return rightResult == result
    }

    private fun testRefactorLucy(): Boolean {
        val customer = Customer("Lucy")
        customer.addRental(Rental(Movie("Move1", NEW_RELEASE), 1))
        customer.addRental(Rental(Movie("Move2", CHILDRENS), 1))
        customer.addRental(Rental(Movie("Move3", REGULAR), 1))
        val result = customer.statement()
        val rightResult = """Rental Record for Lucy
	Move1	3.0
	Move2	1.5
	Move3	2.0
Amount owed is 6.5
You earned 3 frequent renter points"""
        return rightResult == result
    }

    private fun testRefactorAndyWithSex(): Boolean {
        val customer = Customer("Andy")
        customer.addRental(Rental(Movie("Move1", NEW_RELEASE), 7))
        customer.addRental(Rental(Movie("Move2", CHILDRENS), 4))
        customer.addRental(Rental(Movie("Move3", REGULAR), 3))
        customer.addRental(Rental(Movie("MoveSex", SEX), 2))
        val result = customer.statement()
        val rightResult = """Rental Record for Andy
	Move1	21.0
	Move2	3.0
	Move3	3.5
	MoveSex	4.0
Amount owed is 31.5
You earned 7 frequent renter points"""
        return rightResult == result
    }

    /**
     * 重构定义：不改变软件的行为。修改代码。
     *
     * 重构目的：代码易维护、易扩展、更健壮
     *
     * 重构前提：建立可靠的测试机制
     *
     * 重构准则：小步修改，频繁测试
     *
     * 重构步骤：
     * 1、添加新功能时，不修改既有代码，同时构建测试系统。
     * 2、再重构代码，此时不再修改功能，只改进代码。再用测试验证重构是否正确
     * 3、重构过程能加深对系统的理解，生出更高层的抽象。这能增强代码的健壮性。
     * 4、甚至能够提高开发速度，因为代码易扩展。
     *
     * 何时重构：
     *  不为重构而重构！合适的时机：想做别的事情，重构能帮我们做的更好
     *  1、添加功能时重构
     *      重构帮助理解已有代码。重构之后，添加新特性会变得简单
     *  2、修复Bug时重构
     *
     * 好的程序
     *     容易阅读、所有逻辑只存在一处(代码里)、易添加新功能、逻辑尽量简单
     *
     * 怎么说
     *     不告诉产品经理，专业的事，专业的人做
     *
     * 解决的问题：
     *      代码随着时间腐败！事前设计并不能做到完美，重构可以防止代码变成屎山。
     *      设计 和 重构、都很重要，相辅相成
     *
     * 代码无法实现基本的功能时，直接重写，放弃重构。die line 也要放弃重构
     *
     * 设计模式是目标，重构是到达之路
     */

    /**
     * 重构时机：
     * 重复代码 -> 合并
     * 过长函数 -> 拆分成小函数。每个需要注释的地方，都可以抽成一个独立函数，并以其用途命名
     * 过大的类 -> 拆分出子类或父类
     * 过多参数 -> 用对象封装所有参数
     * 冗余的代码 或 无用的代码 -> 冗余消除、无用删除
     * 多余的注释 -> 有时候注释在提醒你代码很糟糕，尝试用重构消灭注释
     * 发散式变化(一个类受多种变化的影响) -> 把需要修改的地方统一到一处(一个类或方法内)，涉及到拆分类
     * 霰弹式变化(一中变化引起多个类的修改) -> 把需要修改的地方统一到一处(一个类或方法内)
     * 过度依赖(函数过度依赖其他对象的数据) -> 把这个函数移动到他该去的地方
     * ??两个类中相同的字段，函数中相同的参数 -> 把字段提炼到另一个类。(通过模拟删除字段，寻找关联字段)
     * switch语句 -> 使用多态替换
     * 平行继承关系 -> 通过引用消除平行继承
     * 过度设计(预测未来功能) -> 过度设计提高了代码的复杂度，而且没有实用价值
     * 过度委托 -> 消除中间层
     * 合并功能相近的函数
     * 修改依赖库 -> 绝大多数时候，库并不完美匹配我们的需求
     * 数据类重构 ->
     */

    /**
     * 构建测试体系
     * 测试体现能够大大缩短debug的时间。且更能保证代码质量
     * 先写测试代码 -》 再开发
     * 测试用例需要频繁的执行。编写测试代码时，可以先让他们失败。
     *
     *  用单元测试来复现线上bug
     *  针对类中可能出错的部分，编写单元测试
     *  编写单元测试技巧：寻找边界条件
     *  花费合理的时间抓出大多数bug
     */
}