/*
 * 权限修饰符 public default protected private
 * 状态修饰符 static final
 * 抽象修饰符 abstract 
 * 
 * 
 * 
 * 类 (多用public)
 *   权限：default public
 *   状态：final
 *   抽象：abstract
 *   public用的最多
 * 
 * 成员变量 (多用private)
 *   权限：default public protected private
 *   状态：final static
 *   public int a;
 *   private int b;
 *   public static final int d;
 * 
 * 构造方法 (只能提供访问权限) (public)
 *   private default protected public
 * 
 * 成员方法 (多用public)
 *   private default protected public
 *   static final 
 *   abstract
 * 
 * 除此以外的组合规则：
 *   成员变量 public static final
 *   成员方法 public static 
 *           public abstract
 *           public final
 * 
 */

// class definition
// private protected static not allowed here
public class Demo {
    // 成员变量
    // abstract not allowed here
    private int x=10;
    int y = 20;
    protected int z = 30;
    public int a = 40;
    public final int b = 50;
    public static int c = 60;
    public static final int d = 70;
    
    // 构造方法：四种权限修饰符均可以
    // 但此处不可使用 static final
    private Demo() {}
    
    Demo(String name){}
    
    protected Demo(String name, int age) {}

    public Demo(String name, int age, String address) {}
    
    //public static Demo()
    //public final Demo()
    
    // 成员方法
    static void show1() {}
    //abstract void show2(); // 不能有{} body
    final void show3() {}
    
}