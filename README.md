# geekbang-java

Fanlu
G20190379010026



不依赖工程的代码存放在根目录的src下 

- https://github.com/Fanlu91/geekbang-java/tree/master/src/main/java

其他则各自形成独立的工程



## Week01

**1.（选做）**自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。

https://github.com/Fanlu91/geekbang-java/notebook/week01/bytecode_analysis.md

**2.（必做）**自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

https://github.com/Fanlu91/geekbang-java/blob/master/src/main/java/week01/classloader/FanluClassloader.java

**3.（必做）**画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

https://github.com/Fanlu91/geekbang-java/notebook/week01/JVM%E5%86%85%E5%AD%98%E5%8F%82%E6%95%B0.png

**4.（选做）**检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。

https://github.com/Fanlu91/geekbang-java/notebook/week01/jvm_usage_analysis.md

**注意：**如果没有线上系统，可以自己 run 一个 web/java 项目。

**5.（选做）**本机使用 G1 GC 启动一个程序，仿照课上案例分析一下 JVM 情况。



## Week02

**1.（选做）**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

 https://github.com/Fanlu91/geekbang-java/notebook/week02/GCLogAnalysis.md

**2.（选做）**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

https://github.com/Fanlu91/geekbang-java/notebook/week02/gateway_server_wrk_test.md

**3.（选做）**如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。



**4.（必做）**根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

https://github.com/Fanlu91/geekbang-java/notebook/week02/GC_Heap_summary.md

**5.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。

https://github.com/Fanlu91/geekbang-java/notebook/week02/jvm_interview_questions.md

