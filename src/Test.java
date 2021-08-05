public class Test {
    public static void main(String[] args) {
        A a = new A();
        test(a);
        A a1 = new A();
        a1.a = 110;
        a = a1;
        a.f1();
    }

    public static void test(A a) {
        A a1 = new A();
        a1.a = 12;
        a = a1;
        // 其实 a就是传参时复制过来的地址。a可以改变 a对象实例中的成员。
        // 但是a的变化(本质是地址的变化) 和对象实例没关系。
        // 就像是C语言中的指针，需要解引用才能真正变换 指针指向的内容。
    }
}

class A {
    int a = 10;
    public void f1() {
        System.out.println(a);
    }
}