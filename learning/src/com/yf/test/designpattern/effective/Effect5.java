package com.yf.test.designpattern.effective;

public class Effect5 {
/*一、引子
一般来说，最好能重用对象而不是每次需要的时候就创建一个相同功能的新对象，特别是当对象不可变时，它始终可以被重用的。重用对象对程序性能起到重要作用。

二、重用不可变对象
对于同时提供了静态工厂方法和构造器的不可变类，通常可以使用静态工厂方法而不是构造器，以避免创建不必要的对象。
[java] view plaincopy
Boolean b1 = Boolean.valueOf("test"); // 使用静态工厂方法（good）  
Boolean b2 = new Boolean("test"); // 使用构造器（bad）  

三、重用不会被修改的可变对象
下面的例子业务为：检验某个人是否出生于1946年 ~ 1964年期间。
public class Person {  
    private Date birthDate = new Date();  
  
    public boolean isBabyBoomer() {  
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));  
  
        calendar.set(1946, Calendar.JANUARY, 1, 0, 0, 0);  
        Date boomStart = calendar.getTime();  
  
        calendar.set(1965, Calendar.JANUARY, 1, 0, 0, 0);  
        Date boomEnd = calendar.getTime();  
  
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;  
    }  
}  
public class Person2 {  
    private Date birthDate = new Date();  
    private static Date BOOM_START;  
    private static Date BOOM_END;  
      
    static {  
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));  
          
        calendar.set(1946, Calendar.JANUARY, 1, 0, 0, 0);  
        BOOM_START = calendar.getTime();  
  
        calendar.set(1965, Calendar.JANUARY, 1, 0, 0, 0);   
        BOOM_END = calendar.getTime();  
    }  
  
    public boolean isBabyBoomer() {  
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;  
    }  
}  
改进前每调用一次isBabyBoomer都会创建一个Calendar，TimeZone，Date对象，改进后的程序只会在初始化的时候创建这些对象一次。如果isBabyBoomer方法被频繁的调用，那么将会显著地提高性能。

四、自动装箱和拆箱
看一下下面这个小程序，不知道你能不能发现什么问题？
[java] view plaincopy
public class Client {  
    public static void main(String[] args) {  
        long start = System.currentTimeMillis();  
        Long sum = 0L;  
        for (long i = 0; i < Integer.MAX_VALUE; i++) {  
            sum += i;  
        }  
        long end = System.currentTimeMillis();  
        System.out.println(">>>>>>>>>>Total spent time: " + (end - start) / 1000 + "s");  
    }  
}  
因为sum定义为Long类型，导致这个程序创建了很多不必要的对象，每次往Long sum中增加long i 时都会构建一个实例。
结论：要优先使用基本数据类型而不是装箱基本类型，要当心无意识的自动装箱。*/
}
