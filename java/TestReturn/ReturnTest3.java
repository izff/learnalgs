// follow the on-line course of Liu Yi
// https://www.bilibili.com/video/av20795925?p=9
// 类、抽象类、接口作为返回值
// 接口作为返回值时，返回的是加上需要时实现接口的类

interface ReturnTest3Love {
    public abstract void love();
}

// 定义具体类实现接口
class ReturnTest3JavaPerson implements ReturnTest3Love {
    public void love() {
        System.out.println("I love Java.");
    }
}

class ReturnTest3LoveDemo {
    public ReturnTest3Love getLove() {
        return new ReturnTest3JavaPerson();
    }
}

public class ReturnTest3 {
    public static void main(String[] args) {
        ReturnTest3LoveDemo pd = new ReturnTest3LoveDemo();
        ReturnTest3Love l = pd.getLove(); // Love l = new Teacher(); 多态
        // 在多态中
        // 对成员变量、静态方法：无论编译还是运行都是看左边的类型
        // 对非静态方法：编译看左边，运行看右边
        l.love();
    }
}