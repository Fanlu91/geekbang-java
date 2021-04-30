## 一次jvm使用诊断

检查测试环境某网关应用的jvm使用情况

```shell
ngw@node1:/home/ngw> java -XX:+PrintCommandLineFlags -version
-XX:InitialHeapSize=128108928 -XX:MaxHeapSize=2049742848 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
java version "1.8.0_144"
Java(TM) SE Runtime Environment (build 1.8.0_144-b01)
Java HotSpot(TM) 64-Bit Server VM (build 25.144-b01, mixed mode)
```

MaxHeapSize 1,954MB InitialHeapSize  122 MB

### jps

```less
ngw@node1:/home/ngw> jps -lvm | grep 22659
22659 /home/ngw/server/ngw-eCNY.jar --spring.config.location=/home/ngw/server/config/application.yml,/home/ngw/server/config/application-ndps-db.yml,/home/ngw/server/config/application-ndps-listener.yml,/home/ngw/server/config/application-ndps-flows.yml,/home/ngw/server/config/application-ndps-registry.yml -Xms2048m -Xmx8192m -Xss256k -XX:PermSize=256m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -Dfile.encoding=UTF-8 -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=172.22.14.30 -Dcom.sun.management.jmxremote.port=3635 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
```



### jinfo

```shell
ngw@node1:/home/ngw> jinfo -flags 22659
Attaching to process ID 22659, please wait...
Error attaching to process: sun.jvm.hotspot.runtime.VMVersionMismatchException: Supported versions are 25.144-b01. Target VM is 25.262-b10
sun.jvm.hotspot.debugger.DebuggerException: sun.jvm.hotspot.runtime.VMVersionMismatchException: Supported versions are 25.144-b01. Target VM is 25.262-b10
	at sun.jvm.hotspot.HotSpotAgent.setupVM(HotSpotAgent.java:435)
	at sun.jvm.hotspot.HotSpotAgent.go(HotSpotAgent.java:305)
	at sun.jvm.hotspot.HotSpotAgent.attach(HotSpotAgent.java:140)
	at sun.jvm.hotspot.tools.Tool.start(Tool.java:185)
	at sun.jvm.hotspot.tools.Tool.execute(Tool.java:118)
	at sun.jvm.hotspot.tools.JInfo.main(JInfo.java:138)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.tools.jinfo.JInfo.runTool(JInfo.java:108)
	at sun.tools.jinfo.JInfo.main(JInfo.java:76)
Caused by: sun.jvm.hotspot.runtime.VMVersionMismatchException: Supported versions are 25.144-b01. Target VM is 25.262-b10
	at sun.jvm.hotspot.runtime.VM.checkVMVersion(VM.java:227)
	at sun.jvm.hotspot.runtime.VM.<init>(VM.java:294)
	at sun.jvm.hotspot.runtime.VM.initialize(VM.java:370)
	at sun.jvm.hotspot.HotSpotAgent.setupVM(HotSpotAgent.java:431)
	... 11 more
```

**解决版本问题**

```shell
[root@node1 ~]# yum list --showduplicate | grep java-1.8 | grep devel
Repodata is over 2 weeks old. Install yum-cron? Or run: yum makecache fast
java-1.8.0-openjdk-devel.i686           1:1.8.0.262.b10-1.el7      rhel79
java-1.8.0-openjdk-devel.x86_64         1:1.8.0.262.b10-1.el7      rhel79
[root@node1 ~]# yum install java-1.8.0-openjdk-devel.x86_64 -y
```

**重新执行命令**

```shell
[root@node1 ~]# jinfo -flags 22659
Attaching to process ID 22659, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.262-b10
Non-default VM flags: -XX:CICompilerCount=3 -XX:InitialHeapSize=130023424 -XX:+ManagementServer -XX:MaxHeapSize=2051014656 -XX:MaxNewSize=683671552 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=42991616 -XX:OldSize=87031808 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
Command line:  -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=172.22.14.30 -Dcom.sun.management.jmxremote.port=3635 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
```

InitialHeapSize 124M 、MaxHeapSize 1,956M、MaxNewSize 652M

