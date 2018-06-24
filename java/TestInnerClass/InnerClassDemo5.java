/*
 * 局部内部类：
 * 1 可以直接访问外部类的成员
 * 2 在局部位置，可以创建内部类对象，通过内部类对象调用内部类方法
 * 3 如何访问局部变量？（面试题）
 *   从内部类访问局部变量，需要此变量被声明为 final
 *   Why?
 *   因为（一般的）局部变量会随着方法的调用结束而消失，而局部对象并没有立马从堆内存中消失，
 *   局部对象还要继续使用那个变量，所以，数据就需要用 final 来修饰；这样，在堆内存中存储的
 *   其实是一个常量值。
 *   对象的回收：垃圾回收器空闲的时候才回收。
 */

class Outer5 {
    private int num = 10;
    // 成员位置
    
    public void method() {
    // 局部位置
        // int num2 = 20;
        final int num2 = 20;
        class Inner {
            public void show() {
                System.out.println(num);
            }
        }
        
        Inner i = new Inner();
        i.show();
    }
    
}

class InnerClassDemo5 {
    public static void main(String[] args) {
        Outer5 o = new Outer5();
        o.method();
    }
}