**6.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [ http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub

https://github.com/Fanlu91/geekbang-java/blob/master/src/main/java/week02/OkHttpDemo.java

## Week03

1.（必做）整合你上次作业的 httpclient/okhttp；

https://github.com/Fanlu91/geekbang-java/tree/master/nio02/src/main/java/client/OkHttpDemo.java

2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）

https://github.com/Fanlu91/geekbang-java/tree/master/nio02/src/main/java/client/NettyClientDemo.java

https://github.com/Fanlu91/geekbang-java/tree/master/nio02/src/main/java/client/DemoClientHandler.java

3.（必做）实现过滤器。

 https://github.com/Fanlu91/geekbang-java/tree/master/nio02/src/main/java/gateway/filter/ProxyBizFilter.java

4.（选做）实现路由。

https://github.com/Fanlu91/geekbang-java/tree/master/nio02/src/main/java/gateway/router/AlwaysFirstHttpEndpointRouter.java

5.（选做）跑一跑课上的各个例子，加深对多线程的理解

https://github.com/Fanlu91/geekbang-java/tree/master/src/main/resources/week03/ThreadPool.md

6.（选做）完善网关的例子，试着调整其中的线程池参数



## Week04

**1.（选做）**把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。

**2.（必做）**思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。
一个简单的代码参考: [ https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301 ](https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301/src/main/java/java0/conc0303/Homework03.java



https://github.com/Fanlu91/geekbang-java/notebook/week04/02.md

code

https://github.com/Fanlu91/geekbang-java/tree/master/03concurrency/src/main/java/conc0303/Homework03.java



**3.（选做）**列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。

https://github.com/Fanlu91/geekbang-java/notebook/week04/juc.md

**4.（选做）**请思考: 什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪些 因素，对于这些你是怎么理解的?

 https://github.com/Fanlu91/geekbang-java/notebook/week04/04.md

**5.（选做）**请思考: 还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决 办法。

**6.（必做）**把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。

https://github.com/Fanlu91/geekbang-java/notebook/week04/06.md



## Week05

**1.（选做）**使 Java 里的动态代理，实现一个简单的 AOP。

https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/java/io/kimmking/homework05/AopDemo.java

https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/java/io/kimmking/homework05/AopMethods.java

https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/java/io/kimmking/homework05/MySchool.java

https://github.com/Fanlu91/geekbang-java/blob/master/04fx/spring01/src/main/resources/applicationContext.xml



**2.（必做）**写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。

https://github.com/Fanlu91/geekbang-java/notebook/week05/02.md

**3.（选做）**实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。

**4.（选做，会添加到高手附加题）**
4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；

package https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springgateway

4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。

**5.（选做）**总结一下，单例的各种写法，比较它们的优劣。
**6.（选做）**maven/spring 的 profile 机制，都有什么用法？
**7.（选做）**总结 Hibernate 与 MyBatis 的各方面异同点。
**8.（必做）**给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

SpringbootConfigurationClass: 

https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/main/java/com/fanlu/springbootstarter/SpringBootConfiguration.java

Test case: 

https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/test/java/com/fanlu/springbootdemo/StarterTest.java



**9.（选做**）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。

**10.（必做）**研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。

JDBC:

https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/main/java/com/fanlu/jdbc/MysqlJdbc.java

Hikari:

https://github.com/Fanlu91/geekbang-java/tree/master/04fx/springbootdemo/src/main/java/com/fanlu/hikari/HikariApplication.java



## Week06

**1.（选做）**尝试使用 Lambda/Stream/Guava 优化之前作业的代码。

**2.（选做）**尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。

**3.（选做）**根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。

**4.（选做）**根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。

**5.（选做）**基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化

**6.（必做）**基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

https://github.com/Fanlu91/geekbang-java/notebook/week06/create_db.sql



**7.（选做）**尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。

**8.（选做）**基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。

**9.（选做**）尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。

**10.（选做）**模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。

**11.（选做）**对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。



## Week07 

**1.（选做）**用今天课上学习的知识，分析自己系统的 SQL 和表结构
**2.（必做）**按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

https://github.com/Fanlu91/geekbang-java/notebook/week07/02.md

**3.（选做）**按自己设计的表结构，插入 1000 万订单模拟数据，测试不同方式的插入效
**4.（选做）**使用不同的索引或组合，测试不同方式查询效率
**5.（选做）**调整测试数据，使得数据尽量均匀，模拟 1 年时间内的交易，计算一年的销售报表：销售总额，订单数，客单价，每月销售量，前十的商品等等（可以自己设计更多指标）
**6.（选做）**尝试自己做一个 ID 生成器（可以模拟 Seq 或 Snowflake）
**7.（选做）**尝试实现或改造一个非精确分页的程序

**8.（选做）**配置一遍异步复制，半同步复制、组复制
**9.（必做）**读写分离 - 动态切换数据源版本 1.0

https://github.com/Fanlu91/geekbang-java/notebook/week07/09.md

**10.（必做）**读写分离 - 数据库框架版本 2.0

https://github.com/Fanlu91/geekbang-java/notebook/week07/10.md

**11.（选做）**读写分离 - 数据库中间件版本 3.0
**12.（选做）**配置 MHA，模拟 master 宕机
**13.（选做）**配置 MGR，模拟 master 宕机
**14.（选做）**配置 Orchestrator，模拟 master 宕机，演练 UI 调整拓扑结构



### 笔记整理

关系型数据库范式，mysql基础 https://github.com/Fanlu91/geekbang-java/notebook/week07/db_design.md


日志、事务和锁 https://github.com/Fanlu91/geekbang-java/notebook/week07/mysql_log_trx_lock.md


索引 https://github.com/Fanlu91/geekbang-java/notebook/week07/index.md


数据库配置、设计和sql优化 https://github.com/Fanlu91/geekbang-java/notebook/week07/optimization.md


## Week08



**1.（选做）**分析前面作业设计的表，是否可以做垂直拆分。
**2.（必做）**设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

过程 https://github.com/Fanlu91/geekbang-java/notebook/week08/02.md
proxy sharding配置 https://github.com/Fanlu91/geekbang-java/notebook/week08/config-sharding.yaml



**3.（选做）**模拟 1000 万的订单单表数据，迁移到上面作业 2 的分库分表中。
**4.（选做）**重新搭建一套 4 个库各 64 个表的分库分表，将作业 2 中的数据迁移到新分库。

**5.（选做）**列举常见的分布式事务，简单分析其使用场景和优缺点。
**6.（必做）**基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。
**7.（选做）**基于 ShardingSphere narayana XA 实现一个简单的分布式事务 demo。
**8.（选做）**基于 seata 框架实现 TCC 或 AT 模式的分布式事务 demo。
**9.（选做☆）**设计实现一个简单的 XA 分布式事务框架 demo，只需要能管理和调用 2 个 MySQL 的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。
**10.（选做☆）**设计实现一个 TCC 分布式事务框架的简单 Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。
**11.（选做☆）**设计实现一个 AT 分布式事务框架的简单 Demo，仅需要支持根据主键 id 进行的单个删改操作的 SQL 或插入操作的事务。





## Week09

**1.（选做）**实现简单的 Protocol Buffer/Thrift/gRPC(选任一个) 远程调用 demo。
**2.（选做）**实现简单的 WebService-Axis2/CXF 远程调用 demo。
**3.（必做）**改造自定义 RPC 的程序，提交到 GitHub：

- 尝试将服务端写死查找接口实现类变成泛型和反射；
- 尝试将客户端动态代理改成 AOP，添加异常处理；
- 尝试使用 Netty+HTTP 作为 client 端传输方式。

**4.（选做☆☆））**升级自定义 RPC 的程序：

- 尝试使用压测并分析优化 RPC 性能；
- 尝试使用 Netty+TCP 作为两端传输方式；
- 尝试自定义二进制序列化；
- 尝试压测改进后的 RPC 并分析优化，有问题欢迎群里讨论；
- 尝试将 fastjson 改成 xstream；
- 尝试使用字节码生成方式代替服务端反射。

**5.（选做）**按课程第二部分练习各个技术点的应用。
**6.（选做）**按 dubbo-samples 项目的各个 demo 学习具体功能使用。
**7.（必做）**结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:

- 用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
- 用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
- 设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。

**8.（挑战☆☆）**尝试扩展 Dubbo

- 基于上次作业的自定义序列化，实现 Dubbo 的序列化扩展 ;
- 基于上次作业的自定义 RPC，实现 Dubbo 的 RPC 扩展 ;
- 在 Dubbo 的 filter 机制上，实现 REST 权限控制，可参考 dubbox;
- 实现一个自定义 Dubbo 的 Cluster/Loadbalance 扩展，如果一分钟内调用某个服务 / 提供者超过 10 次，则拒绝提供服务直到下一分钟 ;
- 整合 Dubbo+Sentinel，实现限流功能 ;
- 整合 Dubbo 与 Skywalking，实现全链路性能监控。



## Week10

**1.（选做）**rpcfx1.1: 给自定义 RPC 实现简单的分组 (group) 和版本 (version)。
**2.（选做）**rpcfx2.0: 给自定义 RPC 实现：

- 基于 zookeeper 的注册中心，消费者和生产者可以根据注册中心查找可用服务进行调用 (直接选择列表里的最后一个)。
- 当有生产者启动或者下线时，通过 zookeeper 通知并更新各个消费者，使得各个消费者可以调用新生产者或者不调用下线生产者。

**3.（挑战☆）**在 2.0 的基础上继续增强 rpcfx 实现：

- 3.0: 实现基于 zookeeper 的配置中心，消费者和生产者可以根据配置中心配置参数（分组，版本，线程池大小等）。
- 3.1：实现基于 zookeeper 的元数据中心，将服务描述元数据保存到元数据中心。
- 3.2：实现基于 etcd/nacos/apollo 等基座的配置 / 注册 / 元数据中心。

**4.（挑战☆☆）**在 3.2 的基础上继续增强 rpcfx 实现：

- 4.0：实现基于 tag 的简单路由；
- 4.1：实现基于 Random/RoundRobbin 的负载均衡 ;
- 4.2：实现基于 IP 黑名单的简单流控；
- 4.3：完善 RPC 框架里的超时处理，增加重试参数；

**5.（挑战☆☆☆）**在 4.3 的基础上继续增强 rpcfx 实现：

- 5.0：实现利用 HTTP 头跨进程传递 Context 参数（隐式传参）；
- 5.1：实现消费端 mock 一个指定对象的功能（Mock 功能）；
- 5.2：实现消费端可以通过一个泛化接口调用不同服务（泛化调用）；
- 5.3：实现基于 Weight/ConsistentHash 的负载均衡 ;
- 5.4：实现基于单位时间调用次数的流控，可以基于令牌桶等算法；

**6.（挑战☆☆☆☆）**：压测，并分析调优 5.4 版本。

**7.（选做）**进度快的，把前几次课的选做题做做。
**8.（选做）**进度慢的，把前几次课的必做题做做。
**9.（选做）**学霸和课代表，，考虑多做做挑战题:

**10.（挑战☆☆）**对于不断努力前行的少年：

- 把你对技术架构演进的认识，做一个总结。
- 把你对微服务的特点，能解决什么问题，适用于什么场景，总结一下。
- 画一个脑图，总结你能想到的微服务相关技术框架和中间件，想想都有什么作用。
- 思考（☆☆）：微服务架构是否能应用到你最近接触或负责的业务系统，如何引入和应用，困难点在什么地方。
- 研究（☆☆☆）：学习和了解 Spring Cloud 体系，特别是 Netflix 和 Alibaba 套件，画出他们的体系结构图。

## Week11

**Week11 作业题目：**

**1.（选做）**按照课程内容，动手验证 Hibernate 和 Mybatis 缓存。

**2.（选做）**使用 spring 或 guava cache，实现业务数据的查询缓存。

**3.（挑战☆）**编写代码，模拟缓存穿透，击穿，雪崩。

**4.（挑战☆☆）**自己动手设计一个简单的 cache，实现过期策略。

**5.（选做）**命令行下练习操作 Redis 的各种基本数据结构和命令。

**6.（选做）**分别基于 jedis，RedisTemplate，Lettuce，Redission 实现 redis 基本操作的 demo，可以使用 spring-boot 集成上述工具。

**7.（选做）**spring 集成练习:

- 实现 update 方法，配合 @CachePut
- 实现 delete 方法，配合 @CacheEvict
- 将示例中的 spring 集成 Lettuce 改成 jedis 或 redisson

**8.（必做）**基于 Redis 封装分布式数据操作：

- 在 Java 中实现一个简单的分布式锁；
- 在 Java 中实现一个分布式计数器，模拟减库存。

**9.（必做）**基于 Redis 的 PubSub 实现订单异步处理

**10.（挑战☆）**基于其他各类场景，设计并在示例代码中实现简单 demo：

- 实现分数排名或者排行榜；
- 实现全局 ID 生成；
- 基于 Bitmap 实现 id 去重；
- 基于 HLL 实现点击量计数；
- 以 redis 作为数据库，模拟使用 lua 脚本实现前面课程的外汇交易事务。

**11.（挑战☆☆）**升级改造项目：

- 实现 guava cache 的 spring cache 适配；
- 替换 jackson 序列化为 fastjson 或者 fst，kryo；
- 对项目进行分析和性能调优。

**12.（挑战☆☆☆）**以 redis 作为基础实现上个模块的自定义 rpc 的注册中心。



## Week12

**1.（必做）**配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

**2.（选做）**练习示例代码里下列类中的作业题:
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java

**3.（选做☆）**练习 redission 的各种功能。

**4.（选做☆☆）**练习 hazelcast 的各种功能。

**5.（选做☆☆☆）**搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。

**6.（必做）**搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

**7.（选做）**基于数据库的订单表，模拟消息队列处理订单：

- 一个程序往表里写新订单，标记状态为未处理 (status=0);
- 另一个程序每隔 100ms 定时从表里读取所有 status=0 的订单，打印一下订单数据，然后改成完成 status=1；
- （挑战☆）考虑失败重试策略，考虑多个消费程序如何协作。

**8.（选做）**将上述订单处理场景，改成使用 ActiveMQ 发送消息处理模式。

**9.（选做）**使用 java 代码，创建一个 ActiveMQ Broker Server，并测试它。

**10.（挑战☆☆）**搭建 ActiveMQ 的 network 集群和 master-slave 主从结构。

**11.（挑战☆☆☆）**基于 ActiveMQ 的 MQTT 实现简单的聊天功能或者 Android 消息推送。

**12.（挑战☆）**创建一个 RabbitMQ，用 Java 代码实现简单的 AMQP 协议操作。

**13.（挑战☆☆）**搭建 RabbitMQ 集群，重新实现前面的订单处理。

**14.（挑战☆☆☆）**使用 Apache Camel 打通上述 ActiveMQ 集群和 RabbitMQ 集群，实现所有写入到 ActiveMQ 上的一个队列 q24 的消息，自动转发到 RabbitMQ。

**15.（挑战☆☆☆）**压测 ActiveMQ 和 RabbitMQ 的性能。

## Week13

**1.（必做）**搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

**2.（选做）**安装 kafka-manager 工具，监控 kafka 集群状态。

**3.（挑战☆）**演练本课提及的各种生产者和消费者特性。

**4.（挑战☆☆☆）**Kafka 金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用 mq 来实现订单定序，然后将订单发送给撮合模块。

- 收单：请实现一个订单的 rest 接口，能够接收一个订单 Order 对象；
- 定序：将 Order 对象写入到 kafka 集群的 order.usd2cny 队列，要求数据有序并且不丢失；
- 撮合：模拟撮合程序（不需要实现撮合逻辑），从 kafka 获取 order 数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。

**5.（选做）**自己安装和操作 RabbitMQ，RocketMQ，Pulsar，以及 Camel 和 Spring Integration。

**6.（必做）**思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。

**7.（挑战☆☆☆☆☆）**完成所有其他版本的要求。期限一年。