发现问题，与查看到的启动参数不相符。

### jstat

```shell
ngw@node1:/home/ngw> jstat -gc -t 22659
Timestamp        S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
9133951.4 1024.0 1024.0  0.0   608.0   3072.0   2467.9   435200.0   424518.9  76160.0 73271.6 9344.0 8776.0 278354 2740.718 1117   375.562 3116.280
ngw@node1:/home/ngw> jstat -gcutil -t 22659
Timestamp         S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
9133983.7  53.12   0.00  25.96  97.60  96.21  93.92 278355 2740.736  1117  375.562 3116.298
      
# 间隔2000ms 重复5次
ngw@node1:/home/ngw> jstat -gcutil -t 22659 2000 5
Timestamp         S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
      9134289.9  53.12   0.00  79.45  97.81  96.21  93.92 278359 2740.792  1117  375.562 3116.354
      9134291.9  53.12   0.00  79.45  97.81  96.21  93.92 278359 2740.792  1117  375.562 3116.354
      9134293.9  53.12   0.00  82.43  97.81  96.21  93.92 278359 2740.792  1117  375.562 3116.354
      9134295.9  53.12   0.00  85.05  97.81  96.21  93.92 278359 2740.792  1117  375.562 3116.354
      9134297.9  53.12   0.00  85.74  97.81  96.21  93.92 278359 2740.792  1117  375.562 3116.354

```

old gen capacity 435200 = 425‬MB

young gen capacity  1024.0 1024 + 3072 = 5MB

这个数据与期望差距很远。



```shell
ngw@node1:/home/ngw> jstat -class 22659
Loaded  Bytes  Unloaded  Bytes     Time
 12984 24498.3       88   122.8       7.82

ngw@node1:/home/ngw> jstat -compiler 22659
Compiled Failed Invalid   Time   FailedType FailedMethod
   14597      3       0   121.38          1 com/alibaba/druid/filter/FilterEventAdapter connection_prepareStatement


ngw@node1:/home/ngw> jstat -gccause 22659
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT    LGCC                 GCC
  0.00  56.25  34.95  97.78  96.21  93.92 278314 2740.150  1116  375.208 3115.357 Allocation Failure   No GC

```

young gc 平均每次时间 9-10毫秒，full gc 平均每次时间 336毫秒，时间较长且频率较高。



### jmap

#### jmap -heap

```less
[root@node1 ~]# jmap -heap 22659
Attaching to process ID 22659, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.262-b10

using thread-local object allocation.
Parallel GC with 4 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 2051014656 (1956.0MB)
   NewSize                  = 42991616 (41.0MB)
   MaxNewSize               = 683671552 (652.0MB)
   OldSize                  = 87031808 (83.0MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 3145728 (3.0MB)
   used     = 1377064 (1.3132705688476562MB)
   free     = 1768664 (1.6867294311523438MB)
   43.77568562825521% used
From Space:
   capacity = 1048576 (1.0MB)
   used     = 589824 (0.5625MB)
   free     = 458752 (0.4375MB)
   56.25% used
To Space:
   capacity = 1048576 (1.0MB)
   used     = 0 (0.0MB)
   free     = 1048576 (1.0MB)
   0.0% used
PS Old Generation
   capacity = 445644800 (425.0MB)
   used     = 436296112 (416.0843963623047MB)
   free     = 9348688 (8.915603637695312MB)
   97.90221090877758% used

26980 interned Strings occupying 2877272 bytes.
```

再次印证实际情况  young/old gen capacity的问题

实际情况与配置 `NewRatio   = 2` `SurvivorRatio    = 8`  的差异十几分明显。

#### jmap -histo

使用root执行报错

```less
[root@node1 ~]# jmap -histo 22659 | head -10
22659: Unable to open socket file: target process not responding or HotSpot VM not loaded
The -F option can be used when the target process is not responding

[root@node1 ~]# jmap -histo 22659 | head -10
22659: well-known file /tmp/.java_pid22659 is not secure: file should be owned by the current user (which is 0) but is owned by 1000

```

