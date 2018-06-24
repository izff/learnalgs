
interface InterTest3 {void show(); } 
// 这儿隐含了public abstract
// 子重写父，其中的方法权限不能更低


// 补齐下面的类
// 使main输出 HelloWorld
class OuterTest3 {
    // begin
    public static InterTest3 method() {
        return new InterTest3() {
            public void show() {
                System.out.println("Hello World!");
            }
        };
    }
    // end
}

class InnerClassTest3 {
    public static void main(String[] args) {
        OuterTest3.method().show();
        // 第一，该看出 method 是 OuterTest3 的静态方法
        // 第二，method()调用了方法，所以，该方法的返回值是一个对象
        // 因为 Inter 有 show() 的方法，所以，method() 返回的就是 Inter
        // 
    }
}