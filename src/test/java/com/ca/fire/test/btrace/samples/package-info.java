package com.ca.fire.test.btrace.samples;

/**
 * AWTEventTracer.java - 通过检测EventQueue.dispatchEvent（）方法演示AWT事件的跟踪。可以通过instanceof检查过滤事件。此示例仅过滤和打印焦点事件。
 * AllLines.java - 演示基于行号的BTrace探测器。可以探测任何类（或类）和行号。当达到指定类的指定行号时，将触发BTrace探测并执行相应的操作。
 * ArgArray.java - 在java.io包中的每个类的每个readXXX方法中打印所有输入参数。在探针规范中演示参数数组访问和多个类/方法匹配。
 * Classload.java - defineClass由任何用户定义的类加载器在每个成功的类加载（返回）上打印堆栈跟踪。
 * CommandArg.java - 演示BTrace命令行参数访问。
 * Deadlock.java - 演示@OnTimer探针和deadlock()内置函数。
 * DTraceInline.java - 演示@DTrace注释，将简单的单行D-script与BTrace程序相关联。
 * DTraceRefDemo.java - 演示@DTraceRef注释以将D-script文件与BTrace程序相关联。此示例将classload.d与其自身相关联。
 * FileTracker.java - 通过探测File{Input/Output}Stream 构造函数入口点打印文件以进行读/写。
 * FinalizeTracker.java - 演示我们可以打印对象的所有字段以及访问（私有）字段（只读）。这在调试/故障排除方案中很有用。此示例打印有关java.io.FileInputStream类的close（）/ finalize（）方法的信息。
 * Histogram.java - 演示使用BTrace地图收集直方图（javax.swing.JComponent 由app创建的对象 - 按子类名称和计数的直方图）。
 * HistogramBean.java - 演示JMX集成。此示例使用@Property 注释公开Map作为MBean属性。
 * HistoOnEvent.java - 演示基于客户端事件获取跟踪输出。在BTrace客户端启动脚本后，按Ctrl-C获取发送事件或退出的菜单。在发送事件时，将打印直方图。这样，客户端可以在需要时“拉”跟踪输出，而不是BTrace代理总是或基于计时器推送跟踪输出。
 * JdbcQueries.java - 演示BTrace聚合工具。此工具类似于 DTrace聚合工具。
 * JInfo.java -演示 printVmArguments()，printProperties()并 printEnv()内置函数。
 * JMap.java - 演示 dumpHeap()内置函数以转储（hprof二进制格式）目标应用程序的堆转储。
 * JStack.java - 演示 jstackAll()内置函数以打印所有线程的堆栈跟踪。
 * LogTracer.java - 演示捕获到实例方法（Logger.log）和打印私有字段值（LogRecord对象）field() 和objectValue()内置函数。
 * MemAlerter.java - 演示@OnLowMememory注释对低内存事件的跟踪。
 * Memory.java - 通过计时器探测器每4秒打印一次内存统计信息。演示内存统计内置函数。
 * MultiClass.java - 演示使用正则表达式clazz 和注释method字段将跟踪代码插入到多个类的多个方法中@OnMethod。
 * NewComponent.java - 跟踪每个java.awt.Component创建并递增计数器并根据计时器打印计数器。
 * OnThrow.java - 每次创建任何异常实例时都会打印异常堆栈跟踪。在大多数情况下，创建后会立即抛出异常。所以，我们得到了投掷点的堆栈跟踪。
 * ProbeExit.java - 演示@OnExit探针和exit(int) 内置函数。
 * Sizeof.java - 演示“sizeof”内置函数，可用于获取（近似）给定Java对象的大小。使用此内置功能可以获得按尺寸方式的直方图等。
 * SocketTracker.java - 打印每个服务器socker创建/绑定和客户端套接字接受。
 * SocketTracker1.java - 类似于SocketTracker.java示例，但此示例使用@OnProbe探针以避免在BTrace程序中使用Sun特定的套接字通道实现类。而是BTO代理将@OnProbe探针映射到@OnMethod探针。
 * SysProp.java - 演示可以探测System类（如java.lang.System）并在action方法中调用BTrace内置函数。
 * SubtypeTracer.java - 演示可以匹配给定超类型的所有子类型。
 * ThreadCounter.java - 演示如何使用BTrace程序中的jvmstat计数器。
 * ThreadCounterBean.java - 演示将BTrace程序公开为具有一个属性的JMX bean（使用@Property注释）。
 * ThreadBean.java - 演示了BTrace [和JMX integratio]的预处理器的使用。
 * ThreadStart.java - 演示从BTrace程序中提升DTrace探测器。另请参见 jthread.d - 关联的D-script。只要跟踪的程序进入java.lang.Thread.start（）方法，此示例就会引发DTrace USDT探测。BTrace程序将JavaThread的名称传递给DTrace。
 * Timers.java - @OnTimer在BTrace程序中演示多个计时器探测器（）。
 * URLTracker.java - 每次URL.openConnection成功返回时打印URL 。该程序也使用jurls.d D-script（显示通过DTrace打开的URL的直方图）。
 * WebServiceTracker.java - 通过指定类和方法级别注释而不是类和方法名称来演示跟踪类和方法。
 */