错误原因应该是因为执行jmap的用户应该是run这个线程的用户，切换至ngw之后可以使用。jstack、jcmd也有同样的问题。

```less
ngw@node1:/home/ngw> jmap -histo 22659 | head -10

 num     #instances         #bytes  class name
----------------------------------------------
   1:         68453      267956688  [B
   2:        102307      140679664  [C
   3:         49317        6925688  [Ljava.lang.Object;
   4:         67170        2149440  java.lang.ThreadLocal$ThreadLocalMap$Entry
   5:         32390        1813840  sun.nio.cs.UTF_8$Encoder
   6:          2496        1637376  io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue
   7:         67932        1630368  java.lang.String
```



### jcmd

```less
ngw@node1:/home/ngw> jcmd 22659 help
22659:
The following commands are available:
VM.unlock_commercial_features
JFR.configure
JFR.stop
JFR.start
JFR.dump
JFR.check
VM.native_memory
ManagementAgent.stop
ManagementAgent.start_local
ManagementAgent.start
VM.classloader_stats
GC.rotate_log
Thread.print
GC.class_stats
GC.class_histogram
GC.heap_dump
GC.finalizer_info
GC.heap_info
GC.run_finalization
GC.run
VM.uptime
VM.dynlibs
VM.flags
VM.system_properties
VM.command_line
VM.version
help

For more information about a specific command use 'help <command>'.
    
    
ngw@node1:/home/ngw> jcmd 22659 VM.command_line
22659:
VM Arguments:
jvm_args: -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=172.22.14.30 -Dcom.sun.management.jmxremote.port=3635 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
java_command: /home/ngw/server/ngw-eCNY.jar --spring.config.location=/home/ngw/server/config/application.yml,/home/ngw/server/config/application-ndps-db.yml,/home/ngw/server/config/application-ndps-listener.yml,/home/ngw/server/config/application-ndps-flows.yml,/home/ngw/server/config/application-ndps-registry.yml -Xms2048m -Xmx8192m -Xss256k -XX:PermSize=256m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -Dfile.encoding=UTF-8
java_class_path (initial): /home/ngw/server/ngw-eCNY.jar
Launcher Type: SUN_STANDARD
    
ngw@node1:/home/ngw> jcmd 22659 GC.heap_info
22659:
 PSYoungGen      total 4096K, used 2573K [0x00000000d7400000, 0x00000000d7900000, 0x0000000100000000)
  eden space 3072K, 63% used [0x00000000d7400000,0x00000000d75eb540,0x00000000d7700000)
  from space 1024K, 59% used [0x00000000d7700000,0x00000000d7798000,0x00000000d7800000)
  to   space 1024K, 0% used [0x00000000d7800000,0x00000000d7800000,0x00000000d7900000)
 ParOldGen       total 435200K, used 431309K [0x0000000085c00000, 0x00000000a0500000, 0x00000000d7400000)
  object space 435200K, 99% used [0x0000000085c00000,0x00000000a0133790,0x00000000a0500000)
 Metaspace       used 73275K, capacity 75704K, committed 76160K, reserved 1116160K
  class space    used 8776K, capacity 9191K, committed 9344K, reserved 1048576K
```



## 问题解决

### 启动参数未生效

首先对比jinfo，启动参数`-Xms2048m -Xmx8192m -Xss256k -XX:PermSize=256m -XX:MaxPermSize=512m` 配置都没有生效。排查应该是参数位置不对导致，调整至jar文件名前重新启动正常。

同时`-XX:PermSize=256m -XX:MaxPermSize=512m` 是jdk8之前的配置，当前是不生效的，移除。

`-Xmx8192m` 设置于实际配置及运行需要也不相符，调整至4g。

