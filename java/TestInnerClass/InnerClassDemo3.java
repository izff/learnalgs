/*
 * 
 * 成员内部类
 * 
 * 
 */

class Outer3 {
    private int num = 10;
    // 成员位置内部类
    class Inner {
        public void show() {
            System.out.println(num);
        }
    }
    
    /*
    public void method() {
    // 局部位置
        class Inner {}
    }
    */
    
}

class InnerClassDemo3 {
    public static void main(String[] args) {
        //这样子不能访问另一个类的成员内部类
        //Inner i = new Inner();
        //i.show();
        
        // 外部类名.内部类名      外部类对象().内部类对象()
        Outer3.Inner oi = new Outer3().new Inner();
        oi.show();
    }
}