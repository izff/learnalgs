/*
 * 匿名内部类在开发中的应用
 */


interface Person {
    public abstract void study();
}

class PersonDemo {
    // 接口名作为形式参数
    // 其实这儿需要的不是接口，而是其实现类的对象
    public void method(Person p) {
        p.study();
    }
}

class Student implements Person {
    public void study() {
        System.out.println("好好学习，天天向上！");
    }
}

class InnerClassTest2 {
    public static void main(String[] args) {
        PersonDemo pd = new PersonDemo();
        Person p = new Student();
        pd.method(p);
        System.out.println("-----------");
        
        // 使用匿名内部类代替接口的实现类对象
        // 优点：使用完成之后就能立即被回收
        // 安卓里面用的多，EE里面相对少
        pd.method(new Person() {
            public void study() {
                System.out.println("Good Good Study, Day Day Up!");
            }
        });
    }
}