/*
 * 权限修饰符
 * 
 * 
 * 
 * 
 * 
 */

package com.test;

public class Father {
    private void show1() {
        System.out.println("private Show()");
    }

    void show2() {
        System.out.println("default (friendly) Show()");
    }
    
    protected void show3() {
        System.out.println("protected Show()");
    }
    
    public void show4() {
        System.out.println("public Show()");
    }
    
    public static void main(String[] args) {
        Father f = new Father();
        f.show1(); 
        f.show2();
        f.show3();
        f.show4();
        System.out.println("-------------");
    }
}