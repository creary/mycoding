package com.yf.test.designpattern.effective;

public class Effect7 {
/*一. 基本概念
1. 所谓的终结方法其实是指finalize()。
2. Java的垃圾回收机制只负责内存相关清理，其他资源的清理（释放文件、释放DB连接）需要程序员手动完成。
3. 调用System.gc() 只是建议JVM执行垃圾回收（GC），但什么时候执行、是否要执行由JVM决定。
4. 用户可以自己调用对象的finalize方法，但是这种调用是正常的方法调用，和对象的销毁过程无关。
5. 如果一个类实现了finalize方法，那么当JVM执行垃圾回收的时候，该方法一定会被调用。
public class C {  
    public static void main(String[] args) {  
        A a = new A();  
        a.b = new B();  
        a = null;  
        System.gc();  
    }  
}  
  
class A {  
    B b;  
  
    public void finalize() {  
        System.out.println("method A.finalize at " + System.nanoTime());  
    }  
}  
  
class B {  
    public void finalize() {  
        System.out.println("method B.finalize at " + System.nanoTime());  
    }  
}  

二. finalize的执行过程
当对象不可达时，GC会判断该对象是否覆盖了finalize方法，如没有覆盖则直接将其回收，否则，若对象未执行过finalize方法，将其放入F-Queue队列，由一低优先级线程执行该队列中对象的finalize方法。执行finalize方法完后，GC会再次判断该对象是否可达，若不可达则进行回收。否则对象“复活”。

三. JDK中代码示例
为防止用户忘记关闭资源，JDK中FileInputStream类中覆盖了finalize方法：
protected void finalize() throws IOException {  
    if ((fd != null) &&  (fd != FileDescriptor.in)) {  
        close();  
    }  
}  

四. 何时使用finalize
1. 尽量少用finalize，最好由系统管理，我们可以定义其他的方法来释放非内存资源。
2. 如果一定要用，那么可以参考FileInputStream类，在finalize检查费内存资源是否释放。*/
}
