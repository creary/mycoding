package com.yf.test.designpattern.effective;

public class ObjectSee {
/*一. 面向对象设计把握的一个重要经验:
谁拥有数据, 谁就对外提供操作这些数据的方法.

二. 面向对象的几个典型案例
1. 人在黑板上画圆
涉及到的对象:  person,  blackboard, circle
画圆的方法应该在那个对象里面呢?
因为画圆的方法涉及到私有成员变量圆心和半径, 所以画圆的方法应该由圆提供.

2. 列车司机紧急刹车
涉及到的对象: 司机, 列车
刹车的方法应该在司机里面还是在列车里面呢?
刹车动作涉及发动机熄火, 离合器离合, 抱死轮子, 所以该方法应该由列车提供

3. 售货员统计售货小票的金额
涉及到的对象: 售货员, 票据
统计的方法应该由售货员提供还是票据提供? (票据)

三. 面向对象设计题
1. 球从一根绳子的一端移动到另一端
[java] view plaincopy
public class Point {  
    private float x;  
    private float y;  
  
    public Point(float x, float y) {  
        this.x = x;  
        this.y = y;  
    }  
}  
  
class Rope {  
    private Point start;  
    private Point end;  
  
    public Rope(Point start, Point end) {  
        this.start = start;  
        this.end = end;  
    }  
  
    public Point nextPoint(Point currentPoint) {  
        return null;  
    }  
}  
  
class Ball {  
    private Rope rope;  
    private Point currentPoint;  
  
    public Ball(Rope rope, Point startPoint) {  
        this.rope = rope;  
        this.currentPoint = startPoint;  
    }  
  
    public void move() {  
    }  
}  

2. 两块石头磨成一把石刀, 石刀可以砍树, 砍成木材, 木材做成椅子
对象: Stone  StoneKnife   Tree  Material    Chair
StoneKnife s = KnifeFactory.createKnife(stone)
Material m = StoneKnife.cut(tree)
Chair c = ChairFactory.makeChair(material)*/
}
