package com.yf.test.designpattern.thinkjava;

public class Think1 {
/*1. 区分重载方法：
参数顺序的不同可以区分两个方法，不过，一般情况下千万别这么做，因为这会使代码难以维护不能通过返回值类型来区分重载方法：因为如果我直接调用f(), 此时java不知道应该调用那一个
[java] view plaincopy
public void f(){   
}  
public int f(){  
return 1;  
}  

2. 只有当需要明确指出对当前对象的引用时， 才需要使用this关键字，例如， 当需要返回对当前对象的引用时，就常常在return语句这样写：
[java] view plaincopy
public class Leaf {  
    private int i = 0;  
  
    public Leaf increment() {  
        i++;  
        return this;  
    }  
  
    void print() {  
        System.out.println("i = " + i);  
    }  
  
    public static void main(String[] args) {  
        Leaf x = new Leaf();  
        x.increment().increment().increment().print();  
    }  
}   
// 由于increment()通过this关键字返回了对当前对象的引用，所以很容易在一条语句对同一个对象执行多次操作

3. 为什么需要finalize()方法？
把一个对象用完后就“弃之不顾”的做法并非总是安全的，当然，java有垃圾回收器负责回收无用对象占据的内存资源，但也有特殊情况：假定你的对象（并非使用new）获得了一块“特殊”的内存区域，由于垃圾回收器只知道释放那些经由new分配的内存，所以它不知道该如何释放该对象的这块“特殊”内存。为了应对这种情况，java允许在类中定义一个名为finalize（）的方法。

不该将finalize（）作为通用的清理方法。

之所以要有finalize（），是由于在分配内存时可能采用了类似C语言中的做法，而非Java中的通常做法，这种噢概念情况主要发生在使用“本地方法”的情况下，本地方法是一种在Java中调用非Java代码的方式。

无论是“垃圾回收”还是“终结方法”都不保证一定会发生，如果Java虚拟机并未面临内存耗尽的情形，它是不会浪费时间
去执行垃圾回收以恢复内存的。

4.初始化顺序：
在类的内部，变量定义的先后顺序决定了初始化的顺序，即使变量定义散布于方法定义之间，他们仍然会在任何方法（包括构造器）被调用之前得到初始化。
[java] view plaincopy
class Window {  
    Window(int marker) {  
        print("Window(" + marker + ")");  
    }  
}  
  
class House {  
    Window w1 = new Window(1); // Before constructor  
  
    House() {  
        // Show that we're in the constructor:  
        print("House()");  
        w3 = new Window(33); // Reinitialize w3  
    }  
  
    Window w2 = new Window(2); // After constructor  
  
    void f() {  
        print("f()");  
    }  
  
    Window w3 = new Window(3); // At end  
}  
  
public class OrderOfInitialization {  
    public static void main(String[] args) {  
        House h = new House();  
        h.f(); // Shows that construction is done  
    }  
}   
/* Output: 
Window(1) 
Window(2) 
Window(3) 
House() 
Window(33) 
f() 

5. 静态数据初始化：
[java] view plaincopy
class Bowl {  
    Bowl(int marker) {  
        print("Bowl(" + marker + ")");  
    }  
  
    void f1(int marker) {  
        print("f1(" + marker + ")");  
    }  
}  
  
class Table {  
    static Bowl bowl1 = new Bowl(1);  
  
    Table() {  
        print("Table()");  
        bowl2.f1(1);  
    }  
  
    void f2(int marker) {  
        print("f2(" + marker + ")");  
    }  
  
    static Bowl bowl2 = new Bowl(2);  
}  
  
class Cupboard {  
    Bowl bowl3 = new Bowl(3);  
  
    static Bowl bowl4 = new Bowl(4);  
  
    Cupboard() {  
        print("Cupboard()");  
        bowl4.f1(2);  
    }  
  
    void f3(int marker) {  
        print("f3(" + marker + ")");  
    }  
  
    static Bowl bowl5 = new Bowl(5);  
}  
  
public class StaticInitialization {  
    public static void main(String[] args) {  
        print("Creating new Cupboard() in main");  
        new Cupboard();  
        print("Creating new Cupboard() in main");  
        new Cupboard();  
        table.f2(1);  
        cupboard.f3(1);  
    }  
  
    static Table table = new Table();  
  
    static Cupboard cupboard = new Cupboard();  
}   
 Output: 
Bowl(1) 
Bowl(2) 
Table() 
f1(1) 
Bowl(4) 
Bowl(5) 
Bowl(3) 
Cupboard() 
f1(2) 
Creating new Cupboard() in main 
Bowl(3) 
Cupboard() 
f1(2) 
Creating new Cupboard() in main 
Bowl(3) 
Cupboard() 
f1(2) 
f2(1) 
f3(1) 
初始化的顺序是先静态对象， 而后是非静态对象，上面的类中要执行main（）（静态方法）必须加载StaticInitialization，然后其静态域table和cupboard被初始化，这将导致它们对应的类也被加载，并且由于它们也都包含静态的Bowl对象，因此Bowl随后也被加载。
1. 一个java文件中可以有多个类，但只能有一个public的类。
2. 类既不可以是private的，因为这会使得除该类职位，其他任何类都不可以访问它，也不可以是protected，所以对于类的访问权限只能是包访问权限或public。（一个内部类可以是private或protected）
3. 访问权限 public > friendly > protected > private

1. 每一个非基本类型的对象都有一个toString方法，而且当编译器需要一个String而你却只有一个对象时，该方法便会被调用。
[java] view plaincopy
public class SprinklerSystem {  
    private String valve；  
  
    public String toString() {  
        value = "test";  
        return "valve = " + valve;  
    }  
  
    public static void main(String[] args) {  
        SprinklerSystem sprinklers = new SprinklerSystem();  
        System.out.println(sprinklers);  
    }  
}   
/* Output: 
value = "test"; 

2. Java会自动在子类的构造器中插入对父类构造器的调用，即会先调用父类的构造器，如果父类没有无参的构造器，那就要通过super(param)显式调用父类带参构造器
class Art {  
    Art() {  
        print("Art constructor");  
    }  
}  
  
class Drawing extends Art {  
    Drawing() {  
        print("Drawing constructor");  
    }  
}  
  
public class Cartoon extends Drawing {  
    public Cartoon() {  
        print("Cartoon constructor");  
    }  
  
    public static void main(String[] args) {  
        Cartoon x = new Cartoon();  
    }  
}   
/* Output: 
Art constructor 
Drawing constructor 
Cartoon constructor 

3. 不能认为一个变量是final的，就认为无法改变它的值，final的意思是无法将引用只想另一个新的对象。
[java] view plaincopy
public class FinalArguments {  
    void with(final Gizmo g) {  
        //! g = new Gizmo(); // Illegal -- g is final  
    }  
}  

4. 继承及初始化：
访问Beetle.main()方法时，加载器开始启动并找到Beetle类的编译代码，在对它进行加载的过程中，编译器注意到它有一个父类。
于是开始加载父类，然后父类中的static初始化即会被执行...
class Insect {  
    private int i = 9;  
    protected int j;  
  
    Insect() {  
        print("i = " + i + ", j = " + j);  
        j = 39;  
    }  
  
    private static int x1 = printInit("static Insect.x1 initialized");  
  
    static int printInit(String s) {  
        print(s);  
        return 47;  
    }  
}  
  
public class Beetle extends Insect {  
    private int k = printInit("Beetle.k initialized");  
  
    public Beetle() {  
        print("k = " + k);  
        print("j = " + j);  
    }  
  
    private static int x2 = printInit("static Beetle.x2 initialized");  
  
    public static void main(String[] args) {  
        print("Beetle constructor");  
        Beetle b = new Beetle();  
    }  
}   
/* Output: 
static Insect.x1 initialized 
static Beetle.x2 initialized 
Beetle constructor 
i = 9, j = 0 
Beetle.k initialized 
k = 47 
j = 39 
1. 动态绑定
又称"后期绑定"或"运行时绑定"，它的含义就是在运行时判断对象的类型，从而调用恰当的方法
[java] view plaincopy
public class Shapes {  
    private static RandomShapeGenerator gen = new RandomShapeGenerator();  
  
    public static void main(String[] args) {  
        Shape[] shapeArr = new Shape[9];   
        // 填充数组  
        for (int i = 0; i < shapeArr.length; i++){  
            shapeArr[i] = gen.next();  
        }  
        // 动态调用方法  
        for (Shape shape : shapeArr){   
            shape.draw();   
        }  
    }  
}  
  
class RandomShapeGenerator {  
    private Random rand = new Random(47);  
  
    public Shape next() {  
        switch (rand.nextInt(3)) {  
            default:  
            case 0:  
                return new Circle();  
            case 1:  
                return new Square();  
            case 2:  
                return new Triangle();  
            }  
    }  
}  
  
class Shape {  
    public void draw() {  
        System.out.println("Shape.draw()");  
    }  
  
    public void erase() {  
        System.out.println("Shape.erase()");  
    }  
}  
  
class Circle extends Shape {  
    public void draw() {  
        System.out.println("Circle.draw()");  
    }  
  
    public void erase() {  
        System.out.println("Circle.erase()");  
    }  
}  
  
class Triangle extends Shape {  
    public void draw() {  
        System.out.println("Triangle.draw()");  
    }  
  
    public void erase() {  
        System.out.println("Triangle.erase()");  
    }  
}  
  
class Square extends Shape {  
    public void draw() {  
        System.out.println("Square.draw()");  
    }  
  
    public void erase() {  
        System.out.println("Square.erase()");  
    }  
}  
/* Output: 
Triangle.draw() 
Triangle.draw() 
Square.draw() 
Triangle.draw() 
Square.draw() 
Triangle.draw() 
Square.draw() 
Triangle.draw() 
Circle.draw() 

2. "覆盖"私有方法：
我们期望的输出是public f()，但是由于private方法被自动认为是final方法，而且对子类是屏蔽的，因此在这种情况下，
Derived类中的f()方法就是一个全新的方法；
结论：只有非private方法才可以被覆盖，所以在子类中，对于父类中的private方法，最好采用不同的名字。
[java] view plaincopy
public class PrivateOverride {  
    private void f() {  
        print("private f()");  
    }  
  
    public static void main(String[] args) {  
        PrivateOverride po = new Derived();  
        po.f();  
    }  
}  
  
class Derived extends PrivateOverride {  
    public void f() {  
        print("public f()");  
    }  
}   
/* Output: 
private f() 

3. 构造器和多态：
复杂对象调用构造器要遵循下面的顺序：
1) 调用父类构造器(如有多层，则会不断递归下去)
2) 按声明顺序调用成员的初始化方法
3) 调用子类构造器的主体
[java] view plaincopy
class Meal {  
    Meal() {  
        print("Meal()");  
    }  
}  
  
class Bread {  
    Bread() {  
        print("Bread()");  
    }  
}  
  
class Cheese {  
    Cheese() {  
        print("Cheese()");  
    }  
}  
  
class Lettuce {  
    Lettuce() {  
        print("Lettuce()");  
    }  
}  
  
class Lunch extends Meal {  
    Lunch() {  
        print("Lunch()");  
    }  
}  
  
class PortableLunch extends Lunch {  
    PortableLunch() {  
        print("PortableLunch()");  
    }  
}  
  
public class Sandwich extends PortableLunch {  
    private Bread bread = new Bread();  
  
    private Cheese cheese = new Cheese();  
  
    private Lettuce lettuce = new Lettuce();  
  
    public Sandwich() {  
        print("Sandwich()");  
    }  
  
    public static void main(String[] args) {  
        new Sandwich();  
    }  
}   
/* Output:  
Meal()  
Lunch()  
PortableLunch()  
Bread()  
Cheese()  
Lettuce()  
Sandwich()  

4. 编写构造器时，应尽可能简单地使对象进入正常状态，如果可以的话，避免调用其它方法。
下面的例子我们会发现radius的值为0，为什么？
一个动态绑定的方法调用会深入到继承层次结构内部，它可以调用子类的方法，然而此时方法所操纵的成员还没有进行初始化。
[java] view plaincopy
class Glyph {  
    void draw() {  
        print("Glyph.draw()");  
    }  
  
    Glyph() {  
        print("Glyph() before draw()");  
        this.draw();  
        print("Glyph() after draw()");  
    }  
}  
  
class RoundGlyph extends Glyph {  
    private int radius = 1;  
  
    RoundGlyph(int r) {  
        radius = r;  
        print("RoundGlyph.RoundGlyph(), radius = " + radius);  
    }  
  
    void draw() {  
        print("RoundGlyph.draw(), radius = " + radius);  
    }  
}  
  
public class PolyConstructors {  
    public static void main(String[] args) {  
        new RoundGlyph(5);  
    }  
}   
/* Output: 
Glyph() before draw() 
RoundGlyph.draw(), radius = 0 
Glyph() after draw() 
RoundGlyph.RoundGlyph(), radius = 5 
之所以调用的是子类的draw方法，this的含义是谁调用我，this指向的就是谁，Glyph构造器是通过RoundGlyph类构造器中super()方法触发，所以this指向的是RoundGlyph中的draw方法

5. "向上转型"与"向下转型"
向上转型：List list = new ArrayList(); list.add("xxx") 此时调用的其实是ArrayList的方法
注意：向上转型之后，无法访问子类中的新加的方法

向下转型:其实就是强转，前提是我们知道它是某一种类型，否则运行时会报ClassCastException

1. java中的多重继承
在c++中，组合多个类的接口的行为被称为多重继承。
在java中一个类只能继承一次，但可以实现多个接口，注意，继承必须写在实现前面

2. 使用接口的核心原因：
1) 为了能够向上转型为多个父类型（以及由此而带来的灵活性）
2) 防止程序员创建该类的对象

3. 策略设计模式：
传递不同的StringProcessor实现类，将会分别做不同的事情
[java] view plaincopy
public abstract class StringProcessor implements Processor {  
    public String name() {  
        return getClass().getSimpleName();  
    }  
  
    public abstract String process(Object input);  
  
    public static String s = "If she weighs the same as a duck, she's made of wood";  
  
    public static void main(String[] args) {  
        Apply.process(new Upcase(), s);  
        Apply.process(new Downcase(), s);  
        Apply.process(new Splitter(), s);  
    }  
}  
  
class Upcase extends StringProcessor {  
    public String process(Object input) { // Covariant return  
        return ((String) input).toUpperCase();  
    }  
}  
  
class Downcase extends StringProcessor {  
    public String process(Object input) {  
        return ((String) input).toLowerCase();  
    }  
}  
  
class Splitter extends StringProcessor {  
    public String process(Object input) {  
        return Arrays.toString(((String) input).split(" "));  
    }  
}  

1. 内部类访问外部类的成员变量：Outer.this.size
[java] view plaincopy
public class Outer {  
    private int size;  
  
    public class Inner1 {  
        private int size;  
        public void display(int size) {  
            size++; // 引用的是display函数的形参  
            this.size++; // 引用的是Inner类中的成员变量  
            Outer.this.size++; // 引用的是Outer类中的成员变量  
        }  
    }  
}  
2. 内部类如何被外部引用：Outer.Inner inner = outer.new Inner()
要创建一个内部类的实例对象，必须先创建一个外部类的实例对象
[java] view plaincopy
public class TestInner {  
    public static void main(String[] args) {  
        Outer outer = new Outer();  
        Outer.Inner inner = outer.new Inner();  
        inner.display();  
    }  
}  
  
class Outer{  
    private int size = 10;  
    public class Inner{  
        public void display(){  
            System.out.println(size);  
        }  
    }  
}  
3. 在方法中定义的内部类如果要访问方法中的局部变量，局部变量必须声明为final
[java] view plaincopy
public class Outer {  
    public void test(){  
        final int x = 0;  
        class Inner {  
            void display(){  
                System.out.println(x);  
            }  
        }  
    }  
}  
4. 外部类可以直接访问静态内部类，无需创建外部类对象
[java] view plaincopy
class Outer {  
    static class Inner{  
          
    }  
}  
  
public class Test{  
    public static void main(String[] args) {  
        Outer.Inner inner = new Outer.Inner();  
    }  
}  
1.各种map的区别
HashMap: 没有按照任何明显的顺序来保存其元素(同HashSet)
TreeMap：按照比较结果的升序保存key(同TreeSet)
LinkedHashMap：按照插入顺序保存key(同LinkedHashSet)

2.ListIterator是一个更为强大的Iterator的子类型，Iterator只能向前移动，但是ListIterator可以双向移动
[java] view plaincopy
public class ListIteration {  
    public static void main(String[] args) {  
        List<Pet> pets = Pets.arrayList(8);  
        ListIterator<Pet> it = pets.listIterator();  
          
        // Forwards:  
        while (it.hasNext()){  
            System.out.print(it.next() + ", " + it.nextIndex() + ", " + it.previousIndex() + "; ");  
        }  
          
        // Backwards:  
        while (it.hasPrevious()){  
            System.out.print(it.previous().id() + " ");  
        }  
    }  
}  
3. TreeSet中的元素是排序的，默认按照字典升序，当然我们可以自己传入一个比较器来自定义排序。
[java] view plaincopy
public class UniqueWordsAlphabetic {  
    public static void main(String[] args) {  
        Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);  
        words.add("a");  
        words.add("c");  
        words.add("B");  
        System.out.println(words);  
    }  
}  
1. finally能做什么？
当要把除内存之外的资源恢复到它们的初始状态时，就要用到finally子句。这种需要清理的资源包括：已经打开的文件或网络连接

2. 子类覆盖父类方法的时候，不能抛出比父类更多的异常

3. 异常处理的一个重要目标是把错误处理的代码同错误发生的地点相分离，这使你能在一段代码中专注于要完成的事情，至于如何处理错误，则放在另一段代码中完成。

4. finally块中避免使用return语句，如果在finally中使用return，会吃掉try catch中的异常信息，屏蔽了错误的发生。
[java] view plaincopy
public class TryCatchFinally {  
    public static final String test() {  
        String t = "";  
   
        try {  
            t = "try";  
            int i = 1/0;  
            return t;  
        } catch (Exception e) {  
            // result = "catch";  
            t = "catch";  
            return t;  
        } finally {  
            t = "finally";  
            return t;  
        }  
    }  
   
    public static void main(String[] args) {  
        System.out.print(TryCatchFinally.test());  
    }  
   
}  
// output:  finally  
5. finally块中避免再次抛出异常，否则也会吃掉try catch语句块中的异常。
6. 在try catch中return，在finally中对值的修改是不会生效的：
[java] view plaincopy
public class TryCatchFinally {  
    public static final String test() {  
        String t = "";  
  
        try {  
            t = "try";  
            return t;  
        } catch (Exception e) {  
            t = "catch";  
            return t;  
        } finally {  
            t = "finally";  
        }  
    }  
   
    public static void main(String[] args) {  
        System.out.print(TryCatchFinally.test());  
    }  
   
}  
// output:  try  
1. String对象是不可变的,使用下面的方式会产生一大堆需要垃圾回收的中间对象
String s = ""；
for(int i = 0; i < 10; i++){
s += "i";
}

2. StringBuffer是线程安全的，效率比StringBuilder较低
1.所有的类都是对其第一次使用时，动态加载到JVM中的
2.Java程序在它开始运行之前并非被完全加载，其各个部分是在必需时才加载的。 
3.在进行类型转换前，如果不确定对象是什么类型，可以使用instanceof
1.使用泛型方法可以取代将整个类泛型话，要定义泛型方法，只需将泛型参数列表至于返回值之前：
[java] view plaincopy
public class GenericMethods {  
    public <T> void f(T x) {  
        System.out.println(x.getClass().getName());  
    }  
  
    public static void main(String[] args) {  
        GenericMethods gm = new GenericMethods();  
        gm.f("");  
        gm.f(1);  
        gm.f(1.0);  
        gm.f(1.0F);  
        gm.f('c');  
        gm.f(gm);  
    }  
}  

2.可变参数可以和泛型方法很好的共存：
[java] view plaincopy
public class GenericVarargs {  
    public static <T> List<T> makeList(T... args) {  
        List<T> result = new ArrayList<T>();  
        for (T item : args)  
            result.add(item);  
        return result;  
    }  
  
    public static void main(String[] args) {  
        List<String> ls = makeList("A");  
        System.out.println(ls);  
        ls = makeList("A", "B", "C");  
        System.out.println(ls);  
        ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));  
        System.out.println(ls);  
    }  
}  
3.边界使你可以在用于泛型的参数类型上设置限制条件。
class AAA<T extends BBB>

4.<? extends T>不是一个集合，而是T的某一种子类的意思，记住是一种，单一的一种，问题来了，由于连哪一种都不确定，带来了不确定性，所以是不可能通过 
add()来加入元素。你或许还觉得为什么add(T)不行? 因为<? extends T>是T的某种子类，能放入子类的容器不一定放入超类，也就是没可能放入T
[java] view plaincopy
public class GenericsAndCovariance {  
    public static void main(String[] args) {  
        List<? extends Fruit> flist = new ArrayList<Apple>();  
        // Compile Error: can't add any type of object:  
        // flist.add(new Apple());  
        // flist.add(new Fruit());  
        // flist.add(new Object());  
        flist.add(null); // Legal but uninteresting  
        // We know that it returns at least Fruit:  
        Fruit f = flist.get(0);  
    }  
}  
5.<? super T>这里比较容易使用，没<? extends T>这么多限制，这里的意思是，以T类为下限的某种类，
简单地说就是T类的超类。但为什么add(T)可以呢？因为能放入某一类的容器一定可以放入其子类，多态的概念。
1.数组的拷贝：System.arraycopy()
[java] view plaincopy
public class CopyingArrays {  
    public static void main(String[] args) {  
        int[] i = new int[7];  
        int[] j = new int[10];  
        Arrays.fill(i, 47);  
        Arrays.fill(j, 99);  
        print("i = " + Arrays.toString(i));  
        print("j = " + Arrays.toString(j));  
        System.arraycopy(i, 0, j, 0, i.length);  
    }  
} /* 
 * Output: i = [47, 47, 47, 47, 47, 47, 47]  
 * j = [99, 99, 99, 99, 99, 99, 99, 99, 99, 99]  
 * j = [47, 47, 47, 47, 47, 47, 47, 99, 99, 99] k = [47, 47, 47, 47, 47] 
2.数组的比较：Arrays.equals()
数组相等的条件是元素个数必须相等，并且对应位置的元素也相等：
[java] view plaincopy
 public class ComparingArrays {  
    public static void main(String[] args) {  
        int[] a1 = new int[10];  
        int[] a2 = new int[10];  
        Arrays.fill(a1, 47);  
        Arrays.fill(a2, 47);  
        print(Arrays.equals(a1, a2));  
        a2[3] = 11;  
        print(Arrays.equals(a1, a2));  
        String[] s1 = new String[4];  
        Arrays.fill(s1, "Hi");  
        String[] s2 = { new String("Hi"), new String("Hi"), new String("Hi"),  
                new String("Hi") };  
        print(Arrays.equals(s1, s2));  
    }  
}  
/* 
 * Output: true false true 
3. 往数组里面填充内容：Arrays.fill()
[java] view plaincopy
public class Test {  
    public static void main(String[] args) {  
        String[] str = new String[10];  
        Arrays.fill(str, "?");  
        for(String s : str){  
            System.out.print(s);  
        }  
    }  
}  

1.HashSet为快速查找而设计的Set
  TreeSet底层为树结构，必须要实现Comparable接口，存入的元素会按照compareTo方法中的规则排序
  LinkedHashSet内部使用链表维护元素的插入次序。
  
2.HashMap使用对象的hashCode()进行快速查询的，此方法能够显著提高性能。
  LinkedHashMap使用链表维护内部次序(插入次序)
  TreeMap基于红黑树的实现，结果经过排序(顺序由comparable决定)
  ConcurrentHashMap一种线程安全的Map

3.为什么HashMap查询速度很快？
因为内部使用了散列：散列使得查询得以快速进行。使用hashCode()方法生成散列码
1. 字符流一般用来操作类似文本文件FileReader、FileWriter
2. 字节流一般用于操作二进制文件，如图片、mp3等。FileInputStream、FileOutputStream
3. 使用缓冲区读写大文件时可以提高效率。BufferedReader、BufferedWriter、BufferedInputStream、BufferedOutputStream
1.注解不支持继承，不能使用关键字extends来继承某个@interface。
1. 线程池
CacheThreadPool：(建议使用)
FixedThreadPool 使用有限的线程集来执行所提交的任务，可以限制线程的数量
SingleThreadExecutor: 线程数量为1的FixedThreadPool  任何时刻都只有一个线程在运行

2. yield：给线程调度机制一个暗示：你的工作已经做的差不多了，可以让别的线程使用CPU了
不过这只是一个暗示，没有任何机制保证它将会被采纳

3. join: 一个线程可以在其他线程之上调用join方法，其效果是等待一段时间直到第二个线程结束才继续执行。

4. 使用lock()和unlock()的好处：
使用synchronized关键字，当失败时，就会抛出一个异常，你没有机会做任何清理工作，使用显式lock，可以在finally子句中维护正确状态
显式lock对象在加锁和释放锁方面，可以实现更细粒度的控制

5. 像Vector和Hashtable这类早期容器具有许多synchronized方法，当他们用于非多线程的应用程序中，便会导致不可接受的开销

6. ConcurrentHashMap允许并发的读取和写入

*
*/
}
