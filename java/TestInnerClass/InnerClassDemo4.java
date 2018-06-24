/*
 * 成员内部的修饰符
 * private
 * 保证数据安全性
 * static (静态是为了从类名访问，所以一般不加 private 修饰)
 * 
 */

/*
class Body {
    private class Heart {
        public void operator() {
            System.out.println("心脏搭桥手术！");
        }
    }
    
    public void method() {
        if (true) {
            Heart h = new Heart();
            h.operator();
        }
    }
}
*/

class Outer4 {
    private int num = 10;
    private static int num2 = 20;
    
    // 静态上下文只能访问静态成员变量
    public static class Inner {
        public void show() {
            //System.out.println(num);
            System.out.println(num2);
        }
        
        public static void show2() {
            //System.out.println(num);
            System.out.println(num2);
        }
    }
}

class InnerClassDemo4 {
    public static void main(String[] args) {
        //下条语句失败，因为 static 对象只能从类型名称来访问
        //Outer4.Inner oi = new Outer4().new Inner();
        Outer4.Inner oi = new Outer4.Inner();
        // 这儿new是在创建 Inner 对象
        oi.show();
        oi.show2();
        
        //静态方法可以从类名或者对象来访问
        //show2 经过了static修饰，所以可以直接从类名访问
        Outer4.Inner.show2();
    }
}
