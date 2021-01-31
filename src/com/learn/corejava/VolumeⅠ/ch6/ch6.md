# 接口、lambda 表达式与内部类

## 接口

### 接口概念

> 在 Java 程序设计语言中，接口不是类，而是对类的一组需求描述,
> 这些类要遵从接口描述的统一格式进行定义。

- 接口中的所有方法自动地属于 public。
- 提供实例域和方法实现的任务应该由实现接口的那个类来完成。
  - 接口中可以定义常量，但接口就不能含有实例域。
    - 接口中的域将被自动设为 public static final。
  - 在 Java SE 8 之前，不能在接口中实现方法。
    - Java SE 8 中。允许在接口中添加静态方法。

为了然类实现一个接口，通常需要下面两个步骤：

1. 将类声明为实现给定的接口。
2. 对接口中的所有方法进行定义。

### 接口的特性

> 接口不是类，尤其不能使用 new 运算符实例化一个接口。
>
> 然而，尽管不能构造接口的对象，却能声明接口的变量。
> 接口变量必须引用实现了接口的类对象。
>
> 尽管每个类只能够拥有一个超类，但却可以实现多个接口。

### 默认方法

> 可以为接口方法提供一个默认实现。必须用 default 修饰符标记这样一个方法。

```java
public interface Comparable<T> {
    default int compareTo(T other) { return 0; }
        // By default, all elements are the same
}
```

> 默认方法可以调用任何其他方法

```java
public interface Collection {
    int size(); // An abstract method
    default boolean isEmpty() {
        return size() == 0;
    }
}
```

### 解决默认方法冲突

> 如果先在一个接口中将一个方法定义为默认方法，然后又在超类或另一个接口中定义了同样的方法，
> 就会产生默认方法冲突。

Java 对相应规则如下：

1. 超类优先。如果超类提供了一个具体方法，同名而且有相同参数类型的默认方法会被忽略。
2. 接口冲突。如果一个接口提供了一个默认方法，另一个接口提供了一个同名而且参数类型相同的方法，
   则必须覆盖这个方法。

---

## lambda 表达式

### lambda 表达式的语法

> Java 中的一种 lambda 表达式形式：参数，箭头(→)以及一个表达式。
> 如果代码要完成的计算无法放在一个表达式中，就可以像写方法一样，把这些代码放在 {} 中，并包含显示的 return 语句。

```
(String first, String second) -> {
    if (first.length() < second.length()) return -1;
    else if (first.length() > second.length()) return 1;
    else rerutn 0;
}
```

> 即视 lambda 表达式没有参数，仍然要提供空括号，像无参数方法一样：

```
() -> { for (int i = 100; i >= 0; i--) System.out.println(i); }
```

> 如果可以推导出一个 lambda 表达式的参数类型，则可以忽略其类型。

```
Comparator<String> comp = (first, second) -> first.length() - second.length();
```

> 在这里，编译器可以推导出 first 和 second 必然是字符串，因为这个 lambda
> 表达式将赋值给一个字符串比较器。

> 如果方法只有一个参数，而且这个参数的类型可以推导得出，那么可以省略小括号

```
ActionListener listener = event -> System.out.println(".....");
```

### 函数式接口

> 对于只有一个抽象方法的接口，需要这种接口对象时，就可以提供一个 lambda 表达式。
> 这种接口称为函数式接口(function interface)。

### 变量作用域

lambda 表达式有 3 个部分：

1. 一个代码块;
2. 参数;
3. 自由变量的值，这是指非参数而且不在代码中定义的变量。

例子：

```
public static void repeatMessage(String text, int delay) {
    ActionListener listener = event -> {
        System.out.println(text);
        Toolkit.getDefaultToolkit().beep();
    };
    new Timer(delay, listener).start();
}
```

> 在这个例子中，这个 lambda 表达式有一个自由变量 text。

来看这样一个调用：

```
repeatMessage("Hello", 1000); // Print Hello every 1,000 milliseconds
```

> 表示 lambda 表达式的数据结构必须存储自由变量的值，在这里就是字符串 "Hello"。
> 我们说它被 lambda 表达式捕获(captured)。

> 可以看到，lambda 表达式可以捕获外围作用域中变量的值。
> 在 Java 中，要确保所捕获的值是明确定义的，这里有一个重要的限制。
> 在 lambda 表达式中，只能引用值不会改变的变量。

例如，下面这样的做法是不合法的：

```
public static void countDown(int start, int delay) {
    ActionListener listener = event -> {
        start--; // Error: Can't mutate captured variable
        System.out.println(start)
    };
    new Timer(delay, listener).start();
}
```

> 另外，如果在 lambda 表达式中引用变量，而这个变量可能在外部改变，这也是不合法的。
> lambda 表达式中捕获的变量必须实际上是最终变量(effectively final)。
> 实际上的最终变量是指，这个变量初始化之后就不会再为它赋新值。

例如，下面这样的做法是不合法的：

```
public static void repeat(String text, int count) {
    for (int i = 0; i < count; i++) {
        ActionListener listener = event -> {
            System.out.println(i + ": " + text); // Error: Can't refer to changing i
        }
    }
}
```

> 在 lambda 表达式中声明与一个局部变量同名的参数与局部变量是不合法的。
> 在方法中，不能有两个同名的局部变量，因此，lambda 表达式中同样也不能有同名的局部变量。

例如，下面这样的做法是不合法的：

```
Path first = Path.get("/usr/bin");
Comparator<String> comp = (first, second) -> first.length() - second.length();
// Error: Variable first already defined
```

---

## 内部类

> 内部类(inner class)是定义在一个类中的另一个类。

需要使用内部类的原因有以下三点：

- 内部类方法可以访问该类定义所在的作用域中的数据，包括私有数据。
- 内部类可以对同一个包中的其他类隐藏起来。
- 当想要定义一个回调函数且不想编写大量代码时，使用匿名(anonymous)内部类比较便捷。

### 内部类的特殊语法规则

- 可以使用 OuterClass.InnerClass 的语法引用内部类，在其他类中实例化内部类需要使用
  OuterClass.new InnerClass();来调用构造器方法。
- 内部类中声明的所有静态域都必须是 final 的。
- 内部类不能有 static 方法。

### 局部内部类

```
public void start(int interval, boolean beep) {
    class TimePrinter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + new Date());
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }
    ActionListener listener = new TimePrinter();
    new Timer(interval, listener).start();
}
```

> 局部类不能用 public 或 private 访问说明符进行声明。它的作用域被限定在声明这个局部类的块中。
>
> 局部类有一个优势，即对外部世界可以完全地隐藏起来。
> 除了 start 方法，没有任何方法知道 TimePrinter 类的存在。
>
> 除此之外，局部类还有一个优点。它们不仅能访问它们的外部类，还可以访问局部变量。
> 不过，那些局部变量必须事实上为 final。

### 匿名内部类

```
public void start(int interval, boolean beep) {
    ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + new Date());
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }
    new Timer(interval, listener).start();
}
```

> 它的含义是：创建一个实现 ActionListener 接口的类的新对象，需要实现的方法
> actionPerformed 定义在括号 {} 内。

通常的语法格式为：

```
new SuperType(construction parameters) {
    inner class methods and data
}
```

> 其中，SuperType 可以是 ActionListener 这样的接口，于是内部类就要实现这个接口。
> SuperType 也可以是一个类，于是内部类就要扩展它。

> 由于构造器名字必须与类名相同，而匿名类没有类名，所以，匿名类不能有构造器。
> 取而代之的是，将构造器参数传递给超类(SuperType)构造器。
