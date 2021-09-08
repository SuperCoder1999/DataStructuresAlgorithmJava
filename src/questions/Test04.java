package questions;

import java.util.ArrayList;

/*
    集合中的对象是引用的，还是复制拷贝的？
    ---答：的确是引用的，修改对象后，集合中的元素也发生改变
 */
public class Test04 {
    public static void main(String[] args) {
        Parent p1 = new Parent(1);
        Parent p2 = new Parent(2);
        Parent p3 = new Parent(3);
        ArrayList<Parent> pList = new ArrayList<Parent>();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);

        //修改其中一个元素对象
        p1.value = 2;

        System.out.println(pList);
    }
}

class Parent {
    public int value;

    public Parent(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "value=" + value +
                '}';
    }
}

