/*
 * 匿名内部类 内部类的简化写法
 * 
 * 前提：存在一个类活着接口，这里的类可以使具体类或者抽象类
 * 
 * 格式：new类名活着接口名() {重写方法; }
 * 
 * 本质上：它是一个对象
 *      对接口－－－实现类的对象
 *      对类－－－－子类的对象
 *      对抽象类－－具体类的对象
 */

interface Inter {
    public abstract void show();
    public abstract void show2();
}

class Outer6 {
    public void method() {
        // 匿名对象
        // 一个方法的时候
        /*
        new Inter() {
            public void show() {
                System.out.println("Inner show!");
            }
        }.show();
        // 本质代表该接口的实现类对象    
        // 和new对应，组成语句，所以必须有;
        */
        
        // 两个方法的时候
        // 可以类似上面一样，一个方法一个方法地写，但是比较麻烦
        // 利用多态，将这个匿名对象赋值给一个接口对象
        Inter i = new Inter() {
            public void show() { System.out.println("show"); }
            public void show2() { System.out.println("show2"); }
        }; // 注意这儿的分号
        i.show();
        i.show2();
    }
 }


class InnerClassDemo6 {
    public static void main(String[] args) {
        Outer6 o = new Outer6();
        o.method();
    }
}