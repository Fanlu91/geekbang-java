## GC垃圾回收

垃圾回收器实际上更应该叫做内存管理器。是对对象的整个生命周期的管理。



### 回收判定算法

#### 引用计数法（Reference Counting）

给对象中添加一个引用计数器，每当有一个地方引用它时，计数器值就加1：当引用失效时，计数器值就减1：任何时刻计数器都为0的对象就是不可能再被使用的。

- 实现简单，判断效率高，大部分情况下都是很不错的算法
- 但**很难解决对象之间的相互循环引用的问题**
  - 循环依赖的问题跟多线程、事务的死锁本质上都是一类问题

#### 根搜索算法（GCRoots Tracing）

又叫引用跟踪算法、可达性算法。Java和C#，都是使用根搜索算法判定对象是否存活。

通过一系列的名为`GCRoot`的对象作为起始点，从这些节点开始向下搜索，搜索所走过的路径称为引用链`Reference Chain`，当一个对象到GCRoot没有任何引用相连（从GCRoot到这个对象不可达）时，则证明此对象是不可用的。采用标记清除进行回收。

##### STW

堆上可能有成千上百万的对象，如何保证标记清除不乱呢？

最终的办法就是STW， stop the world，这时除了垃圾回收线程，没有任何的业务线程可以去修改对象。STW也叫GC暂停，发生的时间越短越好，频率越低越好。

- 随着收集器的不断发展，用户线程停顿时间不断缩短， 但仍然无法完全消除。

- STW总会发生不管是新生代还是老年代收集。



##### 可作GCRoots的对象

- 当前正在执行方法里的局部变量和输入参数，即虚拟机栈（栈帧中的本地变量表）中的引用的对象。
- 活动线程本身ative threads
- 所有类的静态字段，全局有效，即方法区中的类静态属性引用的对象、常量引用的对象。
- JNI引用，本地方法栈中JNl（即一般说的Native方法）引用的对象。



##### **优点**

除了能够解决循环依赖问题，还有只需要扫描部分对象，当大量对象都需要回收只有少部分不回收的时候效率更高。

GCRoots 标记**暂停的时间**与堆内存大小，对象的总数没关系，而是由存活对象（alive objects）的数量决定，所以增加内存大小并不会直接影响标记阶段占用的时间。







不同的gc策略，不同的堆内存配置、参数配置下
gc表现 gc特点 适用场景



### 垃圾回收算法

#### 分代回收 Generational Collection

分代假设：大部分新生对象很快无用，存活较长时间的对象，可能存活更长时间。

根据对象的存活周期的不同将内存划分为几块，不同区域采用不同的策略处理。当前商业虚拟机都是采用这种方式。

- 新生代，每次垃圾回收都有大量对象失去，选择**复制算法**。 
- 老年代，对象存活率高，无人进行分配担保，就必须采用**标记清除**或者**标记整理**算法

#### 具体算法

##### 复制回收算法（Copying）

新生成对象放eden，将survivor区划分为相等的两块s0，s1，每次只使用其中的一块。当eden+s0用完了，就将还存活着的对象复制到s1上，之后将已使用过的内存空间一次清理掉。重复上述过程，s0和s1始终有一块是空的。

现在商业虚拟机都是采用这种收集算法来**回收新生代**。当Survivor空间不够用时，需要依赖其他内存（这里指老年代）进行分配担保（Handle Promotion）

###### 内存分配担保

当在新生代无法分配内存的时候，把新生代的对象转移到老生代，然后把新对象放入腾空的新生代。

不同垃圾回收器组合下，担保机制也略有不同。

- 在Serial+Serial Old的情况下，发现放不下就直接启动担保机制；

- 在Parallel Scavenge+Serial Old的情况下，却是先要去判断一下要分配的内存是不是 >=Eden区大小的一半，如果是那么直接把该对象放入老生代，否则才会启动担保机制。

  <img src="../../../../../../../learning/java/jvm/JVM_JMM.assets/image-20210430163652304.png" alt="image-20210430163652304" style="zoom:67%;" />

