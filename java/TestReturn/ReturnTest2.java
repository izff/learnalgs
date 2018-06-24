// follow the on-line course of Liu Yi
// https://www.bilibili.com/video/av20795925?p=8
// 类、抽象类、接口作为返回值
// 返回抽象类的子类对象

//抽象类
abstract class ReturnTest2Person {
    public abstract void study();
}

// 具体类
class ReturnTest2Student extends ReturnTest2Person {
    public void study() {
        System.out.println("Good Good Study, Day Day Up.");
    }
}

// Demo: 返回Person抽象类
class ReturnTest2PersonDemo {
    public ReturnTest2Person getPerson() {
        // Person p = new Student(); // 用抽象类来接受具体类的对象
        // return p;
        return new ReturnTest2Student();
    }
}

// 测试
public class ReturnTest2 {
    public static void main(String[] args) {
        // 测试Person类中的study方法
        ReturnTest2PersonDemo pd = new ReturnTest2PersonDemo();
        ReturnTest2Person p = pd.getPerson(); // Person p = new Student(); 多态
        p.study();
    }
}
