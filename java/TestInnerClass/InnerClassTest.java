class Outer_ {
    public int num = 10;
    class Inner {
        public int num = 20;
        public void show() {
            int num = 30;
            // 要求依次输出 30 20 10
            System.out.println(num);
            System.out.println(this.num);
            //因为内部类和外部类没有继承关系，所以不能用super
            //System.out.println(super.num);
            
            // method 1:
            System.out.println(new Outer_().num);
            // method 2:
            // 通过外部类名限定this对象
            System.out.println(Outer_.this.num);
        }
    }
}

class InnerClassTest {
    public static void main(String[] args) {
        Outer_.Inner oi = new Outer_().new Inner();
        oi.show();
    }
}