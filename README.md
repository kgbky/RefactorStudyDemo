# Refactor Study Demo

学习代码重构

## 重构定义：

    不改变软件的行为。修改代码。

## 重构目的：

    代码易维护、易扩展、更健壮、更易理解

## 重构前提：

    建立可靠的测试机制

## 重构准则：

    小步修改，频繁测试

## 重构步骤：

    1、添加新功能时，不修改既有代码，同时构建测试系统。
    2、再重构代码，此时不再修改功能，只改进代码。再用测试验证重构是否正确
    3、重构过程能加深对系统的理解，生出更高层的抽象。这能增强代码的健壮性。
    4、甚至能够提高开发速度，因为代码易扩展。

## 重构时机：

不为重构而重构！合适的时机：想做别的事情，重构能帮我们做的更好

    1、添加功能时重构
         重构帮助理解已有代码。重构之后，添加新特性会变得简单 
    2、修复Bug时重构
    3、代码无法实现基本的功能时，直接重写，放弃重构。
    4、died line 也要放弃重构

## 好的程序

    *容易阅读、所有逻辑只存在一处、易添加新功能、逻辑尽量简单

## 解决的问题：

    1、代码随着时间腐败！开发前的设计并不能做到完美，重构可以防止代码变成屎山。
    2、开发前的设计 和 重构、都很重要，相辅相成。
    3、设计模式是目标，重构是到达之路

## 构建测试体系

    1、单元测试技巧：
        *针对可能出错的部分，编写单元测试
        *寻找边界条件
        *花费合理的时间抓出大多数bug

    2、测试能够大大缩短debug的时间。且更能保证代码质量

    3、测试用例需要频繁的执行。编写测试代码时，可以先让他们失败。

## 代码中的坏味道

    1、重复代码或逻辑 -> 合并

    2、过大函数 -> 拆分成小函数。在每个需要注释的地方，可考虑抽成一个独立函数，并以其用途命名

    3、过大的类 -> 拆分出子类或父类

    4、过多参数 -> 用对象封装所有参数

    5、冗余的代码 或 无用的代码 -> 冗余消除(例如合并功能相近的函数)、无用删除

    6、多余的注释 -> 有时候注释在提醒你代码很糟糕，尝试用重构消灭注释
    
    7、发散式变化(一个类受多种变化的影响) -> 把需要修改的地方统一到一处(一个类或方法内)，涉及到拆分类

    8、霰弹式变化(一个变化引起多个类的修改) -> 把需要修改的地方统一到一处(一个类或方法内)

    9、过度依赖(函数过度依赖其他对象的数据) -> 把这个函数移动到他该去的地方

    10、过度设计(预测未来功能) -> 过度设计提高了代码的复杂度，而且没有实用价值

    11、过度委托 -> 消除中间层

    12、数据类重构 -> kotlin 中使用 DataClass

    13、if语句重构 -> 判断逻辑复杂时，可引入解释性变量

    14、方法内变量承担了多个职责 -> 分类临时变量，每个变量要职责单一

    15、重构复杂长函数 -> 用函数对象替换函数，在函数对象类内可进行各种新重构

    16、方法或字段名称，词不达意 -> ReName(命名技巧：先考虑注释要怎么写，再把注释变成名称)

## 心得体会

    1、小型函数优美动人。大大提高了代码的可读性

    2、重构在平时的开发中穿插进行，为重构去要求工期，容易被骂

    3、集合的定义 及其 处理应该在一个类内部，对外提供元素的 getter 和 setter，和 获取集合只读副本的方法
    
    4、修改字段 和 查询字段 的方法要分开设计

    5、Val的优先级高于Var

    6、代码的可理解性是我们虔诚追求的目标

## 重构继承体系（12章）

    1、Tease Apart Inheritance (继承体系承担>1的责任时,需要抽出多个继承体系，以委托方式调用)

    2、Extract Hierarchy (类中有太多的条件表达式时，用子类替代条件表达式)
    