##### 标记-清除算法（Mark-Sweep）

首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象

- 回收停顿小，但效率不高，标记和清除过程的效率都不高 
- 空间碎片，会产生大量不连续的内存碎片，可能会导致大对象无法分配，提前触发GC 

<img src="../../../../../../../learning/java/jvm/JVM_JMM.assets/image-20210430163635923.png" alt="image-20210430163635923" style="zoom: 67%;" />

##### 标记-整理算法（Mark-Compact）

标记过程仍然与“标记-清除”算法 一样，然后让所有存活的对象都向一端移动，直接清理掉端边界以外的内存。





#### 分类（partial/full minor/major young/old）

GC的分类说法相对混乱，个人认为是minor和full 两种情况出现次数较多因此出现了这样的描述，实际应当分为

Partial GC：并不收集整个GC堆的模式

- Young GC：即minor gc，只收集young gen的GC
  - 当年轻代满时会触发Minor GC，这里年轻代满指的是Eden满，Survivor满不会引发GC
- Old GC：只收集old gen的GC。只有CMS的concurrent collection是这个模式
- Mixed GC：收集整个young gen以及部分old gen的GC。只有G1有这个模式

Full GC：收集整个堆，包括young gen、old gen、perm gen（如果存在的话）等所有部分的模式。

- Major GC通常是跟full GC是等价的，收集整个GC堆。但是有的地方会认为Major GC 与Old GC对应（但其实只有CMS有old gc）。
- Parallel Scavenge框架下，默认是在要触发full GC前**先执行一次young GC**，并且两次GC之间能让应用程序稍微运行一小下，以期降低full GC的暂停时间（因为young GC会尽量清理了young gen的对象，减少了full GC的工作量）。控制这个行为的VM参数是`-XX:+ScavengeBeforeFullGC`



### 垃圾收集器

垃圾收集器是垃圾回收算法的具体实现，JDK8 HotSpot虚拟机中的7种垃圾收集器

- 新生代收集器：Serial、ParNew、Parallel Scavenge
- 老年代收集器：Serial Old、Parallel Old、CMS
- 整堆收集器：G1

#### 搭配使用方式

```less
# -XX:+UseSerialGC ： Serial + Serial Old
# -XX:+UseParNewGC ： ParNew + Serial Old

# -XX:+UseParallelGC ： （不同版本有区别！）
Parallel Scavenge + Serial Old(Ps MarkSweep) JDK6/7u4前
Parallel Scavenge + Parallel Old  7u4+/8 默认，相当于和UseParallelOldGC一样。

# -XX:+UseParallelOldGC ： Parallel Scavenge + Parallel Old

# -XX:+UseConcMarkSweepGC ： ParNew + CMS (+ Serial Old)

# -XX:+UseG1GC : G1 JDK9+默认
```

#### 并发/并行垃圾收集的区别

都是多线程的

- 并行（Parallel）
  指多条垃圾收集线程并行工作，使用了所有的cpu，此时用户线程都处于等待状态；

  如ParNew、Parallel Scavenge、Parallel Old；

- 并发（Concurrent）
  指用户线程与垃圾收集线程同时执行（但不一定是并行的，可能会交替执行），用户程序在继续运行，而垃圾收集程序线程运行于另一个CPU上；
  如CMS、G1（也有并行）

#### Serial GC/Parallel GC

Serial/ParNew/Parallel ScavengeSerial Old/Parallel Old

