/*
 * Rule 1
 * 本类中的四种修饰符修饰的方法均能够访问
 * 
 * Rule 2
 * 同一个包下的子类和无关类，除了 private 余下三种均能访问
 * 
 * Rule 3
 * 不同包下子类，default 和 private 均不可访问
 * protected 就是为子类设计的 
 * 
 * Rule 4
 * 不同包下的无关类，仅有 public 可以访问
 */

package com.test2;

import com.test.Father;

public class AnotherPackage {
    public static void main(String[] args) {
        Father f = new Father();
        //f.show1();
        //f.show2();
        //f.show3();
        f.show4();
    }
}