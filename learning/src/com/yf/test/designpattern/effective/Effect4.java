package com.yf.test.designpattern.effective;

public class Effect4 {
/*一、背景
对于有些工具类如java.lang.Math、java.util.Arrays等，它们只包含静态方法和静态域字段，对于这样的类实例化没有什么任何意义。但在实际使用中，如果不加任何特殊处理，这样的类还是可以像其他类一样可以被实例化。

二、处理办法
将构造函数设置为private，这样外部类无法实例化该类，同时在这个私有的构造函数中直接抛出异常，从而避免在类的内部实例化。
[java] view plaincopy
public class UtilityClass {  
    // 私有的构造器，内部实例化会抛出异常  
    private UtilityClass() {  
        throw new AssertionError();  
    }  
}  
三、好处和弊端
这样定义之后，类在内部和外部都不能被实例化了，否则会发生编译错误或抛出异常，但副作用是这个类将不能被子类化了。*/
}
