# 继承（inheritance）
## 类、超类和子类
### 定义子类
使用关键字 extends 表示继承
```java
public class Manager extends Employee {
    // 添加方法和域
}
```
>关键字 extends 表明正在构造的新类派生于一个已存在的类。
>已存在的类称为超类(superclass)、基类(base class)、或父类(parent class)；
>新类称为子类(subclass)、派生类(derived class)或孩子类(child class)。

在 Manager 类中，增加一个用于存储奖金信息的域，以及设置这个域的新方法。
```java
public class Manager extends Employee {
    private double bonus;
    
    public void setBonus(double bonus) {
        this.bonus = bonus;    
    }   
}
```
>由于 setBonus 方法不是在 Employee 类中定义的，所以属于 Employee 
类的对象不能使用它。
>然而，尽管 Manager 类中没有显示地定义 getName 等方法，但属于 Manager
>的对对象却可以使用他们，这是因为 Manager 类自动继承了超类 Employee
>的这些方法。

### 覆盖方法
>超类中的有些方法对子类 Manager 并不一定适合。为此，需要提供一个新的
>方法来覆盖(override)超类中的这个方法。
```java
public class Manager extends Employee {
    private double bonus;

    @Override
    public double getSalary() {
        // do something
    }  
}
```
>我们需要返回 salary 和 bonus 的和，然而，Manager 的 getSalary 方法
>不能直接地访问超类的私有域。只能通过父类暴露出来的公共接口访问。
>
>为此，可以使用特定的关键字 super 解决这个问题。
```java
 public class Manager extends Employee {
     private double bonus;
 
     @Override
     public double getSalary() {
         return super.getSalary() + bonus;
     }  
 }
 ```

### 子类构造器
```java
 public class Manager extends Employee {
     public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }
 }
 ```
>super 调用父类的构造函数，必须放在子类构造器的第一条语句
>
>**也就是说，super(param) 和 this(param) 不能同时出现**

### 多态
>Java 程序设计语言中，对象变量是多态的。一个 Employee
>变量既可以引用一个 Employee 类对象，也可以引用一个 Employee
>类的任何一个子类的对象（例如 Manager 等）

当一个 super 变量 var 引用一个 sub 对象时
- 如果 var 调用的方法只存在在 sub 中，则出错
- 如果 var 调用的方法被 sub 覆盖，则调用 sub 中 override 的方法
- 负责调用 super 中的方法

### 阻止继承：final 类和方法
>不允许拓展的类称为 final 类。
>在定义类的时候使用 final 修饰符就表明这个类是 final 类。
```java
public final class Executive extends Manager {
    // write something
}
```
>类中特定的方法也可以被声明为 final。如果这样做，子类就不能覆盖这个方法。

### 强制类型转换
- 只能在继承层次内进行类型转换。
- 在将超类转换为子类之前，应该使用 instanceof 进行检查

### 抽象类
>使用 abstract 关键字声明一个抽象方法。这样就不需要实现这个方法
>包含一个或多个抽象方法的类本身必须被声明为抽象的。
```java
public abstract class Person {
    public abstract String getDescription();
}
```
>抽象类不能被实例化
>
>抽象方法充当着占位角色，它们的具体实现在子类中。扩展抽象类可以有两种选择。
>一种是在抽象类中定义部分抽象类方法或不定义抽象类方法，这样就必须将子类也标记为抽象类；
>另一种是定义全部抽象方法，这样一来，子类就不是抽象的了。

### 受保护访问
- 仅对本类可见——private
- 对所有类可见——public
- 对本包和所有子类可见——protected
- 对本包可见——默认，不需要修饰符
-----------------------------------------------
## Object：所有类的超类
### equals 方法
>Object类中的 equals 方法用于检测一个对象是否等于另外一个对象。
>在 Object 类中，这个方法将判断两个对象是否具有相同的引用。

**注**：由于 Java 中对象之间用 == 判断是判断两个对象的地址是否相等，所以不能用来判断对象之间的相等性。
对象之间判断是否相等必须使用 equals 方法。

### 相等测试与继承
java语言规范要求 equals 方法具有下面的特征：
1. 自反性：对于任何非空引用 x，x.equals(x)应该返回 true。
2. 对称性：对于任何引用 x 和 y，当且仅当 y.equals(s)返回 true，
    x.equals(y) 也应该放回 true。
