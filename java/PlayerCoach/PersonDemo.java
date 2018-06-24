class PersonDemo {
    public static void main(String[] args) {
        BBCoach bbc = new BBCoach("LBJ", 33);
        BBPlayer bbp = new BBPlayer("Rivers", 47);
        System.out.println("Name: " + bbc.getName());
        System.out.println("Age: " + bbc.getAge());    
        
        bbc.sleep();
        bbc.eat();
        bbc.teach();
        
        
        System.out.println("Name: " + bbp.getName());
        System.out.println("Age: " + bbp.getAge());
        bbp.sleep();
        bbp.eat();
        bbp.study();
    }
}

interface SpeakingEnglish {
    public abstract void speak();
}

abstract class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String          getName() {return name;      }
    public void setName(String name) {this.name = name; }
    public int              getAge() {return age;       }
    public void      setAge(int age) {this.age = age;   }
    
    public void sleep() {
        System.out.println("All person need sleep.");
    }
    
    public abstract void eat();
}

abstract class Player extends Person {
    //public Player() {}
    public Player(String name, int age) {
        super(name, age);
    }
    
    public abstract void study();
}

abstract class Coach extends Person {
    //public Coach() {}
    public Coach(String name, int age) {
        super(name, age);
    }
    
    public abstract void teach();
}

class BBCoach extends Coach {
    //public BBCoach() {}
    
    public BBCoach(String name, int age) {
        super(name, age);
    }
    
    public void eat() {
        System.out.println("Basketball coachs eat burger, drink bear.");
    }
    
    public void teach() {
        System.out.println("Basketball coachs teach dribble and shoot.");
    }
}

class TTCoach extends Coach implements SpeakingEnglish {
    //public TTCoach() {}
    
    public TTCoach(String name, int age) {
        super(name, age);
    }
    
    public void speak() {
        System.out.println("Need to learn English.");
    }
    
    public void eat() {
        System.out.println("Table tennis coachs eat burger, drink bear.");
    }
    
    public void teach() {
        System.out.println("Table tennis coachs serving and hitting.");
    }
}

class TTPlayer extends Player implements SpeakingEnglish {
    //public TTPlayer() {}

    public TTPlayer(String name, int age) {
        super(name, age);
    }
    
    public void speak() {
        System.out.println("Need to learn English.");
    }
    
    public void eat() {
        System.out.println("Table tennis players eat rice, drink tea.");
    }
    
    public void study() {
        System.out.println("Table tennis players learn serving.");
    }
}

class BBPlayer extends Player {
    //public BBPlayer() {}

    public BBPlayer(String name, int age) {
        super(name, age);
    }
    
    public void eat() {
        System.out.println("Basketball players eat steak, drink red bull.");
    }
    
    public void study() {
        System.out.println("Basketball players learn shooting.");
    }
}
