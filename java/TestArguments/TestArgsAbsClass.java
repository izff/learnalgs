public class TestArgsAbsClass {
    public static void main(String[] args) {
        Person p = new Player("LBJ", 33);
        PersonDemo pd = new PersonDemo();
        pd.method(p);
        System.out.println("---------------");
        p.show();
        p.sleep();
        p.eat();
        System.out.println("---------------");
        Player pl = new Player("LBJ", 33);
        pl.show();
        pl.sleep();
        pl.eat();
    }
}

class PersonDemo {
    public void method(Person p) {
        p.show();
        p.sleep();
        p.eat();
    }
}

abstract class Person {
    private String name;
    private int age;
    
    public Person () {}
    
    public Person (String name, int age) {
        this.name = name; // Please take notice of the usage of "this"
        this.age = age;
    }
    
    public void show () {
        System.out.println("Name: "+ name);
        System.out.println("Age: "+ age);
    }
    
    public void sleep() {
        System.out.println("All people need sleep.");
    }
    
    abstract public void eat();
}

class Player extends Person {
    public Player() {}
    
    public Player(String name, int age) {
        super(name, age);
    }
    
    public void eat() {
        System.out.println("The player will have an apple.");
    }
}