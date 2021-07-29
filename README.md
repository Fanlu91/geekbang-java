# geekbang-java

Fanlu
G20190379010026



## 概览

不依赖工程的代码存放在根目录的src下 

- https://github.com/Fanlu91/geekbang-java/tree/master/src/main/java

其他则各自形成独立的工程



>  部分笔记尤其是汇总性质的，因为涉及到很多课程内容，为避免版权等问题存放在私有仓库中，相关链接仅为方便本人学习、复习使用。



## Week01

#### 字节码、JVM架构、JIT编译、Class文件、类加载器、JVM内存结构和运行时数据区、垃圾回收、JVM优化

https://github.com/Fanlu91/learning/blob/main/java/jvm/JVM_JMM.md

- 5w字有点太长，需要精炼拆分



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注           |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------------- |
| 1    | 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运算，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week01/bytecode_analysis.md | 字节码分析实战 |
| 2    | 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。 | https://github.com/Fanlu91/geekbang-java/blob/master/src/main/java/week01/classloader/FanluClassloader.java |                |
| 3    | 画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week01/JVM%E5%86%85%E5%AD%98%E5%8F%82%E6%95%B0.png |                |
| 4    | 检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week01/jvm_usage_analysis.md |                |
| 5    | 本机使用 G1 GC 启动一个程序，仿照课上案例分析一下 JVM 情况。 | 未做                                                         |                |





## Week02

#### JVM面试问题

