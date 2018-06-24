/*
 * 类定义在类{}之中
 * 
 * 访问特点：
 *     可以直接访问外部类的成员，包括私有
 * 
 */

class Outer1 {
    private int num = 20;
    
    class Inner {
        public void show() {
            System.out.println(num);
        }
    }
    
    public void method() {
        //show() 不能直接使用内部类的成员
        Inner i = new Inner();
        i.show();
    }
}

class InnerClassDemo {
    public static void main(String[] args) {
        
    }
}