```shell
ngw@node01:/home/ngw/server/shell> jps -vlm
4676 /home/ngw/server/ngw-eCNY.jar --spring.config.location=/home/ngw/server/config/application.yml,/home/ngw/server/config/application-ndps-db.yml,/home/ngw/server/config/application-ndps-listener.yml,/home/ngw/server/config/application-ndps-flows.yml,/home/ngw/server/config/application-ndps-registry.yml -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -Dfile.encoding=UTF-8 -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=172.22.14.16 -Dcom.sun.management.jmxremote.port=3635 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Xdebug -Xrunjdwp:transport=dt_socket,address=6666,server=y,suspend=n -Xms2048m -Xmx4096m -Xss256k
4824 sun.tools.jps.Jps -vlm -Dapplication.home=/home/ngw/jdk -Xms8m

ngw@node01:/home/ngw/server/shell> jinfo -flags 4676
Attaching to process ID 4676, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.144-b01
Non-default VM flags: -XX:CICompilerCount=3 -XX:InitialHeapSize=2147483648 -XX:+ManagementServer -XX:MaxHeapSize=4294967296 -XX:MaxNewSize=1431306240 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=715653120 -XX:OldSize=1431830528 -XX:ThreadStackSize=256 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
Command line:  -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=172.22.14.16 -Dcom.sun.management.jmxremote.port=3635 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Xdebug -Xrunjdwp:transport=dt_socket,address=6666,server=y,suspend=n -Xms2048m -Xmx4096m -Xss256k

```



### 新生代老年代大小异常

上面发现的  young/old gen capacity问题，研究发现应是因为使用默认垃圾收集器`-XX:+UseParallelGC`，它默认开启`-XX:+UseAdaptiveSizePolicy`。其动态的调整了survivor/eden young/old的占比导致的。

AdaptiveSizePolicy是JVM GC Ergonomics 自适应调节的一部分

每次gc后会重新计算Eden、From和To区的大小，计算依据是GC过程中统计的GC时间、吞吐量、内存占用量。此policy的目标

- pause goal: 应用达到预期的GC暂停时间
  - gc停顿时间超过了预期值，会减小内存大小。
- Throughput goal:应用达到预期的吞吐， 正常运行时间/(正常运行时间+GC耗时)
  - 吞吐小于预期，理论上会增大内存
- Minimum footprint：尽可能小的内存占用量
  - 如果达到了前两个目标，则尝试减小内存，以减少内存消耗

上述内存，oracle原文里描述为generations，应该表示old young eden survivor的大小都有可能被调整。即使SurvivorRatio的默认值是8，年轻带三个区域间的比例依然会变动。 同理默认NewRatio 2也不固定。



The Parallel scavenge collector performs better when used with the -XX:+UseAdaptiveSizePolicy. This automatically sizes the young generation and chooses an optimum survivor ratio to maximize performance. The parallel scavenge collector should always be used with the -XX:UseAdaptiveSizePolicy.

https://www.oracle.com/technical-resources/articles/javame/garbagecollection2.html

https://docs.oracle.com/javase/7/docs/technotes/guides/vm/gc-ergonomics.html



#### 解决办法

1. 显式设置 `-XX:SurvivorRatio=8`，`-XX:NewRatio=2` 可以固定比值；
2. 使用CMS，`-XX:+UseConcMarkSweepGC`，它默认不使用AdaptiveSizePolicy。 
   - oracle 指出parallel scavenge 应当一直和 AdaptiveSizePolicy同时使用，不能单独关闭。
3. 进一步分析，从代码层面找出导致老年代过大的原因，尝试避免问题出现。

```shell
ngw@node01:/home/ngw> jstat -gc -t 4676
Timestamp        S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
         3276.0 87040.0 87040.0 67852.8  0.0   1067520.0 153010.1 1858048.0   35118.1   77696.0 74876.1 9856.0 9316.2      4    0.371   3      0.336    0.706
```



#### 额外收获

在Garbage Collector Ergonomics https://docs.oracle.com/javase/7/docs/technotes/guides/vm/gc-ergonomics.html 中同时发现了未指定的情况下默认initial / max heap值的计算方式。

**initial heap size**

Larger of 1/64th of the machine's physical memory on the machine or some reasonable minimum. 

**maximum heap size**

Smaller of 1/4th of the physical memory or 1GB.

实验机器的物理内存为8G，因此有InitialHeapSize 124M 、MaxHeapSize 1,956M。