https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week02/jvm_interview_questions.md



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注                                                         |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | 使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week02/GCLogAnalysis.md | GC 日志分析实战                                              |
| 2    | 使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week02/gateway_server_wrk_test.md | wrk的使用                                                    |
| 3    | 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。    | 未做                                                         |                                                              |
| 4    | 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week02/GC_Heap_summary.md | 摘取自https://github.com/Fanlu91/learning/blob/main/java/jvm/JVM_JMM.md |
| 5    | 运行课上的例子，以及 Netty 的例子，分析相关现象。            | 未做                                                         |                                                              |
| 6    | 写一段代码，使用 HttpClient 或 OkHttp 访问 [ http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub | https://github.com/Fanlu91/geekbang-java/blob/master/src/main/java/week02/OkHttpDemo.java |                                                              |





## Week03



#### 并发基础（进程和线程）、Java线程状态、Java内存模型、线程安全（竞态条件、原子可见有序、线程安全设计调度模型）、并发编程（ThreadLocal、加锁、线程协作）

https://github.com/Fanlu91/learning/blob/main/java/juc/concurrency.md



#### 锁的特点和分类、synchronized、JUC结构、Locks、原子操作类、其他并发工具（Semaphore、CountDownLatch、CyclicBarrier）

https://github.com/Fanlu91/learning/blob/main/java/juc/lock%26juc.md



#### 线程池、ThreadPoolExecutor、Callable、Future

https://github.com/Fanlu91/learning/blob/main/java/juc/ThreadPool.md



#### 数据类型与集合类、线程安全设计

https://github.com/Fanlu91/learning/blob/main/java/juc/ThreadSafeType.md



#### Socket编程、IO模型、Netty框架、API网关

https://github.com/Fanlu91/learning/blob/main/java/nio/JavaNIO.md

| 编号 | 作业描述                                      | 作业提交                                                     | 备注 |
| ---- | --------------------------------------------- | ------------------------------------------------------------ | ---- |
| 1    | 整合你上次作业的 httpclient/okhttp            | https://github.com/Fanlu91/geekbang-java/tree/master/02nio/src/main/java/client/OkHttpDemo.java |      |
| 2    | 使用 netty 实现后端 http 访问（代替上一步骤） | https://github.com/Fanlu91/geekbang-java/tree/master/02nio/src/main/java/client/NettyClientDemo.java https://github.com/Fanlu91/geekbang-java/tree/master/02nio/src/main/java/client/DemoClientHandler.java |      |
| 3    | 实现过滤器                                    | https://github.com/Fanlu91/geekbang-java/tree/master/02nio/src/main/java/gateway/filter/ProxyBizFilter.java |      |
| 4    | 实现路由                                      | https://github.com/Fanlu91/geekbang-java/tree/master/02nio/src/main/java/gateway/router/AlwaysFirstHttpEndpointRouter.java |      |
| 5    | 跑一跑课上的各个例子，加深对多线程的理解      | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week03/ThreadPool.md |      |
| 6    | 完善网关的例子，试着调整其中的线程池参数      | 未做                                                         |      |





## Week04





| 编号 | 作业描述                                                     | 作业提交                                                     | 备注 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 1    | 把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。 |                                                              |      |
| 2    | 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程，一个简单的代码参考: [ https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301 ] | https://github.com/Fanlu91/geekbang-java/tree/master/03concurrency/src/main/java/conc0303/Homework03.java<br/>https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week04/02.md |      |
| 3    | 列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week04/juc.md |      |
| 4    | 什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪些 因素，对于这些你是怎么理解的? | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week04/04.md |      |
| 5    | 还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决 办法。 | 未做                                                         |      |
| 6    | 把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week04/06.md |      |





## Week05

Spring 需要补的课比较多



#### 架构设计、AOP/IOC基础、Bean核心原理、XML配置原理、Spring操作数据库

https://github.com/Fanlu91/learning/blob/main/Spring/overview.md



#### Proxy、CGlib、Aop使用

https://github.com/Fanlu91/learning/blob/main/Spring/aop.md



#### SpringBoot基础、Spring Starter

https://github.com/Fanlu91/learning/blob/main/Spring/Springboot.md



#### Java、Spring、Springboot 注解

https://github.com/Fanlu91/learning/blob/main/Spring/annotation.md



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注       |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------- |
| 1    | 使 Java 里的动态代理，实现一个简单的 AOP。                   | https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/java/io/kimmking/homework05/AopDemo.java<br/><br/>https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/java/io/kimmking/homework05/AopMethods.java<br/><br/>https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/java/io/kimmking/homework05/MySchool.java<br/><br/>https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/resources/applicationContext.xml |            |
| 2    | 写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week05/02.md |            |
| 3    | 实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。 | 未做                                                         |            |
| 4    | 4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；<br/>4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；<br/>4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；<br/>4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；<br/>4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。 | https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springgateway | 仅完成开头 |
| 5    | 总结一下，单例的各种写法，比较它们的优劣。                   | 未做                                                         |            |
| 6    | maven/spring 的 profile 机制，都有什么用法                   | 未做                                                         |            |
| 7    | 总结 Hibernate 与 MyBatis 的各方面异同点。                   | 未做                                                         |            |
| 8    | 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。 | SpringbootConfigurationClass: <br/>https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/main/java/com/fanlu/springbootstarter/SpringBootConfiguration.java<br/><br/>Test case: <br/>https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/test/java/com/fanlu/springbootdemo/StarterTest.java |            |
| 9    | 学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。 | 未做                                                         |            |
| 10   | 研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：<br/>1）使用 JDBC 原生接口，实现数据库的增删改查操作。<br/>2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。<br/>3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。 | JDBC:<br/><br/>https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/main/java/com/fanlu/jdbc/MysqlJdbc.java<br/><br/>Hikari:<br/><br/>https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/main/java/com/fanlu/hikari/HikariApplication.java |            |
|      | 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。 |                                                              |            |
|      | 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。 |                                                              |            |
|      | 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。 |                                                              |            |



## Week06

#### Guava

https://github.com/Fanlu91/learning/blob/main/java/library/guava.md

#### Lombok

https://github.com/Fanlu91/learning/blob/main/java/library/lombok.md



#### 好代码的标准、设计原则、面向对象、创建型设计模式、结构型设计模式、行为型设计模式

https://github.com/Fanlu91/learning/blob/main/designPattern/designpattern.md



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 1    | 尝试使用 Lambda/Stream/Guava 优化之前作业的代码              |                                                              |      |
| 2    | 尝试使用 Lambda/Stream/Guava 优化工作中编码的代码            |                                                              |      |
| 3    | 根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。 |                                                              |      |
| 4    | 根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。 |                                                              |      |
| 5    | 基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化 |                                                              |      |
| 6    | 基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week06/create_db.sql |      |
| 7    | 尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。 |                                                              |      |
| 8    | 基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。 |                                                              |      |
| 9    | 尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。   |                                                              |      |
| 10   | 模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。 |                                                              |      |
| 11   | 对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。 |                                                              |      |
|      |                                                              |                                                              |      |



## Week07 



#### 关系型数据库范式，mysql基础

https://github.com/Fanlu91/learning/blob/main/mysql/db_design.md

#### 日志、事务和锁

https://github.com/Fanlu91/learning/blob/main/mysql/mysql_log_trx_lock.md

#### 索引

https://github.com/Fanlu91/learning/blob/main/mysql/index.md

#### 数据库配置、设计和sql优化

https://github.com/Fanlu91/learning/blob/main/mysql/optimization.md



#### 主从、高可用、读写分离、shardingsphere

https://github.com/Fanlu91/learning/blob/main/mysql/master_slave.md



#### SQL相关

https://github.com/Fanlu91/learning/blob/main/mysql/sql.md



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 1    | 分析自己系统的 SQL 和表结构                                  |                                                              |      |
| 2    | 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week07/02.md |      |
| 3    | 按自己设计的表结构，插入 1000 万订单模拟数据，测试不同方式的插入效 |                                                              |      |
| 4    | 使用不同的索引或组合，测试不同方式查询效率                   |                                                              |      |
| 5    | 调整测试数据，使得数据尽量均匀，模拟 1 年时间内的交易，计算一年的销售报表：销售总额，订单数，客单价，每月销售量，前十的商品等等（可以自己设计更多指标） |                                                              |      |
| 6    | 尝试自己做一个 ID 生成器（可以模拟 Seq 或 Snowflake）        |                                                              |      |
| 7    | 尝试实现或改造一个非精确分页的程序                           |                                                              |      |
| 8    | 配置一遍异步复制，半同步复制、组复制                         |                                                              |      |
| 9    | 读写分离 - 动态切换数据源版本 1.0                            | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week07/09.md |      |
| 10   | 读写分离 - 数据库框架版本 2.0                                | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week07/10.md |      |
| 11   | 读写分离 - 数据库中间件版本 3.0                              |                                                              |      |
| 12   | 配置 MHA，模拟 master 宕机                                   |                                                              |      |
| 13   | 配置 MGR，模拟 master 宕机                                   |                                                              |      |
| 14   | 配置 Orchestrator，模拟 master 宕机，演练 UI 调整拓扑结构    |                                                              |      |
| 15   |                                                              |                                                              |      |

## Week08

#### 分布式事务基础、XA协议、BASE协议

https://github.com/Fanlu91/learning/blob/main/mysql/distributed_transaction.md



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 1    | 分析前面作业设计的表，是否可以做垂直拆分。                   |                                                              |      |
| 2    | 设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。 | 过程 https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week08/02.md<br/>proxy sharding配置 https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week08/config-sharding.yaml |      |
| 3    | 模拟 1000 万的订单单表数据，迁移到上面作业 2 的分库分表中。  |                                                              |      |
| 4    | 重新搭建一套 4 个库各 64 个表的分库分表，将作业 2 中的数据迁移到新分库。 |                                                              |      |
| 5    | 列举常见的分布式事务，简单分析其使用场景和优缺点。           |                                                              |      |
| 6    | 基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。 |                                                              |      |
| 7    | 基于 ShardingSphere narayana XA 实现一个简单的分布式事务 demo。 |                                                              |      |
| 8    | 基于 seata 框架实现 TCC 或 AT 模式的分布式事务 demo。        |                                                              |      |
| 9    | 设计实现一个简单的 XA 分布式事务框架 demo，只需要能管理和调用 2 个 MySQL 的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。 |                                                              |      |
| 10   | 设计实现一个 TCC 分布式事务框架的简单 Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。 |                                                              |      |
| 11   | 设计实现一个 AT 分布式事务框架的简单 Demo，仅需要支持根据主键 id 进行的单个删改操作的 SQL 或插入操作的事务。 |                                                              |      |





## Week09

#### RPC原理、分布式服务化、Dubbo框架

https://github.com/Fanlu91/learning/blob/main/dubbo/base.md

| 编号 | 作业描述                                                     | 作业提交                                                     | 备注 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 1    | 实现简单的 Protocol Buffer/Thrift/gRPC(选任一个) 远程调用 demo。 |                                                              |      |
| 2    | 实现简单的 WebService-Axis2/CXF 远程调用 demo。              |                                                              |      |
| 3    | 改造自定义 RPC 的程序，提交到 GitHub：<br/><br/>- 尝试将服务端写死查找接口实现类变成泛型和反射；<br/>- 尝试将客户端动态代理改成 AOP，添加异常处理；<br/>- 尝试使用 Netty+HTTP 作为 client 端传输方式。 | https://github.com/Fanlu91/geekbang-java/tree/master/notebook/week09/03.md<br/><br/>https://github.com/Fanlu91/geekbang-java/07rpc/rpc01 |      |
| 4    | 升级自定义 RPC 的程序：<br/><br/>- 尝试使用压测并分析优化 RPC 性能；<br/>- 尝试使用 Netty+TCP 作为两端传输方式；<br/>- 尝试自定义二进制序列化；<br/>- 尝试压测改进后的 RPC 并分析优化，有问题欢迎群里讨论；<br/>- 尝试将 fastjson 改成 xstream；<br/>- 尝试使用字节码生成方式代替服务端反射。 |                                                              |      |
| 5    | 按课程第二部分练习各个技术点的应用。                         |                                                              |      |
| 6    | 按 dubbo-samples 项目的各个 demo 学习具体功能使用。          |                                                              |      |
| 7    | 结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:<br/><br/>- 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;<br/>- 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;<br/>- 设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。 |                                                              |      |
| 8    | 尝试扩展 Dubbo<br/><br/>- 基于上次作业的自定义序列化，实现 Dubbo 的序列化扩展 ;<br/>- 基于上次作业的自定义 RPC，实现 Dubbo 的 RPC 扩展 ;<br/>- 在 Dubbo 的 filter 机制上，实现 REST 权限控制，可参考 dubbox;<br/>- 实现一个自定义 Dubbo 的 Cluster/Loadbalance 扩展，如果一分钟内调用某个服务 / 提供者超过 10 次，则拒绝提供服务直到下一分钟 ;<br/>- 整合 Dubbo+Sentinel，实现限流功能 ;<br/>- 整合 Dubbo 与 Skywalking，实现全链路性能监控。 |                                                              |      |



## Week10



| 编号 | 作业描述                                                     | 作业提交 | 备注 |
| ---- | ------------------------------------------------------------ | -------- | ---- |
| 1    | rpcfx1.1: 给自定义 RPC 实现简单的分组 (group) 和版本 (version)。 |          |      |
| 2    | rpcfx2.0: 给自定义 RPC 实现：<br/><br/>- 基于 zookeeper 的注册中心，消费者和生产者可以根据注册中心查找可用服务进行调用 (直接选择列表里的最后一个)。<br/>- 当有生产者启动或者下线时，通过 zookeeper 通知并更新各个消费者，使得各个消费者可以调用新生产者或者不调用下线生产者。 |          |      |
| 3    | 在 2.0 的基础上继续增强 rpcfx 实现：<br/><br/>- 3.0: 实现基于 zookeeper 的配置中心，消费者和生产者可以根据配置中心配置参数（分组，版本，线程池大小等）。<br/>- 3.1：实现基于 zookeeper 的元数据中心，将服务描述元数据保存到元数据中心。<br/>- 3.2：实现基于 etcd/nacos/apollo 等基座的配置 / 注册 / 元数据中心。 |          |      |
| 4    | 在 3.2 的基础上继续增强 rpcfx 实现：<br/><br/>- 4.0：实现基于 tag 的简单路由；<br/>- 4.1：实现基于 Random/RoundRobbin 的负载均衡 ;<br/>- 4.2：实现基于 IP 黑名单的简单流控；<br/>- 4.3：完善 RPC 框架里的超时处理，增加重试参数； |          |      |
| 5    | 在 4.3 的基础上继续增强 rpcfx 实现：<br/><br/>- 5.0：实现利用 HTTP 头跨进程传递 Context 参数（隐式传参）；<br/>- 5.1：实现消费端 mock 一个指定对象的功能（Mock 功能）；<br/>- 5.2：实现消费端可以通过一个泛化接口调用不同服务（泛化调用）；<br/>- 5.3：实现基于 Weight/ConsistentHash 的负载均衡 ;<br/>- 5.4：实现基于单位时间调用次数的流控，可以基于令牌桶等算法； |          |      |
| 6    | 压测，并分析调优 5.4 版本。                                  |          |      |
| 7    | 无                                                           |          |      |
| 8    | 无                                                           |          |      |
| 9    | 无                                                           |          |      |
| 10   | - 把你对技术架构演进的认识，做一个总结。<br/>- 把你对微服务的特点，能解决什么问题，适用于什么场景，总结一下。<br/>- 画一个脑图，总结你能想到的微服务相关技术框架和中间件，想想都有什么作用。<br/>- 思考（☆☆）：微服务架构是否能应用到你最近接触或负责的业务系统，如何引入和应用，困难点在什么地方。<br/>- 研究（☆☆☆）：学习和了解 Spring Cloud 体系，特别是 Netflix 和 Alibaba 套件，画出他们的体系结构图。 |          |      |



## Week11

#### 缓存概念、实践、策略、常见问题

https://github.com/Fanlu91/learning/blob/main/redis/cache.md



#### Redis基本功能、应用场景、java客户端、Spring整合、高级功能、性能优化

https://github.com/Fanlu91/learning/blob/main/redis/redis.md



| 编号 | 作业描述                                                     | 作业提交                                                     | 备注 |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| 1    | 按照课程内容，动手验证 Hibernate 和 Mybatis 缓存。           | https://github.com/Fanlu91/geekbang-java/blob/master/notebook/week11/01.md |      |
| 2    | 使用 spring 或 guava cache，实现业务数据的查询缓存。         | https://github.com/Fanlu91/geekbang-java/blob/master/notebook/week11/02.md |      |
| 3    | 编写代码，模拟缓存穿透，击穿，雪崩。                         |                                                              |      |
| 4    | 自己动手设计一个简单的 cache，实现过期策略。                 |                                                              |      |
| 5    | 命令行下练习操作 Redis 的各种基本数据结构和命令。            |                                                              |      |
| 6    | 分别基于 jedis，RedisTemplate，Lettuce，Redission 实现 redis 基本操作的 demo，可以使用 spring-boot 集成上述工具。 |                                                              |      |
| 7    | spring 集成练习:<br/>- 实现 update 方法，配合 @CachePut<br/>- 实现 delete 方法，配合 @CacheEvict<br/>- 将示例中的 spring 集成 Lettuce 改成 jedis 或 redisson |                                                              |      |
| 8    | 基于 Redis 封装分布式数据操作：<br/>- 在 Java 中实现一个简单的分布式锁；<br/>- 在 Java 中实现一个分布式计数器，模拟减库存。 | https://github.com/Fanlu91/geekbang-java/blob/master/notebook/week11/08.md |      |
| 9    | 基于 Redis 的 PubSub 实现订单异步处理                        |                                                              |      |
| 10   | 基于其他各类场景，设计并在示例代码中实现简单 demo：<br/><br/>- 实现分数排名或者排行榜；<br/>- 实现全局 ID 生成；<br/>- 基于 Bitmap 实现 id 去重；<br/>- 基于 HLL 实现点击量计数；<br/>- 以 redis 作为数据库，模拟使用 lua 脚本实现前面课程的外汇交易事务。 |                                                              |      |
| 11   | 升级改造项目：<br/><br/>- 实现 guava cache 的 spring cache 适配；<br/>- 替换 jackson 序列化为 fastjson 或者 fst，kryo；<br/>- 对项目进行分析和性能调优。 |                                                              |      |
| 12   | 以 redis 作为基础实现上个模块的自定义 rpc 的注册中心。       |                                                              |      |



## Week12



| 编号 | 作业描述                                                     | 作业提交 | 备注 |
| ---- | ------------------------------------------------------------ | -------- | ---- |
| 1    | 配置 redis 的主从复制，sentinel 高可用，Cluster 集群。       | 未做     |      |
| 2    | 练习示例代码里下列类中的作业题:<br/>08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java |          |      |
| 3    | 练习 redission 的各种功能。                                  |          |      |
| 4    | 练习 hazelcast 的各种功能。                                  |          |      |
| 5    | 搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。 |          |      |
| 6    | 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。 | 未做     |      |
| 7    | 基于数据库的订单表，模拟消息队列处理订单：<br/><br/>- 一个程序往表里写新订单，标记状态为未处理 (status=0);<br/>- 另一个程序每隔 100ms 定时从表里读取所有 status=0 的订单，打印一下订单数据，然后改成完成 status=1；<br/>- （挑战☆）考虑失败重试策略，考虑多个消费程序如何协作。 |          |      |
| 8    | 将上述订单处理场景，改成使用 ActiveMQ 发送消息处理模式。     |          |      |
| 9    | 使用 java 代码，创建一个 ActiveMQ Broker Server，并测试它。  |          |      |
| 10   | 搭建 ActiveMQ 的 network 集群和 master-slave 主从结构。      |          |      |
| 11   | 基于 ActiveMQ 的 MQTT 实现简单的聊天功能或者 Android 消息推送。 |          |      |
| 12   | 创建一个 RabbitMQ，用 Java 代码实现简单的 AMQP 协议操作。    |          |      |
| 13   | 搭建 RabbitMQ 集群，重新实现前面的订单处理。                 |          |      |
| 14   | 使用 Apache Camel 打通上述 ActiveMQ 集群和 RabbitMQ 集群，实现所有写入到 ActiveMQ 上的一个队列 q24 的消息，自动转发到 RabbitMQ。 |          |      |
| 15   | 压测 ActiveMQ 和 RabbitMQ 的性能。                           |          |      |



## Week13



| 编号 | 作业描述                                                     | 作业提交 | 备注 |
| ---- | ------------------------------------------------------------ | -------- | ---- |
| 1    | 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。 | 必做     |      |
| 2    | 安装 kafka-manager 工具，监控 kafka 集群状态。               |          |      |
| 3    | 演练本课提及的各种生产者和消费者特性。                       |          |      |
| 4    | Kafka 金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用 mq 来实现订单定序，然后将订单发送给撮合模块。<br/><br/>- 收单：请实现一个订单的 rest 接口，能够接收一个订单 Order 对象；<br/>- 定序：将 Order 对象写入到 kafka 集群的 order.usd2cny 队列，要求数据有序并且不丢失；<br/>- 撮合：模拟撮合程序（不需要实现撮合逻辑），从 kafka 获取 order 数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。 |          |      |
| 5    | 自己安装和操作 RabbitMQ，RocketMQ，Pulsar，以及 Camel 和 Spring Integration。 |          |      |
| 6    | 思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。 | 未做     |      |
| 7    | 完成所有其他版本的要求。期限一年。                           |          |      |





## Week13

| 编号 | 作业描述                                                     | 作业提交 | 备注 |
| ---- | ------------------------------------------------------------ | -------- | ---- |
| 1    | 思考一下自己负责的系统，或者做过的系统，能否描述清楚其架构。 |          |      |
| 2    | 考虑一下，如果让你做一个针对双十一，某东某宝半价抢 100 个 IPhone 的活动系统，你该如何考虑，从什么地方入手。 |          |      |
| 3    | 可以自行学习以下参考书的一两本。<br/>推荐架构书籍：<br/>《软件架构》Mourad Chabane Oussalah<br/>《架构实战 - 软件架构设计的过程》Peter EeLes<br/>《软件系统架构 - 使用视点和视角与利益相关者合作》Nick Rozanski<br/>《企业 IT 架构转型之道》<br/>《大型网站技术架构演进与性能优化》<br/>《银行信息系统架构》<br/>《商业银行分布式架构实践》 |          |      |
| 4    | 针对课上讲解的内容，自己动手设计一个高并发的秒杀系统，讲架构图， 设计文档等，提交到 GitHub。 |          |      |
| 5    | 针对自己工作的系统，或者自己思考的复杂场景，做系统性的架构设计。 |          |      |

