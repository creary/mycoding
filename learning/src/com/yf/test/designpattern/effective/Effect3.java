package com.yf.test.designpattern.effective;

public class Effect3 {
/*一、背景
所谓的Singleton是指仅仅被实例化一次的类，Singleton通常被用来代表那些本质上唯一的系统组件。
实现Singleton有以下三种方法：
①. 私有构造器，公有静态final域。
②. 私有构造器，公有静态工厂方法。
③. 单元素枚举类型（首选）。

二、私有构造器，公有静态final域
[java] view plaincopy
public class Singleton1 {  
  
    public static final Singleton1 INSTANCE = new Singleton1(); // 公有静态final域  
  
    private Singleton1() {  
    }  
  
}  
三、私有构造器，公有静态工厂方法
缺点1：享有特权的客户端可以借助AccessibleObject.setAccessible方法，通过反射机制，调用私有构造器，如果需要抵御这种攻击，可以修改构造器，让它在被要求创建第二个实例的时候抛出异常。
[java] view plaincopy
public class Singleton2 {  
    private static final Singleton2 INSTANCE = new Singleton2(); // 私有静态final域  
  
    private Singleton2() {  
    }  
    public static Singleton2 getInstance() {  
        return INSTANCE;  
    }  
  
}  

缺点2：每次反序列化一个序列化的实例时，都会创建一个新的实例，为了防止这种情况，不仅要实现Serializable接口，还要提供一个readResolve方法。
public class Singleton2 implements Serializable {  
    private static final long serialVersionUID = 1L;  
    private static final Singleton2 INSTANCE = new Singleton2(); // 私有静态final域  
  
    private Singleton2() {  
    }  
  
//公有静态工厂方法 
    public static Singleton2 getInstance() {  
        return INSTANCE;  
    }  
  //反序列化确保只有一个实例 
    public Object readResolve() {  
        return INSTANCE;  
    }  
  
}  
四、单元素枚举类型
最佳方法：更简洁，无偿提供序列化机制，绝对防止多次实例化，即使是面对复杂的序列化或反射攻击的时候。
public enum Singleton3 {  
    INSTANCE;  
      
    public void testSingleton() {  
        System.out.println("testSingleton");  
    }  
  
    public static void main(String[] args) {  
        Singleton3.INSTANCE.testSingleton();  
    }  
}  
*/
}