| 收集器            | 介绍                                                         | 特点                                                         | **参数设置**                                                 | 缺点                                                         |
| :---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Serial            | 新生代收集器，最基本、发展历史最悠久的，依然是HotSpot在Client模式下默认的新生代收集器 | 采用复制算法，单线程收集 <br/>简单高效（与其他收集器的单线程相比）；对单个CPU环境，Serial收集器没有线程切换开销；<br/>用户的桌面应用场景中，可用内存一般不大（几十M至一两百M），可以在较短时间内完成垃圾收集（几十MS至一百多MS） | `-XX:+UseSerialGC`：添加该参数来显式的使用串行垃圾收集器     | 进行垃圾收集时，必须暂停所有工作线程，直到完成               |
| ParNew            | 新生代收集器，Serial收集器的多线程版本。                     | 除了多线程外，其余的行为、特点和Serial收集器一样。能与CMS收集器配合工作 | `-XX:+UseConcMarkSweepGC`：指定使用CMS后，会默认使用ParNew作为新生代收集器<br/>`-XX:+UseParNewGC`：强制指定使用ParNew<br/>`-XX:ParallelGCThreads`：指定垃圾收集线程数量，默认收集线程与CPU数量相同 | 单个CPU环境中，不会比Serail收集器有更好的效果，因为存在线程交互开销。 |
| Parallel Scavenge | 新生代收集器，与吞吐量关系密切，也称为吞吐量收集器（**Throughput Collector**），CMS等收集器的关注点是缩短垃圾收集时用户线程的**停顿时间**； 而Parallel Scavenge收集器的目标则是达一个**可控制的吞吐量（Throughput）**。 | 采用复制算法，多线程收集，适用于多CPU，对停顿时间没特别要求的运算任务 | `-XX:MaxGCPauseMillis` 控制最大垃圾收集停顿时间，大于0的毫秒数； 如果值过小，停顿时间会缩短，但是垃圾收集次数会更频繁，使吞吐量下降； <br/>`-XX:GCTimeRatio` 设置垃圾收集时间占总时间的比率，0-100的整数，相当于设置吞吐量大小。<br/>垃圾收集时间占应用执行时间的比例算法 1 / (1 + n)，默认是99即1%。 垃圾收集所花费的时间是年轻一代和老年代收集的总时间； | Parallel Scavenge（以及G1）没有使用传统的GC收集器代码框架，与CMS不能同时使用。 |
| Serial Old        | 针对老年代，采用"标记-整理"算法(还有压缩，Mark-Sweep-Compact）， 单线程收集，主要用于Client模式 | 作为CMS收集器的后备预案，在并发收集发生Concurrent Mode Failure时使用 |                                                              |                                                              |
| Parallel Old      | Parallel Scavenge收集器的老年代版本                          | 采用"标记-整理"算法，多线程收集；在注重吞吐量以及CPU资源敏感场景，就有Parallel Scavenge加Parallel Old收集器 | `-XX:+UseParallelOldGC`：指定使用Parallel Old收集器；        |                                                              |

#### CMS收集器

并发标记清理(**Concurrent Mark Sweep**，CMS)收集器也称为并发低停顿收集器(Concurrent Low Pause Collector)或低延迟(low-latency)垃圾收集器。

- 针对老年代； 
- 基于"标记-清除"算法，不进行压缩操作，会产生内存碎片，以获取最短回收停顿时间为目标；
  - free list，使用索引记录当前所有可用内存空间的位置
- 并发收集、低停顿； 
  - 回收过程中，大部分时间业务线程和垃圾回收线程是同时工作的
- 需要更多的内存

第一次实现了让垃圾收集线程与用户线程(基本上)同时工作，适合与用户交互较多的场景；希望系统停顿时间最短，注重服务的响应速度。

`-XX:+UseConcMarkSweepGC`：指定使用CMS收集器

##### 运作过程

1. 初始标记(initial mark) ，速度很快，标记的数据是完全准确的，但需要STW（第一次）
   - 标记一下GC Roots能直接关联到的对象，即根对象直接引用的对象；
   - 被年轻代中所有存活对象所引用的对象
     - Remember Set，专门用来记录跨代对象之间的引用关系
2. 并发标记（concurrent mark)，进行GC Roots Tracing的过程，
   - 上个阶段标记的对象接着向下寻找引用，遍历完整个老年代堆里的对象 
   - 这个阶段并发，应用程序也在运行，并不能保证可以标记出所有的存活对象
