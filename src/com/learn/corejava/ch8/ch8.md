# 泛型程序设计

## 定义简单泛型类
>一个泛型类(generic class)就是具有一个或多个类型变量的类。
```java
public class Pair<T> {
    private T first;
    private T second;
    
    public Pair() {
        first = null;
        second = null;
    }
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(T second) { this.second = second; }
}
```
> Pair 类引入了一个类型变量 T，用尖括号（<>）括起来，并放在类名的后面。
> 泛型类可以有多个类型变量。
```
public class Pair<T, U> {...} 
```
> 类定义中的类型变量可以指定方法的返回类型和局部变量的类型。

用具体的类型替代泛型变量就可以实例化泛型类型 `Pari<String>`

### 泛型方法
> 可以定义一个带有类型参数的简单方法
```java
class ArrayAlg {
    @SafeVarargs 
    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
```
> 这个方法是在普通类中定义的，而不是在泛型类中定义的。然而，这个一个泛型方法。
> 类型变量放在修饰符的后面，返回类型的前面。

> 泛型方法可以定义在普通类中，也可以定义在泛型类中。

当调用一个泛型方法是，在方法名前的尖括号中放入具体的类型：
```
String middle = ArrayAlg.<String>getMiddle("w", "csy", "Public")；
```
在大多数情况下，方法调用中可以省略 <String> 这样的类型参数，只要编译器有足够的信息能够推断出所调用的方法。

## 类型变量的限定
有时，类或方法需要对类型变量加以约束。
如：
```java
class ArrayAlg {
    public static <T> T min(T[] a) {
        if (a == null || a.length == 0) { return null; }
        T smallest = a[0];
        for(T t: a){
            if (smallest.compareTo(t) > 0) { smallest = t; }
        }
        return smallest;
    }   
}
```
这里有一个问题，变量 smallest 类型为 T，怎样才能确信 T 所属的类有 compareTo 方法呢？

> 为了解决这个问题，我们可以将 T 限制为实现了 Comparable 接口的类。
> 可以通过对类型变量 T 设置限定(bound)实现这一点。
```
puclic static <T extends Comparable> T min(T[] a) ...
```
现在，泛型的 min 方法只能被实现了 Comparable 接口的类的数组调用。
> 一个类型变量或通配符可以有多个限定，
```
T extends Comparable & Serializable
```
限定类型用 “&” 分隔，而逗号用来分隔类型变量。
> 在 Java 中，可以根据需要拥有多个接口超类型，但限定中至多有一个类，
> 如果用一个类作为限定，它必须是限定列表中的一个。

## 泛型代码和虚拟机
### 类型擦除
> 无论何时定义一个泛型类型，都自动提供一个相应的原始类型(raw type)。
> 原始类型的名字就是删去类型参数后的泛型类型名。
> 擦除(erased)类型变量，用第一个限定的类型变量来替换，如果没有给定限定就用 Object 替换。

例如，Pair<T> 的原始类型如下所示：
```java
public class Pair {
    private Object first;
    private Object second;
    
    public Pair() {
        first = null;
        second = null;
    }
    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }
    
    public Object getFirst() { return first; }
    public Object getSecond() { return second; }

    public void setFirst(Object first) { this.first = first; }
    public void setSecond(Object second) { this.second = second; }
}
```

### 翻译泛型表达式
> 当程序调用泛型方法时，如果擦除返回类型，编译器会插入强制类型转换。

例如：
```
Pair<Employee> buddies = . . .;
Employee buddy = buddies.getFirst();
```
> 擦除 getFirst 的返回类型后将会返回 Object 类型。编译器自动插入 Employee 的强制转换。

也就是说，编译器把这个方法调用翻译为两条虚拟机指令：
- 对原始方法 Pair.getFirst 的调用。
- 将返回的 Object 类型强制转换为 Employee 类型。

### 翻译泛型方法
类型擦除也会出现在泛型方法中
```
public static <T extends Comparable> T min(T[] a)
```
擦出类型之后，只留下限定类型 Comparable。
```
public static Comparable min(Comparable[] a)
```

有关 Java 泛型转换的事实：
- 虚拟机中没有泛型，只有普通的类和方法。
- 所有的类型参数都用它们的限定类型替换。
- 桥方法被合成来保持多态。
- 为保持类型安全性，必要时插入强制类型转换。

## 约束和局限性
1. 不能用基本类型实例化类型参数
2. 运行时类型检查只适用于原始类型
3. 不能创建参数化类型的数组
4. Varargs 警告
5. 不能实例化类型变量
6. 不能构造泛型数组
7. 泛型类的静态上下文中类型变量无效
8. 不能抛出或捕获泛型类的实例
9. 可以消除对受查异常的检查
10. 注意擦出后的冲突

## 泛型类型的继承规则
![泛型的继承](%20resource/pair.png)

## 通配符类型
主要用作方法的参数。

### 通配符概念
> 通配符中，允许类型参数变化。

假设要编写一个打印雇员对的方法：
```
public static void printBuddies(Pair<Employee> p)
{
 Employee first = p.getFirst();
 Employee second = p.getSecond();
 System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
}
```
由于泛型类型的继承规则，我们不能将 Pair<Manager> 传递给这个方法。
解决这个问题的方法正是使用通配符：
```
public static void printBuddies(Pair<? extends Employee> p)
```
类型 Pair<Manager> 时 Pair<? extends Employee> 的子类型
![使用通配符的子类型关系](%20resource/wildcard.png)


### 通配符的超限定
```j
? super Manager
```
> 带有超类型限定的通配符可以向泛型对象写入，
> 带有子类型限定的通配符可以向泛型对象读取。

















