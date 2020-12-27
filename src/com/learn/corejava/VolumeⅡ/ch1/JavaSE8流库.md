# Java SE 8 流库
> 流提供了一种让我们可以在比集合更高的概念级别上指定计算的数据视图。
> 通过使用流，我们可以说明想要完成什么任务，而不是说明如何去实现它。

## 从迭代到流的操作
在处理集合时，我们通常会迭代遍历它的元素，并在每个元素上执行某项操作。
例如，如果要统计一个 `List<String> words`中长度大于 12 的元素个数，
使用遍历方法如下：
```
long count = 0;
for (String w : words) {
    if (w.length() > 12) count++;
}
```
如果使用流，相同的操作看起来像下面这样：
```
long count = words.stream()
    .filter(w -> w.length() > 12)
    .count();
```
流遵循了 “做什么而非怎么做” 原则。

流与集合存在着显著的差异：
- 流并不存储其元素。这些元素可能存储在底层的集合中，或者是按需生成的。
- 流的操作不会修改其数据源。
    例如，filter 方法不会从新的流中移除元素，而是会生成一个新的流，其中不包含被过滤掉的元素。
- 流的操作是尽可能惰性执行的。这意味着直至需要其结果，操作才会执行。
    例如，我们只想查找前 5 个长单词，那么 filter 方法就会在匹配到第 5 个单词后停止过滤。因此，我们甚至可以操作无限流。

## 流的创建
- 可以用 Collection 接口的 stream 方法将任何集合转换为一个流。
- 对于数组，可以使用静态的 Stream.of 方法(of 方法具有可变长参数)。
- 使用 Array.stream(array, from, to) 可以从数组中位于 from (包括)
    到 to (不包括) 的元素中创建一个流。
- 静态方法 Stream.empty 方法可以创建一个不包含任何元素的流。
- Stream.generate 方法和 Stream.iterate 方法可以用于创建无限流

> The generate method takes a function with no arguments
> (or, technically, an object of the Supplier<T> interface).
> Whenever a stream value is needed, that function is called to produce a value. You can get a
  stream of constant values as
```
Stream<String> echos = Stream.generate(() -> "Echo");
```
> or a stream of random numbers as
```
Stream<Double> randoms = Stream.generate(Math::random);
```

> To produce infinite sequences, such as 0 1 2 3 . . . , 
> use the iterate method instead. It takes a “seed” value and a function 
> (technically, a UnaryOperator<T>) and repeatedly applies the
  function to the previous result. 
> For example,
```
Stream<BigInteger> integers
    = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
```
> The first element in the sequence is the seed BigInteger.ZERO,
> The second element is f(seed),or 1 (as a big integer). 
> The next element is f(f(seed)), or 2, and so on.

## filter、map 和 flatMap 方法

## 抽取子流和连接流
调用 stream.limit(n) 会返回一个新的流，它在 n 个元素之后结束(如果原来的流更短，那么就会在流结束时结束)。

调用 stream.skip(n) 正好相反：它会丢弃前 n 个元素。

静态方法 Stream.concat 方法能将两个流连接起来。

## 其他的流转换
- distinct 方法
- sorted 方法
- peek 方法：
    peek 方法会产生另一个流，它的元素与原来的流中的元素相同，
    但是在每次获取一个元素时，都会调用一个函数。

## 简单简约(Simple Reductions)
简约(reductions)是一种终结操作(terminal operation)，它们会将流简约为可以在程序中使用的非流值。

如：count、max、min、findFirst 等方法

**注意：** 这些方法返回的是一个类型为 Optional<T> 的值

## Optional 类型
> Optional<T> 对象是一种包装器，要么包装了类型 T 的对象，要么没有包装任何东西。

### 如何使用 Optional 值
有效地使用 Optional 的关键是要使用这样的方法：
> 它在值不存在的情况下会产生一个可替代物，而只有在值存在的情况下才会使用这个值。

通常，在没有任何匹配时，我们会希望使用某种默认值：
```
// The wrapped string, or "" if none
String result = optionalString.orElse("");
```
还可以调用代码来计算默认值：
```
// The function is only called when needed
String result = optionalString.orElseGet(() ->
    Locale.getDefault().getDisplayName());
```
或者在没有任何值时抛出异常：
```
// Supply a method that yields an exception object
String result = optionalString.orElseThrow(IllegalStateException::new);
```

也可以选择在有值的时候才消费其值：
```
optionalValue.ifPresent(v -> Process v);
```
ifPresent 方法会接收一个函数。
如果 Optional 值存在，那么它会被传递给该函数。

## 收集结果
```
// 将函数作用于每个元素
stream.forEach(System.out::println)

// toArray 方法会返回一个 Object[] 数组。
// 如果想让数组具有正确的类型，可以将其传递到数组构造器中。
String[] result = stream.toArray(String[]::new);

List<String> result = stream.collect(Collectors.toList());
Set<String> result = stream.collect(Collectors.toSet());
TreeSet<String> result = stream.collect(Collectors.toCollection(TreeSet::new));
String result = stream.collect(Collectors.joining());
String result = stream.collect(Collectors.joining(", "));
String result = stream.map(Object::toString).collect(Collectors.joining(", "));
```

## 收集到映射表中

## 群组和分区

## 下游收集器

## reduce 操作

## 基本类型流

## 并行流



