3. 并发预清理(concurrent preclean)，为了修正并发标记期间因用户程序继续运作而导致标记变动的那一部分对象的标记记录；
   - 并发标记期间引用关系发生了变化，JVM会进行卡片标记，card marking，将发生了改变的区域标记为脏区域
4. 最终标记(final remark)
   - STW（第二次），且停顿时间比初始标记稍长
   - 把脏的区域的引用关系捋清
5. 并发清除(concurrent sweep)
   - 回收所有的垃圾对象
6. 并发重制(CMS concurrent sweep)
   - 重制JVM内部CMS相关的各种数据和状态，为下一次循环做准备



##### 特点

一共两次短暂的STW

CMS会尝试在年轻代比较空的时候进行标记。

整个过程中并发过程（2356）尤其是耗时最长的**并发标记**和**并发清除**都可以与用户线程一起工作。防止出现长时间停顿。





##### 缺点

- 不进行碎片清理，产生大量内存碎片
  - 由于空间不再连续，CMS需要使用可用"空闲列表"内存分配方式，这比简单是用"碰撞指针"分配内存消耗大

- 对CPU资源非常敏感
  - 针对这个问题，使用增量式并发收集器：(Incremental Concurrent Mark Sweep/i-CMS)，但并不理想
- 无法处理浮动垃圾，可能出现"Concurrent Mode Failure"失败
  - 在并发清除时，用户线程新产生的垃圾，称为浮动垃圾(Floating Garbage)。这使得并发清除时需预留一定空间，不能像其他收集器在老年代快满再进行收集。也要可以认为CMS所需要的空间比其他垃圾收集器大
  - 如CMS预留空间无法满足需求，就会出现一次"Concurrent Mode Failure"失败； 这时JVM启用后备预案：临时启用Serail Old收集器，而导致另一次Full GC的产生

#### 选择垃圾收集器

1. **Serial**收集器适用于应用程序的内存使用少于100MB的场景，但是大多数的情况下，只能作为其他GC收集器的替补选项。
2. **Throughput**收集器处理批量任务的时候，能够最大限度利用CPU的处理能力（全力计算、全力GC）通常能够获得更好的性能(吞吐量)。但是如果批量任务并没有使用机器上所有的可用CPU资源，那么使用**Concurrent**收集器往往能够取得更好的性能。
3. 如果CPU计算资源不足，或者无法获取连续空间容纳对象的时候，采用CMS收集器的时候，可能会出现并发模式失效（Concurrent Mode Failure），这会使JVM退化成为单线程的Full GC模式，从而使得CPU利用率下降，导致长时间的Full GC停顿。
4. 相比Throughout收集器，CMS在99%响应时间上有优势，CMS减少Full GC的次数，从而减少了由于Full GC导致长时间停顿的次数。在90%响应时间上，Throughput则可能优于CMS收集器，一般而言在CPU资源充足的情况下，CMS的响应时间要优于Throughout收集器。
5. 一般情况下堆空间小于4G选择CMS收集器的性能会比G1更好，因为CMS的算法相比G1更加简单，在堆较少的情况下运行速度回更快，而当堆较大的时候，G1收集可以分割工作区，不必像CMS一样扫描完整个老年代空间，通常比CMS收集器表现更好。同时G1收集器可以并行对老年代进行压缩整理更加不容易出现碎片化空间，从而降低Full GC出现的几率。
6. 无论是CMS还是G1都仍然可能出现并发模式失效的问题
7. Throughout和CMS算法存在的时间比较长，经过了大量的优化，G1收集器存在的时间较短，实际上大部分Concurrent选择的时候还是选择CMS较多。未来G1应该会作为一个选项。JDK9 之后已经默认使用G1。

