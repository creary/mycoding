package com.yf.test.designpattern.effective;

public class Effect2 {

	/*
	 一、背景
对于有多个可选参数的类，我们一般通过什么办法传递参数呢？这里提供了三种办法：
①. 重叠构造器模式
②. JavaBeans模式
③. Builder构建器模式
下面我们来分析一下以上三种方法的优势及弊端。

二、重叠构造器模式
重叠构造器模式中第一个构造器中只有必要参数，第二个构造器有一个可选参数，第三个构造器中有两个可选参数，依次类推，最后一个构造器中包含所有可选参数。这种方案可行，但是有较大缺陷。
缺点：当有很多可选参数的时候，客户端代码很难编写，并难以阅读，如果客户端不小心颠倒了其中两个参数的顺序，编译器也不会报错，但是程序在运行时会出现错误的行为。
//构造器模式 
public class NutritionFacts1 {  
    private int a1; // 必须  
    private int a2; // 必须  
    private int a3; // 可选  
    private int a4; // 可选  
  
    public NutritionFacts1(int a1, int a2) {  
        this(a1, a2, 0);  
    }  
  
    public NutritionFacts1(int a1, int a2, int a3) {  
        this(a1, a2, 0, 0);  
    }  
  
    public NutritionFacts1(int a1, int a2, int a3, int a4) {  
        this.a1 = a1;  
        this.a2 = a2;  
        this.a3 = a3;  
        this.a4 = a4;  
    }  
  
    public int getA1() {  
        return a1;  
    }  
  
    public void setA1(int a1) {  
        this.a1 = a1;  
    }  
  
    public int getA2() {  
        return a2;  
    }  
  
    public void setA2(int a2) {  
        this.a2 = a2;  
    }  
  
    public int getA3() {  
        return a3;  
    }  
  
    public void setA3(int a3) {  
        this.a3 = a3;  
    }  
  
    public int getA4() {  
        return a4;  
    }  
  
    public void setA4(int a4) {  
        this.a4 = a4;  
    }  
      
    public String toString() {  
        return a1 + "-" + a2 + "-" + a3 + "-" + a4;  
    }  
  
    public static void main(String[] args) {  
        NutritionFacts1 nf = new NutritionFacts1(1, 2, 3, 4);  
        System.out.println(nf);  
    }  
}  
三、JavaBeans模式
JavaBeans模式中调用一个无参的构造器来创建对象，然后调用setter方法来设置每个必要的参数。
缺点：构造过程被分到了几个调用中，在构造过程中JavaBean可能处于不一致的状态，同时在构造过程中JavaBean可能处于不一致的状态，需要付出额外努力确保线程安全。
//JavaBeans模式 
public class NutritionFacts2 {  
    private int a1 = -1;  
    private int a2 = -1;  
    private int a3;  
    private int a4;  
  
    public NutritionFacts2() {  
  
    }  
  
    public int getA1() {  
        return a1;  
    }  
  
    public void setA1(int a1) {  
        this.a1 = a1;  
    }  
  
    public int getA2() {  
        return a2;  
    }  
  
    public void setA2(int a2) {  
        this.a2 = a2;  
    }  
  
    public int getA3() {  
        return a3;  
    }  
  
    public void setA3(int a3) {  
        this.a3 = a3;  
    }  
  
    public int getA4() {  
        return a4;  
    }  
  
    public void setA4(int a4) {  
        this.a4 = a4;  
    }  
  
    public static void main(String[] args) {  
        NutritionFacts2 nf = new NutritionFacts2();  
        nf.setA1(1);  
        nf.setA2(2);  
        nf.setA3(3);  
        nf.setA4(4);  
    }  
}  
四、Builder构建器模式
最优方案：既能保证像构造器模式那样的安全性，也能保证像JavaBeans模式那么好的可读性
①. 不直接生成想要的对象,而是让客户端利用所有必要的参数调用构造器,得到一个builder对象。
②. 客户端在builder对象上调用类似setter的方法来设置每个相关的可选参数。
③. 客户端调用无参的build方法来生成不可变的对象，这个builder是它构建的类的静态成员类。
//Builder模式 
public class NutritionFacts3 {  
    private int a1; // 必须  
    private int a2; // 必须  
    private int a3; // 可选  
    private int a4; // 可选  
  
    public static class Builder {  
        private int a1; // 必须  
        private int a2; // 必须  
        private int a3 = 0; // 可选  
        private int a4 = 0; // 可选  
  
        public Builder(int a1, int a2) {  
            this.a1 = a1;  
            this.a2 = a2;  
        }  
  
        public Builder setA3(int a3) {  
            this.a3 = a3;  
            return this;  
        }  
  
        public Builder setA4(int a4) {  
            this.a4 = a4;  
            return this;  
        }  
  
        public NutritionFacts3 build() {  
            return new NutritionFacts3(this);  
        }  
  
    }  
  
    private NutritionFacts3(Builder builder) {  
        this.a1 = builder.a1;  
        this.a2 = builder.a2;  
        this.a3 = builder.a3;  
        this.a4 = builder.a4;  
    }  
  
    public String toString() {  
        return a1 + "-" + a2 + "-" + a3 + "-" + a4;  
    }  
  
    public static void main(String[] args) {  
        NutritionFacts3 nf = new NutritionFacts3.Builder(1, 2).build();  
        System.out.println(nf);  
    }  
}  
	 
	 
	 
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
}
