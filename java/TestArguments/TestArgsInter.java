class TestArgsInter {
    public static void main(String[] args) {
        DogDemo dd = new DogDemo(); 
        Inter i = new Dog();
        dd.method(i);
    }
}

interface Inter {
    public abstract void makenoise();
}

class Dog implements Inter {
    public void makenoise() {
        System.out.println("Woof! Woof! Woof!");
    }
}

class DogDemo {
    public void method(Inter i) {  
        // interface as argument
        // when we call method, we actually pass an object of that interface
        i.makenoise();
    }
}