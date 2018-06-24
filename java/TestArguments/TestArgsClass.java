class TestArgsClass {
    public static void main(String[] args) {
        Student s = new Student();
        StudentDemo sd = new StudentDemo();
        // class as args of method, then what method really want is 
        // a object of the class
        sd.method(s);
        
        // we can also use a anonymous object for the method
        sd.method(new Student());
    }
}

class StudentDemo {
    public void method(Student s) {
        s.study();
    }
}

class Student {
    public void study() {
        System.out.println("Good Good Study, Day Day Up!");
    }
}