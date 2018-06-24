// https://blog.csdn.net/u010355144/article/details/45459895

class TestDuotaiFu 
{
    int num = 5;
    
    static void show()
    {
        System.out.println("fu show()");
    }
    
    void print()
    {
        System.out.println("fu print()");
    }
}

class TestDuotaiZi extends TestDuotaiFu 
{
    int num = 8;
    
    static void show()
    {
        System.out.println("zi show()");
    }
    
    void print() 
    {
        System.out.println("zi print()");
    }
}

// 测试一下多态的用法：
// 在多态中，成员变量的特点： 无论编译和运行，都参考左边（引用型变量所属的类）
// 在多态中，静态方法的特点：无论编译和运行，都参考做左边。
// 在多态中，非静态方法的特点：编译看左边，运行看右边。
class TestDuotai
{
    public static void main(String[] args) 
    {
        TestDuotaiFu f = new TestDuotaiZi(); //
        System.out.println(f.num);      // 成员变量看左边，即父类
        f.show();                       // 静态方法与左边、即父类，一致
        f.print();                      // 编译时与父类一致，运行时与子类一致
        System.out.println("----------");
        TestDuotaiZi z = new TestDuotaiZi();
        System.out.println(z.num);
        z.show();
        z.print();
    }
}
