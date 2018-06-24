/*
 * Rule 1
 * 本类中的四种修饰符修饰的方法均能够访问
 * Rule 2
 * 同一个包下的子类，除了 private 余下三种均能访问
 * 同一个包下的其他类，除了 private 余下三种均能访问
 */

package com.test;

public class SamePackage {
    public static void main(String[] args) {
        Father f = new Father();
        //f.show1(); 
        System.out.println("Private cannot be accessed! The rest can.");
        f.show2();
        f.show3();
        f.show4();
        System.out.println("-------------");
    }
}