3. 传递性：对于任何引用 x、y 和 z，如果x.equals(y)返回true，
    y.equals(z)返回 true，则 x.equals(z)也应该返回 true。
4. 一致性：如果 x 和 y 引用的对象没有发生变化，反复调用 x.equals(y) 
    应该返回相同的结果。
5. 对于任意非空引用 x，x.equals(null)应该返回 false。

关于编写 equals 方法的建议：
1. 显示参数命名为 otherObject，稍后需要将它转换成另一个叫做 other 的变量
2. 检测 this 与 otherObject 是否引用同一个对象
 `if (this == otherObject) return true;`
3. 检测 otherObject 是否为 null，如果为 null，返回 false。
`if (otherObject == null) return false;`
4. 比较 this 与 otherObject 是否属于同一个类。
    - 如果 equals 的语义在每个子类中有所改变，就使用 getClass 检测：
    `if (getClass() != otherObject.getClass()） return false;`
    - 如果所有的子类都拥有统一的语义，就使用 instanceof 检测：
    `if (!(otherObject instanceof ClassName)) return false;`
5. 将 otherObject 转换为相应的类类型变量:
`ClassName other = (ClassName) otherObject;`
6. 现在开始对所有需要比较的域进行比较。使用 == 比较基本类型域，使用
    Object.equals()比较对象域。
    
### hashCode 方法
>散列码(hash code)是由对象导出的一个整型值。
>如果重新定义 equals 方法，就必须重新定义 hashCode 方法.
>如果 x.equals(y)返回 true，那么 x.hashCode() 就必须与 y.hashCode() 具有相同的值。

## 对象包装器与自动装箱
>所有的基本类型都有一个与之对应的类。例如，Integer 类对应基本类型 int。
>通常，这些类称为包装器(wrapper)。
>
>对象包装器类是不可变的，即一旦构造了包装器，就不允许更改包装器在其中的值。
>同时，对象包装器类还是 final，因此不能定义它们的子类。

我们可以申请一个 Integer 对象的数组列表
`ArrayList<Integer> list = new ArrayList<>();`

可以使用   `list.add(1);`   向 list 中添加元素

它将自动地变换成    `list.add(Integer.valueOf(1));`

这种变换被称为*自动装箱(autoboxing)*

相反地，当将一个 Integer 对象赋给一个 int 值时，将会自动地拆箱。

如 `int n = list.get(i);`

翻译成 `int n = list.get(i).intValue();`

>装箱和拆箱时编译器认可的，而不是虚拟机。

## 枚举类
>在比较两个枚举类型的值时，永远不需要调用 equals，而直接使用 “==” 就可以了。

## 反射
能够分析类能力的程序称为反射(reflective)。反射机制可以用来:
- 在运行时分析类的能力。
- 在运行时查看对象，例如，编写一个 toString 方法供所有类使用。
- 实现通用的数组操作代码。
- 利用 Method 对象，这个对象很想 C++ 中的函数指针。

### Class 类
>在程序运行期间，Java运行时系统始终为所有的对象维护一个被称为*运行时*的类型标志(runtime type identification)。
>这个信息跟踪着每个对象所属的类。虚拟机利用运行时类型信息选择相应的方法执行。

Class 类是保存这些信息的类。
- 一个 Class 对象表示一个特定类的属性。
- 使用 getClass() 方法将会返回一个 Class 类型的实例。
- 使用 Class 对象的 getName() 方法将返回类的名字。
- 调用静态方法 Class.forName(className) 将获得类名对应的 Class 对象。
    其中，className 必须是类名或接口名才能够执行，否则会抛出一个 checked exception
- 如果 T 是任意的 Java 类型(或 void 关键字)，T.class 将代表匹配的类对象。
- 使用 Class 对象的 newInstance() 方法可以动态的创建一个类的实例。

### 利用反射分析类的能力
>在 java.lang.reflect 包中有三个类 Field、Method 和 Constructor 分别用于描述类的域、方法和构造器。

## 继承的设计技巧
1. 将公共操作和域放在超类
2. 不要使用受保护的域
3. 使用继承实现 “ is-a ” 关系
4. 除非所有继承的方法都有意义，否则不要使用继承
5. 在覆盖方法时，不要改变预期的行为
6. 使用多态，而非类型信息
7. 不要过多地使用反射