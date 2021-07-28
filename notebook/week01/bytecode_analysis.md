自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运算，if 和 for，然后自己分析一下对应的字节码

## 代码

```java
package week01;

public class Hello {
    public static void main(String[] args) {
        int tmp = 10;
        tmp += 1;
        tmp -= 2;
        tmp /= 3;
        tmp *= 4;
        for (int i = 0; i < 5; i++) {
            tmp++;
        }
        if (tmp / 2 == 0)
            System.out.println("even");
        else
            System.out.println("odd");
    }
}
```

## 编译和反编译

```shell
➜  week01 ls
Hello.java
➜  week01 javac -g Hello.java 
➜  week01 javap -c -verbose Hello
```

javac

- javac 不指定 -d 参数编译后生成的 .class 文件默认和源代码在同一个目录。 

- javac 工具默认开启了优化功能, 生成的字节码中没有局部变量表(LocalVariableTable)，相当于局部变量名称被擦除。如果需要这些调试信息, 在编译时请加上 `-g` 选项。

javap

- 使用 javap 工具来执行反编译, 获取字节码清单
- 使用**包名**或者**相对路径**都可以反编译成功
- 在反编译 class 时，指定 -verbose 选项, 则会输出附加信息 。主要是Constant pool 常量池
  - 常量池大多数时候指的是运行时常量池 。但运行时常量池里面的常量是从哪里来的呢? 主要就是由 class 文件中的常量池结构体组成的



## Bytecode

### Class info &Constant pool 

```shell
Warning: Binary file Hello contains week01.Hello
Classfile /Users/fanlu/workspace/java/geekbang-java/src/main/java/week01/Hello.class
  Last modified Apr 28, 2021; size 692 bytes
  MD5 checksum d55f5e40e759b89fa73594359dcbdab0
  Compiled from "Hello.java"
public class week01.Hello
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#25         // java/lang/Object."<init>":()V
   #2 = Fieldref           #26.#27        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #28            // even
   #4 = Methodref          #29.#30        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = String             #31            // odd
   #6 = Class              #32            // week01/Hello
   #7 = Class              #33            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #13 = Utf8               this
  #14 = Utf8               Lweek01/Hello;
  #15 = Utf8               main
  #16 = Utf8               ([Ljava/lang/String;)V
  #17 = Utf8               i
  #18 = Utf8               I
  #19 = Utf8               args
  #20 = Utf8               [Ljava/lang/String;
  #21 = Utf8               tmp
  #22 = Utf8               StackMapTable
  #23 = Utf8               SourceFile
  #24 = Utf8               Hello.java
  #25 = NameAndType        #8:#9          // "<init>":()V
  #26 = Class              #34            // java/lang/System
  #27 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
  #28 = Utf8               even
  #29 = Class              #37            // java/io/PrintStream
  #30 = NameAndType        #38:#39        // println:(Ljava/lang/String;)V
  #31 = Utf8               odd
  #32 = Utf8               week01/Hello
  #33 = Utf8               java/lang/Object
  #34 = Utf8               java/lang/System
  #35 = Utf8               out
  #36 = Utf8               Ljava/io/PrintStream;
  #37 = Utf8               java/io/PrintStream
  #38 = Utf8               println
  #39 = Utf8               (Ljava/lang/String;)V
```

JDK8

默认的无参构造函数是由Java编译器生成的， 而不是运行时JVM生成。

指令后面使用了 #1, #2, #3 这样的编号，就是对常量池的引用。

常量池中使用特殊字符串：字段和方法的描述符



`#1` 常量编号，`=` 等号分隔符，`Methodref` 表明这个常量指向的是一个方法

- 类指向的 #7 
  - #7  指向 Class              #33 
  - #33  指向 Unicode字符 java/lang/Object
  - 即类指向 Object类
- 方法签 名指向的#25
  - #25 = NameAndType        #8:#9 
    -  #8 = Utf8               <init>
    - #9 = Utf8               ()V
    - 即指向"<init>":()V，构造函数，返回参数为空。

` #2 = Fieldref           #26.#27 `

-  `#26 = Class              #34`   类的全限定名是 java/lang/System
- `#27 = NameAndType        #35:#36`        out:Ljava/io/PrintStream;
  - L表示对象类型



### 常量池中出现的Attributes

参考https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html

```less
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #22 = Utf8               StackMapTable
  #23 = Utf8               SourceFile
```

