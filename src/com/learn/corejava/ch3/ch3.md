# Java基本程序设计结构

## 一个简单的Java应用程序
```java
public class FirstSample {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
```
需要注意以下三点：
- **Java区分大小写**
- **Java应用程序中的全部内容都必须放置在类中**
- **源代码的文件名必须与公共类（修饰符为public的类）的名字相同**

## 注释
Java有三种注释格式：

1. 单选注释：符号是：//
2. 块注释： 符号是： /* */ 可以跨多行
3. javadoc注释： 符号是： /** */ 可以跨多行，生成javadoc时，这样的注释会被生成标准的javaapi注释。

## 数据类型
### 整型
|  类型 | 存储需求 | 
| :---: | :---: |
| int | 4字节 |
| short | 2字节 |
| long | 8字节 |
| byte | 1字节 |
- 在Java中，整型的范围与运行java代码的机器无关
- Java没有任何无符号(unsigned)形式的int、long、short或byte类型

### 浮点型
| 类型 | 存储需求 |
|:---:|:--------:|
| float | 4字节 |
| double | 8字节 |

    float类型的数值有一个后缀 F 或 f（如3.14F）
    没有后缀或者后缀为D或d的浮点数为double类型    

用于表示溢出和出错情况的三个特殊的浮点数值：
- 正无穷大 —— Double.POSITIVE_INFINITY
- 负无穷大 —— Double.NEGATIVE_INFINITY
- NaN（不是一个数字） —— Double.NaN


    不能使用if (x == Double.NaN)// is never true
    所有“非数值”都是认为是不相同的。
    应该使用if (Double.isNaN(x))// check whether x is "not a number"

*浮点数值不适用于无法接受舍入误差的计算中。*
*如果在数值计算中不允许有任何舍入误差，就应该使用BigDecimal类*

### char类型
char类型的字面值常量要用单引号括起来。

#### Unicode和char类型
- 在Java中，char类型描述了UTF-16编码中的一个代码单元。
- Unicode转义序列会在解析代码之前得到处理
- **强力建议不要在程序中使用char类型，除非确实需要处理UTF-16代码单元。**

### boolean类型
boolean类型有两个值：false和true。

*整型值和布尔值值间不能相互转换*

## 变量
### 变量初始化
Java中，不区分变量的声明和定义

    在C和C++中是区分变量的声明和定义的
    例如：
    int i = 10; // 是一个定义
    extern int i; // 是一个声明

### 常量
- 使用关键字final指示常量。
- 使用static final指示类常量。

## 运算符
### 运算符优先级与结合性
|运算符|结合性|
|:---|:---:|
|[].()（方法调用）|从左向右|
|! ~ ++ -- +（正） -（负） ()（强制类型转换） new|从右向左|
|* / %|从左向右|
|+ -|从左向右|
|<< >> >>>|从左向右|
|< <= > >= instanceof|从左向右|
|== ！=|从左向右|
|&|从左向右|
|^|从左向右|
|&#124;|从左向右|
|&&|从左向右|
|&#124;&#124;|从左向右|
|?:|从右往左|
|= += -= *= /= %= &= &#124;= ^= <<= >>= >>>=|从右往左|

### 数值类型之间的转换
- 如果两个操作数中有一个是double类型，另一个操作数就会转换为double类型。
- 否则，如果其中一个操作数是float类型，另一个操作数就会转换为float类型。
- 否则，如果其中一个操作数是long类型，另一个操作数将会转换为long类型。
- 否则，两个操作数都将被转换为int类型。

## 字符串
Java标准库提供了String类。
- String对象是不可变的（优点：编译器可以让字符串共享）
- 使用 s.equals(t)判断 s 与 t 是否相等。
- 调用方法前一定要检查 str 不为 null。
- 如果要经常构造字符串，应该使用StringBuilder类。

