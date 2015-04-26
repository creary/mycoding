package com.yf.test.designpattern.effective;

public class Effect6 {
/*一、引言
很多人可能在想这么一个问题：Java有垃圾回收机制，那么还存在内存泄露吗？答案是肯定的，所谓的垃圾回收GC会自动管理内存的回收，而不需要程序员每次都手动释放内存，但是如果存在大量的临时对象在不需要使用时并没有取消对它们的引用，就会吞噬掉大量的内存，很快就会造成内存溢出。

二、Java的垃圾回收机制
Java中的对象是在堆中分配，对象的创建有2中方式：new或者反射。对象的回收是通过垃圾收集器，JVM的垃圾收集器简化了程序员的工作，但是却加重了JVM的工作，这是Java程序运行稍慢的原因之一，因为GC为了能正确释放对象，必须监控每一个对象的运行状态，包括对象的申请、引用、被引用、赋值等，GC都要进行监控，监控对象的状态是为了更加准确、及时地释放对象，而释放对象的根本原则就是该对象不再被引用。

三、Java中的内存泄露
内存泄露的对象有以下两个特点：
① 这些对象是可达的，即在有向图中存在通路可以与其相连。
② 这些对象是无用的，即程序以后都不会再使用这些对象。
[java] view plaincopy
public class Stack {  
    private final static int MAX_ATTRIBUTE = 10;  
    private Object[] arr;  
    private int index = 0;;  
  
    public void push(Object obj) {  
        if (index > 9)  
            throw new IllegalArgumentException();  
        arr[index] = obj;  
        index++;  
    }  
  
    public Stack() {  
        arr = new Object[MAX_ATTRIBUTE];  
    }  
  
    public Object pop() {  
        if (index < 0)  
            throw new IllegalArgumentException();  
        return arr[--index];  
    }  
}  
这个程序那里发生了内存泄露呢？如果一个栈先增长然后收缩，那么从栈中弹出来的对象将不会被当做垃圾回收，即使使用栈的程序不再引用这些对象，它们也不会被回收，因为栈内部维护这对这些对象的过期引用。
[java] view plaincopy
public Object pop() {    
    if (size == 0)    
        throw new EmptyStackException();    
    Object result = elements[--size];    
    elements[size] = null; //Eliminate obsolete reference       
    return result;    
}    
解决办法：只要一个元素被弹出栈，那么就将它的引用置为空，GC就会回收。

四、常见的内存泄露
① 像HashMap、Vector等静态集合类的使用最容易引起内存泄露，因为这些静态变量的生命周期与应用程序一致。
[java] view plaincopy
Vector<Object> vector = new Vector<Object>(10);   
for (int i = 1; i < 100; i++) {  
    Object obj = new Object();   
    vector.add(obj);  
    obj = null;  
}  
把对象放入Vector中，如果仅仅释放引用本身，但Vector仍然引用该对象，那么这个对象对GC来说是不可回收的，如果要回收它，最简单的办事是将Vector对象设为null。

② 在Java程序中，我们经常要和监听器打交道，通常调用诸如addXXXListener（）等方法来增加监听器，但往往忘记删除这些监听器，从而增加了内存泄漏的机会。

③ 使用连接池时，除了要显式地关闭连接，还必须显式地关闭Resultset和Statement对象。否则会造成大量的这些对象无法释放，从而引起内存泄露*/
}