*Attributes* are used in the `ClassFile`, `field_info`, `method_info`, and `Code_attribute` structures of the `class` file format 

All attributes have the following general format:

```java
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
```

- A `Code` variable-length, attribute contains the Java Virtual Machine instructions and auxiliary information for a single method, instance initialization method ([§2.9](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html#jvms-2.9)), or class or interface initialization method ([§2.9](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html#jvms-2.9)). Every Java Virtual Machine implementation must recognize `Code` attributes. If the method is either `native` or `abstract`, its `method_info` structure must not have a `Code` attribute. Otherwise, its `method_info` structure must have exactly one `Code` attribute.

- `LineNumberTable` variable-length,attribute is an optional variable-length attribute in the `attributes` table of a `Code` attribute. It may be used by debuggers to **determine which part of the `code` array corresponds to a given line number in the original source file**.
- `LocalVariableTable` variable-length attribute, similar to LineNumberTable, optional determine the **value of a given local variable** during the execution of a method.
- `StackMapTable `, variable-length, attribute is used during the **process of verification** by type checking, at most one `SourceFile` attribute in the `attributes` table of a `ClassFile` structure.
- `SourceFile` attribute is an optional fixed-length attribute in the `attributes` table of a `ClassFile` structure



### 构造函数方法体

```java
{
  public week01.Hello();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lweek01/Hello;
```





### main函数方法体

```less
  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: bipush        10    ## 把byte 10压入操作栈
         2: istore_1			      ## 写到本地变量LocalVariableTable表位置1 即tmp的位置
         3: iinc          1, 1  ## 本地变量表1号元素加1 iinc index const
         6: iinc          1, -2 ## 本地变量表1号元素加 -2 
         9: iload_1             ## 取本地变量表位置1压入栈中
        10: iconst_3						## 把3放入操作数栈的栈顶
        11: idiv								## int 除法
        12: istore_1						## 写到本地变量LocalVariableTable表位置1
        13: iload_1             ## 取本地变量表位置1压入栈中
        14: iconst_4						## 把4放入操作数栈的栈顶
        15: imul								## int 乘法
        16: istore_1						## 写到本地变量LocalVariableTable表位置1
        17: iconst_0						## 把0放入操作数栈的栈顶
        18: istore_2						## （把0）写到本地变量LocalVariableTable表位置2，即i
        19: iload_2							## 读 i
        20: iconst_5						## 把5放入操作数栈的栈顶
        21: if_icmpge     33   ##  if, integer, compare, great equal如果前面的操作数大于等于另外的操作数，就跳转到33
        24: iinc          1, 1	## tmp++
        27: iinc          2, 1	## i++
        30: goto          19		## 循环
        33: iload_1							
        34: iconst_2	
        35: idiv								
        36: ifne          50		## ifne succeeds if and only if value ≠ 0 不等于0跳到50
        39: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream; ##value of the class or interface field is fetched and pushed onto the operand stack.
        42: ldc           #3                  // String even ## Push item from run-time constant pool
        44: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        47: goto          58
        50: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        53: ldc           #5                  // String odd
        55: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        58: return
      LineNumberTable:  ## 源代码行：调用指令序号  源代码和字节码的行对应关系
        line 5: 0
        line 6: 3
        line 7: 6
        line 8: 9
        line 9: 13
        line 10: 17
        line 11: 24
        line 10: 27
        line 13: 33
        line 14: 39
        line 16: 50
        line 17: 58
      LocalVariableTable:  ## javac -g 才会带上，否则会抹除，本地变量表对程序执行本身没有影响
        Start  Length  Slot  Name   Signature
           19      14     2     i   I
            0      59     0  args   [Ljava/lang/String;
            3      56     1   tmp   I
      StackMapTable: number_of_entries = 4
        frame_type = 253 /* append */
          offset_delta = 19
          locals = [ int, int ]
        frame_type = 250 /* chop */
          offset_delta = 13
        frame_type = 16 /* same */
        frame_type = 7 /* same */
}
SourceFile: "Hello.java"
```

参考

https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-7.html

https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html

```
iff = "if and only if"


ifeq succeeds if and only if value = 0
ifne succeeds if and only if value ≠ 0
iflt succeeds if and only if value < 0
ifle succeeds if and only if value ≤ 0
ifgt succeeds if and only if value > 0
ifge succeeds if and only if value ≥ 0
ifnonnull
ifnull
```

