/*
 * 其实就是文件夹
 * 其作用是对类进行分类管理，相同类名放在不同的包里面
 * 多级包用 . 分开
 * 
 *  划分的方法：
 *     按模块划分
 *     按功能划分
 *  分包的注意事项：
 *  A package语句必须是程序的第一条可执行语句
 *  B package语句在一个java文件中只能有一个
 *  C 如果没有package，默认表示无包名
 * 
 * 带包的编译和执行：
 *  A 手动 
 *    编写带包的 java 文件
 *    通过 javac 编译 java 文件
 *    手动创建包名的文件夹
 *    把 javac 生成的 class 文件放在文件夹下面
 *    回到包文件夹的上一层，带包运行
 *     java cn.itcast.HelloWorld
 *  B 自动
 *    javac 编译时带上 -d . 参数即可
 *    javac 能自动创建各级文件夹
 */

//package cn.itcast;

package TestPackage;

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
    