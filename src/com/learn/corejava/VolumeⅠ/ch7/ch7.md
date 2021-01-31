# 异常、断言和日志

## 处理错误

如果由于出现错误而使得某些操作没有完成，程序应该：

- 返回到一种安全状态，并能够让用户执行一些其他的命令；或者
- 允许用户保存所有操作的结果，并以妥善的方式终止程序。

程序中可能出现的错误和问题：

1. 用户输入错误
2. 设备错误
3. 物理限制（磁盘满了，可用存储空间已用完等）
4. 代码错误

### 异常分类

![Throwable](resource/Throwable.png "Throwable")
所有异常都是由 Throwable 继承而来，但在下一层立即分解为两个分支：Error 和 Exception

> Error 类层次结构描述了 Java 运行时系统的内部错误和资源耗尽错误。

> 在设计 Java 程序时，需要关注 Exception 层次结构。
> 这个层次结构有分解为两个分支：
> 一个分支派生于 RuntimeException；
> 另一个分支包含其他异常。
>
> 划分两个分支的规则是：
> 由程序错误导致的异常属于 RuntimeException；
> 而程序本身没有问题，但由于像 I/O 错误这类问题导致的异常属于其他异常。

派生于 RuntimeException 的异常包含下面几种情况：

- 错误的类型转换。
- 数组访问越界。
- 访问 null 指针

不是派生于 RuntimeException 的异常包括：

- 试图在文件尾部后面读取数据。
- 试图打开一个不存在的文件。
- 试图根据给定的字符串查找 Class 对象，而这个字符串表示的类并不存在。

> Java 语言规范将派生于 Error 类或 RuntimeException 类的所有异常称为非受查(unchecked)异常，
> 所有其他的异常称为受查(checked)异常。

### 声明受查异常

`public FileInputStream(String name) throws FileNotFoundException`

遇到下面 4 种情况时应该抛出异常：

- 调用一个抛出受查异常的方法，例如，FileInputStream 构造器。
- 程序运行过程中发现错误，并且利用 throw 语句抛出一个受查异常。
- 程序出现错误。
- Java 虚拟机和运行时库出现的内部错误。

> 对于那些可能被他人使用的 Java 方法，应该根据异常规范，在方法的首部声明这个方法可能抛出的异常。

> 从 Error 继承的错误，任何程序代码都具有抛出那些异常的潜能，而我们对其没有任何控制能力。

> 而 RuntimeException 完全在我们的控制之下，我们应该将更多的时间花费在修正程序中的错误上，
> 而不是花费在说明这些错误发生的可能性上。

### 如何抛出错误

`throw new EOFException();`

1. 找到一个合适的异常类
2. 创建这个类的一个对象
3. 使用 throw 语句将对象抛出

### 创建异常类

> 在程序中，可能会遇到任何标准异常类都没有能够充分地描述清楚的问题。
> 在这种情况下，创建自己的异常类就是一件顺理成章的事情了。
> 我们需要做的只是定义一个派生于 Exception 的类，或者派生于 Exception 子类的类。
> 习惯上，定义的类应该包括两个构造器，一个是默认的构造器；
> 另一个是带有详细描述信息的构造器。

```java
class FileFormatException extends IOException {
    public FileFormatExceotion();
    public FileFormatException(String gripe) {
        super(gripe);
    }
}
```

## 捕获异常

### 捕获异常

想要捕获一个异常，必须设置 try/catch 语句块。最简单的 try 语句块如下所示：

```
try {
    code
    more code
    more code
} catch (ExceptionType e) {
    handler for this type
}
```

> 如果在 try 语句块中的任何代码抛出一个在 catch 子句中说明的异常类，那么

1. 程序将跳过 try 语句块的其余代码。
2. 程序将执行 catch 子句中的处理器代码。
   > 如果在 try 语句块中的代码没有抛出任何异常，那么程序将跳过 catch 子句。

> 如果方法中的任何代码抛出了一个在 catch 子句中没有声明的异常类型，
> 那么这个方法就会立刻退出。

> catch 子句可以有多个，捕获多个异常。

### 再次抛出异常或异常链

> 在 catch 子句中可以抛出一个异常，这样做的目的是改变异常的类型。

```
try {
    access the database
} catch (SQLException e) {
    Throwable se = new ServletException("database error");
    se.initCause(e);
    throw se;
}
```

当捕获到异常时，就可以使用下面这条语句重新得到原始异常：

```
Throwable e = se.getCause();
```

> 这样可以让用户抛出子系统中的高级异常，而不会丢失原始异常的细节。

### finally 子句

> 无论是否有异常被捕获，finally 子句中的代码都会被执行。

### 带资源的 try 语句

```
try (Resource res = ...) {
    work with res
}
```

> try 块退出时，会自动调用 res.close().

## 使用异常机制的技巧

1. 异常处理不能代替简单的测试
2. 不要过分地细化异常
3. 利用异常层次结构
4. 不要抑制异常
5. 在检测错误时，“苛刻” 要比放任更好
6. 不要羞于传递异常

## 使用断言

- 断言失败是致命的，不可恢复的错误。
- 断言检测只用于开发和测试阶段。

## 记录日志

记录日志 API 的优点：

- 可以很容易地取消全部日志记录，或者仅仅取消某个级别的日志，而且打开和关闭这个操作也很容易。
- 可以很容易地禁止日志记录的输出，因此，将这些日志代码留在程序中的开销很小。
- 日志记录可以被定向到不同的处理器，用于在控制台中显示，用于存储在文件中等。
- 日志记录器和处理器都可以对记录进行过滤。过滤器可以根据过滤实现器制定的标准丢弃那些无用的记录项。
- 日志记录可以采用不同的方式格式化，例如：纯文本或 XML。
- 应用程序可以使用多个日志记录器，它们使用类似包名的这种具有层次结构的名字。
- 在默认情况下，日志系统的配置由配置文件控制。如果需要的话，应用程序可以替换这个配置。

### 基本日志

要生成简单的日志记录，可以使用全局日志记录器(global logger)并调用其 info 方法：

```
Logger.getGlobal().info(infomation);
```

如果在适当的地方(如 main 开始)调用 `Logger.getGlobal().setLevel(Level.OFF);`
将会取消所有的日志。

### 高级日志

调用 getLogger 方法创建或获取记录器：

```
private static final Logger myLogger = Logger.getLogger("com.mycompany.myapp")；
```

> 未被任何变量引用的日志记录器可能会被垃圾回收。为了防止这种情况发生，要像上面的例子一样，
> 用一个静态变量存储日志记录器的一个引用。

> 与包名类似，日志记录器名也具有层次结构。
> 事实上，与包名相比，日志记录器的层次性更强。对于包来说，一个包的名字与其父包的名字之间没有语义关系，
> 但是日志记录器的父与子之间将共享某些属性。
> 例如，如果对 com.mycompany.myapp 日志记录器设置了日志级别，它的子记录器也会继承这个级别。

通常，有以下 7 个日志记录器级别（从高到低）：

- SEVERE
- WARNING
- INFO
- CONFIG
- FINE
- FINER
- FINEST

在默认情况下，只记录前三个级别。可以通过 logger.setLevel(Level.XXX)设置其他的级别。
