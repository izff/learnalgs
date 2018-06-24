// https://www.bilibili.com/video/av20795925/?p=10

class Student {
    public void study() {
        System.out.println("Good Good Study, Day Day Up!");
    }
}

class StudentDemo {
    public Student getStudent() {
        return new Student();
    }
}

class ChainProgram {
    public static void main(String[] args) {
        StudentDemo sd = new StudentDemo();
        
        System.out.println("Method 1: ");
        Student s = sd.getStudent();
        s.study();

        // Method 2: 
        System.out.println("Method 2: ");
        sd.getStudent().study();
        System.out.println("要求每次返回所得均为对象");
    }
}