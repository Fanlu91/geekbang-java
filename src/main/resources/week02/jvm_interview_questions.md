# 1什么是JVM？

1.1 请问JDK与JVM有什么区别？

jvm + lib + tools

1.2 你认识哪些JVM厂商?

oracle redhat

1.3 OracleJDK与OpenJDK有什么区别?

oracle 非开源

1.4 开发中使用哪个版本的JDK？生产环境呢? 为什么这么选？

jdk1.8

# 2什么是Java字节码？

2.1 字节码文件中包含哪些内容?

0xCAFEBABE 魔数 、meta info、常量池、操作码、padding

2.2 什么是常量?

constant，描述符

2.3 你怎么理解常量池?

享元模式

# 3JVM的运行时数据区有哪些?

heap、non-heap、 stack areas、pc register、constant pool

3.1 什么是堆内存？

heap，所有线程共有的内存区域，存放对象、数组等

3.2 堆内存包括哪些部分？

堆内存一般包括 新生代、老年代

3.3 什么是非堆内存?

non-heap，包括metaspace、ccs、jit code



# 4什么是内存溢出？

oom 需要使用的内存超出可用值

4.1 什么是内存泄漏?

不使用没有被回收

没有在正确的时间被回收

4.2 两者有什么关系？

泄漏最终会导致溢出

# 5给定一个具体的类，请分析对象的内存占用

5.1 怎么计算出来的?

对象头+对象体

5.2 对象头中包含哪些部分？



# 6常用的JVM启动参数有哪些?

6.1 设置堆内存XMX应该考虑哪些因素？

当前系统最大内存数，占用数，同时运行的其他应用程序穷寇

6.2 假设物理内存是8G，设置多大堆内存比较合适?

假设没有其他应用程序共享，设置为70%-80% 即6G左右比较合理

6.3 -Xmx 设置的值与JVM进程所占用的内存有什么关系?

堆内存外还包括stack 、非堆及直接引用内存

6.4 怎样开启GC日志？

-XX:PrintGCDetails -XX:PrintGC

6.5 请指定使用G1垃圾收集器来启动Hello程序

java -XX:UseG1Gc Hello



# 7Java8默认使用的垃圾收集器是什么?

parallel scavenge + parallel old

7.1 Java11的默认垃圾收集器是什么?

G1

7.2 常见的垃圾收集器有哪些?

serial serialold 

parNew \parallel scavenge \ Concurrent Mark Sweep 

G1 \ ZGC \ shennandoah



7.3 什么是串行垃圾收集?

单线程

7.4 什么是并行垃圾收集?

parallel ，并行时STW

7.5 什么是并发垃圾收集器?

concurrent，并发过程与业务线程同时执行

7.6 什么是增量式垃圾收集?

increment

7.7 什么是年轻代？
7.8 什么是GC停顿(GC pause)?

STW

7.9 GC停顿与STW停顿有什么区别？

一回事？

# 8如果CPU使用率突然飙升，你会怎么排查？

8.1 如果系统响应变慢，你会怎么排查？

查看GC状态

查看监控指标，线程状态

8.2 系统性能一般怎么衡量？

吞吐量

响应时间 99% 90%



# 9使用过哪些JVM相关的工具？

9.1 查看JVM进程号的命令是什么?

jps
9.2 怎么查看剩余内存?

free -h

9.3 查看线程栈的工具是什么？

jstack

9.4 用什么工具来获取堆内存转储?

jmap -histo

9.5 内存Dump时有哪些注意事项?

影响业务运行。数据过大时间会较长

9.6 使用JMAP转储堆内存大致的参数怎么处理？

jmap -heap

9.7 为什么转储文件以 .hprof 结尾?

不知道

9.8 内存Dump完成之后，用什么工具来分析?

jvisual, eclipse MAT

9.9 如果忘记了使用什么参数你一般怎么处理?

jcmd pid help



# 10开放性问题：你碰到过哪些JVM问题？

配置失效导致gc频繁

内存泄漏导致OOM

